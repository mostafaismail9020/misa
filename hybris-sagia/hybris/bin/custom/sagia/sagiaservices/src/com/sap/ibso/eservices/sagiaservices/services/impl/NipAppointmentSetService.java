package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * NipAppointmentSetService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class NipAppointmentSetService extends AbstractSagiaService<NationalInvestorAppointmentData> {

    /**
     * Calls the save method from the AbstractSagiaService
     * @param nationalInvestorAppointmentData nationalInvestorAppointmentData
     * @return NationalInvestorAppointmentData
     */
    public final NationalInvestorAppointmentData create(NationalInvestorAppointmentData nationalInvestorAppointmentData){
         return super.saveAndParseResponse(nationalInvestorAppointmentData, NationalInvestorAppointmentData.class);
    }
}
