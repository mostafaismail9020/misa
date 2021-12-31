package com.sap.ibso.eservices.sagiaservices.services.impl;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SagiaRealEstateService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaRealEstateService extends AbstractSagiaService<RealEstateData> {
    /**
     * Get all Real Estate Objects.
     * @return collection of Real Estate objects.
     */
    public Collection<RealEstateData> getRealEstateHistory() {
        return getCollectionFromBatch(RealEstateData.class, new ArrayList<>(
                Arrays.asList("EntityDetailsSet", "RealEstateSet")));
    }

    /**
     * Gets a Real Estate object based on a given id.
     * @param realEstateId realEstateId
     * @return Real Estate object.
     */
    public RealEstateData getRealEstateById(String realEstateId) {
        return super.get(RealEstateData.class, (Object)realEstateId, REQUEST_PARAMETER_EXPAND, "AttachmentsSet");
    }
    
    /**
     * get the first entry from the list
     * @return RealEstateData
     */
	public RealEstateData getLatestRealEstate() {
		return getRealEstateHistory()
				.stream()
				.findFirst()
				.orElse(null);
	}
}
