package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.CalendarSlot;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class CalendarSlotReversePopulator implements Populator<CalendarSlot, CalendarSlotData> {
    @Override
    public void populate(CalendarSlot calendarSlot, CalendarSlotData calendarSlotData) throws ConversionException {
        calendarSlotData.setApptId(calendarSlot.getId());
        calendarSlotData.setApptDate(calendarSlot.getDate());
        calendarSlotData.setApptDatep(calendarSlot.getDateP());
        calendarSlotData.setApptTimeStart(calendarSlot.getDateTimeStart());
        calendarSlotData.setApptTimeTo(calendarSlot.getDateTimeEnd());
        calendarSlotData.setApptType(calendarSlot.getType1());
        calendarSlotData.setMinistry1(calendarSlot.getMinistry1());
        calendarSlotData.setApptType1(calendarSlot.getType2());
        calendarSlotData.setMinistry2(calendarSlot.getMinistry2());
        calendarSlotData.setApptType2(calendarSlot.getType3());
        calendarSlotData.setMinistry3(calendarSlot.getMinistry3());
        calendarSlotData.setBranch(calendarSlot.getBranch());
        calendarSlotData.setTimeDuration(calendarSlot.getTimeDuration());
        calendarSlotData.setApptBooked(calendarSlot.getBooked());
        calendarSlotData.setApptAvailable(calendarSlot.getAvailable());
    }
}
