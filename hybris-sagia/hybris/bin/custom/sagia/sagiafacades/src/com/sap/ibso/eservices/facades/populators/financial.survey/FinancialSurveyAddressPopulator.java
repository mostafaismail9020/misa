package com.sap.ibso.eservices.facades.populators.financial.survey;

import com.sap.ibso.eservices.core.model.SagiaSurveyAddressModel;
import com.sap.ibso.eservices.facades.data.license.amendment.Address;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class FinancialSurveyAddressPopulator implements Populator<SagiaSurveyAddressModel, Address> {


    @Override
    public void populate(SagiaSurveyAddressModel sagiaSurveyAddressModel, Address address) throws ConversionException {
        address.setCity(sagiaSurveyAddressModel.getCity());
        address.setCityDescription(sagiaSurveyAddressModel.getCityDescription());
        address.setCountry(sagiaSurveyAddressModel.getCountry());
        address.setCountryDescription(sagiaSurveyAddressModel.getCountryDescription());
        address.setRegion(sagiaSurveyAddressModel.getRegion());
        address.setRegionDescription(sagiaSurveyAddressModel.getRegionDescription());
        address.setStreet(sagiaSurveyAddressModel.getStreet());
        address.setWebsite(sagiaSurveyAddressModel.getWebsite());
        address.setNumber(sagiaSurveyAddressModel.getNumber());
        address.setZipCode(sagiaSurveyAddressModel.getZipCode());
        address.setTelephone(sagiaSurveyAddressModel.getTelephone());
        address.setEmail(sagiaSurveyAddressModel.getEmail());
    }
}
