package com.sap.ibso.eservices.facades.sagia.economic.impl;

import java.util.ArrayList;
import java.util.List;

import com.sap.ibso.eservices.core.model.EmploymentModel;
import com.sap.ibso.eservices.core.model.HousingFacilitiesModel;
import com.sap.ibso.eservices.core.model.IndustrialCitiesModel;
import com.sap.ibso.eservices.core.model.InfraLogisticsLandingModel;
import com.sap.ibso.eservices.core.model.InfrastructureModel;
import com.sap.ibso.eservices.core.model.LengthOfNetworkModel;
import com.sap.ibso.eservices.core.model.PrivateCitiesModel;
import com.sap.ibso.eservices.core.model.TotalAreaModel;
import com.sap.ibso.eservices.core.sagia.services.InfraLogisticsService;
import com.sap.ibso.eservices.facades.data.EmploymentData;
import com.sap.ibso.eservices.facades.data.HousingFacilitiesData;
import com.sap.ibso.eservices.facades.data.IndustrialCitiesData;
import com.sap.ibso.eservices.facades.data.InfraLogisticsLandingData;
import com.sap.ibso.eservices.facades.data.InfrastructureData;
import com.sap.ibso.eservices.facades.data.LengthOfNetworkData;
import com.sap.ibso.eservices.facades.data.PrivateCitiesData;
import com.sap.ibso.eservices.facades.data.TotalAreaData;
import com.sap.ibso.eservices.facades.sagia.economic.InfraLogisticsFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultInfraLogisticsFacade implements InfraLogisticsFacade {
	private InfraLogisticsService infraLogisticsService;

	private Converter<InfraLogisticsLandingModel, InfraLogisticsLandingData> infraLogisticsLandingConverter;
	private Converter<InfrastructureModel, InfrastructureData> infrastructureConverter;
	private Converter<LengthOfNetworkModel, LengthOfNetworkData> lengthOfNetworkConverter;
	private Converter<EmploymentModel, EmploymentData> employmentConverter;
	private Converter<TotalAreaModel, TotalAreaData> totalAreaConverter;
	private Converter<HousingFacilitiesModel, HousingFacilitiesData> housingFacilitiesConverter;
	private Converter<IndustrialCitiesModel, IndustrialCitiesData> industrialCitiesConverter;
	private Converter<PrivateCitiesModel, PrivateCitiesData> privateCitiesConverter;

	/**
	 * @return the infraLogisticsService
	 */
	public InfraLogisticsService getInfraLogisticsService() {
		return infraLogisticsService;
	}

	/**
	 * @param infraLogisticsService the infraLogisticsService to set
	 */
	public void setInfraLogisticsService(InfraLogisticsService infraLogisticsService) {
		this.infraLogisticsService = infraLogisticsService;
	}

	/**
	 * @return the infrastructureConverter
	 */
	public Converter<InfrastructureModel, InfrastructureData> getInfrastructureConverter() {
		return infrastructureConverter;
	}

	/**
	 * @param infrastructureConverter the infrastructureConverter to set
	 */
	public void setInfrastructureConverter(Converter<InfrastructureModel, InfrastructureData> infrastructureConverter) {
		this.infrastructureConverter = infrastructureConverter;
	}

	/**
	 * @return the infraLogisticsLandingConverter
	 */
	public Converter<InfraLogisticsLandingModel, InfraLogisticsLandingData> getInfraLogisticsLandingConverter() {
		return infraLogisticsLandingConverter;
	}

	/**
	 * @param infraLogisticsLandingConverter the infraLogisticsLandingConverter to
	 *                                       set
	 */
	public void setInfraLogisticsLandingConverter(
			Converter<InfraLogisticsLandingModel, InfraLogisticsLandingData> infraLogisticsLandingConverter) {
		this.infraLogisticsLandingConverter = infraLogisticsLandingConverter;
	}

	/**
	 * @return the lengthOfNetworkConverter
	 */
	public Converter<LengthOfNetworkModel, LengthOfNetworkData> getLengthOfNetworkConverter() {
		return lengthOfNetworkConverter;
	}

	/**
	 * @param lengthOfNetworkConverter the lengthOfNetworkConverter to set
	 */
	public void setLengthOfNetworkConverter(
			Converter<LengthOfNetworkModel, LengthOfNetworkData> lengthOfNetworkConverter) {
		this.lengthOfNetworkConverter = lengthOfNetworkConverter;
	}

	/**
	 * @return the employmentConverter
	 */
	public Converter<EmploymentModel, EmploymentData> getEmploymentConverter() {
		return employmentConverter;
	}

	/**
	 * @param employmentConverter the employmentConverter to set
	 */
	public void setEmploymentConverter(Converter<EmploymentModel, EmploymentData> employmentConverter) {
		this.employmentConverter = employmentConverter;
	}

	/**
	 * @return the totalAreaConverter
	 */
	public Converter<TotalAreaModel, TotalAreaData> getTotalAreaConverter() {
		return totalAreaConverter;
	}

	/**
	 * @param totalAreaConverter the totalAreaConverter to set
	 */
	public void setTotalAreaConverter(Converter<TotalAreaModel, TotalAreaData> totalAreaConverter) {
		this.totalAreaConverter = totalAreaConverter;
	}

	/**
	 * @return the housingFacilitiesConverter
	 */
	public Converter<HousingFacilitiesModel, HousingFacilitiesData> getHousingFacilitiesConverter() {
		return housingFacilitiesConverter;
	}

	/**
	 * @param housingFacilitiesConverter the housingFacilitiesConverter to set
	 */
	public void setHousingFacilitiesConverter(
			Converter<HousingFacilitiesModel, HousingFacilitiesData> housingFacilitiesConverter) {
		this.housingFacilitiesConverter = housingFacilitiesConverter;
	}

	/**
	 * @return the industrialCitiesConverter
	 */
	public Converter<IndustrialCitiesModel, IndustrialCitiesData> getIndustrialCitiesConverter() {
		return industrialCitiesConverter;
	}

	/**
	 * @param industrialCitiesConverter the industrialCitiesConverter to set
	 */
	public void setIndustrialCitiesConverter(
			Converter<IndustrialCitiesModel, IndustrialCitiesData> industrialCitiesConverter) {
		this.industrialCitiesConverter = industrialCitiesConverter;
	}

	/**
	 * @return the privateCitiesConverter
	 */
	public Converter<PrivateCitiesModel, PrivateCitiesData> getPrivateCitiesConverter() {
		return privateCitiesConverter;
	}

	/**
	 * @param privateCitiesConverter the privateCitiesConverter to set
	 */
	public void setPrivateCitiesConverter(Converter<PrivateCitiesModel, PrivateCitiesData> privateCitiesConverter) {
		this.privateCitiesConverter = privateCitiesConverter;
	}

	@Override
	public InfraLogisticsLandingData getInfraLogisticsLandingData() {

		final InfraLogisticsLandingModel infraLogisticsLandingModel = getInfraLogisticsService()
				.getInfraLogisticsLandingModel();

		final InfraLogisticsLandingData infraLogisticsLandingData = getInfraLogisticsLandingConverter()
				.convert(infraLogisticsLandingModel);

		return infraLogisticsLandingData;
	}

	@Override
	public InfrastructureData getInfrastructureData() {

		final InfrastructureModel infrastructureModel = getInfraLogisticsService().getInfrastructureModel();

		final InfrastructureData infrastructureData = getInfrastructureConverter().convert(infrastructureModel);

		return infrastructureData;
	}

	@Override
	public List<LengthOfNetworkData> getLengthOfNetworkData() {
		final List<LengthOfNetworkModel> lengthOfNetworkListModel = getInfraLogisticsService()
				.getLengthOfNetworkModel();

		final List<LengthOfNetworkData> lengthOfNetworkData = new ArrayList<LengthOfNetworkData>();

		for (final LengthOfNetworkModel lengthOfNetworkModel : lengthOfNetworkListModel) {
			final LengthOfNetworkData loData = getLengthOfNetworkConverter().convert(lengthOfNetworkModel);
			lengthOfNetworkData.add(loData);
		}
		return lengthOfNetworkData;

	}

	@Override
	public EmploymentData getEmploymentData() {

		final EmploymentModel employmentModel = getInfraLogisticsService().getEmploymentModel();

		final EmploymentData employmentData = getEmploymentConverter().convert(employmentModel);

		return employmentData;
	}

	@Override
	public TotalAreaData getTotalAreaData() {

		final TotalAreaModel totalAreaModel = getInfraLogisticsService().getTotalAreaModel();

		final TotalAreaData totalAreaData = getTotalAreaConverter().convert(totalAreaModel);

		return totalAreaData;
	}

	@Override
	public HousingFacilitiesData getHousingFacilitiesData() {

		final HousingFacilitiesModel housingFacilitiesModel = getInfraLogisticsService().getHousingFacilitiesModel();

		final HousingFacilitiesData housingFacilitiesData = getHousingFacilitiesConverter()
				.convert(housingFacilitiesModel);

		return housingFacilitiesData;
	}

	@Override
	public IndustrialCitiesData getIndustrialCitiesData() {

		final IndustrialCitiesModel industrialCitiesModel = getInfraLogisticsService().getIndustrialCitiesModel();

		final IndustrialCitiesData industrialCitiesData = getIndustrialCitiesConverter().convert(industrialCitiesModel);

		return industrialCitiesData;
	}

	@Override
	public List<PrivateCitiesData> getPrivateCitiesListData() {

		final List<PrivateCitiesModel> privateCitieListsModel = getInfraLogisticsService().getPrivateCitiesModel();

		final List<PrivateCitiesData> privateCitiesData = new ArrayList<PrivateCitiesData>();

		for (final PrivateCitiesModel privateCitiesModel : privateCitieListsModel) {
			final PrivateCitiesData eoData = getPrivateCitiesConverter().convert(privateCitiesModel);
			privateCitiesData.add(eoData);
		}
		return privateCitiesData;

	}

}
