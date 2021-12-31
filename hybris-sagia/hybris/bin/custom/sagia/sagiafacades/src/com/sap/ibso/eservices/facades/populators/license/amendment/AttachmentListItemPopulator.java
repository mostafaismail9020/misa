package com.sap.ibso.eservices.facades.populators.license.amendment;

import com.sap.ibso.eservices.facades.data.license.amendment.listItem.AttachmentListItem;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class AttachmentListItemPopulator implements Populator<CustomizingGetData, AttachmentListItem> {

    @Override
    public void populate(CustomizingGetData customizingGetData, AttachmentListItem attachmentListItem) {
        attachmentListItem.setId(customizingGetData.getFieldkey().trim());
        attachmentListItem.setName(customizingGetData.getDescription());
        attachmentListItem.setLongDescription(customizingGetData.getLongDescr());
        attachmentListItem.setDockeyId(customizingGetData.getDockey_ID());
    }
}
