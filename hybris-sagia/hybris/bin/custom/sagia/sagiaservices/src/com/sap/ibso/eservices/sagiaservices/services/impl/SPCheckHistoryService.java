package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCheckHistory;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.*;

/**
 * SPCheckHistoryService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SPCheckHistoryService extends AbstractSagiaService<SpCheckHistory> {
    /**
     * retrieves a Collection of SpCheckHistory
     * @param serviceDiscriminator serviceDiscriminator
     * @return Collection of SpCheckHistory
     */
    public final Collection<SpCheckHistory> getCollection(String serviceDiscriminator){
        return super.getCollection(SpCheckHistory.class,
                PROCESS_TYPE,
                "'ZS13'",
                CAT_CODE1,
                "'ZSPLJAWA'",
                CAT_CODE2,
                "'" + serviceDiscriminator + "'");
    }

    /**
     * retrieves SpCheckHistory by id
     * @param id id
     * @return SpCheckHistory
     */
    public final SpCheckHistory get(Integer id){
        return super.get(SpCheckHistory.class, id);
    }
}
