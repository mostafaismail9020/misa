package com.sap.ibso.eservices.sagiaservices.services.license;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadContentData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;


/**
 * ConvToNationalsConverter
 */

public class ConvToNationalsConverter {

	public static final String NOT_POSSIBLE = "not possible";
	private static final Logger LOGGER = Logger.getLogger(ConvToNationalsConverter.class);

	private ConvToNationalsConverter() { }

	public static ConvToNationalsData fromFormData(ConvToNationalsFormData convToNationalsFormData,
			Collection<CustomizingGetData> supportedAttachments) {
		ConvToNationalsData newConvToNationals = new ConvToNationalsData();
		newConvToNationals.setSrID(convToNationalsFormData.getSrID());
		newConvToNationals.setTransType(convToNationalsFormData.getTransType());
		newConvToNationals.setIsInstant(convToNationalsFormData.getIsInstant());
		newConvToNationals.setTermCond(convToNationalsFormData.getTermsAndConditionsChecked());
		
		if(convToNationalsFormData.getIsInstant()) {
			List<UploadContentData> srvReqConvNatToUploadNav = new ArrayList<>();
			newConvToNationals.setConvNatToUploadNav(srvReqConvNatToUploadNav);
		}else {
			newConvToNationals.setConvNatToUploadNav(
					getConvToNationalsAttachments(convToNationalsFormData.getFiles(), supportedAttachments));
		}
		
		return newConvToNationals;
	}
	
	private static List<UploadContentData> getConvToNationalsAttachments(List<MultipartFile> uploadedFiles,
			Collection<CustomizingGetData> supportedAttachments) {
		if (uploadedFiles.size() != supportedAttachments.size()) {
			throw new IllegalArgumentException(NOT_POSSIBLE);
		}
		Map<CustomizingGetData, MultipartFile> maps = IntStream.range(0, supportedAttachments.size()).boxed()
				.collect(Collectors.toMap(new ArrayList<CustomizingGetData>(supportedAttachments)::get, uploadedFiles::get));

		return createUploadableConvToNatFile(maps);
	}
	
	private static List<UploadContentData> createUploadableConvToNatFile(
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
	
	public static ConvToNationalsData fromResubmitFormData(ConvToNationalsResubmitFormData convToNationalsFormData,
			List<ContentHDRData> previouslyAttachedFiles) {
		ConvToNationalsData newConvToNationals = new ConvToNationalsData();
		newConvToNationals.setConvNatToUploadNav(
				createNewConvToNationalsAttachments(convToNationalsFormData.getFiles(), previouslyAttachedFiles));
		return newConvToNationals;
	}

	private static List<UploadContentData> createNewConvToNationalsAttachments(
			List<ConvertToNationalsAttachmentFile> uploadedFiles,
			List<ContentHDRData> previouslyAttachedFiles) {
		if (uploadedFiles.size() > previouslyAttachedFiles.size()) {
			throw new IllegalArgumentException(NOT_POSSIBLE);
		}

		Map<ContentHDRData, MultipartFile> newFilesToReplace = new HashMap<>();
		for (ContentHDRData oldFile : previouslyAttachedFiles) {

			ConvertToNationalsAttachmentFile matchFile = uploadedFiles.stream()
					.filter(newFile -> newFile.getDocumentID().equals(oldFile.getDocumentID()))
					.findFirst()
					.orElse(null);
			if (Objects.nonNull(matchFile)) {
				newFilesToReplace.put(oldFile, matchFile.getMultiPartFile());
			}
		}
		
		return newFilesToReplace.entrySet()
								.stream()
								.map(entry -> newFileToUpload(entry.getKey(), entry.getValue()))
								.collect(Collectors.toList());
	}
	
	private static UploadContentData newFileToUpload(ContentHDRData previouslyAttachedFile, MultipartFile newFile) {

		try {

			UploadContentData uploadedAttachment = new UploadContentData();
			uploadedAttachment.setFilename(previouslyAttachedFile.getFilename());
			uploadedAttachment.setMimeType("application/pdf");
			uploadedAttachment.setObjectID(previouslyAttachedFile.getObjectId());
			uploadedAttachment.setFileContString(Base64.encode(newFile.getBytes()));
			return uploadedAttachment;
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(),ex);
			throw new SagiaODataException(ex.getMessage());
		}

	}
	
	public static DetailedConvToNationalsData createDetailedConvNationalsData(ConvToNationalsData convToNationalData, Collection<CustomizingGetData> serviceDetails ) {
		
		return new DetailedConvToNationalsData()
				.setSrID(convToNationalData.getSrID())
				.setSrGuid(convToNationalData.getSrGuid())
				.setSrStDesc(convToNationalData.getSrStDesc())
				.setConvNatToContentNav(convToNationalData.getConvNatToContentNav())
				.setGetTextDataList(convToNationalData.getConvNatToTextNav())
				.setLongDescr(serviceDetails.stream()
										    .findFirst()
										    .map(CustomizingGetData::getLongDescr)
										    .orElse(""));
	}
}
