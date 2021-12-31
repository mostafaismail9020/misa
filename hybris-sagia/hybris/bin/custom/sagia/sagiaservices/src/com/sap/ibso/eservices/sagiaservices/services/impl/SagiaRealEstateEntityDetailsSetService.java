package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSetData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * SagiaRealEstateEntityDetailsSetService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaRealEstateEntityDetailsSetService extends AbstractSagiaService<RealEstateEntityDetailsSetData> {
    /**
     * Get entity details set.
     *
     * @return Collection of RealEstateEntityDetailsSetData object.
     * @throws SagiaODataException exception
     */
    public Collection<RealEstateEntityDetailsSetData> getRealEstateEntityDetailsSet() throws SagiaODataException {
        return super.getCollection(RealEstateEntityDetailsSetData.class);
    }

}
