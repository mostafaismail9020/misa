package com.sap.ibso.eservices.storefront.forms;


import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentList;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SagiaRealEstateForm  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String objectId;
    private String objectGuid;
    private String requestType;
    private String purchaseType;
    private String plotNo;
    private String plotNo2;
    private String plotNo3;
    private String plotNo4;
    private String plotNo5;
    private String plotArea;
    private String deedNo;
    private String purchaseDate;
    private Boolean outsideMakkah;
    private Boolean approvedIndustrial;
    private String projectValue;
    private String price;
    private String region;
    private String regionName;
    private String city;
    private String cityName;
    private String housingType;
    private String district;
    private String unitNo;
    private String blockNo;
    private String remarks;
    private Boolean thirtyMore;
    private String bpID;
    private String postingDate;
    private String status;
    private boolean termsAndConditionsChecked;
    transient  Set<RealEstateAttachment> attachments;
    transient  private List<MultipartFile> files;
    private List<String> fileData;
    transient Collection<RealEstateAttachmentList> documentsToUpload;
    private List<String> fileText;
    private boolean isResubmittedForm;
    private String identityType;
    private String identityNumber;
    private String deedNumber;
    private String mojDeedDate;
    private String mojVerified;
    private String mojRegion;
    private String mojCity;
    private String mojIssuerCourtName;
    
    

	public String getMojIssuerCourtName() {
		return mojIssuerCourtName;
	}

	public void setMojIssuerCourtName(String mojIssuerCourtName) {
		this.mojIssuerCourtName = mojIssuerCourtName;
	}

	public String getMojRegion() {
		return mojRegion;
	}

	public void setMojRegion(String mojRegion) {
		this.mojRegion = mojRegion;
	}

	public String getMojCity() {
		return mojCity;
	}

	public void setMojCity(String mojCity) {
		this.mojCity = mojCity;
	}

	public String getMojDeedDate() {
		return mojDeedDate;
	}

	public void setMojDeedDate(String mojDeedDate) {
		this.mojDeedDate = mojDeedDate;
	}

	public String getMojVerified() {
		return mojVerified;
	}

	public void setMojVerified(String mojVerified) {
		this.mojVerified = mojVerified;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getDeedNumber() {
		return deedNumber;
	}

	public void setDeedNumber(String deedNumber) {
		this.deedNumber = deedNumber;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public List<String> getFileText() {
        return fileText;
    }

    public void setFileText(List<String> fileText) {
        this.fileText = fileText;
    }


    public Collection<RealEstateAttachmentList> getDocumentsToUpload() {
        return documentsToUpload;
    }

    public void setDocumentsToUpload(Collection<RealEstateAttachmentList> documentsToUpload) {
        this.documentsToUpload = documentsToUpload;
    }

    public void setObjectId(final String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectGuid(final String objectGuid) {
        this.objectGuid = objectGuid;
    }

    public String getObjectGuid() {
        return objectGuid;
    }

    public void setRequestType(final String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setPurchaseType(final String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPlotNo(final String plotNo) {
        this.plotNo = plotNo;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo2(final String plotNo2) {
        this.plotNo2 = plotNo2;
    }

    public String getPlotNo2() {
        return plotNo2;
    }

    public void setPlotNo3(final String plotNo3) {
        this.plotNo3 = plotNo3;
    }

    public String getPlotNo3() {
        return plotNo3;
    }

    public void setPlotNo4(final String plotNo4) {
        this.plotNo4 = plotNo4;
    }

    public String getPlotNo4() {
        return plotNo4;
    }

    public void setPlotNo5(final String plotNo5) {
        this.plotNo5 = plotNo5;
    }

    public String getPlotNo5() {
        return plotNo5;
    }

    public void setPlotArea(final String plotArea) {
        this.plotArea = plotArea;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setDeedNo(final String deedNo) {
        this.deedNo = deedNo;
    }

    public String getDeedNo() {
        return deedNo;
    }

    public void setPurchaseDate(final String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setOutsideMakkah(final Boolean outsideMakkah) {
        this.outsideMakkah = outsideMakkah;
    }

    public Boolean getOutsideMakkah() {
        return outsideMakkah;
    }

    public void setApprovedIndustrial(final Boolean approvedIndustrial) {
        this.approvedIndustrial = approvedIndustrial;
    }

    public Boolean getApprovedIndustrial() {
        return approvedIndustrial;
    }

    public void setProjectValue(final String projectValue) {
        this.projectValue = projectValue;
    }

    public String getProjectValue() {
        return projectValue;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setHousingType(final String housingType) {
        this.housingType = housingType;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setUnitNo(final String unitNo) {
        this.unitNo = unitNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setBlockNo(final String blockNo) {
        this.blockNo = blockNo;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setThirtyMore(final Boolean thirtyMore) {
        this.thirtyMore = thirtyMore;
    }

    public Boolean getThirtyMore() {
        return thirtyMore;
    }

    public void setBpID(final String bpID) {
        this.bpID = bpID;
    }

    public String getBpID() {
        return bpID;
    }

    public void setPostingDate(final String postingDate) {
        this.postingDate = postingDate;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<String> getFileData()
    {
        return fileData;
    }

    public void setFileData(final List<String> fileData)
    {
        this.fileData = fileData;
    }

    public Set<RealEstateAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<RealEstateAttachment> attachments) {
        this.attachments = attachments;
    }

    public boolean isTermsAndConditionsChecked() {
        return termsAndConditionsChecked;
    }

    public void setTermsAndConditionsChecked(boolean termsAndConditionsChecked) {
        this.termsAndConditionsChecked = termsAndConditionsChecked;
    }

    public boolean isResubmittedForm() {
        return isResubmittedForm;
    }

    public void setResubmittedForm(boolean resubmittedForm) {
        isResubmittedForm = resubmittedForm;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
