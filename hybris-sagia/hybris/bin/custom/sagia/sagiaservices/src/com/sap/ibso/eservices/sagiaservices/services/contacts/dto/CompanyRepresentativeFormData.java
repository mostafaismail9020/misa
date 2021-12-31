/**
 * ***********************************************************************
 * Copyright (c) 2017, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * Moscow, Russian Federation
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.contacts.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.contacts.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CompanyRepresentativeFormData {
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String nationality;
    private MultipartFile fileNationalId;
    private MultipartFile fileGovDoc;

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber
     */
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality
     */
    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return
     */
    public MultipartFile getFileNationalId() {
        return fileNationalId;
    }

    /**
     * @param fileNationalId
     */
    public void setFileNationalId(final MultipartFile fileNationalId) {
        this.fileNationalId = fileNationalId;
    }

    /**
     * @return
     */
    public MultipartFile getFileGovDoc() {
        return fileGovDoc;
    }

    /**
     * @param fileGovDoc
     */
    public void setFileGovDoc(final MultipartFile fileGovDoc) {
        this.fileGovDoc = fileGovDoc;
    }
}
