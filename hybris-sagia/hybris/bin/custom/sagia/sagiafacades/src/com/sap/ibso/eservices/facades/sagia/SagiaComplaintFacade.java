package com.sap.ibso.eservices.facades.sagia;

import java.util.List;

import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;

/**
 * SagiaComplaintFacade
 */
public interface SagiaComplaintFacade {

    /**
     * Retrieves ComplaintFormData
     * @return ComplaintFormData
     */
    ComplaintFormData getComplaintFormData();

    /**
     * Retrieves Tickets
     * @return List of TicketData
     */
    List<TicketData> getTickets();

    /**
     * Retrieves OpenTicketsSize
     * @param tickets tickets
     * @return integer
     */
    int getOpenTicketsSize(List<TicketData> tickets);
    
    /**
     * Retrieves ComplaintFormData
     * @return ComplaintFormData
     */
    ContactUsFormData getContactUsFormData();

}
