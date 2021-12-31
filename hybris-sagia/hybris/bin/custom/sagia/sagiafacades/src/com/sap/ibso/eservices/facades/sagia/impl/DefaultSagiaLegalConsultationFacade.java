package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.AttachmantHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryType;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.AttachmantHDRPopulator;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.LegalInquiryHDRPopulator;
import com.sap.ibso.eservices.facades.populators.zesrvEnhOData.LegalInquiryTypePopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaLegalConsultationFacade;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmantHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryDropdownSetData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryHDRData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.DocumentAttachmentService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.AttachmentHDRService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.LegalInquiryDropdownSetService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.LegalInquiryHDRService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaLegalConsultationFacade
 */
public class DefaultSagiaLegalConsultationFacade implements SagiaLegalConsultationFacade {

    private static final Logger LOG = Logger.getLogger(DefaultSagiaIsicFacade.class);
    private LegalInquiryHDRService legalInquiryHDRService;
    private AttachmentHDRService attachmentHDRService;
    private LegalInquiryHDRPopulator legalInquiryHDRPopulator;
    private AttachmantHDRPopulator attachmantHDRPopulator;

    private LegalInquiryTypePopulator legalInquiryTypePopulator;

    private LegalInquiryDropdownSetService legalInquiryDropdownSetService;
    private DocumentAttachmentService documentAttachmentService;

    @Autowired
    private I18NService i18NService;

    /**
     * @return
     */
    public List<LegalInquiryType> getLegalInquiryTypes() {
        List<LegalInquiryType> legalInquiryTypes = new ArrayList<>();
        try {
            Collection<LegalInquiryDropdownSetData> legalInquiryDropdownSetData = legalInquiryDropdownSetService.getCollection(i18NService.getCurrentLocale().getLanguage().substring(0, 1).toUpperCase());
            legalInquiryDropdownSetData.forEach(legalInquiryTypeData -> {
                        LegalInquiryType legalInquiryType = new LegalInquiryType();
                        legalInquiryTypePopulator.populate(legalInquiryTypeData, legalInquiryType);
                        legalInquiryTypes.add(legalInquiryType);
                    }
            );
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
        return legalInquiryTypes;
    }

    /**
     * @return
     */
    @Override
    public List<LegalInquiryHDR> getLegalInquiryHDR() {

            List<LegalInquiryHDR> licenseInquiryHDRs= new ArrayList<>();
            Collection<LegalInquiryHDRData> legalInquiryHDRsData = legalInquiryHDRService.getLegalConsultationList();
            if (legalInquiryHDRsData != null && !legalInquiryHDRsData.isEmpty()) {
                for (LegalInquiryHDRData legalInquiryHDRData : legalInquiryHDRsData) {
                    LegalInquiryHDR legalInquiryHDR = new LegalInquiryHDR();
                    legalInquiryHDRPopulator.populate(legalInquiryHDRData, legalInquiryHDR);
                    licenseInquiryHDRs.add(legalInquiryHDR);
                }
            }
            return licenseInquiryHDRs;
        }
    

    @Override
    public LegalInquiryHDR getLegalInquiryHDR(final String srId)  {
            LegalInquiryHDR legalInquiryHDR = new LegalInquiryHDR();
            LegalInquiryHDRData legalInquiryHDRData = legalInquiryHDRService.getLegalConsultationBy(srId);
            legalInquiryHDRPopulator.populate(legalInquiryHDRData, legalInquiryHDR);
            return legalInquiryHDR;
        }

    @Override
    public Collection<AttachmantHDR> getLegalConsultationSupportingAttachments() {
        List<AttachmantHDR> attachments = new ArrayList<>();
        Collection<AttachmantHDRData> attachmentsData = attachmentHDRService.getLegalConsultationAttachment();
        for (AttachmantHDRData attachmentHDRData :
                attachmentsData) {
            AttachmantHDR attachmentHDR = new AttachmantHDR();
            attachmantHDRPopulator.populate(attachmentHDRData, attachmentHDR);
            attachments.add(attachmentHDR);
        }
        return attachments;

    }

    @Override
    public InputStream readAttachment(String objectId, String documentId) {
        return documentAttachmentService.readAttachmentBy(objectId, documentId);
    }

    /**
     * @return
     */
    public LegalInquiryHDRService getLegalInquiryHDRService() {
        return legalInquiryHDRService;
    }

    /**
     * @param legalInquiryHDRService
     */
    public void setLegalInquiryHDRService(LegalInquiryHDRService legalInquiryHDRService) {
        this.legalInquiryHDRService = legalInquiryHDRService;
    }

    /**
     * @return
     */
    public LegalInquiryHDRPopulator getLegalInquiryHDRPopulator() {
        return legalInquiryHDRPopulator;
    }

    /**
     * @param legalInquiryHDRPopulator
     */
    public void setLegalInquiryHDRPopulator(LegalInquiryHDRPopulator legalInquiryHDRPopulator) {
        this.legalInquiryHDRPopulator = legalInquiryHDRPopulator;
    }

    /**
     * @return
     */
    public AttachmentHDRService getAttachmentHDRService() {
        return attachmentHDRService;
    }

    /**
     * @param attachmentHDRService
     */
    public void setAttachmentHDRService(AttachmentHDRService attachmentHDRService) {
        this.attachmentHDRService = attachmentHDRService;
    }

    /**
     * @return
     */
    public AttachmantHDRPopulator getAttachmantHDRPopulator() {
        return attachmantHDRPopulator;
    }

    /**
     * @param attachmantHDRPopulator
     */
    public void setAttachmantHDRPopulator(AttachmantHDRPopulator attachmantHDRPopulator) {
        this.attachmantHDRPopulator = attachmantHDRPopulator;
    }

    /**
     * @param legalInquiryTypePopulator
     */
    public void setLegalInquiryTypePopulator(LegalInquiryTypePopulator legalInquiryTypePopulator) {
        this.legalInquiryTypePopulator = legalInquiryTypePopulator;
    }

    /**
     * @param legalInquiryDropdownSetService
     */
    public void setLegalInquiryDropdownSetService(LegalInquiryDropdownSetService legalInquiryDropdownSetService) {
        this.legalInquiryDropdownSetService = legalInquiryDropdownSetService;
    }

    /**
     * @param documentAttachmentService
     */
    public void setDocumentAttachmentService(DocumentAttachmentService documentAttachmentService) {
        this.documentAttachmentService = documentAttachmentService;
    }
}














