package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * HomeHDRService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class HomeHDRService extends AbstractSagiaService<HomeHDRData> {
    private static final String HOME_HDR_DATA_EXPAND = "HomeToShareHolderNav,HomeToContactNav,HomeToLicInfoNav,HomeToShareHolderNav,HomeHDRToSalAndEmpNav,HomeHDRToClassificNav";
    private static final String CONTENT_DETAILS_ENTITY_SET = "ContentDetails";


    /**
     * Get the Home HDR(dashboard) data from CRM
     * @return - HomeHDR Data
     * @throws IOException exception
     */
    public final HomeHDRData get() {
        return super.get(HomeHDRData.class, (Object) "", REQUEST_PARAMETER_EXPAND, HOME_HDR_DATA_EXPAND);
    }


    /**
     * reads AttachmentFile
     * @param objectIDValue objectIDValue
     * @param  documentIdValue documentIdValue
     * @return InputStream
     */
    public InputStream readAttachmentFile(String objectIDValue, String documentIdValue) {
        String objectId = "ObjectId=" + "'" + objectIDValue + "'";
        String documentId = "DocumentID=" + "'" + documentIdValue + "'";
        return getMediaEntity(CONTENT_DETAILS_ENTITY_SET, Arrays.asList(objectId, documentId));
    }
}
