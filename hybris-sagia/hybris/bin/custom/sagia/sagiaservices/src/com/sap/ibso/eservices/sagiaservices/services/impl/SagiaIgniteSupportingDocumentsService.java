package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.CRMIgniteServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.IgniteServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * SagiaIgniteSupportingDocumentsService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2023 MISA
 */
public class SagiaIgniteSupportingDocumentsService extends AbstractSagiaService<IgniteServiceUploadData> {

    private static final String PARAMETERS = "Scenario eq 'ZS32' and Fieldname eq 'ATTACHMENT' and Fieldkey eq '";

    /**
     * Get collection with what to upload.
     * @param serviceUrl serviceUrl
     * @return Collection of GovtServiceUploadData
     */
    public Collection<IgniteServiceUploadData> getFilesToBeUploaded(String serviceUrl) {
        return super.getCollection(IgniteServiceUploadData.class, REQUEST_PARAMETER_FILTER, PARAMETERS + serviceUrl +"'");
    }
}

