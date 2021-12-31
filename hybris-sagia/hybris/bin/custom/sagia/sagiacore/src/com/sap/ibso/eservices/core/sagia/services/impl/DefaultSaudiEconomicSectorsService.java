package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.AvgMonthlyWagesModel;
import com.sap.ibso.eservices.core.model.ExternalSectorModel;
import com.sap.ibso.eservices.core.model.FiscalSectorModel;
import com.sap.ibso.eservices.core.model.GraduatesByDegreeModel;
import com.sap.ibso.eservices.core.model.GrowthrateWagesModel;
import com.sap.ibso.eservices.core.model.LabourPrivateSectorModel;
import com.sap.ibso.eservices.core.model.MonetarySectorModel;
import com.sap.ibso.eservices.core.model.NonSaudiEmploymentByRegionModel;
import com.sap.ibso.eservices.core.model.NonSaudiEmploymentModel;
import com.sap.ibso.eservices.core.model.NonSaudiUnemploymentModel;
import com.sap.ibso.eservices.core.model.OverallUnemploymentModel;
import com.sap.ibso.eservices.core.model.PopulationByRegionModel;
import com.sap.ibso.eservices.core.model.RealSectorModel;
import com.sap.ibso.eservices.core.model.SaudiEmploymentByRegionModel;
import com.sap.ibso.eservices.core.model.SaudiEmploymentModel;
import com.sap.ibso.eservices.core.model.SaudiPopulationModel;
import com.sap.ibso.eservices.core.model.SaudiUnemploymentModel;
import com.sap.ibso.eservices.core.sagia.dao.SaudiEconomicSectorsDAO;
import com.sap.ibso.eservices.core.sagia.services.SaudiEconomicSectorsService;

public class DefaultSaudiEconomicSectorsService implements SaudiEconomicSectorsService {

	private SaudiEconomicSectorsDAO saudiEconomicSectorsDAO;

	@Override
	public List<RealSectorModel> getRealSectorModel() {
		final List<RealSectorModel> realSectorModel = getSaudiEconomicSectorsDAO().getRealSectorModelBySearch();
		return realSectorModel;
	}

	@Override
	public List<MonetarySectorModel> getMonetarySectorModel() {
		final List<MonetarySectorModel> monetarySectorModel = getSaudiEconomicSectorsDAO()
				.getMonetarySectorModelBySearch();
		return monetarySectorModel;
	}

	@Override
	public List<FiscalSectorModel> getFiscalSectorModel() {
		final List<FiscalSectorModel> fiscalSectorModel = getSaudiEconomicSectorsDAO().getFiscalSectorModelBySearch();
		return fiscalSectorModel;
	}

	@Override
	public List<ExternalSectorModel> getExternalSectorModel() {
		final List<ExternalSectorModel> externalSectorModel = getSaudiEconomicSectorsDAO()
				.getExternalSectorModelBySearch();
		return externalSectorModel;
	}

	@Override
	public List<GraduatesByDegreeModel> getGraduatesByDegreeModel(int year) {
		final List<GraduatesByDegreeModel> graduatesByDegreeModel = getSaudiEconomicSectorsDAO()
				.getGraduatesByDegreeModelBySearch(year);
		return graduatesByDegreeModel;
	}

	@Override
	public List<SaudiPopulationModel> getSaudiPopulationModel(int year) {
		final List<SaudiPopulationModel> saudiPopulationModel = getSaudiEconomicSectorsDAO()
				.getSaudiPopulationModelBySearch(year);
		return saudiPopulationModel;
	}

	@Override
	public List<PopulationByRegionModel> getPopulationByRegionModel(int year) {
		final List<PopulationByRegionModel> populationByRegionModel = getSaudiEconomicSectorsDAO()
				.getPopulationByRegionModelBySearch(year);
		return populationByRegionModel;
	}

	@Override
	public List<SaudiEmploymentByRegionModel> getSaudiEmploymentByRegionModel(int year) {
		final List<SaudiEmploymentByRegionModel> saudiEmploymentByRegionModel = getSaudiEconomicSectorsDAO()
				.getSaudiEmploymentByRegionModelBySearch(year);
		return saudiEmploymentByRegionModel;
	}

	@Override
	public List<NonSaudiEmploymentByRegionModel> getNonSaudiEmploymentByRegionModel(int year) {
		final List<NonSaudiEmploymentByRegionModel> nonSaudiEmploymentByRegionModel = getSaudiEconomicSectorsDAO()
				.getNonSaudiEmploymentByRegionModelBySearch(year);
		return nonSaudiEmploymentByRegionModel;
	}

	@Override
	public List<SaudiEmploymentModel> getSaudiEmploymentModel(int year) {
		final List<SaudiEmploymentModel> saudiEmploymentModel = getSaudiEconomicSectorsDAO()
				.getSaudiEmploymentModelBySearch(year);
		return saudiEmploymentModel;
	}

	@Override
	public List<NonSaudiEmploymentModel> getNonSaudiEmploymentModel(int year) {
		final List<NonSaudiEmploymentModel> nonSaudiEmploymentModel = getSaudiEconomicSectorsDAO()
				.getNonSaudiEmploymentModelBySearch(year);
		return nonSaudiEmploymentModel;
	}

	@Override
	public List<AvgMonthlyWagesModel> getAvgMonthlyWagesModel(int year) {
		final List<AvgMonthlyWagesModel> avgMonthlyWagesModel = getSaudiEconomicSectorsDAO()
				.getAvgMonthlyWagesModelBySearch(year);
		return avgMonthlyWagesModel;
	}

	@Override
	public List<GrowthrateWagesModel> getGrowthrateWagesModel(int year) {
		final List<GrowthrateWagesModel> growthrateWagesModel = getSaudiEconomicSectorsDAO()
				.getGrowthrateWagesModelBySearch(year);
		return growthrateWagesModel;
	}

	@Override
	public List<LabourPrivateSectorModel> getLabourPrivateSectorModel(int year) {
		final List<LabourPrivateSectorModel> labourPrivateSectorModel = getSaudiEconomicSectorsDAO()
				.getLabourPrivateSectorModelBySearch(year);
		return labourPrivateSectorModel;
	}

	@Override
	public List<SaudiUnemploymentModel> getSaudiUnemploymentModel(int year) {
		final List<SaudiUnemploymentModel> saudiUnemploymentModel = getSaudiEconomicSectorsDAO()
				.getSaudiUnemploymentModelBySearch(year);
		return saudiUnemploymentModel;
	}

	@Override
	public List<NonSaudiUnemploymentModel> getNonSaudiUnemploymentModel(int year) {
		final List<NonSaudiUnemploymentModel> NonSaudiUnemploymentModel = getSaudiEconomicSectorsDAO()
				.getNonSaudiUnemploymentModelBySearch(year);
		return NonSaudiUnemploymentModel;
	}
	
	@Override
	public List<OverallUnemploymentModel> getOverallUnemploymentModel(int year) {
		final List<OverallUnemploymentModel> OverallUnemploymentModel = getSaudiEconomicSectorsDAO()
				.getOverallUnemploymentModelBySearch(year);
		return OverallUnemploymentModel;
	}


	public SaudiEconomicSectorsDAO getSaudiEconomicSectorsDAO() {
		return saudiEconomicSectorsDAO;
	}

	public void setSaudiEconomicSectorsDAO(SaudiEconomicSectorsDAO saudiEconomicSectorsDAO) {
		this.saudiEconomicSectorsDAO = saudiEconomicSectorsDAO;
	}

}
