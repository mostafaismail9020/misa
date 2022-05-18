package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.*;

import com.sap.ibso.eservices.core.sagia.dao.EconomicDAO;
import com.sap.ibso.eservices.core.sagia.services.EconomicService;

import java.util.List;

public class DefaultEconomicService implements EconomicService{
	
	private EconomicDAO economicDAO;

	@Override
	public List<DashboardModel> getAllDashboardModels() {
		return getEconomicDAO().getAllDashboardModelBySearch();
	}


	@Override
	public CreditRatingModel getCreditRatingModel()
	{
		final CreditRatingModel creditRating = getEconomicDAO().getCreditRatingModelBySearch();
		return creditRating;
	}
	

	public EconomicDAO getEconomicDAO() {
		return economicDAO;
	}

	public void setEconomicDAO(EconomicDAO economicDAO) {
		this.economicDAO = economicDAO;
	}

	
}
