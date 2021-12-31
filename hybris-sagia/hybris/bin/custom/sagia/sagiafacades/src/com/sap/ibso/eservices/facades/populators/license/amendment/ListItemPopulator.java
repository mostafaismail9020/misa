package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItem;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ListItemPopulator implements Populator<CustomizingGetData, ListItem> {

    @Override
    public void populate(CustomizingGetData customizingGetData, ListItem listItem) {
        listItem.setId(customizingGetData.getFieldkey());
        listItem.setName(customizingGetData.getDescription());
    }
}
