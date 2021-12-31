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

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class LicenseReplacementPopulator implements Populator<LicenseReplaceMentData, LicenseReplaceMents> {
    private SagiaFormatProvider sagiaFormatProvider;
    private  LicenseReplacementAttachmentPopulator licenseReplacementAttachmentPopulator;
    private  LicenseReplacementMessagePopulator licenseReplacementMessagePopulator;
    private LicenseReplacementDocumentUploadPopulator licenseReplacementDocumentUploadPopulator;

    @Override
    public void populate(LicenseReplaceMentData licenseReplaceMentData, LicenseReplaceMents licenseReplaceMents)  {
        licenseReplaceMents.setReApply(licenseReplaceMentData.getReApply());
        licenseReplaceMents.setSrID(licenseReplaceMentData.getSrID());
        licenseReplaceMents.setSrGuid(licenseReplaceMentData.getSrGuid());
        licenseReplaceMents.setLicID(licenseReplaceMentData.getLicID());
        licenseReplaceMents.setLicGuid(licenseReplaceMentData.getLicGuid());
        licenseReplaceMents.setLicType(licenseReplaceMentData.getLicType());
        licenseReplaceMents.setLicTypeDesc(licenseReplaceMentData.getLicTypeDesc());
        licenseReplaceMents.setBpID(licenseReplaceMentData.getBpID());
        licenseReplaceMents.setBpGuid(licenseReplaceMentData.getBpGuid());

        if (licenseReplaceMentData.getSrCrDate() != null) {
            licenseReplaceMents.setSrCrDate(sagiaFormatProvider.getLocalizedDateTimeData(licenseReplaceMentData.getSrCrDate()));
        }

        licenseReplaceMents.setSrStCode(licenseReplaceMentData.getSrStCode());
        licenseReplaceMents.setSrStDesc(licenseReplaceMentData.getSrStDesc());

        if (licenseReplaceMentData.getLicExDate() != null) {
            licenseReplaceMents.setLicExDate(sagiaFormatProvider.getLocalizedDateTimeData(licenseReplaceMentData.getLicExDate()));
        }

        if (licenseReplaceMentData.getValidFrom() != null) {
            licenseReplaceMents.setValidFrom(sagiaFormatProvider.getLocalizedDateTimeData(licenseReplaceMentData.getValidFrom()));
        }

        licenseReplaceMents.setTransType(licenseReplaceMentData.getTransType());
        licenseReplaceMents.setAction(licenseReplaceMentData.getAction());
        licenseReplaceMents.setBpChangeID(licenseReplaceMentData.getBpChangeID());
        licenseReplaceMents.setSalesrepCount(licenseReplaceMentData.getSalesrepCount());
        licenseReplaceMents.setContactChInd(licenseReplaceMentData.getContactChInd());
        licenseReplaceMents.setNonChangable(licenseReplaceMentData.getNonChangable());
        licenseReplaceMents.setTermCond(licenseReplaceMentData.getTermCond());

        setUploadedAttachments(licenseReplaceMentData, licenseReplaceMents);

        setMessages(licenseReplaceMentData, licenseReplaceMents);

        setSupportedAttachments(licenseReplaceMentData, licenseReplaceMents);
    }

    private void setUploadedAttachments(LicenseReplaceMentData licenseReplaceMentData, LicenseReplaceMents licenseReplaceMents) {
        List<ContentHDRData> attachmentsData =licenseReplaceMentData.getLicenseReplaceMentToContentNav();
        if (attachmentsData != null && !attachmentsData.isEmpty()) {
            ArrayList<LicenseReplacementAttachments> attachments = new ArrayList<>();
            for (ContentHDRData attachedFileData : attachmentsData) {
                LicenseReplacementAttachments licenseReplacementAttachments = new LicenseReplacementAttachments();
                licenseReplacementAttachmentPopulator.populate(attachedFileData, licenseReplacementAttachments);
                attachments.add(licenseReplacementAttachments);
            }
            licenseReplaceMents.setUploadedAttachments(attachments);
        }
    }

    private void setMessages(LicenseReplaceMentData licenseReplaceMentData, LicenseReplaceMents licenseReplaceMents) {
        List<LicenseReplaceMentToTextNavData> messagesData = licenseReplaceMentData.getLicenseReplaceMentToTextNav();
        if (messagesData != null && !messagesData.isEmpty()) {
            ArrayList<LicenseReplacementMessages> messages = new ArrayList<>();
            for (LicenseReplaceMentToTextNavData messageData : messagesData) {
                LicenseReplacementMessages licenseReplacementMessages = new LicenseReplacementMessages();
                licenseReplacementMessagePopulator.populate(messageData, licenseReplacementMessages);
                messages.add(licenseReplacementMessages);
            }
            licenseReplaceMents.setMessages(messages);
        }
    }

    private void setSupportedAttachments(LicenseReplaceMentData licenseReplaceMentData, LicenseReplaceMents licenseReplaceMents) {
    		List<UploadContentData> uploadedfileData = licenseReplaceMentData.getLicenseReplToUploadNav();

        if (uploadedfileData != null && !uploadedfileData.isEmpty()) {
            ArrayList<LicenseReplacementAttachmentToBeUploaded> files = new ArrayList<>();
            for(UploadContentData fileData : uploadedfileData){
                LicenseReplacementAttachmentToBeUploaded file = new LicenseReplacementAttachmentToBeUploaded();
                licenseReplacementDocumentUploadPopulator.populate(fileData,file);
                files.add(file);
            }
            licenseReplaceMents.setSupportedAttachments(files);
        }
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    /**
     * @return
     */
    public LicenseReplacementAttachmentPopulator getLicenseReplacementAttachmentPopulator() {
        return licenseReplacementAttachmentPopulator;
    }

    /**
     * @param licenseReplacementAttachmentPopulator
     */
    public void setLicenseReplacementAttachmentPopulator(LicenseReplacementAttachmentPopulator licenseReplacementAttachmentPopulator) {
        this.licenseReplacementAttachmentPopulator = licenseReplacementAttachmentPopulator;
    }

    /**
     * @return
     */
    public LicenseReplacementMessagePopulator getLicenseReplacementMessagePopulator() {
        return licenseReplacementMessagePopulator;
    }

    /**
     * @param licenseReplacementMessagePopulator
     */
    public void setLicenseReplacementMessagePopulator(LicenseReplacementMessagePopulator licenseReplacementMessagePopulator) {
        this.licenseReplacementMessagePopulator = licenseReplacementMessagePopulator;
    }

    /**
     * @return
     */
    public LicenseReplacementDocumentUploadPopulator getLicenseReplacementDocumentUploadPopulator() {
        return licenseReplacementDocumentUploadPopulator;
    }

    /**
     * @param licenseReplacementDocumentUploadPopulator
     */
    public void setLicenseReplacementDocumentUploadPopulator(LicenseReplacementDocumentUploadPopulator licenseReplacementDocumentUploadPopulator) {
        this.licenseReplacementDocumentUploadPopulator = licenseReplacementDocumentUploadPopulator;
    }


}
