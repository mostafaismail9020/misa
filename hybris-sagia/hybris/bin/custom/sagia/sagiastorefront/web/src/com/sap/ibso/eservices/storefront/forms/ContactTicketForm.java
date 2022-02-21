package com.sap.ibso.eservices.storefront.forms;

import org.springframework.web.multipart.MultipartFile;

public class ContactTicketForm {

    private String comment;
    
    private MultipartFile pdfAttachment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

	public MultipartFile getPdfAttachment() {
		return pdfAttachment;
	}

	public void setPdfAttachment(MultipartFile pdfAttachment) {
		this.pdfAttachment = pdfAttachment;
	}   
}
