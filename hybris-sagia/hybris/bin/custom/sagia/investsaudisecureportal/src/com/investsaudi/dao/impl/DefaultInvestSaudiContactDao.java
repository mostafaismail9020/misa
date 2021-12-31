package com.investsaudi.dao.impl;

import java.util.List;

import com.investsaudi.dao.InvestSaudiContactDao;
import com.sap.ibso.eservices.core.model.InvestSaudiContactModel;
import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultInvestSaudiContactDao implements InvestSaudiContactDao {
	
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<InvestSaudiContactModel> getActiveContact() {
		
		
		  final StringBuilder sql = new StringBuilder();
		  sql.append("SELECT {contact:"+InvestSaudiContactModel.PK+"}	"); sql.append("FROM	");
		  sql.append("{	"); sql.append(InvestSaudiContactModel._TYPECODE).append(" as contact");
		  sql.append("}	");
		  sql.append("WHERE {contact:"+InvestSaudiContactModel.ISACTIVE+"} = 1 ");
		  sql.append("Order By {contact:"+InvestSaudiContactModel.RANKING+"}");
		  
		  final FlexibleSearchQuery searchQuery = new
		  FlexibleSearchQuery(sql.toString()); 
		  final SearchResult<InvestSaudiContactModel> result= flexibleSearchService.search(searchQuery);
		  
		  return result.getResult();
		 
	}

	

	@Override
	public List<InvestSaudiContactModel> getActiveContactByDepartment(InvestSaudiSectorModel department) {
		final StringBuilder sql = new StringBuilder();
		  sql.append("SELECT {contact:"+InvestSaudiContactModel.PK+"}	"); sql.append("FROM	");
		  sql.append("{	"); sql.append(InvestSaudiContactModel._TYPECODE).append(" as contact");
		  sql.append("}	");
		  sql.append("WHERE {contact:"+InvestSaudiContactModel.ISACTIVE+"} = 1 ");
		  sql.append("AND {contact:"+InvestSaudiContactModel.DEPARTMENT+"} = "+department.getPk());
		  
		  final FlexibleSearchQuery searchQuery = new
		  FlexibleSearchQuery(sql.toString()); 
		  final SearchResult<InvestSaudiContactModel> result= flexibleSearchService.search(searchQuery);
		  
		  return result.getResult();
	}
	
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

}
