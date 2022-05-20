package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.facades.data.*;

public interface SaudiEconomicSectorsFacade {

	List<GraduatesByDegreeData> getGraduatesByDegreeData(int year);

	Map<String, List<EconomicSectorData>> getEconomicSectorData();

	List<PopulationDistributionData> getPopulationDistributionData(String popDistribution, int year);

	List<GenderDistributionData> getGenderDistributionData(String distribution, int year);

	List<RegionDistributionData> getRegionDistributionData(String distribution, int year);
}
