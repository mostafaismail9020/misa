/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.AddressHDR;
import com.sap.ibso.eservices.facades.data.ContentHDRDocument;
import com.sap.ibso.eservices.facades.data.ContentHDRImage;
import com.sap.ibso.eservices.facades.data.ServiceRequestHDR;
import com.sap.ibso.eservices.facades.form.LicenseRenewalForm;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.fest.util.Collections;
import org.fest.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.populators
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LicenseRenewalReversePopulator implements Populator<LicenseRenewalForm, ServiceRequestHDR> {
    private MediaService mediaService;
    private ModelService modelService;

    private static final Logger LOGGER = Logger.getLogger(LicenseRenewalReversePopulator.class);

    @Override
    public void populate(LicenseRenewalForm licenseRenewalForm, ServiceRequestHDR serviceRequestHDR) throws ConversionException {
        serviceRequestHDR.setSrGuid(licenseRenewalForm.getSrGuid());
        serviceRequestHDR.setReApply(false);
        serviceRequestHDR.setLicenseDuration(licenseRenewalForm.getDuration());
        AddressHDR address = new AddressHDR();
        address.setStreet(licenseRenewalForm.getStreet());
        address.setCity(licenseRenewalForm.getCity());
        address.setCountry(licenseRenewalForm.getCountry());
        address.setZipCode(licenseRenewalForm.getZipCode());
        address.setBuilding(licenseRenewalForm.getBuildingNo());
        address.setAdditionalNotes(licenseRenewalForm.getAdditNo());
        address.setHouseNo(licenseRenewalForm.getHouseNo());

        serviceRequestHDR.setAddress(address);

        //setAttachedImages(licenseRenewalForm, serviceRequestHDR);

        // Re-apply check
        if(!Collections.isEmpty(licenseRenewalForm.getPreviouslyAttachedDocuments())){
            if(licenseRenewalForm.getPreviouslyAttachedDocuments().size() != licenseRenewalForm.getMultipartFile().size()){
                throw new IllegalArgumentException("The numbers of previously uploaded documents and the newly uploaded ones are not the same.");
            }
            serviceRequestHDR.setReApply(true);
            setAttachedDocumentsForReapply(licenseRenewalForm, serviceRequestHDR);
        }
        else
        {
            setAttachedDocuments(licenseRenewalForm, serviceRequestHDR);
        }

    }

    private void setAttachedDocumentsForReapply(LicenseRenewalForm licenseRenewalForm, ServiceRequestHDR serviceRequestHDR) {
        List<ContentHDRDocument> documents = new ArrayList<>();

        for(int i=0; i<licenseRenewalForm.getMultipartFile().size(); i++){
            MultipartFile documentFile = licenseRenewalForm.getMultipartFile().get(i);

            // if the previously uploaded document was not changed
            if(documentFile.getSize() == 0){
                documents.add(licenseRenewalForm.getPreviouslyAttachedDocuments().get(i));
            }
            else {
                ContentHDRDocument document = new ContentHDRDocument();
                try {
                    document.setFileContentString(org.apache.ws.security.util.Base64.encode(documentFile.getBytes()));
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }

                document.setFileName(documentFile.getOriginalFilename());
                document.setFileSize(ObjectUtils.toString(documentFile.getSize()));
                document.setMimetype(documentFile.getContentType());
                document.setObjectId(licenseRenewalForm.getPreviouslyAttachedDocuments().get(i).getObjectId());
                document.setKeyID(licenseRenewalForm.getPreviouslyAttachedDocuments().get(i).getKeyID());
                documents.add(document);
            }
        }
        serviceRequestHDR.setAttachedDocuments(documents);
    }

    private void setAttachedDocuments(LicenseRenewalForm licenseRenewalForm, ServiceRequestHDR serviceRequestHDR) {
        final int draftFilesSize;
        if (!org.apache.commons.collections.CollectionUtils.isEmpty(licenseRenewalForm.getDraftFiles())) {
            draftFilesSize = licenseRenewalForm.getDraftFiles().size();
        }else {
            draftFilesSize = 0;
        }

        List<ContentHDRDocument> documents = new ArrayList<>();
        // for each of the multipart files that were submitted with the form
        for (int idx = 0; idx < licenseRenewalForm.getMultipartFile().size(); idx++) {
            MultipartFile documentFile = licenseRenewalForm.getMultipartFile().get(idx);
            String dockeyID = licenseRenewalForm.getDockeyID().get(idx);
            ContentHDRDocument document = new ContentHDRDocument();

            // if there are drafts
            if (idx < draftFilesSize && licenseRenewalForm.getDraftFiles() != null &&
                    licenseRenewalForm.getDraftFiles().get(idx) != null && !Strings.isEmpty(licenseRenewalForm.getDraftFiles().get(idx))) {
                final String fileCode = licenseRenewalForm.getDraftFiles().get(idx);
                final CatalogUnawareMediaModel media = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);

                document.setFileContentString(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(media)));
                document.setFileName(media.getRealFileName());
                document.setFileSize(ObjectUtils.toString(media.getSize()));
                document.setMimetype(media.getMime());
            }
            else {

                try {
                    document.setFileContentString(org.apache.ws.security.util.Base64.encode(documentFile.getBytes()));
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(),e);
                }

                document.setFileName(documentFile.getOriginalFilename());
                document.setFileSize(ObjectUtils.toString(documentFile.getSize()));
                document.setMimetype(documentFile.getContentType());
            }
            document.setKeyID(dockeyID);
            documents.add(document);
        }
        serviceRequestHDR.setAttachedDocuments(documents);
    }

    private void setAttachedImages(LicenseRenewalForm licenseRenewalForm, ServiceRequestHDR serviceRequestHDR) {
        List<ContentHDRImage> images = new ArrayList<>();

        licenseRenewalForm.getImg().removeIf(s-> s.isEmpty());
        if(!Collections.isEmpty(licenseRenewalForm.getPreviouslyAttachedImages())){
            // Iterate through previous images
            for(ContentHDRImage image : licenseRenewalForm.getPreviouslyAttachedImages()){

                // If the image was not removed
                if(licenseRenewalForm.getImg().contains(image.getDocumentID())){
                    images.add(image);
                    licenseRenewalForm.getImg().remove(licenseRenewalForm.getImg().indexOf(image.getDocumentID()));
                }
            }
        }

        // Get multi file upload images
        for (String fileCode : licenseRenewalForm.getImg()) {
            if (!Strings.isEmpty(fileCode)) {
                final CatalogUnawareMediaModel imageFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);

                ContentHDRImage image = new ContentHDRImage();
                image.setMimetype(imageFile.getMime());
                image.setFileName(imageFile.getRealFileName());
                image.setFileSize(imageFile.getSize());
                image.setFileContentString(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(imageFile)));
                if(!Strings.isEmpty(licenseRenewalForm.getObjectId())){
                    image.setObjectID(licenseRenewalForm.getObjectId());
                }
                images.add(image);
            }
        }
        serviceRequestHDR.setAttachedImages(images);
    }

    /**
     * @return MediaService
     */
    public MediaService getMediaService() {
        return mediaService;
    }

    /**
     * @param mediaService mediaService
     */
    public void setMediaService(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * @return ModelService
     */
    public ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService modelService
     */
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }
}
