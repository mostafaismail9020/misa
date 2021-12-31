package com.sap.ibso.eservices.storefront.forms;

import java.util.Map;

public class SagiaTemporaryBiddingLicenseJsonResponse {

    private boolean valid;
    private Map<String, String> errors;
    private String createdEntityId;

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
	public void setNewEntityCreatedId(String entityId) {
		this.createdEntityId = entityId;
	}

	public String getEntityCreatedId() {
		return this.createdEntityId;
	}
}
