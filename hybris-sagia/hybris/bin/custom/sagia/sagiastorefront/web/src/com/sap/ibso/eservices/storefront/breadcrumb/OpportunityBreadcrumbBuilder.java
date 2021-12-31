/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.breadcrumb;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.history.BrowseHistory;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSContentPageService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.helper.ProductAndCategoryHelper;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * OpportunityBreadcrumbBuilder implementation for {@link ProductData}
 */
public class OpportunityBreadcrumbBuilder extends ProductBreadcrumbBuilder
{
	private static final String LAST_LINK_CLASS = "active";

	private static final Logger log = LoggerFactory.getLogger(OpportunityBreadcrumbBuilder.class);

	private UrlResolver<ProductModel> productModelUrlResolver;
	private UrlResolver<CategoryModel> categoryModelUrlResolver;
	private BrowseHistory browseHistory;
	private ProductService productService;
	private ProductAndCategoryHelper productAndCategoryHelper;

	@Autowired
	private CMSContentPageService cmsContentPageService;

	/**
	 * Returns a list of breadcrumbs for the given product.
	 *
	 * @param productCode
	 * @return breadcrumbs for the given product
	 */
	@Override
	public List<Breadcrumb> getBreadcrumbs(final String productCode)
	{
		final ProductModel productModel = getProductService().getProductForCode(productCode);
		final List<Breadcrumb> breadcrumbs = new ArrayList<>();
		final Collection<CategoryModel> categoryModels = new ArrayList<>();
		final Breadcrumb last;
		final ProductModel baseProductModel = getProductAndCategoryHelper().getBaseProduct(productModel);

		last = getProductBreadcrumb(baseProductModel);
		categoryModels.addAll(baseProductModel.getSupercategories());
		last.setLinkClass(LAST_LINK_CLASS);

		breadcrumbs.add(last);

		CategoryModel toDisplay = processCategoryModels(categoryModels, null);
		categoryModels.clear();
		if (toDisplay != null) {
			breadcrumbs.add(getCategoryBreadcrumb(toDisplay));
			categoryModels.addAll(toDisplay.getSupercategories());
		}

		String url = "/sectors-opportunities";

		try {
			ContentPageModel pageForLabel = cmsContentPageService.getPageForLabelOrIdAndMatchType(url, true);
			breadcrumbs.add(getBreadcrumb(pageForLabel, url));
		} catch (CMSItemNotFoundException e) {
			log.error("Error generating breadcrumbs for label [{}]", productModel.getName(), e);
		}

		Collections.reverse(breadcrumbs);
		return breadcrumbs;
	}

	@Override
	protected Breadcrumb getCategoryBreadcrumb(final CategoryModel category)
	{
		String categoryCode = category.getCode();
		final String categoryUrl = getCategoryModelUrlResolver().resolve(category);
		return new Breadcrumb(categoryUrl, category.getName(), null, category.getCode());
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
