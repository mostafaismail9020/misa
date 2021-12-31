package com.sap.ibso.eservices.sagiaservices.converters.isic;

import java.util.Locale;

import com.sap.ibso.eservices.core.model.IsicTextsModel;
import com.sap.ibso.eservices.sagiaservices.data.IsicTextsSetData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class IsicTextsDataReversePopulator implements Populator<IsicTextsSetData, IsicTextsModel>{

	private static final String ARABIC_LANGUAGE_KEY = "ar";
	
    public void populate(IsicTextsSetData source, IsicTextsModel target) throws ConversionException {

        target.setCode(source.getIndsector());
        target.setDescription(source.getText_en(), Locale.ENGLISH);
        target.setDescription(source.getText_ar(), new Locale(ARABIC_LANGUAGE_KEY));
        target.setIsicColumnType(source.getTextshort());

    }
}
