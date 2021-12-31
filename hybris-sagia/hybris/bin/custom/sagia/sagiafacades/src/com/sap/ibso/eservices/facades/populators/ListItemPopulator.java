package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.ListItem;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ListItemPopulator implements Populator<CustomizingGetData, ListItem> {
    @Override
    public void populate(CustomizingGetData customizingGetData, ListItem listItem) throws ConversionException {
        listItem.setScenario(customizingGetData.getScenario());
        listItem.setFieldName(customizingGetData.getFieldname());
        listItem.setFieldKey(customizingGetData.getFieldkey());
        listItem.setFieldSubType(customizingGetData.getFieldSubtype());
        listItem.setDescription(customizingGetData.getDescription());
        listItem.setDockeyID(customizingGetData.getDockey_ID());
        listItem.setLongDescription(customizingGetData.getLongDescr());
        listItem.setOptionalAttachment(customizingGetData.getOptionalAttach());
    }
}
