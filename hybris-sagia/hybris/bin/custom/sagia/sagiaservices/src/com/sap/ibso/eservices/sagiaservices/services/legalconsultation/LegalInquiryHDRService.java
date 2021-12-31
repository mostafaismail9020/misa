package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.LegalConsultationFormData;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * LegalInquiryHDRService
 */
public class LegalInquiryHDRService extends AbstractSagiaService<LegalInquiryHDRData> {

    private LegalInquiryHDRConverter legalInquiryHDRConverter;
    /**
     * retrieves LegalConsultationList
     * @return list with all Legal Consultations
     */
    public final Collection<LegalInquiryHDRData> getLegalConsultationList() {
        return super.getCollection(LegalInquiryHDRData.class);
    }

    /**
     * return a legal consultation by id, with all the navigation properties expanded
     * @param srId id of the legal consultation
     * @return LegalInquiryHDRData
     */
    public final LegalInquiryHDRData getLegalConsultationBy(String srId) {
        return get(LegalInquiryHDRData.class, srId, new QueryOptionsBuilder()
                        .expand(createLegalConsultationExpandQuery())
                        .build());
        }

    /**
     * get all navigation properties of Legal Consultation
     * build full expression of $expand parameter
     * @return String
     */
    private String createLegalConsultationExpandQuery() {
        return Arrays.asList(LegalConsultationExpandableEntities.values())
                .stream()
                .map(LegalConsultationExpandableEntities::navEntity)
                .collect(Collectors.joining(","));
    }

    /**
     * create new Legal Consultation
     * @param legalConsultationFormData legalConsultationFormData
     */

    public final void createLegalConsultation(LegalConsultationFormData legalConsultationFormData){
        LegalInquiryHDRData legalInquiry = legalInquiryHDRConverter.fromFormData(legalConsultationFormData);
        legalInquiry.setTransType("ZS20");
        save(legalInquiry);
    }

    public LegalInquiryHDRConverter getLegalInquiryHDRConverter()
    {
        return legalInquiryHDRConverter;
    }

    public void setLegalInquiryHDRConverter(final LegalInquiryHDRConverter legalInquiryHDRConverter)
    {
        this.legalInquiryHDRConverter = legalInquiryHDRConverter;
    }
}

