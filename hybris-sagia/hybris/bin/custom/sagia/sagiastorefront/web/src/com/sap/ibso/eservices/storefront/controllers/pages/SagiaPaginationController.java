package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/pagination")
public class SagiaPaginationController extends SagiaAbstractPageController {
    private static final String SAGIA_FIRST_PAGE_INDEX = "1";
    private static final String SAGIA_PAYMENTS_SESSION_ATTRIBUTE = "Payments";
    private static final String SAGIA_SERVICES_SESSION_ATTRIBUTE = "ServiceRequests";
    private static final String SAGIA_TICKETS_SESSION_ATTRIBUTE = "Tickets";
    private static final String SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE = "Appointments";
    private static final String SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE = "LegalConsultations";
    private static final String SAGIA_BRANCHES_SESSION_ATTRIBUTE = "Branches";

    @Resource(name = "sagiaPaginationFacade")
    private SagiaPaginationFacade sagiaPaginationFacade;
    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;
    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;
    @Resource(name = "sagiaServiceRequestFacade")
    private SagiaServiceRequestFacade serviceRequestFacade;
    @Resource(name = "sagiaPaymentFacade")
    private PaymentFacade sagiaPaymentFacade;
    @Resource(name = "sagiaComplaintFacade")
    private SagiaComplaintFacade sagiaComplaintFacade;
    @Resource(name = "sagiaAppointmentFacade")
    private SagiaAppointmentFacade sagiaAppointmentFacade;
    @Resource(name = "sagiaLegalConsultationFacade")
    private SagiaLegalConsultationFacade sagiaLegalConsultationFacade;

    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade licenseFacade;

    @RequestMapping(value = "/services/page/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getServicePage(@PathVariable("number") String number) {
        List<ServiceRequestData> serviceRequestsData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE) != null) {
            serviceRequestsData = getSessionService().getAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE);

        } else {
            serviceRequestsData = serviceRequestFacade.getServiceRequestsData();
            getSessionService().setAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE, serviceRequestsData);
        }
        serviceRequestsData = sagiaPaginationFacade.getServiceListForPage(serviceRequestsData,Integer.valueOf(number),
                Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), serviceRequestsData.size());
        return new Gson().toJson(serviceRequestsData);
    }

    @RequestMapping(value = "/services/page/{number}/{itemsPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public String getServicePage(@PathVariable("number") String number, @PathVariable int itemsPerPage) {
        List<ServiceRequestData> serviceRequestsData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE) != null) {
            serviceRequestsData = getSessionService().getAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE);

        } else {
            serviceRequestsData = serviceRequestFacade.getServiceRequestsData();
            getSessionService().setAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE, serviceRequestsData);
        }
        serviceRequestsData = sagiaPaginationFacade.getServiceListForPage(serviceRequestsData, Integer.valueOf(number),
                itemsPerPage, serviceRequestsData.size());
        return new Gson().toJson(serviceRequestsData);
    }

    @RequestMapping(value = "/payments/page/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getPaymentPage(@PathVariable("number") String number) {
        List<PaymentData> paymentData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE) != null) {
            paymentData = getSessionService().getAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE);
        } else {
            paymentData = sagiaPaymentFacade.getPayments();
            getSessionService().setAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE, paymentData);
        }
        paymentData = sagiaPaginationFacade.getPaymentListForPage(paymentData, Integer.valueOf(number),
                Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()),
                paymentData.size());
        return new Gson().toJson(paymentData);
    }

    @RequestMapping(value = "/payments/page/{number}/{itemsPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public String getPaymentPage(@PathVariable("number") String number, @PathVariable int itemsPerPage) {
        List<PaymentData> paymentData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE) != null) {
            paymentData = getSessionService().getAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE);
        } else {
            paymentData = sagiaPaymentFacade.getPayments();
            getSessionService().setAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE, paymentData);
        }
        paymentData = sagiaPaginationFacade.getPaymentListForPage(paymentData, Integer.valueOf(number),
                itemsPerPage,
                paymentData.size());
        return new Gson().toJson(paymentData);
    }

    @RequestMapping(value = "/tickets/page/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getTicketPage(@PathVariable("number") String number) {
        List<TicketData> ticketData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE) != null) {
            ticketData= getSessionService().getAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE);
        } else {
            ticketData = sagiaComplaintFacade.getTickets();
            getSessionService().setAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE, ticketData);
        }
        ticketData = sagiaPaginationFacade.getTicketListForPage(ticketData, Integer.valueOf(number),
                Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), ticketData.size());
        return new Gson().toJson(ticketData);
    }

    @RequestMapping(value = "/tickets/page/{number}/{itemsPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public String getTicketPage(@PathVariable("number") String number, @PathVariable int itemsPerPage) {
        List<TicketData> ticketData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE) != null) {
            ticketData= getSessionService().getAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE);
        } else {
            ticketData = sagiaComplaintFacade.getTickets();
            getSessionService().setAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE, ticketData);
        }
        ticketData = sagiaPaginationFacade.getTicketListForPage(ticketData, Integer.valueOf(number),
                itemsPerPage, ticketData.size());
        return new Gson().toJson(ticketData);
    }

    @RequestMapping(value = "/appointments/page/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getAppointmentPage(@PathVariable("number") String number) {
        List<Appointment> appointmentsData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE) != null) {
            appointmentsData= getSessionService().getAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE);
        } else {
            appointmentsData = new ArrayList<>(sagiaAppointmentFacade.getAppointments());
            getSessionService().setAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE, appointmentsData);
        }
        appointmentsData = sagiaPaginationFacade.getAppointmentListForPage(appointmentsData,Integer.valueOf(number),
                Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), appointmentsData.size());
        return new Gson().toJson(appointmentsData);
    }

    @RequestMapping(value = "/appointments/page/{number}/{itemsPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public String getAppointmentPage(@PathVariable("number") String number, @PathVariable int itemsPerPage) {
        List<Appointment> appointmentsData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE) != null) {
            appointmentsData= getSessionService().getAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE);
        } else {
            appointmentsData = new ArrayList<>(sagiaAppointmentFacade.getAppointments());
            getSessionService().setAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE, appointmentsData);
        }
        appointmentsData = sagiaPaginationFacade.getAppointmentListForPage(appointmentsData,Integer.valueOf(number),
                itemsPerPage, appointmentsData.size());
        return new Gson().toJson(appointmentsData);
    }

    @RequestMapping(value = "/legalConsultations/page/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getLegalConsultationPage(@PathVariable("number") String number) {
        List<LegalInquiryHDR> legalConsultations = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE) != null) {
            legalConsultations= getSessionService().getAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE);
        } else {
            legalConsultations = new ArrayList<LegalInquiryHDR>(sagiaLegalConsultationFacade.getLegalInquiryHDR());
            getSessionService().setAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE, legalConsultations);
        }
        legalConsultations = sagiaPaginationFacade.getLegalConsultationListForPage(legalConsultations,Integer.valueOf(number),
                Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), legalConsultations.size());
        return new Gson().toJson(legalConsultations);
    }

    @RequestMapping(value = "/legalConsultations/page/{number}/{itemsPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public String getLegalConsultationPage(@PathVariable("number") String number, @PathVariable int itemsPerPage) {
        List<LegalInquiryHDR> legalConsultations = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE) != null) {
            legalConsultations= getSessionService().getAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE);
        } else {
            legalConsultations = new ArrayList<LegalInquiryHDR>(sagiaLegalConsultationFacade.getLegalInquiryHDR());
            getSessionService().setAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE, legalConsultations);
        }
        legalConsultations = sagiaPaginationFacade.getLegalConsultationListForPage(legalConsultations,Integer.valueOf(number),
                itemsPerPage, legalConsultations.size());
        return new Gson().toJson(legalConsultations);
    }

    @RequestMapping(value = "/legalConsultations/sort/{parameter}/order/{order}", method = RequestMethod.GET)
    @ResponseBody
    public String getSortedLegalConsultationList(@PathVariable("parameter") String parameter, @PathVariable("order") String order) {
        List<LegalInquiryHDR> legalConsultations = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE) != null) {
            legalConsultations= getSessionService().getAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE);
        } else {
            legalConsultations = new ArrayList<LegalInquiryHDR>(sagiaLegalConsultationFacade.getLegalInquiryHDR());
            getSessionService().setAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE, legalConsultations);
        }
        legalConsultations = sagiaPaginationFacade.sortLegalConsultationList(legalConsultations, parameter,order);
        getSessionService().removeAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE);
        getSessionService().setAttribute(SAGIA_LEGALCONSULTATIONS_SESSION_ATTRIBUTE, legalConsultations);
        return this.getLegalConsultationPage(SAGIA_FIRST_PAGE_INDEX);
    }

    @RequestMapping(value = "/appointments/sort/{parameter}/order/{order}", method = RequestMethod.GET)
    @ResponseBody
    public String getSortedAppointmentList(@PathVariable("parameter") String parameter, @PathVariable("order") String order) {
        List<Appointment> appointmentsData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE) != null) {
            appointmentsData = getSessionService().getAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE);
        } else {
            appointmentsData = new ArrayList<>(sagiaAppointmentFacade.getAppointments());
            getSessionService().setAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE, appointmentsData);
        }
        appointmentsData = sagiaPaginationFacade.sortAppointmentList(appointmentsData, parameter,order);
        getSessionService().removeAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE);
        getSessionService().setAttribute(SAGIA_APPOINTMENTS_SESSION_ATTRIBUTE, appointmentsData);
        return this.getAppointmentPage(SAGIA_FIRST_PAGE_INDEX);
    }

    @RequestMapping(value = "/services/sort/{parameter}/order/{order}", method = RequestMethod.GET)
    @ResponseBody
    public String getSortedServiceList(@PathVariable("parameter") String parameter, @PathVariable("order") String order) {
        List<ServiceRequestData> serviceRequestsData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE) != null) {
            serviceRequestsData = getSessionService().getAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE);
        } else {
            serviceRequestsData = serviceRequestFacade.getServiceRequestsData();
            getSessionService().setAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE, serviceRequestsData);
        }
        serviceRequestsData = sagiaPaginationFacade.sortServicesList(serviceRequestsData, parameter,order);
        getSessionService().removeAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE);
        getSessionService().setAttribute(SAGIA_SERVICES_SESSION_ATTRIBUTE, serviceRequestsData);
        return this.getServicePage(SAGIA_FIRST_PAGE_INDEX);
    }

    @RequestMapping(value = "/payments/sort/{parameter}/order/{order}", method = RequestMethod.GET)
    @ResponseBody
    public String getSortedPaymentList(@PathVariable("parameter") String parameter, @PathVariable("order") String order) {
        List<PaymentData> paymentData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE) != null) {
            paymentData = getSessionService().getAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE);
        } else {
            paymentData = sagiaPaymentFacade.getPayments();
            getSessionService().setAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE, paymentData);
        }
        paymentData = sagiaPaginationFacade.sortPaymentList(paymentData, parameter,order);
        getSessionService().removeAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE);
        getSessionService().setAttribute(SAGIA_PAYMENTS_SESSION_ATTRIBUTE, paymentData);
        return this.getPaymentPage(SAGIA_FIRST_PAGE_INDEX);
    }

    @RequestMapping(value = "/tickets/sort/{parameter}/order/{order}", method = RequestMethod.GET)
    @ResponseBody
    public String getSortedTicketList(@PathVariable("parameter") String parameter, @PathVariable("order") String order){
        List<TicketData> ticketData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE) != null) {
            ticketData = getSessionService().getAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE);
        } else {
            ticketData = sagiaComplaintFacade.getTickets();
            getSessionService().setAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE, ticketData);
        }
        ticketData = sagiaPaginationFacade.sortTicketList(ticketData, parameter,order);
        getSessionService().removeAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE);
        getSessionService().setAttribute(SAGIA_TICKETS_SESSION_ATTRIBUTE, ticketData);
        return this.getTicketPage(SAGIA_FIRST_PAGE_INDEX);
    }

    @RequestMapping(value = "/branches/page/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getBranchPage(@PathVariable("number") String number) {
        List<BranchData> branchData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_BRANCHES_SESSION_ATTRIBUTE) != null) {
            branchData = getSessionService().getAttribute(SAGIA_BRANCHES_SESSION_ATTRIBUTE);
        } else {
            HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
            branchData = licenseFacade.getLicense(homeHDR).getBranches();
            getSessionService().setAttribute(SAGIA_BRANCHES_SESSION_ATTRIBUTE, branchData);
        }
        branchData = sagiaPaginationFacade.getDashboardBranchesListForPage(branchData,Integer.valueOf(number),
                Integer.parseInt(sagiaConfigurationFacade.getItemsPerPage()), branchData.size());
        return new Gson().toJson(branchData);
    }

    @RequestMapping(value = "/branches/page/{number}/{itemsPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public String getBranchPage(@PathVariable("number") String number, @PathVariable int itemsPerPage) {
        List<BranchData> branchData = new ArrayList<>(); //avoid null pointer exception
        if(getSessionService().getAttribute(SAGIA_BRANCHES_SESSION_ATTRIBUTE) != null) {
            branchData = getSessionService().getAttribute(SAGIA_BRANCHES_SESSION_ATTRIBUTE);
        } else {
            HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();
            branchData = licenseFacade.getLicense(homeHDR).getBranches();
            getSessionService().setAttribute(SAGIA_BRANCHES_SESSION_ATTRIBUTE, branchData);
        }
        branchData = sagiaPaginationFacade.getDashboardBranchesListForPage(branchData,Integer.valueOf(number),
                itemsPerPage, branchData.size());
        return new Gson().toJson(branchData);
    }
}
