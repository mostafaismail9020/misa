package com.sap.ibso.eservices.bol.duration;

import com.sap.ibso.eservices.bol.duration.data.AverageProcessingTimeBackendData;

/**
 * Provides synchronous access to e-services related average processing time information in SAP backend system.
 */
public interface AverageProcessingTimeBackendService
{
    /**
     * Gets the average processing time for an e-service. The e-service in focus is identified by service type.
     *
     * @param serviceType the service type
     * @return the average processing time backend data
     */
    AverageProcessingTimeBackendData getAverageProcessingTimeData(String serviceType);
}
