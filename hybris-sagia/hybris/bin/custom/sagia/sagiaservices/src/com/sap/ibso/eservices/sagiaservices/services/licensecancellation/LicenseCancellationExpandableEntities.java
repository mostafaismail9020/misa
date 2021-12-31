package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

public enum LicenseCancellationExpandableEntities {

	TO_CONTENT_NAV("LicenseCancelToContentNav"),
	TO_TEXT_NAV("LicenseCancelToTextNav");

    private String navEntity;

	LicenseCancellationExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    public String navEntity() {
        return navEntity;
    }
}
