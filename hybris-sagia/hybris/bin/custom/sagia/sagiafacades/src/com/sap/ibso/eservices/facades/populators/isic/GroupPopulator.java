package com.sap.ibso.eservices.facades.populators.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicGroup;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicGroupData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class GroupPopulator implements Populator<IsicMasterModel, IsicGroup> {

    @Override
    public void populate(IsicMasterModel source, IsicGroup target) {
        target.setGroupId(source.getIsicGroup());
        target.setDivisionId(source.getIsicDivision());
    }
}