package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class NationalInvestorAppointmentPopulator implements Populator<NationalInvestorAppointmentData, NationalInvestorAppointment> {
    @Override
    public void populate(NationalInvestorAppointmentData appointmentData, NationalInvestorAppointment appointment) throws ConversionException {
        appointment.setBranch(appointmentData.getBranch());
        appointment.setServiceType1(appointmentData.getServiceType1());
        appointment.setService1(appointmentData.getService1());
        appointment.setEmail(appointmentData.getEmail());
        appointment.setInvestorId(appointmentData.getIDNumber());
        appointment.setAppointmentPrint(appointmentData.getApptPrint());
        appointment.setDate(appointmentData.getDateAppt());
        appointment.setTimeFrom(appointmentData.getTimeFrom());
        appointmentData.setTimeTo(appointmentData.getTimeTo());
        appointment.setAppointmentID(appointmentData.getApptID());
        appointment.setStatus(appointmentData.getApptStatus());
        appointment.setStatusDescription(appointmentData.getApptStatDesc());
        appointment.setDepartment(appointmentData.getDepartment());
        appointment.setDepartmentDescription(appointmentData.getDeptDesc());
        appointment.setBpID(appointmentData.getBpID());
        appointment.setAction(appointmentData.getAction());
    }
}
