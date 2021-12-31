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
public class ServicesRequestAddressReversePopulator implements Populator<AddressHDR, AddressHDRData> {
    @Override
    public void populate(AddressHDR addressHDR, AddressHDRData addressHDRData) throws ConversionException {
		addressHDRData.setSrId(addressHDR.getSrID());
		addressHDRData.setSrGuid(addressHDR.getSrGuid());
		addressHDRData.setBpID(addressHDR.getBpID());
		addressHDRData.setBpGuid(addressHDR.getBpGuid());
		addressHDRData.setStreet(addressHDR.getStreet());
		addressHDRData.setBuilding(addressHDR.getBuilding());
		addressHDRData.setHouseNo(addressHDR.getHouseNo());
		addressHDRData.setCountryDesc(addressHDR.getCountryDesc());
		addressHDRData.setCountry(addressHDR.getCountry());
		addressHDRData.setZipCode(addressHDR.getZipCode());
		addressHDRData.setAdditNo(addressHDR.getAdditionalNotes());
		addressHDRData.setCity(addressHDR.getCity());
		addressHDRData.setPeriod(addressHDR.getPeriod());
    }
}
