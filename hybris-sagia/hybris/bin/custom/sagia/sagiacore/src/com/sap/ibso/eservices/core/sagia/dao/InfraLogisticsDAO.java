package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.EmploymentModel;
import com.sap.ibso.eservices.core.model.HousingFacilitiesModel;
import com.sap.ibso.eservices.core.model.IndustrialCitiesModel;
import com.sap.ibso.eservices.core.model.InfraLogisticsLandingModel;
import com.sap.ibso.eservices.core.model.InfrastructureModel;
import com.sap.ibso.eservices.core.model.LengthOfNetworkModel;
import com.sap.ibso.eservices.core.model.PrivateCitiesModel;
import com.sap.ibso.eservices.core.model.TotalAreaModel;

public interface InfraLogisticsDAO {

	InfraLogisticsLandingModel getInfraLogisticsLandingModelBySearch();
	
	InfrastructureModel getInfrastructureModelBySearch();
	
	List<LengthOfNetworkModel> getLengthOfNetworkModelBySearch();

	EmploymentModel getEmploymentModelBySearch();

	TotalAreaModel getTotalAreaModelBySearch();

	HousingFacilitiesModel getHousingFacilitiesModelBySearch();

	IndustrialCitiesModel getIndustrialCitiesModelBySearch();
	
	List<PrivateCitiesModel> getPrivateCitiesModelBySearch();

}
