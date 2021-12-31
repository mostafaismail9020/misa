/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at Jun 21, 2018 1:15:47 PM
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

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class SpecialServiceForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private String serviceRegion;
    private String phoneNumber;
    private String email;
    private List<String> applicantName;
    private List<String> iqmaNumber;
    private List<String> iqmaExpiryDate;
    private List<String> nationality;
    private List<String> nationalityNote;
    private List<String> applicantProfession;
    private List<String> investorNumber;

    transient private List<MultipartFile> files;

    public SpecialServiceForm() {
        // default constructor
    }

    public String getServiceRegion() {
        return serviceRegion;
    }

    public void setServiceRegion(String serviceRegion) {
        this.serviceRegion = serviceRegion;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(List<String> applicantName) {
        this.applicantName = applicantName;
    }

    public List<String> getIqmaNumber() {
        return iqmaNumber;
    }

    public void setIqmaNumber(List<String> iqmaNumber) {
        this.iqmaNumber = iqmaNumber;
    }

    public List<String> getIqmaExpiryDate() {
        return iqmaExpiryDate;
    }

    public void setIqmaExpiryDate(List<String> iqmaExpiryDate) {
        this.iqmaExpiryDate = iqmaExpiryDate;
    }

    public List<String> getNationality() {
        return nationality;
    }

    public void setNationality(List<String> nationality) {
        this.nationality = nationality;
    }

    public List<String> getNationalityNote() {
        return nationalityNote;
    }

    public void setNationalityNote(List<String> nationalityNote) {
        this.nationalityNote = nationalityNote;
    }

    public List<String> getApplicantProfession() {
        return applicantProfession;
    }

    public void setApplicantProfession(List<String> applicantProfession) {
        this.applicantProfession = applicantProfession;
    }

    public List<String> getInvestorNumber() {
        return investorNumber;
    }

    public void setInvestorNumber(List<String> investorNumber) {
        this.investorNumber = investorNumber;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
