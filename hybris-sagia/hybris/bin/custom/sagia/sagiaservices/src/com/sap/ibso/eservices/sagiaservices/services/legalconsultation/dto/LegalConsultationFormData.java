package com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.AttachmantHDRData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

public class LegalConsultationFormData {

    private String srId;
    private String legEnqTitle;
    private String legEnqDesc;
    private String textMsg;
    private List<MultipartFile> files;
    private List<AttachmantHDRData> attachments = Collections.emptyList();
    private boolean termsAndConditionsChecked;
    private List<String> draftFiles;

    public String getSrId() {
        return srId;
    }

    public void setSrId(String srId) {
        this.srId = srId;
    }

    public String getLegEnqTitle() {
        return legEnqTitle;
    }

    public void setLegEnqTitle(String legEnqTitle) {
        this.legEnqTitle = legEnqTitle;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public List<AttachmantHDRData> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmantHDRData> attachments) {
        this.attachments = attachments;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getLegEnqDesc() {
        return legEnqDesc;
    }

    public void setLegEnqDesc(String legEnqDesc) {
        this.legEnqDesc = legEnqDesc;
    }

    public boolean isTermsAndConditionsChecked() {
        return termsAndConditionsChecked;
    }

    public void setTermsAndConditionsChecked(boolean termsAndConditionsChecked) {
        this.termsAndConditionsChecked = termsAndConditionsChecked;
    }

    public List<String> getDraftFiles()
    {
        return draftFiles;
    }

    public void setDraftFiles(final List<String> draftFiles)
    {
        this.draftFiles = draftFiles;
    }
}

