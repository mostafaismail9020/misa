package com.sap.ibso.eservices.sagiaservices.services.classification;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AttachmentListFilterExpression
 */
public class AttachmentListFilterExpression {
    private static final String AND = " and ";
    private static final String EQUALS = " eq ";
    private static final String TYPE = "Type";
    private static final String SUBTYPE = "Subtype";
    private Map<String, String> filterParams = new HashMap<>();

    /**
     * build
     * @return String
     */
    public String build() {
        return "(" + filterParams.entrySet()
                .stream()
                .map(entry -> buildFilterExpression(entry.getKey(), entry.getValue(), EQUALS))
                .collect(Collectors.joining(AND)) + ")";
    }

    /**
     * buildFilterExpression
     * @param propertyName propertyName
     * @param value value
     * @param operator operator
     * @return String
     */
    private String buildFilterExpression(String propertyName, String value, String operator) {
        return propertyName + operator + "'" + value + "'";
    }

    /**
     * type
     * @param type type
     * @return AttachmentListFilterExpression
     */
    public AttachmentListFilterExpression type(String type) {
        this.filterParams.put(TYPE, type);
        return this;
    }

    /**
     * subtype
     * @param subtype subtype
     * @return AttachmentListFilterExpression
     */
    public AttachmentListFilterExpression subtype(String subtype) {
        this.filterParams.put(SUBTYPE, subtype);
        return this;
    }
}