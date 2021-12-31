package com.sap.ibso.eservices.sagiaservices.services.licensereplacement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementMessages;
import com.sap.ibso.eservices.facades.data.license.replace.LicenseReplacementAttachments;

import java.util.List;

public class DetailedLicenseReplacementData {

	@JsonProperty("srID")
	private String srID;
	@JsonProperty("srGuid")
	private String srGuid;
	@JsonProperty("srStDesc")
	private String srStDesc;
	@JsonProperty("longDescr")
	private String longDescr;

	@JsonProperty("LicenseReplToUploadNav")
	private List<LicenseReplacementAttachments> licenseReplToUploadNav ;

	@JsonProperty("LicenseReplToTextNav")
	private List<LicenseReplacementMessages> getTextDataList;
	
	public String getSrID() {
		return srID;
	}
	public DetailedLicenseReplacementData setSrID(String srID) {
		this.srID = srID;
		return this;
	}
	public String getSrGuid() {
		return srGuid;
	}
	public DetailedLicenseReplacementData setSrGuid(String srGuid) {
		this.srGuid = srGuid;
		return this;
	}
	public String getSrStDesc() {
		return srStDesc;
	}
	public DetailedLicenseReplacementData setSrStDesc(String srStDesc) {
		this.srStDesc = srStDesc;
		return this;
	}
	public String getLongDescr() {
		return longDescr;
	}
	public DetailedLicenseReplacementData setLongDescr(String longDescr) {
		this.longDescr = longDescr;
		return this;
	}

	public List<LicenseReplacementAttachments> getLicenseReplToUploadNav() {
		return licenseReplToUploadNav;
	}

	public void setLicenseReplToUploadNav(List<LicenseReplacementAttachments> licenseReplToUploadNav) {
		this.licenseReplToUploadNav = licenseReplToUploadNav;
	}

	public DetailedLicenseReplacementData setLicenseReplToContentNav(List<LicenseReplacementAttachments> licenseReplToContentNav) {
		this.licenseReplToUploadNav = licenseReplToContentNav;
		return this;
	}
	public List<LicenseReplacementMessages> getGetTextDataList() {
		return getTextDataList;
	}
	public DetailedLicenseReplacementData setGetTextDataList(List<LicenseReplacementMessages> getTextDataList) {
		this.getTextDataList = getTextDataList;
		return this;
	}
}
