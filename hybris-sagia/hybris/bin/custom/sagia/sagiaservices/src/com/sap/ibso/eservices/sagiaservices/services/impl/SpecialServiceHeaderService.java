package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpecialServiceHeaderData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * SpecialServiceHeaderService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SpecialServiceHeaderService extends AbstractSagiaService<SpecialServiceHeaderData> {
    /**
     * retrieves SpecialServiceHeaderData by id
     * @param id id
     * @return SpecialServiceHeaderData
     */
    public final SpecialServiceHeaderData get(Integer id){
        return super.get(SpecialServiceHeaderData.class, (Object)id.toString(), REQUEST_PARAMETER_EXPAND, "TOATTACHMENTS,TOAPPLICANTS");
    }

    /**
     * saves SpecialService
     * @param specialServiceHeaderData specialServiceHeaderData
     * @return boolean
     */
    public boolean saveSpecialService(SpecialServiceHeaderData specialServiceHeaderData) {
        return super.createObjectWithBatchPost(specialServiceHeaderData, "SERVICEHEADERSET");
    }
}
