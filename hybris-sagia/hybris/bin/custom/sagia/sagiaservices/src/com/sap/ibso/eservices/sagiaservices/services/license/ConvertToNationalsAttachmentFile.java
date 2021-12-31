package com.sap.ibso.eservices.sagiaservices.services.license;

import org.springframework.web.multipart.MultipartFile;

public class ConvertToNationalsAttachmentFile {

	private MultipartFile multiPartFile;
	private String documentID;
	
	public MultipartFile getMultiPartFile() {
		return multiPartFile;
	}
	public void setMultiPartFile(MultipartFile multiPartFile) {
		this.multiPartFile = multiPartFile;
	}
	public String getDocumentID() {
		return documentID;
	}
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

}
