package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * LegalInquiryService
 */
public class LegalInquiryService extends AbstractSagiaService<LegalInquiryData> {
    /**
     * get legal inquiry
     * @return Collection of LegalInquiryData
     */
    public final Collection<LegalInquiryData> getLegalInquiry() {
        return super.getCollection(LegalInquiryData.class);
    }
}
