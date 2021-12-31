package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.AvgMonthlyWagesModel;
import com.sap.ibso.eservices.core.model.SaudiEmploymentByRegionModel;
import com.sap.ibso.eservices.core.model.NonSaudiEmploymentByRegionModel;
import com.sap.ibso.eservices.core.model.ExternalSectorModel;
import com.sap.ibso.eservices.core.model.FiscalSectorModel;
import com.sap.ibso.eservices.core.model.GraduatesByDegreeModel;
import com.sap.ibso.eservices.core.model.GrowthrateWagesModel;
import com.sap.ibso.eservices.core.model.LabourPrivateSectorModel;
import com.sap.ibso.eservices.core.model.MonetarySectorModel;
import com.sap.ibso.eservices.core.model.NonSaudiEmploymentModel;
import com.sap.ibso.eservices.core.model.NonSaudiUnemploymentModel;
import com.sap.ibso.eservices.core.model.OverallUnemploymentModel;
import com.sap.ibso.eservices.core.model.PopulationByRegionModel;
import com.sap.ibso.eservices.core.model.RealSectorModel;
import com.sap.ibso.eservices.core.model.SaudiEmploymentModel;
import com.sap.ibso.eservices.core.model.SaudiPopulationModel;
import com.sap.ibso.eservices.core.model.SaudiUnemploymentModel;

public interface SaudiEconomicSectorsDAO {

	List<RealSectorModel> getRealSectorModelBySearch();

	List<MonetarySectorModel> getMonetarySectorModelBySearch();

	List<FiscalSectorModel> getFiscalSectorModelBySearch();

	List<ExternalSectorModel> getExternalSectorModelBySearch();

	List<GraduatesByDegreeModel> getGraduatesByDegreeModelBySearch(int year);

	List<SaudiPopulationModel> getSaudiPopulationModelBySearch(int year);

	List<PopulationByRegionModel> getPopulationByRegionModelBySearch(int year);

	List<SaudiEmploymentByRegionModel> getSaudiEmploymentByRegionModelBySearch(int year);

	List<NonSaudiEmploymentByRegionModel> getNonSaudiEmploymentByRegionModelBySearch(int year);

	List<SaudiEmploymentModel> getSaudiEmploymentModelBySearch(int year);

	List<NonSaudiEmploymentModel> getNonSaudiEmploymentModelBySearch(int year);

	List<AvgMonthlyWagesModel> getAvgMonthlyWagesModelBySearch(int year);

	List<GrowthrateWagesModel> getGrowthrateWagesModelBySearch(int year);

	List<LabourPrivateSectorModel> getLabourPrivateSectorModelBySearch(int year);

	List<SaudiUnemploymentModel> getSaudiUnemploymentModelBySearch(int year);

	List<NonSaudiUnemploymentModel> getNonSaudiUnemploymentModelBySearch(int year);
	
	List<OverallUnemploymentModel> getOverallUnemploymentModelBySearch(int year);

}
