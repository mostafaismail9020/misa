package com.investsaudi.portal.core.dao.impl;

import com.google.common.collect.ImmutableMap;
import com.investsaudi.portal.core.dao.InvestSaudiOpportunityDao;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
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
import org.apache.log4j.Logger;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class InvestSaudiOpportunityDaoImpl extends DefaultGenericDao<OpportunityProductModel> implements InvestSaudiOpportunityDao {
	
	private static final Logger LOG = Logger.getLogger(InvestSaudiOpportunityDaoImpl.class);

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private PaginatedFlexibleSearchService paginatedFlexibleSearchService;
	
	private static final String QUERY_OPPORTUNITY_COMPONENT = "SELECT {p:pk} FROM {OpportunityProduct AS p JOIN CatalogVersion AS cv ON {p.catalogVersion}={cv.pk}} WHERE {p.code}=?code AND {cv.pk} = ?catalogVersionPk"; 

    private static final String QUERY_FEATURED_OPPORTUNITIES = "SELECT {p:pk} FROM {OpportunityProduct AS p} WHERE {p:featured} = ?featuredValue" + " ORDER BY {p.creationtime} DESC";

    private static final String QUERY_CREATION_DATE_OPPORTUNITIES = " SELECT {p.PK} " + " FROM {OpportunityProduct AS p} " + " ORDER BY {p.creationtime} DESC";

    private static final String QUERY_FEATURED_OPPORTUNITIES_BY_CATEGORY_CODE = "SELECT {p.PK} FROM {OpportunityProduct AS p JOIN CategoryProductRelation as rel ON {p:PK} = {rel:target} JOIN Category AS c ON {rel:source} = {c:PK} } WHERE {p:featured} =?featuredValue AND {c.code} =?categoryCode ORDER BY {p.creationtime} DESC";

    private static final String LIKE = "}) LIKE '%";

    private static final String BASE_QUERY = " SELECT {p:PK}, {c:code} FROM {OpportunityProduct AS p JOIN CategoryProductRelation as rel ON {p:PK} = {rel:target} JOIN Category AS c ON {rel:source} = {c:PK} } WHERE {c:code} IN (?list) AND";

    public InvestSaudiOpportunityDaoImpl(String typecode) {
        super(typecode);
    }

    @Override
    public List<OpportunityProductModel> findProductsByCode(String code) {
        validateParameterNotNull(code, "Opportunity Product code must not be null!");

        return find(Collections.singletonMap(ProductModel.CODE, (Object) code));
    }
	
	@Override
    public OpportunityProductModel findProductByCodeAndCatalogVersion(CatalogVersionModel catalogVersion, String code) {
    	validateParameterNotNull(catalogVersion, "CatalogVersion must not be null!");
        validateParameterNotNull(code, "Opportunity Product code must not be null!");
        
        final Map<String, Object> params = new HashMap<String, Object>();      
 	    params.put("code", code);
 	    params.put("catalogVersionPk", catalogVersion.getPk());
 	    LOG.debug("OpportunityProductModel code: "+code);
 	    LOG.debug("catalogVersionPk: "+catalogVersion.getPk());
 	    
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_OPPORTUNITY_COMPONENT, params); 
        final SearchResult<OpportunityProductModel> resultList = flexibleSearchService.search(searchQuery);
        return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null; 
    }

    @Override
    public List<OpportunityProductModel> getFeaturedOpportunities() {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_FEATURED_OPPORTUNITIES, ImmutableMap.of("featuredValue", Boolean.TRUE));

        final SearchResult<OpportunityProductModel> resultList = flexibleSearchService.search(searchQuery);

        return !resultList.getResult().isEmpty() ? resultList.getResult() : getLastProductsCreated();
    }

    @Override
    public List<OpportunityProductModel> getFeaturedOpportunitiesByCategoryCode(String categoryCode){

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("featuredValue", Boolean.TRUE);
        params.put("categoryCode", categoryCode);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_FEATURED_OPPORTUNITIES_BY_CATEGORY_CODE, params);

        final SearchResult<OpportunityProductModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();

    }

    @Override
    public SearchPageData<OpportunityProductModel> searchOpportunityByNameAndSectors(String text, List<String> categories, PaginationData paginationData) {

        String query = BASE_QUERY + "(LOWER({" + OpportunityProductModel.CODE + LIKE + text.toLowerCase() +
                "%' OR LOWER({" + OpportunityProductModel.NAME + LIKE + text.toLowerCase() + "%')";

        String queryWithoutCategory = "SELECT {" + OpportunityProductModel.PK + "} " +
                "FROM {" + OpportunityProductModel._TYPECODE + "} WHERE " +
                "(LOWER({" + OpportunityProductModel.CODE + LIKE + text.toLowerCase() +
                "%' OR LOWER({" + OpportunityProductModel.NAME + LIKE + text.toLowerCase() + "%')";

        if (categories.isEmpty()) {

            final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryWithoutCategory);

            SearchPageData<OpportunityProductModel> searchPageData = new SearchPageData<>();
            searchPageData.setPagination(paginationData);

            PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
            parameter.setFlexibleSearchQuery(searchQuery);
            parameter.setSearchPageData(searchPageData);

            return paginatedFlexibleSearchService.search(parameter);
        }
        final Map<String, Object> queryParams = new HashMap<>(1);
        queryParams.put("list", categories);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query, queryParams);

        SearchPageData<OpportunityProductModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    private List<OpportunityProductModel> getLastProductsCreated() {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_CREATION_DATE_OPPORTUNITIES);

        final SearchResult<OpportunityProductModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();
    }
}
