package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.facades.data.ServiceRequestHDR;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseCountry;
import com.sap.ibso.eservices.facades.data.account.RemovePopupALRResponse;
import com.sap.ibso.eservices.facades.form.LicenseRenewalForm;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.facades.sagia.SagiaDraftFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaRenewMyLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.data.GovtDocCheck;
import com.sap.ibso.eservices.sagiaservices.data.RemovePopupALR;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.impl.GlobalValsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.TemporaryBiddingLicenseListItemService;
import com.sap.ibso.eservices.sagiaservices.services.impl.TemporaryBiddingLicenseService;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * DefaultSagiaRenewMyLicenceFacade
 */
public class DefaultSagiaRenewMyLicenceFacade implements SagiaRenewMyLicenseFacade {

    private ZUI5SagiaFacade zui5SagiaFacade;
    private RegionCityPopulator regionCityPopulator;
    private CustomizationListService customizationListService;
    private ContentDetailsService contentDetailsService;
    private ListItemPopulator listItemPopulator;
    private LicenseRenewalReversePopulator licenseRenewalReversePopulator;
    private ServiceReqReversePopulator serviceReqReversePopulator;
    private GlobalValsService globalValsService;

    @Autowired
    private TemporaryBiddingLicenseCountryPopulator temporaryBiddingLicenseCountryPopulator;

    @Autowired
    private TemporaryBiddingLicenseService temporaryBiddingLicenseService;

    @Autowired
    private TemporaryBiddingLicenseListItemService temporaryBiddingLicenseListItemService;


    @Autowired
    private ServicesRequestHDRPopulator servicesRequestHDRPopulator;


    @Autowired
    private ServiceRequestReversePopulator servicesRequestHDRReversePopulator;

    @Autowired
    private SagiaDraftFacade draftFacade;

    @Autowired
    private GovernmentDocumentsCheckPopulator governmentDocumentsCheckPopulator;

    @Autowired
    private RemovePopupALRPopulator removePopupALRPopulator;

    /**
     * @return
     */
    public ServiceRequestReversePopulator getServicesRequestHDRReversePopulator() {
        return servicesRequestHDRReversePopulator;
    }

    /**
     * @param servicesRequestHDRReversePopulator
     */
    public void setServicesRequestHDRReversePopulator(ServiceRequestReversePopulator servicesRequestHDRReversePopulator) {
        this.servicesRequestHDRReversePopulator = servicesRequestHDRReversePopulator;
    }

    /**
     * @return
     */
    public ZUI5SagiaFacade getZui5SagiaFacade() {
        return zui5SagiaFacade;
    }

    /**
     * @param zui5SagiaFacade
     */
    public void setZui5SagiaFacade(ZUI5SagiaFacade zui5SagiaFacade) {
        this.zui5SagiaFacade = zui5SagiaFacade;
    }

    /**
     * @return
     */
    public ServicesRequestHDRPopulator getServicesRequestHDRPopulator() {
        return servicesRequestHDRPopulator;
    }

    /**
     * @param servicesRequestHDRPopulator
     */
    public void setServicesRequestHDRPopulator(ServicesRequestHDRPopulator servicesRequestHDRPopulator) {
        this.servicesRequestHDRPopulator = servicesRequestHDRPopulator;
    }

    /**
     * @param contentDetailsService
     */
    public void setContentDetailsService(ContentDetailsService contentDetailsService) {
        this.contentDetailsService = contentDetailsService;
    }

    /**
     * @return
     */
    public RegionCityPopulator getRegionCityPopulator() {
        return regionCityPopulator;
    }

    /**
     * @param regionCityPopulator
     */
    public void setRegionCityPopulator(RegionCityPopulator regionCityPopulator) {
        this.regionCityPopulator = regionCityPopulator;
    }

    /**
     * @param customizationListService
     */
    public void setCustomizationListService(CustomizationListService customizationListService) {
        this.customizationListService = customizationListService;
    }

    /**
     * @param listItemPopulator
     */
    public void setListItemPopulator(ListItemPopulator listItemPopulator) {
        this.listItemPopulator = listItemPopulator;
    }

    /**
     * @param serviceReqReversePopulator
     */
    public void setServiceReqReversePopulator(ServiceReqReversePopulator serviceReqReversePopulator) {
        this.serviceReqReversePopulator = serviceReqReversePopulator;
    }

    /**
     * @param id
     * @return
     * @throws ODataException
     */
    @Override
    public ServiceRequestHDR getServiceRequestHDR(String id) {
        ServiceRequestHDRsData serviceRequestHDRsData = zui5SagiaFacade.getServiceRequestData(id);
        ServiceRequestHDR serviceRequestHDR = new ServiceRequestHDR();
        servicesRequestHDRPopulator.populate(serviceRequestHDRsData, serviceRequestHDR);
        return serviceRequestHDR;
    }

    @Override
    public ServiceRequestHDR getServiceRequestHDR() {
        ServiceRequestHDRsData serviceRequestHDRsData = zui5SagiaFacade.getServiceRequestData();
        ServiceRequestHDR serviceRequestHDR = new ServiceRequestHDR();
        servicesRequestHDRPopulator.populate(serviceRequestHDRsData, serviceRequestHDR);
        return serviceRequestHDR;
    }

    @Override
    public ServiceRequestHDR getLatestServiceRequestHDR() {

        ServiceRequestHDRsData serviceRequestHDRsData = zui5SagiaFacade.getLatestServiceRequestData();
        ServiceRequestHDR serviceRequestHDR = new ServiceRequestHDR();
        servicesRequestHDRPopulator.populate(serviceRequestHDRsData, serviceRequestHDR);
        return serviceRequestHDR;
    }

    @Override
    public Collection<ServiceRequestHDR> getServicesRequestHDR() {
        Collection<ServiceRequestHDRsData> serviceRequestHDRsData = zui5SagiaFacade.getServicesRequestHDRServiceData();
        Collection<ServiceRequestHDR> result = new ArrayList<>();
        for (ServiceRequestHDRsData serviceRequestHDRItem : serviceRequestHDRsData) {
            ServiceRequestHDR serviceRequestHDR = new ServiceRequestHDR();
            servicesRequestHDRPopulator.populate(serviceRequestHDRItem, serviceRequestHDR);
            result.add(serviceRequestHDR);
        }
        return result;
    }

    @Override
    public List<TemporaryBiddingLicenseCountry> getCountries(String language) {
        List<TemporaryBiddingLicenseCountry> countries = new ArrayList<>();
        Collection<TemporaryBiddingLicenseListItemData> listItems = temporaryBiddingLicenseListItemService.getListItems("C", language);
        for (TemporaryBiddingLicenseListItemData listItem : listItems) {
            TemporaryBiddingLicenseCountry country = new TemporaryBiddingLicenseCountry();
            temporaryBiddingLicenseCountryPopulator.populate(listItem, country);
            countries.add(country);
        }
        return countries;
    }

    /**
     * retrieves File
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    public InputStream getFile(String objectId, String documentId) {
        return contentDetailsService.readAttachmentBy(objectId, documentId);
    }

    /**
     * @return
     */
    public Collection<ListItem> getLicenseRenewalSupportingAttachments() {
        List<ListItem> attachments = new ArrayList<>();
        Collection<CustomizingGetData> attachmentsData = customizationListService.readLicenseRenewalSupportingAttachments();
        for (CustomizingGetData item :
                attachmentsData) {
            ListItem attachment = new ListItem();
            listItemPopulator.populate(item, attachment);
            attachments.add(attachment);
        }
        return attachments;
    }

    /**
     * retrieves service info
     * @param scenario scenario
     * @return Collection of ListItem
     */
    public Collection<ListItem> getServiceInfo(String scenario) {
        List<ListItem> serviceInfo = new ArrayList<>();
        Collection<CustomizingGetData> serviceInfoCollection = customizationListService.getServiceInfo(scenario);
        for (CustomizingGetData item :
                serviceInfoCollection) {
            ListItem info = new ListItem();
            listItemPopulator.populate(item, info);
            serviceInfo.add(info);
        }
        return serviceInfo;
    }

    @Override
    public CustomizationListService getCustomizationListService() {
        return customizationListService;
    }

    @Override
    public String renewServiceRequest(final LicenseRenewalForm licenseRenewalForm) {
        ServiceRequestHDR serviceRequestHDR = new ServiceRequestHDR();
        licenseRenewalReversePopulator.populate(licenseRenewalForm, serviceRequestHDR);

        ServiceReqData serviceReqData = new ServiceReqData();
        serviceReqReversePopulator.populate(serviceRequestHDR, serviceReqData);

        return zui5SagiaFacade.sendServicesReqHDRService(serviceReqData);
    }

    @Override
    public String renewServiceRequest(final ServiceRequestHDR serviceRequestHDR) {
        ServiceReqData serviceReqData = new ServiceReqData();
        serviceReqReversePopulator.populate(serviceRequestHDR, serviceReqData);
        return zui5SagiaFacade.sendServicesReqHDRService(serviceReqData);
    }

    /**
     * checks LicenseRenewalAvailability
     */
    public void checkLicenseRenewalAvailability() {
        globalValsService.checkLicenseRenewalAvailability();
    }

    /**
     * Check government documents for License Renewal
     * @return - Government Documents Check response DTO
     */
    @Override
    public GovernmentDocumentsCheck checkGovernmentDocuments() {
        GovtDocCheck govtDocCheck = zui5SagiaFacade.checkGovernmentDocuments();
        GovernmentDocumentsCheck governmentDocumentsCheck = new GovernmentDocumentsCheck();
        governmentDocumentsCheckPopulator.populate(govtDocCheck,governmentDocumentsCheck);
        return governmentDocumentsCheck;
    }

    @Override
    public RemovePopupALRResponse removePopupALR() {
        RemovePopupALR removePopupALR =zui5SagiaFacade.removePopupALR();
        RemovePopupALRResponse removePopupALRResponse = new RemovePopupALRResponse();
        removePopupALRPopulator.populate(removePopupALR,removePopupALRResponse);
        return removePopupALRResponse;
    }

    /**
     * @return
     */
    public LicenseRenewalReversePopulator getLicenseRenewalReversePopulator()
    {
        return licenseRenewalReversePopulator;
    }

    /**
     * @param licenseRenewalReversePopulator
     */
    public void setLicenseRenewalReversePopulator(final LicenseRenewalReversePopulator licenseRenewalReversePopulator)
    {
        this.licenseRenewalReversePopulator = licenseRenewalReversePopulator;
    }

    public void setGlobalValsService(GlobalValsService globalValsService) {
        this.globalValsService = globalValsService;
    }

    public void setGovernmentDocumentsCheckPopulator(GovernmentDocumentsCheckPopulator governmentDocumentsCheckPopulator) {
        this.governmentDocumentsCheckPopulator = governmentDocumentsCheckPopulator;
    }

    public void setRemovePopupALRPopulator(RemovePopupALRPopulator removePopupALRPopulator) {
        this.removePopupALRPopulator = removePopupALRPopulator;
    }

	
}
