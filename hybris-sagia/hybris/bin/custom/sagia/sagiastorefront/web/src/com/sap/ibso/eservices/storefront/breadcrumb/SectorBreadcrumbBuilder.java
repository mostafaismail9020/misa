/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.breadcrumb;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSContentPageService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * SearchBreadcrumbBuilder implementation for
 * {@link de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData}
 */
@Component ("sectorBreadcrumbBuilder")
public class SectorBreadcrumbBuilder
{
	private static final Logger log = LoggerFactory.getLogger(SectorBreadcrumbBuilder.class);
	private static final String LAST_LINK_CLASS = "active";

	private CommerceCategoryService commerceCategoryService;
	private UrlResolver<CategoryModel> categoryModelUrlResolver;

	@Autowired
	private CMSContentPageService cmsContentPageService;

	public List<Breadcrumb> getBreadcrumbs(final CategoryModel category)
	{
		List<Breadcrumb> breadcrumbs = new ArrayList<>();
		String categoryName = category.getName();
		String url = "/sectors-opportunities";

		try {
			ContentPageModel pageForLabel = cmsContentPageService.getPageForLabelOrIdAndMatchType(url, true);
			breadcrumbs.add(getBreadcrumb(pageForLabel, url));
		} catch (CMSItemNotFoundException e) {
			log.error("Error generating breadcrumbs for label [{}]", categoryName, e);
		}

		breadcrumbs.add(new Breadcrumb("#", categoryName, LAST_LINK_CLASS));

		return breadcrumbs;
	}

	private Breadcrumb getBreadcrumb(final ContentPageModel page, String url) {
		String title = page.getTitle();
		if (title == null) {
			title = page.getName();
		}
		Breadcrumb breadcrumb = new Breadcrumb(url, title, LAST_LINK_CLASS);
		return breadcrumb;
	}

}
