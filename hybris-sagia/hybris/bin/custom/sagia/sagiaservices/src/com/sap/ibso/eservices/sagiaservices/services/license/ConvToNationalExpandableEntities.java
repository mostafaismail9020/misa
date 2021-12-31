package com.sap.ibso.eservices.sagiaservices.services.license;

public enum ConvToNationalExpandableEntities {

    /**
     * an enum with all navigation properties of
     * Convert to Nationals entity, used in querying data
     */
    TO_CONTENT_NAV("ConvNatToContentNav"),
	TO_TEXT_NAV("ConvNatToTextNav");
    private String navEntity;

	ConvToNationalExpandableEntities(String navEntity) {
	    this.navEntity = navEntity;
    }
    public String navEntity() {
	    return navEntity;
    }
}
