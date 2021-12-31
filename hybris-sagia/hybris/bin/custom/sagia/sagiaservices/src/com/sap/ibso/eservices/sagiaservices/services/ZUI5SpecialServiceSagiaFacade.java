package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.services.impl.*;
import org.springframework.beans.factory.annotation.Required;

import java.io.InputStream;
import java.util.Collection;

/**
 * ZUI5SpecialServiceSagiaFacade
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ZUI5SpecialServiceSagiaFacade {
    private SPCheckHistoryService spCheckHistoryService;

    private SpecialServiceHeaderService specialServiceHeader;

    private SpecialServiceAttachmentService specialServiceAttachment;

    private SpecialServiceCountryService specialServiceCountryService;

    private SpecialServiceRegionService specialServiceRegionService;

    private SPCheckApplicants spCheckApplicants;

    /**
     * Collection of services based on type
     * @param serviceDiscriminator serviceDiscriminator
     * @return Collection of SpCheckHistory
     */
    public Collection<SpCheckHistory> getSpecialServices(String serviceDiscriminator) {
        return spCheckHistoryService.getCollection(serviceDiscriminator);
    }

    /**
     * Get special service by id
     * @param id id
     * @return SpecialServiceHeaderData
     */
    public SpecialServiceHeaderData getSpecialService(Integer id) {
        return specialServiceHeader.get(id);
    }

    /**
     * Saves a special service
     * @param specialServiceHeaderData specialServiceHeaderData
     * @return boolean
     */
    public boolean saveSpecialService(SpecialServiceHeaderData specialServiceHeaderData){
        return specialServiceHeader.saveSpecialService(specialServiceHeaderData);
    }

    /**
     * Loads attachment data from the CRM
     * @param objectId objectId
     * @param documentGuid documentGuid
     * @return InputStream
     */
    public InputStream readAttachmentBy(String objectId, String documentGuid) {
        return specialServiceAttachment.readAttachmentBy(objectId, documentGuid);
    }

    /**
     * Get the countries collection used in special services
     * @param language language
     * @return Collection of SpCountryData
     */
    public Collection<SpCountryData> getCountryCollection(String language){
        return specialServiceCountryService.getCountryCollection(language);
    }

    /**
     * Get the regions collection used in special services
     * @param language language
     * @return Collection of SpRegionData
     */
    public Collection<SpRegionData> getRegionCollection(String language){
        return specialServiceRegionService.getRegionCollection(language);
    }

    /**
     * Category code might be hardcoded CAT_CODE1='ZSPECIAL_SERVICES' (not used anywhere else)
     * @param language language
     * @param processType processType
     * @param categoryCode1 categoryCode1
     * @param categoryCode2 categoryCode2
     * @return Collection of SpAttachmentData
     */
    public Collection<SpAttachmentData> getAttachmentCollection(String language, String processType, String categoryCode1, String categoryCode2){
        return specialServiceAttachment.getCollection(language, processType, categoryCode1, categoryCode2);
    }

    /**
     * Get the collection af avalable applicants
     * @param processType processType
     * @param categoryCode1 categoryCode1
     * @param categoryCode2 categoryCode2
     * @return Collection of ServiceApplicantData
     */
    public Collection<ServiceApplicantData> getApplicantsCollection(String processType, String categoryCode1, String categoryCode2){
        return spCheckApplicants.getCollection(processType, categoryCode1, categoryCode2);
    }

    /**
     * setSpCheckHistoryService
     * @param spCheckHistoryService spCheckHistoryService
     */
    @Required
    public void setSpCheckHistoryService(SPCheckHistoryService spCheckHistoryService) {
        this.spCheckHistoryService = spCheckHistoryService;
    }

    /**
     * setSpecialServiceHeader
     * @param specialServiceHeader specialServiceHeader
     */
    @Required
    public void setSpecialServiceHeader(SpecialServiceHeaderService specialServiceHeader) {
        this.specialServiceHeader = specialServiceHeader;
    }

    /**
     * setSpecialServiceAttachment
     * @param  specialServiceAttachment specialServiceAttachment
     */
    @Required
    public void setSpecialServiceAttachment(SpecialServiceAttachmentService specialServiceAttachment) {
        this.specialServiceAttachment = specialServiceAttachment;
    }

    /**
     * setSpecialServiceCountryService
     * @param specialServiceCountryService specialServiceCountryService
     */
    @Required
    public void setSpecialServiceCountryService(SpecialServiceCountryService specialServiceCountryService) {
        this.specialServiceCountryService = specialServiceCountryService;
    }

    /**
     * setSpecialServiceRegionService
     * @param specialServiceRegionService specialServiceRegionService
     */
    @Required
    public void setSpecialServiceRegionService(SpecialServiceRegionService specialServiceRegionService) {
        this.specialServiceRegionService = specialServiceRegionService;
    }

    /**
     * setSpCheckApplicants
     * @param spCheckApplicants spCheckApplicants
     */
    public void setSpCheckApplicants(SPCheckApplicants spCheckApplicants) {
        this.spCheckApplicants = spCheckApplicants;
    }
}
