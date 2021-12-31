package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.LicenseTypeData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * LicenseTypeService
 */
public class LicenseTypeService extends AbstractSagiaService<LicenseTypeData> {
    /**
     * retrieves Collection of LicenseTypeData
     *
     * @return Collection of LicenseTypeData
     * @throws SagiaODataException excetion
     */
    public Collection<LicenseTypeData> getCollection() throws SagiaODataException {
        return super.getCollection(LicenseTypeData.class);
    }
}
