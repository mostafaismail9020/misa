package com.sap.ibso.eservices.storefront.forms;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class SupportVisitForm implements Serializable {
	private static final long serialVersionUID = 1L;
    private String dateString;
    transient private List<MultipartFile> files;
    private String message;
    private List<String> fileData;
	private String addInfo;
    
    public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<String> getFileData() {
        return fileData;
    }

    public void setFileData(List<String> fileData) {
        this.fileData = fileData;
    }
}
