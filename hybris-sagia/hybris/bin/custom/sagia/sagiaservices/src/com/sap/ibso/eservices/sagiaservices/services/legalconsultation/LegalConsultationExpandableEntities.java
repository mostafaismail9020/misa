package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

/**
 * LegalConsultationExpandableEntities
 */
public enum LegalConsultationExpandableEntities {

    /**
     * an enum with all navigation properties of
     * Legal Consultation entity, used in querying data
     */

    TO_LEGAL_INQUIRY_NAV("LegalInquiry"),
    TO_CONTENT_NAV("ContentHDRSet"),
    TO_TEXT_NAV("GetTextSet");

    private String navEntity;

    LegalConsultationExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    public String navEntity() {
        return navEntity;
    }
}

