package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.TimeSlot;


/**
 * Provides access to RealTimeScheduleFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface RealTimeScheduleFacade {
    /**
     * Creates a new Schedule call
     *
     * @param timeSlot timeSlot
     * @return String
     */
    String saveScheduleCall(TimeSlot timeSlot);
}
