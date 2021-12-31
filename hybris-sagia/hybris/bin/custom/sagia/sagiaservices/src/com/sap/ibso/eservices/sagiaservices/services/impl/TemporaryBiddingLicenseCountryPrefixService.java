package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseCountryPrefixData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * TemporaryBiddingLicenseCountryPrefixService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class TemporaryBiddingLicenseCountryPrefixService extends AbstractSagiaService<TemporaryBiddingLicenseCountryPrefixData> {
    /**
     * retrieves CountryPrefix
     * @param countryCode countryCode
     * @return TemporaryBiddingLicenseCountryPrefixData
     */
    public TemporaryBiddingLicenseCountryPrefixData getCountryPrefix(String countryCode) {
        return get(TemporaryBiddingLicenseCountryPrefixData.class, countryCode);
    }
}
