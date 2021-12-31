package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.specialservices.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * SagiaSpecialServiceFacade
 */
public interface SagiaSpecialServiceFacade {
    /**
     * Retrieves Services Collection
     * @param serviceDiscriminator serviceDiscriminator
     * @return Collection of SpecialService
     * @throws IOException exception
     */
    Collection<SpecialService> getServices(String serviceDiscriminator) throws IOException;

    /**
     * Retrieves LatestService Created
     * @param serviceDiscriminator serviceDiscriminator
     * @return SpecialService
     */
    SpecialService getLatestServiceCreated(String serviceDiscriminator);

    /**
     * Retrieves SpecialServiceHeader by id
     * @param id id
     * @return SpecialServiceHeader
     * @throws IOException exception
     */
    SpecialServiceHeader getService(Integer id) throws IOException;

    /**
     * saves SpecialService
     * @param specialServiceHeader specialServiceHeader
     * @return boolean
     */
    boolean saveSpecialService(SpecialServiceHeader specialServiceHeader);

    /**
     * gets Attachements
     * @param objectId objectId
     * @param documentGuid documentGuid
     * @return InputStream
     */
    InputStream getAttachements(String objectId, String documentGuid);

    /**
     * gets CountryCollection
     * @param language language
     * @return Collection of Country
     */
    Collection<Country> getCountryCollection(String language);

    /**
     * gets Region Collection
     * @param language language
     * @return Collection of Region
     */
    Collection<Region> getRegionCollection(String language);

    /**
     * gets Attachment Collection
     * @param language language
     * @param processType processType
     * @param categoryCode1 categoryCode1
     * @param categoryCode2 categoryCode2
     * @return Collection of Attachment
     */
    Collection<Attachment> getAttachmentCollection(String language, String processType, String categoryCode1, String categoryCode2);

    /**
     * gets Applicants
     * @param processType processType
     * @param categoryCode1 categoryCode1
     * @param categoryCode2 categoryCode2
     * @return Collection of ServiceApplicant
     */
    Collection<ServiceApplicant> getApplicants(String processType, String categoryCode1, String categoryCode2);

    /**
     * validate number of uploaded attachments eq number of supported attachments
     * @param serviceDiscriminator serviceDiscriminator
     * @param uploadedFiles uploadedFiles
     * @param draftFiles draftFiles
     */
    void validUploadedAttachments(String serviceDiscriminator, List<MultipartFile> uploadedFiles, final List<String> draftFiles);
}
