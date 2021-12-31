package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * CalendarSlotService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class CalendarSlotService extends AbstractSagiaService<CalendarSlotData> {


    /**
     * Loads the timeslots given a calendar slot object
     * @param calendarSlotData calendarSlotData
     * @return Collection of CalendarSlotData
     */
    public final Collection<CalendarSlotData> getCollection(CalendarSlotData calendarSlotData) {
        return super.getCollection(
                CalendarSlotData.class,
                REQUEST_PARAMETER_FILTER,
                "Branch eq '"+calendarSlotData.getBranch()+"' " +
                        "and ApptDate eq datetime'"+calendarSlotData.getApptDate()+"' " +
                        "and ApptType  eq '"+calendarSlotData.getApptType() +"' " +
                        "and Ministry1 eq '"+calendarSlotData.getMinistry1()+"' " +
                        "and ApptType1 eq '"+calendarSlotData.getApptType1()+"' " +
                        "and Ministry2 eq '"+calendarSlotData.getMinistry2()+"' " +
                        "and ApptType2 eq '"+calendarSlotData.getApptType2()+"' " +
                        "and Ministry3 eq '"+calendarSlotData.getMinistry3()+"' ");

    }
}
