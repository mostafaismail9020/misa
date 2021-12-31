package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseCancellationSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadAssoNavData;

import org.apache.ws.security.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LicenseCancellationConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LicenseCancellationConverter.class);
	private LicenseCancellationConverter() {}

	public static LicenseClearanceSetData fromLicenseClearanceFormData(LicenseClearanceFormData licenseClearanceFormData,
			Collection<CustomizingGetData> supportedAttachments) {
		LicenseClearanceSetData newlicense = new LicenseClearanceSetData();
		
		newlicense.setIsInstant(licenseClearanceFormData.getIsInstant());
		newlicense.setTermCond(licenseClearanceFormData.getTermsAndConditionsChecked());
		newlicense.setCrNo(licenseClearanceFormData.getCrNo());
		newlicense.setZakatNo(licenseClearanceFormData.getZakathNo());
		
		if(licenseClearanceFormData.getIsInstant()) {
			List<UploadAssoNavData> srvReqUploadAssoNavData = new ArrayList<>();
			newlicense.setLicenseClearToUploadNav(srvReqUploadAssoNavData);
		}else {
			newlicense.setLicenseClearToUploadNav(
				getLicenseAttachments(licenseClearanceFormData.getFiles(), supportedAttachments));
		}
		
		return newlicense;
	}

	public static LicenseCancellationSetData fromLicenseCancelFormData(LicenseCancelFormData licenseCancelFormData,
																	   Collection<CustomizingGetData> supportedAttachments) {
		LicenseCancellationSetData newlicense = new LicenseCancellationSetData();
		
		newlicense.setIsInstant(licenseCancelFormData.getIsInstant());
		newlicense.setTermCond(licenseCancelFormData.getTermsAndConditionsChecked());
		
		if(licenseCancelFormData.getIsInstant()) {
			List<UploadAssoNavData> srvReqUploadAssoNavData = new ArrayList<>();
			newlicense.setLicenseCancelToUploadNav(srvReqUploadAssoNavData);
		}else {
			newlicense.setLicenseCancelToUploadNav(
					getLicenseAttachments(licenseCancelFormData.getFiles(), supportedAttachments));
		}
		
		return newlicense;
	}

	private static List<UploadAssoNavData> getLicenseAttachments(List<MultipartFile> uploadedFiles,
			Collection<CustomizingGetData> supportedAttachments) {
		if (uploadedFiles.size() != supportedAttachments.size()) {
			throw new IllegalArgumentException("not possible");
		}
		Map<CustomizingGetData, MultipartFile> maps = IntStream.range(0, supportedAttachments.size()).boxed()
				.collect(Collectors.toMap(new ArrayList<CustomizingGetData>(supportedAttachments)::get, uploadedFiles::get));
		return createUploadableLicenseFile(maps);
	}

	private static List<UploadAssoNavData> createUploadableLicenseFile(
			Map<CustomizingGetData, MultipartFile> maps) {
		return maps.entrySet()
				.stream()
				.filter(entry -> entry.getValue().getSize() > 0)
				.map(entry -> createAttachmentToUpload(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}


	private static UploadAssoNavData createAttachmentToUpload(CustomizingGetData customDTO,
			MultipartFile uploadedFile) {
		try {
			UploadAssoNavData uploadedAttachment = new UploadAssoNavData();
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
}
