package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;


import com.sap.ibso.eservices.facades.data.zesrvEnhOData.*;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.*;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LegalInquiryHDRReversePopulator implements Populator<LegalInquiryHDR, LegalInquiryHDRData> {
    /**
     * Populate from LegalInquiryHDR to LegalInquiryHDRData
     *
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    private LegalInquiryReversePopulator legalInquiryReversePopulator;
    private UploadContentReversePopulator uploadContentReversePopulator;
    private GetTextReversePopulator getTextReversePopulator;
    private ContentHDRReversePopulator contentHDRReversePopulator;
    private AttachmantHDRReversePopulator attachmantHDRReversePopulator;

    /**
     * @return
     */
    public LegalInquiryReversePopulator getLegalInquiryReversePopulator() {
        return legalInquiryReversePopulator;
    }

    /**
     * @param legalInquiryReversePopulator
     */
    public void setLegalInquiryReversePopulator(LegalInquiryReversePopulator legalInquiryReversePopulator) {
        this.legalInquiryReversePopulator = legalInquiryReversePopulator;
    }

    /**
     * @return
     */
    public UploadContentReversePopulator getUploadContentReversePopulator() {
        return uploadContentReversePopulator;
    }

    /**
     * @param uploadContentReversePopulator
     */
    public void setUploadContentReversePopulator(UploadContentReversePopulator uploadContentReversePopulator) {
        this.uploadContentReversePopulator = uploadContentReversePopulator;
    }

    /**
     * @return
     */
    public GetTextReversePopulator getGetTextReversePopulator() {
        return getTextReversePopulator;
    }

    /**
     * @param getTextReversePopulator
     */
    public void setGetTextReversePopulator(GetTextReversePopulator getTextReversePopulator) {
        this.getTextReversePopulator = getTextReversePopulator;
    }

    /**
     * @return
     */
    public ContentHDRReversePopulator getContentHDRReversePopulator() {
        return contentHDRReversePopulator;
    }

    /**
     * @param contentHDRReversePopulator
     */
    public void setContentHDRReversePopulator(ContentHDRReversePopulator contentHDRReversePopulator) {
        this.contentHDRReversePopulator = contentHDRReversePopulator;
    }

    /**
     * @return
     */
    public AttachmantHDRReversePopulator getAttachmantHDRReversePopulator() {
        return attachmantHDRReversePopulator;
    }

    /**
     * @param attachmantHDRReversePopulator
     */
    public void setAttachmantHDRReversePopulator(AttachmantHDRReversePopulator attachmantHDRReversePopulator) {
        this.attachmantHDRReversePopulator = attachmantHDRReversePopulator;
    }



    @Override
    public void populate(LegalInquiryHDR source, LegalInquiryHDRData target) throws ConversionException {
        target.setSrId(source.getSrId());
        target.setSrGuid(source.getSrGuid());
        target.setBpId(source.getBpId());
        target.setBpGuid(source.getBpGuid());
        target.setSrStCode(source.getSrStCode());
        target.setSrStDesc(source.getSrStDesc());
        target.setTransType(source.getTransType());
        target.setTransTypeDesc(source.getTransTypeDesc());
        target.setAction(source.getAction());
        target.setLogonUser(source.getLogonUser());
        target.setBpName(source.getBpName());


        LegalInquiryData legalInquiryData = new LegalInquiryData();
        if (source.getLegalInquiry() != null) {
            legalInquiryReversePopulator.populate(source.getLegalInquiry(), legalInquiryData);
        }
        target.setLegalInquiry(legalInquiryData);

        setUploadContent(source, target);

        setTextData(source, target);

        setContent(source, target);

        setAttachments(source, target);
    }

    private void setUploadContent(LegalInquiryHDR source, LegalInquiryHDRData target) {
        List<UploadContentData> uploadContentDataList = new ArrayList<>();
        if(source.getUploadContentSet() != null && !source.getUploadContentSet().isEmpty()){
            for (UploadContent upload : source.getUploadContentSet()) {
                UploadContentData uploadContentData = new UploadContentData();
                uploadContentReversePopulator.populate(upload, uploadContentData);
                uploadContentDataList.add(uploadContentData);
            }
        }
        target.setUploadContentSet(uploadContentDataList);
    }

    private void setTextData(LegalInquiryHDR source, LegalInquiryHDRData target) {
        List<GetTextData> getTextDataList = new ArrayList<>();
        if(source.getGetTextSet() != null && !source.getGetTextSet().isEmpty()){
            for (GetText text : source.getGetTextSet()) {
                GetTextData getTextData = new GetTextData();
                getTextReversePopulator.populate(text, getTextData);
                getTextDataList.add(getTextData);
            }
        }
        target.setGetTextSet(getTextDataList);
    }

    private void setContent(LegalInquiryHDR source, LegalInquiryHDRData target) {
        List<ContentHDRData> contentHDRDataList = new ArrayList<>();
        if(source.getContentHDRSet() != null && !source.getContentHDRSet().isEmpty()){
            for (ContentHDR content : source.getContentHDRSet()) {
                ContentHDRData contentHDRData = new ContentHDRData();
                contentHDRReversePopulator.populate(content, contentHDRData);
                contentHDRDataList.add(contentHDRData);
            }
        }
        target.setContentHDRSet(contentHDRDataList);
    }

    private void setAttachments(LegalInquiryHDR source, LegalInquiryHDRData target) {
        List<AttachmantHDRData> attachmantHDRDataList = new ArrayList<>();
        if(source.getAttachmantHDRSet() != null && !source.getAttachmantHDRSet().isEmpty()){
            for (AttachmantHDR attachment : source.getAttachmantHDRSet()) {
                AttachmantHDRData attachmantHDRData = new AttachmantHDRData();
                attachmantHDRReversePopulator.populate(attachment, attachmantHDRData);
                attachmantHDRDataList.add(attachmantHDRData);
            }
        }
        target.setAttachmantHDRSet(attachmantHDRDataList);
    }
}
