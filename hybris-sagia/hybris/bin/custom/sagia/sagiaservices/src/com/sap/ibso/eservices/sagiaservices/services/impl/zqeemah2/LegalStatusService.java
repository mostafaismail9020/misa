package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.LegalStatusData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * LegalStatusService
 */
public class LegalStatusService extends AbstractSagiaService<LegalStatusData> {
    /**
     * retrieves LegalStatuses
     * @return Collection of LegalStatusData
     */
    public Collection<LegalStatusData> getLegalStatuses() {
        return super.getCollection(LegalStatusData.class);
    }
}
