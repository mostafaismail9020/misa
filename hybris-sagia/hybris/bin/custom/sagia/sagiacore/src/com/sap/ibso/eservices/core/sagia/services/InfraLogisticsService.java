package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface InfraLogisticsService {

	InfraLogisticsLandingModel getInfraLogisticsLandingModel();

	List<LengthOfNetworkModel> getLengthOfNetworkModel();

	List<PrivateCitiesModel> getPrivateCitiesModel();

	List<InfrastructureLogisticsModel> getInfrastructureLogisticsModel();

}
