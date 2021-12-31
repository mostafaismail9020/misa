package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AmendHeaderData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;

import java.util.Collection;

/**
 * AmendHeadersService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class AmendHeadersService extends AbstractSagiaService<AmendHeaderData> {
    /**
     * retrieves Collection of AmendHeaderData
     * @return Collection of AmendHeaderData
     * @throws SagiaODataException exception
     */
    public Collection<AmendHeaderData> getCollection() throws SagiaODataException {
        return super.getCollection(AmendHeaderData.class);
    }
}
