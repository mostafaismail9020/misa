package com.sap.ibso.eservices.storefront.forms;

import java.io.Serializable;

public class SagiaApplyReviewForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private boolean termsAndConditionsChecked;

    public boolean isTermsAndConditionsChecked() {
        return termsAndConditionsChecked;
    }

    public void setTermsAndConditionsChecked(boolean termsAndConditionsChecked) {
        this.termsAndConditionsChecked = termsAndConditionsChecked;
    }
}
