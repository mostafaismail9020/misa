package com.sap.ibso.eservices.facades.sagia.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.sap.ibso.eservices.facades.data.TicketData;
import com.sap.ibso.eservices.facades.populators.tickets.TicketsDataPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaComplaintFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CategorizationSchemaGetListData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.CategorizationSchemaGetListService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.CategorizationSchemaContactUsListService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.ComplaintsAndEnquiryService;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;

/**
 * DefaultSagiaComplaintFacade
 */
public class DefaultSagiaComplaintFacade implements SagiaComplaintFacade {
	private ComplaintsAndEnquiryService complaintsAndEnquiryService;
	private CategorizationSchemaGetListService categorizationSchemaGetListService;
	private CategorizationSchemaContactUsListService categorizationSchemaContactUsListService;
	/**
	 * @param categorizationSchemaContactUsListService the categorizationSchemaContactUsListService to set
	 */
	public void setCategorizationSchemaContactUsListService(
			CategorizationSchemaContactUsListService categorizationSchemaContactUsListService) {
		this.categorizationSchemaContactUsListService = categorizationSchemaContactUsListService;
	}

	private CustomizationListService customizationListService;
	private TicketsDataPopulator ticketsDataPopulator;
	
	@Override
	public ComplaintFormData getComplaintFormData() {
		Collection<CategorizationSchemaGetListData> schema = categorizationSchemaGetListService.readCategorizationSchema();
		Collection<CustomizingGetData> branches = customizationListService.readBranches();
		Collection<CustomizingGetData> attachments = customizationListService.readComplaintsSupportingAttachments();

		ComplaintFormData complaintFormData = new ComplaintFormData();
		complaintFormData.setEnquiryTypes(filterCategoriesBy(schema, "1"));
		complaintFormData.setCategoryOne(filterCategoriesBy(schema, "2"));
		complaintFormData.setCategoryTwo(filterCategoriesBy(schema, "3"));
		complaintFormData.setBranches(branches);
		complaintFormData.setAttachments(attachments);
		
		return complaintFormData;
	}
	
	@Override
	public ContactUsFormData getContactUsFormData() {
		Collection<CategorizationSchemaGetListData> schema = categorizationSchemaContactUsListService.readCategorizationSchema();
		//Collection<CustomizingGetData> branches = customizationListService.readBranches();
		//Collection<CustomizingGetData> attachments = customizationListService.readComplaintsSupportingAttachments();

		ContactUsFormData complaintFormData = new ContactUsFormData();
		complaintFormData.setEnquiryTypes(filterCategoriesBy(schema, "1"));
		complaintFormData.setCategoryOne(filterCategoriesBy(schema, "2"));
		complaintFormData.setCategoryTwo(filterCategoriesBy(schema, "3"));
		//complaintFormData.setBranches(branches);
		//complaintFormData.setAttachments(attachments);
		
		return complaintFormData;
	}
	
	private Collection<CategorizationSchemaGetListData> filterCategoriesBy(
			Collection<CategorizationSchemaGetListData> categorizationSchema, String categoryLevel) {
		return categorizationSchema.stream()
				.filter(category -> category.getCatLevel().equals(categoryLevel))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<TicketData> getTickets() {
		Collection<ComplaintsAndEnquiryHdrsData> complaintsAndEnquiryHDRModels = complaintsAndEnquiryService.getComplaintsList();
		List<TicketData> tickets = new ArrayList<>();
		if(complaintsAndEnquiryHDRModels != null) {
			for (ComplaintsAndEnquiryHdrsData complaint : complaintsAndEnquiryHDRModels) {
				TicketData ticketData = new TicketData();
				ticketsDataPopulator.populate(complaint, ticketData);
				tickets.add(ticketData);
			}
		}
		return tickets;
	}
	
	@Override
	public int getOpenTicketsSize(List<TicketData> tickets) {
		return complaintsAndEnquiryService.getOpenTicketsFrom(tickets).size();
	}

	/**
	 * @param complaintsAndEnquiryService
	 */
	public void setComplaintsAndEnquiryService(ComplaintsAndEnquiryService complaintsAndEnquiryService) {
		this.complaintsAndEnquiryService = complaintsAndEnquiryService;
	}

	/**
	 * @param categorizationSchemaGetListService
	 */
	public void setCategorizationSchemaGetListService(
			CategorizationSchemaGetListService categorizationSchemaGetListService) {
		this.categorizationSchemaGetListService = categorizationSchemaGetListService;
	}

	/**
	 * @param customizationListService
	 */
	public void setCustomizationListService(CustomizationListService customizationListService) {
		this.customizationListService = customizationListService;
	}

	/**
	 * @param ticketsDataPopulator
	 */
	public void setTicketsDataPopulator(TicketsDataPopulator ticketsDataPopulator)	{
		this.ticketsDataPopulator = ticketsDataPopulator;
	}
}
