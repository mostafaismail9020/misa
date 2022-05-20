package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface InfraLogisticsDAO {

	InfraLogisticsLandingModel getInfraLogisticsLandingModelBySearch();
	
	List<LengthOfNetworkModel> getLengthOfNetworkModelBySearch();
	
	List<PrivateCitiesModel> getPrivateCitiesModelBySearch();

	List<InfrastructureLogisticsModel> getAllInfrastructureLogisticsModel();

}
