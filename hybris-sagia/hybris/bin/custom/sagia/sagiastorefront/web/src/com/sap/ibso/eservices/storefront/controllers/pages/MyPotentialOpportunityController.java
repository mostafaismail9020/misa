package com.sap.ibso.eservices.storefront.controllers.pages;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import com.sap.ibso.eservices.facades.user.SagiaUserFacade;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.comments.model.CommentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import com.sap.ibso.eservices.storefront.forms.ContactTicketForm;
import com.sap.ibso.eservices.facades.data.SagiaServiceRequestFormData;
import com.sap.ibso.eservices.core.enums.IncidentCategory;
import com.sap.ibso.eservices.core.enums.ServiceCategory;
import com.sap.ibso.eservices.core.enums.Priority;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.ticket.strategies.TicketEventStrategy;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.catalog.model.CatalogVersionModel;
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
import java.util.EnumSet;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import de.hybris.platform.core.model.media.MediaModel;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.IllegalStateException;
import org.springframework.http.MediaType;
import de.hybris.platform.catalog.CatalogVersionService;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.springframework.web.bind.annotation.RequestParam;
import com.investsaudi.portal.core.service.ContactTicketBusinessService;


@Controller
@RequestMapping(value = "/potentialOpportunity")
public class MyPotentialOpportunityController extends SagiaAbstractPageController {

	private static final Logger LOG = Logger.getLogger(MyPotentialOpportunityController.class);

	private static final String POTENTIAL_OPPORTUNITY_CMS_PAGE = "potential-opportunity";
	private static final String PATH_VARIABLE_PATTERN = "/{ticketId}";
	private static final String SERVICE_REQUEST_CMS_PAGE = "service-request";
        private static final String THIS_CONTROLLER_REDIRECTION_URL = "/potentialOpportunity/";
	
        @Resource(name = "sagiaUserService")
	private SagiaUserService sagiaUserService;
        
        @Resource(name = "sagiaUserFacade")
	private SagiaUserFacade sagiaUserFacade;

	@Resource(name = "investSaudiProductFacade")
	private InvestSaudiProductFacade investSaudiProductFacade;

	@Resource(name = "contactTicketEventStrategy")
	private TicketEventStrategy ticketEventStrategy;

	@Autowired
    private MediaService mediaService;
	
	@Autowired
	private ModelService modelService;
	
	
	@Autowired
	private ContactTicketBusinessService contactTicketBusinessService;

	@Resource
    private CatalogVersionService catalogVersionService;

	private static final String CATALOG_ID = "sagiaContentCatalog";
	private static final String VERSION_ONLINE = "Online";
		
	@RequestMapping(value = PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String getMyPotentialOpportunityPage(@PathVariable("ticketId") final String ticketId, final Model model)
			throws CMSItemNotFoundException {

		ContactTicketModel contactTicketDetails = sagiaUserService.getContactTicketForTicketId(ticketId);
		List<CommentModel> comments = contactTicketDetails.getComments();
		CopyOnWriteArrayList<CommentModel> commentsModified = new CopyOnWriteArrayList<>(comments);
		LOG.info("comments before sorting "+commentsModified);
		Collections.reverse(commentsModified);
		LOG.info("comments after sorting "+commentsModified);
		model.addAttribute("comments", commentsModified);
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
	
	@RequestMapping(value = "{ticketId}/serviceRequest", method = { RequestMethod.GET })
	@RequireHardLogIn
	public String getMyServiceRequestPage(@PathVariable("ticketId") final String ticketId,
			@ModelAttribute("sagiaServiceRequestFormData") SagiaServiceRequestFormData sagiaServiceRequestFormData,
			final Model model) throws CMSItemNotFoundException {

		List<IncidentCategory> incidentCategories = sagiaUserFacade.getIncidentCategoryEnumValues();
		List<ServiceCategory> serviceCategories = sagiaUserFacade.getServiceCategoryEnumValues();
		List<Priority> priorities = sagiaUserFacade.getPriorityEnumValues();

		model.addAttribute("ticketId", ticketId);
		model.addAttribute("incidentCategories", incidentCategories);
		model.addAttribute("serviceCategories", serviceCategories);
		model.addAttribute("priorities", priorities);

		model.addAttribute("sagiaServiceRequestFormData", sagiaServiceRequestFormData);
		storeCmsPageInModel(model, getContentPageForLabelOrId(SERVICE_REQUEST_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SERVICE_REQUEST_CMS_PAGE));
		return getViewForPage(model);
	}

	@RequestMapping(value = "{ticketId}/serviceRequest", method = RequestMethod.POST)
	public String submitServiceRequestForm(@PathVariable("ticketId") final String ticketId, final Model model,
			@ModelAttribute("sagiaServiceRequestFormData") final SagiaServiceRequestFormData sagiaServiceRequestFormData,
			final BindingResult bindingResult, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException{
        boolean attachRequestToTicket = false;
		attachRequestToTicket = sagiaUserFacade.validateSagiaUerFormData(sagiaServiceRequestFormData, ticketId);
		if (attachRequestToTicket) {
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"Service Request added successfully to Contact Ticket");
		}
		return "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + ticketId;
	}
	
	 
	 
	@RequestMapping(value = "/{ticketId}/uploadAttachment",method = RequestMethod.POST,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.TEXT_PLAIN_VALUE})
    public String uploadAttachment(final Model model, @ModelAttribute("contactTicketForm") final ContactTicketForm contactTicketForm,
    		final BindingResult bindingResult, @PathVariable("ticketId") final String ticketId, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
		
    	final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(CATALOG_ID, VERSION_ONLINE);
		final MediaModel mediaModel = modelService.create(MediaModel.class);
          LOG.info("calling uploadAttachment controller");

        if(null != contactTicketForm && null != contactTicketForm.getComment()) {
            LOG.info("receieved the file");
        }
        try
        {
        final byte[] bytes = contactTicketForm.getPdfAttachment().getBytes();

        if (null != bytes) {
        	LOG.info("Entered into bytes != null :"+bytes.length);
            final InputStream inputStream = new ByteArrayInputStream(bytes);
            LOG.info("inputStream is: "+inputStream);
            mediaModel.setCode("ticket_"+ticketId+"_"+System.currentTimeMillis());
            mediaModel.setCatalogVersion(catalogVersion); // use catalogVersionService to get the online version
            mediaModel.setRealFileName("ticket_"+ticketId+".pdf");
            LOG.info("before saving media model");
            modelService.save(mediaModel);
            LOG.info("mediaModel "+mediaModel);
            LOG.info("after saving media model");
            mediaService.setStreamForMedia(mediaModel, inputStream);

             inputStream.close();
            }	
        }
            catch (final IOException ex)
            {
                LOG.error(ex.getMessage());
                ex.printStackTrace();
            }
        ContactTicketModel contactTicketModel = sagiaUserService.getContactTicketForTicketId(ticketId);
        LOG.info("contactTicketModel is " +contactTicketModel);
        CsTicketModel ticket = (CsTicketModel)contactTicketModel;
        //in ticketmodel attachments is unmodifiable so , we need to always create new list and add both old and new attachments
		final ArrayList<MediaModel> list = new ArrayList<MediaModel>();
	    if (!CollectionUtils.isEmpty(ticket.getAttachments()))
			{
			list.addAll(ticket.getAttachments());
			}
			list.add(mediaModel);
			ticket.setAttachments(list);
		
		modelService.save(ticket);
		contactTicketBusinessService.ticketAttachment2SCPI(contactTicketModel);
		
         return  "redirect:" + THIS_CONTROLLER_REDIRECTION_URL + ticketId;
        // set the media model to attachments attribute in contact ticket model
        }

        private File createFile(MultipartFile multipartFile)
        {
        	try {
        		
        		   File file = File.createTempFile(multipartFile.getOriginalFilename(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        		   file.deleteOnExit();
        		   multipartFile.transferTo(file);
        		   return file;
        	}
        	catch(IOException | IllegalStateException ex)
        	{
        		LOG.error("Exception in createFile: "+ex);
        	}
        	return null;
        }             
}
