package com.sap.ibso.eservices.sagiaservices.services.complaints;

import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GeneralInquirySet;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.UploadAssoNavData;

import org.apache.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ContactUsTicketConverter {
	
	private static final Logger LOGGER = Logger.getLogger(ComplaintsEnquiryConverter.class);
	
	private ContactUsTicketConverter() {}
	
	public static GeneralInquirySet fromFormToData(ContactUsFormData contactUsFormData) {
		GeneralInquirySet newContactTicket = new GeneralInquirySet();
		newContactTicket.setFirstName(contactUsFormData.getFirstName());
		newContactTicket.setLastName(contactUsFormData.getLastName());
		newContactTicket.setTelNo(contactUsFormData.getPhoneNumber());
		newContactTicket.setEmail(contactUsFormData.getEmail());
		newContactTicket.setTextMsg(contactUsFormData.getMessage());
		/*
		 * newContactTicket.setAddress("asdsad"); THIS ATTRIBUTE IS NOT USED
		 */		
		newContactTicket.setCategory1(contactUsFormData.getSelectedCategoryOne());
		newContactTicket.setEnquiryType(contactUsFormData.getSelectedEnquiryType());
		
		return newContactTicket;
	}

}
