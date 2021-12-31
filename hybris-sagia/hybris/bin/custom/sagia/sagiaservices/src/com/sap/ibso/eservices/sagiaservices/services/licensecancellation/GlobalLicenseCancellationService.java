package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
//
import java.util.Arrays;

/**
 * GlobalLicenseCancellationService
 */
public final class GlobalLicenseCancellationService extends AbstractSagiaService<GlobalLicenseCancellation> {
    /**
     * retrieves GlobalLicenseCancellation
     * @param objectIDValue objectIDValue
     * @param stageValue stageValue
     * @return GlobalLicenseCancellation
     */
    public final GlobalLicenseCancellation getGlobalLicenseCancellation(String objectIDValue, String stageValue) {
        String objectId = "SrID=" + "'" + objectIDValue + "'";
        String documentId = "Stage=" + "'" + stageValue + "'";
        return super.get(GlobalLicenseCancellation.class, Arrays.asList(objectId, documentId));
    }

 }
