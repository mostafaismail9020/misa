package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

public enum LicenseClearanceSetExpandableEntities {

	TO_CONTENT_NAV("LicenseClearToContentNav"),
	TO_TEXT_NAV("LicenseClearToTextNav");

    private String navEntity;

	LicenseClearanceSetExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    public String navEntity() {
        return navEntity;
    }
}
