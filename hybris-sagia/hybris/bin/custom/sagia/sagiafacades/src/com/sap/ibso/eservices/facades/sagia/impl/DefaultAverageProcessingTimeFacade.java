package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.sagiaservices.data.duration.AverageProcessingTimeData;
import com.sap.ibso.eservices.sagiaservices.duration.AverageProcessingTimeService;
import com.sap.ibso.eservices.core.sagia.services.SagiaBETransTypeService;
import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultAverageProcessingTimeFacade
 */
public class DefaultAverageProcessingTimeFacade implements AverageProcessingTimeFacade{
    private static final Logger LOG = LoggerFactory.getLogger(DefaultAverageProcessingTimeFacade.class);

    private AverageProcessingTimeService averageProcessingTimeService;
    private SagiaBETransTypeService sagiaBETransTypeService;

    /**
     * retrieves AverageProcessingTimeService
     * @return AverageProcessingTimeService
     */
    public AverageProcessingTimeService getAverageProcessingTimeService() {
        return averageProcessingTimeService;
    }

    /**
     * sets AverageProcessingTimeService
     * @param averageProcessingTimeService averageProcessingTimeService
     */
    public void setAverageProcessingTimeService(AverageProcessingTimeService averageProcessingTimeService) {
        this.averageProcessingTimeService = averageProcessingTimeService;
    }

    /**
     * retrieves SagiaBETransTypeService
     * @return SagiaBETransTypeService
     */
    public SagiaBETransTypeService getSagiaBETransTypeService() {
        return sagiaBETransTypeService;
    }

    /**
     * sets SagiaBETransTypeService
     * @param sagiaBETransTypeService sagiaBETransTypeService
     */
    public void setSagiaBETransTypeService(SagiaBETransTypeService sagiaBETransTypeService) {
        this.sagiaBETransTypeService = sagiaBETransTypeService;
    }

    /**
     * retrieves AverageProcessingTimeData
     * @param entityName entityName
     * @return AverageProcessingTimeData
     */
    public AverageProcessingTimeData getAverageProcessingTimeData(String entityName) {
        try {
            final String transType = getSagiaBETransTypeService().getTransTypeForCode(entityName);
            if (transType != null) {
                AverageProcessingTimeData averageProcessingTimeData = getAverageProcessingTimeService().getAverageProcessingTimeData(transType);
                if (averageProcessingTimeData.getDays() > 0 || averageProcessingTimeData.getHours() > 0 || averageProcessingTimeData.getMinutes() > 0 || averageProcessingTimeData.getSeconds() > 0) {
                    return averageProcessingTimeData;
                }

            }
        } catch (Exception e){
            LOG.error(e.getMessage(), e);
            return null;
        }
        return null;
    }


}
