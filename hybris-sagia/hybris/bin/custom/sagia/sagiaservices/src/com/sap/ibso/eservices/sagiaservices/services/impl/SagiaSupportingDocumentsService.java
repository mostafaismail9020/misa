package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * SagiaSupportingDocumentsService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaSupportingDocumentsService extends AbstractSagiaService<GovtServiceUploadData> {

    private static final String PARAMETERS = "Scenario eq 'ZS10' and Fieldname eq 'ATTACHMENT' and Fieldkey eq '";

    /**
     * Get collection with what to upload.
     * @param serviceUrl serviceUrl
     * @return Collection of GovtServiceUploadData
     */
    public Collection<GovtServiceUploadData> getFilesToBeUploaded(String serviceUrl) {
        return super.getCollection(GovtServiceUploadData.class, REQUEST_PARAMETER_FILTER, PARAMETERS + serviceUrl +"'");
    }
}

