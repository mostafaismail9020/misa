package com.sap.ibso.eservices.storefront.forms;

import java.io.Serializable;

public class SagiaEditProfileForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String entityName;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
