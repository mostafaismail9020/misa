package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.enums.FinancialSurveyCompanyStatus;
import com.sap.ibso.eservices.core.enums.FinancialSurveyScaleLevel;
import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyQuarterModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyBranchDAO;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyShareholderDAO;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveySubsidiaryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.finance.survey.Shareholder;
import com.sap.ibso.eservices.facades.data.finance.survey.Affiliate;
import com.sap.ibso.eservices.facades.data.license.amendment.Branch;
import com.sap.ibso.eservices.facades.data.license.amendment.BusinessActivity;
import com.sap.ibso.eservices.facades.data.license.amendment.Subsidiary;
import com.sap.ibso.eservices.sagiaservices.services.financialsurvey.SagiaFinancialSurveyService;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.commercefacades.user.data.ShareholderEquity;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 *
 */
public class FinancialSurveyPopulator implements Populator<FinancialSurveyModel, FinancialSurvey> {

    private static final String APPLICATION_PDF = "application/pdf";

    @Resource
    private FinancialSurveyBranchPopulator financialSurveyBranchPopulator;

    @Resource
    private FinancialSurveyShareholderPopulator financialSurveyShareholderPopulator;

    @Resource
    private FinancialSurveyAffiliatePopulator financialSurveyAffiliatePopulator;


    @Resource
    private FinancialSurveySubsidiaryPopulator financialSurveySubsidiaryPopulator;

    @Resource(name = "sagiaFormatProvider")
    private SagiaFormatProvider sagiaFormatProvider;

    @Resource
    private FinancialSurveyShareholderDAO financialSurveyShareholderDAO;

    @Resource
    private FinancialSurveyBranchDAO financialSurveyBranchDAO;

    @Resource
    private FinancialSurveySubsidiaryDAO financialSurveySubsidiaryDAO;

    @Resource
    private SagiaFinancialSurveyService sagiaFinancialSurveyService;

    @Override
    public void populate(FinancialSurveyModel financialSurveyModel, FinancialSurvey financialSurvey) throws ConversionException {


        //Get Previous Quarter Code
        FinancialSurveyQuarterModel previousQuarter = financialSurveyModel.getQuarter().getPreviousQuarter();
        FinancialSurveyModel previousQuarterFinancialSurveyModel = null;
        if(previousQuarter != null ){
            previousQuarterFinancialSurveyModel = sagiaFinancialSurveyService.getFinancialSurvey(previousQuarter.getCode());
        }


        List<Shareholder> shareholderList = new ArrayList<>();
         financialSurveyModel.getShareholders().forEach(shareholderModel -> {Shareholder shareholder = new Shareholder();
            financialSurveyShareholderPopulator.populate(shareholderModel,shareholder);
            shareholderList.add(shareholder);});
        financialSurvey.setShareholders(shareholderList);

        // Get List of branches.
        List<Branch> branchList = new ArrayList<>();
        financialSurveyModel.getBranches().forEach( branchModel -> {
            Branch branch = new Branch();
            financialSurveyBranchPopulator.populate(branchModel,branch);
            branchList.add(branch);
                }
        );
        financialSurvey.setBranches(branchList);


        // Get List of branches.

        List<Affiliate> affiliateList = new ArrayList<>();
        financialSurveyModel.getAffiliates().forEach(affiliateModel -> {Affiliate affiliate = new Affiliate();
            financialSurveyAffiliatePopulator.populate(affiliateModel,affiliate);
            affiliateList.add(affiliate);});
        financialSurvey.setAffiliates(affiliateList);

        List<Subsidiary> subsidiaryList = new ArrayList<>();
        financialSurveyModel.getSubsidiaries().forEach( subsidiaryModel -> {
            Subsidiary subsidiary = new Subsidiary();
            financialSurveySubsidiaryPopulator.populate(subsidiaryModel,subsidiary);
            subsidiaryList.add(subsidiary);
                }
        );
        financialSurvey.setSubsidiaries(subsidiaryList);

        List<BusinessActivity> businessActivityList = new ArrayList<>();
        if (financialSurveyModel.getBusinessActivityId() != null ) {
            BusinessActivity businessActivity = new BusinessActivity();
            businessActivity.setId(financialSurveyModel.getBusinessActivityId());
            businessActivity.setDescription(financialSurveyModel.getBusinessActivityDescription());
            businessActivityList.add(businessActivity);
        }
        financialSurvey.setBusinessActivities(businessActivityList);

        financialSurvey.setEconomicActivityClass(financialSurveyModel.getEconomicActivityClass()!=null ? financialSurveyModel.getEconomicActivityClass().getCode() :  null);
        financialSurvey.setEconomicActivityDivision(financialSurveyModel.getEconomicActivityDivision()!=null ? financialSurveyModel.getEconomicActivityDivision().getCode() :  null);
        financialSurvey.setEconomicActivityGroup(financialSurveyModel.getEconomicActivityGroup()!=null ? financialSurveyModel.getEconomicActivityGroup().getCode() :  null);
        financialSurvey.setEconomicActivitySection(financialSurveyModel.getEconomicActivitySection()!=null ? financialSurveyModel.getEconomicActivitySection().getCode() :  null);
        financialSurvey.setEconomicActivityBranch(financialSurveyModel.getEconomicActivityBranch()!=null ? financialSurveyModel.getEconomicActivityBranch().getCode() :  null);
        financialSurvey.setEconomicActivity(financialSurveyModel.getEconomicActivity()!=null ? financialSurveyModel.getEconomicActivity().getCode() :  null);

        if(financialSurveyModel.getCompanyStatus() != null ){
            financialSurvey.setCompanyStatus(financialSurveyModel.getCompanyStatus().getCode());
        }else {
            financialSurvey.setCompanyStatus(FinancialSurveyCompanyStatus.ACTIVE.getCode());
        }

        if(FinancialSurveyScaleLevel.THOUSANDS.equals(financialSurveyModel.getScaleLevel())) {
            financialSurvey.setIsScaleLevelActualUnit(false);
        }else {
            financialSurvey.setIsScaleLevelActualUnit(true);
        }

        financialSurvey.setIsConsolidated(financialSurveyModel.isIsConsolidated());
        financialSurvey.setPaidUpCapitalCurrentQuarter(financialSurveyModel.getPaidUpCapitalCurrentQuarter());
        financialSurvey.setDisclosureCurrency(financialSurveyModel.getDisclosureCurrency());
        financialSurvey.setSuspensionDate(financialSurveyModel.getSuspensionDate()!=null?sagiaFormatProvider.formatBackEndDateToUIStr(financialSurveyModel.getSuspensionDate()):null);


     //   financialSurvey.setLicenseType(financialSurveyModel.getL);
        ShareholderEquity shareholderEquity = new ShareholderEquity();
        shareholderEquity.setAdditionalPaidUpCapitalCurrentQuarter(financialSurveyModel.getAdditionalPaidUpCapitalCurrentQuarter());
        shareholderEquity.setRetainedEarningsIncludeCurrentQuarter(financialSurveyModel.getRetainedEarningsIncludeCurrentQuarter());
        shareholderEquity.setProfitLossQuarterCurrentQuarter(financialSurveyModel.getProfitLossQuarterCurrentQuarter());
        shareholderEquity.setTotalReservesCurrentQuarter(financialSurveyModel.getTotalReservesCurrentQuarter());
        shareholderEquity.setTreasurySharesCurrentQuarter(financialSurveyModel.getTreasurySharesCurrentQuarter());
        shareholderEquity.setHeadOfficeAccountInBranchCurrentQuarter(financialSurveyModel.getHeadOfficeAccountInBranchCurrentQuarter());
        shareholderEquity.setShareholderEquityOthersCurrentQuarter(financialSurveyModel.getShareholderEquityOthersCurrentQuarter());
        shareholderEquity.setMinorityRightsCurrentQuarter(financialSurveyModel.getMinorityRightsCurrentQuarter());
        shareholderEquity.setTotalShareholderEquityCurrentQuarter(financialSurveyModel.getTotalShareholderEquityCurrentQuarter());


        if(previousQuarterFinancialSurveyModel != null ){
            shareholderEquity.setAdditionalPaidUpCapitalPreviousQuarter(previousQuarterFinancialSurveyModel.getAdditionalPaidUpCapitalCurrentQuarter());
            shareholderEquity.setRetainedEarningsIncludePreviousQuarter(previousQuarterFinancialSurveyModel.getRetainedEarningsIncludeCurrentQuarter());
            shareholderEquity.setProfitLossQuarterPreviousQuarter(previousQuarterFinancialSurveyModel.getProfitLossQuarterCurrentQuarter());
            shareholderEquity.setTotalReservesPreviousQuarter(previousQuarterFinancialSurveyModel.getTotalReservesCurrentQuarter());
            shareholderEquity.setTreasurySharesPreviousQuarter(previousQuarterFinancialSurveyModel.getTreasurySharesCurrentQuarter());
            shareholderEquity.setHeadOfficeAccountInBranchPreviousQuarter(previousQuarterFinancialSurveyModel.getHeadOfficeAccountInBranchCurrentQuarter());
            shareholderEquity.setShareholderEquityOthersPreviousQuarter(previousQuarterFinancialSurveyModel.getShareholderEquityOthersCurrentQuarter());
            shareholderEquity.setMinorityRightsPreviousQuarter(previousQuarterFinancialSurveyModel.getMinorityRightsCurrentQuarter());
            shareholderEquity.setTotalShareholderEquityPreviousQuarter(previousQuarterFinancialSurveyModel.getTotalShareholderEquityCurrentQuarter());
        }




        financialSurvey.setIsBranchSectionFilled(financialSurveyModel.isIsBranchSectionFilled());
        financialSurvey.setIsCompanyProfileSectionFilled(financialSurveyModel.isIsCompanyProfileSectionFilled());
        financialSurvey.setIsEquitySectionFilled(financialSurveyModel.isIsEquitySectionFilled());
        financialSurvey.setIsShareholdersSectionFilled(financialSurveyModel.isIsShareholdersSectionFilled());

        financialSurvey.setShareholderEquity(shareholderEquity);
    }



}
