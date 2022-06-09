package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.enums.FinancialSurveyShareholderType;
import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;
import com.sap.ibso.eservices.facades.data.finance.survey.Shareholder;
import com.sap.ibso.eservices.facades.data.license.amendment.Transaction;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

public class FinancialSurveyShareholderPopulator implements Populator<FinancialSurveyShareholderModel, Shareholder> {


    @Resource
    private FinancialSurveyTransactionPopulator financialSurveyTransactionPopulator;




    @Override
    public void populate(FinancialSurveyShareholderModel financialSurveyShareholderModel, Shareholder shareholder) throws ConversionException {

        shareholder.setSrId(financialSurveyShareholderModel.getPk().getLong().toString());
        shareholder.setAction("2");
        //shareholder.setShareholderType(financialSurveyShareholderModel.getShareholderType());
        shareholder.setShareholderType(FinancialSurveyShareholderType.INDIVIDUAL.equals(financialSurveyShareholderModel.getShareholderTypeRef())?"1":"2");


        // shareholder.setShareholderCountry(financialSurveyShareholderModel.getShareholderCountry());
        String companyCountryCode = "";
        if(null!= financialSurveyShareholderModel.getCompanyCountryRef() ){
            companyCountryCode = financialSurveyShareholderModel.getCompanyCountryRef().getCode();
        }
        shareholder.setCompanyCountry(companyCountryCode);
        shareholder.setShareholderNameEnglish(financialSurveyShareholderModel.getShareholderNameEnglish());
        shareholder.setShareholderSector(financialSurveyShareholderModel.getShareholderSector());
        shareholder.setShareholderSubsector(financialSurveyShareholderModel.getShareholderSubsector());
        shareholder.setShareholderGender(financialSurveyShareholderModel.getShareholderGender());
        //shareholder.setShareholderNationalityCurrent(financialSurveyShareholderModel.getShareholderNationalityCurrent());
        String shareholderNationalityCurrentCode = "";
        if(null!= financialSurveyShareholderModel.getShareholderNationalityCurrentRef() ){
            shareholderNationalityCurrentCode = financialSurveyShareholderModel.getShareholderNationalityCurrentRef().getCode();
        }
        shareholder.setShareholderNationalityCurrent(shareholderNationalityCurrentCode);

        // shareholder.setShareholderCountry(financialSurveyShareholderModel.getShareholderCountry());
        String shareholderCountryCode = "";
        if(null!= financialSurveyShareholderModel.getShareholderCountryRef() ){
            shareholderCountryCode = financialSurveyShareholderModel.getShareholderCountryRef().getCode();
        }
        shareholder.setShareholderCountry(shareholderCountryCode);
        shareholder.setShareholderPercentage(financialSurveyShareholderModel.getShareholderPercentage());
        shareholder.setShareholderCapital(financialSurveyShareholderModel.getShareholderCapital());
        shareholder.setShareholderIsVotingPower(financialSurveyShareholderModel.isShareholderIsVotingPower());
        shareholder.setShareholderVotingPower(financialSurveyShareholderModel.getShareholderVotingPower());
        shareholder.setShareholderHasPreferredShares(financialSurveyShareholderModel.isShareholderHasPreferredShares());
        shareholder.setShareholderHaveReverseInvestment(financialSurveyShareholderModel.isShareholderHaveReverseInvestment());
        shareholder.setValueOfReverseInvestment(financialSurveyShareholderModel.getValueOfReverseInvestment());
        shareholder.setShareholderMultinationalCompany(financialSurveyShareholderModel.getShareholderMultinationalCompany());
        shareholder.setNationalityOfUCP(financialSurveyShareholderModel.getNationalityOfUCP());


        shareholder.setPaidUpCapitalCurrentQuarter(financialSurveyShareholderModel.getPaidUpCapitalCurrentQuarter());
        shareholder.setAdditionalPaidUpCapitalCurrentQuarter(financialSurveyShareholderModel.getAdditionalPaidUpCapitalCurrentQuarter());
        shareholder.setRetainedEarningsIncludeCurrentQuarter(financialSurveyShareholderModel.getRetainedEarningsIncludeCurrentQuarter());
        shareholder.setProfitLossQuarterCurrentQuarter(financialSurveyShareholderModel.getProfitLossQuarterCurrentQuarter());
        shareholder.setTotalReservesCurrentQuarter(financialSurveyShareholderModel.getTotalReservesCurrentQuarter());
        shareholder.setTreasurySharesCurrentQuarter(financialSurveyShareholderModel.getTreasurySharesCurrentQuarter());
        shareholder.setHeadOfficeAccountInBranchCurrentQuarter(financialSurveyShareholderModel.getHeadOfficeAccountInBranchCurrentQuarter());
        shareholder.setShareholderEquityOthersCurrentQuarter(financialSurveyShareholderModel.getShareholderEquityOthersCurrentQuarter());
        shareholder.setMinorityRightsCurrentQuarter(financialSurveyShareholderModel.getMinorityRightsCurrentQuarter());
        shareholder.setTotalShareholderEquityCurrentQuarter(financialSurveyShareholderModel.getTotalShareholderEquityCurrentQuarter());

        Transaction transaction = new Transaction ();
        financialSurveyTransactionPopulator.populate(financialSurveyShareholderModel.getTransaction(),transaction);
        shareholder.setTransaction(transaction);
    }
}
