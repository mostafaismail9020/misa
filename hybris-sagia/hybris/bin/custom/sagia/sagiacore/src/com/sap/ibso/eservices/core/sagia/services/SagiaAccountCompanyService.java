package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.facades.data.ProfileCompanyData;

/**
 * Provides access to the Account Company Service
 * Created by i335541 on 2/12/18.
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaAccountCompanyService {

    /**
     * Get a company by its code.
     *
     * @param code - Code of the company.
     * @return - The company with that specific code.
     */
    ProfileCompanyData getCompanyForCode(String code);
}
