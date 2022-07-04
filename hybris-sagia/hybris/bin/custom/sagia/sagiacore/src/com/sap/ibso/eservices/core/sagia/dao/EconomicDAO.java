package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.*;

import java.util.List;


public interface EconomicDAO {

	List<DashboardModel> getAllDashboardModelBySearch();
	CreditRatingModel getCreditRatingModelBySearch();
}
