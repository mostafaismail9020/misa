package com.sap.ibso.eservices.sagiaservices.services.notifications.dto;

import java.math.BigDecimal;

public class PaymentItem {
    private String description;
    private BigDecimal value;
    private String currency;

    public PaymentItem(String description, BigDecimal value, String currency) {
        this.description = description;
        this.value = value;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
