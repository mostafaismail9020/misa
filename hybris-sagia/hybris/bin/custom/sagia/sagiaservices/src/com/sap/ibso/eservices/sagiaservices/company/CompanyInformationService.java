package com.sap.ibso.eservices.sagiaservices.company;

import com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData;

/**
 * Provides access to e-services related investor company data.
 */
public interface CompanyInformationService
{
    /**
     * Retrieves basic information about an investor company which is associated to the current user.
     *
     * @return the basic company data
     */
    BasicCompanyData getBasicInformation();
}
