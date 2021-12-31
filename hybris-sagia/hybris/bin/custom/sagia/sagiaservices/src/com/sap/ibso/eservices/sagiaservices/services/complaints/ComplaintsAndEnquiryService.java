
package com.sap.ibso.eservices.sagiaservices.services.complaints;

import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ComplaintsAndEnquiryService
 */
public class ComplaintsAndEnquiryService extends AbstractSagiaService<ComplaintsAndEnquiryHdrsData> {
	
	private static final String OPEN_STATUS = "Open";
	/**
	 * retrieves ComplaintsList
	 * @return list with all complaints
	 */
    public final Collection<ComplaintsAndEnquiryHdrsData> getComplaintsList() {
		return getCollection(ComplaintsAndEnquiryHdrsData.class);
    }

	/**
	 * retrieves a complaint by id, with all the navigation properties expanded
	 * @return ComplaintsAndEnquiryHdrsData
	 * @param srId id of the complaint
	 */
	public ComplaintsAndEnquiryHdrsData getComplaintBy(String srId) {
        return get(ComplaintsAndEnquiryHdrsData.class, srId, new QueryOptionsBuilder()
				.expand(createComplaintExpandQuery())
				.build());
	}

	/**
	 * call service to create new complaint
	 * @param complaintFormData complaintFormData
	 * @param supportedAttachments supportedAttachments
	 */
	public void createComplaint(ComplaintFormData complaintFormData, Collection<CustomizingGetData> supportedAttachments) {
		save(ComplaintsEnquiryConverter.fromFormData(complaintFormData, supportedAttachments));
	}


	/**
	 * call service to update complaint
	 * @param updatableDetails updatableDetails
	 * @param complaintId complaintId
	 */
	public void updateComplaint(UpdatableComplaintDetails updatableDetails, String complaintId) {
		save(ComplaintsEnquiryConverter.fromUpdatableDetails(updatableDetails, complaintId));
	}

	/**
	 * creates ComplaintExpandQuery
	 * @return String
	 */
	private String createComplaintExpandQuery() {
		return Arrays.asList(ComplaintsExpandableEntities.values())
				.stream()
				.map(ComplaintsExpandableEntities::navEntity)
				.collect(Collectors.joining(","));
	}

	/**
	 * retrieves OpenTicketsFrom
	 * @param tickets tickets
	 * @return List of TicketData
	 */
	public List<TicketData> getOpenTicketsFrom(List<TicketData> tickets) {
		return tickets.stream()
					  .filter(ticket -> ticket.getStatus().equalsIgnoreCase(OPEN_STATUS))
					  .collect(Collectors.toList());
	}
}
