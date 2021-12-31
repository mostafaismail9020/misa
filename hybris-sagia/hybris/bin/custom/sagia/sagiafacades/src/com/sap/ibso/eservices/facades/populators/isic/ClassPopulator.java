package com.sap.ibso.eservices.facades.populators.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicClass;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicClassData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ClassPopulator implements Populator<IsicMasterModel, IsicClass> {

    @Override
    public void populate(IsicMasterModel source, IsicClass target) {

        target.setClassId(source.getIsicClass());
        target.setGroupId(source.getIsicGroup());
    }
}