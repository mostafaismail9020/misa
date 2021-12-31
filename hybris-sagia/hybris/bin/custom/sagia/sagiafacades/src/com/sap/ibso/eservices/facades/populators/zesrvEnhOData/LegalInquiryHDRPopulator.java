package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.AttachmantHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.ContentHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.GetText;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiry;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.UploadContent;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zesrv.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmantHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.UploadContentData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LegalInquiryHDRPopulator implements Populator<LegalInquiryHDRData, LegalInquiryHDR> {
    /**
     * Populate from LegalInquiryHDRData to LegalInquiryHDR
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    private LegalInquiryPopulator legalInquiryPopulator;
    private UploadContentPopulator uploadContentPopulator;
    private GetTextPopulator getTextPopulator;
    private ContentHDRPopulator contentHDRPopulator;
    private AttachmantHDRPopulator attachmantHDRPopulator;

    private SagiaFormatProvider sagiaFormatProvider;

    /**
     * @return
     */
    public LegalInquiryPopulator getLegalInquiryPopulator() {
        return legalInquiryPopulator;
    }

    /**
     * @param legalInquiryPopulator
     */
    public void setLegalInquiryPopulator(LegalInquiryPopulator legalInquiryPopulator) {
        this.legalInquiryPopulator = legalInquiryPopulator;
    }

    /**
     * @return
     */
    public UploadContentPopulator getUploadContentPopulator() {
        return uploadContentPopulator;
    }

    /**
     * @param uploadContentPopulator
     */
    public void setUploadContentPopulator(UploadContentPopulator uploadContentPopulator) {
        this.uploadContentPopulator = uploadContentPopulator;
    }

    /**
     * @return
     */
    public GetTextPopulator getGetTextPopulator() {
        return getTextPopulator;
    }

    /**
     * @param getTextPopulator
     */
    public void setGetTextPopulator(GetTextPopulator getTextPopulator) {
        this.getTextPopulator = getTextPopulator;
    }

    /**
     * @return
     */
    public ContentHDRPopulator getContentHDRPopulator() {
        return contentHDRPopulator;
    }

    /**
     * @param contentHDRPopulator
     */
    public void setContentHDRPopulator(ContentHDRPopulator contentHDRPopulator) {
        this.contentHDRPopulator = contentHDRPopulator;
    }

    /**
     * @return
     */
    public AttachmantHDRPopulator getAttachmantHDRPopulator() {
        return attachmantHDRPopulator;
    }

    /**
     * @param attachmantHDRPopulator
     */
    public void setAttachmantHDRPopulator(AttachmantHDRPopulator attachmantHDRPopulator) {
        this.attachmantHDRPopulator = attachmantHDRPopulator;
    }


    /**
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(LegalInquiryHDRData source, LegalInquiryHDR target) throws ConversionException {
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

        LocalDateTime srCrDate = source.getSrCrDate();
        if (srCrDate != null) {
            target.setSrCrDate(sagiaFormatProvider.getLocalizedDateData(srCrDate));
        }

        LegalInquiry legalInquiry = new LegalInquiry();
        LegalInquiryData legalInquiryData = source.getLegalInquiry();
        if(legalInquiryData != null){
            legalInquiryPopulator.populate(legalInquiryData, legalInquiry);
        }
        target.setLegalInquiry(legalInquiry);

        setUploads(source, target);
        setTexts(source, target);
        setContents(source, target);
        setAttachments(source, target);
    }

    private void setTexts(LegalInquiryHDRData source, LegalInquiryHDR target) {
        List<GetText> texts = new ArrayList<>();
        List<GetTextData> getTextData = source.getGetTextSet();
        if(getTextData != null && !getTextData.isEmpty()){
            for (GetTextData text: getTextData) {
                GetText getText = new GetText();
                getTextPopulator.populate(text, getText);
                texts.add(getText);
            }
        }
        target.setGetTextSet(texts);
    }

    private void setUploads(LegalInquiryHDRData source, LegalInquiryHDR target) {
        List<UploadContent> uploadContents = new ArrayList<>();
        List<UploadContentData> uploadContentData = source.getUploadContentSet();
        if(uploadContentData != null && !uploadContentData.isEmpty()){
        for (UploadContentData upload: uploadContentData) {
            UploadContent uploadContent = new UploadContent();
            uploadContentPopulator.populate(upload, uploadContent);
            uploadContents.add(uploadContent);
        }
    }
        target.setUploadContentSet(uploadContents);
    }

    private void setContents(LegalInquiryHDRData source, LegalInquiryHDR target) {
        List<ContentHDR> contents = new ArrayList<>();
        List<ContentHDRData> contentHDRData = source.getContentHDRSet();
        if(contentHDRData != null && !contentHDRData.isEmpty()){
            for (ContentHDRData content: contentHDRData) {
                contents.add(contentHDRPopulator.from(content));
            }
        }
        target.setContentHDRSet(contents);
    }

    private void setAttachments(LegalInquiryHDRData source, LegalInquiryHDR target) {
        List<AttachmantHDR> attachments = new ArrayList<>();
        List<AttachmantHDRData> attachmantHDRData = source.getAttachmantHDRSet();
        if(attachmantHDRData != null && !attachmantHDRData.isEmpty()){
            for (AttachmantHDRData attachment: attachmantHDRData) {
                AttachmantHDR attachmantHDR = new AttachmantHDR();
                attachmantHDRPopulator.populate(attachment, attachmantHDR);
                attachments.add(attachmantHDR);
            }
        }
        target.setAttachmantHDRSet(attachments);
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}



