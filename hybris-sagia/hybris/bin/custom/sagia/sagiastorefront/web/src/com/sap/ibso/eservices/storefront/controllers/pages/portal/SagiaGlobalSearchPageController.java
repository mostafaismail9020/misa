/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/globalSearch")
public class SagiaGlobalSearchPageController extends SagiaSearchPageController
{
	private static final String GLOBAL_SEARCH_PAGE = "globalSearch";
	private static final Logger LOG = Logger.getLogger(SagiaGlobalSearchPageController.class);
	
	@Override
	protected String getPageId() {
		return GLOBAL_SEARCH_PAGE;
	}
}
