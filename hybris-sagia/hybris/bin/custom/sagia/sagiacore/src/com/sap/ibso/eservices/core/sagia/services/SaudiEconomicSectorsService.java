package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface SaudiEconomicSectorsService {

	List<GraduatesByDegreeModel> getGraduatesByDegreeModel(int year);

	List<EconomicsSectorModel> getEconomicSectorModels();

	List<PopulationDistributionModel> getPopulationDistributionModels(String popDistribution, int year);

    List<GenderDistributionModel> getGenderDistributionModels(String distribution, int year);

    List<RegionDistributionModel> getRegionDistributionModel(String distribution, int year);
}
