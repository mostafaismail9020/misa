/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Jun 20, 2018 8:54:01 AM
 * ----------------------------------------------------------------
 *
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.sap.ibso.eservices.storefront.forms;

import com.sap.ibso.eservices.facades.data.NationalInvestorAtachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class NationalInvestorForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private String crNumber;
    private String srqGuid;
    private String srqId;
    private String partner;
    private String nameArabic;
    private String nameEnglish;
    private String capital;
    private String legalStatus;
    private String compNatType;
    private String compNationality;
    private String country;
    private String region;
    private String city;
    private String mobile;
    private String email;
    private String licenceType;
    private String isicSection;
    private String isicDivision;
    private String number700;
    private String zakatNumber;
    private String molNumber;
    private String gosiNumber;
    private String contactName;
    private String contactNationality;
    private String contactCountry;
    private String contactRegion;
    private String contactCity;
    private String contactMobile;
    private String contactEmail;
    private String crIssueDate;
    private String crExpiryDate;
    private String crActivity;
    private String crExpiryCheck;
    private String crValidCheck;
    private String success;
    private String error;
    private List<NationalInvestorAtachment> attachments;
    private List<MultipartFile> files;
    private Boolean termsAndConditionsChecked;

    public void setCrNumber(final String crNumber) {
        this.crNumber = crNumber;
    }

    public String getCrNumber() {
        return crNumber;
    }

    public void setSrqGuid(final String srqGuid) {
        this.srqGuid = srqGuid;
    }

    public String getSrqGuid() {
        return srqGuid;
    }

    public void setSrqId(final String srqId) {
        this.srqId = srqId;
    }

    public String getSrqId() {
        return srqId;
    }

    public void setPartner(final String partner) {
        this.partner = partner;
    }

    public String getPartner() {
        return partner;
    }

    public void setNameArabic(final String nameArabic) {
        this.nameArabic = nameArabic;
    }

    public String getNameArabic() {
        return nameArabic;
    }

    public void setNameEnglish(final String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setCapital(final String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setLegalStatus(final String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getLegalStatus() {
        return legalStatus;
    }

    public void setCompNatType(final String compNatType) {
        this.compNatType = compNatType;
    }

    public String getCompNatType() {
        return compNatType;
    }

    public void setCompNationality(final String compNationality) {
        this.compNationality = compNationality;
    }

    public String getCompNationality() {
        return compNationality;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
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

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setLicenceType(final String licenceType) {
        this.licenceType = licenceType;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setIsicSection(final String isicSection) {
        this.isicSection = isicSection;
    }

    public String getIsicSection() {
        return isicSection;
    }

    public void setIsicDivision(final String isicDivision) {
        this.isicDivision = isicDivision;
    }

    public String getIsicDivision() {
        return isicDivision;
    }

    public void setNumber700(final String number700) {
        this.number700 = number700;
    }

    public String getNumber700() {
        return number700;
    }

    public void setZakatNumber(final String zakatNumber) {
        this.zakatNumber = zakatNumber;
    }

    public String getZakatNumber() {
        return zakatNumber;
    }

    public void setMolNumber(final String molNumber) {
        this.molNumber = molNumber;
    }

    public String getMolNumber() {
        return molNumber;
    }

    public void setGosiNumber(final String gosiNumber) {
        this.gosiNumber = gosiNumber;
    }

    public String getGosiNumber() {
        return gosiNumber;
    }

    public void setContactName(final String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactNationality(final String contactNationality) {
        this.contactNationality = contactNationality;
    }

    public String getContactNationality() {
        return contactNationality;
    }

    public void setContactCountry(final String contactCountry) {
        this.contactCountry = contactCountry;
    }

    public String getContactCountry() {
        return contactCountry;
    }

    public void setContactRegion(final String contactRegion) {
        this.contactRegion = contactRegion;
    }

    public String getContactRegion() {
        return contactRegion;
    }

    public void setContactCity(final String contactCity) {
        this.contactCity = contactCity;
    }

    public String getContactCity() {
        return contactCity;
    }

    public void setContactMobile(final String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactEmail(final String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setCrIssueDate(final String crIssueDate) {
        this.crIssueDate = crIssueDate;
    }

    public String getCrIssueDate() {
        return crIssueDate;
    }

    public void setCrExpiryDate(final String crExpiryDate) {
        this.crExpiryDate = crExpiryDate;
    }

    public String getCrExpiryDate() {
        return crExpiryDate;
    }

    public void setCrActivity(final String crActivity) {
        this.crActivity = crActivity;
    }

    public String getCrActivity() {
        return crActivity;
    }

    public void setCrExpiryCheck(final String crExpiryCheck) {
        this.crExpiryCheck = crExpiryCheck;
    }

    public String getCrExpiryCheck() {
        return crExpiryCheck;
    }

    public void setCrValidCheck(final String crValidCheck) {
        this.crValidCheck = crValidCheck;
    }

    public String getCrValidCheck() {
        return crValidCheck;
    }

    public void setSuccess(final String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public List<NationalInvestorAtachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<NationalInvestorAtachment> attachments) {
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
}
