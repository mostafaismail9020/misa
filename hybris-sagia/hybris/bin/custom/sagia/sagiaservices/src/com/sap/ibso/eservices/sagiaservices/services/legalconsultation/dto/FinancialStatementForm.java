package com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class FinancialStatementForm implements Serializable{

	private static final long serialVersionUID = 1L;

    private String srId;
    private List<MultipartFile> files;
    private boolean termsAndConditionsChecked;
	private Integer hoursToCompleteSurvey;
	private Integer minutesToCompleteSurvey;
	private String sourceOfKnowledge;
    private List<String> draftFiles;
    private List<String> fileData;


	public void setHoursToCompleteSurvey(Integer hoursToCompleteSurvey) {
		this.hoursToCompleteSurvey = hoursToCompleteSurvey;
	}

	public void setMinutesToCompleteSurvey(Integer minutesToCompleteSurvey) {
		this.minutesToCompleteSurvey = minutesToCompleteSurvey;
	}

	public void setSourceOfKnowledge(String sourceOfKnowledge) {
		this.sourceOfKnowledge = sourceOfKnowledge;
	}

	public Integer getHoursToCompleteSurvey() {
		return hoursToCompleteSurvey;
	}

	public Integer getMinutesToCompleteSurvey() {
		return minutesToCompleteSurvey;
	}

	public String getSourceOfKnowledge() {
		return sourceOfKnowledge;
	}

	public String getSrId() {
		return srId;
	}
	public void setSrId(String srId) {
		this.srId = srId;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public boolean isTermsAndConditionsChecked() {
		return termsAndConditionsChecked;
	}
	public void setTermsAndConditionsChecked(boolean termsAndConditionsChecked) {
		this.termsAndConditionsChecked = termsAndConditionsChecked;
	}
	public List<String> getDraftFiles() {
		return draftFiles;
	}
	public void setDraftFiles(List<String> draftFiles) {
		this.draftFiles = draftFiles;
	}

   public List<String> getFileData() {
        return fileData;
    }

    public void setFileData(List<String> fileData) {
        this.fileData = fileData;
    }
}
