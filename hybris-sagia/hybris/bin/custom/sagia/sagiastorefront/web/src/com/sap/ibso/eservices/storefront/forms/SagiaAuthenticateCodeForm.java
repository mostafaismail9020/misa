package com.sap.ibso.eservices.storefront.forms;

import java.io.Serializable;

public class SagiaAuthenticateCodeForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String code;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
