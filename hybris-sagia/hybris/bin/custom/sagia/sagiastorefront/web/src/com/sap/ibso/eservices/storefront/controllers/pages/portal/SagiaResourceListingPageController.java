/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/resourcesList")
public class SagiaResourceListingPageController extends SagiaSearchPageController
{
	private static final String RESOURCES_LIST_PAGE = "resources-list-page";
	
	@Override
	protected String getPageId() {
		return RESOURCES_LIST_PAGE;
	}
	
	@Override
	protected String getFilterParam() {
		return "";
	}
}
