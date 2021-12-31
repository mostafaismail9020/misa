package com.sap.ibso.eservices.facades.sagia.economic;

import java.util.List;

import com.sap.ibso.eservices.facades.data.AvgMonthlyWagesData;
import com.sap.ibso.eservices.facades.data.ExternalSectorData;
import com.sap.ibso.eservices.facades.data.FiscalSectorData;
import com.sap.ibso.eservices.facades.data.GraduatesByDegreeData;
import com.sap.ibso.eservices.facades.data.GrowthrateWagesData;
import com.sap.ibso.eservices.facades.data.LabourPrivateSectorData;
import com.sap.ibso.eservices.facades.data.MonetarySectorData;
import com.sap.ibso.eservices.facades.data.NonSaudiEmploymentByRegionData;
import com.sap.ibso.eservices.facades.data.NonSaudiEmploymentData;
import com.sap.ibso.eservices.facades.data.NonSaudiUnemploymentData;
import com.sap.ibso.eservices.facades.data.OverallUnemploymentData;
import com.sap.ibso.eservices.facades.data.PopulationByRegionData;
import com.sap.ibso.eservices.facades.data.RealSectorData;
import com.sap.ibso.eservices.facades.data.SaudiEmploymentByRegionData;
import com.sap.ibso.eservices.facades.data.SaudiEmploymentData;
import com.sap.ibso.eservices.facades.data.SaudiPopulationData;
import com.sap.ibso.eservices.facades.data.SaudiUnemploymentData;

public interface SaudiEconomicSectorsFacade {

	List<RealSectorData> getRealSectorData();

	List<MonetarySectorData> getMonetarySectorData();

	List<FiscalSectorData> getFiscalSectorData();

	List<ExternalSectorData> getExternalSectorData();

	List<GraduatesByDegreeData> getGraduatesByDegreeData(int year);

	List<SaudiPopulationData> getSaudiPopulationData(int year);

	List<PopulationByRegionData> getPopulationByRegionData(int year);

	List<SaudiEmploymentByRegionData> getSaudiEmploymentByRegionData(int year);

	List<NonSaudiEmploymentByRegionData> getNonSaudiEmploymentByRegionData(int year);

	List<SaudiEmploymentData> getSaudiEmploymentData(int year);

	List<NonSaudiEmploymentData> getNonsaudiEmploymentData(int year);

	List<AvgMonthlyWagesData> getAvgMonthlyWagesData(int year);

	List<GrowthrateWagesData> getGrowthrateWagesData(int year);

	List<LabourPrivateSectorData> getLabourPrivateSectorData(int year);

	List<SaudiUnemploymentData> getSaudiUnemploymentData(int year);

	List<NonSaudiUnemploymentData> getNonSaudiUnemploymentData(int year);

	List<OverallUnemploymentData> getOverallUnemploymentData(int year);

}
