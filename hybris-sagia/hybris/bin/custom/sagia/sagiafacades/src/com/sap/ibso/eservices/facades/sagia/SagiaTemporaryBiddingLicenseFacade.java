package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicense;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseCountry;
import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseGovernmentEntity;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseData;

import java.util.List;

/**
 * Created by i335541 on 2/12/18.
 */
public interface SagiaTemporaryBiddingLicenseFacade {
    /**
     * Creates TemporaryBidding License
     * @param temporaryBiddingLicense temporaryBiddingLicense
     * @return TemporaryBiddingLicenseData
     */
    TemporaryBiddingLicenseData createTemporaryBiddingLicense(TemporaryBiddingLicense temporaryBiddingLicense);

    /**
     * Retrieves List of Countries
     * @param language language
     * @return List of TemporaryBiddingLicenseCountry
     */
    List<TemporaryBiddingLicenseCountry> getCountries(String language);

    /**
     * Retrieves GovernmentEntities
     * @param language language
     * @return List of TemporaryBiddingLicenseGovernmentEntity
     */
    List<TemporaryBiddingLicenseGovernmentEntity> getGovernmentEntities(String language);

    /**
     * Retrieves CountryPrefix
     * @param countryCode countryCode
     * @return String prefix
     */
    String getCountryPrefix(String countryCode);
}
