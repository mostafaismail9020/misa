package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZAPPEALData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;

import java.util.Map;

/**
 *
 */
public class ClassificationUpgradeAppealPopulator extends ODataPopulator<ZAPPEALData> {

    @Override
    public void populate(ODataModel model, ZAPPEALData zClassAppeal) {
        super.populate(model, zClassAppeal);
        Map<String, Object> zClassAppealSetModel = model.get();
        String classProperty = CollectionUtils.getValueFrom(zClassAppealSetModel, "Class");
        zClassAppeal.setClassProperty(classProperty);
    }
}
