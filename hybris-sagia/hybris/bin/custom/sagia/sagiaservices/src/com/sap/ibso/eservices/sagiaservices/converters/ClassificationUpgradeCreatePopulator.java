package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZCREATEData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;

import java.util.Map;

/**
 *
 */
public class ClassificationUpgradeCreatePopulator extends ODataPopulator<ZCREATEData> {
    @Override
    public void populate(ODataModel model, ZCREATEData zCreate) {
        super.populate(model, zCreate);
        Map<String, Object> zCreateSetModel = model.get();
        String classProperty = CollectionUtils.getValueFrom(zCreateSetModel, "Class");
        String returnProperty = CollectionUtils.getValueFrom(zCreateSetModel, "Return");
        zCreate.setClassProperty(classProperty);
        zCreate.setReturnProperty(returnProperty);
    }
}
