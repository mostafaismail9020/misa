package com.sap.ibso.eservices.facades.sagia.impl;
import com.sap.ibso.eservices.facades.sagia.RealTimeScheduleFacade;
import com.sap.ibso.eservices.facades.data.TimeSlot;
import com.sap.ibso.eservices.facades.populators.realTimeOnlineSupport.RealTimeScheduleReversePopulator;
import com.sap.ibso.eservices.soapservices.WebcallbackRequestData;
import com.sap.ibso.eservices.soapservices.service.SagiaSoapService;
import com.sap.ibso.eservices.soapservices.WebcallbackResponseData;


/**
 * DefaultRealTimeScheduleFacade
 */
public class DefaultRealTimeScheduleFacade implements RealTimeScheduleFacade{
    private RealTimeScheduleReversePopulator realTimeScheduleReversePopulator;

    /**
     * saves ScheduleCall
     * @param timeSlot timeSlot
     * @return String
     */
    public String saveScheduleCall(TimeSlot timeSlot) {
        WebcallbackRequestData requestData = new WebcallbackRequestData();
        realTimeScheduleReversePopulator.populate(timeSlot, requestData);
        final WebcallbackResponseData responseData = getSagiaSoapService().sendWebcallback(requestData);
        if (responseData != null &&  responseData.getResponse() != null && !responseData.getResponse().isEmpty()) {
            return responseData.getResponse();
        }
        else {
            return null;
        }
    }

    /**
     * @return RealTimeScheduleReversePopulator
     */
    public RealTimeScheduleReversePopulator getRealTimeScheduleReversePopulator() {
        return realTimeScheduleReversePopulator;
    }

    /**
     * @param realTimeScheduleReversePopulator realTimeScheduleReversePopulator
     */
    public void setRealTimeScheduleReversePopulator(RealTimeScheduleReversePopulator realTimeScheduleReversePopulator) {
        this.realTimeScheduleReversePopulator = realTimeScheduleReversePopulator;
    }

    public SagiaSoapService getSagiaSoapService() {
        return sagiaSoapService;
    }

    public void setSagiaSoapService(SagiaSoapService sagiaSoapService) {
        this.sagiaSoapService = sagiaSoapService;
    }

    private SagiaSoapService sagiaSoapService;

}
