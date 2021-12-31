package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.categories.SagiaCategoryData;

import java.util.List;
import java.util.Map;

/**
 * SagiaNavigationMenuFacade
 */
public interface SagiaNavigationMenuFacade {
    /**
     * returns a map in which key is category name and value is array of services belonging to the category
     * @return navigationMenuServices Map
     */
    Map<String, List<SagiaServiceData>> getNavigationMenuServices();

    /**
     * returns a map in which key is category label and value is categories grouped under that label
     * @return navigationMenuCategories Map
     */
    Map<String, List<SagiaCategoryData>> getNavigationMenuCategories();
}
