package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentListData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * SagiaRealEstateAttachmentSetService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaRealEstateAttachmentSetService extends AbstractSagiaService<RealEstateAttachmentListData> {

    private static final String TYPE = "Type eq ";
    private static final String SUBTYPE = "Subtype eq ";
    private static final String LANGUAGE = "Language eq '";
    private I18NService i18NService;

    /**
     * Get files to be uploaded when creating a Real Estate intance.
     *
     * @param requestType requestType
     * @param realEstateType realEstateType
     * @return Collection of RealEstateAttachmentListData
     */
    public Collection<RealEstateAttachmentListData> getAttachmentSet(String requestType, String realEstateType) {
        String upperCaseLanguage = i18NService.getCurrentLocale().getLanguage().toUpperCase();
        return super.getCollection(RealEstateAttachmentListData.class, REQUEST_PARAMETER_FILTER, TYPE + getType(requestType) + " and " + SUBTYPE + getSubtype(realEstateType) + " and " + LANGUAGE + upperCaseLanguage + "'");
    }

    private String getSubtype(String realEstateType) {
        switch (realEstateType){
            case "10": return "'P'"; // Personal Housing - P
            case "20": return "'P'"; // Industrial - W
            case "30": return "'P'"; // Administrative - W
            case "40": return "'P'"; // Warehousing - W
            case "50": return "'I'"; // Investment - I
            default: return null;
        }
    }

    private String getType(String requestType) {
        return "Buy".equals(requestType) ? "'B'" : "'S'";
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
