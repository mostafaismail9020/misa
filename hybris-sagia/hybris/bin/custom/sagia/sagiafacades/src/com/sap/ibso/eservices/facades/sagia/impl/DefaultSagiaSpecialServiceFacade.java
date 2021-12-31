package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.specialservices.*;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.facades.sagia.SagiaSpecialServiceFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SpecialServiceSagiaFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaSpecialServiceFacade
 */
public class DefaultSagiaSpecialServiceFacade implements SagiaSpecialServiceFacade {

    private ZUI5SpecialServiceSagiaFacade zui5SpecialServiceSagiaFacade;
    private SpecialServicePopulator sagiaSpecialServicePopulator;
    private SpecialServiceHeaderPopulator sagiaSpecialServiceHeaderPopulator;
    private SpecialServiceHeaderReversePopulator sagiaSpecialServiceHeaderReversePopulator;
    private SpecialServiceCountryPopulator specialServiceCountryPopulator;
    private SpecialServiceRegionPopulator specialServiceRegionPopulator;
    private SpecialServiceAttachmentPopulator specialServiceAttachmentPopulator;
    private ServiceApplicantPopulator serviceApplicantPopulator;

    @Override
    public Collection<SpecialService> getServices(String serviceDiscriminator) throws IOException {
        Collection<SpecialService> specialServices = new ArrayList<>();
        Collection<SpCheckHistory> spCheckHistoryCollection = zui5SpecialServiceSagiaFacade.getSpecialServices(serviceDiscriminator);
        if(CollectionUtils.isNotEmpty(spCheckHistoryCollection)) {
            for (SpCheckHistory spCheckHistory : spCheckHistoryCollection) {
                SpecialService specialService = new SpecialService();
                sagiaSpecialServicePopulator.populate(spCheckHistory, specialService);
                specialServices.add(specialService);
            }
        }
        return specialServices;
    }
    
	@Override
	public SpecialService getLatestServiceCreated(String serviceDiscriminator) {
		Collection<SpCheckHistory> spCheckHistoryCollection = zui5SpecialServiceSagiaFacade
				.getSpecialServices(serviceDiscriminator);
		SpCheckHistory latestSpCheckHistory = spCheckHistoryCollection.stream().findFirst().orElse(null);
		SpecialService specialService = new SpecialService();
		sagiaSpecialServicePopulator.populate(latestSpCheckHistory, specialService);
		return specialService;
	}

    @Override
    public SpecialServiceHeader getService(Integer id) throws IOException {
        SpecialServiceHeaderData specialServiceHeaderData = zui5SpecialServiceSagiaFacade.getSpecialService(id);
        SpecialServiceHeader specialServiceHeader = new SpecialServiceHeader();
        sagiaSpecialServiceHeaderPopulator.populate(specialServiceHeaderData, specialServiceHeader);
        return specialServiceHeader;
    }

    @Override
    public boolean saveSpecialService(SpecialServiceHeader specialServiceHeader) {
        SpecialServiceHeaderData specialServiceHeaderData = new SpecialServiceHeaderData();
        sagiaSpecialServiceHeaderReversePopulator.populate(specialServiceHeader, specialServiceHeaderData);
        return zui5SpecialServiceSagiaFacade.saveSpecialService(specialServiceHeaderData);
    }

    @Override
    public InputStream getAttachements(String objectId, String documentGuid) {
        return zui5SpecialServiceSagiaFacade.readAttachmentBy(objectId, documentGuid);
    }

    @Override
    public Collection<Country> getCountryCollection(String language) {
        Collection<SpCountryData> spCountryCollection = zui5SpecialServiceSagiaFacade.getCountryCollection(language);
        Collection<Country> countryCollection = new ArrayList<>();
        for (SpCountryData spCountryData : spCountryCollection) {
            Country country = new Country();
            specialServiceCountryPopulator.populate(spCountryData, country);
            countryCollection.add(country);
        }
        return countryCollection;
    }

    @Override
    public Collection<Region> getRegionCollection(String language) {
        Collection<SpRegionData> spRegionCollection = zui5SpecialServiceSagiaFacade.getRegionCollection(language);
        Collection<Region> regionCollection = new ArrayList<>();
        for (SpRegionData spCountryData : spRegionCollection) {
            Region region = new Region();
            specialServiceRegionPopulator.populate(spCountryData, region);
            regionCollection.add(region);
        }
        return regionCollection;
    }

    @Override
    public Collection<ServiceApplicant> getApplicants(String processType, String categoryCode1, String categoryCode2) {
        Collection<ServiceApplicantData> applicantsDataCollection = zui5SpecialServiceSagiaFacade.getApplicantsCollection(
                processType,
                categoryCode1,
                categoryCode2
        );
        Collection<ServiceApplicant> serviceApplicants = new ArrayList<>();
        for(ServiceApplicantData item : applicantsDataCollection){
            ServiceApplicant applicant = new ServiceApplicant();
            serviceApplicantPopulator.populate(item, applicant);
            serviceApplicants.add(applicant);
        }
        return serviceApplicants;
    }

    @Override
    public Collection<Attachment> getAttachmentCollection(String language, String processType, String categoryCode1, String categoryCode2) {
        Collection<SpAttachmentData> spAttachmentCollection = zui5SpecialServiceSagiaFacade.getAttachmentCollection(language, processType, categoryCode1, categoryCode2);
        Collection<Attachment> attachmentCollection = new ArrayList<>();
        for (SpAttachmentData spAttachmentData : spAttachmentCollection) {
            Attachment attachment = new Attachment();
            specialServiceAttachmentPopulator.populate(spAttachmentData, attachment);
            attachmentCollection.add(attachment);
        }
        return attachmentCollection;
    }
    
    /**
     * @param serviceDiscriminator uploadedFiles
     * @param draftFiles
     */
	@Override
	public void validUploadedAttachments(String serviceDiscriminator, List<MultipartFile> uploadedFiles,
                                       final List<String> draftFiles) {
		long supportedAttachmentsSize = getAttachmentCollection("EN", "ZS13", "ZSPECIAL_SERVICES", serviceDiscriminator)
				.stream().count();
		long uploadedFilesSize = uploadedFiles.stream().filter(file -> file.getSize() > 0).count();
        long countOfDraftFiles = 0;
		if(draftFiles != null && CollectionUtils.isNotEmpty(draftFiles)){
            countOfDraftFiles = draftFiles.stream().filter(Strings::isNotEmpty).count();
        }
		if (supportedAttachmentsSize != uploadedFilesSize + countOfDraftFiles) {
			throw new SagiaRuntimeException(
					"The number of uploaded files should be equal to the number of supported attachments!");
		}
	}

    /**
     * @param zui5SpecialServiceSagiaFacade
     */
    public void setZui5SpecialServiceSagiaFacade(ZUI5SpecialServiceSagiaFacade zui5SpecialServiceSagiaFacade) {
        this.zui5SpecialServiceSagiaFacade = zui5SpecialServiceSagiaFacade;
    }

    /**
     * @param sagiaSpecialServicePopulator
     */
    public void setSagiaSpecialServicePopulator(SpecialServicePopulator sagiaSpecialServicePopulator) {
        this.sagiaSpecialServicePopulator = sagiaSpecialServicePopulator;
    }

    /**
     * @param sagiaSpecialServiceHeaderPopulator
     */
    public void setSagiaSpecialServiceHeaderPopulator(SpecialServiceHeaderPopulator sagiaSpecialServiceHeaderPopulator) {
        this.sagiaSpecialServiceHeaderPopulator = sagiaSpecialServiceHeaderPopulator;
    }

    /**
     * @param sagiaSpecialServiceHeaderReversePopulator
     */
    public void setSagiaSpecialServiceHeaderReversePopulator(SpecialServiceHeaderReversePopulator sagiaSpecialServiceHeaderReversePopulator) {
        this.sagiaSpecialServiceHeaderReversePopulator = sagiaSpecialServiceHeaderReversePopulator;
    }

    /**
     * @param specialServiceCountryPopulator
     */
    public void setSpecialServiceCountryPopulator(SpecialServiceCountryPopulator specialServiceCountryPopulator) {
        this.specialServiceCountryPopulator = specialServiceCountryPopulator;
    }

    /**
     * @param specialServiceRegionPopulator
     */
    public void setSpecialServiceRegionPopulator(SpecialServiceRegionPopulator specialServiceRegionPopulator) {
        this.specialServiceRegionPopulator = specialServiceRegionPopulator;
    }

    /**
     * @param specialServiceAttachmentPopulator
     */
    public void setSpecialServiceAttachmentPopulator(SpecialServiceAttachmentPopulator specialServiceAttachmentPopulator) {
        this.specialServiceAttachmentPopulator = specialServiceAttachmentPopulator;
    }

    /**
     * @param serviceApplicantPopulator
     */
    public void setServiceApplicantPopulator(ServiceApplicantPopulator serviceApplicantPopulator) {
        this.serviceApplicantPopulator = serviceApplicantPopulator;
    }

}
