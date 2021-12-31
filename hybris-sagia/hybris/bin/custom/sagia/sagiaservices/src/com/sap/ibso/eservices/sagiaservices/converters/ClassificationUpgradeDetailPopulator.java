package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCLASS_DETSETData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;

import java.util.Map;

/**
 *
 */
public class ClassificationUpgradeDetailPopulator extends ODataPopulator<ZCLASS_DETSETData> {
    @Override
    public void populate(ODataModel model, ZCLASS_DETSETData zClassDetail) {
        super.populate(model, zClassDetail);
        Map<String, Object> zClassDetSetModel = model.get();
        String classProperty = CollectionUtils.getValueFrom(zClassDetSetModel, "Class");
        zClassDetail.setClassProperty(classProperty);

    }


}