package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;

import com.sap.ibso.eservices.facades.data.EmploymentData;
import com.sap.ibso.eservices.facades.data.LengthOfNetworkData;
import com.sap.ibso.eservices.facades.data.HousingFacilitiesData;
import com.sap.ibso.eservices.facades.data.IndustrialCitiesData;
import com.sap.ibso.eservices.facades.data.InfraLogisticsLandingData;
import com.sap.ibso.eservices.facades.data.InfrastructureData;
import com.sap.ibso.eservices.facades.data.PrivateCitiesData;
import com.sap.ibso.eservices.facades.data.TotalAreaData;

public interface InfraLogisticsFacade {
	InfraLogisticsLandingData getInfraLogisticsLandingData();
	
	InfrastructureData getInfrastructureData();

	List<LengthOfNetworkData> getLengthOfNetworkData();

	EmploymentData getEmploymentData();

	TotalAreaData getTotalAreaData();

	HousingFacilitiesData getHousingFacilitiesData();

	IndustrialCitiesData getIndustrialCitiesData();

	List<PrivateCitiesData> getPrivateCitiesListData();

}
