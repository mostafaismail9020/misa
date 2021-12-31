package com.sap.ibso.eservices.sagiaservices.services.complaints;

/**
 *
 */
public enum ComplaintsExpandableEntities {

    TO_DETAIL_NAV("CompAndEnqHdrToDetailNav"),
    TO_CONTENT_NAV("CompAndEnqHdrToContentNav"),
    TO_TEXT_NAV("CompAndEnqHdrToTextNav");

    private String navEntity;

    /**
     * @param navEntity
     */
    ComplaintsExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    /**
     * @return
     */
    public String navEntity() {
        return navEntity;
    }
}
