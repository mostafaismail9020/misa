package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaCategoryModel;
import com.sap.ibso.eservices.core.model.SagiaServiceModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaGovtServiceDAO;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DefaultSagiaGovtServiceDAO implements SagiaGovtServiceDAO {

    private FlexibleSearchService flexibleSearchService;

    private static final String GET_CATEGORIES_QUERY = "SELECT {" + SagiaCategoryModel.PK + "} FROM {" + SagiaCategoryModel._TYPECODE + "} ";
    private static final String GET_SERVICES_QUERY = "SELECT {" + SagiaServiceModel.PK + "} FROM {" + SagiaServiceModel._TYPECODE + "}";
    private static final String GET_SERVICES_BY_CATEGORY_QUERY = GET_SERVICES_QUERY + " WHERE {"+SagiaServiceModel.CATEGORY+"} = ?category";

    /**
     *
     * @return map with categories and services, that has categories as keys and list of services as values.
     */
    @Override
    public Map<SagiaCategoryModel, List<SagiaServiceModel>> getGovtCategoriesServices() {
        Map<SagiaCategoryModel, List<SagiaServiceModel>> servicesByCategoryMap = new LinkedHashMap<>();
        List<SagiaCategoryModel> categoryModels = getGovtCategories();
        if (CollectionUtils.isNotEmpty(categoryModels)) {
            categoryModels.stream().forEach(sagiaCategory -> servicesByCategoryMap.put(sagiaCategory, getServicesByCategory(sagiaCategory)));
        }
        return servicesByCategoryMap;
    }

    private List<SagiaCategoryModel> getGovtCategories() {
        FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(GET_CATEGORIES_QUERY);
        SearchResult<SagiaCategoryModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        return searchResult.getResult();
    }

    private List<SagiaServiceModel> getServicesByCategory(SagiaCategoryModel sagiaCategory) {
        FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(GET_SERVICES_BY_CATEGORY_QUERY);
        flexibleSearchQuery.addQueryParameter("category", sagiaCategory);
        SearchResult<SagiaServiceModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        return searchResult.getResult();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
