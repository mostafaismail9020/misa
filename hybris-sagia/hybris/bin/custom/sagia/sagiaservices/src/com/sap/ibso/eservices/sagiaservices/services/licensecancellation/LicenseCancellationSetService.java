package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseCancellationSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * LicenseCancellationSetService
 */
public final class LicenseCancellationSetService extends AbstractSagiaService<LicenseCancellationSetData> {
    public final Collection<LicenseCancellationSetData> getCollection() {
        return super.getCollection(LicenseCancellationSetData.class);
    }

    /**
     * retrieves LicenseCancellationSetData by id and stageValue
     * @param objectIDValue objectIDValue
     * @param stageValue stageValue
     * @return LicenseCancellationSetData
     */
    public final LicenseCancellationSetData get(String objectIDValue, String stageValue) {
        String objectId = "SrID=" + "'" + objectIDValue + "'";
        String documentId = "Stage=" + "'" + stageValue + "'";
        return super.get(LicenseCancellationSetData.class, Arrays.asList(objectId, documentId), REQUEST_PARAMETER_EXPAND, createExpandQuery());
    }

    /**
     * retrieves LatestEntityCreated
     * @return LicenseCancellationSetData
     */
    public LicenseCancellationSetData getLatestEntityCreated() {
        return getCollection().stream().findFirst().orElse(null);
    }

    /**
     * creates LicenseCancellation
     * @param licenseCancelFormData licenseCancelFormData
     * @param supportedAttachments supportedAttachments
     */
    public void createLicenseCancellation(LicenseCancelFormData licenseCancelFormData,
                                          Collection<CustomizingGetData> supportedAttachments) {
        LicenseCancellationSetData liccancel = LicenseCancellationConverter.fromLicenseCancelFormData(licenseCancelFormData, supportedAttachments);
        liccancel.setTransType("ZSR6");
        save(liccancel);
    }

    private String createExpandQuery() {
        return Arrays.asList(LicenseCancellationExpandableEntities.values())
                .stream()
                .map(LicenseCancellationExpandableEntities::navEntity)
                .collect(Collectors.joining(","));
    }
}
