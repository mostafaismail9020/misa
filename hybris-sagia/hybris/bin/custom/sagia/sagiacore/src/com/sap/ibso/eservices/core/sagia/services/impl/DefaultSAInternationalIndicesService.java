package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SaudiArabiaInternationalIndicesModel;
import com.sap.ibso.eservices.core.sagia.dao.SAInternationalIndicesDAO;
import com.sap.ibso.eservices.core.sagia.services.SAInternationalIndicesService;

import java.util.List;

public class DefaultSAInternationalIndicesService implements SAInternationalIndicesService {

	private SAInternationalIndicesDAO saInternationalIndicesDAO;

	@Override
	public List<SaudiArabiaInternationalIndicesModel> getSaudiArabiaInternationalIndicesModel(final String indicator, final Integer startYear,
																							  final Integer endYear) {
		return getSaInternationalIndicesDAO()
				.getSaudiArabiaInternationalIndicesModelBySearch(indicator,startYear,endYear);
	}


	/**
	 * @return the saInternationalIndicesDAO
	 */
	public SAInternationalIndicesDAO getSaInternationalIndicesDAO() {
		return saInternationalIndicesDAO;
	}

	/**
	 * @param saInternationalIndicesDAO the saInternationalIndicesDAO to set
	 */
	public void setSaInternationalIndicesDAO(SAInternationalIndicesDAO saInternationalIndicesDAO) {
		this.saInternationalIndicesDAO = saInternationalIndicesDAO;
	}

}
