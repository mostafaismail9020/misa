package com.sap.ibso.eservices.sagiaservices.duration;

import com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData;

/**
 * Provides access to e-services related average processing time information.
 */
public interface AverageProcessingTimeService
{
    /**
     * Gets the average processing time for an e-service. The e-service in focus is identified by service type.
     * <p>
     * The following basic parameter check is performed: service type must not be null.
     * </p>
     *
     * @param serviceType the service type
     * @return the average processing time
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    AverageProcessingTimeData getAverageProcessingTimeData(String serviceType);
}
