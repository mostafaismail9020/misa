package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketSectorModel;
import com.sap.ibso.eservices.core.sagia.dao.TicketSectorDAO;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultTicketSectorDAO  implements TicketSectorDAO {

	@Resource
	private FlexibleSearchService flexibleSearchService;
	
	
	@Override
	public List<TicketSectorModel> getActiveTicketSectors() {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {sector:" + TicketSectorModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketSectorModel._TYPECODE).append(" as sector");
		sql.append("}	");
		sql.append("WHERE {sector:" + TicketSectorModel.ISACTIVE + "} = 1 ");
		sql.append("Order By {sector:" + TicketSectorModel.NAME + "}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		final SearchResult<TicketSectorModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();

	}

	@Override
	public List<TicketConfigurationModel> getTicketConfigurationBySectorCode(String code) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT {configuration:" + TicketConfigurationModel.PK + "}	");
		sql.append("FROM	");
		sql.append("{	");
		sql.append(TicketConfigurationModel._TYPECODE).append(" as configuration");		
		sql.append("  JOIN "+TicketSectorModel._TYPECODE+ " AS sector ON {sector.pk} = {configuration.sector} ");
		sql.append(" }	");
		sql.append("WHERE {configuration:" + TicketConfigurationModel.ISACTIVE + "} = 1 and ");
		sql.append(" {sector:" + TicketSectorModel.CODE + "} = ?code  ");
		sql.append("Order By {configuration:" + TicketSectorModel.NAME + "}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(sql.toString());
		searchQuery.addQueryParameter("code", code);
		
		final SearchResult<TicketConfigurationModel> result = flexibleSearchService.search(searchQuery);

		return result.getResult();
	}

}
