package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZListData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * ClassificationUpgradeService
 */
public class ClassificationUpgradeService extends AbstractSagiaService<ZListData> {


    /**
     * get history list for all classifications upgrade
     * @return Collection of ZListData
     */
	public final Collection<ZListData> getClassificationUpgradeList() {
        return getCollection(ZListData.class, new String[]{ "Type", "'ZCLU'"});
    }
}
