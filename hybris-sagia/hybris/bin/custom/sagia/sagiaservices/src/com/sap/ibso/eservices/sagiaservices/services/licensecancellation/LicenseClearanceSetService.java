package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * LicenseClearanceSetService
 */
public final class LicenseClearanceSetService extends AbstractSagiaService<LicenseClearanceSetData> {
    /**
     * retrieves LicenseClearanceSetData by id and stageValue
     * @param objectIDValue objectIDValue
     * @param stageValue stageValue
     * @return LicenseClearanceSetData
     */
    public final LicenseClearanceSetData get(String objectIDValue, String stageValue){
        String objectId = "SrID=" + "'" + objectIDValue + "'";
        String documentId = "Stage=" + "'" + stageValue + "'";
        return super.get(LicenseClearanceSetData.class, Arrays.asList(objectId, documentId), REQUEST_PARAMETER_EXPAND,createExpandQuery());
    }

    private String createExpandQuery() {
        return Arrays.asList(LicenseClearanceSetExpandableEntities.values())
                .stream()
                .map(LicenseClearanceSetExpandableEntities::navEntity)
                .collect(Collectors.joining(","));
    }

    /**
     * creates LicenseClearance
     * @param licenseClearanceFormData licenseClearanceFormData
     * @param supportedAttachments supportedAttachments
     */
    public void createLicenseClearance(LicenseClearanceFormData licenseClearanceFormData,
                                         Collection<CustomizingGetData> supportedAttachments) {
        LicenseClearanceSetData licClearance = LicenseCancellationConverter.fromLicenseClearanceFormData(licenseClearanceFormData, supportedAttachments);
        licClearance.setTransType("ZSR9");
        save(licClearance);
    }

    /**
     * updates GlobalLicenseCancellationData
     * @param objectIDValue objectIDValue
     * @param stageValue stageValue
     * @param licenseClearanceSetData licenseClearanceSetData
     */
    public void updateGlobalLicenseCancellationData(String objectIDValue, String stageValue,LicenseClearanceSetData licenseClearanceSetData) {
        String objectId = "SrID=" + "'" + objectIDValue + "'";
        String documentId = "Stage=" + "'" + stageValue + "'";
        save(licenseClearanceSetData, Arrays.asList(objectId, documentId));
    }

}
