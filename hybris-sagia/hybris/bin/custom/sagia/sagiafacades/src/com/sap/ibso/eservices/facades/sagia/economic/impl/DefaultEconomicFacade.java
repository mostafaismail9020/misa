package com.sap.ibso.eservices.facades.sagia.economic.impl;

import com.sap.ibso.eservices.core.model.*;


import com.sap.ibso.eservices.core.sagia.services.EconomicService;
import com.sap.ibso.eservices.facades.data.*;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import com.sap.ibso.eservices.facades.sagia.economic.EconomicFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultEconomicFacade implements EconomicFacade{
	
	private EconomicService economicService;

	private Converter<CreditRatingModel, CreditRatingData> creditRatingConverter;

	private Converter<DashboardModel, DashboardData> dashboardConverter;



	@Override
	public List<DashboardData> getAllDashboardData() {

		final List<DashboardModel> listDashboardModels = getEconomicService().getAllDashboardModels();
		List<DashboardData> listDashboardCards = new ArrayList<>();
		listDashboardModels.forEach(card -> {
			DashboardData data = getDashboardConverter().convert(card);
			listDashboardCards.add(data);
		});
		Collections.sort(listDashboardCards, Comparator.comparing(DashboardData::getIndex));
		return listDashboardCards;
	}
	
	@Override
	public CreditRatingData getCreditRatingData()
	{
		final CreditRatingModel creditRatingModel = getEconomicService().getCreditRatingModel();

		return getCreditRatingConverter().convert(creditRatingModel);
	}

	/**
	 * @return the creditRatingConverter
	 */
	public Converter<CreditRatingModel, CreditRatingData> getCreditRatingConverter() {
		return creditRatingConverter;
	}


	/**
	 * @param creditRatingConverter the creditRatingConverter to set
	 */
	public void setCreditRatingConverter(Converter<CreditRatingModel, CreditRatingData> creditRatingConverter) {
		this.creditRatingConverter = creditRatingConverter;
	}

	public Converter<DashboardModel, DashboardData> getDashboardConverter() {
		return dashboardConverter;
	}

	public void setDashboardConverter(Converter<DashboardModel, DashboardData> dashboardConverter) {
		this.dashboardConverter = dashboardConverter;
	}

	public EconomicService getEconomicService() {
		return economicService;
	}

	public void setEconomicService(EconomicService economicService) {
		this.economicService = economicService;
	}
}
 