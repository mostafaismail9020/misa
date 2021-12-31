package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Arrays;

/**
 * ContentDetailsService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class AppointmentPdfService extends AbstractSagiaService<ContentDetailsData> {
	/**
	 * reads AttachmentBy
	 * @param id id
	 * @return InputStream
	 */
	public InputStream readAttachmentBy(String id) {
		return getMediaEntity(getEntitySetName(), Arrays.asList(id));
	}

}
