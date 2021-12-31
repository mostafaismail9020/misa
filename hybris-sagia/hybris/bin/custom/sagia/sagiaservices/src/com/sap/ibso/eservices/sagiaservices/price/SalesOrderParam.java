package com.sap.ibso.eservices.sagiaservices.price;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Sales order parameter for updating a sales order in CRM once the payment
 * has been completed in Hybris.
 */
public class SalesOrderParam {

    private String transactionNumber;

    private String salesOrderId;

    private String investorId;

    private BigDecimal salesOrderAmount;

    private BigDecimal totalAmountPayed;

    private Date transactionTime;

    private String paymentMethod;

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(String salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public BigDecimal getSalesOrderAmount() {
        return salesOrderAmount;
    }

    public void setSalesOrderAmount(BigDecimal salesOrderAmount) {
        this.salesOrderAmount = salesOrderAmount;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotalAmountPayed() {
        return totalAmountPayed;
    }

    public void setTotalAmountPayed(BigDecimal totalAmountPayed) {
        this.totalAmountPayed = totalAmountPayed;
    }
}
