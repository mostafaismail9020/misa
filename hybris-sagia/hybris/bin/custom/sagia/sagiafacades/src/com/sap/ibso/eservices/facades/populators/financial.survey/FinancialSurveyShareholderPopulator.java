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

    @Resource
    private FinancialSurveyPreviousQuarterTransactionPopulator financialSurveyPreviousQuarterTransactionPopulator;




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



        String shareholderNationalityOfUCPCode = "";
        if(null != financialSurveyShareholderModel.getNationalityOfUCPRef() ){
            shareholderNationalityOfUCPCode = financialSurveyShareholderModel.getNationalityOfUCPRef().getCode();
        }
        shareholder.setNationalityOfUCP(shareholderNationalityOfUCPCode);

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


        // Populate prev quarter fields.
        FinancialSurveyShareholderModel shareholderPrevQuarter = financialSurveyShareholderModel.getFinancialSurveyShareholderPreviousQuarter();
        if ( shareholderPrevQuarter != null ) {

            financialSurveyPreviousQuarterTransactionPopulator.populate(shareholderPrevQuarter.getTransaction(),transaction);


            shareholder.setPaidUpCapitalPreviousQuarter(shareholderPrevQuarter.getPaidUpCapitalCurrentQuarter());
            shareholder.setAdditionalPaidUpCapitalPreviousQuarter(shareholderPrevQuarter.getAdditionalPaidUpCapitalCurrentQuarter());
            shareholder.setRetainedEarningsIncludePreviousQuarter(shareholderPrevQuarter.getRetainedEarningsIncludeCurrentQuarter());
            shareholder.setProfitLossQuarterPreviousQuarter(shareholderPrevQuarter.getProfitLossQuarterCurrentQuarter());
            shareholder.setTotalReservesPreviousQuarter(shareholderPrevQuarter.getTotalReservesCurrentQuarter());
            shareholder.setTreasurySharesPreviousQuarter(shareholderPrevQuarter.getTreasurySharesCurrentQuarter());
            shareholder.setHeadOfficeAccountInBranchPreviousQuarter(shareholderPrevQuarter.getHeadOfficeAccountInBranchCurrentQuarter());
            shareholder.setShareholderEquityOthersPreviousQuarter(shareholderPrevQuarter.getShareholderEquityOthersCurrentQuarter());
            shareholder.setMinorityRightsPreviousQuarter(shareholderPrevQuarter.getMinorityRightsCurrentQuarter());
            shareholder.setTotalShareholderEquityPreviousQuarter(shareholderPrevQuarter.getTotalShareholderEquityCurrentQuarter());
        }

        shareholder.setTransaction(transaction);



    }
}
