package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.AddressHDR;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AddressHDRData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServicesRequestAddressPopulator implements Populator<AddressHDRData, AddressHDR> {
    @Override
    public void populate(AddressHDRData addressHDRData, AddressHDR addressHDR) throws ConversionException {
        addressHDR.setSrID(addressHDRData.getSrId());
		addressHDR.setSrGuid(addressHDRData.getSrGuid());
		addressHDR.setBpID(addressHDRData.getBpID());
		addressHDR.setBpGuid(addressHDRData.getBpGuid());
		addressHDR.setStreet(addressHDRData.getStreet());
		addressHDR.setBuilding(addressHDRData.getBuilding());
		addressHDR.setHouseNo(addressHDRData.getHouseNo());
		addressHDR.setCountryDesc(addressHDRData.getCountryDesc());
		addressHDR.setCountry(addressHDRData.getCountry());
		addressHDR.setZipCode(addressHDRData.getZipCode());
		addressHDR.setAdditionalNotes(addressHDRData.getAdditNo());
		addressHDR.setCity(addressHDRData.getCity());
		addressHDR.setPeriod(addressHDRData.getPeriod());
    }
}
