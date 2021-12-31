package com.sap.ibso.eservices.facades.sagia.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeRequirementModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaLicenseTypeRequirementService;
import com.sap.ibso.eservices.facades.data.SagiaLicenseTypeRequirementData;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseTypeRequirementFacade;

import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultSagiaLicenseTypeRequirementFacade implements SagiaLicenseTypeRequirementFacade {

	private SagiaLicenseTypeRequirementService sagiaLicenseTypeRequirementService;
	private Converter<SagiaLicenseTypeRequirementModel, SagiaLicenseTypeRequirementData> sagiaLicenseTypeRequirementConverter;
	
	
	@Override
	public SagiaLicenseTypeRequirementData getSagiaLicenseTypeRequirementForID(String splRequirementId) {
		SagiaLicenseTypeRequirementModel sagiaLicenseTypeRequirementModel = sagiaLicenseTypeRequirementService.getLicenseTypeRequirementForID(splRequirementId);
		if(sagiaLicenseTypeRequirementModel!= null) 
		 return sagiaLicenseTypeRequirementConverter.convert(sagiaLicenseTypeRequirementModel);
		return null;
	}


	public void setSagiaLicenseTypeRequirementService(
			SagiaLicenseTypeRequirementService sagiaLicenseTypeRequirementService) {
		this.sagiaLicenseTypeRequirementService = sagiaLicenseTypeRequirementService;
	}


	public void setSagiaLicenseTypeRequirementConverter(
			Converter<SagiaLicenseTypeRequirementModel, SagiaLicenseTypeRequirementData> sagiaLicenseTypeRequirementConverter) {
		this.sagiaLicenseTypeRequirementConverter = sagiaLicenseTypeRequirementConverter;
	}


	@Override
	public List<SagiaLicenseTypeRequirementData> getSagiaLicenseTypeRequirementForListId(List<String> splRequirementId) {

		List<SagiaLicenseTypeRequirementModel> sagiaLicenseTypeRequirementListModel = sagiaLicenseTypeRequirementService.getLicenseTypeRequirementForListId(splRequirementId);
		if(sagiaLicenseTypeRequirementListModel!= null)  {
			return sagiaLicenseTypeRequirementConverter.convertAll(sagiaLicenseTypeRequirementListModel);
		}
		return null;
	}
	
	
	
	

}
