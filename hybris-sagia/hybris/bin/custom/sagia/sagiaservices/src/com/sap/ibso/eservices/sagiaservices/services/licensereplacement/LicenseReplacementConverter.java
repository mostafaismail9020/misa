package com.sap.ibso.eservices.sagiaservices.services.licensereplacement;

import com.sap.ibso.eservices.facades.data.replace.LicenseReplaceMents;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadContentData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import org.apache.ws.security.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public final class LicenseReplacementConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LicenseReplacementConverter.class);

	private LicenseReplacementConverter() {
	}

	public static LicenseReplaceMentData fromFormData(LicenseReplacementFormData licenseReplacementFormData,
			Collection<CustomizingGetData> supportedAttachments) {
		LicenseReplaceMentData newlicense = new LicenseReplaceMentData();
		newlicense.setLicenseReplToUploadNav(
				getLicenseReplacementAttachments(licenseReplacementFormData.getFiles(), supportedAttachments));
		return newlicense;
	}


	private static List<UploadContentData> getLicenseReplacementAttachments(List<MultipartFile> uploadedFiles,
			Collection<CustomizingGetData> supportedAttachments) {
		if (uploadedFiles.size() != supportedAttachments.size()) {
			throw new IllegalArgumentException("not possible");
		}
		Map<CustomizingGetData, MultipartFile> maps = IntStream.range(0, supportedAttachments.size()).boxed()
				.collect(Collectors.toMap(new ArrayList<CustomizingGetData>(supportedAttachments)::get, uploadedFiles::get));
		return createUploadableLicenseReplacemnetFile(maps);
	}

	private static List<UploadContentData> createUploadableLicenseReplacemnetFile(
			Map<CustomizingGetData, MultipartFile> maps) {
		return maps.entrySet()
				.stream()
				.filter(entry -> entry.getValue().getSize() > 0)
				.map(entry -> createAttachmentToUpload(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	private static UploadContentData createAttachmentToUpload(CustomizingGetData customDTO,
			MultipartFile uploadedFile) {
		try {
			UploadContentData uploadedAttachment = new UploadContentData();
			uploadedAttachment.setFilename(uploadedFile.getOriginalFilename());
			uploadedAttachment.setMimeType("application/pdf");
			uploadedAttachment.setFileContString(Base64.encode(uploadedFile.getBytes()));
			uploadedAttachment.setFileCont("");
			uploadedAttachment.setDockey_ID(customDTO.getDockey_ID());

			return uploadedAttachment;
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(),ex);
			return null;
		}
	}
	
	public static LicenseReplaceMentData fromResubmitFormData(LicenseReplacementResubmitFormData licenseReplacementFormData,
			List<ContentHDRData> previouslyAttachedFiles) {
		LicenseReplaceMentData licenseReplaceMentData = new LicenseReplaceMentData();
		licenseReplaceMentData.setLicenseReplToUploadNav(createNewLicenseReplacementsAttachments(
				licenseReplacementFormData.getFiles(), previouslyAttachedFiles));
		return licenseReplaceMentData;
	}
	
	private static List<UploadContentData> createNewLicenseReplacementsAttachments(
			List<LicenseReplacementAttachmentFile> uploadedFiles,
			List<ContentHDRData> previouslyAttachedFiles) {
		if (uploadedFiles.size() > previouslyAttachedFiles.size()) {
			throw new IllegalArgumentException("The uploaded files for License Replacement are more than the supported attachments!");
		}
		Map<ContentHDRData, MultipartFile> newFilesToReplace = new HashMap<>();
		for (ContentHDRData oldFile : previouslyAttachedFiles) {

			LicenseReplacementAttachmentFile matchFile = uploadedFiles
					.stream()
					.filter(newFile -> newFile.getDocumentID().equals(oldFile.getDocumentID()))
					.findFirst()
					.orElse(null);
			if (Objects.nonNull(matchFile)) {
				newFilesToReplace.put(oldFile, matchFile.getMultiPartFile());
			}
		}
		return newFilesToReplace
					.entrySet()
					.stream()
					.map(entry -> newFileToUpload(entry.getKey(), entry.getValue()))
					.collect(Collectors.toList());
	}

	private static UploadContentData newFileToUpload(ContentHDRData previouslyAttachedFile,
			MultipartFile newFile) {
		try {

			UploadContentData uploadedAttachment = new UploadContentData();
			uploadedAttachment.setMimeType("application/pdf");
			uploadedAttachment.setFileContString(Base64.encode(newFile.getBytes()));
			uploadedAttachment.setFilename(previouslyAttachedFile.getFilename());
			uploadedAttachment.setDocumentID(previouslyAttachedFile.getDocumentID());
			uploadedAttachment.setObjectID(previouslyAttachedFile.getObjectId());
			return uploadedAttachment;
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(),ex);
			throw new SagiaODataException(ex.getMessage());
		}
	}

	public static DetailedLicenseReplacementData createDetailedLicenseReplacementData(LicenseReplaceMents licensereplacement, Collection<CustomizingGetData> serviceDetails ) {

		return new DetailedLicenseReplacementData()
				.setSrID(licensereplacement.getSrID())
				.setSrGuid(licensereplacement.getSrGuid())
				.setSrStDesc(licensereplacement.getSrStDesc())
				.setLicenseReplToContentNav(licensereplacement.getUploadedAttachments())
				.setGetTextDataList(licensereplacement.getMessages())
				.setLongDescr(serviceDetails.stream()
						.findFirst()
						.map(CustomizingGetData::getLongDescr)
						.orElse(""));
	}

}
