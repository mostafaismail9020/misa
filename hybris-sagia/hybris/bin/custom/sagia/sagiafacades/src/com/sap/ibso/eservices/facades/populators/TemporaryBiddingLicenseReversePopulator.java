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
public class TemporaryBiddingLicenseReversePopulator implements Populator<TemporaryBiddingLicense, TemporaryBiddingLicenseData> {

    @Override
    public void populate(TemporaryBiddingLicense temporaryBiddingLicense, TemporaryBiddingLicenseData temporaryBiddingLicenseData) throws ConversionException {
        temporaryBiddingLicenseData.setCAname(temporaryBiddingLicense.getCompanyNameInArabic());
        temporaryBiddingLicenseData.setCEname(temporaryBiddingLicense.getCompanyNameInEnglish());
        temporaryBiddingLicenseData.setPAname(temporaryBiddingLicense.getProjectNameInArabic());
        temporaryBiddingLicenseData.setPEname(temporaryBiddingLicense.getProjectNameInEnglish());
        temporaryBiddingLicenseData.setCapital(temporaryBiddingLicense.getCapital());
        temporaryBiddingLicenseData.setGentity(temporaryBiddingLicense.getGovernmentEntity());
        temporaryBiddingLicenseData.setCountry(temporaryBiddingLicense.getCountry());
        temporaryBiddingLicenseData.setCity(temporaryBiddingLicense.getCity());
        temporaryBiddingLicenseData.setPcode(temporaryBiddingLicense.getPostalCode());
        temporaryBiddingLicenseData.setPBox(temporaryBiddingLicense.getPoBox());
        temporaryBiddingLicenseData.setTPhone(temporaryBiddingLicense.getTelephone());
        temporaryBiddingLicenseData.setMobile(temporaryBiddingLicense.getMobile());
        temporaryBiddingLicenseData.setEmail(temporaryBiddingLicense.getEmail());
        temporaryBiddingLicenseData.setReturn(temporaryBiddingLicense.getState());
        temporaryBiddingLicenseData.setId(temporaryBiddingLicense.getId());

        List<TemporaryBiddingLicenseAttachment> attachments = temporaryBiddingLicense.getAttachments();
        if (attachments != null) {
            temporaryBiddingLicenseData.setZTEMPTOATT(attachments.stream().map(this::mapAttachmentToAttachmentData).collect(Collectors.toList()));
        }
    }

    private TemporaryBiddingLicenseAttachmentData mapAttachmentToAttachmentData(TemporaryBiddingLicenseAttachment temporaryBiddingLicenseAttachment) {
        TemporaryBiddingLicenseAttachmentData temporaryBiddingLicenseAttachmentData = new TemporaryBiddingLicenseAttachmentData();
        temporaryBiddingLicenseAttachmentData.setId(temporaryBiddingLicenseAttachment.getId());
        temporaryBiddingLicenseAttachmentData.setFilename(temporaryBiddingLicenseAttachment.getFilename());
        temporaryBiddingLicenseAttachmentData.setMimeType(temporaryBiddingLicenseAttachment.getMimeType());
        temporaryBiddingLicenseAttachmentData.setContent(temporaryBiddingLicenseAttachment.getContent());
        return temporaryBiddingLicenseAttachmentData;
    }
}
