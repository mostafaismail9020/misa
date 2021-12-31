package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class TemporaryBiddingLicenseReversePopulator extends ODataReversePopulator<TemporaryBiddingLicenseData> {

    private TemporaryBiddingLicenseAttachmentReversePopulator temporaryBiddingLicenseAttachmentReversePopulator;

    @Override
    public void populate(TemporaryBiddingLicenseData temporaryBiddingLicenseData, ODataModel model) throws ConversionException {
        super.populateWithDefaultFieldName(temporaryBiddingLicenseData, model);

        List<TemporaryBiddingLicenseAttachmentData> attachments = temporaryBiddingLicenseData.getZTEMPTOATT();
        Set<Map> attachmentsSet = new HashSet<>();
        for (TemporaryBiddingLicenseAttachmentData attachment : attachments) {
            ODataModel oDataModel = new ODataModel();
            temporaryBiddingLicenseAttachmentReversePopulator.populateWithDefaultFieldName(attachment, oDataModel);
            attachmentsSet.add(oDataModel.get());
        }
        model.put("ZTEMPTOATT", attachmentsSet);
    }


    /**
     * @return
     */
    public TemporaryBiddingLicenseAttachmentReversePopulator getTemporaryBiddingLicenseAttachmentReversePopulator() {
        return temporaryBiddingLicenseAttachmentReversePopulator;
    }

    /**
     * @param temporaryBiddingLicenseAttachmentReversePopulator
     */
    public void setTemporaryBiddingLicenseAttachmentReversePopulator(TemporaryBiddingLicenseAttachmentReversePopulator temporaryBiddingLicenseAttachmentReversePopulator) {
        this.temporaryBiddingLicenseAttachmentReversePopulator = temporaryBiddingLicenseAttachmentReversePopulator;
    }
}
