package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * TemporaryBiddingLicenseService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class TemporaryBiddingLicenseService extends AbstractSagiaService<TemporaryBiddingLicenseData> {
    /**
     * creates a TemporaryBiddingLicenseData
     * @param temporaryBiddingLicenseData temporaryBiddingLicenseData
     * @return TemporaryBiddingLicenseData
     */
    public final TemporaryBiddingLicenseData create(TemporaryBiddingLicenseData temporaryBiddingLicenseData){
    		return saveAndParseResponse(temporaryBiddingLicenseData, TemporaryBiddingLicenseData.class);
    }
}
