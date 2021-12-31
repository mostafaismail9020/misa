package com.sap.ibso.eservices.sagiaservices.services.complaints.dto;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CategorizationSchemaGetListData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToDetailNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class ComplaintFormData {
    private Collection<CategorizationSchemaGetListData> enquiryTypes;
    private Collection<CategorizationSchemaGetListData> categoryOne;
    private Collection<CategorizationSchemaGetListData> categoryTwo;
    private Collection<CustomizingGetData> branches;
    private Collection<CustomizingGetData> attachments;
    private CompAndEnqHdrToDetailNavData details;
    private ComplaintsAndEnquiryHdrsData expandedComplaint;
    private List<MultipartFile> files;
    private List<String> dockeys;

    /**
     * @return
     */
    public ComplaintsAndEnquiryHdrsData getExpandedComplaint() {
        return expandedComplaint;
    }

    /**
     * @param expandedComplaint
     */
    public void setExpandedComplaint(ComplaintsAndEnquiryHdrsData expandedComplaint) {
        this.expandedComplaint = expandedComplaint;
    }

    /**
     * @return
     */
    public List<String> getDockeys() {
        return dockeys;
    }

    /**
     * @param dockeys
     */
    public void setDockeys(List<String> dockeys) {
        this.dockeys = dockeys;
    }

    /**
     * @return
     */
    public List<MultipartFile> getFiles() {
        return files;
    }

    /**
     * @param files
     */
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    /**
     * @return
     */
    public Collection<CustomizingGetData> getAttachments() {
        return attachments;
    }

    /**
     * @param attachments
     */
    public void setAttachments(Collection<CustomizingGetData> attachments) {
        this.attachments = attachments;
    }

    /**
     * @return
     */
    public CompAndEnqHdrToDetailNavData getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(CompAndEnqHdrToDetailNavData details) {
        this.details = details;
    }

    /**
     * @return
     */
    public Collection<CustomizingGetData> getBranches() {
        return branches;
    }

    /**
     * @param branches
     */
    public void setBranches(Collection<CustomizingGetData> branches) {
        this.branches = branches;
    }

    /**
     * @param enquiryTypes
     */
    public void setEnquiryTypes(Collection<CategorizationSchemaGetListData> enquiryTypes) {
        this.enquiryTypes = enquiryTypes;
    }

    /**
     * @return
     */
    public Collection<CategorizationSchemaGetListData> getEnquiryTypes() {
        return enquiryTypes;
    }

    /**
     * @return
     */
    public Collection<CategorizationSchemaGetListData> getCategoryOne() {
        return categoryOne;
    }

    /**
     * @param categoryOne
     */
    public void setCategoryOne(Collection<CategorizationSchemaGetListData> categoryOne) {
        this.categoryOne = categoryOne;
    }

    /**
     * @return
     */
    public Collection<CategorizationSchemaGetListData> getCategoryTwo() {
        return categoryTwo;
    }

    /**
     * @param categoryTwo
     */
    public void setCategoryTwo(Collection<CategorizationSchemaGetListData> categoryTwo) {
        this.categoryTwo = categoryTwo;
    }
}
