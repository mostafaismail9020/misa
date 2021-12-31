package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
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
public class ServicesRequestHDRPopulator implements Populator<ServiceRequestHDRsData, ServiceRequestHDR> {

    private ServicesRequestCommentPopulator servicesRequestCommentPopulator;
    private ServicesRequestAddressPopulator servicesRequestAddressPopulator;
    private ServicesRequestDocumentPopulator servicesRequestDocumentPopulator;
    private ServicesRequestImagePopulator servicesRequestImagePopulator;
    private SagiaFormatProvider sagiaFormatProvider;
    private ContentHDRPopulator contentHDRPopulator;

    /**
     * @param servicesRequestCommentPopulator servicesRequestCommentPopulator
     */
    public void setServicesRequestCommentPopulator(ServicesRequestCommentPopulator servicesRequestCommentPopulator) {
        this.servicesRequestCommentPopulator = servicesRequestCommentPopulator;
    }

    /**
     * @param servicesRequestAddressPopulator servicesRequestAddressPopulator
     */
    public void setServicesRequestAddressPopulator(ServicesRequestAddressPopulator servicesRequestAddressPopulator) {
        this.servicesRequestAddressPopulator = servicesRequestAddressPopulator;
    }

    /**
     * @param servicesRequestDocumentPopulator servicesRequestDocumentPopulator
     */
    public void setServicesRequestDocumentPopulator(ServicesRequestDocumentPopulator servicesRequestDocumentPopulator) {
        this.servicesRequestDocumentPopulator = servicesRequestDocumentPopulator;
    }

    /**
     * @param servicesRequestImagePopulator servicesRequestImagePopulator
     */
    public void setServicesRequestImagePopulator(ServicesRequestImagePopulator servicesRequestImagePopulator) {
        this.servicesRequestImagePopulator = servicesRequestImagePopulator;
    }

    /**
     * @param sagiaFormatProvider sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider)    {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    @Override
    public void populate(ServiceRequestHDRsData serviceRequestHDRsData, ServiceRequestHDR serviceRequestHDR) throws ConversionException {
        serviceRequestHDR.setAction(serviceRequestHDRsData.getAction());
        serviceRequestHDR.setBpGuid(serviceRequestHDRsData.getBpGuid());
        serviceRequestHDR.setDate(serviceRequestHDRsData.getLicExDate());
        serviceRequestHDR.setLicenseExpirationDate(serviceRequestHDRsData.getLicExDate());
        serviceRequestHDR.setLicenseExpirationDateDate(sagiaFormatProvider.getLocalizedDateData(serviceRequestHDRsData.getLicExDate()));
        serviceRequestHDR.setLicenseGuid(serviceRequestHDRsData.getLicGuid());
        serviceRequestHDR.setLicenseID(serviceRequestHDRsData.getLicID());
        serviceRequestHDR.setLicRenFlag(serviceRequestHDRsData.getLicRenFlag());
        serviceRequestHDR.setLicenseType(serviceRequestHDRsData.getLicType());
        serviceRequestHDR.setLicenseTypeDesc(serviceRequestHDRsData.getLicTypeDesc());
        serviceRequestHDR.setPreviousSrGuid(serviceRequestHDRsData.getPrevSrGuid());
        serviceRequestHDR.setReApply(serviceRequestHDRsData.getReApply());
        serviceRequestHDR.setDate(serviceRequestHDRsData.getSrCrDate());
        serviceRequestHDR.setDateData(sagiaFormatProvider.getLocalizedDateData(serviceRequestHDRsData.getSrCrDate()));
        serviceRequestHDR.setSrGuid(serviceRequestHDRsData.getSrGuid());
        serviceRequestHDR.setSrID(serviceRequestHDRsData.getSrID());
        serviceRequestHDR.setStatusCode(serviceRequestHDRsData.getSrStCode());
        serviceRequestHDR.setStatusDescription(serviceRequestHDRsData.getSrStDesc());
        serviceRequestHDR.setTransType(serviceRequestHDRsData.getTransType());
        serviceRequestHDR.setValidFrom(serviceRequestHDRsData.getValidFrom());
        serviceRequestHDR.setValidFromDate(sagiaFormatProvider.getLocalizedDateData(serviceRequestHDRsData.getSrCrDate()));
        serviceRequestHDR.setMaxLicenseDuration(serviceRequestHDRsData.getMaxLicenseDuration());
        serviceRequestHDR.setLicenseFeePerYr(serviceRequestHDRsData.getLicenseFeePerYr());
        serviceRequestHDR.setSubscriptionOutstandingFee(serviceRequestHDRsData.getSubscriptionOutstandingFee());
        serviceRequestHDR.setIsAllowed(serviceRequestHDRsData.getIsAllowed());
        serviceRequestHDR.setMsgToInvestor(serviceRequestHDRsData.getMsgToInvestor());

        AddressHDR addressHDR = new AddressHDR();
        AddressHDRData addressHDRData = serviceRequestHDRsData.getSrvReqToAddressNav();
        if(addressHDRData != null){
            servicesRequestAddressPopulator.populate(addressHDRData, addressHDR);
        }

        serviceRequestHDR.setAddress(addressHDR);
        setAttachments(serviceRequestHDRsData, serviceRequestHDR);
        setComments(serviceRequestHDRsData, serviceRequestHDR);
    }

    private void setComments(ServiceRequestHDRsData serviceRequestHDRsData, ServiceRequestHDR serviceRequestHDR) {
        List<ServiceRequestComment> comments = new ArrayList<>();
        List<TextHDRData> textHDRData = serviceRequestHDRsData.getSrvReqToTextNav();
        if(textHDRData != null && !textHDRData.isEmpty()){
            for (TextHDRData item : textHDRData) {
                ServiceRequestComment serviceRequestComment = new ServiceRequestComment();
                servicesRequestCommentPopulator.populate(item, serviceRequestComment);
                comments.add(serviceRequestComment);
            }
        }
        serviceRequestHDR.setComments(comments);
    }

    private void setAttachments(ServiceRequestHDRsData serviceRequestHDRsData, ServiceRequestHDR serviceRequestHDR) {
        List<ContentHDRDocument> documents = new ArrayList<>();
        List<ContentHDRData> srvReqToContentNav = serviceRequestHDRsData.getSrvReqToContentNav();
        if(srvReqToContentNav != null && !srvReqToContentNav.isEmpty()){
            for (ContentHDRData document: srvReqToContentNav) {
                ContentHDRDocument contentHDRDocument = new ContentHDRDocument();
                servicesRequestDocumentPopulator.populate(document, contentHDRDocument);
                documents.add(contentHDRDocument);
            }
        }
        //filter documents which have null as primary keys
        documents.removeIf(doc -> doc.getObjectId() == null && doc.getDocumentID() == null);
        serviceRequestHDR.setAttachedDocuments(documents);

        List<ContentHDRImage> images = new ArrayList<>();
        List<AttachmentHDRData> attachmentHDRData = serviceRequestHDRsData.getSrvReqToAttachNav();
        if(attachmentHDRData != null && !attachmentHDRData.isEmpty()){
            for (AttachmentHDRData image: attachmentHDRData) {
                ContentHDRImage contentHDRImage = new ContentHDRImage();
                servicesRequestImagePopulator.populate(image, contentHDRImage);
                images.add(contentHDRImage);
            }
        }
        //filter images which have null as primary keys
        images.removeIf(img -> img.getObjectID() == null && img.getDocumentID() == null);
        serviceRequestHDR.setAttachedImages(images);
    }
    
	public ContentHDRPopulator getContentHDRPopulator() {
		return contentHDRPopulator;
	}

	/**
	 * @param contentHDRPopulator
	 */
	public void setContentHDRPopulator(final ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}
}
