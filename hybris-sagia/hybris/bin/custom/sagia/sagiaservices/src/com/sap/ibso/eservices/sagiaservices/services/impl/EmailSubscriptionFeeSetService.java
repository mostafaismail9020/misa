package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zverifyModuleSrvOData.EmailSubscriptionFeeData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

public class EmailSubscriptionFeeSetService extends AbstractSagiaService<EmailSubscriptionFeeData> {

	/**
     * retrieves EmailSubscriptionFeeData by id
     * @param id id
     * @return EmailSubscriptionFeeData
     */
    public final EmailSubscriptionFeeData get(String id){
        return super.get(EmailSubscriptionFeeData.class, id);
    }
}
