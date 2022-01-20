package com.sap.ibso.eservices.storefront.controllers.pages;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.apache.log4j.Logger;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;


@Controller
@RequestMapping(value = "/potentialOpportunity")
public class MyPotentialOpportunityController extends SagiaAbstractPageController {

	private static final Logger LOG = Logger.getLogger(MyPotentialOpportunityController.class);

	private static final String POTENTIAL_OPPORTUNITY_CMS_PAGE = "potential-opportunity";
	private static final String PATH_VARIABLE_PATTERN = "/{ticketId}";

	@Resource(name = "sagiaUserService")
	private SagiaUserService sagiaUserService;

	@Resource(name = "investSaudiProductFacade")
	private InvestSaudiProductFacade investSaudiProductFacade;

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

}
