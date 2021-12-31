package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GenInqAttSet;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.customizinglist.CustomizingGetListFilterExpression;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;


import java.util.Collection;
import java.util.Map;
import org.apache.ws.security.util.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;

import java.io.IOException;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;


public class GeneralInquirySetService extends AbstractSagiaService<GenInqAttSet> {
	
	private static final Logger LOGGER = Logger.getLogger(GeneralInquirySetService.class);
	
	public void readContactUsSupportingAttachment(String OBJECT_UID, ContactUsFormData contactUsFormData) {
		try {
	    GenInqAttSet genInqAttSet = new GenInqAttSet();
		MultipartFile uploadedFile = contactUsFormData.getContactfile();
		genInqAttSet.setFilename(uploadedFile.getOriginalFilename());
		genInqAttSet.setMimeType(OBJECT_UID);
		genInqAttSet.setFileContString(Base64.encode(uploadedFile.getBytes()));
		/*
		 * These attributes were no used now
		 * genInqAttSet.setMimeType("application/pdf"); genInqAttSet.setSize(objectId);
		 * genInqAttSet.setDOC_GUID("1234"); genInqAttSet.setOBJECT_ID(OBJECT_UID);
		 * genInqAttSet.setSUCCESS(objectId);
		 */
		save(genInqAttSet);
		} catch(IOException ex){ 
			LOGGER.error(ex.getMessage(),ex);  
			}
		
	}
	
    }

