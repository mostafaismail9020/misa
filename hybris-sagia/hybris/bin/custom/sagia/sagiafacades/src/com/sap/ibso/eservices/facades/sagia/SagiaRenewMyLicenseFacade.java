package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.LicenseDuration;
import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.facades.data.ServiceRequestHDR;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseCountry;
import com.sap.ibso.eservices.facades.data.account.RemovePopupALRResponse;
import com.sap.ibso.eservices.facades.form.LicenseRenewalForm;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovernmentDocumentsCheck;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * SagiaRenewMyLicenseFacade
 */
public interface SagiaRenewMyLicenseFacade {

    /**
     * Retrieves ServiceRequestHDR
     * @param id id
     * @return ServiceRequestHDR
     * @throws IOException exception
     */
    ServiceRequestHDR getServiceRequestHDR(String id);

    ServiceRequestHDR getServiceRequestHDR();

    /**
     * Retrieves Latest ServiceRequestHDR
     * @return ServiceRequestHDR
     */
    ServiceRequestHDR getLatestServiceRequestHDR();

    /**
     * Retrieves ServicesRequestHDR
     * @return Collection of ServiceRequestHDR
     * @throws IOException exception
     */
    Collection<ServiceRequestHDR> getServicesRequestHDR();

    /**
     * Retrieves Countries List
     * @param language language
     * @return List of TemporaryBiddingLicenseCountry
     */
    List<TemporaryBiddingLicenseCountry> getCountries(String language);

    /**
     * Retrieves File
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    InputStream getFile(String objectId, String documentId);

    /**
     * Retrieves LicenseRenewalSupportingAttachments
     * @return Collection of ListItem
     */
    Collection<ListItem> getLicenseRenewalSupportingAttachments();

    /**
     * Gets service information
     * @param scenario scenario
     * @return Collection of ListItem
     */
    Collection<ListItem> getServiceInfo(String scenario);

    /**
     * Retrieves CustomizationListService
     * @return CustomizationListService
     */
    CustomizationListService getCustomizationListService();

    /**
     * renews ServiceRequest
     * @param licenseRenewalForm licenseRenewalForm
     * @return String
     */
    String renewServiceRequest(LicenseRenewalForm licenseRenewalForm);


    /**
     * renews ServiceRequest
     * @param serviceRequestHDR serviceRequestHDR
     * @return String
     */
    String renewServiceRequest(final ServiceRequestHDR serviceRequestHDR);

    /**
     * Checks whether the renewal service is active
     */
    void checkLicenseRenewalAvailability();

    /**
     * Check government documents for License Renewal
     * @return - Government Documents Check response DTO
     */
    GovernmentDocumentsCheck checkGovernmentDocuments();

    /**
     * Removes the License renewal popup from dashboard
     * @return Response object DTO
     */
    RemovePopupALRResponse removePopupALR();


}
