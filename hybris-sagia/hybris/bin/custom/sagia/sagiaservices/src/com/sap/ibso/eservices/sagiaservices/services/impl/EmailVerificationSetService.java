package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import com.sap.ibso.eservices.sagiaservices.data.zverifyModuleSrvOData.EmailVerificationData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Arrays;
import java.util.Collection;

public class EmailVerificationSetService extends AbstractSagiaService<EmailVerificationData> {

	/**
     * retrieves EmailVerificationData by id
     * @param id id
     * @return EmailVerificationData
     */
    public final EmailVerificationData get(String id){
        return super.get(EmailVerificationData.class, id);
    }
    
    /**
     * creates EmailVerificationData
     * @param EmailVerificationData EmailVerificationData
     */
    public final void create(EmailVerificationData EmailVerificationData){
        super.save(EmailVerificationData);
    }

}
