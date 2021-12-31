package com.sap.ibso.eservices.facades.sagia.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.sap.ibso.eservices.core.event.AppointmentEmailGenerationEvent;
import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.CalendarSlot;
import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.facades.populators.AppointmentPopulator;
import com.sap.ibso.eservices.facades.populators.AppointmentReversePopulator;
import com.sap.ibso.eservices.facades.populators.CalendarSlotPopulator;
import com.sap.ibso.eservices.facades.populators.CalendarSlotReversePopulator;
import com.sap.ibso.eservices.facades.populators.ListItemPopulator;
import com.sap.ibso.eservices.facades.populators.NationalInvestorAppointmentReversePopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaAppointmentFacade;
import com.sap.ibso.eservices.sagiaservices.appointment.AppointmentCalendarLabels;
import com.sap.ibso.eservices.sagiaservices.appointment.AppointmentCalendarLabelsService;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;

import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

/**
 * DefaultSagiaAppointmentFacade
 */
public class DefaultSagiaAppointmentFacade implements SagiaAppointmentFacade {

    private ZUI5SagiaFacade zui5SagiaFacade;
    @Autowired
    private AppointmentPopulator appointmentPopulator;
    @Autowired
    private AppointmentReversePopulator appointmentReversePopulator;
    @Autowired
    private ListItemPopulator listItemPopulator;
    @Autowired
    CalendarSlotPopulator calendarSlotPopulator;
    @Autowired
    CalendarSlotReversePopulator calendarSlotReversePopulator;
    @Autowired
    NationalInvestorAppointmentReversePopulator nationalInvestorAppointmentReversePopulator;
    @Autowired
    private EventService eventService;
    @Autowired
    private BaseStoreService baseStoreService;
    @Autowired
    private BaseSiteService baseSiteService;
    @Autowired
    private CommonI18NService commonI18NService;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    private AppointmentCalendarLabelsService appointmentCalendarLabelsService;
    /**
     * @param zui5SagiaFacade
     */
    public void setZui5SagiaFacade(ZUI5SagiaFacade zui5SagiaFacade) {
        this.zui5SagiaFacade = zui5SagiaFacade;
    }

    /**
     * @param appointmentPopulator
     */
    public void setAppointmentPopulator(AppointmentPopulator appointmentPopulator) {
        this.appointmentPopulator = appointmentPopulator;
    }

    /**
     * @param appointmentReversePopulator
     */
    public void setAppointmentReversePopulator(AppointmentReversePopulator appointmentReversePopulator) {
        this.appointmentReversePopulator = appointmentReversePopulator;
    }

    /**
     * @param calendarSlotReversePopulator
     */
    public void setCalendarSlotReversePopulator(CalendarSlotReversePopulator calendarSlotReversePopulator) {
        this.calendarSlotReversePopulator = calendarSlotReversePopulator;
    }

    /**
     * @param listItemPopulator
     */
    public void setListItemPopulator(ListItemPopulator listItemPopulator) {
        this.listItemPopulator = listItemPopulator;
    }

    /**
     * @param calendarSlotPopulator
     */
    public void setCalendarSlotPopulator(CalendarSlotPopulator calendarSlotPopulator) {
        this.calendarSlotPopulator = calendarSlotPopulator;
    }

    /**
     * @param nationalInvestorAppointmentReversePopulator
     */
    public void setNationalInvestorAppointmentReversePopulator(NationalInvestorAppointmentReversePopulator nationalInvestorAppointmentReversePopulator) {
        this.nationalInvestorAppointmentReversePopulator = nationalInvestorAppointmentReversePopulator;
    }

    /**
     * Gets the appointments through the zui5SagiaFacade facade
     *
     * @return
     * @throws IOException
     */
    @Override
    public Collection<Appointment> getAppointments() {
        Collection<AppointmentData> appointmentData = zui5SagiaFacade.getAppointments();
        Collection<Appointment> result = new ArrayList<>();
        for (AppointmentData appointmentItem : appointmentData) {
            Appointment appointment = new Appointment();
            appointmentPopulator.populate(appointmentItem, appointment);
            result.add(appointment);
        }
        return result;
    }

    /**
     * Gets the appointment for a given id through the zui5SagiaFacade facade
     *
     * @param id
     * @return
     */
    @Override
    public Appointment getAppointment(Integer id) {
        AppointmentData appointmentData = zui5SagiaFacade.getAppointment(id);
        Appointment appointment = new Appointment();
        appointmentPopulator.populate(appointmentData, appointment);
        return appointment;
    }

    /**
     * Gets the data required by the drop downs through the zui5SagiaFacade facade
     *
     * @return
     * @throws IOException
     */
    @Override
    public Collection<ListItem> getAppointmentOptions() {
        String appointmentOptionsKey = "appointmentOptions_" + commonI18NService.getCurrentLanguage().getIsocode();
        Collection<CustomizingGetData> appointmentOptions = getSessionService().getCurrentSession().getAttribute(appointmentOptionsKey);
        if(appointmentOptions == null){
            appointmentOptions = zui5SagiaFacade.getAppointmentOptions();
            getSessionService().getCurrentSession().setAttribute(appointmentOptionsKey, appointmentOptions);
        }

        Collection<ListItem> result = new ArrayList<>();
        for (CustomizingGetData item : appointmentOptions) {
            ListItem listItem = new ListItem();
            listItemPopulator.populate(item, listItem);
            result.add(listItem);
        }
        return result;
    }

    /**
     * Saves an appointment and sends an email confirmation
     *
     * @param appointment
     */
    @Override
    public Appointment saveAppointment(Appointment appointment) {
        AppointmentData appointmentData = new AppointmentData();
        appointmentReversePopulator.populate(appointment, appointmentData);
        AppointmentData savedAppointmentData = zui5SagiaFacade.saveAppointment(appointmentData);
        Appointment savedAppointment = new Appointment();
        if(savedAppointmentData != null){
            appointmentPopulator.populate(savedAppointmentData, savedAppointment);
        }
        //getEventService().publishEvent(initializeEvent(new AppointmentEmailGenerationEvent(), appointmentData));
        return savedAppointment;
    }

    /**
     * prints AppointmentDetails
     * @param id id
     * @return InputStream
     */
    public InputStream printAppointmentDetails(String id){
        return zui5SagiaFacade.printAppointmentDetails(id);
    }

    protected AbstractCommerceUserEvent initializeEvent(final AppointmentEmailGenerationEvent event, final AppointmentData appointment) {
        event.setBaseStore(getBaseStoreService().getCurrentBaseStore());
        event.setSite(getBaseSiteService().getCurrentBaseSite());
        event.setAppointmentData(appointment);
        event.setCustomer((CustomerModel) getUserService().getCurrentUser());
        event.setLanguage(getCommonI18NService().getCurrentLanguage());
        event.setCurrency(getCommonI18NService().getCurrentCurrency());
        return event;
    }

    /**
     * Filters the appointment options for the department dropdown
     *
     * @param customizingModelList
     * @return
     */
    @Override
    public Collection<ListItem> getDepartments(Collection<ListItem> customizingModelList) {
        return customizingModelList.stream().filter(p -> "DEPARTMENT".equals(p.getFieldName())).collect(Collectors.toList());
    }

    /**
     * Gets the departments by making a CRM call
     *
     * @return
     * @throws IOException
     */
    @Override
    public Collection<ListItem> getBranches() {
        return getBranches(getAppointmentOptions());
    }

    /**
     * Filters the appointment options for the branch dropdown
     *
     * @param customizingModelList
     * @return
     */
    @Override
    public Collection<ListItem> getBranches(Collection<ListItem> customizingModelList) {
        String appointmentBranchesKey = "appointmentBranches_" + commonI18NService.getCurrentLanguage().getIsocode();
        Collection<ListItem> branches = getSessionService().getCurrentSession().getAttribute(appointmentBranchesKey);
        if(branches == null){
            branches = customizingModelList.stream().filter(p -> "BRANCH".equals(p.getFieldName())).collect(Collectors.toList());
            getSessionService().getCurrentSession().setAttribute(appointmentBranchesKey, branches);
        }

        return branches;
    }

    /**
     * Filters the appointment options for the service type dropdown
     *
     * @param customizingModelList
     * @return
     */
    @Override
    public List<ListItem> getServiceTypes(Collection<ListItem> customizingModelList) {
        return customizingModelList.stream().filter(p -> "SERVTYPE".equals(p.getFieldName())).collect(Collectors.toList());
    }

    /**
     * Filters the appointment options for the ministry dropdown
     *
     * @param customizingModelList
     * @return
     */
    @Override
    public List<ListItem> getMinistries(Collection<ListItem> customizingModelList) {
        return customizingModelList.stream().filter(p -> "SERVICE".equals(p.getFieldName())).collect(Collectors.toList());
    }

    /**
     * Filters the appointment options for the service dropdown
     *
     * @param customizingModelList
     * @return
     */
    @Override
    public List<ListItem> getService(Collection<ListItem> customizingModelList) {
        return customizingModelList.stream().filter(p -> "SUBSERVICE".equals(p.getFieldName())).collect(Collectors.toList());
    }

    @Override
    public Collection<ListItem> getContactPersons(Collection<ListItem> customizingModelList) {
        return customizingModelList.stream().filter(p -> "Contactperson".equals(p.getFieldName())).collect(Collectors.toList());
    }

    /**
     * Loads the calendar slots for making an appointment
     *
     * @param calendarSlot
     * @return
     */
    @Override
    public Collection<CalendarSlot> getCalendarSlots(CalendarSlot calendarSlot) {
        CalendarSlotData calendarSlotData = new CalendarSlotData();
        calendarSlotReversePopulator.populate(calendarSlot, calendarSlotData);
        Collection<CalendarSlotData> calendarSlotDataCollection = zui5SagiaFacade.getCalendarSlots(calendarSlotData);
        Collection<CalendarSlot> result = new ArrayList<>();
        for (CalendarSlotData item : calendarSlotDataCollection) {
            CalendarSlot calendarSlotItem = new CalendarSlot();
            calendarSlotPopulator.populate(item, calendarSlotItem);
            result.add(calendarSlotItem);
        }
        return result;
    }

    /**
     * @return
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * @param eventService
     */
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * @return
     */
    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    /**
     * @param baseStoreService
     */
    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    /**
     * @return
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * @param baseSiteService
     */
    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    /**
     * @return
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    /**
     * @param commonI18NService
     */
    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    /**
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return
     */
    public SessionService getSessionService() {
        return sessionService;
    }

    /**
     * @param sessionService
     */
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    
    public void setAppointmentCalendarLabelsService(AppointmentCalendarLabelsService appointmentCalendarLabelsService) {
        this.appointmentCalendarLabelsService = appointmentCalendarLabelsService;
    }


	@Override
	public AppointmentCalendarLabels getAppointmentCalendarLabels() {
		return appointmentCalendarLabelsService.getCalendarLabels();
	}
}
