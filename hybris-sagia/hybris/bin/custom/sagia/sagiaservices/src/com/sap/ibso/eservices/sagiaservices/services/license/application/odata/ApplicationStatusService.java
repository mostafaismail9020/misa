package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * Implements access to CRM_ID_STATUS_ENT collection of the ZQEEMAH service.
 */
public class ApplicationStatusService extends AbstractSagiaService<ApplicationStatusData> {
    /**
     * retrieves ApplicationStatus
     * @param investorId investorId
     * @return Collection of ApplicationStatusData
     */
	@Cacheable(value = "odataCache", key = "#investorId")
    public Collection<ApplicationStatusData> getApplicationStatus(String investorId) {
		System.out.println("in side service:"+investorId);
        return super.getCollection(ApplicationStatusData.class, "InvId", "'" + investorId + "'");
    }

	@CacheEvict(value = "odataCache", key = "#investorId")
	public void evictApplicationStatus(String investorId) {
		
	}
	
    /**
     * retrieves LatestApplicationStatus
     * @param investorId investorId
     * @return ApplicationStatusData
     */
    public Optional<ApplicationStatusData> getLatestApplicationStatus(String investorId) {
        return getApplicationStatus(investorId).stream().findFirst();
    }
}
