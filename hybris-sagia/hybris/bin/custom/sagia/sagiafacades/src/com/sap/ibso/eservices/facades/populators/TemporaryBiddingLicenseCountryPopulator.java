package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseCountry;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseListItemData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class TemporaryBiddingLicenseCountryPopulator implements Populator<TemporaryBiddingLicenseListItemData, TemporaryBiddingLicenseCountry> {

    @Override
    public void populate(TemporaryBiddingLicenseListItemData temporaryBiddingLicenseListItemData, TemporaryBiddingLicenseCountry temporaryBiddingLicenseCountry) throws ConversionException {
        temporaryBiddingLicenseCountry.setCode(temporaryBiddingLicenseListItemData.getCountry());
        temporaryBiddingLicenseCountry.setName(temporaryBiddingLicenseListItemData.getCtext());
    }

}
