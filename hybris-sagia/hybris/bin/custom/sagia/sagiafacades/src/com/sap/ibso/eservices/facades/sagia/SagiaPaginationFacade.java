package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.Appointment;
import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.LegalInquiryHDR;
import com.sap.ibso.eservices.facades.serviceRequests.data.ServiceRequestData;

import java.util.List;

/**
 * SagiaPaginationFacade
 */
public interface SagiaPaginationFacade {

     String STATUS = "status";
     String NUMBER = "number";
     String DATE = "date";
     String NAME = "name";
     String DESC = "desc";
     String AMOUNT = "amount";
     String BRANCH = "branch";
    
    /**
     * retrieves PagesNumber
     * @param listSize listSize
     * @param itemsPerPage itemsPerPage
     * @return page number
     */
    int getPagesNumber(double listSize, double itemsPerPage);

    /**
     * retrieves ServiceListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of ServiceRequestData
     */
    List<ServiceRequestData> getServiceListForPage(List<ServiceRequestData> dataList, int pageNumber, int itemsperpage, int listSize);

    /**
     * retrieves PaymentListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of PaymentData
     */
    List<PaymentData> getPaymentListForPage(List<PaymentData> dataList, int pageNumber, int itemsperpage, int listSize);

    /**
     * retrieves TicketListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of TicketData
     */
    List<TicketData> getTicketListForPage(List<TicketData> dataList, int pageNumber, int itemsperpage, int listSize);

    /**
     * retrieves AppointmentListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of Appointment
     */
    List<Appointment> getAppointmentListForPage(List<Appointment> dataList, int pageNumber, int itemsperpage, int listSize);

    /**
     * retrieves LegalConsultationListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of LegalInquiryHDR
     */
    List<LegalInquiryHDR> getLegalConsultationListForPage(List<LegalInquiryHDR> dataList, int pageNumber, int itemsperpage, int listSize);

    /**
     * sorts ServicesList
     * @param serviceList serviceList
     * @param parameter parameter
     * @param order order
     * @return List of ServiceRequestData
     */
    List<ServiceRequestData> sortServicesList(List<ServiceRequestData> serviceList, String parameter, String order);

    /**
     * sorts PaymentList
     * @param dataList dataList
     * @param parameter parameter
     * @param order order
     * @return List of PaymentData
     */
    List<PaymentData> sortPaymentList(List<PaymentData> dataList, String parameter, String order);

    /**
     * sorts TicketList
     * @param dataList dataList
     * @param parameter parameter
     * @param order order
     * @return List of TicketData
     */
    List<TicketData> sortTicketList(List<TicketData> dataList, String parameter, String order);

    /**
     * sorts AppointmentList
     * @param dataList dataList
     * @param parameter parameter
     * @param order order
     * @return List of Appointment
     */
    List<Appointment> sortAppointmentList(List<Appointment> dataList, String parameter, String order);

    /**
     * sorts LegalConsultationList
     * @param dataList dataList
     * @param parameter parameter
     * @param order order
     * @return List of LegalInquiryHDR
     */
    List<LegalInquiryHDR> sortLegalConsultationList(List<LegalInquiryHDR> dataList, String parameter, String order);

    /**
     * retrieves DashboardBranchesListForPage
     * @param dataList dataList
     * @param pageNumber pageNumber
     * @param itemsperpage itemsperpage
     * @param listSize listSize
     * @return List of BranchData
     */
    List<BranchData> getDashboardBranchesListForPage(List<BranchData> dataList, int pageNumber, int itemsperpage, int listSize);
}
