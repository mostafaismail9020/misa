package com.sap.ibso.eservices.sagiaservices.services.attachments;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * ContentDetailsService
 */
public class ContentDetailsService extends AbstractSagiaService<ContentDetailsData> {

	/**
	 * read MediaEntity from service by documents IDs
	 * @param objectIDValue objectIDValue
	 * @param documentIdValue documentIdValue
	 * @return InputStream
	 */
	public InputStream readAttachmentBy(String objectIDValue, String documentIdValue) {
		if(Objects.isNull(objectIDValue) || Objects.isNull(documentIdValue)) {
			return null;
		}
		String objectId = "ObjectId=" + "'" + objectIDValue + "'";
		String documentId = "DocumentID=" + "'" + documentIdValue + "'";
		return getMediaEntity(getEntitySetName(), Arrays.asList(objectId, documentId));
	}


	/**
	 * read MediaEntity from service by documents IDs
	 * @param objectIDValue objectIDValue
	 * @param documentIdValue documentIdValue
	 * @return AttachmentFile
	 */
	public AttachmentFile downloadAttachmentBy(String objectIDValue, String documentIdValue) {
		String objectId = "ObjectId=" + "'" + objectIDValue + "'";
		String documentId = "DocumentID=" + "'" + documentIdValue + "'";
		return downloadFile(getEntitySetName(), Arrays.asList(objectId, documentId));
	}
}
