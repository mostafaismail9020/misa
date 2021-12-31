/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.ibso.eservices.storefront.controllers.SagiaConstants;

/**
 * @author Razvan Badea <razvan.badea@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping("/payments-overview")
@RequireHardLogIn
public class SagiaPaymentsOverviewController extends SagiaAbstractPageController {

	private static final String SAGIA_PAYMENTS_OVERVIEW_CMS_PAGE = "payments-overview";
	
    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

	@RequestMapping(method = RequestMethod.GET)
	public String getPaymentsPage(final Model model) throws CMSItemNotFoundException
	{
		model.addAttribute("MIGS_Session_JS", configurationService.getConfiguration().getString(SagiaConstants.MIGS_SESSION_URL));
		storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_PAYMENTS_OVERVIEW_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_PAYMENTS_OVERVIEW_CMS_PAGE));
		return getViewForPage(model);
	}
}
