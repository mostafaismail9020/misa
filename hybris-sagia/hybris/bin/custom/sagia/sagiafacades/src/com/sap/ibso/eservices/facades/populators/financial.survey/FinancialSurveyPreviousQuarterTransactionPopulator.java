package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.SagiaSurveyTransactionModel;
import com.sap.ibso.eservices.facades.data.license.amendment.Transaction;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class FinancialSurveyPreviousQuarterTransactionPopulator implements Populator<SagiaSurveyTransactionModel, Transaction> {


    @Override
    public void populate(SagiaSurveyTransactionModel sagiaSurveyTransactionModel, Transaction transaction) throws ConversionException {


		transaction.setTradeDebitPreviousQuarter(sagiaSurveyTransactionModel.getTradeDebitCurrentQuarter());
		transaction.setTradeCreditPreviousQuarter(sagiaSurveyTransactionModel.getTradeCreditCurrentQuarter());
		transaction.setLoansAssetsPreviousQuarter(sagiaSurveyTransactionModel.getLoansAssetsCurrentQuarter());
		transaction.setLoansLiabilitiesPreviousQuarter(sagiaSurveyTransactionModel.getLoansLiabilitiesCurrentQuarter());
		transaction.setInterestReceivedPreviousQuarter(sagiaSurveyTransactionModel.getInterestReceivedCurrentQuarter());
		transaction.setInterestPayablePreviousQuarter(sagiaSurveyTransactionModel.getInterestPayableCurrentQuarter());
		transaction.setDividendsReceivedPreviousQuarter(sagiaSurveyTransactionModel.getDividendsReceivedCurrentQuarter());
		transaction.setDividendsPaidPreviousQuarter(sagiaSurveyTransactionModel.getDividendsPaidCurrentQuarter());
		transaction.setExpensesReceivedPreviousQuarter(sagiaSurveyTransactionModel.getExpensesReceivedCurrentQuarter());
		transaction.setExpensesPaidPreviousQuarter(sagiaSurveyTransactionModel.getExpensesPaidCurrentQuarter());
        transaction.setSellProductionSuppliesPreviousQuarter(sagiaSurveyTransactionModel.getSellProductionSuppliesCurrentQuarter());
		transaction.setPurchaseProductionSuppliesPreviousQuarter(sagiaSurveyTransactionModel.getPurchaseProductionSuppliesCurrentQuarter());
        transaction.setSellMachineryPreviousQuarter(sagiaSurveyTransactionModel.getSellMachineryCurrentQuarter());
		transaction.setPurchaseMachineryPreviousQuarter(sagiaSurveyTransactionModel.getPurchaseMachineryCurrentQuarter());
        transaction.setCurrentDebitAccountPreviousQuarter(sagiaSurveyTransactionModel.getCurrentDebitAccountCurrentQuarter());
		transaction.setCurrentCreditAccountPreviousQuarter(sagiaSurveyTransactionModel.getCurrentCreditAccountCurrentQuarter());
        transaction.setExpensesReceivablePreviousQuarter(sagiaSurveyTransactionModel.getExpensesReceivableCurrentQuarter());
		transaction.setExpensesPayablePreviousQuarter(sagiaSurveyTransactionModel.getExpensesPayableCurrentQuarter());
        transaction.setInsuranceCommissionReceivablePreviousQuarter(sagiaSurveyTransactionModel.getInsuranceCommissionReceivableCurrentQuarter());
		transaction.setInsuranceCommissionPayablePreviousQuarter(sagiaSurveyTransactionModel.getInsuranceCommissionPayableCurrentQuarter());
		transaction.setOtherDebitPreviousQuarter(sagiaSurveyTransactionModel.getOtherDebitCurrentQuarter());
		transaction.setOtherCreditPreviousQuarter(sagiaSurveyTransactionModel.getOtherCreditCurrentQuarter());
		transaction.setTotalDebitPreviousQuarter(sagiaSurveyTransactionModel.getTotalDebitCurrentQuarter());
		transaction.setTotalCreditPreviousQuarter(sagiaSurveyTransactionModel.getTotalCreditCurrentQuarter());

    }
}
