/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.contacts;

import com.sap.ibso.eservices.facades.data.account.ContactUpdateAttachment;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateEntity;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateForm;
import com.sap.ibso.eservices.sagiaservices.services.contacts.dto.*;
import com.sap.ibso.eservices.sagiaservices.services.impl.ServiceBpHDRService;
import org.apache.ws.security.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.contacts
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ContactsConverter {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceBpHDRService.class);

    private static final String GM_FILE_NAME = "GM_IQAMA";
    private static final String GM_CONTACT_TYPE = "GM";
    private static final String COMPANY_REPRESENTATIVE_FILE_NAME = "REP1_IQAMA";
    private static final String COMPANY_REPRESENTATIVE_GOS_FILE_NAME = "REP1_GOSI";
    private static final String COMPANY_REPRESENTATIVE_TYPE = "R1";
    private static final String PRIMARY_CONTACT = "1";
    private static final String NOT_PRIMARY_CONTACT = "0";


    private ContactsConverter() { }

    public static ContactUpdateDTO convert(final ContactUpdateForm contactUpdateForm){
        final ContactUpdateDTO result = new ContactUpdateDTO();
        for(ContactUpdateEntity contact : contactUpdateForm.getUpdatedContacts()){
            final ContactUpdateData contactUpdateData = new ContactUpdateData();
            contactUpdateData.setContactType(contact.getContactType());
            contactUpdateData.setFirstName(contact.getFirstName());
            contactUpdateData.setLastName(contact.getLastName());
            contactUpdateData.setMiddleName(contact.getMiddleName());
            contactUpdateData.setEmail(contact.getEmail());
            contactUpdateData.setNationalId(contact.getNationalId());
            contactUpdateData.setMobileNumber(contact.getMobileNumber());
            contactUpdateData.setPrimaryContact(contact.getPrimaryContact());
            result.getContacts().add(contactUpdateData);
        }

        for(ContactUpdateAttachment attachment : contactUpdateForm.getAttachments()){
            final ContactUpdateFileUploadData file = new ContactUpdateFileUploadData();
            file.setFileContString(attachment.getFileContent());
            file.setFilename(attachment.getFileName());
            file.setMimeType(attachment.getFileType());
            result.getFiles().add(file);
        }
        return result;
    }
    /**
     * @param formData
     * @return
     */
    public static ContactUpdateDTO convert(final GeneralManagerFormData formData) {
        final ContactUpdateDTO result = new ContactUpdateDTO();
        result.getFiles().add(convert(formData.getFileNationalId(), GM_FILE_NAME));
        result.getContacts().add(convertContactData(formData, GM_CONTACT_TYPE, PRIMARY_CONTACT));
        return result;
    }

    /**
     * @param formData
     * @return
     */
    public static ContactUpdateDTO convert(final CompanyRepresentativeFormData formData) {
        final ContactUpdateDTO result = new ContactUpdateDTO();
        result.getFiles().add(convert(formData.getFileNationalId(), COMPANY_REPRESENTATIVE_FILE_NAME));
        result.getFiles().add(convert(formData.getFileGovDoc(), COMPANY_REPRESENTATIVE_GOS_FILE_NAME));
        result.getContacts().add(convertContactData(formData, COMPANY_REPRESENTATIVE_TYPE, NOT_PRIMARY_CONTACT));
        return result;
    }

    private static ContactUpdateFileUploadData convert(final MultipartFile file, final String name) {
        final ContactUpdateFileUploadData fileDTO = new ContactUpdateFileUploadData();
        try {
            fileDTO.setFileContString(Base64.encode(file.getBytes()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        fileDTO.setFilename(name);
        fileDTO.setMimeType(file.getContentType());
        return fileDTO;
    }

    private static ContactUpdateData convertContactData(final GeneralManagerFormData formData, final String contactType, final String primaryContact) {
        final ContactUpdateData contactData = new ContactUpdateData();
        contactData.setContactType(contactType);
        contactData.setFirstName(formData.getFirstName());
        contactData.setLastName(formData.getLastName());
        contactData.setMiddleName(formData.getMiddleName());
        contactData.setEmail(formData.getEmail());
        contactData.setNationalId(formData.getNationality());
        contactData.setMobileNumber(formData.getMobileNumber());
        contactData.setPrimaryContact(primaryContact);
        return contactData;
    }

    private static ContactUpdateData convertContactData(final CompanyRepresentativeFormData formData, final String contactType, final String primaryContact) {
        final ContactUpdateData contactData = new ContactUpdateData();
        contactData.setContactType(contactType);
        contactData.setFirstName(formData.getFirstName());
        contactData.setLastName(formData.getLastName());
        contactData.setMiddleName(formData.getMiddleName());
        contactData.setEmail(formData.getEmail());
        contactData.setNationalId(formData.getNationality());
        contactData.setMobileNumber(formData.getMobileNumber());
        contactData.setPrimaryContact(primaryContact);
        return contactData;
    }
}
