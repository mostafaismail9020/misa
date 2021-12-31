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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.contacts.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ContactUpdateDTO {
    @JsonProperty("TransType")
    private String transactionType = "ZSR1";
    @JsonProperty("SrvBPHdrToBPContactNav")
    private List<ContactUpdateData> contacts = new ArrayList<>();
    @JsonProperty("SrvBPHdrToUploadAssoNav")
    private List<ContactUpdateFileUploadData> files = new ArrayList<>();

    /**
     * @return
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType
     */
    public void setTransactionType(final String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return
     */
    public List<ContactUpdateData> getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     */
    public void setContacts(final List<ContactUpdateData> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return
     */
    public List<ContactUpdateFileUploadData> getFiles() {
        return files;
    }

    /**
     * @param files
     */
    public void setFiles(final List<ContactUpdateFileUploadData> files) {
        this.files = files;
    }
}
