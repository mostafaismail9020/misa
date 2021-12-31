package com.sap.ibso.eservices.sagiaservices.services.licensereplacement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadAssoNavData;

import java.util.Collections;
import java.util.List;

public class LicenseReplacementResubmitFormData {

	@JsonProperty("SrGuid")
	private String srGuid = "";
	@JsonProperty("LicenseReplToUploadNav")
	private List<UploadAssoNavData> attachments = Collections.emptyList();
	private List<LicenseReplacementAttachmentFile> files;
	private List<String> fileData;

	public String getSrGuid() {
		return srGuid;
	}
	public void setSrGuid(String srGuid) {
		this.srGuid = srGuid;
	}
	public List<UploadAssoNavData> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<UploadAssoNavData> attachments) {
		this.attachments = attachments;
	}
	public List<LicenseReplacementAttachmentFile> getFiles() {
		return files;
	}
	public void setFiles(List<LicenseReplacementAttachmentFile> files) {
		this.files = files;
	}

	public List<String> getFileData() {
		return fileData;
	}

	public void setFileData(List<String> fileData) {
		this.fileData = fileData;
	}
}
