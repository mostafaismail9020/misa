package com.sap.ibso.eservices.bol.company;

import com.sap.ibso.eservices.bol.company.data.BasicCompanyBackendData;

/**
 * Provides synchronous access to e-services related investor company data in SAP backend system.
 */
public interface CompanyInformationBackendService
{
    /**
     * Retrieves basic information about an entity (i.e. an investor company). For information that are language-dependent,
     * a language ISO code determines the corresponding language.
     *
     * @param entityId the entity identifier
     * @param language the language ISO code
     * @return the basic company backend data
     */
    BasicCompanyBackendData getBasicInformation(String entityId, String language);
}
