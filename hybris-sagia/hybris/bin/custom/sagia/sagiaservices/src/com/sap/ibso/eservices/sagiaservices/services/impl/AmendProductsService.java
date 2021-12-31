package com.sap.ibso.eservices.sagiaservices.services.impl;


import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.amendproducts.AmendProductsFilterExpression;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.Map;


/**
 * AmendProductsService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class AmendProductsService extends AbstractSagiaService<ProductData> {
    public static final String ALLPAGES = "allpages";

    /**
     * retrieves AmendProductsWithId
     * @param productId productId
     * @param skip skip
     * @param top top
     * @return Collection of ProductData
     */
    public Collection<ProductData> getAmendProductsWithId(String productId, String skip, String top) {
        AmendProductsFilterExpression filterExpression = new AmendProductsFilterExpression().productId(productId);

        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .skip(skip)
                .top(top)
                .filter(filterExpression.build())
                .inLineCount(ALLPAGES)
                .build();
        return getCollection(ProductData.class, queryOptions);
    }

    /**
     * retrieves AmendProductsWithDescription
     * @param productDescription productDescription
     * @param skip skip
     * @param top top
     * @return Collection of ProductData
     */
    public Collection<ProductData> getAmendProductsWithDescription(String productDescription, String skip, String top) {
        AmendProductsFilterExpression filterExpression = new AmendProductsFilterExpression().productDescription(productDescription);
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .skip(skip)
                .top(top)
                .filter(filterExpression.build())
                .inLineCount(ALLPAGES)
                .build();
        return getCollection(ProductData.class, queryOptions);
    }

    /**
     * retrieves AmendProductsWithDescription
     * @param skip skip
     * @param top top
     * @return Collection of ProductData
     */
    public Collection<ProductData> getAmendProductsWithDescription (String skip, String top) {
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .skip(skip)
                .top(top)
                .inLineCount(ALLPAGES)
                .build();
        return getCollection(ProductData.class, queryOptions);
    }
}
