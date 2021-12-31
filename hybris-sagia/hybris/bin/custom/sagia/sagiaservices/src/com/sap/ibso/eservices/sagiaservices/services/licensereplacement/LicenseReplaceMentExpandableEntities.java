package com.sap.ibso.eservices.sagiaservices.services.licensereplacement;

public enum LicenseReplaceMentExpandableEntities {

	TO_CONTENT_NAV("LicenseReplToContentNav"),
	TO_TEXT_NAV("LicenseReplToTextNav");
	
    private String navEntity;

	LicenseReplaceMentExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    public String navEntity() {
        return navEntity;
    }
}
