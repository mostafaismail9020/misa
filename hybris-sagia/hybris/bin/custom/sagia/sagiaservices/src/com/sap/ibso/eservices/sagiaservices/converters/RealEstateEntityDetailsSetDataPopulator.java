package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateEntityDetailsSetData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class RealEstateEntityDetailsSetDataPopulator extends ODataPopulator<RealEstateEntityDetailsSetData> {
    public static final String USERNAME = "Username";
    public static final String SELL = "Sell";
    public static final String BUY = "Buy";

    @Override
    public void populate(ODataModel model, RealEstateEntityDetailsSetData realEstateEntityDetailsSetData) throws ConversionException {
        if (model.get(USERNAME) != null) {
            realEstateEntityDetailsSetData.setUsername(model.get(USERNAME).toString());
        }
        if (model.get(SELL) != null) {
            realEstateEntityDetailsSetData.setSell(model.get(SELL).toString());
        }

        if (model.get(BUY) != null) {
            realEstateEntityDetailsSetData.setBuy(model.get(BUY).toString());
        }

    }
}
