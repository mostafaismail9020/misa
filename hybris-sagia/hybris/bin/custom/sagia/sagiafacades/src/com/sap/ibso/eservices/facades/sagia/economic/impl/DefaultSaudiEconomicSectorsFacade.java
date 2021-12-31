package com.sap.ibso.eservices.facades.sagia.economic.impl;

import java.util.ArrayList;
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
import com.sap.ibso.eservices.core.sagia.services.SaudiEconomicSectorsService;
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
import com.sap.ibso.eservices.facades.sagia.economic.SaudiEconomicSectorsFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultSaudiEconomicSectorsFacade implements SaudiEconomicSectorsFacade {

	private SaudiEconomicSectorsService saudiEconomicSectorsService;
	private Converter<RealSectorModel, RealSectorData> realSectorConverter;
	private Converter<MonetarySectorModel, MonetarySectorData> monetarySectorConverter;
	private Converter<FiscalSectorModel, FiscalSectorData> fiscalSectorConverter;
	private Converter<ExternalSectorModel, ExternalSectorData> externalSectorConverter;
	private Converter<GraduatesByDegreeModel, GraduatesByDegreeData> graduatesByDegreeConverter;
	private Converter<SaudiPopulationModel, SaudiPopulationData> saudiPopulationConverter;
	private Converter<PopulationByRegionModel, PopulationByRegionData> populationByRegionConverter;
	private Converter<SaudiEmploymentByRegionModel, SaudiEmploymentByRegionData> saudiEmploymentByRegionConverter;
	private Converter<NonSaudiEmploymentByRegionModel, NonSaudiEmploymentByRegionData> nonSaudiEmploymentByRegionConverter;
	private Converter<SaudiEmploymentModel, SaudiEmploymentData> saudiEmploymentConverter;
	private Converter<NonSaudiEmploymentModel, NonSaudiEmploymentData> nonSaudiEmploymentConverter;
	private Converter<AvgMonthlyWagesModel, AvgMonthlyWagesData> avgMonthlyWagesConverter;
	private Converter<GrowthrateWagesModel, GrowthrateWagesData> growthrateWagesConverter;
	private Converter<LabourPrivateSectorModel, LabourPrivateSectorData> labourPrivateSectorConverter;
	private Converter<SaudiUnemploymentModel, SaudiUnemploymentData> saudiUnemploymentConverter;
	private Converter<NonSaudiUnemploymentModel, NonSaudiUnemploymentData> nonSaudiUnemploymentConverter;
	private Converter<OverallUnemploymentModel, OverallUnemploymentData> OverallUnemploymentConverter;

	public SaudiEconomicSectorsService getSaudiEconomicSectorsService() {
		return saudiEconomicSectorsService;
	}

	public void setSaudiEconomicSectorsService(SaudiEconomicSectorsService saudiEconomicSectorsService) {
		this.saudiEconomicSectorsService = saudiEconomicSectorsService;
	}

	public Converter<RealSectorModel, RealSectorData> getRealSectorConverter() {
		return realSectorConverter;
	}

	public void setRealSectorConverter(Converter<RealSectorModel, RealSectorData> realSectorConverter) {
		this.realSectorConverter = realSectorConverter;
	}

	public Converter<MonetarySectorModel, MonetarySectorData> getMonetarySectorConverter() {
		return monetarySectorConverter;
	}

	public void setMonetarySectorConverter(Converter<MonetarySectorModel, MonetarySectorData> monetarySectorConverter) {
		this.monetarySectorConverter = monetarySectorConverter;
	}

	public Converter<FiscalSectorModel, FiscalSectorData> getFiscalSectorConverter() {
		return fiscalSectorConverter;
	}

	public void setFiscalSectorConverter(Converter<FiscalSectorModel, FiscalSectorData> fiscalSectorConverter) {
		this.fiscalSectorConverter = fiscalSectorConverter;
	}

	public Converter<ExternalSectorModel, ExternalSectorData> getExternalSectorConverter() {
		return externalSectorConverter;
	}

	public void setExternalSectorConverter(Converter<ExternalSectorModel, ExternalSectorData> externalSectorConverter) {
		this.externalSectorConverter = externalSectorConverter;
	}

	public Converter<GraduatesByDegreeModel, GraduatesByDegreeData> getGraduatesByDegreeConverter() {
		return graduatesByDegreeConverter;
	}

	public void setGraduatesByDegreeConverter(
			Converter<GraduatesByDegreeModel, GraduatesByDegreeData> graduatesByDegreeConverter) {
		this.graduatesByDegreeConverter = graduatesByDegreeConverter;
	}

	public Converter<SaudiPopulationModel, SaudiPopulationData> getSaudiPopulationConverter() {
		return saudiPopulationConverter;
	}

	public void setSaudiPopulationConverter(
			Converter<SaudiPopulationModel, SaudiPopulationData> saudiPopulationConverter) {
		this.saudiPopulationConverter = saudiPopulationConverter;
	}

	public Converter<PopulationByRegionModel, PopulationByRegionData> getPopulationByRegionConverter() {
		return populationByRegionConverter;
	}

	public void setPopulationByRegionConverter(
			Converter<PopulationByRegionModel, PopulationByRegionData> populationByRegionConverter) {
		this.populationByRegionConverter = populationByRegionConverter;
	}

	public Converter<SaudiEmploymentByRegionModel, SaudiEmploymentByRegionData> getSaudiEmploymentByRegionConverter() {
		return saudiEmploymentByRegionConverter;
	}

	public void setSaudiEmploymentByRegionConverter(
			Converter<SaudiEmploymentByRegionModel, SaudiEmploymentByRegionData> saudiEmploymentByRegionConverter) {
		this.saudiEmploymentByRegionConverter = saudiEmploymentByRegionConverter;
	}

	public Converter<NonSaudiEmploymentByRegionModel, NonSaudiEmploymentByRegionData> getNonSaudiEmploymentByRegionConverter() {
		return nonSaudiEmploymentByRegionConverter;
	}

	public void setNonSaudiEmploymentByRegionConverter(
			Converter<NonSaudiEmploymentByRegionModel, NonSaudiEmploymentByRegionData> nonSaudiEmploymentByRegionConverter) {
		this.nonSaudiEmploymentByRegionConverter = nonSaudiEmploymentByRegionConverter;
	}

	public Converter<SaudiEmploymentModel, SaudiEmploymentData> getSaudiEmploymentConverter() {
		return saudiEmploymentConverter;
	}

	public void setSaudiEmploymentConverter(
			Converter<SaudiEmploymentModel, SaudiEmploymentData> saudiEmploymentConverter) {
		this.saudiEmploymentConverter = saudiEmploymentConverter;
	}

	public Converter<NonSaudiEmploymentModel, NonSaudiEmploymentData> getNonSaudiEmploymentConverter() {
		return nonSaudiEmploymentConverter;
	}

	public void setNonSaudiEmploymentConverter(
			Converter<NonSaudiEmploymentModel, NonSaudiEmploymentData> nonSaudiEmploymentConverter) {
		this.nonSaudiEmploymentConverter = nonSaudiEmploymentConverter;
	}

	public Converter<AvgMonthlyWagesModel, AvgMonthlyWagesData> getAvgMonthlyWagesConverter() {
		return avgMonthlyWagesConverter;
	}

	public void setAvgMonthlyWagesConverter(
			Converter<AvgMonthlyWagesModel, AvgMonthlyWagesData> avgMonthlyWagesConverter) {
		this.avgMonthlyWagesConverter = avgMonthlyWagesConverter;
	}

	public Converter<GrowthrateWagesModel, GrowthrateWagesData> getGrowthrateWagesConverter() {
		return growthrateWagesConverter;
	}

	public void setGrowthrateWagesConverter(
			Converter<GrowthrateWagesModel, GrowthrateWagesData> growthrateWagesConverter) {
		this.growthrateWagesConverter = growthrateWagesConverter;
	}

	public Converter<LabourPrivateSectorModel, LabourPrivateSectorData> getLabourPrivateSectorConverter() {
		return labourPrivateSectorConverter;
	}

	public void setLabourPrivateSectorConverter(
			Converter<LabourPrivateSectorModel, LabourPrivateSectorData> labourPrivateSectorConverter) {
		this.labourPrivateSectorConverter = labourPrivateSectorConverter;
	}

	public Converter<SaudiUnemploymentModel, SaudiUnemploymentData> getSaudiUnemploymentConverter() {
		return saudiUnemploymentConverter;
	}

	public void setSaudiUnemploymentConverter(
			Converter<SaudiUnemploymentModel, SaudiUnemploymentData> saudiUnemploymentConverter) {
		this.saudiUnemploymentConverter = saudiUnemploymentConverter;
	}

	public Converter<NonSaudiUnemploymentModel, NonSaudiUnemploymentData> getNonSaudiUnemploymentConverter() {
		return nonSaudiUnemploymentConverter;
	}

	public void setNonSaudiUnemploymentConverter(
			Converter<NonSaudiUnemploymentModel, NonSaudiUnemploymentData> nonSaudiUnemploymentConverter) {
		this.nonSaudiUnemploymentConverter = nonSaudiUnemploymentConverter;
	}
	
	/**
	 * @return the overallUnemploymentConverter
	 */
	public Converter<OverallUnemploymentModel, OverallUnemploymentData> getOverallUnemploymentConverter() {
		return OverallUnemploymentConverter;
	}

	/**
	 * @param overallUnemploymentConverter the overallUnemploymentConverter to set
	 */
	public void setOverallUnemploymentConverter(
			Converter<OverallUnemploymentModel, OverallUnemploymentData> overallUnemploymentConverter) {
		OverallUnemploymentConverter = overallUnemploymentConverter;
	}

	@Override
	public List<RealSectorData> getRealSectorData() {

		final List<RealSectorModel> model = getSaudiEconomicSectorsService().getRealSectorModel();
		final List<RealSectorData> realSectorListData = new ArrayList<RealSectorData>();
		for (final RealSectorModel realSectorModel : model) {
			final RealSectorData rsData = getRealSectorConverter().convert(realSectorModel);
			realSectorListData.add(rsData);
		}
		return realSectorListData;
	}

	@Override
	public List<MonetarySectorData> getMonetarySectorData() {
		final List<MonetarySectorModel> model = getSaudiEconomicSectorsService().getMonetarySectorModel();
		final List<MonetarySectorData> monetarySectorListData = new ArrayList<MonetarySectorData>();
		for (final MonetarySectorModel monetarySectorModel : model) {
			final MonetarySectorData rsData = getMonetarySectorConverter().convert(monetarySectorModel);
			monetarySectorListData.add(rsData);
		}
		return monetarySectorListData;
	}

	@Override
	public List<FiscalSectorData> getFiscalSectorData() {
		final List<FiscalSectorModel> model = getSaudiEconomicSectorsService().getFiscalSectorModel();
		final List<FiscalSectorData> fiscalSectorListData = new ArrayList<FiscalSectorData>();
		for (final FiscalSectorModel fiscalSectorModel : model) {
			final FiscalSectorData rsData = getFiscalSectorConverter().convert(fiscalSectorModel);
			fiscalSectorListData.add(rsData);
		}
		return fiscalSectorListData;
	}

	@Override
	public List<ExternalSectorData> getExternalSectorData() {
		final List<ExternalSectorModel> model = getSaudiEconomicSectorsService().getExternalSectorModel();
		final List<ExternalSectorData> externalSectorListData = new ArrayList<ExternalSectorData>();
		for (final ExternalSectorModel externalSectorModel : model) {
			final ExternalSectorData rsData = getExternalSectorConverter().convert(externalSectorModel);
			externalSectorListData.add(rsData);
		}
		return externalSectorListData;
	}

	@Override
	public List<GraduatesByDegreeData> getGraduatesByDegreeData(int year) {
		final List<GraduatesByDegreeModel> model = getSaudiEconomicSectorsService().getGraduatesByDegreeModel(year);
		final List<GraduatesByDegreeData> graduatesByDegreeListData = new ArrayList<GraduatesByDegreeData>();
		for (final GraduatesByDegreeModel graduatesByDegreeModel : model) {
			final GraduatesByDegreeData rsData = getGraduatesByDegreeConverter().convert(graduatesByDegreeModel);
			graduatesByDegreeListData.add(rsData);
		}
		return graduatesByDegreeListData;
	}

	@Override
	public List<SaudiPopulationData> getSaudiPopulationData(int year) {
		final List<SaudiPopulationModel> model = getSaudiEconomicSectorsService().getSaudiPopulationModel(year);
		final List<SaudiPopulationData> saudiPopulationListData = new ArrayList<SaudiPopulationData>();
		for (final SaudiPopulationModel saudiPopulationModel : model) {
			final SaudiPopulationData rsData = getSaudiPopulationConverter().convert(saudiPopulationModel);
			saudiPopulationListData.add(rsData);
		}
		return saudiPopulationListData;
	}

	@Override
	public List<PopulationByRegionData> getPopulationByRegionData(int year) {
		final List<PopulationByRegionModel> model = getSaudiEconomicSectorsService().getPopulationByRegionModel(year);
		final List<PopulationByRegionData> populationByRegionListData = new ArrayList<PopulationByRegionData>();
		for (final PopulationByRegionModel populationByRegionModel : model) {
			final PopulationByRegionData rsData = getPopulationByRegionConverter().convert(populationByRegionModel);
			populationByRegionListData.add(rsData);
		}
		return populationByRegionListData;
	}

	@Override
	public List<SaudiEmploymentByRegionData> getSaudiEmploymentByRegionData(int year) {
		final List<SaudiEmploymentByRegionModel> model = getSaudiEconomicSectorsService()
				.getSaudiEmploymentByRegionModel(year);
		final List<SaudiEmploymentByRegionData> saudiEmploymentByRegionListData = new ArrayList<SaudiEmploymentByRegionData>();
		for (final SaudiEmploymentByRegionModel saudiEmploymentByRegionModel : model) {
			final SaudiEmploymentByRegionData rsData = getSaudiEmploymentByRegionConverter()
					.convert(saudiEmploymentByRegionModel);
			saudiEmploymentByRegionListData.add(rsData);
		}
		return saudiEmploymentByRegionListData;
	}

	@Override
	public List<NonSaudiEmploymentByRegionData> getNonSaudiEmploymentByRegionData(int year) {
		final List<NonSaudiEmploymentByRegionModel> model = getSaudiEconomicSectorsService()
				.getNonSaudiEmploymentByRegionModel(year);
		final List<NonSaudiEmploymentByRegionData> nonSaudiEmploymentByRegionListData = new ArrayList<NonSaudiEmploymentByRegionData>();
		for (final NonSaudiEmploymentByRegionModel nonSaudiEmploymentByRegionModel : model) {
			final NonSaudiEmploymentByRegionData rsData = getNonSaudiEmploymentByRegionConverter()
					.convert(nonSaudiEmploymentByRegionModel);
			nonSaudiEmploymentByRegionListData.add(rsData);
		}
		return nonSaudiEmploymentByRegionListData;
	}

	@Override
	public List<SaudiEmploymentData> getSaudiEmploymentData(int year) {
		final List<SaudiEmploymentModel> model = getSaudiEconomicSectorsService().getSaudiEmploymentModel(year);
		final List<SaudiEmploymentData> saudiEmploymentListData = new ArrayList<SaudiEmploymentData>();
		for (final SaudiEmploymentModel saudiEmploymentModel : model) {
			final SaudiEmploymentData rsData = getSaudiEmploymentConverter().convert(saudiEmploymentModel);
			saudiEmploymentListData.add(rsData);
		}
		return saudiEmploymentListData;
	}

	@Override
	public List<NonSaudiEmploymentData> getNonsaudiEmploymentData(int year) {
		final List<NonSaudiEmploymentModel> model = getSaudiEconomicSectorsService().getNonSaudiEmploymentModel(year);
		final List<NonSaudiEmploymentData> nonSaudiEmploymentListData = new ArrayList<NonSaudiEmploymentData>();
		for (final NonSaudiEmploymentModel nonSaudiEmploymentModel : model) {
			final NonSaudiEmploymentData rsData = getNonSaudiEmploymentConverter().convert(nonSaudiEmploymentModel);
			nonSaudiEmploymentListData.add(rsData);
		}
		return nonSaudiEmploymentListData;
	}

	@Override
	public List<AvgMonthlyWagesData> getAvgMonthlyWagesData(int year) {
		final List<AvgMonthlyWagesModel> model = getSaudiEconomicSectorsService().getAvgMonthlyWagesModel(year);
		final List<AvgMonthlyWagesData> avgMonthlyWagesListData = new ArrayList<AvgMonthlyWagesData>();
		for (final AvgMonthlyWagesModel avgMonthlyWagesModel : model) {
			final AvgMonthlyWagesData rsData = getAvgMonthlyWagesConverter().convert(avgMonthlyWagesModel);
			avgMonthlyWagesListData.add(rsData);
		}
		return avgMonthlyWagesListData;
	}

	@Override
	public List<GrowthrateWagesData> getGrowthrateWagesData(int year) {
		final List<GrowthrateWagesModel> model = getSaudiEconomicSectorsService().getGrowthrateWagesModel(year);
		final List<GrowthrateWagesData> growthrateWagesListData = new ArrayList<GrowthrateWagesData>();
		for (final GrowthrateWagesModel growthrateWagesModel : model) {
			final GrowthrateWagesData rsData = getGrowthrateWagesConverter().convert(growthrateWagesModel);
			growthrateWagesListData.add(rsData);
		}
		return growthrateWagesListData;
	}

	@Override
	public List<LabourPrivateSectorData> getLabourPrivateSectorData(int year) {
		final List<LabourPrivateSectorModel> model = getSaudiEconomicSectorsService().getLabourPrivateSectorModel(year);
		final List<LabourPrivateSectorData> labourPrivateSectorListData = new ArrayList<LabourPrivateSectorData>();
		for (final LabourPrivateSectorModel labourPrivateSectorModel : model) {
			final LabourPrivateSectorData rsData = getLabourPrivateSectorConverter().convert(labourPrivateSectorModel);
			labourPrivateSectorListData.add(rsData);
		}
		return labourPrivateSectorListData;
	}

	@Override
	public List<SaudiUnemploymentData> getSaudiUnemploymentData(int year) {
		final List<SaudiUnemploymentModel> model = getSaudiEconomicSectorsService().getSaudiUnemploymentModel(year);
		final List<SaudiUnemploymentData> saudiUnemploymentListData = new ArrayList<SaudiUnemploymentData>();
		for (final SaudiUnemploymentModel saudiUnemploymentModel : model) {
			final SaudiUnemploymentData rsData = getSaudiUnemploymentConverter().convert(saudiUnemploymentModel);
			saudiUnemploymentListData.add(rsData);
		}
		return saudiUnemploymentListData;
	}

	@Override
	public List<NonSaudiUnemploymentData> getNonSaudiUnemploymentData(int year) {
		final List<NonSaudiUnemploymentModel> model = getSaudiEconomicSectorsService()
				.getNonSaudiUnemploymentModel(year);
		final List<NonSaudiUnemploymentData> nonSaudiUnemploymentListData = new ArrayList<NonSaudiUnemploymentData>();
		for (final NonSaudiUnemploymentModel nonSaudiUnemploymentModel : model) {
			final NonSaudiUnemploymentData rsData = getNonSaudiUnemploymentConverter()
					.convert(nonSaudiUnemploymentModel);
			nonSaudiUnemploymentListData.add(rsData);
		}
		return nonSaudiUnemploymentListData;
	}
	
	@Override
	public List<OverallUnemploymentData> getOverallUnemploymentData(int year) {
		final List<OverallUnemploymentModel> model = getSaudiEconomicSectorsService()
				.getOverallUnemploymentModel(year);
		final List<OverallUnemploymentData> overallUnemploymentListData = new ArrayList<OverallUnemploymentData>();
		for (final OverallUnemploymentModel overallUnemploymentModel : model) {
			final OverallUnemploymentData rsData = getOverallUnemploymentConverter()
					.convert(overallUnemploymentModel);
			overallUnemploymentListData.add(rsData);
		}
		return overallUnemploymentListData;
	}

}
