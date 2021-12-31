package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.DropdownValue;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoDictionaryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DropdownValuePopulator implements Populator<OrgInfoDictionaryData, DropdownValue> {

    @Override
    public void populate(OrgInfoDictionaryData orgInfoDictionaryData, DropdownValue dropdownValue) throws ConversionException {
        dropdownValue.setLegalStatus(orgInfoDictionaryData.getSource());
        dropdownValue.setLegalStatusText(orgInfoDictionaryData.getTextlong());
        dropdownValue.setRegion(orgInfoDictionaryData.getBland_reg());
        dropdownValue.setRegionText(orgInfoDictionaryData.getBezei_reg());
        dropdownValue.setCity(orgInfoDictionaryData.getCityCode_cty());
        dropdownValue.setCityText(orgInfoDictionaryData.getCityName_cty());
        dropdownValue.setCountry(orgInfoDictionaryData.getLand1());
        dropdownValue.setCountryText(orgInfoDictionaryData.getLandx50());
        dropdownValue.setNationality(orgInfoDictionaryData.getLand1());
        dropdownValue.setNationalityText(orgInfoDictionaryData.getNatio50());
    }
}
