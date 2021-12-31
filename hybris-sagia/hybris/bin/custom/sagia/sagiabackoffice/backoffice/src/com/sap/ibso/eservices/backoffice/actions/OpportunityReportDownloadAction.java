package com.sap.ibso.eservices.backoffice.actions;

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
import java.util.Set;

import javax.annotation.Resource;

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

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.permissions.PermissionFacade;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import com.hybris.cockpitng.search.data.pageable.PageableList;
import com.hybris.cockpitng.util.type.BackofficeTypeUtils;
import com.sap.ibso.eservices.core.handlers.AnswersAndQuestionsHandler;
import com.sap.ibso.eservices.core.model.TicketAnswerModel;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.util.Config;

public class OpportunityReportDownloadAction extends AbstractComponentWidgetAdapterAware
implements CockpitAction<String, Pageable<? extends ItemModel>>
{
	private static final Logger LOG = LoggerFactory.getLogger(OpportunityReportDownloadAction.class);

	protected static final String LABEL_EXCEL_EXPORT_ACTION_CONFIRMATION = "excelExportAction.confirmation";
	protected static final String PARAM_CONFIRMATION_THRESHOLD = "confirmation.threshold";
	protected static final String MODEL_SELECTED_OBJECTS = "selectedObjects";
	protected static final String MODEL_PAGEABLE = "pageable";
	protected static final String FILE_LAYOUT = "filepath.file.layout";
	protected static final String OUTPUT_FILE_NAME = "filepath.output.file.name";
	protected static final String DATE_FORMAT = "MMM, d, yy, hh:mm:ss a";
	protected static final String NO_ROWS_MSG = "export.no.rows.report.msg";
	protected static final String NO_ROWS_TITLE = "export.no.rows.report.title";
	protected static final String CONTENT_TYPE = "text/comma-separated-values;charset=UTF-8";

	@Resource
	private TypeService typeService;
	@Resource
	private PermissionFacade permissionFacade;
	@Resource
	private BackofficeTypeUtils backofficeTypeUtils;
	@Resource
	private ConfigurationService configurationService;
	@Resource
	AnswersAndQuestionsHandler answersAndQuestionsHandler;

	@Override
	public ActionResult<Pageable<? extends ItemModel>> perform(final ActionContext<String> ctx)
	{
		if (LOG.isDebugEnabled())
		{			
			LOG.debug("Opportunity Report - Start Perform");
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
			LOG.debug("Opportunity Report - Ends Perform");
		}

		return result;
	}

	private void exportDataToExcel(final String inputFilePath, final String outputFilePath, Pageable<ItemModel> excelExportData) throws FileNotFoundException, IOException
	{
		final XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(inputFilePath)));
		final XSSFSheet sheet = workbook.getSheetAt(0);

		int rowNum = sheet.getLastRowNum() + 1;

		for (ItemModel itemModel : excelExportData.getAllResults())
		{
			if (itemModel != null && itemModel instanceof CsTicketModel)
			{
				CsTicketModel csTicket = (CsTicketModel) itemModel;
				rowNum = toSheet(sheet, rowNum, csTicket);
			}
		}

		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		workbook.write(buffer);

		Filedownload.save(buffer.toByteArray(), CONTENT_TYPE, outputFilePath);

		buffer.close();
	}

	private int toSheet(final XSSFSheet sheet, int rowNum, final CsTicketModel csTicket)
	{
		int j = 0;
		final XSSFRow row = sheet.createRow(rowNum++);

		row.createCell(j++).setCellValue(csTicket.getTicketID());
		row.createCell(j++).setCellValue(csTicket.getCustomer() != null ? csTicket.getCustomer().getName() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getBaseSite() != null ? csTicket.getBaseSite().getName() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getCustomer() != null ? csTicket.getCustomer().getUid() : StringUtils.EMPTY);
		// "Customer phone number");
		row.createCell(j++).setCellValue(getPhone(csTicket));
		row.createCell(j++).setCellValue(csTicket.getHeadline());
		row.createCell(j++).setCellValue(csTicket.getCreationtime() != null ? new SimpleDateFormat(DATE_FORMAT).format(csTicket.getCreationtime()) : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getState() != null ? csTicket.getState().getCode() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getCategory() != null ? csTicket.getCategory().getCode() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getPriority() != null ? csTicket.getPriority().getCode() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getAssignedAgent() != null ? csTicket.getAssignedAgent().getDisplayName() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getAssignedGroup() != null ? csTicket.getAssignedGroup().getDisplayName() : StringUtils.EMPTY);
		row.createCell(j++).setCellValue(csTicket.getLocation());
		row.createCell(j++).setCellValue(csTicket.getConfiguration() != null ? csTicket.getConfiguration().getCode() : StringUtils.EMPTY);
		// "Investment Size");
		row.createCell(j++).setCellValue("");
		fillAnswersAndQuestions(csTicket, row, j);

		return rowNum;
	}

	private void fillAnswersAndQuestions(CsTicketModel csTicket, XSSFRow row, int j)
	{
		Set<TicketAnswerModel> ticketAnswers = csTicket.getAnswers();

		for (TicketAnswerModel ticketAnswerModel : ticketAnswers){
			if(ticketAnswerModel.getTicketQuestion() != null){
				row.createCell(j++).setCellValue(ticketAnswerModel.getTicketQuestion().getQuestion());

				String answerFormated = ticketAnswerModel.getAnswer().replace(",", " ").replace(";", ":").replace('"', ' ');
				row.createCell(j++).setCellValue(answerFormated);
			}
		}

		return;
	}

	private String getPhone(CsTicketModel csTicket)
	{
		String phone = null;
		if (csTicket.getCustomer() != null && csTicket.getCustomer().getDefaultShipmentAddress() != null)
		{
			phone = csTicket.getCustomer().getDefaultShipmentAddress().getPhone1();
			if (csTicket.getCustomer().getDefaultShipmentAddress().getPhone1() != null)
			{
				return phone;
			}
		}
		else if (csTicket.getCustomer() != null && !csTicket.getCustomer().getAddresses().isEmpty())
		{
			phone = csTicket.getCustomer().getAddresses().stream().findFirst().get().getPhone1();
			if (phone != null)
			{
				return phone;
			}
		}
		return phone;
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
