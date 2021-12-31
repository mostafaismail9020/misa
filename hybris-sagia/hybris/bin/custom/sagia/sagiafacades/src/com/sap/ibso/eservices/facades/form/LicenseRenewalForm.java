package com.sap.ibso.eservices.facades.form;

import java.util.List;

import com.sap.ibso.eservices.facades.data.ContentHDRDocument;
import com.sap.ibso.eservices.facades.data.ContentHDRImage;
import org.springframework.web.multipart.MultipartFile;

/**
 * LicenseRenewalForm
 */
public class LicenseRenewalForm
{

    private List<MultipartFile> multipartFile;
    private List<MultipartFile> images;
    private List<String> draftFiles;
    private List<String> dockeyID;

    private List<String> img;

    private String objectId;
    private String srId;
    private String srGuid;
    private String bpID;
    private String bpGuid;
    private String street;
    private String buildingNo;
    private String houseNo;
    private String countryDesc;
    private String country;
    private String zipCode;
    private String additNo;
    private String city;
    private String duration;
    private Boolean termsAndConditionsChecked;
    private List<ContentHDRDocument> previouslyAttachedDocuments;
    private List<ContentHDRImage> previouslyAttachedImages;


    public List<MultipartFile> getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(List<MultipartFile> multipartFile) {
        this.multipartFile = multipartFile;
    }

    public List<String> getDraftFiles()    {
        return draftFiles;
    }

    public void setDraftFiles(final List<String> draftFiles)    {
        this.draftFiles = draftFiles;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<String> getImg()
    {
        return img;
    }

    public void setImg(final List<String> img)
    {
        this.img = img;
    }

    public String getSrId() {
        return srId;
    }

    public void setSrId(String srId) {
        this.srId = srId;
    }

    public String getSrGuid() {
        return srGuid;
    }

    public void setSrGuid(String srGuid) {
        this.srGuid = srGuid;
    }

    public String getBpID() {
        return bpID;
    }

    public void setBpID(String bpID) {
        this.bpID = bpID;
    }

    public String getBpGuid() {
        return bpGuid;
    }

    public void setBpGuid(String bpGuid) {
        this.bpGuid = bpGuid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAdditNo() {
        return additNo;
    }

    public void setAdditNo(String additNo) {
        this.additNo = additNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getDockeyID() {
        return dockeyID;
    }

    public void setDockeyID(List<String> dockeyID) {
        this.dockeyID = dockeyID;
    }

    public Boolean getTermsAndConditionsChecked() {
        return termsAndConditionsChecked;
    }

    public void setTermsAndConditionsChecked(Boolean termsAndConditionsChecked) {
        this.termsAndConditionsChecked = termsAndConditionsChecked;
    }

    public List<ContentHDRDocument> getPreviouslyAttachedDocuments() {
        return previouslyAttachedDocuments;
    }

    public void setPreviouslyAttachedDocuments(List<ContentHDRDocument> previouslyAttachedDocuments) {
        this.previouslyAttachedDocuments = previouslyAttachedDocuments;
    }

    public List<ContentHDRImage> getPreviouslyAttachedImages() {
        return previouslyAttachedImages;
    }

    public void setPreviouslyAttachedImages(List<ContentHDRImage> previouslyAttachedImages) {
        this.previouslyAttachedImages = previouslyAttachedImages;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
    
}
