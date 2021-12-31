package com.sap.ibso.eservices.core.sagia.services;

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

public interface SaudiEconomicSectorsService {

	List<RealSectorModel> getRealSectorModel();

	List<MonetarySectorModel> getMonetarySectorModel();

	List<FiscalSectorModel> getFiscalSectorModel();

	List<ExternalSectorModel> getExternalSectorModel();

	List<GraduatesByDegreeModel> getGraduatesByDegreeModel(int year);

	List<SaudiPopulationModel> getSaudiPopulationModel(int year);

	List<PopulationByRegionModel> getPopulationByRegionModel(int year);

	List<SaudiEmploymentByRegionModel> getSaudiEmploymentByRegionModel(int year);

	List<NonSaudiEmploymentByRegionModel> getNonSaudiEmploymentByRegionModel(int year);

	List<SaudiEmploymentModel> getSaudiEmploymentModel(int year);

	List<NonSaudiEmploymentModel> getNonSaudiEmploymentModel(int year);

	List<AvgMonthlyWagesModel> getAvgMonthlyWagesModel(int year);

	List<GrowthrateWagesModel> getGrowthrateWagesModel(int year);

	List<LabourPrivateSectorModel> getLabourPrivateSectorModel(int year);

	List<SaudiUnemploymentModel> getSaudiUnemploymentModel(int year);

	List<NonSaudiUnemploymentModel> getNonSaudiUnemploymentModel(int year);
	
	List<OverallUnemploymentModel> getOverallUnemploymentModel(int year);

}
