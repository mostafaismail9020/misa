package com.sap.ibso.eservices.facades.sagia;

import java.util.List;

import com.sap.ibso.eservices.facades.data.SagiaLicenseTypeRequirementData;

/**
 * 
 * SagiaLicenseTypeRequirementFacade
 *
 */
public interface SagiaLicenseTypeRequirementFacade {
	
	/**
	 * Get SagiaLicenseTypeRequirement for splRequirementId. 
	 * @param splRequirementId
	 * @return
	 */
	public SagiaLicenseTypeRequirementData getSagiaLicenseTypeRequirementForID(String splRequirementId);
	
	/**
	 * Get SagiaLicenseTypeRequirement for a list of splRequirementId. 
	 * @param splRequirementId
	 * @return
	 */
	public List<SagiaLicenseTypeRequirementData> getSagiaLicenseTypeRequirementForListId(List<String> splRequirementId);

}
