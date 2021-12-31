package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.TemporaryBiddingLicenseGovernmentEntity;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TemporaryBiddingLicenseListItemData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class TemporaryBiddingLicenseGovernmentEntityPopulator implements Populator<TemporaryBiddingLicenseListItemData, TemporaryBiddingLicenseGovernmentEntity> {

    @Override
    public void populate(TemporaryBiddingLicenseListItemData temporaryBiddingLicenseListItemData, TemporaryBiddingLicenseGovernmentEntity temporaryBiddingLicenseGovernmentEntity) throws ConversionException {
        temporaryBiddingLicenseGovernmentEntity.setCode(temporaryBiddingLicenseListItemData.getGentity());
        temporaryBiddingLicenseGovernmentEntity.setName(temporaryBiddingLicenseListItemData.getGtext());
    }
}
