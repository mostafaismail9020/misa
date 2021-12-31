package com.sap.ibso.eservices.sagiaservices.services.amendproducts;



import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class AmendProductsFilterExpression {


    private static final String AND = " and ";
    private static final String EQ = " eq ";
    private static final String PRODUCT_ID = "ProductID";
    private static final String PRODUCT_DESCRIPTION = "ProdDesc";
    private Map<String, String> filterParams = new HashMap<>();

    /**
     * @return
     */
    public String build() {
        return filterParams.entrySet()
                .stream()
                .map(entry -> buildFilterExpression(entry.getKey(), entry.getValue(), EQ))
                .collect(Collectors.joining(AND));
    }

    private String buildFilterExpression(String propertyName, String value, String operator) {
        return propertyName + operator + "'*" + value + "*'";
    }

    /**
     * @param productId
     * @return
     */
    public AmendProductsFilterExpression productId(String productId) {
        this.filterParams.put(PRODUCT_ID, productId);
        return this;
    }

    /**
     * @param description
     * @return
     */
    public AmendProductsFilterExpression productDescription(String description) {
        this.filterParams.put(PRODUCT_DESCRIPTION, description);
        return this;
    }
}
