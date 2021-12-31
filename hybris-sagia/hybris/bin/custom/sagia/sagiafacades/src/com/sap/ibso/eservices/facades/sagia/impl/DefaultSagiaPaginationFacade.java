package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.sagia.SagiaPaginationFacade;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * DefaultSagiaPaginationFacade
 */
public class DefaultSagiaPaginationFacade implements SagiaPaginationFacade {

    /**
     * retrieves PagesNumber
     * @param listSize     listSize
     * @param itemsPerPage itemsPerPage
     * @return int
     */
    public int getPagesNumber(double listSize, double itemsPerPage) {
        return (int) Math.ceil(listSize / itemsPerPage);
    }

    /**
     * retrieves ServiceListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsPerPage itemsPerPage
     * @param listSize listSize
     * @return List of ServiceRequestData
     */
    public List<ServiceRequestData> getServiceListForPage(List<ServiceRequestData> dataList, int pageNumber, int itemsPerPage, int listSize) {
        int startIndex = itemsPerPage * (pageNumber - 1);
        int endIndex = (startIndex + itemsPerPage) > listSize ? listSize : (startIndex + itemsPerPage);
        return dataList.subList(startIndex, endIndex);
    }

    /**
     * sorts ServicesList
     * @param serviceList serviceList
     * @param parameter parameter
     * @param order order
     * @return List of ServiceRequestData
     */
    public List<ServiceRequestData> sortServicesList(List<ServiceRequestData> serviceList, String parameter, String order) {
        serviceList = new ArrayList<>(serviceList);
        switch (parameter.toLowerCase()) {
            case STATUS:
                sortServiceRequestData(serviceList, order);
                break;
            case NAME:
                sortServiceRequestDataByName(serviceList, order);
                break;
            case DATE:
                sortServiceRequestDataByDate(serviceList, order);
                break;
            default:
                break;
        }
        return serviceList;
    }

    private void sortServiceRequestDataByName(List<ServiceRequestData> serviceList, String order) {
        if (order.equalsIgnoreCase(DESC)) {
            serviceList.sort(Comparator.comparing(ServiceRequestData::getSortByNameValue).reversed());
        } else {
            serviceList.sort(Comparator.comparing(ServiceRequestData::getSortByNameValue));
        }
    }

    private void sortServiceRequestDataByDate(List<ServiceRequestData> serviceList, String order) {
        if (order.equalsIgnoreCase(DESC)) {
            //format date from CRM yyyy-MM-dd'T'HH:mm
            serviceList.sort(Comparator.comparing(ServiceRequestData::getRequestDate,Comparator.comparing(SagiaDateData::getDate)).reversed());
        } else {
            serviceList.sort(Comparator.comparing(ServiceRequestData::getRequestDate,Comparator.comparing(SagiaDateData::getDate)));
        }
    }

    private void sortServiceRequestData(List<ServiceRequestData> serviceList, String order) {
        if (order.equalsIgnoreCase(DESC)) {
            serviceList.sort(Comparator.comparing(ServiceRequestData::getStatus).reversed());
        } else {
            serviceList.sort(Comparator.comparing(ServiceRequestData::getStatus));
        }
    }

    /**
     * retrieves PaymentListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of PaymentData
     */
    public List<PaymentData> getPaymentListForPage(List<PaymentData> dataList, int pageNumber, int itemsperpage, int listSize) {
        return getSubList(dataList, pageNumber, itemsperpage, listSize);
    }

    /**
     * sorts PaymentList
     * @param dataList dataList
     * @param parameter parameter
     * @param  order order
     * @return List of PaymentData
     */
    public List<PaymentData> sortPaymentList(List<PaymentData> dataList, String parameter, String order) {
        dataList = new ArrayList<>(dataList);
        switch (parameter.toLowerCase()) {
            case STATUS:
                sortPaymentData(dataList, order, Comparator.comparing(PaymentData::getStatus));
                break;
            case AMOUNT:
                sortPaymentData(dataList, order, Comparator.comparing(PaymentData::getAmount));
                break;
            case DATE:
                sortPaymentData(dataList, order, Comparator.comparing(PaymentData::getJavaPaymentDate));
                break;
            default:
                break;
        }
        return dataList;
    }

    private void sortPaymentData(List<PaymentData> dataList, String order, Comparator<PaymentData> comparing) {
        if (order.equalsIgnoreCase(DESC)) {
            dataList.sort(comparing.reversed());
        } else {
            dataList.sort(comparing);
        }
    }

    /**
     * retrieves TicketListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of TicketData
     */
    public List<TicketData> getTicketListForPage(List<TicketData> dataList, int pageNumber, int itemsperpage, int listSize) {
        return getSubList(dataList, pageNumber, itemsperpage, listSize);
    }

    @Override
    public List<Appointment> getAppointmentListForPage(List<Appointment> dataList, int pageNumber, int itemsperpage, int listSize) {
        return getSubList(dataList, pageNumber, itemsperpage, listSize);
    }

    @Override
    public List<LegalInquiryHDR> getLegalConsultationListForPage(List<LegalInquiryHDR> dataList, int pageNumber, int itemsperpage, int listSize) {
        return getSubList(dataList, pageNumber, itemsperpage, listSize);
    }


    private List getSubList(List<?> dataList, int pageNumber, int itemsperpage, int listSize) {
        int startIndex = itemsperpage * (pageNumber - 1);
        int endIndex = (startIndex + itemsperpage) > listSize ? listSize : (startIndex + itemsperpage);
        return dataList.subList(startIndex, endIndex);
    }

    /**
     * sorts TicketList
     * @param dataList dataList
     * @param parameter parameter
     * @param order order
     * @return List of TicketList
     */
    public List<TicketData> sortTicketList(List<TicketData> dataList, String parameter, String order) {
        dataList = new ArrayList<>(dataList);
        switch (parameter.toLowerCase()) {
            case STATUS:
                sortTicketData(dataList, order, Comparator.comparing(TicketData::getStatus));
                break;
            case NUMBER:
                sortTicketData(dataList, order, Comparator.comparing(TicketData::getTicketNumber));
                break;
            case DATE:
                sortTicketData(dataList, order, Comparator.comparing(TicketData::getLastUpdate));
                break;
            default:
                break;
        }
        return dataList;
    }

    private void sortTicketData(List<TicketData> dataList, String order, Comparator<TicketData> comparing) {
        if (order.equalsIgnoreCase(DESC)) {
            dataList.sort(comparing.reversed());
        } else {
            dataList.sort(comparing);
        }
    }

    @Override
    public List<Appointment> sortAppointmentList(List<Appointment> dataList, String parameter, String order) {
        dataList = new ArrayList<>(dataList);
        switch (parameter.toLowerCase()) {
            case STATUS:
                sortAppointment(dataList, order, Comparator.comparing(Appointment::getStatus));
                break;
            case DATE:
                sortAppointmentByDate(dataList, order);
                break;
            case BRANCH:
                sortAppointment(dataList, order, Comparator.comparing(Appointment::getBranch));
                break;
            default:
                break;
        }
        return dataList;
    }

    private void sortAppointmentByDate(List<Appointment> dataList, String order) {
        if (order.equalsIgnoreCase(DESC)) {
            dataList.sort(Comparator.comparing(Appointment::getDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed());
        } else {
            dataList.sort(Comparator.comparing(Appointment::getDate, Comparator.nullsLast(Comparator.naturalOrder())));
        }
    }

    private void sortAppointment(List<Appointment> dataList, String order, Comparator<Appointment> comparing) {
        if (order.equalsIgnoreCase(DESC)) {
            dataList.sort(comparing.reversed());
        } else {
            dataList.sort(comparing);
        }
    }

    @Override
    public List<LegalInquiryHDR> sortLegalConsultationList(List<LegalInquiryHDR> dataList, String parameter, String order) {
        dataList = new ArrayList<>(dataList);
        switch (parameter.toLowerCase()) {
            case STATUS:
                sortLegalInquiryHDR(dataList, order, Comparator.comparing(LegalInquiryHDR::getSrStDesc));
                break;
            case NUMBER:
                sortLegalInquiryHDR(dataList, order, Comparator.comparing(LegalInquiryHDR::getSrId));
                break;
            case DATE:
                sortLegalInquiryHDRByDate(dataList, order);
                break;
            default:
                break;
        }
        return dataList;
    }

    private void sortLegalInquiryHDRByDate(List<LegalInquiryHDR> dataList, String order) {
        if (order.equalsIgnoreCase(DESC)) {
            dataList.sort(Comparator.comparing(LegalInquiryHDR::getSrCrDate,Comparator.comparing(SagiaDateData::getDate)).reversed());
        } else {
            dataList.sort(Comparator.comparing(LegalInquiryHDR::getSrCrDate,Comparator.comparing(SagiaDateData::getDate)));
        }
    }

    private void sortLegalInquiryHDR(List<LegalInquiryHDR> dataList, String order, Comparator<LegalInquiryHDR> comparing) {
        if (order.equalsIgnoreCase(DESC)) {
            dataList.sort(comparing.reversed());
        } else {
            dataList.sort(comparing);
        }
    }

    /**
     * retrieves DashboardBranchesListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of BranchData
     */
    public List<BranchData> getDashboardBranchesListForPage(List<BranchData> dataList, int pageNumber, int itemsperpage, int listSize) {
        int startIndex = itemsperpage * (pageNumber - 1);
        int endIndex = (startIndex + itemsperpage) > listSize ? listSize : (startIndex + itemsperpage);
        return dataList.subList(startIndex, endIndex);
    }
}
