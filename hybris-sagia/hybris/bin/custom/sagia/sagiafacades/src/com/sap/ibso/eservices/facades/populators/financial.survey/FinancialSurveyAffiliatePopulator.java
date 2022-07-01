package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.enums.FinancialSurveyAffiliateType;
import com.sap.ibso.eservices.core.model.FinancialSurveyAffiliateModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;
import com.sap.ibso.eservices.facades.data.finance.survey.Affiliate;
import com.sap.ibso.eservices.facades.data.license.amendment.Transaction;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

public class FinancialSurveyAffiliatePopulator implements Populator<FinancialSurveyAffiliateModel, Affiliate> {


    @Resource
    private FinancialSurveyTransactionPopulator financialSurveyTransactionPopulator;

    @Resource
    private FinancialSurveyPreviousQuarterTransactionPopulator financialSurveyPreviousQuarterTransactionPopulator;




    @Override
    public void populate(FinancialSurveyAffiliateModel financialSurveyAffiliateModel, Affiliate affiliate) throws ConversionException {

        affiliate.setSrId(financialSurveyAffiliateModel.getPk().getLong().toString());
        affiliate.setAction("2");
        affiliate.setAffiliateType(FinancialSurveyAffiliateType.INDIVIDUAL.equals(financialSurveyAffiliateModel.getAffiliateTypeRef())?"1":"2");

        String companyCountryCode = "";
        if(null!= financialSurveyAffiliateModel.getCompanyCountryRef() ){
            companyCountryCode = financialSurveyAffiliateModel.getCompanyCountryRef().getCode();
        }
        affiliate.setCompanyCountry(companyCountryCode);

        affiliate.setAffiliateNameEnglish(financialSurveyAffiliateModel.getAffiliateNameEnglish());
        affiliate.setAffiliateSubsector(financialSurveyAffiliateModel.getAffiliateSubsector());
        affiliate.setAffiliateSector(financialSurveyAffiliateModel.getAffiliateSector());
        affiliate.setAffiliateGender(financialSurveyAffiliateModel.getAffiliateGender());

        // shareholder.setShareholderCountry(financialSurveyShareholderModel.getShareholderCountry());
        String affiliateNationalityCurrentCode = "";
        if(null!= financialSurveyAffiliateModel.getAffiliateNationalityCurrentRef() ){
            affiliateNationalityCurrentCode = financialSurveyAffiliateModel.getAffiliateNationalityCurrentRef().getCode();
        }
        affiliate.setAffiliateNationalityCurrent(affiliateNationalityCurrentCode);

        String affiliateCountryCode = "";
        if(null!= financialSurveyAffiliateModel.getAffiliateCountryRef() ){
            affiliateCountryCode = financialSurveyAffiliateModel.getAffiliateCountryRef().getCode();
        }
        affiliate.setAffiliateCountry(affiliateCountryCode);

        affiliate.setAffiliateMultinationalCompany(financialSurveyAffiliateModel.getAffiliateMultinationalCompany());

        Transaction transaction = new Transaction ();
        financialSurveyTransactionPopulator.populate(financialSurveyAffiliateModel.getTransaction(),transaction);

        // Populate prev quarter fields.
        FinancialSurveyAffiliateModel financialSurveyAffiliateModelPrevQuarter = financialSurveyAffiliateModel.getFinancialSurveyAffiliatePreviousQuarter();
        if ( financialSurveyAffiliateModelPrevQuarter != null && financialSurveyAffiliateModelPrevQuarter.getTransaction() != null) {

            financialSurveyPreviousQuarterTransactionPopulator.populate(financialSurveyAffiliateModelPrevQuarter.getTransaction(),transaction);
        }
        affiliate.setTransaction(transaction);
    }
}
