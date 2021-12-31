package com.sap.ibso.eservices.sagiaservices.services.license.application;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;

import java.util.Optional;

/**
 * Provides access to ZQEEMAH service data.
 */
public interface ZQeemahService {
    /**
     * Retrieves the license application status for the currently logged in user (customer).
     * <p>
     * If no application status is found for the user (e.g. because no license application process
     * was started so far) then {@link Optional#empty()} is returned.
     *
     * @return the application status data
     */
    Optional<ApplicationStatusData> getApplicationStatus();

    /**
     * Creates an applicant reference identifier for the currently logged in user (customer) and stores it. A successfully created
     * and stored applicant reference identifier promotes the user as license applicant.
     *
     * @return {@code true} if a new applicant reference identifier was successfully created and stored, {@code false}
     * if the current user has already an applicant reference identifier assigned in which case there is no need to create a new one
     * @throws InvestorRegistrationException if the applicant reference identifier creation failed
     */
    boolean createApplicantReferenceId();

	void evictApplicationStatus();
}
