package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.AppointmentListItem;
import com.sap.ibso.eservices.facades.data.CalendarSlot;
import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.facades.sagia.SagiaAppointmentFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaPaginationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaSpecialServiceFacade;
import com.sap.ibso.eservices.facades.sagia.impl.SagiaInvestorNotificationsFacade;
import com.sap.ibso.eservices.sagiaservices.appointment.AppointmentCalendarLabels;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.InvestorNotificationData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/appointments")
public class SagiaAppointmentController extends SagiaAbstractPageController {
    private static final String SAGIA_APPOINTMENTS_CMS_PAGE = "appointments";
    private static final String SAGIA_CREATE_APPOINTMENT_CMS_PAGE = "appointment-create";
    private static final String SAGIA_APPOINTMENT_DETAILS_CMS_PAGE = "appointment-details";
    private static final String SAGIA_APPOINTMENT_INFO_CMS_PAGE = "appointment-info";
    private static final String NOTIFICATION_TYPE_VI = "VI";
    // to be replaced with config
    private static final int SAGIA_ITEMS_PER_PAGE = 5;
    private static final String SAGIA_FIRST_PAGE_INDEX = "1";
    private static final String DEPARTMENTS = "departments";
    private static final String BRANCHES = "branches";
    private static final String MINISTRIES = "ministries";
    private static final String SERVICE_TYPES = "serviceTypes";
    private static final String SERVICES = "services";
    private static final String CONTACT_PERSONS = "contactPersons";
    private static final String APPOINTMENT_MODEL = "appointmentModel";
    private static final String APPOINTMENT_ID = "appointmentId";
    private static final String APPOINTMENTS = "Appointments";
    private static final String APPOINTMENTS_PAGE_NUMBER = "appointmentsPageNumber";
    private static final String FORM_OPTIONS_JSON = "formOptionsJSON";
    private static final String APPOINTMENTS_JSON = "appointmentsJson";
    private static final String APPOINTMENT_JSON = "appointmentJson";
    private static final String APPOINTMENTS_MODEL = "appointments";

    @Resource(name = "sagiaAppointmentFacade")
    private SagiaAppointmentFacade sagiaAppointmentFacade;

    @Resource(name = "sagiaSpecialServicesFacade")
    private SagiaSpecialServiceFacade sagiaSpecialServiceFacade;

    @Resource(name = "sagiaPaginationFacade")
    private SagiaPaginationFacade sagiaPaginationFacade;

    @Resource(name = "sagiaInvestorNotificationsFacade")
    private SagiaInvestorNotificationsFacade investorNotificationsFacade;

    @Resource(name = "sagiaFormatProvider")
    private SagiaFormatProvider formatProvider;

    /**
     * Renders the create/edit appointment page
     *
     * @param id
     * @param model
     * @return
     * @throws IOException
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(path = {"/create", "/edit/{id}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String editAppointment(@PathVariable("id") Optional<String> id, final Model model) throws CMSItemNotFoundException {
        loadAppointmentData(model, id);
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CREATE_APPOINTMENT_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CREATE_APPOINTMENT_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Saves an appointment
     *
     * @param id
     * @param appointment
     * @param model
     * @return
     */
    @RequestMapping(value = {"/create", "/edit/{id}"}, method = RequestMethod.POST)
    @RequireHardLogIn
    public ResponseEntity saveAppointment(@PathVariable("id") Optional<String> id, Appointment appointment, final Model model) {
        Appointment savedAppointment = sagiaAppointmentFacade.saveAppointment(appointment);
        return new ResponseEntity(savedAppointment, HttpStatus.OK);
    }

    private void loadAppointmentData(final Model model, Optional<String> id) {
        Appointment appointmentData = new Appointment();
        if (id.isPresent()) {
            appointmentData = sagiaAppointmentFacade.getAppointment(Integer.parseInt(id.get()));
            model.addAttribute(APPOINTMENT_ID, id);
        } else {
            model.addAttribute(APPOINTMENT_ID, "");
        }
        model.addAttribute(APPOINTMENT_MODEL, appointmentData);

        Collection<ListItem> formOptions = sagiaAppointmentFacade.getAppointmentOptions();

        Collection<ListItem> departments = sagiaAppointmentFacade.getDepartments(formOptions);
        model.addAttribute(DEPARTMENTS, departments);

        Collection<ListItem> branches = sagiaAppointmentFacade.getBranches(formOptions);
        model.addAttribute(BRANCHES, branches);

        Collection<ListItem> ministries = sagiaAppointmentFacade.getMinistries(formOptions);
        model.addAttribute(MINISTRIES, ministries);

        Collection<ListItem> serviceTypes = sagiaAppointmentFacade.getServiceTypes(formOptions);
        model.addAttribute(SERVICE_TYPES, serviceTypes);

        Collection<ListItem> services = sagiaAppointmentFacade.getService(formOptions);
        model.addAttribute(SERVICES, services);

        Collection<ListItem> contactPersons = sagiaAppointmentFacade.getContactPersons(formOptions);
        model.addAttribute(CONTACT_PERSONS, contactPersons);

        Collection<Appointment> appointments = sagiaAppointmentFacade.getAppointments();
        getSessionService().setAttribute(APPOINTMENTS, appointments);
        model.addAttribute(APPOINTMENTS_PAGE_NUMBER, sagiaPaginationFacade.getPagesNumber(appointments.size(), SAGIA_ITEMS_PER_PAGE));
        List<Appointment> appointmentsList = new ArrayList<>(appointments);
        appointments = sagiaPaginationFacade.getAppointmentListForPage(appointmentsList, Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), SAGIA_ITEMS_PER_PAGE, appointments.size());
        model.addAttribute(SAGIA_APPOINTMENTS_CMS_PAGE, appointments);

        Map<String, List<AppointmentListItem>> jsonData = getFormOptionsJSON(formOptions);
        String formOptionsJSON = new Gson().toJson(jsonData);
        model.addAttribute(FORM_OPTIONS_JSON, formOptionsJSON);

        String appointmentsJson1 = new Gson().toJson(appointments);
        model.addAttribute(APPOINTMENTS_JSON, appointmentsJson1);

        model.addAttribute(APPOINTMENT_JSON, new Gson().toJson(appointmentData));
    }

    /**
     * Displays details of an appointment based on its id
     *
     * @param id
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(path = {"/details/{id}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String appointmentDetails(@PathVariable("id") Integer id, final Model model) throws CMSItemNotFoundException {
        Appointment appointmentData = sagiaAppointmentFacade.getAppointment(id);
        model.addAttribute("appointmentData", appointmentData);

        String appointmentDataJSON = new Gson().toJson(appointmentData);
        model.addAttribute("appointmentDataJSON", appointmentDataJSON);

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_APPOINTMENT_DETAILS_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_APPOINTMENT_DETAILS_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Displays the details of an appointment based on the information in the URL that is rendered as a QR code on the details page
     *
     * @param departmentKey
     * @param branchKey
     * @param aptDate
     * @param ministryKeys
     * @param serviceTypeKeys
     * @param serviceKeys
     * @param model
     * @return
     * @throws IOException
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(path = {"/appointment-info/{departmentKey}/{branchKey}/{aptDate}/{ministryKeys}/{serviceTypeKeys}/{serviceKeys}"}, method = RequestMethod.GET)
    public String appointmentInfoQR(
            @PathVariable("departmentKey") Optional<String> departmentKey,
            @PathVariable("branchKey") Optional<String> branchKey,
            @PathVariable("aptDate") Optional<String> aptDate,
            @PathVariable("ministryKeys") Optional<String> ministryKeys,
            @PathVariable("serviceTypeKeys") Optional<String> serviceTypeKeys,
            @PathVariable("serviceKeys") Optional<String> serviceKeys,
            final Model model) throws IOException, CMSItemNotFoundException {
        Collection<ListItem> formOptions = sagiaAppointmentFacade.getAppointmentOptions();

        if (departmentKey.isPresent()) {
            Optional<ListItem> department = sagiaAppointmentFacade.getDepartments(formOptions).stream().filter(x -> x.getFieldKey().equals(departmentKey.get())).findFirst();
            if (department.isPresent()) {
                model.addAttribute("department", department.get());
            }
        }

        if (branchKey.isPresent()) {
            Optional<ListItem> branch = sagiaAppointmentFacade.getBranches(formOptions).stream().filter(x -> x.getFieldKey().equals(branchKey.get())).findFirst();
            if (branch.isPresent()) {
                model.addAttribute("branch", branch.get());
            }
        }

        if (aptDate.isPresent()) {
            LocalDateTime dateTime = LocalDateTime.parse(aptDate.get());
            model.addAttribute("date", dateTime);
        }

        addMinistry(ministryKeys, model, formOptions);

        addServiceTypes(serviceTypeKeys, model, formOptions);

        addServices(serviceTypeKeys, serviceKeys, model, formOptions);

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_APPOINTMENT_INFO_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_APPOINTMENT_INFO_CMS_PAGE));
        return getViewForPage(model);
    }

    private void addServices(@PathVariable("serviceTypeKeys") Optional<String> serviceTypeKeys, @PathVariable("serviceKeys") Optional<String> serviceKeys, Model model, Collection<ListItem> formOptions) {
        String service1 = "-";
        String service2 = "-";
        String service3 = "-";
        if (serviceTypeKeys.isPresent()) {
            Collection<ListItem> services = sagiaAppointmentFacade.getService(formOptions);
            String[] serviceTokens = serviceKeys.get().split(":");
            if (!"-".equals(serviceTokens[0])) {
                service1 = services.stream().filter(x -> x.getFieldKey().equals(serviceTokens[0])).findFirst().get().getDescription();
            }
            if (!"-".equals(serviceTokens[1])) {
                service2 = services.stream().filter(x -> x.getFieldKey().equals(serviceTokens[1])).findFirst().get().getDescription();
            }
            if (!"-".equals(serviceTokens[2])) {
                service3 = services.stream().filter(x -> x.getFieldKey().equals(serviceTokens[2])).findFirst().get().getDescription();
            }
        }

        model.addAttribute("service1", service1);
        model.addAttribute("service2", service2);
        model.addAttribute("service3", service3);
    }

    private void addServiceTypes(@PathVariable("serviceTypeKeys") Optional<String> serviceTypeKeys, Model model, Collection<ListItem> formOptions) {
        String serviceType1 = "";
        String serviceType2 = "";
        String serviceType3 = "";
        if (serviceTypeKeys.isPresent()) {
            Collection<ListItem> serviceTypes = sagiaAppointmentFacade.getServiceTypes(formOptions);
            String[] serviceTypesTokens = serviceTypeKeys.get().split(":");
            if (!"-".equals(serviceTypesTokens[0])) {
                serviceType1 = serviceTypes.stream().filter(x -> x.getFieldKey().equals(serviceTypesTokens[0])).findFirst().get().getDescription();
            }
            if (!"-".equals(serviceTypesTokens[1])) {
                serviceType2 = serviceTypes.stream().filter(x -> x.getFieldKey().equals(serviceTypesTokens[1])).findFirst().get().getDescription();
            }
            if (!"-".equals(serviceTypesTokens[2])) {
                serviceType3 = serviceTypes.stream().filter(x -> x.getFieldKey().equals(serviceTypesTokens[2])).findFirst().get().getDescription();
            }
        }

        model.addAttribute("serviceType1", serviceType1);
        model.addAttribute("serviceType2", serviceType2);
        model.addAttribute("serviceType3", serviceType3);
    }

    private void addMinistry(@PathVariable("ministryKeys") Optional<String> ministryKeys, Model model, Collection<ListItem> formOptions) {
        String ministry1 = "-";
        String ministry2 = "-";
        String ministry3 = "-";
        if (ministryKeys.isPresent()) {
            Collection<ListItem> ministries = sagiaAppointmentFacade.getMinistries(formOptions);
            String[] ministriesTokens = ministryKeys.get().split(":");
            if (!"-".equals(ministriesTokens[0])) {
                ministry1 = ministries.stream().filter(x -> x.getFieldKey().equals(ministriesTokens[0])).findFirst().get().getDescription();
            }
            if (!"-".equals(ministriesTokens[1])) {
                ministry2 = ministries.stream().filter(x -> x.getFieldKey().equals(ministriesTokens[1])).findFirst().get().getDescription();
            }
            if (!"-".equals(ministriesTokens[2])) {
                ministry3 = ministries.stream().filter(x -> x.getFieldKey().equals(ministriesTokens[2])).findFirst().get().getDescription();
            }
        }

        model.addAttribute("ministry1", ministry1);
        model.addAttribute("ministry2", ministry2);
        model.addAttribute("ministry3", ministry3);
    }

    /**
     * Loads the available time slots for creating a new appointment
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/get-calendar-slots", method = RequestMethod.POST)
    @RequireHardLogIn
    public @ResponseBody
    ResponseEntity getCalendarSlots(@RequestBody Appointment appointment) {
        CalendarSlot calendarSlot = new CalendarSlot();
        calendarSlot.setBranch(appointment.getBranch());
        calendarSlot.setMinistry1(appointment.getMinistry1());
        calendarSlot.setMinistry2(appointment.getMinistry2());
        calendarSlot.setMinistry3(appointment.getMinistry3());
        calendarSlot.setType1(appointment.getServiceType1());
        calendarSlot.setType2(appointment.getServiceType2());
        calendarSlot.setType3(appointment.getServiceType3());
        calendarSlot.setDate(formatProvider.getDateTimeFromUIDateString(appointment.getDateString()));
        Collection<CalendarSlot> calendarSlotCollection = sagiaAppointmentFacade.getCalendarSlots(calendarSlot);
        return new ResponseEntity<>(calendarSlotCollection, HttpStatus.OK);
    }

    /**
     * Loads all the appointments
     *
     * @param model
     * @return
     * @throws CMSItemNotFoundException exception
     */

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S1067 | Expressions should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity","squid:S1067"})
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getAppointments(final Model model, HttpServletRequest request) throws CMSItemNotFoundException {
        Collection<ListItem> formOptions = sagiaAppointmentFacade.getAppointmentOptions();

        Collection<ListItem> departments = sagiaAppointmentFacade.getDepartments(formOptions);
        model.addAttribute(DEPARTMENTS, departments);

        Collection<ListItem> branches = sagiaAppointmentFacade.getBranches(formOptions);
        model.addAttribute(BRANCHES, branches);

        Collection<ListItem> ministries = sagiaAppointmentFacade.getMinistries(formOptions);
        model.addAttribute(MINISTRIES, ministries);

        Collection<ListItem> serviceTypes = sagiaAppointmentFacade.getServiceTypes(formOptions);
        model.addAttribute(SERVICE_TYPES, serviceTypes);

        Collection<ListItem> services = sagiaAppointmentFacade.getService(formOptions);
        model.addAttribute(SERVICES, services);

        Collection<ListItem> contactPersons = sagiaAppointmentFacade.getContactPersons(formOptions);
        model.addAttribute(CONTACT_PERSONS, contactPersons);
        
        AppointmentCalendarLabels calendarLabels = sagiaAppointmentFacade.getAppointmentCalendarLabels();
        model.addAttribute("calendarLabelsJson", new Gson().toJson(calendarLabels));

        Collection<Appointment> appointments = sagiaAppointmentFacade.getAppointments();
        getSessionService().setAttribute(APPOINTMENTS, appointments);
        model.addAttribute(APPOINTMENTS_PAGE_NUMBER, sagiaPaginationFacade.getPagesNumber(appointments.size(), SAGIA_ITEMS_PER_PAGE));
        List<Appointment> appointmentsList = new ArrayList<>(appointments);
        appointments = sagiaPaginationFacade.getAppointmentListForPage(appointmentsList, Integer.valueOf(SAGIA_FIRST_PAGE_INDEX), SAGIA_ITEMS_PER_PAGE, appointments.size());
        String filter = null;
        if(request != null) {
            filter = request.getParameter("searchParameter");
        }
        if(filter != null && !filter.isEmpty()) {
            model.addAttribute("searchParameter", filter);
            List<Appointment> filteredAppointments = new ArrayList<>();
            for(Appointment appointment : appointments) {
                String serviceType1Description = appointment.getServiceType1Description();
                String branchDescription = appointment.getBranchDescription();
                String statusDescription = appointment.getStatusDescription();
                if((serviceType1Description != null && !serviceType1Description.isEmpty() && serviceType1Description.toLowerCase().contains(filter.toLowerCase()))
                        || (branchDescription != null && !branchDescription.isEmpty() && branchDescription.toLowerCase().contains(filter.toLowerCase()))
                        || (statusDescription != null && !statusDescription.isEmpty() && statusDescription.toLowerCase().contains(filter.toLowerCase()))) {
                    filteredAppointments.add(appointment);
                }
            }
            model.addAttribute(APPOINTMENTS_MODEL, filteredAppointments);
        } else {
            model.addAttribute(APPOINTMENTS_MODEL, appointments);
        }

        Map<String, List<AppointmentListItem>> jsonData = getFormOptionsJSON(formOptions);
        String formOptionsJSON = new Gson().toJson(jsonData);
        model.addAttribute(FORM_OPTIONS_JSON, formOptionsJSON);

        String appointmentsJson1 = new Gson().toJson(appointments);
        model.addAttribute(APPOINTMENTS_JSON, appointmentsJson1);

        model.addAttribute(APPOINTMENT_MODEL, new AppointmentData());

        Collection<InvestorNotificationData> notifications = investorNotificationsFacade.getCRMNotificationsByType(NOTIFICATION_TYPE_VI);
        String notificationsJson =
                new Gson().toJson(notifications);
        model.addAttribute("notificationsJson", notificationsJson);

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_APPOINTMENTS_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_APPOINTMENTS_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Loads the form options as a JSON object
     *
     * @param entries
     * @return
     */
    public Map<String, List<AppointmentListItem>> getFormOptionsJSON(Collection<ListItem> entries) {
        List<AppointmentListItem> convertedItems = entries.stream().map(this::createAppointmentListItemPOJO).collect(Collectors.toList());
        Map<String, List<AppointmentListItem>> result = new HashMap<>();
        List<AppointmentListItem> departments = convertedItems.stream().filter(p -> "DEPARTMENT".equalsIgnoreCase(p.getFieldName())).collect(Collectors.toList());
        result.put(DEPARTMENTS, departments);
        List<AppointmentListItem> branches = convertedItems.stream().filter(p -> "BRANCH".equalsIgnoreCase(p.getFieldName())).collect(Collectors.toList());
        result.put(BRANCHES, branches);

        List<AppointmentListItem> serviceTypes = convertedItems.stream()
                .filter(p -> "SERVTYPE".equalsIgnoreCase(p.getFieldName()))
                .collect(Collectors.toList());
        for (AppointmentListItem item : serviceTypes) {
            List<AppointmentListItem> ministries = convertedItems.stream()
                    .filter(p -> p.getFieldSubType().equalsIgnoreCase(item.getFieldKey()) && "SERVICE".equalsIgnoreCase(p.getFieldName()))
                    .collect(Collectors.toList());
            for (AppointmentListItem ministry : ministries) {
                List<AppointmentListItem> services = convertedItems.stream().filter(p -> p.getFieldSubType().equals(ministry.getFieldKey())).collect(Collectors.toList());
                ministry.setServices(services);
            }
            if (CollectionUtils.isNotEmpty(ministries)) {
                item.setMinistries(!ministries.isEmpty() ? ministries : Collections.emptyList());
            }
            List<AppointmentListItem> services = convertedItems.stream()
                    .filter(p -> p.getFieldSubType().equalsIgnoreCase(item.getFieldKey()) && "SUBSERVICE".equalsIgnoreCase(p.getFieldName()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(services)) {
                item.setServices(!services.isEmpty() ? services : Collections.emptyList());
            }
        }
        result.put(SERVICE_TYPES, serviceTypes);

        List<AppointmentListItem> contactPersons = convertedItems.stream().filter(p -> "CONTACTPERSON".equalsIgnoreCase(p.getFieldName())).collect(Collectors.toList());
        result.put(CONTACT_PERSONS, contactPersons);

        return result;
    }

    private AppointmentListItem createAppointmentListItemPOJO(ListItem item) {
        AppointmentListItem convertedItem = new AppointmentListItem();
        convertedItem.setScenario(item.getScenario());
        convertedItem.setFieldName(item.getFieldName());
        convertedItem.setFieldKey(item.getFieldKey());
        convertedItem.setFieldSubType(item.getFieldSubType());
        convertedItem.setDescription(item.getDescription());
        convertedItem.setDockeyID(item.getDockeyID());
        convertedItem.setLongDescription(item.getLongDescription());
        convertedItem.setServices(new ArrayList<>());
        convertedItem.setMinistries(new ArrayList<>());
        return convertedItem;
    }
}
