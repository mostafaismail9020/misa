/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/articlesList")
public class SagiaArticleListPageController extends SagiaSearchPageController
{
	private static final String DEFAULT_SORT_TYPE = ":creationTime-desc";
	private static final String ARTICLE_LIST_PAGE = "article-list-page";
	
	@Override
	protected String getPageId() {
		return ARTICLE_LIST_PAGE;
	}
	
	@Override
	protected String getFilterParam() {
		return ":resource:Article";
	}
	
	protected String getDefaultSort() {
		return DEFAULT_SORT_TYPE;
	}
}
