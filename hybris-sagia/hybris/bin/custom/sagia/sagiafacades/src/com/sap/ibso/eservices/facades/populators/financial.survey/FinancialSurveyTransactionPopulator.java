package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.SagiaSurveyTransactionModel;
import com.sap.ibso.eservices.facades.data.license.amendment.Transaction;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class FinancialSurveyTransactionPopulator implements Populator<SagiaSurveyTransactionModel, Transaction> {


    @Override
    public void populate(SagiaSurveyTransactionModel sagiaSurveyTransactionModel, Transaction transaction) throws ConversionException {


        transaction.setTradeDebitCurrentQuarter(sagiaSurveyTransactionModel.getTradeDebitCurrentQuarter());
		transaction.setTradeCreditCurrentQuarter(sagiaSurveyTransactionModel.getTradeCreditCurrentQuarter());

		transaction.setLoansAssetsCurrentQuarter(sagiaSurveyTransactionModel.getLoansAssetsCurrentQuarter());
		transaction.setLoansLiabilitiesCurrentQuarter(sagiaSurveyTransactionModel.getLoansLiabilitiesCurrentQuarter());
		transaction.setInterestReceivedCurrentQuarter(sagiaSurveyTransactionModel.getInterestReceivedCurrentQuarter());
		transaction.setInterestPayableCurrentQuarter(sagiaSurveyTransactionModel.getInterestPayableCurrentQuarter());

		transaction.setDividendsReceivedCurrentQuarter(sagiaSurveyTransactionModel.getDividendsReceivedCurrentQuarter());
		transaction.setDividendsPaidCurrentQuarter(sagiaSurveyTransactionModel.getDividendsPaidCurrentQuarter());

		transaction.setExpensesReceivedCurrentQuarter(sagiaSurveyTransactionModel.getExpensesReceivedCurrentQuarter());
		transaction.setExpensesPaidCurrentQuarter(sagiaSurveyTransactionModel.getExpensesPaidCurrentQuarter());

		transaction.setSellProductionSuppliesCurrentQuarter(sagiaSurveyTransactionModel.getSellProductionSuppliesCurrentQuarter());
		transaction.setPurchaseProductionSuppliesCurrentQuarter(sagiaSurveyTransactionModel.getPurchaseProductionSuppliesCurrentQuarter());

		transaction.setSellMachineryCurrentQuarter(sagiaSurveyTransactionModel.getSellMachineryCurrentQuarter());
		transaction.setPurchaseMachineryCurrentQuarter(sagiaSurveyTransactionModel.getPurchaseMachineryCurrentQuarter());

		transaction.setCurrentDebitAccountCurrentQuarter(sagiaSurveyTransactionModel.getCurrentDebitAccountCurrentQuarter());
		transaction.setCurrentCreditAccountCurrentQuarter(sagiaSurveyTransactionModel.getCurrentCreditAccountCurrentQuarter());

		transaction.setExpensesReceivableCurrentQuarter(sagiaSurveyTransactionModel.getExpensesReceivableCurrentQuarter());
		transaction.setExpensesPayableCurrentQuarter(sagiaSurveyTransactionModel.getExpensesPayableCurrentQuarter());
		transaction.setInsuranceCommissionReceivableCurrentQuarter(sagiaSurveyTransactionModel.getInsuranceCommissionReceivableCurrentQuarter());
		transaction.setInsuranceCommissionPayableCurrentQuarter(sagiaSurveyTransactionModel.getInsuranceCommissionPayableCurrentQuarter());


		transaction.setOtherDebitCurrentQuarter(sagiaSurveyTransactionModel.getOtherDebitCurrentQuarter());
		transaction.setOtherCreditCurrentQuarter(sagiaSurveyTransactionModel.getOtherCreditCurrentQuarter());


		transaction.setTotalDebitCurrentQuarter(sagiaSurveyTransactionModel.getTotalDebitCurrentQuarter());
		transaction.setTotalCreditCurrentQuarter(sagiaSurveyTransactionModel.getTotalCreditCurrentQuarter());

    }
}
