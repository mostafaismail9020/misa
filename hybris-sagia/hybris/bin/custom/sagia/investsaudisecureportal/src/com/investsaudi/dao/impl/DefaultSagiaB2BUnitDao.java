package com.investsaudi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.investsaudi.dao.SagiaB2BUnitDao;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultSagiaB2BUnitDao implements SagiaB2BUnitDao {
	
	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<B2BUnitModel> getDisplayedB2BUnit() {
		
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {unit:"+B2BUnitModel.PK+"}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(B2BUnitModel._TYPECODE).append(" as unit");
		sql.append("}	");
		sql.append("WHERE {unit:"+B2BUnitModel.ISDISPLAYTOCUSTOMERS+"} = 1 ");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		searchQuery.setDisableSearchRestrictions(true);
		final SearchResult<B2BUnitModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();
		
	}
	
	
	
	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
