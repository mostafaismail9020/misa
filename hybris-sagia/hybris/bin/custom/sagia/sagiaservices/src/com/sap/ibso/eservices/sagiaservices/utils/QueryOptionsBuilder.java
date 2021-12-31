package com.sap.ibso.eservices.sagiaservices.utils;

import java.util.HashMap;
import java.util.Map;

public final class QueryOptionsBuilder {
	
	private static final String EXPAND = "$expand";
	private static final String SKIP = "$skip";
	private static final String TOP = "$top";
	private static final String FILTER = "$filter";
	private static final String INLINECOUNT = "$inlinecount";
	private Map<String, String> queryParams = new HashMap<>();

	/**
	 * @return full OData Query expression
	 */
	public Map<String, String> build() {
		return this.queryParams;
	}

	public QueryOptionsBuilder skip(String skipValue) {
		this.queryParams.put(SKIP, skipValue);
		return this;
	}

	public QueryOptionsBuilder top(String topValue) {
		queryParams.put(TOP, topValue);
		return this;
	}

	public QueryOptionsBuilder filter(String filterValue) {
		queryParams.put(FILTER, filterValue);
		return this;
	}
	
	public QueryOptionsBuilder expand(String expandValue) {
		queryParams.put(EXPAND, expandValue);
		return this;
	}

	public QueryOptionsBuilder inLineCount(String inLineCountValue) {
		queryParams.put(INLINECOUNT, inLineCountValue);
		return this;
	}

}