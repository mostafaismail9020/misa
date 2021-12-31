package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseCancellationSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseCancelFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseClearanceFormData;

import java.io.IOException;
import java.util.Collection;

/**
 * SagiaLicenseCancellationFacade
 */
public interface SagiaLicenseCancellationFacade {
    /**
     * retrieves GlobalLicenseCancellation
     * @param id id
     * @param stage stage
     * @return GlobalLicenseCancellation
     */
    GlobalLicenseCancellation getGlobalLicenseCancellation(String id, String stage);

    /**
     * retrieves LicenseClearanceSetData
     * @param id id
     * @param stage stage
     * @return LicenseClearanceSetData
     */
    LicenseClearanceSetData getLicenseClearanceSetData(String id, String stage);

    /**
     * retrieves LatestLicenceCancellationSetData
     * @return LicenseCancellationSetData
     */
    LicenseCancellationSetData getLatestLicenceCancellationSetData();

    /**
     * retrieves LicenseCancellationSetData
     * @param id id
     * @param stage stage
     * @return LicenseCancellationSetData
     */
    LicenseCancellationSetData getLicenseCancellationSetData(String id, String stage);

    /**
     * creates LicenseClearance
     * @param licenseClearanceFormData licenseClearanceFormData
     * @throws IOException exception
     */
    void createLicenseClearance(LicenseClearanceFormData licenseClearanceFormData) throws IOException;

    /**
     * creates LicenseCancellation
     * @param licenseCancelFormData licenseCancelFormData
     * @throws IOException exception
     */
    void createLicenseCancellation(LicenseCancelFormData licenseCancelFormData) throws IOException;

    /**
     * updates GlobalLicenseCancellation
     * @param data data
     * @throws IOException exception
     */
    void updateGlobalLicenseCancellation(LicenseClearanceSetData data) throws IOException;

    /**
     * reads LicenseCancellationSupportingAttachments
     * @param type type
     * @return Collection of CustomizingGetData
     * @throws IOException exception
     */
    Collection<CustomizingGetData> readLicenseCancellationSupportingAttachments(int type) throws IOException;
}