package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.InfraLogisticsDAO;
import com.sap.ibso.eservices.core.sagia.services.InfraLogisticsService;

public class DefaultInfraLogisticsService implements InfraLogisticsService {

	private InfraLogisticsDAO infraLogisticsDAO;

	/**
	 * @return the infraLogisticsDAO
	 */
	public InfraLogisticsDAO getInfraLogisticsDAO() {
		return infraLogisticsDAO;
	}

	/**
	 * @param infraLogisticsDAO the infraLogisticsDAO to set
	 */
	public void setInfraLogisticsDAO(InfraLogisticsDAO infraLogisticsDAO) {
		this.infraLogisticsDAO = infraLogisticsDAO;
	}

	@Override
	public InfraLogisticsLandingModel getInfraLogisticsLandingModel() {
		final InfraLogisticsLandingModel infraLogisticsLandingModel = getInfraLogisticsDAO()
				.getInfraLogisticsLandingModelBySearch();
		return infraLogisticsLandingModel;
	}

	@Override
	public List<LengthOfNetworkModel> getLengthOfNetworkModel() {
		final List<LengthOfNetworkModel> lengthOfNetworkModel = getInfraLogisticsDAO()
				.getLengthOfNetworkModelBySearch();
		return lengthOfNetworkModel;
	}

	@Override
	public List<PrivateCitiesModel> getPrivateCitiesModel() {
		final List<PrivateCitiesModel> privateCitiesModel = getInfraLogisticsDAO().getPrivateCitiesModelBySearch();
		return privateCitiesModel;
	}

	@Override
	public List<InfrastructureLogisticsModel> getInfrastructureLogisticsModel() {
		final List<InfrastructureLogisticsModel> infrastructureLogisticsModels = getInfraLogisticsDAO().getAllInfrastructureLogisticsModel();
		return infrastructureLogisticsModels;
	}

}
