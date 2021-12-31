package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmantHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * AttachmentHDRService
 */
public class AttachmentHDRService extends AbstractSagiaService<AttachmantHDRData> {

    /**
     * retrieves LegalConsultationAttachment
     * @return Collection of AttachmantHDRData
     */
    public final Collection<AttachmantHDRData> getLegalConsultationAttachment() {
        return super.getCollection(AttachmantHDRData.class);
    }
}

