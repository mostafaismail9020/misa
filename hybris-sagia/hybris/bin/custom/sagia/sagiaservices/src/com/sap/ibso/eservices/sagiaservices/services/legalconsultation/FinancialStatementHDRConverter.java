package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.FinancialStatementHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.UploadContentData;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.FinancialStatementForm;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.fest.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

public class FinancialStatementHDRConverter {

    private static final Logger LOGGER = Logger.getLogger(FinancialStatementHDRConverter.class);

    private ModelService modelService;
    private MediaService mediaService;

    private FinancialStatementHDRConverter() {
    }


    /**
     * create Financial Statement entity from the payload request
     *
     * @param financialStatementForm financialStatementForm
     * @return FinancialStatementHDRData
     */
    public FinancialStatementHDRData fromFormData(FinancialStatementForm financialStatementForm) {

    	FinancialStatementHDRData newFinancialStatement = new FinancialStatementHDRData();
        //newFinancialStatement.setFinancialStatement(getFinancialStatementDetails(financialStatementForm));

        /**/

        final List<UploadContentData> uploadedFiles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(financialStatementForm.getDraftFiles())) {
            for (int i = 0; i < financialStatementForm.getFiles().size(); i++)
            {
                if (indexExists(i, financialStatementForm.getDraftFiles()) && !Strings.isEmpty(financialStatementForm.getDraftFiles().get(i))) {
                    final UploadContentData contentData = createAttachmentFromDraftFile(financialStatementForm.getDraftFiles().get(i));
                    uploadedFiles.add(contentData);
                }
                else {
                    final UploadContentData contentData = createAttachmentToUpload(financialStatementForm.getFiles().get(i));
                    uploadedFiles.add(contentData);
                }
            }
        }
        else {
            final List<UploadContentData> files = financialStatementForm.getFiles().stream().map(this::createAttachmentToUpload)
                                                                             .collect(Collectors.toList());
            uploadedFiles.addAll(files);
        }

        newFinancialStatement.setUploadContentSet(uploadedFiles);

        return newFinancialStatement;
    }


    private UploadContentData createAttachmentToUpload(MultipartFile uploadedFile) {
        try {
            UploadContentData uploadedAttachment = new UploadContentData();
            uploadedAttachment.setFileConts(Base64.encode(uploadedFile.getBytes()));
            uploadedAttachment.setFilename(uploadedFile.getOriginalFilename());
            uploadedAttachment.setMimeType("application/pdf");
            return uploadedAttachment;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(),ex);
            return null;
        }
    }

    private UploadContentData createAttachmentFromDraftFile(final String fileCode) {
        final CatalogUnawareMediaModel uploadedFile = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
        UploadContentData uploadedAttachment = new UploadContentData();
        uploadedAttachment.setFilename(uploadedFile.getRealFileName());
        uploadedAttachment.setMimeType(uploadedFile.getMime());
        uploadedAttachment.setFileConts(org.apache.ws.security.util.Base64.encode(mediaService.getDataFromMedia(uploadedFile)));
        return uploadedAttachment;
    }

    private boolean indexExists(int index, List list) {
        return list != null && index < list.size();
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    public MediaService getMediaService()
    {
        return mediaService;
    }

    public void setMediaService(final MediaService mediaService)
    {
        this.mediaService = mediaService;
    }

}
