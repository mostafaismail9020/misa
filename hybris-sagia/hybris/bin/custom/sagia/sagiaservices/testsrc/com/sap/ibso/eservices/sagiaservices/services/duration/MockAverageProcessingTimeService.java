package com.sap.ibso.eservices.sagiaservices.services.duration;

import com.sap.ibso.eservices.sagiaservices.duration.AverageProcessingTimeService;
import com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData;

public class MockAverageProcessingTimeService implements AverageProcessingTimeService {
    @Override
    public AverageProcessingTimeData getAverageProcessingTimeData(String serviceType) {
        AverageProcessingTimeData averageProcessingTimeData = new AverageProcessingTimeData();

        averageProcessingTimeData.setDays(12);
        averageProcessingTimeData.setHours(3);
        averageProcessingTimeData.setMinutes(26);
        averageProcessingTimeData.setSeconds(0);
        return averageProcessingTimeData;
    }
}
