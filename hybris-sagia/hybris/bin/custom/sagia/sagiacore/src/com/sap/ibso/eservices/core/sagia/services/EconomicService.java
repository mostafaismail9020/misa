package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.*;

import java.util.List;

public interface EconomicService {

	List<DashboardModel> getAllDashboardModels();
	CreditRatingModel getCreditRatingModel();

}