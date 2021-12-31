package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.*;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.*;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class FinancialStatementHDRReversePopulator implements Populator<FinancialStatementHDR, FinancialStatementHDRData> {

    /**
     * Populate from FinancialStatementHDR to FinancialStatementHDRData
     *
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    private UploadContentReversePopulator uploadContentReversePopulator;
    private ContentHDRReversePopulator contentHDRReversePopulator;
    private AttachmantHDRReversePopulator attachmantHDRReversePopulator;


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
    public void populate(FinancialStatementHDR source, FinancialStatementHDRData target) throws ConversionException {
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

        setUploadContent(source, target);

        setContent(source, target);

        setAttachments(source, target);
    }

    private void setUploadContent(FinancialStatementHDR source, FinancialStatementHDRData target) {
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

    private void setContent(FinancialStatementHDR source, FinancialStatementHDRData target) {
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

    private void setAttachments(FinancialStatementHDR source, FinancialStatementHDRData target) {
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
