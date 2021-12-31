package com.sap.ibso.eservices.sagiaservices.services.complaints;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GeneralInquirySet;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;

import org.apache.commons.lang.StringUtils;
/**
 * ContactUsService
 */
public class ContactUsService extends AbstractSagiaService<GeneralInquirySet> {

	public GeneralInquirySet createContactUsTicket(ContactUsFormData contactUsFormData) {
		GeneralInquirySet response = super.saveAndParseResponse(ContactUsTicketConverter.fromFormToData(contactUsFormData),GeneralInquirySet.class);
		return response;
	}
}
