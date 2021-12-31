package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryDropdownSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;

/**
 * LegalInquiryDropdownSetService
 */
public class LegalInquiryDropdownSetService extends AbstractSagiaService<LegalInquiryDropdownSetData> {

    private static final String FILTER_PARAMETER = "((ScenarioId eq 'LEGL') and (Lang eq '%s'))";
    /**
     * retrieves Collection of LegalInquiryDropdownSetData
     * @param language language
     * @return Collection of LegalInquiryDropdownSetData
     */
    public Collection<LegalInquiryDropdownSetData> getCollection(String language) {
        return getCollection(LegalInquiryDropdownSetData.class, new QueryOptionsBuilder().filter(String.format(FILTER_PARAMETER, language)).build());
    }
}
