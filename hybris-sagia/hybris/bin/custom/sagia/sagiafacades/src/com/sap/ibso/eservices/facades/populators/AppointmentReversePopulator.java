package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class AppointmentReversePopulator implements Populator<Appointment, AppointmentData> {

    private SessionService sessionService;
    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(Appointment appointment, AppointmentData appointmentData) throws ConversionException {
        appointmentData.setContactperson(appointment.getContactPerson());
        appointmentData.setContactperson(appointment.getContactPerson());
        appointmentData.setApptID(appointment.getAppointmentID());
        appointmentData.setApptStatus(appointment.getStatus());
        appointmentData.setApptStatDesc(appointment.getStatusDescription());
        appointmentData.setDepartment(appointment.getDepartment());
        appointmentData.setDeptDesc(appointment.getDepartmentDescription());
        appointmentData.setBranch(appointment.getBranch());

        appointmentData.setDateAppt(sagiaFormatProvider.getDateTimeFromUIDateString(appointment.getDateString()));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        appointmentData.setTimeFrom(LocalTime.parse(appointment.getTimeStartString(), timeFormatter));
        appointmentData.setTimeTo(appointmentData.getTimeFrom().plus(15, ChronoUnit.MINUTES));
        appointmentData.setBpID(appointment.getBpID());
        appointmentData.setServType1(appointment.getServiceType1());
        appointmentData.setServType1Desc(appointment.getServiceType1Description());
        appointmentData.setServType2(appointment.getServiceType2());
        appointmentData.setServType2Desc(appointment.getServiceType2Description());
        appointmentData.setServType3(appointment.getServiceType3());
        appointmentData.setServType3Desc(appointment.getServiceType3Description());
        appointmentData.setMinistry1(appointment.getMinistry1());
        appointmentData.setMinistry1Desc(appointment.getMinistry1Description());
        appointmentData.setMinistry2(appointment.getMinistry2());
        appointmentData.setMinistry2Desc(appointment.getMinistry2Description());
        appointmentData.setMinistry3(appointment.getMinistry3());
        appointmentData.setMinistry3Desc(appointment.getMinistry3Description());
        appointmentData.setService1(appointment.getService1());
        appointmentData.setService1Desc(appointment.getService1Description());
        appointmentData.setService2(appointment.getService2());
        appointmentData.setService2Desc(appointment.getService2Description());
        appointmentData.setService3(appointment.getService3());
        appointmentData.setService3Desc(appointment.getService3Description());
        appointmentData.setApptPrint(appointment.getPrint());
        appointmentData.setAction(appointment.getAction());        
        
        appointmentData.setNotes(appointment.getNotes());

        Collection<ListItem> customizingModelList = sessionService.getCurrentSession().getAttribute("appointmentBranches");
        if(customizingModelList != null){
            appointmentData.setBranchDesc(customizingModelList.stream().filter(p -> p.getFieldKey().equals(appointmentData.getBranch())).findFirst().get().getDescription());
        }
    }


    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
