package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.SaudiArabiaInternationalIndicesModel;

import java.util.List;

public interface SAInternationalIndicesDAO {

	List<SaudiArabiaInternationalIndicesModel> getSaudiArabiaInternationalIndicesModelBySearch(final String indicator, final Integer startYear, final Integer endYear);

}
