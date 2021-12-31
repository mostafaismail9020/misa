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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping("/service-requests-overview")
@RequireHardLogIn
public class SagiaServiceRequestsOverviewController extends SagiaAbstractPageController {

	private static final String SAGIA_SERVICE_REQUEST_OVERVIEW_CMS_PAGE = "service-requests-overview";

	@RequestMapping(method = RequestMethod.GET)
	public String getPaymentsPage(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_SERVICE_REQUEST_OVERVIEW_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_SERVICE_REQUEST_OVERVIEW_CMS_PAGE));
		return getViewForPage(model);
	}
}
