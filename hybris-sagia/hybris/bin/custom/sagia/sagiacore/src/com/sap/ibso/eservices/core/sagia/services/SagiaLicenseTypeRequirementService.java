package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeRequirementModel;

public interface SagiaLicenseTypeRequirementService {
	
	
	/**
	 * get License Type Requirement for the requirement ID.
	 * @param splRequirementId
	 * @return SagiaLicenseTypeRequirement
	 */
	public SagiaLicenseTypeRequirementModel getLicenseTypeRequirementForID(String splRequirementId);
	
	
	/**
	 * get License Type Requirement for the requirement ID.
	 * @param splRequirementId
	 * @return SagiaLicenseTypeRequirement
	 */
	public List<SagiaLicenseTypeRequirementModel> getLicenseTypeRequirementForListId(List<String> splRequirementListId);
	
	

}
