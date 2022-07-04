package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SaudiArabiaInternationalIndicesModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import com.sap.ibso.eservices.core.sagia.dao.SAInternationalIndicesDAO;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

public class DefaultSAInternationalIndicesDAO implements SAInternationalIndicesDAO {

	private FlexibleSearchService flexibleSearchService;
	private static final String START_YEAR = "startYear";
	private static final String END_YEAR = "endYear";
	private static final String SELECTED_CHART = "selectedChart";
	private static final String SELECTED_INDICATOR = "indicator";


	@Override
	public List<SaudiArabiaInternationalIndicesModel> getSaudiArabiaInternationalIndicesModelBySearch(String indicator, Integer startYear, Integer endYear) {
		final String queryString = "SELECT {" + SaudiArabiaInternationalIndicesModel.PK + "} FROM {"
				+ SaudiArabiaInternationalIndicesModel._TYPECODE + "}" + " WHERE { " + SaudiArabiaInternationalIndicesModel.UID + " }= '" +indicator +"' AND {"
				+ SaudiArabiaInternationalIndicesModel.YEAR + "} BETWEEN " + startYear + " AND " + endYear
				+ " ORDER BY{" + SaudiArabiaInternationalIndicesModel.YEAR + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		final SearchResult<SaudiArabiaInternationalIndicesModel> searchResult = getFlexibleSearchService()
				.search(query);

		return searchResult.getResult();
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
