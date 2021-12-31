package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.time.LocalDate;
import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * ApptAvailSet
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ApptAvailSet extends AbstractSagiaService<CalendarSlotData> {
    /**
     * Gets the collection of available time slots from the CRM given a date and a selected branch.
     * This is made for the National Investor registration and the Appointment type (ApptType) is SAGIASER
     * @param selectedDate selectedDate
     * @return Collection of CalendarSlotData
     */
    public final Collection<CalendarSlotData> getCollection(LocalDate selectedDate, String branch){
        String date = selectedDate.getYear() + "-" + selectedDate.getMonthValue() + "-" + selectedDate.getDayOfMonth();
        return super.getCollection(CalendarSlotData.class,
                REQUEST_PARAMETER_FILTER,
                "Branch eq '" + branch + "' and ApptDate eq datetime'" + date + "T00:00:00' and ApptType eq 'SAGIASER'");
    }
}
