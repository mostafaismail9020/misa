package com.sap.ibso.eservices.sagiaservices.services.complaints;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToDetailNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadAssoNavData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
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
 * ComplaintsEnquiryConverter
 */
public final class ComplaintsEnquiryConverter {

	private static final Logger LOGGER = Logger.getLogger(ComplaintsEnquiryConverter.class);

	private ComplaintsEnquiryConverter() {}

	/**
	 * create Complaint entity from the payload request
	 * map the uploaded files with the supported attachments by their ID
	 * @param complaintFormData complaintFormData
	 * @param supportedAttachments supportedAttachments
	 * @return ComplaintsAndEnquiryHdrsData
	 */
	public static ComplaintsAndEnquiryHdrsData fromFormData(ComplaintFormData complaintFormData,
			Collection<CustomizingGetData> supportedAttachments) {
		ComplaintsAndEnquiryHdrsData newComplaint = new ComplaintsAndEnquiryHdrsData();
		newComplaint.setCompAndEnqHdrToUploadAssoNav(
				getComplaintAttachments(complaintFormData.getFiles(), supportedAttachments));
		newComplaint.setCompAndEnqHdrToDetailNav(getComplaintDetails(complaintFormData.getDetails()));
		
		return newComplaint;
	}


	/**
	 * create Complaint entity from the payload request
	 * @param updatableDetails updatableDetails
	 * @param complaintId complaintId
	 * @return ComplaintsAndEnquiryHdrsData
	 */
	public static ComplaintsAndEnquiryHdrsData fromUpdatableDetails(UpdatableComplaintDetails updatableDetails,
			String complaintId) {

		CompAndEnqHdrToDetailNavData complaintDetailsData = new CompAndEnqHdrToDetailNavData();
		complaintDetailsData.setTextMsg(updatableDetails.getTextMsg());
		complaintDetailsData.setSrID(complaintId);// The "unique" complaint id is not taking into consideration by crm
													// service when updating
		ComplaintsAndEnquiryHdrsData complaintToUpdate = new ComplaintsAndEnquiryHdrsData();
		complaintToUpdate.setSrID(complaintId); // the id is read from the navigation entity of the main entity (o_o)
		complaintToUpdate.setAction("U");
		complaintToUpdate.setCompAndEnqHdrToDetailNav(complaintDetailsData);

		return complaintToUpdate;
	}

	/**
	 * retrieves ComplaintDetails
	 * @param details details
	 * @return CompAndEnqHdrToDetailNavData
	 */
	private static CompAndEnqHdrToDetailNavData getComplaintDetails(CompAndEnqHdrToDetailNavData details) {
		
		CompAndEnqHdrToDetailNavData detailDTO = new CompAndEnqHdrToDetailNavData();
		detailDTO.setBranch(details.getBranch());
		detailDTO.setCategory1(details.getCategory1());
		detailDTO.setCategory2(details.getCategory2());
		detailDTO.setEnquiryType(details.getEnquiryType());
		detailDTO.setSrID(details.getSrID());
		detailDTO.setTextMsg(details.getTextMsg());
		
		return detailDTO;
	}


	/**
	 * creating a new complaint requires to upload specific attachments
	 * check that the user does upload all the files (and no one extra)
	 * map uploaded file with the corresponding requested file from CRM by their
	 * unique identifier (dockey_ID)
	 * @param uploadedFiles uploadedFiles
	 * @param supportedAttachments supportedAttachments
	 * @return List of UploadAssoNavData
	 */
	private static List<UploadAssoNavData> getComplaintAttachments(List<MultipartFile> uploadedFiles,
			Collection<CustomizingGetData> supportedAttachments) {
		if(uploadedFiles.size() != supportedAttachments.size()) {
			throw new IllegalArgumentException("not possible");
		}
		Map<CustomizingGetData, MultipartFile> maps = IntStream.range(0, supportedAttachments.size()).boxed()
				.collect(Collectors.toMap(new ArrayList<>(supportedAttachments)::get, uploadedFiles::get));
		return createUploadableComplaintFile(maps);
	}

	/**
	 * creates UploadableComplaintFile
	 * @param maps maps
	 * @return List of UploadAssoNavData
	 */
	private static List<UploadAssoNavData> createUploadableComplaintFile(
			Map<CustomizingGetData, MultipartFile> maps) {
		return maps.entrySet()
				   .stream()
				   .filter(entry -> entry.getValue().getSize() > 0)
				   .map(entry -> createAttachmentToUpload(entry.getKey(), entry.getValue()))
				   .collect(Collectors.toList());
	}

	/**
	 * TODO: handle exceptions
	 * build payload for POSTing entity UploadAssoNavData
	 * @param customDTO customDTO
	 * @param uploadedFile uploadedFile
	 * @return UploadAssoNavData
	 */

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
