package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.CalendarSlot;
import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.sagiaservices.appointment.AppointmentCalendarLabels;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Provides access to SagiaAppointmentFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaAppointmentFacade {
    /**
     * Loads the appointments from the CRM
     *
     * @return Collection of Appointment
     * @throws IOException
     */
    Collection<Appointment> getAppointments();

    /**
     * Loads all data used to fill all the dropdown controls for creating an appointment
     *
     * @return Collection of ListItem
     * @throws IOException
     */
    Collection<ListItem> getAppointmentOptions();

    /**
     * Filters data for the department dropdown
     *
     * @param customizingModelList customizingModelList
     * @return Collection of ListItem
     */
    Collection<ListItem> getDepartments(Collection<ListItem> customizingModelList);

    /**
     * Filters data for the branch dropdown
     *
     * @param customizingModelList customizingModelList
     * @return Collection of ListItem
     */
    Collection<ListItem> getBranches(Collection<ListItem> customizingModelList);

    /**
     * Loads the branches by making a call to the CRM
     *
     * @return Collection of ListItem
     * @throws IOException exception
     */
    Collection<ListItem> getBranches() throws IOException;

    /**
     * Filters data for the service type dropdown
     *
     * @param customizingModelList customizingModelList
     * @return Collection of ListItem
     */
    Collection<ListItem> getServiceTypes(Collection<ListItem> customizingModelList);

    /**
     * Filters data for the ministry dropdown
     *
     * @param customizingModelList customizingModelList
     * @return Collection of ListItem
     */
    Collection<ListItem> getMinistries(Collection<ListItem> customizingModelList);

    /**
     * Filters data for the service dropdown
     *
     * @param customizingModelList customizingModelList
     * @return Collection of ListItem
     */
    Collection<ListItem> getService(Collection<ListItem> customizingModelList);

    /**
     * Filters data for the contact person dropdown
     * @param customizingModelList customizingModelList
     * @return Collection of ListItem
     */
    Collection<ListItem> getContactPersons(Collection<ListItem> customizingModelList);

    /**
     * Loads an appointment by id
     *
     * @param id id
     * @return Appointment
     */
    Appointment getAppointment(Integer id);

    /**
     * Creates a new appointment
     *
     * @param appointment appointment
     * @return Appointment
     */
    Appointment saveAppointment(Appointment appointment);

    /**
     * prints AppointmentDetails
     * @param id id
     * @return InputStream
     */
    InputStream printAppointmentDetails(String id);

    /**
     * Retrives the available calendar slots for a selected date
     *
     * @param calendarSlot calendarSlot
     * @return Collection of CalendarSlot
     */
    Collection<CalendarSlot> getCalendarSlots(CalendarSlot calendarSlot);
    
    /**
     * @return label texts displayed in appointment calendar
     * depending on the current language
     */
    AppointmentCalendarLabels getAppointmentCalendarLabels();

}
