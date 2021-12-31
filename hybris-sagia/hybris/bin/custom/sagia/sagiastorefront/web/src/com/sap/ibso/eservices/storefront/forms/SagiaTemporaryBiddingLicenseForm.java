package com.sap.ibso.eservices.storefront.forms;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class SagiaTemporaryBiddingLicenseForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String companyNameInArabic;
    private String companyNameInEnglish;
    private String projectNameInArabic;
    private String projectNameInEnglish;
    private String capital;
    private String governmentEntity;
    private String country;
    private String city;
    private String postalCode;
    private String poBox;
    private String telephone;
    private String mobile;
    private String email;
    transient private List<MultipartFile> temporaryBiddingLicenseMultipartFiles;
    private List<String> draftFileData;
    private Boolean termsAndConditionsChecked;

    public String getCompanyNameInArabic() {
        return companyNameInArabic;
    }

    public void setCompanyNameInArabic(String companyNameInArabic) {
        this.companyNameInArabic = companyNameInArabic;
    }

    public String getCompanyNameInEnglish() {
        return companyNameInEnglish;
    }

    public void setCompanyNameInEnglish(String companyNameInEnglish) {
        this.companyNameInEnglish = companyNameInEnglish;
    }

    public String getProjectNameInArabic() {
        return projectNameInArabic;
    }

    public void setProjectNameInArabic(String projectNameInArabic) {
        this.projectNameInArabic = projectNameInArabic;
    }

    public String getProjectNameInEnglish() {
        return projectNameInEnglish;
    }

    public void setProjectNameInEnglish(String projectNameInEnglish) {
        this.projectNameInEnglish = projectNameInEnglish;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getGovernmentEntity() {
        return governmentEntity;
    }

    public void setGovernmentEntity(String governmentEntity) {
        this.governmentEntity = governmentEntity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MultipartFile> getTemporaryBiddingLicenseMultipartFiles() {
        return temporaryBiddingLicenseMultipartFiles;
    }

    public void setTemporaryBiddingLicenseMultipartFiles(List<MultipartFile> temporaryBiddingLicenseMultipartFiles) {
        this.temporaryBiddingLicenseMultipartFiles = temporaryBiddingLicenseMultipartFiles;
    }

    public List<String> getDraftFileData() {
        return draftFileData;
    }

    public void setDraftFileData(final List<String> draftFileData) {
        this.draftFileData = draftFileData;
    }

    public Boolean getTermsAndConditionsChecked() {
        return termsAndConditionsChecked;
    }

    public void setTermsAndConditionsChecked(Boolean termsAndConditionsChecked) {
        this.termsAndConditionsChecked = termsAndConditionsChecked;
    }
}
