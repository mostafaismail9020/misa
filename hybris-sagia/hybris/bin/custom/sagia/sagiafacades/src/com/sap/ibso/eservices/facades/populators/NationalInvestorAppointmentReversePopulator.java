package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NationalInvestorAppointmentReversePopulator implements Populator<NationalInvestorAppointment, NationalInvestorAppointmentData> {
    private SagiaFormatProvider sagiaFormatProvider;
    @Override
    public void populate(NationalInvestorAppointment appointment, NationalInvestorAppointmentData appointmentData) throws ConversionException {
        appointmentData.setBranch(appointment.getBranch());
        appointmentData.setServiceType1(appointment.getServiceType1());
        appointmentData.setService1(appointment.getService1());
        appointmentData.setEmail(appointment.getEmail());
        appointmentData.setIDNumber(appointment.getInvestorId());
        appointmentData.setApptPrint(appointment.getAppointmentPrint());
        appointmentData.setDateAppt(appointment.getDate());
        appointmentData.setDateAppt(sagiaFormatProvider.getDateFromUIDateStringOrNull(appointment.getDateString()));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        appointmentData.setTimeFrom(LocalTime.parse(appointment.getTimeFromString(), timeFormatter));
        appointmentData.setTimeTo(appointmentData.getTimeFrom().plus(15, ChronoUnit.MINUTES));

        appointmentData.setApptID(appointment.getAppointmentID());
        appointmentData.setApptStatus(appointment.getStatus());
        appointmentData.setApptStatDesc(appointment.getStatusDescription());
        appointmentData.setDepartment(appointment.getDepartment());
        appointmentData.setDeptDesc(appointment.getDepartmentDescription());
        appointmentData.setBpID(appointment.getBpID());
        appointmentData.setAction(appointment.getAction());
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
