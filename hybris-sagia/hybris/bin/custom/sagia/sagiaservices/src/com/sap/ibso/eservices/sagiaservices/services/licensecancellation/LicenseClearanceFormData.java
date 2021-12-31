package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadAssoNavData;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

public class LicenseClearanceFormData {

	@JsonProperty("SrID")
	private String srID = "";
	@JsonProperty("TransType")
	private String transType ="";
	@JsonProperty("IsInstant")
	private Boolean isInstant;
	@JsonProperty("LicenseReplToUploadNav")
	private List<UploadAssoNavData> attachments = Collections.emptyList();
	private List<MultipartFile> files;
	private Boolean termsAndConditionsChecked;
	private List<String> fileData;
	@JsonProperty("CrNo")
	private String crNo = "";
	@JsonProperty("ZakathNo")
	private String zakathNo ="";


	
	public String getCrNo() {
		return crNo;
	}
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	public String getZakathNo() {
		return zakathNo;
	}
	public void setZakathNo(String zakathNo) {
		this.zakathNo = zakathNo;
	}
	public Boolean getIsInstant() {
		return isInstant;
	}
	public void setIsInstant(Boolean isInstant) {
		this.isInstant = isInstant;
	}
	public String getSrID() {
		return srID;
	}
	public void setSrID(String srID) {
		this.srID = srID;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public List<UploadAssoNavData> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<UploadAssoNavData> attachments) {
		this.attachments = attachments;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public Boolean getTermsAndConditionsChecked() {
		return termsAndConditionsChecked;
	}

	public void setTermsAndConditionsChecked(Boolean termsAndConditionsChecked) {
		this.termsAndConditionsChecked = termsAndConditionsChecked;
	}


	public List<String> getFileData() {
		return fileData;
	}

	public void setFileData(List<String> fileData) {
		this.fileData = fileData;
	}

}
