package com.sap.ibso.eservices.facades.populators;


import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.specialservices.ServiceApplicant;
import com.sap.ibso.eservices.facades.data.specialservices.ServiceAttachment;
import com.sap.ibso.eservices.facades.data.specialservices.SpecialServiceHeader;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceAttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpecialServiceHeaderData;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.log4j.Logger;
import org.fest.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 * The structure of this method is not difficult to understand in the given context.
 */
@SuppressWarnings({"squid:RedundantThrowsDeclarationCheck"})
public class SpecialServiceHeaderReversePopulator implements Populator<SpecialServiceHeader, SpecialServiceHeaderData> {


    ServiceAttachmentReversePopulator serviceAttachmentReversePopulator;
    ServiceApplicantReversePopulator serviceApplicantReversePopulator;
    SagiaFormatProvider sagiaFormatProvider;

    private MediaService mediaService;

    private static final Logger LOGGER = Logger.getLogger(SpecialServiceHeaderReversePopulator.class);

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:S134 | Control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776","squid:S134","squid:MethodCyclomaticComplexity"})
    @Override
    public void populate(SpecialServiceHeader specialServiceHeader, SpecialServiceHeaderData specialServiceHeaderData) throws ConversionException {
        specialServiceHeaderData.setOBJECT_ID(specialServiceHeader.getId());
        specialServiceHeaderData.setPROCESS_TYPE(specialServiceHeader.getType());
        specialServiceHeaderData.setZZSER_REGION(specialServiceHeader.getServiceRegion());
        specialServiceHeaderData.setCATEGORY1(specialServiceHeader.getCategory1());
        specialServiceHeaderData.setCAT_CODE1(specialServiceHeader.getCategoryCode1());
        specialServiceHeaderData.setCATEGORY2(specialServiceHeader.getCategory2());
        specialServiceHeaderData.setCAT_CODE2(specialServiceHeader.getCategoryCode2());
        specialServiceHeaderData.setDESCRIPTION(specialServiceHeader.getDescription());
        specialServiceHeaderData.setPOSTING_DATE_FORMATTED(specialServiceHeader.getDate());
        specialServiceHeaderData.setSTATUS(specialServiceHeader.getStatus());
        specialServiceHeaderData.setCOMMENTS(specialServiceHeader.getComments());
        specialServiceHeaderData.setERROR_MSG(specialServiceHeader.getErrorMessage());
        specialServiceHeaderData.setZZEMAIL_ADD(specialServiceHeader.getEmail());
        specialServiceHeaderData.setZZPHONE_NUM(specialServiceHeader.getPhoneNumber());
        specialServiceHeaderData.setZZNEW_COMP(specialServiceHeader.getCompany());
        specialServiceHeaderData.setZZ_CR_NUMBER(specialServiceHeader.getCRNumber());
        specialServiceHeaderData.setZZNEW_PROF(specialServiceHeader.getProfession());
        specialServiceHeaderData.setZZAPP_REASON(specialServiceHeader.getApplicationReason());

        Set<ServiceApplicantData> serviceApplicantDataSet = new HashSet<>();
        if (specialServiceHeader.getApplicants() != null && !specialServiceHeader.getApplicants().isEmpty()) {
            for (ServiceApplicant item : specialServiceHeader.getApplicants()) {
                item.setObjectGuid(null);
                ServiceApplicantData serviceApplicantData = new ServiceApplicantData();
                serviceApplicantReversePopulator.populate(item, serviceApplicantData);
                serviceApplicantDataSet.add(serviceApplicantData);
            }
        }
        specialServiceHeaderData.setTOAPPLICANTS(serviceApplicantDataSet);


        Set<ServiceAttachmentData> serviceAttachmentDataSet = new HashSet<>();
        specialServiceHeaderData.setTOATTACHMENTS(serviceAttachmentDataSet);

        if (specialServiceHeader.getFiles() != null && !specialServiceHeader.getFiles().isEmpty()) {
            for (int idx = 0; idx < specialServiceHeader.getFiles().size(); idx++) {
                if (specialServiceHeader.getFiles().get(idx) != null) {
                    MultipartFile file = specialServiceHeader.getFiles().get(idx);
                    ServiceAttachment serviceAttachment = new ServiceAttachment();
                    serviceAttachment.setFileName(specialServiceHeader.getFileNames().get(idx));
                    serviceAttachment.setMimeType(file.getContentType());
                    try {
                        serviceAttachment.setFileContentString(org.apache.ws.security.util.Base64.encode(file.getBytes()));
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(),e);
                    }
                    ServiceAttachmentData serviceAttachmentData = new ServiceAttachmentData();
                    serviceAttachmentReversePopulator.populate(serviceAttachment, serviceAttachmentData);
                    serviceAttachmentDataSet.add(serviceAttachmentData);
                }
            }
        }

        if (specialServiceHeader.getDraftFiles() != null && !specialServiceHeader.getDraftFiles().isEmpty()) {
            for (int idx = 0; idx < specialServiceHeader.getDraftFiles().size(); idx++) {
                if (specialServiceHeader.getDraftFiles().get(idx) != null) {
                    final String fileCode = specialServiceHeader.getDraftFiles().get(idx);

                    if (!Strings.isEmpty(fileCode)) {
                        final CatalogUnawareMediaModel uploadedFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                        ServiceAttachment serviceAttachment = new ServiceAttachment();

                        serviceAttachment.setFileName(uploadedFile.getRealFileName());
                        serviceAttachment.setMimeType(uploadedFile.getMime());
                        serviceAttachment.setFileContentString(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(uploadedFile)));

                        ServiceAttachmentData serviceAttachmentData = new ServiceAttachmentData();
                        serviceAttachmentReversePopulator.populate(serviceAttachment, serviceAttachmentData);
                        serviceAttachmentDataSet.add(serviceAttachmentData);
                    }
                }
            }
        }

    }

    /**
     * @param serviceAttachmentReversePopulator serviceAttachmentReversePopulator
     */
    public void setServiceAttachmentReversePopulator(ServiceAttachmentReversePopulator serviceAttachmentReversePopulator) {
        this.serviceAttachmentReversePopulator = serviceAttachmentReversePopulator;
    }

    /**
     * @param serviceApplicantReversePopulator serviceApplicantReversePopulator
     */
    public void setServiceApplicantReversePopulator(ServiceApplicantReversePopulator serviceApplicantReversePopulator) {
        this.serviceApplicantReversePopulator = serviceApplicantReversePopulator;
    }

    /**
     * @param sagiaFormatProvider sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    /**
     *
     * @param mediaService the mediaService
     */
    public void setMediaService(final MediaService mediaService)
    {
        this.mediaService = mediaService;
    }
}
