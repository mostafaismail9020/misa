package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * GetTextService
 */
public class GetTextService extends AbstractSagiaService<GetTextData> {

    /**
     * retrieves LegalConsultationGetText
     * @return Collection of GetTextData
     */
    public final Collection<GetTextData> getLegalConsultationGetText() {
        return super.getCollection(GetTextData.class);
    }
}

