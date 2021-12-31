package com.sap.ibso.eservices.sagiaservices.duration.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.duration.AverageProcessingTimeBackendService;
import com.sap.ibso.eservices.bol.duration.data.AverageProcessingTimeBackendData;
import com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData;
import com.sap.ibso.eservices.sagiaservices.duration.AverageProcessingTimeService;
import org.springframework.util.Assert;

/**
 * Implements access to e-services related average processing time information.
 */
public class DefaultAverageProcessingTimeService extends BackendAwareService implements AverageProcessingTimeService
{
    /**
     * Creates the default average processing time service.
     *
     * @param averageProcessingTimeBackendServiceBeanName the bean name (or alias) of the average processing time backend service
     */
    public DefaultAverageProcessingTimeService(String averageProcessingTimeBackendServiceBeanName)
    {
        super(averageProcessingTimeBackendServiceBeanName);
    }

    @Override
    public AverageProcessingTimeData getAverageProcessingTimeData(String serviceType)
    {
        // Basic parameter checks
        Assert.notNull(serviceType, "Service type must not be null.");

        // Backend service access
        AverageProcessingTimeBackendService backendService = getBackendService();
        // Retrieve the average processing time data in the corresponding backend system
        AverageProcessingTimeBackendData backendData = backendService.getAverageProcessingTimeData(serviceType);

        return convert(backendData, serviceType);
    }

    /**
     * Converts a {@link AverageProcessingTimeBackendData} instance into a {@link AverageProcessingTimeData} instance.
     *
     * @param backendData the average processing time backend data
     * @return the average processing time data
     */
    private AverageProcessingTimeData convert(AverageProcessingTimeBackendData backendData, String serviceType)
    {
        AverageProcessingTimeData data = new AverageProcessingTimeData();

        data.setServiceType(serviceType);
        data.setDays(backendData.getDays());
        data.setHours(backendData.getHours());
        data.setMinutes(backendData.getMinutes());
        data.setSeconds(backendData.getSeconds());

        return data;
    }
}
