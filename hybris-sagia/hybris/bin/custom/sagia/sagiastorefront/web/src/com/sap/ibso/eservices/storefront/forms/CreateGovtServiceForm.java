package com.sap.ibso.eservices.storefront.forms;

import com.sap.ibso.eservices.sagiaservices.data.CRMGovtServiceUploadData;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class CreateGovtServiceForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private String srID;
    private String ministryType;
    private String serviceType;
    transient private List<GovtServicesToUploadNav> formFiles;
    transient private List<MultipartFile> files;
    private List<String> dockeyID;
    private boolean consentBox;
    private String categoryUrl;
    private String serviceName;
    private Collection<CRMGovtServiceUploadData> documentsToUpload;
    private List<String> fileText;
    private List<String> fileData;
    private Boolean termsAndConditionsChecked;

    public String getSrID() {
        return srID;
    }

    public void setSrID(String srID) {
        this.srID = srID;
    }

    public String getMinistryType() {
        return ministryType;
    }

    public void setMinistryType(String ministryType) {
        this.ministryType = ministryType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<GovtServicesToUploadNav> getFormFiles() {
        return formFiles;
    }

    public void setFormFiles(List<GovtServicesToUploadNav> formFiles) {
        this.formFiles = formFiles;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public boolean isConsentBox() {
        return consentBox;
    }

    public void setConsentBox(boolean consentBox) {
        this.consentBox = consentBox;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<String> getDockeyID() {
        return dockeyID;
    }

    public void setDockeyID(List<String> dockeyID) {
        this.dockeyID = dockeyID;
    }

    public Collection<CRMGovtServiceUploadData> getDocumentsToUpload() {
        return documentsToUpload;
    }

    public void setDocumentsToUpload(Collection<CRMGovtServiceUploadData> documentsToUpload) {
        this.documentsToUpload = documentsToUpload;
    }

    public List<String> getFileText() {
        return fileText;
    }

    public void setFileText(List<String> fileText) {
        this.fileText = fileText;
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
