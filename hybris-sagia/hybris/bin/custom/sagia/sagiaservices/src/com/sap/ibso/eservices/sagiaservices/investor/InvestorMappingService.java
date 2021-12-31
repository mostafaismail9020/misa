package com.sap.ibso.eservices.sagiaservices.investor;

import com.sap.ibso.eservices.sagiaservices.investor.exception.MissingApplicantReferenceIdentifierException;
import com.sap.ibso.eservices.sagiaservices.investor.exception.MissingEntityIdentifierException;

import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Provides access to identifiers for investor related data mapping (in SAP backend system).
 */
public interface InvestorMappingService
{
    /**
     * Gets the applicant reference identifier associated with the current user.
     *
     * @return the applicant reference identifier
     * @throws MissingApplicantReferenceIdentifierException if the current user has no associated applicant reference identifier
     */
    String getApplicantReferenceId(CustomerModel customer);

    /**
     * Gets the entity identifier associated with the current user.
     *
     * @return the entity identifier
     * @throws MissingEntityIdentifierException if the current user has no associated entity identifier
     */
    String getEntityId();
}
