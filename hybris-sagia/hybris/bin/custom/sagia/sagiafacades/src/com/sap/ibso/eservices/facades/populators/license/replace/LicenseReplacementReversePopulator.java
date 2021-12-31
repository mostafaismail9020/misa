package com.sap.ibso.eservices.facades.populators.license.replace;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementAttachmentToBeUploaded;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementAttachments;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementMessages;
import com.sap.ibso.eservices.facades.data.replace.LicenseReplaceMents;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentToTextNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadContentData;
import de.hybris.platform.converters.Populator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LicenseReplacementReversePopulator implements Populator<LicenseReplaceMents, LicenseReplaceMentData> {
    private static final String ZONE_UTC = "UTC";
    private SagiaFormatProvider sagiaFormatProvider;
    private  LicenseReplacementAttachmentReversePopulator licenseReplacementAttachmentReversePopulator;
    private  LicenseReplacementMessageReversePopulator licenseReplacementMessageReversePopulator;
    private LicenseReplacementDocumentUploadReversePopulator licenseReplacementDocumentUploadReversePopulator;

    @Override
    public void populate(LicenseReplaceMents source, LicenseReplaceMentData target) {
        target.setReApply(source.getReApply());
        target.setSrID(source.getSrID());
        target.setSrGuid(source.getSrGuid());
        target.setLicID(source.getLicID());
        target.setLicGuid(source.getLicGuid());
        target.setLicType(source.getLicType());
        target.setLicTypeDesc(source.getLicTypeDesc());
        target.setBpID(source.getBpID());
        target.setBpGuid(source.getBpGuid());
        LocalDateTime dateTime = Instant.ofEpochMilli(source.getSrCrDate().getMillis()).atZone(ZoneId.of(ZONE_UTC)).toLocalDateTime();
        target.setSrCrDate(dateTime);
        source.setSrStCode(source.getSrStDesc());
        source.setSrStDesc(source.getSrStDesc());
        LocalDateTime dateTime2 = Instant.ofEpochMilli(source.getLicExDate().getMillis()).atZone(ZoneId.of(ZONE_UTC)).toLocalDateTime();
        target.setLicExDate(dateTime2);
        LocalDateTime dateTime3 = Instant.ofEpochMilli(source.getValidFrom().getMillis()).atZone(ZoneId.of(ZONE_UTC)).toLocalDateTime();
        target.setLicExDate(dateTime3);
        target.setTransType(source.getTransType());
        target.setAction(source.getAction());
        target.setBpChangeID(source.getBpChangeID());
        target.setSalesrepCount(source.getSalesrepCount());
        target.setContactChInd(source.getContactChInd());
        target.setNonChangable(source.getNonChangable());
        target.setTermCond(source.getTermCond());

        List<LicenseReplacementAttachments> attachments = source.getUploadedAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            ArrayList<ContentHDRData> attachmentsData = new ArrayList<>();
            for (LicenseReplacementAttachments attachedFileData : attachments) {
                ContentHDRData attachedFile = new ContentHDRData();
                licenseReplacementAttachmentReversePopulator.populate(attachedFileData, attachedFile);
                attachmentsData.add(attachedFile);
            }
            target.setLicenseReplaceMentToContentNav(attachmentsData);
        }

        List<LicenseReplacementMessages> messages = source.getMessages();
        if (messages != null && !messages.isEmpty()) {
            ArrayList<LicenseReplaceMentToTextNavData> messagesData = new ArrayList<>();
            for (LicenseReplacementMessages licenseMessage : messages) {
                LicenseReplaceMentToTextNavData messageData = new LicenseReplaceMentToTextNavData();
                licenseReplacementMessageReversePopulator.populate(licenseMessage, messageData);
                messagesData.add(messageData);
            }
            target.setLicenseReplaceMentToTextNav(messagesData);
        }
        List<LicenseReplacementAttachmentToBeUploaded> files = source.getSupportedAttachments();
        if (files != null && !files.isEmpty()) {
            ArrayList<UploadContentData> uploadfilesData = new ArrayList<>();
            for(LicenseReplacementAttachmentToBeUploaded file : files){
            	UploadContentData fileData = new UploadContentData();
                licenseReplacementDocumentUploadReversePopulator.populate(file, fileData);
                uploadfilesData.add(fileData);
            }
            target.setLicenseReplToUploadNav(uploadfilesData);
        }
    }

    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    public LicenseReplacementAttachmentReversePopulator getLicenseReplacementAttachmentReversePopulator() {
        return licenseReplacementAttachmentReversePopulator;
    }

    public void setLicenseReplacementAttachmentReversePopulator(LicenseReplacementAttachmentReversePopulator licenseReplacementAttachmentReversePopulator) {
        this.licenseReplacementAttachmentReversePopulator = licenseReplacementAttachmentReversePopulator;
    }

    public LicenseReplacementMessageReversePopulator getLicenseReplacementMessageReversePopulator() {
        return licenseReplacementMessageReversePopulator;
    }

    public void setLicenseReplacementMessageReversePopulator(LicenseReplacementMessageReversePopulator licenseReplacementMessageReversePopulator) {
        this.licenseReplacementMessageReversePopulator = licenseReplacementMessageReversePopulator;
    }

    public LicenseReplacementDocumentUploadReversePopulator getLicenseReplacementDocumentUploadReversePopulator() {
        return licenseReplacementDocumentUploadReversePopulator;
    }

    public void setLicenseReplacementDocumentUploadReversePopulator(LicenseReplacementDocumentUploadReversePopulator licenseReplacementDocumentUploadReversePopulator) {
        this.licenseReplacementDocumentUploadReversePopulator = licenseReplacementDocumentUploadReversePopulator;
    }

}
