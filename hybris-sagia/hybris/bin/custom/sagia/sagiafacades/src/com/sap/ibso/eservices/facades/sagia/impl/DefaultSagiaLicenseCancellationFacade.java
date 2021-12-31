package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.constants.SagiaFacadesConstants;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseCancellationFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseCancellationSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseCancelFormData;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseClearanceFormData;

import java.util.Collection;
import java.util.Collections;

/**
 * DefaultSagiaLicenseCancellationFacade
 */
public class DefaultSagiaLicenseCancellationFacade implements SagiaLicenseCancellationFacade {
    private ZUI5SagiaFacade zui5SagiaFacade;

    /**
     * retrieves Zui5SagiaFacade
     * @return ZUI5SagiaFacade
     */
    public ZUI5SagiaFacade getZui5SagiaFacade() {
        return zui5SagiaFacade;
    }

    /**
     * @param zui5SagiaFacade zui5SagiaFacade
     */
    public void setZui5SagiaFacade(ZUI5SagiaFacade zui5SagiaFacade) {
        this.zui5SagiaFacade = zui5SagiaFacade;
    }

    /**
     * retrieves GlobalLicenseCancellation
     * @param id id
     * @param stage stage
     * @return GlobalLicenseCancellation
     */
    public GlobalLicenseCancellation getGlobalLicenseCancellation(String id, String stage) {
        return getZui5SagiaFacade().getGlobalLicenseCancellation(id, stage);
    }

    /**
     * retrieves LicenseClearanceSetData
     * @param id id
     * @param stage stage
     * @return LicenseClearanceSetData
     */
    public LicenseClearanceSetData getLicenseClearanceSetData(String id, String stage) {
        return getZui5SagiaFacade().getLicenseClearanceSetData(id, stage);
    }

    /**
     * retrieves LicenseCancellationSetData
     * @param id id
     * @param stage stage
     * @return LicenseCancellationSetData
     */
    public LicenseCancellationSetData getLicenseCancellationSetData(String id, String stage) {
        return getZui5SagiaFacade().getLicenseCancellationSetData(id, stage);
    }

    /**
     * retrieves LatestLicenceCancellationSetData
     * @return LicenseCancellationSetData
     */
    public LicenseCancellationSetData getLatestLicenceCancellationSetData() {
        return getZui5SagiaFacade().getLatestLicenceCancellationCreated();
    }

    /**
     * creates LicenseClearance
     * @param licenseClearanceFormData licenseClearanceFormData
     */
    public void createLicenseClearance(LicenseClearanceFormData licenseClearanceFormData)   {
        Collection<CustomizingGetData> supportedAttachments = getZui5SagiaFacade().readClearSetAttachments();
        getZui5SagiaFacade().getLicenseClearanceSetService().createLicenseClearance(licenseClearanceFormData, supportedAttachments);
    }

    /**
     * creates LicenseCancellation
     * @param licenseCancelFormData licenseCancelFormData
     */
    public void createLicenseCancellation(LicenseCancelFormData licenseCancelFormData)   {
        Collection<CustomizingGetData> supportedAttachments = getZui5SagiaFacade().readCancelLicenseSupportingAttachments();
        getZui5SagiaFacade().getLicenseCancellationSetService().createLicenseCancellation(licenseCancelFormData, supportedAttachments);
    }

    /**
     * readLicenseCancellationSupportingAttachments
     * @param type type
     * @return Collection of CustomizingGetData
     */
    public Collection<CustomizingGetData> readLicenseCancellationSupportingAttachments(int type)  {
        Collection<CustomizingGetData> supportedAttachments = Collections.emptyList();

        if (type == SagiaFacadesConstants.LICENSE_CLEARANCE_SUPPORTING_ATTACHMENTS) {
            supportedAttachments = getZui5SagiaFacade().readClearSetAttachments();
        } else if (type == SagiaFacadesConstants.LICENSE_CANCEL_SUPPORTING_ATTACHMENTS) {
            supportedAttachments = getZui5SagiaFacade().readCancelLicenseSupportingAttachments();
        }

        return supportedAttachments;
    }

    /**
     * updates GlobalLicenseCancellation
     * @param licenseClearanceSetData licenseClearanceSetData
     */
    public void updateGlobalLicenseCancellation(LicenseClearanceSetData licenseClearanceSetData) {
        LicenseClearanceSetData data = new LicenseClearanceSetData();
        int st = Integer.parseInt(licenseClearanceSetData.getStage());
        data.setStage(String.format("%02d", ++st));
        data.setSrGuid(licenseClearanceSetData.getSrGuid());
        data.setSrID(licenseClearanceSetData.getSrID());
        getZui5SagiaFacade().updateGlobalLicenseCancellation(licenseClearanceSetData.getSrID(), licenseClearanceSetData.getStage(), data);
    }
}
