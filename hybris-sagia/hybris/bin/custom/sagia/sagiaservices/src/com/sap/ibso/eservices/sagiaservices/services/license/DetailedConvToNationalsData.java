package com.sap.ibso.eservices.sagiaservices.services.license;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;

public class DetailedConvToNationalsData {

	@JsonProperty("srID")
	private String srID;
	@JsonProperty("srGuid")
	private String srGuid;
	@JsonProperty("srStDesc")
	private String srStDesc;
	@JsonProperty("longDescr")
	private String longDescr;
	@JsonProperty("convNatToContentNav")
	private List<ContentHDRData> convNatToContentNav;
	@JsonProperty("convNatToTextNav")
	private List<GetTextData> getTextDataList;
	
	public String getSrID() {
		return srID;
	}
	public DetailedConvToNationalsData setSrID(String srID) {
		this.srID = srID;
		return this;
	}
	public String getSrGuid() {
		return srGuid;
	}
	public DetailedConvToNationalsData setSrGuid(String srGuid) {
		this.srGuid = srGuid;
		return this;
	}
	public String getSrStDesc() {
		return srStDesc;
	}
	public DetailedConvToNationalsData setSrStDesc(String srStDesc) {
		this.srStDesc = srStDesc;
		return this;
	}
	public String getLongDescr() {
		return longDescr;
	}
	public DetailedConvToNationalsData setLongDescr(String longDescr) {
		this.longDescr = longDescr;
		return this;
	}
	public List<ContentHDRData> getConvNatToContentNav() {
		return convNatToContentNav;
	}
	public DetailedConvToNationalsData setConvNatToContentNav(List<ContentHDRData> convNatToContentNav) {
		this.convNatToContentNav = convNatToContentNav;
		return this;
	}
	public List<GetTextData> getGetTextDataList() {
		return getTextDataList;
	}
	public DetailedConvToNationalsData setGetTextDataList(List<GetTextData> getTextDataList) {
		this.getTextDataList = getTextDataList;
		return this;
	}
}
