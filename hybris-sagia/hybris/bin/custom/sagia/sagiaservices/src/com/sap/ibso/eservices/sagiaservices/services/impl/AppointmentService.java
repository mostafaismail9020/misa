package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.io.InputStream;
import java.util.Collection;

/**
 * AppointmentService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class AppointmentService extends AbstractSagiaService<AppointmentData> {
    AppointmentPdfService appointmentPdfService;
    /**
     * retrieves Collection of AppointmentData
     * @return Collection of AppointmentData
     */
    public final Collection<AppointmentData> getCollection() {
        return super.getCollection(AppointmentData.class);
    }

    /**
     * retrieves AppointmentData by id
     * @param id id
     * @return AppointmentData
     */
    public final AppointmentData get(Integer id){
        return super.get(AppointmentData.class, id);
    }

    /**
     * creates AppointmentData
     * @param appointmentData appointmentData
     */
    public final void create(AppointmentData appointmentData){
        super.save(appointmentData);
    }

    /**
     * saves Appointment
     * @param appointmentData appointmentData
     * @return AppointmentData
     */
    public final AppointmentData saveAppointment(AppointmentData appointmentData){
        return super.saveAndParseResponse(appointmentData, AppointmentData.class);
    }

    /**
     * saves Appointment
     * @param appointmentData appointmentData
     * @param id id
     * @return AppointmentData
     */
    public final AppointmentData saveAppointment(AppointmentData appointmentData, Integer id){
        return super.saveAndParseResponse(appointmentData, id, AppointmentData.class);
    }

    /**
     * prints AppointmentDetails
     * @param id id
     * @return InputStream
     */
    public InputStream printAppointmentDetails(String id){
        return appointmentPdfService.readAttachmentBy(id);
    }

    public void setAppointmentPdfService(AppointmentPdfService appointmentPdfService) {
        this.appointmentPdfService = appointmentPdfService;
    }
}
