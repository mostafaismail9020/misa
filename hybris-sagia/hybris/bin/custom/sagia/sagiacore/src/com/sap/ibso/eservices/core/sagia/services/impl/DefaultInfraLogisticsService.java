package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.EmploymentModel;
import com.sap.ibso.eservices.core.model.HousingFacilitiesModel;
import com.sap.ibso.eservices.core.model.IndustrialCitiesModel;
import com.sap.ibso.eservices.core.model.InfraLogisticsLandingModel;
import com.sap.ibso.eservices.core.model.InfrastructureModel;
import com.sap.ibso.eservices.core.model.LengthOfNetworkModel;
import com.sap.ibso.eservices.core.model.PrivateCitiesModel;
import com.sap.ibso.eservices.core.model.TotalAreaModel;
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
	public InfrastructureModel getInfrastructureModel() {
		final InfrastructureModel InfrastructureModel = getInfraLogisticsDAO().getInfrastructureModelBySearch();
		return InfrastructureModel;
	}

	@Override
	public List<LengthOfNetworkModel> getLengthOfNetworkModel() {
		final List<LengthOfNetworkModel> lengthOfNetworkModel = getInfraLogisticsDAO()
				.getLengthOfNetworkModelBySearch();
		return lengthOfNetworkModel;
	}

	@Override
	public EmploymentModel getEmploymentModel() {
		final EmploymentModel employmentModel = getInfraLogisticsDAO().getEmploymentModelBySearch();
		return employmentModel;
	}

	@Override
	public TotalAreaModel getTotalAreaModel() {
		final TotalAreaModel totalAreaModel = getInfraLogisticsDAO().getTotalAreaModelBySearch();
		return totalAreaModel;
	}

	@Override
	public HousingFacilitiesModel getHousingFacilitiesModel() {
		final HousingFacilitiesModel housingFacilitiesModel = getInfraLogisticsDAO()
				.getHousingFacilitiesModelBySearch();
		return housingFacilitiesModel;
	}

	@Override
	public IndustrialCitiesModel getIndustrialCitiesModel() {
		final IndustrialCitiesModel industrialCitiesModel = getInfraLogisticsDAO().getIndustrialCitiesModelBySearch();
		return industrialCitiesModel;
	}

	@Override
	public List<PrivateCitiesModel> getPrivateCitiesModel() {
		final List<PrivateCitiesModel> privateCitiesModel = getInfraLogisticsDAO().getPrivateCitiesModelBySearch();
		return privateCitiesModel;
	}

}
