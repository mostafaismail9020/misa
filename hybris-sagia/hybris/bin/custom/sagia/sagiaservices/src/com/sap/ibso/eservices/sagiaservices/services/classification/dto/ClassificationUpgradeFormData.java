package com.sap.ibso.eservices.sagiaservices.services.classification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZATT_LISTData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * ClassificationUpgradeFormData
 */
public class ClassificationUpgradeFormData {

    @JsonProperty("Appeal")
    private String appeal;
    @JsonProperty("Class")
    private String classProperty;
    @JsonProperty("Entity")
    private String entity;

    @JsonProperty("ZCLASSATT")
    private List<ZATT_LISTData> attachments = Collections.emptyList();
    private List<MultipartFile> files;

    /**
     * @return String
     */
    public String getAppeal() {
        return appeal;
    }

    /**
     * @param appeal appeal
     */
    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    /**
     * @return String
     */
    public String getClassProperty() {
        return classProperty;
    }

    /**
     * @param classProperty classProperty
     */
    public void setClassProperty(String classProperty) {
        this.classProperty = classProperty;
    }

    /**
     * @return String
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity entity
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * @return List of ZATT_LISTData
     */
    public List<ZATT_LISTData> getAttachments() {
        return attachments;
    }


    /**
     * @param attachments attachments
     */
    public void setAttachments(List<ZATT_LISTData> attachments) {
        this.attachments = attachments;
    }

    /**
     * @return List of MultipartFile
     */
    public List<MultipartFile> getFiles() {
        return files;
    }

    /**
     * @param files files
     */
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}