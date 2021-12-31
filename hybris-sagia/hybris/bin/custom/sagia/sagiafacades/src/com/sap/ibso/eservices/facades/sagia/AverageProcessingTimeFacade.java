package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData;

/**
 * Provides access to AverageProcessingTimeFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface AverageProcessingTimeFacade {
    /**
     * gets AverageProcessingTimeData
     * @param entityName entityName
     * @return AverageProcessingTimeData
     */
    AverageProcessingTimeData getAverageProcessingTimeData(String entityName);
}
