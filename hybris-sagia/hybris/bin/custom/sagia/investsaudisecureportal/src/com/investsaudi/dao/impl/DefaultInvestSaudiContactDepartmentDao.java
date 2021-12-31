package com.investsaudi.dao.impl;

import java.util.List;

import com.investsaudi.dao.InvestSaudiContactDepartmentDao;
import com.sap.ibso.eservices.core.model.InvestSaudiSectorModel;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultInvestSaudiContactDepartmentDao implements InvestSaudiContactDepartmentDao {

	
	private FlexibleSearchService flexibleSearchService;

	
	@Override
	public List<InvestSaudiSectorModel> getContactDepartment() {
		
		
		  final StringBuilder sql = new StringBuilder();
		  sql.append("SELECT {sector:"+InvestSaudiSectorModel.PK+"}	"); sql.append("FROM	");
		  sql.append("{	"); sql.append(InvestSaudiSectorModel._TYPECODE).append(" as sector");
		  sql.append("}	");
		  sql.append("Order By {sector:"+InvestSaudiSectorModel.RANKING+"}");
		  
		  final FlexibleSearchQuery searchQuery = new
		  FlexibleSearchQuery(sql.toString()); 
		  final SearchResult<InvestSaudiSectorModel> result= flexibleSearchService.search(searchQuery);
		  
		  return result.getResult();
		 
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
	
}
