package com.sap.ibso.eservices.storefront.util;

import java.util.Map;

public class JsonResponseWrapper {
    private Boolean success;
    private Map<String, String> formErrors;
    private Object responseBody;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, String> getFormErrors() {
        return formErrors;
    }

    public void setFormErrors(Map<String, String> formErrors) {
        this.formErrors = formErrors;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}
