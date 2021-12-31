package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeRequirementModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLicenseTypeRequirementDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaLicenseTypeRequirementService;

/**
 * Default implementation of SagiaLicenseTypeRequirementService
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaLicenseTypeRequirementService implements SagiaLicenseTypeRequirementService {

	
	private SagiaLicenseTypeRequirementDAO sagiaLicenseTypeRequirementDAO;

    public SagiaLicenseTypeRequirementDAO getSagiaLicenseTypeRequirementDAO() {
        return sagiaLicenseTypeRequirementDAO;
    }

    public void setSagiaLicenseTypeRequirementDAO(SagiaLicenseTypeRequirementDAO sagiaLicenseTypeRequirementDAO) {
        this.sagiaLicenseTypeRequirementDAO = sagiaLicenseTypeRequirementDAO;
    }
    
    
	@Override
	public SagiaLicenseTypeRequirementModel getLicenseTypeRequirementForID(String splRequirementId) {
		return getSagiaLicenseTypeRequirementDAO().getLicenseTypeRequirementForID(splRequirementId);
	}

	@Override
	public List<SagiaLicenseTypeRequirementModel> getLicenseTypeRequirementForListId(
			List<String> splRequirementListId) {
		
		return getSagiaLicenseTypeRequirementDAO().getLicenseTypeRequirementForListId(splRequirementListId);
	}

}
