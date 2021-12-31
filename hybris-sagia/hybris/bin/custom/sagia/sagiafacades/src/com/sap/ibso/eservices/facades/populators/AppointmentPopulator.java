package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class AppointmentPopulator implements Populator<AppointmentData, Appointment> {

    private SagiaFormatProvider sagiaFormatProvider;
    private UserService         userService;

    @Override
    public void populate(AppointmentData appointmentData, Appointment appointment) throws ConversionException {
        appointment.setContactPerson(appointmentData.getContactperson());
        appointment.setContactPerson(appointmentData.getContactperson());
        appointment.setAppointmentID(appointmentData.getApptID());
        appointment.setStatus(appointmentData.getApptStatus());
        appointment.setStatusDescription(appointmentData.getApptStatDesc());
        appointment.setDepartment(appointmentData.getDepartment());
        appointment.setDepartmentDescription(appointmentData.getDeptDesc());
        appointment.setBranch(appointmentData.getBranch());
        appointment.setBranchDescription(appointmentData.getBranchDesc());
        appointment.setDate(appointmentData.getDateAppt());
        appointment.setDateData(sagiaFormatProvider.getLocalizedDateData(appointmentData.getDateAppt()));
        appointment.setDateString(appointment.getDateData().getDateFormatted());
        appointment.setTimeTo(appointmentData.getTimeTo());
        appointment.setTimeStart(appointmentData.getTimeFrom());
        appointment.setBpID(appointmentData.getBpID());
        appointment.setServiceType1(appointmentData.getServType1());
        appointment.setServiceType1Description(appointmentData.getServType1Desc());
        appointment.setServiceType2(appointmentData.getServType2());
        appointment.setServiceType2Description(appointmentData.getServType2Desc());
        appointment.setServiceType3(appointmentData.getServType3());
        appointment.setServiceType3Description(appointmentData.getServType3Desc());
        appointment.setMinistry1(appointmentData.getMinistry1());
        appointment.setMinistry1Description(appointmentData.getMinistry1Desc());
        appointment.setMinistry2(appointmentData.getMinistry2());
        appointment.setMinistry2Description(appointmentData.getMinistry2Desc());
        appointment.setMinistry3(appointmentData.getMinistry3());
        appointment.setMinistry3Description(appointmentData.getMinistry3Desc());
        appointment.setService1(appointmentData.getService1());
        appointment.setService1Description(appointmentData.getService1Desc());
        appointment.setService2(appointmentData.getService2());
        appointment.setService2Description(appointmentData.getService2Desc());
        appointment.setService3(appointmentData.getService3());
        appointment.setService3Description(appointmentData.getService3Desc());
        appointment.setPrint(appointmentData.getApptPrint());
        appointment.setAction(appointmentData.getAction());
        
        appointment.setNotes(appointmentData.getNotes());

        if (userService.getCurrentUser() != null) {
            appointment.setUserEmail(((CustomerModel) userService.getCurrentUser()).getUserNameEmail());
        }
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    /**
     * Set User service.
     * @param userService the userService
     */
    public void setUserService(final UserService userService)    {
        this.userService = userService;
    }
}
