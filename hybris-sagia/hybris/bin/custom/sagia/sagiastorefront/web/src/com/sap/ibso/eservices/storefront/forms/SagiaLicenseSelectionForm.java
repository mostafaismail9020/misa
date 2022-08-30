package com.sap.ibso.eservices.storefront.forms;

public class SagiaLicenseSelectionForm {
    private String selectedLicenseCode;
    private String userNationalId;

    public String getSelectedLicenseCode() {
        return selectedLicenseCode;
    }

    public void setSelectedLicenseCode(String selectedLicenseCode) {
        this.selectedLicenseCode = selectedLicenseCode;
    }

    public String getUserNationalId() {
        return userNationalId;
    }

    public void setUserNationalId(String userNationalId) {
        this.userNationalId = userNationalId;
    }
}
