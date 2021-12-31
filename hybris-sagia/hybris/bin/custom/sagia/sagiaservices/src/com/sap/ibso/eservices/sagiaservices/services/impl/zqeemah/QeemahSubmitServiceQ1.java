package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.QeemahSubmitData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * QeemahSubmitServiceQ1
 */
public class QeemahSubmitServiceQ1 extends AbstractSagiaService<QeemahSubmitData> {
    public QeemahSubmitData saveQeemah(QeemahSubmitData qeemahSubmitData){
        return super.saveAndParseResponse(qeemahSubmitData, QeemahSubmitData.class);
    }
}
