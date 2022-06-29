package com.sap.ibso.eservices.facades.sagia.economic.impl;


import com.sap.ibso.eservices.core.model.SaudiArabiaInternationalIndicesModel;
import com.sap.ibso.eservices.core.sagia.services.SAInternationalIndicesService;
import com.sap.ibso.eservices.facades.sagia.economic.SAInternationalIndicesFacade;

import com.sap.ibso.eservices.facades.data.SAInternationalIndicesData;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class DefaultSAInternationalIndicesFacade implements SAInternationalIndicesFacade {

	  private SAInternationalIndicesService saInternationalIndicesService;
	  private Converter<SaudiArabiaInternationalIndicesModel, SAInternationalIndicesData> saInternationalIndicesConverter;

	

	@Override
	public List<SAInternationalIndicesData> getSAInternationalIndicesListData(final String indicator, final Integer startYear, final Integer endYear)
	  {
		  final List<SaudiArabiaInternationalIndicesModel> model = getSaInternationalIndicesService().getSaudiArabiaInternationalIndicesModel(indicator,startYear,endYear);

		  final List<SAInternationalIndicesData> saInternationalIndicesListData = new ArrayList<>();

		  for(final SaudiArabiaInternationalIndicesModel saInternationalIndicesModel :  model)
		  {
			  final SAInternationalIndicesData eoData = getSaInternationalIndicesConverter().convert(saInternationalIndicesModel);
			  saInternationalIndicesListData.add(eoData);
		  }
		  return saInternationalIndicesListData;
	  }


	/**
	 * @return the saInternationalIndicesService
	 */
	public SAInternationalIndicesService getSaInternationalIndicesService() {
		return saInternationalIndicesService;
	}


	/**
	 * @param saInternationalIndicesService the saInternationalIndicesService to set
	 */
	public void setSaInternationalIndicesService(SAInternationalIndicesService saInternationalIndicesService) {
		this.saInternationalIndicesService = saInternationalIndicesService;
	}

	public Converter<SaudiArabiaInternationalIndicesModel, SAInternationalIndicesData> getSaInternationalIndicesConverter() {
		return saInternationalIndicesConverter;
	}

	public void setSaInternationalIndicesConverter(Converter<SaudiArabiaInternationalIndicesModel, SAInternationalIndicesData> saInternationalIndicesConverter) {
		this.saInternationalIndicesConverter = saInternationalIndicesConverter;
	}

}
