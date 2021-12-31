package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.DropdownValue;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DropdownValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DropdownValuePopulator implements Populator<DropdownValueData, DropdownValue> {
    @Override
    public void populate(DropdownValueData dropdownValueData, DropdownValue dropdownValue) throws ConversionException {
        dropdownValue.setRegionText(dropdownValueData.getQ2regiontext());
		dropdownValue.setLanguage(dropdownValueData.getQ2langu());
		dropdownValue.setLegalStatus(dropdownValueData.getQ2legalstatus());
		dropdownValue.setUom(dropdownValueData.getQ2uom());
		dropdownValue.setCity(dropdownValueData.getQ2city());
		dropdownValue.setLegalStatusText(dropdownValueData.getQ2legalstatustext());
		dropdownValue.setUomtext(dropdownValueData.getQ2uomtext());
		dropdownValue.setCityText(dropdownValueData.getQ2citytext());
		dropdownValue.setProduct(dropdownValueData.getQ2product());
		dropdownValue.setNationality(dropdownValueData.getQ2natio());
		dropdownValue.setProductText(dropdownValueData.getQ2producttext());
		dropdownValue.setNationalityText(dropdownValueData.getQ2natiotext());
		dropdownValue.setCountry(dropdownValueData.getQ2country());
		dropdownValue.setCountryText(dropdownValueData.getQ2countrytext());
		dropdownValue.setRegion(dropdownValueData.getQ2region());
		dropdownValue.setFlag(dropdownValueData.getQ2flag());
    }
}
