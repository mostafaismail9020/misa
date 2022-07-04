package com.sap.ibso.eservices.facades.sagia.economic.impl;

import java.util.*;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.services.SaudiEconomicSectorsService;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.sagia.economic.SaudiEconomicSectorsFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;

public class DefaultSaudiEconomicSectorsFacade implements SaudiEconomicSectorsFacade {

	private SaudiEconomicSectorsService saudiEconomicSectorsService;
	private Converter<EconomicsSectorModel, EconomicSectorData> economicSectorConverter;
	private Converter<PopulationDistributionModel, PopulationDistributionData> populationDistributionConverter;
	private Converter<GenderDistributionModel, GenderDistributionData> genderDistributionConverter;
	private Converter<RegionDistributionModel, RegionDistributionData> regionDistributionConverter;
	private Converter<GraduatesByDegreeModel, GraduatesByDegreeData> graduatesByDegreeConverter;

	public Converter<PopulationDistributionModel, PopulationDistributionData> getPopulationDistributionConverter() {
		return populationDistributionConverter;
	}

	public void setPopulationDistributionConverter(Converter<PopulationDistributionModel, PopulationDistributionData> populationDistributionConverter) {
		this.populationDistributionConverter = populationDistributionConverter;
	}

	public Converter<GenderDistributionModel, GenderDistributionData> getGenderDistributionConverter() {
		return genderDistributionConverter;
	}

	public void setGenderDistributionConverter(Converter<GenderDistributionModel, GenderDistributionData> genderDistributionConverter) {
		this.genderDistributionConverter = genderDistributionConverter;
	}

	public Converter<EconomicsSectorModel, EconomicSectorData> getEconomicSectorConverter() {
		return economicSectorConverter;
	}

	public void setEconomicSectorConverter(Converter<EconomicsSectorModel, EconomicSectorData> economicSectorConverter) {
		this.economicSectorConverter = economicSectorConverter;
	}

	public Converter<RegionDistributionModel, RegionDistributionData> getRegionDistributionConverter() {
		return regionDistributionConverter;
	}

	public void setRegionDistributionConverter(Converter<RegionDistributionModel, RegionDistributionData> regionDistributionConverter) {
		this.regionDistributionConverter = regionDistributionConverter;
	}

	public SaudiEconomicSectorsService getSaudiEconomicSectorsService() {
		return saudiEconomicSectorsService;
	}

	public void setSaudiEconomicSectorsService(SaudiEconomicSectorsService saudiEconomicSectorsService) {
		this.saudiEconomicSectorsService = saudiEconomicSectorsService;
	}

	public Converter<GraduatesByDegreeModel, GraduatesByDegreeData> getGraduatesByDegreeConverter() {
		return graduatesByDegreeConverter;
	}

	public void setGraduatesByDegreeConverter(
			Converter<GraduatesByDegreeModel, GraduatesByDegreeData> graduatesByDegreeConverter) {
		this.graduatesByDegreeConverter = graduatesByDegreeConverter;
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
	public Map<String, List<EconomicSectorData>> getEconomicSectorData() {
		final List<EconomicsSectorModel> model = getSaudiEconomicSectorsService().getEconomicSectorModels();
		final Map<String, List<EconomicSectorData>> map = new HashMap<>();
		for (final EconomicsSectorModel economicSectorModel : model) {
			final EconomicSectorData esData = getEconomicSectorConverter().convert(economicSectorModel);
			if(CollectionUtils.isNotEmpty(map.get(esData.getUid()))){
				map.get(esData.getUid()).add(esData);
			}else{
				List<EconomicSectorData> economicSectorList = new ArrayList<>();
				economicSectorList.add(esData);
				Collections.sort(economicSectorList, Comparator.comparing(EconomicSectorData::getIndex));
				map.put(esData.getUid(),economicSectorList);
			}
		}
		return map;
	}

	@Override
	public List<PopulationDistributionData> getPopulationDistributionData(String popDistribution, int year) {
		final List<PopulationDistributionModel> model = getSaudiEconomicSectorsService().getPopulationDistributionModels(popDistribution,year );
		final List<PopulationDistributionData> populationDistributionData = new ArrayList<>();
		for (final PopulationDistributionModel populationDistributionModel : model) {
			final PopulationDistributionData rsData = getPopulationDistributionConverter().convert(populationDistributionModel);
			populationDistributionData.add(rsData);
		}
		Collections.sort(populationDistributionData, Comparator.comparing(PopulationDistributionData::getYear));
		return populationDistributionData;
	}

	@Override
	public List<GenderDistributionData> getGenderDistributionData(String distribution, int year) {
		final List<GenderDistributionModel> model = getSaudiEconomicSectorsService().getGenderDistributionModels(distribution,year);
		final List<GenderDistributionData> genderDistributionDataList = new ArrayList<>();
		for (final GenderDistributionModel genderDistributionModel : model) {
			final GenderDistributionData data = getGenderDistributionConverter().convert(genderDistributionModel);
			genderDistributionDataList.add(data);
		}
		Collections.sort(genderDistributionDataList, Comparator.comparing(GenderDistributionData::getYear));
		return genderDistributionDataList;
	}

	@Override
	public List<RegionDistributionData> getRegionDistributionData(String distribution, int year) {
		final List<RegionDistributionModel> model = getSaudiEconomicSectorsService().getRegionDistributionModel(distribution, year);
		final List<RegionDistributionData> regionDistributionData = new ArrayList<>();
		for (final RegionDistributionModel regionDistributionModel : model) {
			final RegionDistributionData rdData = getRegionDistributionConverter().convert(regionDistributionModel);
			regionDistributionData.add(rdData);
		}
		Collections.sort(regionDistributionData, Comparator.comparing(RegionDistributionData::getYear));
		return regionDistributionData;
	}

}
