/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/articlesList")
public class SagiaArticleListPageController extends SagiaSearchPageController
{
	private static final String ARTICLE_LIST_PAGE = "article-list-page";
	private static final Logger LOG = Logger.getLogger(SagiaArticleListPageController.class);
	
	@Override
	protected String getPageId() {
		return ARTICLE_LIST_PAGE;
	}
	
	@Override
	protected String getFilterParam() {
		return ":resource:Article";
	}
}
