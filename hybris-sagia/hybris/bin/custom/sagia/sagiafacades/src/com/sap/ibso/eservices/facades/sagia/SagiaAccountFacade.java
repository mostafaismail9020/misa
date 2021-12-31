package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateForm;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateHistory;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;

import java.io.InputStream;
import java.util.Collection;

/**
 * Provides access to SagiaAccountFacade
 * Created by i335541 on 2/12/18.
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaAccountFacade {
    /**
     *
     * Retrieves ContactData For ProfileCode
     * @return ContactData
     */
    ContactData getCompanyContacts();

    /**
     * Retrieves ProfileCompanyData
     * @return BasicCompanyData
     */
    BasicCompanyData getProfileCompanyData();

    /**
     * Get the current primary contact.
     * If none is found, it returns the general manager.
     * @return Company's primary contact.
     */
    ProfileCompanyRepresentativeData getPrimaryContact();

    /**
     * retrieves SurveyHDRById
     * @param id id
     * @return SurveyData
     */
    SurveyData getSurveyHDRById(String id);

    /**
     * updates company contacts
     * @param contactUpdateForm - The list of  contacts to be updated
     */
    void updateContacts(ContactUpdateForm contactUpdateForm);

    /**
     * Checks if the contact update request may be completed.
     */
    void checkContactUpdateAvailability();

    /**
     * reads Attachment
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    InputStream readAttachment(String objectId, String documentId);

    /**
     * Gets all the history of the contact updates.
     * @return - Collection of contact updates history
     */
    Collection<ContactUpdateHistory> getAllContactUpdateHistory();

    /**
     * Return a contact update history entry DTO
     * @param id - The id for which the history entry DTO is retrieved.
     * @return - The history entry DTO with that specific id.
     */
    ContactUpdateHistory getContactUpdateHistoryEntry(String id);

    /**
     * Get the Renewal Indicators which are used to verify which license renewal is available,
     * if there is one. Also helps triggering the renewal popups.
     *
     * @param homeHDRData homeHDRData
     * @return Renewal Indicators
     */
    LicenseRenewalIndicators getRenewalIndicators(HomeHDRData homeHDRData);


    /**
     * Get the Mandatory Survey Indicators which verifies if there are mandatory and active surveys to be completed
     * @param homeHDRData homeHDRData
     * @return Mandatory Survey Indicators
     */
    MandatorySurveyIndicators getMandatorySurveyIndicators(HomeHDRData homeHDRData);

    /**
     * Create the map of the possible values of the renewal durations.
     * 
     * @param homeHDRData
     * @return
     */
	Integer getRenewalMaxDuration(String homeHDRData);
	
	Integer getRenewalLicenseFeePerYr(String homeHDRData);

	SagiaDateData getLicExDateData(HomeHDRData homeHDRData);

	Integer getSubsOutstandingFee(String homeHDRData);
}
