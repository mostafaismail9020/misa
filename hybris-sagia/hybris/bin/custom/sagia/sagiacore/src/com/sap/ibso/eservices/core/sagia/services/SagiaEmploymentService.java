package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.facades.employment.data.EmploymentData;

/**
 * Provides access to the Employment Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaEmploymentService {

    /**
     * Get employment data.
     *
     * @return - An entity containing the employment data.
     */
    EmploymentData getEmploymentData();
}
