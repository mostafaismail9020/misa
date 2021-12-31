package com.sap.ibso.eservices.sagiaservices.services.customizinglist;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CustomizingGetListFilterExpression
 */
public class CustomizingGetListFilterExpression {

	private static final String AND = " and ";
	private static final String EQUALS = " eq ";
	private static final String SCENARIO = "Scenario";
	private static final String FIELD_NAME = "Fieldname";
	private Map<String, String> filterParams = new HashMap<>();

	/**
	 * @return
	 */
	public String build() {
		String filterExpression = filterParams.entrySet()
				.stream()
				.map(entry -> buildFilterExpression(entry.getKey(), entry.getValue(), EQUALS))
				.collect(Collectors.joining(AND));

		return "(" + filterExpression + ")";

	}
	private String buildFilterExpression(String propertyName, String value, String operator) {
		return propertyName + operator + "'" + value + "'";
	}

	/**
	 * @param scenario
	 * @return
	 */
	public CustomizingGetListFilterExpression scenario(String scenario) {
		this.filterParams.put(SCENARIO, scenario);
		return this;
	}

	/**
	 * @param fieldNAme
	 * @return
	 */
	public CustomizingGetListFilterExpression fieldName(String fieldNAme) {
		this.filterParams.put(FIELD_NAME, fieldNAme);
		return this;
	}
}
