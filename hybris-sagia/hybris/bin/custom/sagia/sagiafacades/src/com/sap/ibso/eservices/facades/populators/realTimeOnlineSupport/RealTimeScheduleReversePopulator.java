package com.sap.ibso.eservices.facades.populators.realTimeOnlineSupport;

import com.sap.ibso.eservices.facades.data.TimeSlot;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.soapservices.WebcallbackRequestData;
import de.hybris.platform.commercefacades.storesession.StoreSessionFacade;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.log4j.Logger;
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RealTimeScheduleReversePopulator implements Populator<TimeSlot, WebcallbackRequestData> {
	final private static Logger LOG = Logger.getLogger(RealTimeScheduleReversePopulator.class);
    private UserService userService;

    private SagiaConfigurationFacade sagiaConfigurationFacade;

    private StoreSessionFacade storeSessionFacade;
    final private static String AR_ISOCODE = "ar";
    final private static String PATTERN="uuuu-MM-dd HH:mm:ss";
    final private static String SECCONDS=":00";
    final private static String EMPTY = " ";
    final private static String PLUS = "+";
    final private static int BACK_HOURS = 3;

    @Override
    public void populate(TimeSlot source, WebcallbackRequestData target) throws ConversionException {
        final CustomerModel user = (CustomerModel) getUserService().getCurrentUser();
        final String mobilNumber = PLUS + source.getMobileCountryCode() + source.getMobileNumber() ;
        final String scheduleDatetime = source.getDateStart() + EMPTY + source.getTimeStart() + SECCONDS;

        target.setNumber(mobilNumber);
        target.setCallTime(getConvertedTime(scheduleDatetime));
        target.setQueue(getQueue());
        target.setNotes(user.getName());
		LOG.info("#########Webcallback request 1####### Number: "+mobilNumber+" **** CallTime: "+getConvertedTime(scheduleDatetime)+" ^^^^^^ Queue: "+getQueue()+" **** Notes: "+user.getName());

    }

    private String getQueue(){
        if(AR_ISOCODE.equals(storeSessionFacade.getCurrentLanguage().getIsocode())){
            return getSagiaConfigurationFacade().getWebcall_Ar_Queue();
        } else {
            return getSagiaConfigurationFacade().getWebcall_En_Queue();
        }

    }

    private  String getConvertedTime(String inputTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(inputTime, formatter)
                .minusHours(BACK_HOURS).format(formatter);
    }

    /**
     * @return UserService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StoreSessionFacade getStoreSessionFacade() {
        return storeSessionFacade;
    }

    public void setStoreSessionFacade(StoreSessionFacade storeSessionFacade) {
        this.storeSessionFacade = storeSessionFacade;
    }

    public SagiaConfigurationFacade getSagiaConfigurationFacade() {
        return sagiaConfigurationFacade;
    }

    public void setSagiaConfigurationFacade(SagiaConfigurationFacade sagiaConfigurationFacade) {
        this.sagiaConfigurationFacade = sagiaConfigurationFacade;
    }


}
