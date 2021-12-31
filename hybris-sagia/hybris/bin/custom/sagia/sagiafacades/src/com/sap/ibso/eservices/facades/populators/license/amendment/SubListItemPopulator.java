package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.listItem.SubListItem;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class SubListItemPopulator implements Populator<CustomizingGetData, SubListItem> {

    @Override
    public void populate(CustomizingGetData customizingGetData, SubListItem subListItem) {
        subListItem.setId(customizingGetData.getFieldkey());
        subListItem.setName(customizingGetData.getDescription());
        subListItem.setParentId(customizingGetData.getFieldSubtype());
    }
}
