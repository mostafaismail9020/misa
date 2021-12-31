package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicense;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class TemporaryBiddingLicensePopulator implements Populator<TemporaryBiddingLicenseData, TemporaryBiddingLicense> {

    @Override
    public void populate(TemporaryBiddingLicenseData temporaryBiddingLicenseData, TemporaryBiddingLicense temporaryBiddingLicense) throws ConversionException {
        temporaryBiddingLicense.setCompanyNameInArabic(temporaryBiddingLicenseData.getCAname());
        temporaryBiddingLicense.setCompanyNameInEnglish(temporaryBiddingLicenseData.getCEname());
        temporaryBiddingLicense.setProjectNameInArabic(temporaryBiddingLicenseData.getPAname());
        temporaryBiddingLicense.setProjectNameInEnglish(temporaryBiddingLicenseData.getPEname());
        temporaryBiddingLicense.setCapital(temporaryBiddingLicenseData.getCapital());
        temporaryBiddingLicense.setGovernmentEntity(temporaryBiddingLicenseData.getCAname());
        temporaryBiddingLicense.setCountry(temporaryBiddingLicenseData.getCountry());
        temporaryBiddingLicense.setCity(temporaryBiddingLicenseData.getCity());
        temporaryBiddingLicense.setPostalCode(temporaryBiddingLicenseData.getPcode());
        temporaryBiddingLicense.setPoBox(temporaryBiddingLicenseData.getPBox());
        temporaryBiddingLicense.setTelephone(temporaryBiddingLicenseData.getTPhone());
        temporaryBiddingLicense.setMobile(temporaryBiddingLicenseData.getMobile());
        temporaryBiddingLicense.setEmail(temporaryBiddingLicenseData.getEmail());
        temporaryBiddingLicense.setState(temporaryBiddingLicenseData.getReturn());
        temporaryBiddingLicense.setId(temporaryBiddingLicenseData.getId());

        List<TemporaryBiddingLicenseAttachmentData> attachments = temporaryBiddingLicenseData.getZTEMPTOATT();
        if (attachments != null) {
            temporaryBiddingLicense.setAttachments(attachments.stream().map(this::mapAttachmentDataToAttachment).collect(Collectors.toList()));
        }
    }

    private TemporaryBiddingLicenseAttachment mapAttachmentDataToAttachment(TemporaryBiddingLicenseAttachmentData temporaryBiddingLicenseAttachmentData) {
        TemporaryBiddingLicenseAttachment temporaryBiddingLicenseAttachment = new TemporaryBiddingLicenseAttachment();
        temporaryBiddingLicenseAttachment.setId(temporaryBiddingLicenseAttachmentData.getId());
        temporaryBiddingLicenseAttachment.setContent(temporaryBiddingLicenseAttachmentData.getContent());
        temporaryBiddingLicenseAttachment.setFilename(temporaryBiddingLicenseAttachmentData.getFilename());
        temporaryBiddingLicenseAttachment.setMimeType(temporaryBiddingLicenseAttachmentData.getMimeType());
        return temporaryBiddingLicenseAttachment;
    }

}