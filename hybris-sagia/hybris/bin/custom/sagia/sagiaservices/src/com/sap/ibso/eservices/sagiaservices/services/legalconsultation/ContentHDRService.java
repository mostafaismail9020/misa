package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * ContentHDRService
 */
public class ContentHDRService extends AbstractSagiaService<ContentHDRData> {
    /**
     * retrieves LegalConsultationContent
     * @return Collection of ContentHDRData
     */
    public final Collection<ContentHDRData> getLegalConsultationContent() {
        return super.getCollection(ContentHDRData.class);
    }
}
