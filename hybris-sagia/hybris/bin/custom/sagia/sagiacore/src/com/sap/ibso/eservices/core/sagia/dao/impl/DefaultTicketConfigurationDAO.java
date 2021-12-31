/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.sagia.dao.TicketConfigurationDAO;


/**
 * @author anouarbadri
 *
 */
public class DefaultTicketConfigurationDAO implements TicketConfigurationDAO
{

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<TicketConfigurationModel> getActiveTicketConfiguration()
	{

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {configuration:" + TicketConfigurationModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketConfigurationModel._TYPECODE).append(" as configuration");
		sql.append("}	");
		sql.append("WHERE {configuration:" + TicketConfigurationModel.ISACTIVE + "} = 1 ");
		sql.append("Order By {configuration:" + TicketConfigurationModel.NAME + "}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		final SearchResult<TicketConfigurationModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();

	}

	@Override
	public TicketConfigurationModel getTicketConfigurationByCode(String code) {
		
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {configuration:" + TicketConfigurationModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketConfigurationModel._TYPECODE).append(" as configuration");
		sql.append("}	");
		sql.append("WHERE {configuration:" + TicketConfigurationModel.ISACTIVE + "} = 1 ");
		sql.append("and  {configuration:" + TicketConfigurationModel.CODE + "} = ?code ");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		searchQuery.addQueryParameter("code", code);
		final SearchResult<TicketConfigurationModel> searchResult = flexibleSearchService.search(searchQuery);

		if(searchResult.getResult().size() >0)
		{
			return searchResult.getResult().get(0);
		}
		return null;
	}
	
	@Override
	public List<TicketConfigurationModel> getActiveServiceRequestConfiguration()
	{

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {configuration:" + TicketConfigurationModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketConfigurationModel._TYPECODE).append(" as configuration");
		sql.append("}	");
		sql.append("WHERE {configuration:" + TicketConfigurationModel.ISACTIVE + "} = 1 and ");
		sql.append(" {configuration:" + TicketConfigurationModel.ISSERVICEREQUEST + "} = 1  ");
		sql.append("Order By {configuration:" + TicketConfigurationModel.NAME + "}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		final SearchResult<TicketConfigurationModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();

	}
	
	@Override
	public List<TicketConfigurationModel> getActiveOpportunityConfiguration()
	{

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {configuration:" + TicketConfigurationModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketConfigurationModel._TYPECODE).append(" as configuration");
		sql.append("}	");
		sql.append("WHERE {configuration:" + TicketConfigurationModel.ISACTIVE + "} = 1 and ");
		sql.append(" {configuration:" + TicketConfigurationModel.ISSERVICEREQUEST + "} = 0  ");
		sql.append("Order By {configuration:" + TicketConfigurationModel.NAME + "}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		final SearchResult<TicketConfigurationModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();

	}




}
