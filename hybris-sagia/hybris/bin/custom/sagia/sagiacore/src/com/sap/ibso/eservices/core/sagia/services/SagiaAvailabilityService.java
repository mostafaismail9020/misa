package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SagiaAvailabilityModel;
import com.sap.ibso.eservices.core.enums.AvailabilityTypeEnum;
import com.sap.ibso.eservices.core.enums.DayOfWeekEnum;
import java.util.List;

/**
 * Provides access to the Availability Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaAvailabilityService {

    /**
     * retrieves Availabilities
     * @return List of SagiaAvailabilityModel
     */
    List<SagiaAvailabilityModel> getAvailabilities();

    /**
     * retrieves TimeSlotAvailabilities
     * @return List
     */
    List<String> getTimeSlotAvailabilities();

    /**
     * retrieves AvailabilityForTypeAndDay
     * @param type type
     * @param day day
     * @return SagiaAvailabilityModel
     */
    SagiaAvailabilityModel getAvailabilityForTypeAndDay(AvailabilityTypeEnum type, DayOfWeekEnum day);

    /**
     * check if is Available
     * @param type type
     * @return boolean
     */
    boolean isAvailable(String type);
}
