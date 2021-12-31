package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.CalendarSlot;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;


import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class CalendarSlotPopulator implements Populator<CalendarSlotData, CalendarSlot> {
    SagiaFormatProvider sagiaFormatProvider;
    @Override
    public void populate(CalendarSlotData calendarSlotData, CalendarSlot calendarSlot) throws ConversionException {
        calendarSlot.setId(calendarSlotData.getApptId());
        calendarSlot.setDate(calendarSlotData.getApptDate());
        calendarSlot.setDateData(sagiaFormatProvider.getLocalizedDateData(calendarSlotData.getApptDate()));
        calendarSlot.setDateP(calendarSlotData.getApptDatep());
        calendarSlot.setDateTimeStart(calendarSlotData.getApptTimeStart());
        calendarSlot.setDateTimeStartData(sagiaFormatProvider.getLocalizedTimeData(calendarSlotData.getApptTimeStart()));
        calendarSlot.setDateTimeEnd(calendarSlotData.getApptTimeTo());
        calendarSlot.setDateTimeEndData(sagiaFormatProvider.getLocalizedTimeData(calendarSlotData.getApptTimeTo()));
        calendarSlot.setType1(calendarSlotData.getApptType());
        calendarSlot.setMinistry1(calendarSlotData.getMinistry1());
        calendarSlot.setType2(calendarSlotData.getApptType1());
        calendarSlot.setMinistry2(calendarSlotData.getMinistry2());
        calendarSlot.setType3(calendarSlotData.getApptType2());
        calendarSlot.setMinistry3(calendarSlotData.getMinistry3());
        calendarSlot.setBranch(calendarSlotData.getBranch());
        calendarSlot.setTimeDuration(calendarSlotData.getTimeDuration());
        calendarSlot.setBooked(calendarSlotData.getApptBooked());
        calendarSlot.setAvailable(calendarSlotData.getApptAvailable());

    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
