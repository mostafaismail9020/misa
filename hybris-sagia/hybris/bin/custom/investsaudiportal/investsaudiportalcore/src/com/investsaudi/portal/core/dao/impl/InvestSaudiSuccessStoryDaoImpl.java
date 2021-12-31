package com.investsaudi.portal.core.dao.impl;


import com.investsaudi.portal.core.dao.InvestSaudiSuccessStoryDao;
import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchParameter;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchService;

import javax.annotation.Resource;
import java.util.*;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class InvestSaudiSuccessStoryDaoImpl extends DefaultGenericDao<SuccessStoryProductModel> implements InvestSaudiSuccessStoryDao
{
    public InvestSaudiSuccessStoryDaoImpl(String typecode) {
        super(typecode);
    }

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private PaginatedFlexibleSearchService paginatedFlexibleSearchService;

    private static final String QUERY_SUCCESS_STORIES_BY_CATEGORY_CODE = "SELECT {p.PK} FROM {SuccessStoryProduct AS p JOIN CategoryProductRelation as rel ON {p:PK} = {rel:target} JOIN Category AS c ON {rel:source} = {c:PK} } WHERE {c.code} =?categoryCode ORDER BY {p.creationtime} DESC";

    private static final String QUERY_SUCCESS_STORIES = "SELECT {p:pk} FROM {SuccessStoryProduct AS p} ORDER BY {p.creationtime} DESC";

    private static final String LIKE = "}) LIKE '%";

    private static final String BASE_QUERY = " SELECT {p:PK}, {c:code} FROM {SuccessStoryProduct AS p JOIN CategoryProductRelation as rel ON {p:PK} = {rel:target} JOIN Category AS c ON {rel:source} = {c:PK} } WHERE {c:code} IN (?list) AND";


    @Override
    public List<SuccessStoryProductModel> findProductsByCode(String code) {
        validateParameterNotNull(code, "Success Story Product code must not be null!");

        return find(Collections.singletonMap(ProductModel.CODE, (Object) code));
    }

    @Override
    public List<SuccessStoryProductModel> getSuccessStoriesByCategoryCode(String categoryCode){

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("categoryCode", categoryCode);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_SUCCESS_STORIES_BY_CATEGORY_CODE, params);

        final SearchResult<SuccessStoryProductModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();

    }

    @Override
    public SearchPageData<SuccessStoryProductModel> searchSuccessStoriesByNameAndSectors(String text, List<String> categories, PaginationData paginationData) {

        String query = BASE_QUERY + "(LOWER({" + SuccessStoryProductModel.CODE + LIKE + text.toLowerCase() +
                "%' OR LOWER({" + SuccessStoryProductModel.NAME + LIKE + text.toLowerCase() + "%')";

        String queryWithoutCategory = "SELECT {" + SuccessStoryProductModel.PK + "} " +
                "FROM {" + SuccessStoryProductModel._TYPECODE + "} WHERE " +
                "(LOWER({" + SuccessStoryProductModel.CODE + LIKE + text.toLowerCase() +
                "%' OR LOWER({" + SuccessStoryProductModel.NAME + LIKE + text.toLowerCase() + "%')";

        if (categories.isEmpty()) {

            final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryWithoutCategory);

            SearchPageData<SuccessStoryProductModel> searchPageData = new SearchPageData<>();
            searchPageData.setPagination(paginationData);

            PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
            parameter.setFlexibleSearchQuery(searchQuery);
            parameter.setSearchPageData(searchPageData);

            return paginatedFlexibleSearchService.search(parameter);
        }
        final Map<String, Object> queryParams = new HashMap<>(1);
        queryParams.put("list", categories);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query, queryParams);

        SearchPageData<SuccessStoryProductModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    @Override
    public List<SuccessStoryProductModel> getSuccessStories() {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_SUCCESS_STORIES);

        final SearchResult<SuccessStoryProductModel> resultList = flexibleSearchService.search(searchQuery);

        return !resultList.getResult().isEmpty() ? resultList.getResult() : new ArrayList<>();
    }

}
