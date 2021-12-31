package com.investsaudi.portal.core.dao.impl;

import com.google.common.collect.ImmutableMap;
import com.investsaudi.portal.core.dao.InvestSaudiSiteMapDao;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class InvestSaudiSiteMapDaoImpl implements InvestSaudiSiteMapDao {

    @Resource
    private FlexibleSearchService flexibleSearchService;


    private static final String QUERY_GET_ALL_CONTENT_PAGES = "SELECT {p:pk} FROM {ContentPage AS p} WHERE {p.catalogversion} = ?catalogVersion";
    private static final String QUERY_GET_ALL_CATEGORY_PAGES = "SELECT {p:pk} FROM {CategoryPage AS p} WHERE {p.catalogversion} = ?catalogVersion";
    private static final String QUERY_GET_ALL_PRODUCT_PAGES = "SELECT {p:pk} FROM {Product AS p} WHERE {p.catalogversion} = ?catalogVersion";


    @Override
    public List<ContentPageModel> getAllActiveContentPagesForCatalogVersion(CatalogVersionModel catalogVersionModel) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_GET_ALL_CONTENT_PAGES, ImmutableMap.of("catalogVersion", catalogVersionModel.getPk()));

        final SearchResult<ContentPageModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();
    }

    @Override
    public List<CategoryPageModel> getAllActiveCategoryPagesForCatalogVersion(CatalogVersionModel catalogVersionModel) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_GET_ALL_CATEGORY_PAGES, ImmutableMap.of("catalogVersion", catalogVersionModel.getPk()));

        final SearchResult<CategoryPageModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();
    }

    @Override
    public List<ProductModel> getAllActiveProductPagesForCatalogVersion(CatalogVersionModel catalogVersionModel) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_GET_ALL_PRODUCT_PAGES, ImmutableMap.of("catalogVersion", catalogVersionModel.getPk()));

        final SearchResult<ProductModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();
    }
}
