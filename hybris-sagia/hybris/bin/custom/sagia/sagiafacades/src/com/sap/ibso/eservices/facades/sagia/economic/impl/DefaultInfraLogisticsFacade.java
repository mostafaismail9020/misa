package com.sap.ibso.eservices.facades.sagia.economic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.services.InfraLogisticsService;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.sagia.economic.InfraLogisticsFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultInfraLogisticsFacade implements InfraLogisticsFacade {
	private InfraLogisticsService infraLogisticsService;

	private Converter<InfraLogisticsLandingModel, InfraLogisticsLandingData> infraLogisticsLandingConverter;
	private Converter<LengthOfNetworkModel, LengthOfNetworkData> lengthOfNetworkConverter;
	private Converter<PrivateCitiesModel, PrivateCitiesData> privateCitiesConverter;
	private Converter<InfrastructureLogisticsModel, InfrastructureLogisticsData> infrastructureLogisticsConverter;


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

	public Converter<InfrastructureLogisticsModel, InfrastructureLogisticsData> getInfrastructureLogisticsConverter() {
		return infrastructureLogisticsConverter;
	}

	public void setInfrastructureLogisticsConverter(Converter<InfrastructureLogisticsModel, InfrastructureLogisticsData> infrastructureLogisticsConverter) {
		this.infrastructureLogisticsConverter = infrastructureLogisticsConverter;
	}

	@Override
	public InfraLogisticsLandingData getInfraLogisticsLandingData() {

		final InfraLogisticsLandingModel infraLogisticsLandingModel = getInfraLogisticsService()
				.getInfraLogisticsLandingModel();

		return getInfraLogisticsLandingConverter()
				.convert(infraLogisticsLandingModel);
	}

	@Override
	public List<LengthOfNetworkData> getLengthOfNetworkData() {
		final List<LengthOfNetworkModel> lengthOfNetworkListModel = getInfraLogisticsService()
				.getLengthOfNetworkModel();

		final List<LengthOfNetworkData> lengthOfNetworkData = new ArrayList<>();

		for (final LengthOfNetworkModel lengthOfNetworkModel : lengthOfNetworkListModel) {
			final LengthOfNetworkData loData = getLengthOfNetworkConverter().convert(lengthOfNetworkModel);
			lengthOfNetworkData.add(loData);
		}
		return lengthOfNetworkData;

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

	@Override
	public List<InfrastructureLogisticsData> getInfrastructureLogisticsData() {
		final List<InfrastructureLogisticsModel> infrastructureLogisticsModel = getInfraLogisticsService().getInfrastructureLogisticsModel();

		final List<InfrastructureLogisticsData> infrastructureLogisticsData = new ArrayList<>();

		for (final InfrastructureLogisticsModel model : infrastructureLogisticsModel) {
			final InfrastructureLogisticsData eoData = getInfrastructureLogisticsConverter().convert(model);
			infrastructureLogisticsData.add(eoData);
		}
		Collections.sort(infrastructureLogisticsData, Comparator.comparing(InfrastructureLogisticsData::getIndex));
		return infrastructureLogisticsData;
	}

}
