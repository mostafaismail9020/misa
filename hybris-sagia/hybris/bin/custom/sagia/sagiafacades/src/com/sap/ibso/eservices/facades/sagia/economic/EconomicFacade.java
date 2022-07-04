package com.sap.ibso.eservices.facades.sagia.economic;

import com.sap.ibso.eservices.facades.data.*;

import java.util.List;

public interface EconomicFacade {

	List<DashboardData> getAllDashboardData();

	CreditRatingData getCreditRatingData();

}
