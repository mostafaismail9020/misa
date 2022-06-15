package com.sap.ibso.eservices.backoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.permissions.PermissionFacade;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import com.hybris.cockpitng.search.data.pageable.PageableList;
import com.hybris.cockpitng.util.type.BackofficeTypeUtils;
import com.sap.ibso.eservices.core.model.FinancialSurveyAffiliateModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyBranchModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.model.SagiaSubsidiaryModel;
import com.sap.ibso.eservices.core.model.SagiaSurveyTransactionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCompanyProfileDAO;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class FinancialSurveyReportDownloadAction extends AbstractComponentWidgetAdapterAware
implements CockpitAction<String, Pageable<? extends ItemModel>>
{
	private static final Logger LOG = LoggerFactory.getLogger(FinancialSurveyReportDownloadAction.class);

	protected static final String LABEL_EXCEL_EXPORT_ACTION_CONFIRMATION = "excelExportAction.confirmation";
	protected static final String PARAM_CONFIRMATION_THRESHOLD = "confirmation.threshold";
	protected static final String MODEL_SELECTED_OBJECTS = "selectedObjects";
	protected static final String MODEL_PAGEABLE = "pageable";
	protected static final String FILE_LAYOUT = "finance.survey.filepath.file.layout";
	protected static final String OUTPUT_FILE_NAME = "finance.survey.filepath.output.file.name";
	protected static final String DATE_FORMAT = "MMM, d, yy, hh:mm:ss a";
	protected static final String NO_ROWS_MSG = "export.no.rows.report.msg";
	protected static final String NO_ROWS_TITLE = "export.no.rows.report.title";
	protected static final String CONTENT_TYPE = "text/comma-separated-values;charset=UTF-8";

	private static final int COMPANY_PROFILE_SHEET_NUM = 0;
	private static final int BRANCH_PROFILE_SHEET_NUM = 1;
	private static final int SUBSIDIARY_PROFILE_SHEET_NUM = 2;
	private static final int SHAREHOLDER_EQUITY_PROFILE_SHEET_NUM = 3;
	private static final int SHAREHOLDER_SHEET_NUM = 4;
	private static final int AFFILIATE_SHEET_NUM = 5;

	@Resource
	private TypeService typeService;
	@Resource
	private PermissionFacade permissionFacade;
	@Resource
	private BackofficeTypeUtils backofficeTypeUtils;
	@Resource
	private ConfigurationService configurationService;

	@Resource
	SagiaCompanyProfileDAO  sagiaCompanyProfileDAO;

	@Override
	public ActionResult<Pageable<? extends ItemModel>> perform(final ActionContext<String> ctx)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Financial Survey Report - Start Perform");
		}

		final ActionResult<Pageable<? extends ItemModel>> result = new ActionResult<>(ActionResult.ERROR);
		getData(ctx).ifPresent(excelExportData -> {
			final int totalCount = excelExportData.getTotalCount();

			if (totalCount == 0)
			{
				showNoRowsToExport(ctx);
				return;
			}

			if (isMaxRowsExceeded(ctx, totalCount))
			{
				showMaxRowsExceeded(ctx, totalCount);
			}

			try
			{
				final String inputFilePath = configurationService.getConfiguration().getString(FILE_LAYOUT);
				if (inputFilePath == null || inputFilePath.isEmpty())
				{
					throw new IllegalArgumentException("Invalid configuration for 'input file layout'");
				}

				final String outputFilePath = configurationService.getConfiguration().getString(OUTPUT_FILE_NAME);
				if (outputFilePath == null || outputFilePath.isEmpty())
				{
					throw new IllegalArgumentException("Invalid configuration for 'output file layout'");
				}
				if (LOG.isDebugEnabled())
				{
					LOG.debug(String.format("InputFilePath: %s, OutputFilePath: %s", inputFilePath, outputFilePath));
				}

				if (ctx.getData() != null)
				{
					exportDataToExcel(inputFilePath, outputFilePath, excelExportData);
				}
			}
			catch (final Exception e)
			{
				LOG.error(e.getMessage(), e);
			}
		});

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Financial Survey Report - Ends Perform");
		}

		return result;
	}

	private void exportDataToExcel(final String inputFilePath, final String outputFilePath, Pageable<ItemModel> excelExportData) throws FileNotFoundException, IOException
	{
		final XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(inputFilePath)));



		final XSSFSheet companyProfileSheet = workbook.getSheetAt(COMPANY_PROFILE_SHEET_NUM);
		final XSSFSheet branchSheet = workbook.getSheetAt(BRANCH_PROFILE_SHEET_NUM);
		final XSSFSheet subsidiarySheet = workbook.getSheetAt(SUBSIDIARY_PROFILE_SHEET_NUM);
		final XSSFSheet shareholderEquitySheet = workbook.getSheetAt(SHAREHOLDER_EQUITY_PROFILE_SHEET_NUM);
		final XSSFSheet shareholderSheet = workbook.getSheetAt(SHAREHOLDER_SHEET_NUM);
		final XSSFSheet affiliateSheet = workbook.getSheetAt(AFFILIATE_SHEET_NUM);

		int companyProfileRowNum = companyProfileSheet.getLastRowNum() + 1;
		int branchRowNum = branchSheet.getLastRowNum() + 1;
		int subsidiaryRowNum = subsidiarySheet.getLastRowNum() + 1 ;
		int shareholderEquityRowNum = shareholderEquitySheet.getLastRowNum() + 1 ;
		int shareholderRowNum = shareholderSheet.getLastRowNum() + 1 ;
		int affiliateRowNum = affiliateSheet.getLastRowNum() + 1 ;

		for (ItemModel itemModel : excelExportData.getAllResults())
		{
			if (itemModel != null && itemModel instanceof FinancialSurveyModel)
			{
				FinancialSurveyModel financialSurvey = (FinancialSurveyModel) itemModel;
				companyProfileRowNum = fillCompanyProfileSheet(companyProfileSheet, companyProfileRowNum, financialSurvey);
				branchRowNum = fillBranchSheet(branchSheet, branchRowNum, financialSurvey);
				subsidiaryRowNum = fillSubsidiarySheet(subsidiarySheet, subsidiaryRowNum , financialSurvey);
				shareholderEquityRowNum = fillShareholderEquitySheet(shareholderEquitySheet, shareholderEquityRowNum, financialSurvey);
				shareholderRowNum = fillShareholdersSheet(shareholderEquitySheet, shareholderRowNum, financialSurvey);
				affiliateRowNum = fillAffeliateSheet(affiliateSheet, affiliateRowNum, financialSurvey);
			}
		}

		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		workbook.write(buffer);

		Filedownload.save(buffer.toByteArray(), CONTENT_TYPE, outputFilePath);

		buffer.close();
	}

	private int fillCompanyProfileSheet(final XSSFSheet sheet, int rowNum, final FinancialSurveyModel financialSurvey)
	{
		int j = 0;
		final XSSFRow row = sheet.createRow(rowNum++);

		SagiaCompanyProfileModel sagiaCompanyProfileModel = sagiaCompanyProfileDAO.getSagiaCompanyProfile(financialSurvey.getUser().getPk().getLong().toString());

		row.createCell(j++).setCellValue(financialSurvey.getUser().getCustomerID());
		row.createCell(j++).setCellValue(financialSurvey.getCommercialRegistrationNo());
		row.createCell(j++).setCellValue((sagiaCompanyProfileModel != null && sagiaCompanyProfileModel.getCrIssueDate() != null) ? new SimpleDateFormat(DATE_FORMAT).format(sagiaCompanyProfileModel.getCrIssueDate()) : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(financialSurvey.getQuarter() != null ? financialSurvey.getQuarter().getCode() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(financialSurvey.getCompanyName());
		row.createCell(j++).setCellValue(sagiaCompanyProfileModel != null ? sagiaCompanyProfileModel.getUnifiedNo700(): StringUtils.EMPTY );
		row.createCell(j++).setCellValue((sagiaCompanyProfileModel != null &&  sagiaCompanyProfileModel.getIncorporationDate() != null ) ? new SimpleDateFormat(DATE_FORMAT).format(sagiaCompanyProfileModel.getIncorporationDate()) : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(financialSurvey.getBusinessActivityId());
		row.createCell(j++).setCellValue(financialSurvey.getCompanyStatus() != null ? financialSurvey.getCompanyStatus().getCode(): StringUtils.EMPTY);
		row.createCell(j++).setCellValue(financialSurvey.getSuspensionDate() != null ? new SimpleDateFormat(DATE_FORMAT).format(financialSurvey.getSuspensionDate()) : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaCompanyProfileModel != null ? sagiaCompanyProfileModel.getFinancialManagerName() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaCompanyProfileModel != null ? sagiaCompanyProfileModel.getFinancialManagerEmail() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaCompanyProfileModel != null ? sagiaCompanyProfileModel.getFinancialManagerTelephone() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(financialSurvey.getDisclosureCurrency());

		row.createCell(j++).setCellValue("");


		return rowNum;
	}


	private int fillBranchSheet(final XSSFSheet sheet, int rowNum, final FinancialSurveyModel financialSurvey)
	{
		for ( FinancialSurveyBranchModel financialSurveyBranchModel : financialSurvey.getBranches() ){
			int j = 0;
			final XSSFRow row = sheet.createRow(rowNum++);
			row.createCell(j++).setCellValue(financialSurveyBranchModel.getFinancialSurvey().getUser().getCustomerID());
			row.createCell(j++).setCellValue(financialSurveyBranchModel.getFinancialSurvey().getCommercialRegistrationNo());
			row.createCell(j++).setCellValue(financialSurveyBranchModel.getFinancialSurvey().getQuarter() != null ? financialSurveyBranchModel.getFinancialSurvey().getQuarter().getCode() : StringUtils.EMPTY);
			row.createCell(j++).setCellValue(financialSurveyBranchModel.getName());
			row.createCell(j++).setCellValue(financialSurveyBranchModel.getType());
			row.createCell(j++).setCellValue(financialSurveyBranchModel.getWeight());
			row.createCell(j++).setCellValue( (financialSurveyBranchModel.getAddress()!= null && financialSurveyBranchModel.getAddress().getEmail()!=null)?financialSurveyBranchModel.getAddress().getEmail():StringUtils.EMPTY);
			row.createCell(j++).setCellValue( (financialSurveyBranchModel.getAddress()!= null && financialSurveyBranchModel.getAddress().getTelephone()!=null)?financialSurveyBranchModel.getAddress().getTelephone():StringUtils.EMPTY);
			row.createCell(j++).setCellValue( (financialSurveyBranchModel.getAddress()!= null && financialSurveyBranchModel.getAddress().getWebsite()!=null)?financialSurveyBranchModel.getAddress().getWebsite():StringUtils.EMPTY);
			row.createCell(j++).setCellValue("");
		}
		return rowNum;
	}


	private int fillSubsidiarySheet(final XSSFSheet sheet, int rowNum, final FinancialSurveyModel financialSurvey)
	{
		for ( SagiaSubsidiaryModel sagiaSubsidiaryModel : financialSurvey.getSubsidiaries() ){
			int j = 0;
			final XSSFRow row = sheet.createRow(rowNum++);
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getFinancialSurvey().getUser().getCustomerID());
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getFinancialSurvey().getCommercialRegistrationNo());
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getFinancialSurvey().getQuarter() != null ? sagiaSubsidiaryModel.getFinancialSurvey().getQuarter().getCode() : StringUtils.EMPTY);
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getName());
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getCommercialRegistrationNo());
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getUnifiedNo700());
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getContribution());
			row.createCell(j++).setCellValue(sagiaSubsidiaryModel.getIsDataIncludedInHeadOffice());
			row.createCell(j++).setCellValue("");
		}
		return rowNum;
	}

	private int fillShareholderEquitySheet(final XSSFSheet sheet, int rowNum, final FinancialSurveyModel financialSurvey)
	{
			int j = 0;
			final XSSFRow row = sheet.createRow(rowNum++);
		    row.createCell(j++).setCellValue(financialSurvey.getUser().getCustomerID());
		    row.createCell(j++).setCellValue(financialSurvey.getCommercialRegistrationNo());
		    row.createCell(j++).setCellValue(financialSurvey.getQuarter() != null ? financialSurvey.getQuarter().getCode() : StringUtils.EMPTY);


			row.createCell(j++).setCellValue(financialSurvey.getPaidUpCapitalCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getAdditionalPaidUpCapitalCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getRetainedEarningsIncludeCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getProfitLossQuarterCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getTotalReservesCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getTreasurySharesCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getHeadOfficeAccountInBranchCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getShareholderEquityOthersCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getMinorityRightsCurrentQuarter());
		    row.createCell(j++).setCellValue(financialSurvey.getTotalShareholderEquityCurrentQuarter());

			row.createCell(j++).setCellValue("");
		return rowNum;
	}

	private int fillShareholdersSheet(final XSSFSheet sheet, int rowNum, final FinancialSurveyModel financialSurvey)
	{
		for ( FinancialSurveyShareholderModel financialSurveyShareholderModel : financialSurvey.getShareholders() ){
			int j = 0;
			final XSSFRow row = sheet.createRow(rowNum++);
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getFinancialSurvey().getUser().getCustomerID());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getFinancialSurvey().getCommercialRegistrationNo());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getFinancialSurvey().getQuarter() != null ? financialSurveyShareholderModel.getFinancialSurvey().getQuarter().getCode() : StringUtils.EMPTY);
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderTypeRef() != null ? financialSurveyShareholderModel.getShareholderTypeRef().getCode() : StringUtils.EMPTY );
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderNameEnglish());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderGender() != null ? financialSurveyShareholderModel.getShareholderGender() : StringUtils.EMPTY );
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderNationalityCurrentRef() != null ? financialSurveyShareholderModel.getShareholderNationalityCurrentRef().getName() : StringUtils.EMPTY );
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderCountryRef() != null ? financialSurveyShareholderModel.getShareholderCountryRef().getName() : StringUtils.EMPTY );
			// missing nationalty of ucp and economic activity for ucp

			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderPercentage());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderCapital());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderVotingPower());

			// prefered shares is missing
			row.createCell(j++).setCellValue(StringUtils.EMPTY);
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getValueOfReverseInvestment());

			//Transaction
			j = fillTransactionAttributes(financialSurveyShareholderModel.getTransaction(), j, row);

			// Equity shareholder

			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getAdditionalPaidUpCapitalCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getRetainedEarningsIncludeCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getProfitLossQuarterCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getTotalReservesCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getTreasurySharesCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getHeadOfficeAccountInBranchCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getShareholderEquityOthersCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getMinorityRightsCurrentQuarter());
			row.createCell(j++).setCellValue(financialSurveyShareholderModel.getTotalShareholderEquityCurrentQuarter());


			row.createCell(j++).setCellValue("");
		}
		return rowNum;
	}


	private int fillAffeliateSheet(final XSSFSheet sheet, int rowNum, final FinancialSurveyModel financialSurvey)
	{
		for ( FinancialSurveyAffiliateModel financialSurveyAffiliateModel : financialSurvey.getAffiliates() ){
			int j = 0;
			final XSSFRow row = sheet.createRow(rowNum++);
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getFinancialSurvey().getUser().getCustomerID());
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getFinancialSurvey().getCommercialRegistrationNo());
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getFinancialSurvey().getQuarter() != null ? financialSurveyAffiliateModel.getFinancialSurvey().getQuarter().getCode() : StringUtils.EMPTY);
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateTypeRef() != null ? financialSurveyAffiliateModel.getAffiliateTypeRef().getCode() : StringUtils.EMPTY );
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateNameEnglish());
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateGender() != null ? financialSurveyAffiliateModel.getAffiliateGender() : StringUtils.EMPTY );
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateNationalityCurrentRef() != null ? financialSurveyAffiliateModel.getAffiliateNationalityCurrentRef().getName() : StringUtils.EMPTY );
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateCountryRef() != null ? financialSurveyAffiliateModel.getAffiliateCountryRef().getName() : StringUtils.EMPTY );

			// sector needs to be convetreted to a ref
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateSector());
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateSubsector());
			row.createCell(j++).setCellValue(financialSurveyAffiliateModel.getAffiliateMultinationalCompany());

			//Transaction
			j = fillTransactionAttributes(financialSurveyAffiliateModel.getTransaction(), j, row);


			row.createCell(j++).setCellValue("");
		}
		return rowNum;
	}

	private int fillTransactionAttributes(SagiaSurveyTransactionModel sagiaSurveyTransactionModel, int j, XSSFRow row) {
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getTradeCreditCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getLoansAssetsCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getInterestReceivedCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getDividendsReceivedCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getExpensesReceivableCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getExpensesReceivableCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getInsuranceCommissionReceivableCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getOtherDebitCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getTotalDebitCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getTotalCreditCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getLoansLiabilitiesCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getInterestPayableCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getDividendsPaidCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getExpensesPaidCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getPurchaseProductionSuppliesCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getPurchaseMachineryCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getCurrentCreditAccountCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getExpensesPaidCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getInsuranceCommissionPayableCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getOtherCreditCurrentQuarter() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(sagiaSurveyTransactionModel!=null ? sagiaSurveyTransactionModel.getTotalCreditCurrentQuarter(): StringUtils.EMPTY);
		return j;
	}


	protected void showNoRowsToExport(final ActionContext<String> ctx)
	{
		Messagebox.show(ctx.getLabel(NO_ROWS_MSG), ctx.getLabel(NO_ROWS_TITLE), Messagebox.OK, Messagebox.EXCLAMATION);
	}

	protected void showMaxRowsExceeded(final ActionContext<String> ctx, final int rows)
	{
		Messagebox.show(ctx.getLabel("export.max.rows.exceeded.msg", new Integer[]
				{ rows, getExportMaxRows(ctx) }), ctx.getLabel("export.max.rows.exceeded.title"), Messagebox.OK, Messagebox.EXCLAMATION);
	}

	protected boolean isMaxRowsExceeded(final ActionContext<String> ctx, final int rows)
	{
		return rows > getExportMaxRows(ctx);
	}

	@Override
	public boolean needsConfirmation(final ActionContext<String> ctx)
	{
		final int size = getSize(ctx);
		return size > getConfirmationThresholdValue(ctx) && size <= getExportMaxRows(ctx);
	}

	protected int getExportMaxRows(final ActionContext<String> ctx)
	{
		return Config.getInt("backoffice.excel.export.max.rows", Integer.MAX_VALUE);
	}

	@Override
	public String getConfirmationMessage(final ActionContext<String> ctx)
	{
		return ctx.getLabel(LABEL_EXCEL_EXPORT_ACTION_CONFIRMATION, new Integer[]
				{ getSize(ctx), getConfirmationThresholdValue(ctx) });
	}

	@Override
	public boolean canPerform(final ActionContext<String> ctx)
	{
		final String typeCode = StringUtils.isNotBlank(ctx.getData()) ? ctx.getData() : getSelectedItemsType(ctx).orElse(StringUtils.EMPTY);
		return StringUtils.isNotEmpty(typeCode) && typeService.isAssignableFrom(ItemModel._TYPECODE, typeCode) && permissionFacade.canReadType(typeCode);
	}

	public Optional<Pageable<ItemModel>> getData(final ActionContext<String> ctx)
	{
		return Optional.ofNullable(getSelectedItems(ctx).orElseGet(() -> getPageable(ctx)));
	}

	protected Optional<Pageable<ItemModel>> getSelectedItems(final ActionContext<String> ctx)
	{
		final Collection<Object> selectedObjects = getSelectedItemsFromModel(ctx);
		if (CollectionUtils.isNotEmpty(selectedObjects))
		{
			final String typeCode = backofficeTypeUtils.findClosestSuperType(new ArrayList<>(selectedObjects));
			return Optional.of(new PageableList(new ArrayList(selectedObjects), selectedObjects.size(), typeCode));
		}
		return Optional.empty();
	}

	protected Collection<Object> getSelectedItemsFromModel(final ActionContext<String> ctx)
	{
		final WidgetModel widgetModel = getWidgetModel(ctx);
		return widgetModel != null ? widgetModel.getValue(MODEL_SELECTED_OBJECTS, widgetModel.getValueType(MODEL_SELECTED_OBJECTS))
				: Collections.emptyList();
	}

	protected Optional<String> getSelectedItemsType(final ActionContext<String> ctx)
	{
		final Collection<Object> selection = getSelectedItemsFromModel(ctx);
		if (CollectionUtils.isNotEmpty(selection))
		{
			return Optional.ofNullable(backofficeTypeUtils.findClosestSuperType(new ArrayList<>(selection)));
		}
		return Optional.empty();
	}

	protected Pageable<ItemModel> getPageable(final ActionContext<String> ctx)
	{
		final WidgetModel widgetModel = getWidgetModel(ctx);
		return widgetModel != null ? widgetModel.getValue(getPageableModelProperty(ctx), Pageable.class) : null;
	}

	protected WidgetModel getWidgetModel(final ActionContext<String> ctx)
	{
		return (WidgetModel) ctx.getParameter(ActionContext.PARENT_WIDGET_MODEL);
	}

	public int getSize(final ActionContext<String> ctx)
	{
		return getData(ctx).map(Pageable::getTotalCount).orElse(0);
	}

	protected String getPageableModelProperty(final ActionContext<String> ctx)
	{
		final String pageableModelProperty = (String) ctx.getParameter(MODEL_PAGEABLE);
		return StringUtils.isNotEmpty(pageableModelProperty) ? pageableModelProperty : MODEL_PAGEABLE;
	}

	protected int getConfirmationThresholdValue(final ActionContext<String> ctx)
	{
		final String confirmationThreshold = String.valueOf(ctx.getParameter(PARAM_CONFIRMATION_THRESHOLD));
		return IntegerValidator.getInstance().isValid(confirmationThreshold) ? Integer.parseInt(confirmationThreshold) : 500;
	}
}
