package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.SubmitData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SubmitServiceQ1
 */
public class SubmitServiceQ1 extends AbstractSagiaService<SubmitData> {
    public SubmitData saveQeemah(SubmitData qeemahSubmitData){
        return super.saveAndParseResponse(qeemahSubmitData, SubmitData.class);
    }
}
