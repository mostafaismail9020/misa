package com.sap.ibso.eservices.sagiaservices.services.license;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadAssoNavData;

public class ConvToNationalsResubmitFormData {

	@JsonProperty("SrGuid")
	private String srGuid = "";
	@JsonProperty("ConvNatToUploadNav")
	private List<UploadAssoNavData> attachments = Collections.emptyList();
	private List<ConvertToNationalsAttachmentFile> files;
	
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
	public List<ConvertToNationalsAttachmentFile> getFiles() {
		return files;
	}
	public void setFiles(List<ConvertToNationalsAttachmentFile> files) {
		this.files = files;
	}
}
