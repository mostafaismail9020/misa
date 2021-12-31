package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCitySetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.Map;

/**
 * NipCityService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipCityService extends AbstractSagiaService<NIPCitySetData> {
    /**
     * retrieves Collection of NIPCitySetData
     * @param language language
     * @return Collection of NIPCitySetData
     */
    public final Collection<NIPCitySetData> getCollection(String language) {
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .skip("0")
                .top("500")
                .inLineCount("allpages")
                .filter("LANGUAGE eq'" + language + "'")
                .build();
        return super.getCollection(
                NIPCitySetData.class,
                queryOptions);
    }

    /**
     * retrieves Collection of NIPCitySetData by regionCode
     * @param regionCode regionCode
     * @param language language
     * @return Collection of NIPCitySetData
     */
    public final Collection<NIPCitySetData> getCollection(String regionCode, String language) {
        Map<String, String> queryOptions = new QueryOptionsBuilder()
                .skip("0")
                .top("500")
                .inLineCount("allpages")
                .filter("LANGUAGE eq'" + language + "' and REGION eq '" + regionCode + "'")
                .build();

        return super.getCollection(
                NIPCitySetData.class,
                queryOptions);
    }

    /**
     * creates NIPCitySetData
     * @param nipCitySetData nipCitySetData
     */
    public final void create(NIPCitySetData nipCitySetData){
        super.save(nipCitySetData);
    }
}
