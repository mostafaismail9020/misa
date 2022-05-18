package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface InfraLogisticsService {

	InfraLogisticsLandingModel getInfraLogisticsLandingModel();

	InfrastructureModel getInfrastructureModel();

	List<LengthOfNetworkModel> getLengthOfNetworkModel();

	EmploymentModel getEmploymentModel();

	TotalAreaModel getTotalAreaModel();

	HousingFacilitiesModel getHousingFacilitiesModel();

	IndustrialCitiesModel getIndustrialCitiesModel();

	List<PrivateCitiesModel> getPrivateCitiesModel();

	List<InfrastructureLogisticsModel> getInfrastructureLogisticsModel();

}
