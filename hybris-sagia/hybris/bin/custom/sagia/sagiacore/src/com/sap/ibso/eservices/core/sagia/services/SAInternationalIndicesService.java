package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.SaudiArabiaInternationalIndicesModel;

import java.util.List;

public interface SAInternationalIndicesService {

	  List<SaudiArabiaInternationalIndicesModel> getSaudiArabiaInternationalIndicesModel(final String indicator, final Integer startYear, final Integer endYear);

}
