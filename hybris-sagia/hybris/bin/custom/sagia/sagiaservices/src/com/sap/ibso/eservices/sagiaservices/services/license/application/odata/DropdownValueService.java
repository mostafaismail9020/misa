package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DropdownValueData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * DropdownValueService
 */
public class DropdownValueService extends AbstractSagiaService<DropdownValueData> {
    /**
     * retrieves DropdownValuesData
     * @param language language
     * @param flag flag
     * @param region region
     * @return Collection of DropdownValueData
     */
    public Collection<DropdownValueData> getDropdownValuesData(String language, String flag, String region) {
        return super.getCollection(DropdownValueData.class,
                "Flag", "'" + flag + "'",
                "Language", "'" + language  + "'",
                "Region", "'" + region  + "'"
        );
    }
}
