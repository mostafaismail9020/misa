package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceRequestReversePopulator implements Populator<ServiceRequestHDR,ServiceRequestHDRsData>{
    private ServicesRequestCommentReversePopulator commentReversePopulator;
    private ServicesRequestAddressReversePopulator addressReversePopulator;
    private ServicesRequestDocumentReversePopulator documentReversePopulator;
    private ServicesRequestImageReversePopulator imageReversePopulator;

    /**
     * @param commentReversePopulator commentReversePopulator
     */
    public void setCommentReversePopulator(ServicesRequestCommentReversePopulator commentReversePopulator) {
        this.commentReversePopulator = commentReversePopulator;
    }

    /**
     * @param addressReversePopulator addressReversePopulator
     */
    public void setAddressReversePopulator(ServicesRequestAddressReversePopulator addressReversePopulator) {
        this.addressReversePopulator = addressReversePopulator;
    }

    /**
     * @param documentReversePopulator documentReversePopulator
     */
    public void setDocumentReversePopulator(ServicesRequestDocumentReversePopulator documentReversePopulator) {
        this.documentReversePopulator = documentReversePopulator;
    }

    /**
     * @param imageReversePopulator imageReversePopulator
     */
    public void setImageReversePopulator(ServicesRequestImageReversePopulator imageReversePopulator) {
        this.imageReversePopulator = imageReversePopulator;
    }

    @Override
    public void populate(ServiceRequestHDR serviceRequest, ServiceRequestHDRsData serviceRequestHDRsData) throws ConversionException {
        serviceRequestHDRsData.setAction(serviceRequest.getAction());
        serviceRequestHDRsData.setBpGuid(serviceRequest.getBpGuid());
        serviceRequestHDRsData.setBpID(serviceRequest.getBpID());
        serviceRequestHDRsData.setLicExDate(serviceRequest.getLicenseExpirationDate());
        serviceRequestHDRsData.setLicGuid(serviceRequest.getLicenseGuid());
        serviceRequestHDRsData.setLicID(serviceRequest.getLicenseID());
        serviceRequestHDRsData.setLicRenFlag(serviceRequest.getLicRenFlag());
        serviceRequestHDRsData.setLicType(serviceRequest.getLicenseType());
        serviceRequestHDRsData.setLicTypeDesc(serviceRequest.getLicenseTypeDesc());
        serviceRequestHDRsData.setPrevSrGuid(serviceRequest.getPreviousSrGuid());
        serviceRequestHDRsData.setReApply(serviceRequest.getReApply());
        serviceRequestHDRsData.setSrCrDate(serviceRequest.getDate());
        serviceRequestHDRsData.setSrGuid(serviceRequest.getSrGuid());
        serviceRequestHDRsData.setSrID(serviceRequest.getSrID());
        serviceRequestHDRsData.setSrStCode(serviceRequest.getStatusCode());
        serviceRequestHDRsData.setSrStDesc(serviceRequest.getStatusDescription());
        serviceRequestHDRsData.setTransType(serviceRequest.getTransType());
        serviceRequestHDRsData.setValidFrom(serviceRequest.getValidFrom());
        serviceRequestHDRsData.setIsAllowed(serviceRequest.getIsAllowed());
        serviceRequestHDRsData.setMsgToInvestor(serviceRequest.getMsgToInvestor());

        setServReq(serviceRequest, serviceRequestHDRsData);
    }

    private void setServReq(ServiceRequestHDR serviceRequest, ServiceRequestHDRsData serviceRequestHDRsData) {
        AddressHDRData addressHDRData = new AddressHDRData();
        if(serviceRequest.getAddress() != null){
            addressReversePopulator.populate(serviceRequest.getAddress(), addressHDRData);
        }
        serviceRequestHDRsData.setSrvReqToAddressNav(addressHDRData);

        setSrvReqToAttachNav(serviceRequest, serviceRequestHDRsData);

        setSrvReqToContentNav(serviceRequest, serviceRequestHDRsData);

        setSrvReqToTextNav(serviceRequest, serviceRequestHDRsData);
    }

    private void setSrvReqToAttachNav(ServiceRequestHDR serviceRequest, ServiceRequestHDRsData serviceRequestHDRsData) {
        List<AttachmentHDRData> attachmentHDRDataList = new ArrayList<>();
        if(serviceRequest.getAttachedImages() != null && !serviceRequest.getAttachedImages().isEmpty()){
            for(ContentHDRImage image : serviceRequest.getAttachedImages()){
                AttachmentHDRData attachmentHDRData = new AttachmentHDRData();
                imageReversePopulator.populate(image, attachmentHDRData);
                attachmentHDRDataList.add(attachmentHDRData);
            }
        }
        serviceRequestHDRsData.setSrvReqToAttachNav(attachmentHDRDataList);
    }

    private void setSrvReqToContentNav(ServiceRequestHDR serviceRequest, ServiceRequestHDRsData serviceRequestHDRsData) {
        List<ContentHDRData> contentHDRDataList = new ArrayList<>();
        if(serviceRequest.getAttachedDocuments() != null && !serviceRequest.getAttachedDocuments().isEmpty()){
            for(ContentHDRDocument document : serviceRequest.getAttachedDocuments()){
                ContentHDRData contentHDRData = new ContentHDRData();
                documentReversePopulator.populate(document, contentHDRData);
                contentHDRDataList.add(contentHDRData);
            }
        }
        serviceRequestHDRsData.setSrvReqToContentNav(contentHDRDataList);
    }

    private void setSrvReqToTextNav(ServiceRequestHDR serviceRequest, ServiceRequestHDRsData serviceRequestHDRsData) {
        List<TextHDRData> textHDRDataList = new ArrayList<>();
        if(serviceRequest.getComments() != null && !serviceRequest.getComments().isEmpty()){
            for(ServiceRequestComment comment : serviceRequest.getComments()){
                TextHDRData textHDRData = new TextHDRData();
                commentReversePopulator.populate(comment, textHDRData);
                textHDRDataList.add(textHDRData);
            }
        }
        serviceRequestHDRsData.setSrvReqToTextNav(textHDRDataList);
    }
}
