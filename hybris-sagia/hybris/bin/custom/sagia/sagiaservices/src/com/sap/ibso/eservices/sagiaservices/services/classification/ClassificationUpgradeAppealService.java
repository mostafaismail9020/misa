package com.sap.ibso.eservices.sagiaservices.services.classification;

import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZAPPEALData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 *  ClassificationUpgradeAppealService
 */
public class ClassificationUpgradeAppealService extends AbstractSagiaService<ZAPPEALData> {


    /**
     * get appeal list for classifications upgrade
     * @return Collection of ZAPPEALData
     */
    public final Collection<ZAPPEALData> getClassificationUpgradeAppealList() {
        String functionImportParameter = "Type";
        String functionImportParameterValue = "'A'";
        return getCollection(ZAPPEALData.class, new String[]{functionImportParameter, functionImportParameterValue});
    }
}
