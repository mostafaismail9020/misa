package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.AttachmantHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * SagiaLegalConsultationFacade
 */
public interface SagiaLegalConsultationFacade {

    /**
     * Loads the legal consultations types from the CRM
     *
     * @return List of LegalInquiryType
     * @throws IOException exception
     */
    List<LegalInquiryType> getLegalInquiryTypes();

    /**
     * Loads the legal consultations from the CRM
     *
     * @return List of LegalInquiryHDR
     * @throws IOException exception
     */


    List<LegalInquiryHDR> getLegalInquiryHDR();

    /**
     * Loads a legal consultation by id
     *
     * @param id id
     * @return LegalInquiryHDR
     */

    LegalInquiryHDR getLegalInquiryHDR(String id);

    /**
     * retrieves LegalConsultationSupportingAttachments
     * @return Collection of AttachmantHDR
     */
    Collection<AttachmantHDR> getLegalConsultationSupportingAttachments();

    /**
     * reads Attachment
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    InputStream readAttachment(String objectId, String documentId);
}
