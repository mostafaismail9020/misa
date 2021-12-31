package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPHeaderSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * NipService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipService extends AbstractSagiaService<NIPHeaderSetData> {
    /**
     * retrieves the Collection of NIPHeaderSetData
     * @return Collection of NIPHeaderSetData
     */
    public final Collection<NIPHeaderSetData> getCollection() {
        return super.getCollection(NIPHeaderSetData.class);
    }

    /**
     * retrieves the Collection of NIPHeaderSetData by crNumber
     * @param crNumber crNumber
     * @return Collection of NIPHeaderSetData
     */
    public final Collection<NIPHeaderSetData> get(String crNumber){
        StringBuilder filter = new StringBuilder();
        filter.append("CR_NUMBER eq '");
        filter.append(crNumber);
        filter.append("'");
        return super.getCollection(NIPHeaderSetData.class, REQUEST_PARAMETER_FILTER, filter.toString());
    }

    /**
     * creates NIPHeaderSetData
     * @param nipHeaderSetData nipHeaderSetData
     */
    public final void create(NIPHeaderSetData nipHeaderSetData){
        super.save(nipHeaderSetData);
    }
}
