package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.SaudiEconomicSectorsDAO;
import com.sap.ibso.eservices.core.sagia.services.SaudiEconomicSectorsService;

public class DefaultSaudiEconomicSectorsService implements SaudiEconomicSectorsService {

	private SaudiEconomicSectorsDAO saudiEconomicSectorsDAO;

	@Override
	public List<GraduatesByDegreeModel> getGraduatesByDegreeModel(int year) {
		return getSaudiEconomicSectorsDAO()
				.getGraduatesByDegreeModelBySearch(year);
	}

	@Override
	public List<EconomicsSectorModel> getEconomicSectorModels() {
		return getSaudiEconomicSectorsDAO().getAllEconomicSectors();
	}

	@Override
	public List<PopulationDistributionModel> getPopulationDistributionModels(String popDistribution, int year) {
		return getSaudiEconomicSectorsDAO().getAllPopulationDistributions(popDistribution, year);
	}

	@Override
	public List<GenderDistributionModel> getGenderDistributionModels(String distribution, int year) {
		return getSaudiEconomicSectorsDAO().getGenderDistributionModelsBySearch(distribution, year);
	}

	@Override
	public List<RegionDistributionModel> getRegionDistributionModel(String distribution, int year) {
		return getSaudiEconomicSectorsDAO().getRegionDistributionModelsBySearch(distribution, year);
	}


	public SaudiEconomicSectorsDAO getSaudiEconomicSectorsDAO() {
		return saudiEconomicSectorsDAO;
	}

	public void setSaudiEconomicSectorsDAO(SaudiEconomicSectorsDAO saudiEconomicSectorsDAO) {
		this.saudiEconomicSectorsDAO = saudiEconomicSectorsDAO;
	}

}
