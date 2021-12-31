package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import com.sap.ibso.eservices.facades.data.account.SuccessfulPaymentData;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderParam;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

public class AfterPaymentPopulator {

    private static final String TRANSACTION_ID_ATTRIBUTE = "transaction_id";
    private static final String SALES_ORDER_1 = "salesOrder1";
    private static final String SALES_ORDER_2 = "salesOrder2";
    private static final String SALES_ORDER_1_AMOUNT = "salesOrder1Amount";
    private static final String SALES_ORDER_2_AMOUNT = "salesOrder2Amount";
    private static final String AMOUNT = "amount";

    private UserService userService;

    public SuccessfulPaymentData createPaymentSuccessfulDataFromMap(Map<String,String> map, String transactionId){
        SuccessfulPaymentData paymentData = new SuccessfulPaymentData();
        paymentData.setTransactionNumber(transactionId);
        paymentData.setSalesOrder1(map.get(SALES_ORDER_1));
        paymentData.setSalesOrder2(map.get(SALES_ORDER_2));
        paymentData.setSalesOrder1Amount(map.get(SALES_ORDER_1_AMOUNT));
        paymentData.setSalesOrder2Amount(map.get(SALES_ORDER_2_AMOUNT));
        paymentData.setAmountPayed(map.get(AMOUNT));
        return paymentData;
    }

    public SalesOrderParam createSalesOrderParam(SagiaPaymentModel sagiaPaymentModel){
        SalesOrderParam salesOrderUpdate = new SalesOrderParam();
        salesOrderUpdate.setSalesOrderAmount(sagiaPaymentModel.getSalesOrderAmount());
        salesOrderUpdate.setTotalAmountPayed(sagiaPaymentModel.getTotalPayedAmount());
        salesOrderUpdate.setSalesOrderId(sagiaPaymentModel.getSalesOrderId());
        salesOrderUpdate.setTransactionNumber(sagiaPaymentModel.getTransactionId());
        salesOrderUpdate.setTransactionTime(sagiaPaymentModel.getCreationtime());
        salesOrderUpdate.setInvestorId(sagiaPaymentModel.getUser().getEntityID());
        return salesOrderUpdate;
    }

    public void populateSagiaPayments(SuccessfulPaymentData source,SagiaPaymentModel target1,SagiaPaymentModel target2){
        target1.setSalesOrderId(source.getSalesOrder1());
        target1.setTransactionId(source.getTransactionNumber());
        target1.setUser((CustomerModel) userService.getCurrentUser());
        target1.setTotalPayedAmount(new BigDecimal(source.getAmountPayed()));
        target1.setSalesOrderAmount(new BigDecimal(source.getSalesOrder1Amount()));
        target1.setUpdated(false);

	if(StringUtils.isNotEmpty(source.getSalesOrder2())) {
        target2.setSalesOrderId(source.getSalesOrder2());
        target2.setTransactionId(source.getTransactionNumber());
        target2.setUser((CustomerModel) userService.getCurrentUser());
        target2.setTotalPayedAmount(new BigDecimal(source.getAmountPayed()));
        target2.setSalesOrderAmount(new BigDecimal(source.getSalesOrder2Amount()));
        target2.setUpdated(false);
	}
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
