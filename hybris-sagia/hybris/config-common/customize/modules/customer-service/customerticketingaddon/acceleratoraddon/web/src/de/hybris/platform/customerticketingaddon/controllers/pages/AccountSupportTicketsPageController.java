/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.customerticketingaddon.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUserGroupData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerticketingaddon.constants.CustomerticketingaddonConstants;
import de.hybris.platform.customerticketingaddon.forms.SupportTicketForm;
import de.hybris.platform.customerticketingaddon.forms.BDSupportTicketForm;
import de.hybris.platform.customerticketingfacades.TicketFacade;
import de.hybris.platform.customerticketingfacades.data.StatusData;
import de.hybris.platform.customerticketingfacades.data.TicketCategory;
import de.hybris.platform.customerticketingfacades.data.TicketData;
import de.hybris.platform.ticket.service.UnsupportedAttachmentException;

import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.facades.InvestSaudiContactFacade;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import com.sap.security.core.server.csi.XSSEncoder;
import java.util.Set;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import com.sap.ibso.eservices.core.model.TicketQuestionModel;
import de.hybris.platform.customerticketingfacades.data.TicketQuestionData;
import de.hybris.platform.customerticketingaddon.controllers.CustomerticketingaddonControllerConstants;
import com.sap.ibso.eservices.core.sagia.services.TicketConfigurationService ;
import com.sap.ibso.eservices.core.sagia.services.SagiaTicketSectorService ;
import com.sap.ibso.eservices.core.model.TicketSectorModel;
import com.google.gson.Gson;
import com.google.common.reflect.TypeToken;


import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import de.hybris.platform.customerticketingfacades.data.TicketConfigurationData;

import com.sap.ibso.eservices.core.model.TicketSectorModel;
import de.hybris.platform.customerticketingfacades.data.TicketSectorData;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;

/**
 * Controller for Customer Support tickets.
 */
@Controller
@RequestMapping("/my-account")
public class AccountSupportTicketsPageController extends AbstractSearchPageController
{
	private static final Logger LOG = Logger.getLogger(AccountSupportTicketsPageController.class);

	// CMS Pages"WOAGUserGroup"
	
	private static final String SUPPORT_TICKET_CODE_PATH_VARIABLE_PATTERN = "{ticketId:.*}";
	private static final String REDIRECT_TO_SUPPORT_TICKETS_PAGE = REDIRECT_PREFIX + "/my-account/support-tickets";
	private static final String BUSINESS_DEVELOPMENT_USER_GROUP = "BDUserGroup";
	private static final String NIPC_USER_GROUP = "NIPCUserGroup";
	private static final String WOAG_USER_GROUP = "WOAGUserGroup";
	private static final String MARCOM_USER_GROUP = "marcomUserGroup";
	private static final String DEFAULT_COUNTRY_CODE = "SA";
	private static final String TICKET_CATEGORY_OPPORTUNITYSUBMISSION = "OPPORTUNITYSUBMISSION";

	@Resource(name = "csAttachmentUploadMaxSize")
	private long maxUploadSizeValue;

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "defaultTicketFacade")
	private TicketFacade ticketFacade;

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "validator")
	private Validator validator;

	@Resource(name = "allowedUploadedFormats")
	private String allowedUploadedFormats;
	
   @Resource(name = "userService")
   private UserService userService;
   
   @Resource(name = "i18NFacade")
   private I18NFacade i18NFacade;
  
   @Resource(name = "ticketConfigurationService")
   private TicketConfigurationService ticketConfigurationService;
   
   
   @Resource(name = "sagiaTicketSectorService")
   private SagiaTicketSectorService sagiaTicketSectorService;
    
    
    @Resource(name = "ticketQuestionConverter")
	private Converter<TicketQuestionModel, TicketQuestionData> ticketQuestionConverter;
    
    @Resource(name = "ticketConfigurationConverter")
	private Converter<TicketConfigurationModel, TicketConfigurationData> ticketConfigurationConverter;
    
    
    
    @Resource(name = "ticketSectorConverter")
	private Converter<TicketSectorModel, TicketSectorData> ticketSectorConverter;
    

	private final String[] DISALLOWED_FIELDS = new String[] {};

	@InitBinder
	public void initBinder(final WebDataBinder binder)
	{
		binder.setDisallowedFields(DISALLOWED_FIELDS);
	}

	public enum OppType
	{
		// Constant names cannot be changed due to their usage in dependant extensions, thus nosonar
		MyOpportunity, // NOSONAR
		AllOpportunity // NOSONAR
	}
	/**
	 * Lists all tickets
	 *
	 * @param pageNumber
	 * @param showMode
	 * @param sortCode
	 * @param model
	 * @param ticketAdded
	 * @return View String
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/support-tickets", method = RequestMethod.GET)
	@RequireHardLogIn
	public String supportTickets(@RequestParam(value = "page", defaultValue = "0") final int pageNumber,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "sec", required = false) final String sec,
			@RequestParam(value = "oppType", defaultValue = "MyOpportunity") final OppType oppType,
			@RequestParam(value = "ticketAdded", required = false, defaultValue = "false") final boolean ticketAdded,
			final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(CustomerticketingaddonConstants.SUPPORT_TICKETS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(CustomerticketingaddonConstants.SUPPORT_TICKETS_PAGE));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				accountBreadcrumbBuilder.getBreadcrumbs(CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_HISTORY));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		final PageableData pageableData = createPageableData(pageNumber, 10, sortCode, showMode);
		
		boolean nipcMember = false;
		boolean bduMember = false;
		String b2bUnit = "";
		SearchPageData<TicketData> searchPageData = null;
		UserModel currentUser = userService.getCurrentUser();
		if(currentUser != null) {
			Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
			for(PrincipalGroupModel curGroup : curGroups) {
				if(BUSINESS_DEVELOPMENT_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					bduMember = true;
					model.addAttribute("bdUserGroup",curGroup.getUid());
				}
				if(NIPC_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					nipcMember = true;
					model.addAttribute("nipcUserGroup",curGroup.getUid());
				}
			}
			if(currentUser instanceof B2BCustomerModel) {
				B2BUnitModel b2bUnitModel = ((B2BCustomerModel)currentUser).getDefaultB2BUnit();
				if(null!=b2bUnitModel)
				{
					b2bUnit = b2bUnitModel.getUid();
				}
			}
			model.addAttribute("isNipcMember",nipcMember);
			
//			if(nipcMember) {
//				model.addAttribute("oppType",oppType);
//				model.addAttribute("sec",sec);
//			}
			
		}
		//LOG.info("**********b2bUnit: "+b2bUnit+" ^^^^ nipcMember: "+nipcMember+" ****** oppType: "+oppType+" ^^^^sector: "+sec);
		if(nipcMember && StringUtils.isNotBlank(b2bUnit)) {
			if(oppType.MyOpportunity == oppType) {
				searchPageData = ticketFacade.getTicketsByB2BUnit(pageableData, b2bUnit);
			}else {
				searchPageData = ticketFacade.getTicketsByTicketCategory(pageableData, TICKET_CATEGORY_OPPORTUNITYSUBMISSION, sec);
			}
			//searchPageData = ticketFacade.getTicketsByB2BUnit(pageableData, b2bUnit);
			model.addAttribute("oppType",oppType);
			model.addAttribute("sec",sec);
			
		}
		else if(bduMember && StringUtils.isNotBlank(b2bUnit)){
			searchPageData = ticketFacade.getTickets(pageableData);

		}
		else if(StringUtils.isNotBlank(b2bUnit)){
			searchPageData = ticketFacade.getTicketsByB2BUnit(pageableData, b2bUnit);
			
		}else {
			searchPageData = ticketFacade.getTickets(pageableData);
		}
		
		//final SearchPageData<TicketData> searchPageData = ticketFacade.getTickets(pageableData);

		populateModel(model, searchPageData, showMode);
		
		if (ticketAdded)
		{
			GlobalMessages.addConfMessage(model, CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_ADDED);
		}

		return getViewForPage(model);
	}

	@InitBinder
	public void init(final WebDataBinder binder)
	{
		binder.setBindEmptyMultipartFiles(false);
	}

	/**
	 * Used for retrieving page to create a customer support ticket.
	 *
	 * @param model
	 * @return View String
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/add-support-ticket", method = RequestMethod.GET)
	@RequireHardLogIn
	public String addSupportTicket(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(CustomerticketingaddonConstants.ADD_SUPPORT_TICKET_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(CustomerticketingaddonConstants.ADD_SUPPORT_TICKET_PAGE));

		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getBreadcrumbs(CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_ADD));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new SupportTicketForm());
		model.addAttribute(CustomerticketingaddonConstants.MAX_UPLOAD_SIZE, Long.valueOf(maxUploadSizeValue));
		model.addAttribute(CustomerticketingaddonConstants.MAX_UPLOAD_SIZE_MB,
				FileUtils.byteCountToDisplaySize(maxUploadSizeValue));

		model.addAttribute("regions", i18NFacade.getRegionsForCountryIso(DEFAULT_COUNTRY_CODE));

		

		//Load the list of sector based on the userGroup of the current User.
		
		List<TicketConfigurationModel>  listConfiguration = ticketConfigurationService.getActiveServiceRequestConfiguration();
		final List<TicketConfigurationData> listConfigurationData = Converters.convertAll(listConfiguration, ticketConfigurationConverter);
		model.addAttribute("subSectors", listConfigurationData);
		
		


		try
		{
			model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_ASSOCIATED_OBJECTS,
					ticketFacade.getAssociatedToObjects());
			model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_CATEGORIES, ticketFacade.getTicketCategories());
			
			UserModel currentUser = userService.getCurrentUser();
	
			if(currentUser != null) {
				Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
				for(PrincipalGroupModel curGroup : curGroups) {
					if(BUSINESS_DEVELOPMENT_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
						model.addAttribute("bdUserGroup",curGroup.getUid());
						model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new BDSupportTicketForm());
						
						/*
						 * List<TicketConfigurationModel> listConfiguration2 =
						 * ticketConfigurationService.getActiveOpportunityConfiguration(); final
						 * List<TicketConfigurationData> listConfigurationData2 =
						 * Converters.convertAll(listConfiguration2, ticketConfigurationConverter);
						 * model.addAttribute("subSectors", listConfigurationData2);
						 */
						
						List<TicketSectorModel>  listSector = sagiaTicketSectorService.getAllTicketSector();
						List<TicketSectorData> listSectorData = Converters.convertAll(listSector, ticketSectorConverter);
						model.addAttribute("sectors", listSectorData);
						
					}
					if(NIPC_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
						model.addAttribute("nipcUserGroup",curGroup.getUid());
						model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new BDSupportTicketForm());
						List<TicketSectorModel>  listSector = sagiaTicketSectorService.getAllTicketSector();
						List<TicketSectorData> listSectorData = Converters.convertAll(listSector, ticketSectorConverter);
						model.addAttribute("sectors", listSectorData);
					}

				}
			}
			
		}
		catch (final UnsupportedOperationException ex)
		{
			LOG.error(ex.getMessage(), ex);
		}


		return getViewForPage(model);
	}
	
	
	
	@RequestMapping(path = "/getSubSectors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getsubSectors(@RequestParam(value = "sector") final String sector) throws Exception {
        
	    List<TicketConfigurationModel>  listSubSector = sagiaTicketSectorService.getAllTicketConfigurationForSectorCode(sector);
		final List<TicketConfigurationData> listSubSectorData = Converters.convertAll(listSubSector, ticketConfigurationConverter);
	 
        return new Gson().toJson(listSubSectorData);
    }

	
	

	/**
	 * Used for retrieving page to create a customer support ticket.
	 *
	 * @param model
	 * @return View String
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/questions-per-sector", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getQuestionsPerSector(@RequestParam(value = "sector") final String sector,
								   final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new SupportTicketForm());

		List<TicketQuestionModel>  listQuestion = ticketConfigurationService.getQuestionsByConfigurationCode(sector);
		
		final List<TicketQuestionData> listQuestionData = Converters.convertAll(listQuestion, ticketQuestionConverter);

		
		model.addAttribute("questions", listQuestionData);

		return CustomerticketingaddonControllerConstants.Views.Fragments.Account.SupportTicketQuestionsFragment;
	}

	/**
	 * Creates a ticket.
	 *
	 * @param supportTicketForm
	 * @param bindingResult
	 * @return View String
	 */
	@RequestMapping(value = "/add-support-ticket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequireHardLogIn
	@ResponseBody
	public ResponseEntity<List<Map<String, String>>> addSupportTicket(final BDSupportTicketForm supportTicketForm,
			final BindingResult bindingResult, @RequestParam(value = "questions") final String questions)
	{
		
		boolean isBDUser= false;
		boolean isNIPCUser= false;
		UserModel currentUser = userService.getCurrentUser();
		if(currentUser != null) {
			Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
			for(PrincipalGroupModel curGroup : curGroups) {
				if(BUSINESS_DEVELOPMENT_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					isBDUser = true;
				}
				if(NIPC_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					isNIPCUser = true;
				}
			}
		}
		
		
		if (isBDUser)
		{
			validator.validate(supportTicketForm, bindingResult);
		}else if(isNIPCUser)
		{
			validator.validate(supportTicketForm, bindingResult);
		}
		else
		{
			final SupportTicketForm supportTicket = new SupportTicketForm();

			supportTicket.setMessage(supportTicketForm.getMessage());
			supportTicket.setSubject(supportTicketForm.getSubject());
			supportTicket.setQuestion2(supportTicketForm.getQuestion2());
			supportTicket.setQuestion3(supportTicketForm.getQuestion3());
			supportTicket.setQuestion4(supportTicketForm.getQuestion4());
			supportTicket.setId(supportTicketForm.getId());
			supportTicket.setStatus(supportTicketForm.getStatus());
			supportTicket.setAssociatedTo(supportTicketForm.getAssociatedTo());
			supportTicket.setFiles(supportTicketForm.getFiles());
			supportTicket.setTicketCategory(supportTicketForm.getTicketCategory());



			validator.validate(supportTicket, bindingResult);
		}
		if (bindingResult.hasErrors())
		{
			final List<Map<String, String>> list = buildErrorMessagesMap(bindingResult);
			list.add(buildMessageMap(CustomerticketingaddonConstants.FORM_GLOBAL_ERROR_KEY,
					CustomerticketingaddonConstants.FORM_GLOBAL_ERROR));

			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
		
		

		try
		{
			ticketFacade.createTicket(this.populateTicketData(supportTicketForm, questions));
		}
		catch (final UnsupportedAttachmentException usAttEx)
		{
			LOG.error(usAttEx.getMessage(), usAttEx);
			final Map<String, String> map = Maps.newHashMap();
			map.put(CustomerticketingaddonConstants.SUPPORT_TICKET_TRY_LATER, getMessageSource()
					.getMessage(CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_ATTACHMENT_BLOCK_LISTED, new Object[]
			{ allowedUploadedFormats }, getI18nService().getCurrentLocale()));
			return new ResponseEntity<>(Lists.newArrayList(map), HttpStatus.BAD_REQUEST);
		}
		catch (final RuntimeException rEX)
		{
			final Map<String, String> map = Maps.newHashMap();
			LOG.error(rEX.getMessage(), rEX);

			map.put(CustomerticketingaddonConstants.SUPPORT_TICKET_TRY_LATER, getMessageSource().getMessage(
					CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_TRY_LATER, null, getI18nService().getCurrentLocale()));
			return new ResponseEntity<>(Lists.newArrayList(map), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * Get Ticket Details.
	 *
	 * @param ticketId
	 * @param model
	 * @param redirectModel
	 * @param ticketUpdated
	 * @return View String
	 * @throws CMSItemNotFoundException
	 */
	@RequestMapping(value = "/support-ticket/" + SUPPORT_TICKET_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String getSupportTicket(@PathVariable("ticketId") final String ticketId, final Model model,
			@RequestParam(value = "ticketUpdated", required = false, defaultValue = "false") final boolean ticketUpdated,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(CustomerticketingaddonConstants.UPDATE_SUPPORT_TICKET_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(CustomerticketingaddonConstants.UPDATE_SUPPORT_TICKET_PAGE));

		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getBreadcrumbs(CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_UPDATE));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new SupportTicketForm());
		model.addAttribute(CustomerticketingaddonConstants.MAX_UPLOAD_SIZE, Long.valueOf(maxUploadSizeValue));
		model.addAttribute(CustomerticketingaddonConstants.MAX_UPLOAD_SIZE_MB,
				FileUtils.byteCountToDisplaySize(maxUploadSizeValue));
		
		UserModel currentUser = userService.getCurrentUser();
		if(currentUser != null) {
			Set<PrincipalGroupModel> curGroups = currentUser.getGroups();
			for(PrincipalGroupModel curGroup : curGroups) {
				if(WOAG_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					model.addAttribute("woagUserGroup",curGroup.getUid());
					model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new BDSupportTicketForm());
					
				}
				if(BUSINESS_DEVELOPMENT_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					model.addAttribute("bdUserGroup",curGroup.getUid());
					model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new BDSupportTicketForm());
				}
				if(NIPC_USER_GROUP.equalsIgnoreCase(curGroup.getUid())) {
					model.addAttribute("nipcUserGroup",curGroup.getUid());
					model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_FORM, new BDSupportTicketForm());
				}
			}
		}
		
		try
		{
			final TicketData ticketData = ticketFacade.getTicket(XSSEncoder.encodeHTML(ticketId));
			model.addAttribute(CustomerticketingaddonConstants.SUPPORT_TICKET_DATA, ticketData);
		}
		catch (final UnsupportedEncodingException e)
		{
			LOG.error("Attempted to load ticket details that does not exist or is not visible", e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
					CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_TRY_LATER, null);
			return REDIRECT_TO_SUPPORT_TICKETS_PAGE;
		}

		if (ticketUpdated)
		{
			GlobalMessages.addConfMessage(model, CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_ADDED);
		}

		return getViewForPage(model);
	}

	/**
	 * Updates a ticket with new information from form.
	 *
	 * @param supportTicketForm
	 * @param bindingResult
	 * @return View String
	 */
	@RequestMapping(value = "/support-ticket/"
			+ SUPPORT_TICKET_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequireHardLogIn
	@ResponseBody
	public ResponseEntity<List<Map<String, String>>> updateSupportTicket(final SupportTicketForm supportTicketForm,
			final BindingResult bindingResult)
	{
		validator.validate(supportTicketForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			final List<Map<String, String>> list = buildErrorMessagesMap(bindingResult);
			list.add(buildMessageMap(CustomerticketingaddonConstants.FORM_GLOBAL_ERROR_KEY,
					CustomerticketingaddonConstants.FORM_GLOBAL_ERROR));
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}

		try
		{
			ticketFacade.updateTicket(this.populateTicketData(supportTicketForm, null));
		}
		catch (final UnsupportedAttachmentException usAttEx)
		{
			LOG.error(usAttEx.getMessage(), usAttEx);
			final Map<String, String> map = Maps.newHashMap();

			map.put(CustomerticketingaddonConstants.SUPPORT_TICKET_TRY_LATER, getMessageSource()
					.getMessage(CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_ATTACHMENT_BLOCK_LISTED, new Object[]
			{ allowedUploadedFormats }, getI18nService().getCurrentLocale()));
			return new ResponseEntity<>(Lists.newArrayList(map), HttpStatus.BAD_REQUEST);
		}
		catch (final RuntimeException rEx)
		{
			LOG.error(rEx.getMessage(), rEx);
			//Assuming there might have been an error occurred, If ticket data returned as null. Return to the update page.
			final Map<String, String> map = Maps.newHashMap();
			map.put(CustomerticketingaddonConstants.SUPPORT_TICKET_TRY_LATER, getMessageSource().getMessage(
					CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_TRY_LATER, null, getI18nService().getCurrentLocale()));
			return new ResponseEntity<>(Lists.newArrayList(map), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Populated the data from the form bean to ticket data object.
	 *
	 * @param supportTicketForm
	 * @return TicketData
	 */
	protected TicketData populateTicketData(final SupportTicketForm supportTicketForm, String questions) // TODO: should be moved to populator class
	{
		final TicketData ticketData = new TicketData();
		if (cartFacade.hasSessionCart())
		{
			final CartData cartData = cartFacade.getSessionCart();
			if (!cartData.getEntries().isEmpty())
			{
				ticketData.setCartId(cartData.getCode());
			}
		}
		if (StringUtils.isNotBlank(supportTicketForm.getId()))
		{
			ticketData.setId(supportTicketForm.getId());
		}
		
		final StatusData status = new StatusData();
		status.setId(supportTicketForm.getStatus());
		ticketData.setStatus(status);
		ticketData.setCustomerId(customerFacade.getCurrentCustomerUid());
		ticketData.setSubject(supportTicketForm.getSubject());
		ticketData.setMessage(supportTicketForm.getMessage());
		ticketData.setAssociatedTo(supportTicketForm.getAssociatedTo());
		ticketData.setTicketCategory(supportTicketForm.getTicketCategory());
		ticketData.setAttachments(supportTicketForm.getFiles());
		ticketData.setSector(supportTicketForm.getQuestion2());
		ticketData.setLocation(supportTicketForm.getQuestion4());
		if (StringUtils.isNotBlank(questions))
		{
			HashMap<String,String> map = new Gson().fromJson(questions, new TypeToken<HashMap<String, String>>(){}.getType());
			ticketData.setQueAnsMap(map);
		}
		
		return ticketData;
	}

	protected List<Breadcrumb> getBreadcrumbs(final String breadcrumbCode)
	{
		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.add(new Breadcrumb("/my-account/support-tickets", getMessageSource().getMessage(
				CustomerticketingaddonConstants.TEXT_SUPPORT_TICKETING_HISTORY, null, getI18nService().getCurrentLocale()), null));
		breadcrumbs.add(
				new Breadcrumb("#", getMessageSource().getMessage(breadcrumbCode, null, getI18nService().getCurrentLocale()), null));
		return breadcrumbs;
	}

	/**
	 * Build the error message list with map contains the validation error code and localised message.
	 *
	 * @param bindingResult
	 * @return Map of error code and message
	 */
	protected List<Map<String, String>> buildErrorMessagesMap(final BindingResult bindingResult)
	{
		return bindingResult.getAllErrors().stream().filter(err -> err.getCode() != null && err.getCode().length() > 0).map(err ->
			{
				final Map<String, String> map = Maps.newHashMap();
				map.put(err.getCodes()[0].replaceAll("\\.", "-"), err.getDefaultMessage());
				return map;
			}).collect(Collectors.toList());
	}

	/**
	 * Build a map with key and localsed Message.
	 *
	 * @param key
	 *           the render key
	 * @param localisedKey
	 *           the localised message key
	 * @return Map of error code and message
	 */
	protected Map<String, String> buildMessageMap(final String key, final String localisedKey)
	{
		final Map<String, String> map = Maps.newHashMap();
		map.put(key, getMessageSource().getMessage(localisedKey, null, getI18nService().getCurrentLocale()));

		return map;
	}
}
