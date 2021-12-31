package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

public enum FinancialStatementExpandableEntities {

    /**
     * an enum with all navigation properties of
     * Financial Statement entity, used in querying data
     */

    TO_CONTENT_NAV("ContentHDRSet");
    //TO_TEXT_NAV("GetTextSet");

    private String navEntity;

    FinancialStatementExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    public String navEntity() {
        return navEntity;
    }
}
