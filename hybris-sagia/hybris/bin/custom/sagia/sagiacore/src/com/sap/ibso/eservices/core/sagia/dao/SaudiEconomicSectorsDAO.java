package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;

public interface SaudiEconomicSectorsDAO {

	List<GraduatesByDegreeModel> getGraduatesByDegreeModelBySearch(int year);

	List<EconomicsSectorModel> getAllEconomicSectors();

	List<PopulationDistributionModel> getAllPopulationDistributions(String popDistribution, int year);

    List<GenderDistributionModel> getGenderDistributionModelsBySearch(String distribution, int year);

    List<RegionDistributionModel> getRegionDistributionModelsBySearch(String distribution, int year);
}
