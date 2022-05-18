package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface InfraLogisticsDAO {

	InfraLogisticsLandingModel getInfraLogisticsLandingModelBySearch();
	
	InfrastructureModel getInfrastructureModelBySearch();
	
	List<LengthOfNetworkModel> getLengthOfNetworkModelBySearch();

	EmploymentModel getEmploymentModelBySearch();

	TotalAreaModel getTotalAreaModelBySearch();

	HousingFacilitiesModel getHousingFacilitiesModelBySearch();

	IndustrialCitiesModel getIndustrialCitiesModelBySearch();
	
	List<PrivateCitiesModel> getPrivateCitiesModelBySearch();

	List<InfrastructureLogisticsModel> getAllInfrastructureLogisticsModel();

}
