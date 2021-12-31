package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZATT_LISTData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_ATTData;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.services.classification.dto.ClassificationUpgradeFormData;
import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ClassificationUpgradeConverter
 */
public final class ClassificationUpgradeConverter {

    private static final Logger LOGGER = Logger.getLogger(ClassificationUpgradeConverter.class);

    private ClassificationUpgradeConverter() {
    }
    /**
     * create Classification Upgrade entity from the payload request
     * map the uploaded files with the supported attachments by their ID
     * @param classificationUpgradeFormData classificationUpgradeFormData
     * @param supportedAttachments supportedAttachments
     */
    public static ZCLASS_DETSETData fromFormData(ClassificationUpgradeFormData classificationUpgradeFormData,
                                                 Collection<ZATT_LISTData> supportedAttachments) {
        ZCLASS_DETSETData newClassificationUpgrade = new ZCLASS_DETSETData();
        newClassificationUpgrade.setAppeal(classificationUpgradeFormData.getAppeal());
        newClassificationUpgrade.setClassProperty(classificationUpgradeFormData.getClassProperty());
        newClassificationUpgrade.setEntity(classificationUpgradeFormData.getEntity());
        newClassificationUpgrade.setZCLASSATT(getClassificationUpgradeAttachments(classificationUpgradeFormData.getFiles(), supportedAttachments));
        return newClassificationUpgrade;
    }

    /**
     * in order to create a request for upgrading a classification requires to upload specific attachments
     * check that the user upload all the files
     * map uploaded file with the corresponding requested file from CRM
     * @param supportedAttachments supportedAttachments
     * @param uploadedFiles uploadedFiles
     */

    private static List<ZCLASS_ATTData> getClassificationUpgradeAttachments(List<MultipartFile> uploadedFiles,
                                                                            Collection<ZATT_LISTData> supportedAttachments) {
        if (uploadedFiles.size() != supportedAttachments.size()) {
            throw new IllegalArgumentException("not possible");
        }
        Map<ZATT_LISTData, MultipartFile> maps = IntStream.range(0, supportedAttachments.size()).boxed()
                .collect(Collectors.toMap(new ArrayList<>(supportedAttachments)::get, uploadedFiles::get));
        return createClassificationUpgradeFile(maps);
    }

    private static List<ZCLASS_ATTData> createClassificationUpgradeFile(
            Map<ZATT_LISTData, MultipartFile> maps) {
        return maps.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getSize() > 0)
                .map(entry -> createAttachmentToUpload(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static ZCLASS_ATTData createAttachmentToUpload(ZATT_LISTData supportedAttachment, MultipartFile uploadedFile) {
        try {
            ZCLASS_ATTData uploadedAttachment = new ZCLASS_ATTData();
            uploadedAttachment.setFilename(supportedAttachment.getAttachmentName());
            uploadedAttachment.setMimeType("application/pdf");
            uploadedAttachment.setFileContString(Base64.encode(uploadedFile.getBytes()));
            return uploadedAttachment;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(),ex);
            return null;
        }
    }
}