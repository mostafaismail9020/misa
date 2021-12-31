package com.sap.ibso.eservices.sagiaservices.services.surveys.zui5;

/**
 * SurveyHDRExpandableEntities
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public enum SurveyHDRExpandableEntities {

    TO_ANS_NAV("SurveyHdrToSurvQuestDefNav"),
    TO_DEF_NAV("SurveyQuestDefToSurvQuestDDLBNav");

    private String navEntity;

    SurveyHDRExpandableEntities(String navEntity) {
        this.navEntity = navEntity;
    }

    /**
     * navEntity
     * @return String
     */
    public String navEntity() {
        return navEntity;
    }
}

