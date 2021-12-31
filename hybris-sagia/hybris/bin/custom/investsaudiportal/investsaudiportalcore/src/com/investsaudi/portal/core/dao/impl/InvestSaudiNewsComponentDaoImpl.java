package com.investsaudi.portal.core.dao.impl;

import com.investsaudi.portal.core.dao.InvestSaudiNewsComponentDao;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchParameter;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InvestSaudiNewsComponentDaoImpl extends DefaultGenericDao<InvestSaudiNewsComponentModel> implements InvestSaudiNewsComponentDao {
    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private PaginatedFlexibleSearchService paginatedFlexibleSearchService;

    private static final String QUERY_ALL_NEWS_COMPONENT = "SELECT {p:pk} FROM {InvestSaudiNewsComponent AS p} ORDER BY {p.newsDate} DESC";
    private static final String QUERY_FIRST_NEWS_COMPONENT = "SELECT {p:pk} FROM {InvestSaudiNewsComponent AS p} ORDER BY {p.newsDate} DESC";

    public InvestSaudiNewsComponentDaoImpl(String typecode) {
        super(typecode);
    }

    @Override
    public SearchPageData<InvestSaudiNewsComponentModel> getAllNews(PaginationData paginationData) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_ALL_NEWS_COMPONENT);

        SearchPageData<InvestSaudiNewsComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);

    }

    @Override
    public Collection<InvestSaudiNewsComponentModel> getNews(int number) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_FIRST_NEWS_COMPONENT);
        searchQuery.setCount(number);
        final SearchResult<InvestSaudiNewsComponentModel> resultList = flexibleSearchService.search(searchQuery);
        return null != resultList ? resultList.getResult() : new ArrayList<>();
    }
}
