package com.sap.ibso.eservices.storefront.controllers.pages;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.comments.model.CommentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import com.sap.ibso.eservices.storefront.forms.ContactTicketForm;
import com.sap.ibso.eservices.storefront.forms.SagiaServiceRequestForm;
import com.sap.ibso.eservices.core.enums.IncidentCategory;
import com.sap.ibso.eservices.core.enums.ServiceCategory;
import com.sap.ibso.eservices.core.enums.Priority;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.core.Registry;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.i18n.I18NService;

import javax.annotation.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.UUID;
import java.util.EnumSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
@RequestMapping(value = "/potentialOpportunity")
public class MyPotentialOpportunityController extends SagiaAbstractPageController {

	private static final Logger LOG = Logger.getLogger(MyPotentialOpportunityController.class);

	private static final String POTENTIAL_OPPORTUNITY_CMS_PAGE = "potential-opportunity";
	private static final String PATH_VARIABLE_PATTERN = "/{ticketId}";
	private static final String SERVICE_REQUEST_CMS_PAGE = "service-request";
        private static final String THIS_CONTROLLER_REDIRECTION_URL = "/potentialOpportunity/";
	private static final String CA_1 = "CA_1";
	
        @Resource(name = "sagiaUserService")
	private SagiaUserService sagiaUserService;

	@Resource(name = "investSaudiProductFacade")
	private InvestSaudiProductFacade investSaudiProductFacade;

	@Resource(name = "contactTicketEventStrategy")
	private TicketEventStrategy ticketEventStrategy;

	@Resource(name = "modelService")
	private ModelService modelService;

        @Resource(name = "i18nService")
        private I18NService i18nService;


	@RequestMapping(value = PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String getMyPotentialOpportunityPage(@PathVariable("ticketId") final String ticketId, final Model model)
			throws CMSItemNotFoundException {

		ContactTicketModel contactTicketDetails = sagiaUserService.getContactTicketForTicketId(ticketId);
		model.addAttribute("comments", contactTicketDetails.getComments());
		model.addAttribute("contactTicketDetails", contactTicketDetails);
		if(StringUtils.isNotEmpty(contactTicketDetails.getOpportunityCode())) {
			ProductData opportunityProductDetails = investSaudiProductFacade.getProductForCode(contactTicketDetails.getOpportunityCode());
			model.addAttribute("opportunityDetails", opportunityProductDetails);
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(POTENTIAL_OPPORTUNITY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(POTENTIAL_OPPORTUNITY_CMS_PAGE));
		return getViewForPage(model);
	}


	@RequestMapping(value = PATH_VARIABLE_PATTERN)
	public String setCommentsToContactTicket(@ModelAttribute("contactTicketForm") ContactTicketForm contactTicketForm, 
			final Model model, @PathVariable("ticketId") final String ticketId)
			throws CMSItemNotFoundException {

//		List<CommentModel> comments = new ArrayList<>();
//		ContactTicketModel contactTicketModel = sagiaUserService.getContactTicketForTicketId(ticketId);
//		CsTicketModel ticket = (CsTicketModel)contactTicketModel;
//		CsCustomerEventModel comment = ticketEventStrategy.createCreationEventForTicket(ticket,
//				CsEventReason.FIRSTCONTACT, CsInterventionType.TICKETMESSAGE, contactTicketForm.getComment());
//		comments.add(comment);
//		contactTicketModel.setComments(comments);
		
		ContactTicketModel contactTicket = sagiaUserService.addContactTicketComments(ticketId, contactTicketForm.getComment());
		if (null != contactTicket) {
			List<CommentModel> comments = contactTicket.getComments();
			Collections.reverse(comments);
			modelService.save(contactTicket);
			model.addAttribute("comments", comments);
			model.addAttribute("contactTicketDetails", contactTicket);
			if (StringUtils.isNotEmpty(contactTicket.getOpportunityCode())) {
				ProductData opportunityProductDetails = investSaudiProductFacade.getProductForCode(contactTicket.getOpportunityCode());
				model.addAttribute("opportunityDetails", opportunityProductDetails);
			}
		}
		
		storeCmsPageInModel(model, getContentPageForLabelOrId(POTENTIAL_OPPORTUNITY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(POTENTIAL_OPPORTUNITY_CMS_PAGE));
		
		return getViewForPage(model);
	}
	
	@RequestMapping(value = "{ticketId}/serviceRequest", method = {RequestMethod.GET})
	@RequireHardLogIn
	public String getMyServiceRequestPage(@PathVariable("ticketId") final String ticketId, @ModelAttribute("sagiaServiceRquestForm") SagiaServiceRequestForm sagiaServiceRquestForm, final Model model)
			throws CMSItemNotFoundException {

		final EnumerationService enumService = (EnumerationService) Registry.getApplicationContext().getBean("enumerationService");
		List<IncidentCategory> incidentCategories = enumService.getEnumerationValues(com.sap.ibso.eservices.core.enums.IncidentCategory.class);
		List<ServiceCategory> serviceCategories = enumService.getEnumerationValues(com.sap.ibso.eservices.core.enums.ServiceCategory.class);
		List<Priority> priorities = enumService.getEnumerationValues(com.sap.ibso.eservices.core.enums.Priority.class);
		
		model.addAttribute("ticketId", ticketId);
		model.addAttribute("incidentCategories", incidentCategories);
		model.addAttribute("serviceCategories", serviceCategories);
		model.addAttribute("priorities", priorities);
		
		model.addAttribute("sagiaServiceRquestForm", sagiaServiceRquestForm);
		storeCmsPageInModel(model, getContentPageForLabelOrId(SERVICE_REQUEST_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SERVICE_REQUEST_CMS_PAGE));
		return getViewForPage(model);
	}
	
	@RequestMapping(value = "{ticketId}/serviceRequest",method = RequestMethod.POST)
	public String submitServiceRequestForm(@PathVariable("ticketId") final String ticketId, final Model model, @ModelAttribute("sagiaServiceRquestForm") final SagiaServiceRequestForm sagiaServiceRquestForm, final BindingResult bindingResult,
			final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
			
	{
		final EnumerationService enumService = (EnumerationService) Registry.getApplicationContext().getBean("enumerationService");
		List<ServiceCategory> serviceCategories = enumService.getEnumerationValues(com.sap.ibso.eservices.core.enums.ServiceCategory.class);
		/* Defaulting Service Category to CA_1 */
		if(serviceCategories != null) {
			for (ServiceCategory serviceCategory : serviceCategories) {
				if(serviceCategory.getCode() == CA_1) {
					sagiaServiceRquestForm.setServiceCategory(serviceCategory);
				}
			}
		}
		
		ServiceRequestModel serviceRequest = new ServiceRequestModel();
		serviceRequest.setId(UUID.randomUUID().toString());
    	if(null != sagiaServiceRquestForm.getSubject()) {
    		serviceRequest.setSubject(sagiaServiceRquestForm.getSubject(), i18nService.getCurrentLocale());
    	}if(null != sagiaServiceRquestForm.getDescription()) {
    		serviceRequest.setDescription(sagiaServiceRquestForm.getDescription(), i18nService.getCurrentLocale());
    	}if(null != sagiaServiceRquestForm.getIncidentCategory()) {
    		serviceRequest.setIncidentCategory(sagiaServiceRquestForm.getIncidentCategory());
    	}if(null != sagiaServiceRquestForm.getServiceCategory()) {
    		serviceRequest.setServiceCategory(sagiaServiceRquestForm.getServiceCategory());
    	}if(null != sagiaServiceRquestForm.getPriority()) {
    		serviceRequest.setPriority(sagiaServiceRquestForm.getPriority());
    	}
		boolean attachRequestToTicket = sagiaUserService.attachServiceRequestToContactTicket(serviceRequest, ticketId);
		LOG.debug("attachRequestToTicket : "+attachRequestToTicket);
		LOG.debug("Hit Successfully POST Service Request Controller");
		if(Objects.nonNull(sagiaServiceRquestForm)) {
			LOG.debug("Subject : "+sagiaServiceRquestForm.getSubject());
			LOG.debug("Description : "+sagiaServiceRquestForm.getDescription());
			LOG.debug("Incident Category : "+sagiaServiceRquestForm.getIncidentCategory());
			LOG.debug("ServiceCategory : "+sagiaServiceRquestForm.getServiceCategory());
			LOG.debug("Priority : "+sagiaServiceRquestForm.getPriority());
		}
		if(attachRequestToTicket) {
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "Service Request added successfully to Contact Ticket");
		}
		return "redirect:"+THIS_CONTROLLER_REDIRECTION_URL+ticketId;
	}

}
