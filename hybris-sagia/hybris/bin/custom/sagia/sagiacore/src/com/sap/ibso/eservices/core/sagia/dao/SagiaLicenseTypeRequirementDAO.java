package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeRequirementModel;

public interface SagiaLicenseTypeRequirementDAO {
	
	SagiaLicenseTypeRequirementModel getLicenseTypeRequirementForID(String splRequirementId);

	List<SagiaLicenseTypeRequirementModel> getLicenseTypeRequirementForListId(List<String> splRequirementListId);

}
