package com.sap.ibso.eservices.storefront.forms;

import java.util.Map;

public class SagiaInvestorJsonResponse {
    private boolean valid;
    private Map<String, String> errors;
    private Object responseData;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
