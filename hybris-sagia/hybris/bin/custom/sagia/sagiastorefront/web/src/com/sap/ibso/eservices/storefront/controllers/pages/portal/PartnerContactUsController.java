/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/showInterest")
public class PartnerContactUsController extends AbstractPageController
{
	@Resource(name = "sessionService")
	private SessionService sessionService;
	@Resource(name = "sagiaConfigurationFacade")
	private SagiaConfigurationFacade sagiaConfigurationFacade;

	@RequestMapping(method = RequestMethod.GET, params = "partner")
	public String showInterest(@RequestParam(name = "partner", required=true) final String partnerSystem,final Model model,final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		boolean partnerPresent=false;
		String partnerList = sagiaConfigurationFacade.getSagiaPartnerList();
		final String[] partnerListArray = partnerList.split(",");
		for (int i = 0; i < partnerListArray.length; i++)
		{
			if(partnerListArray[i].equalsIgnoreCase(partnerSystem))
			{
				partnerPresent=true;
			}
		}

		if(partnerPresent) {
			final ContentPageModel contentPage = getContentPageForLabelOrId("showInterest");
			sessionService.setAttribute("partnerSystem", partnerSystem);
			storeCmsPageInModel(model, contentPage);
			setUpMetaDataForContentPage(model, contentPage);
			updatePageTitle(model, contentPage);
			return getViewForPage(model);
		}
		else
		{
			return "redirect:/404";
		}
	}

	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}
}
