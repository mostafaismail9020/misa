package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;

import com.sap.ibso.eservices.core.model.InfrastructureLogisticsModel;
import com.sap.ibso.eservices.facades.data.*;

public interface InfraLogisticsFacade {

	InfraLogisticsLandingData getInfraLogisticsLandingData();

	List<LengthOfNetworkData> getLengthOfNetworkData();

	List<PrivateCitiesData> getPrivateCitiesListData();

	List<InfrastructureLogisticsData> getInfrastructureLogisticsData();

}
