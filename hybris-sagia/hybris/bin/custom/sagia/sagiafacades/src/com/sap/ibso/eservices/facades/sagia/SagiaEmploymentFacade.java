package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.employment.data.EmploymentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;

import java.io.IOException;

/**
 * SagiaEmploymentFacade
 */
public interface SagiaEmploymentFacade {
    /**
     * Splits an employment DTO from the whole information of dashboard contained in a HomeHDRData object
     *
     * @param homeHDRData - Whole dashboard data
     * @return - Employment DTO for the dashboard
     * @throws IOException exception
     */
    EmploymentData getEmploymentData(HomeHDRData homeHDRData);
}
