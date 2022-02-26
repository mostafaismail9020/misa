package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.FinancialSurveyAffiliateModel;
import com.sap.ibso.eservices.facades.data.finance.survey.Affiliate;
import com.sap.ibso.eservices.facades.data.license.amendment.Transaction;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

public class FinancialSurveyAffiliatePopulator implements Populator<FinancialSurveyAffiliateModel, Affiliate> {


    @Resource
    private FinancialSurveyTransactionPopulator financialSurveyTransactionPopulator;


    @Override
    public void populate(FinancialSurveyAffiliateModel financialSurveyAffiliateModel, Affiliate affiliate) throws ConversionException {

        affiliate.setSrId(financialSurveyAffiliateModel.getPk().getLong().toString());
        affiliate.setAction("2");
        affiliate.setAffiliateType(financialSurveyAffiliateModel.getAffiliateType());
        affiliate.setCompanyCountry(financialSurveyAffiliateModel.getCompanyCountry());
        affiliate.setAffiliateNameEnglish(financialSurveyAffiliateModel.getAffiliateNameEnglish());
        affiliate.setAffiliateSubsector(financialSurveyAffiliateModel.getAffiliateSubsector());
        affiliate.setAffiliateSector(financialSurveyAffiliateModel.getAffiliateSector());
        affiliate.setAffiliateGender(financialSurveyAffiliateModel.getAffiliateGender());
        affiliate.setAffiliateNationalityCurrent(financialSurveyAffiliateModel.getAffiliateNationalityCurrent());
        affiliate.setAffiliateCountry(financialSurveyAffiliateModel.getAffiliateCountry());
        affiliate.setAffiliateMultinationalCompany(financialSurveyAffiliateModel.getAffiliateMultinationalCompany());

        Transaction transaction = new Transaction ();
        financialSurveyTransactionPopulator.populate(financialSurveyAffiliateModel.getTransaction(),transaction);
        affiliate.setTransaction(transaction);
    }
}
