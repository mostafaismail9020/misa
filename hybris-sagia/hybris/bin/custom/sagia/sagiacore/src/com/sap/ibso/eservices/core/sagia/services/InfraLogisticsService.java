package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.EmploymentModel;
import com.sap.ibso.eservices.core.model.HousingFacilitiesModel;
import com.sap.ibso.eservices.core.model.IndustrialCitiesModel;
import com.sap.ibso.eservices.core.model.InfraLogisticsLandingModel;
import com.sap.ibso.eservices.core.model.InfrastructureModel;
import com.sap.ibso.eservices.core.model.LengthOfNetworkModel;
import com.sap.ibso.eservices.core.model.PrivateCitiesModel;
import com.sap.ibso.eservices.core.model.TotalAreaModel;

public interface InfraLogisticsService {

	InfraLogisticsLandingModel getInfraLogisticsLandingModel();

	InfrastructureModel getInfrastructureModel();

	List<LengthOfNetworkModel> getLengthOfNetworkModel();

	EmploymentModel getEmploymentModel();

	TotalAreaModel getTotalAreaModel();

	HousingFacilitiesModel getHousingFacilitiesModel();

	IndustrialCitiesModel getIndustrialCitiesModel();

	List<PrivateCitiesModel> getPrivateCitiesModel();

}
