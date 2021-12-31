package com.sap.ibso.eservices.sagiaservices.services.impl;


import com.sap.ibso.eservices.sagiaservices.data.nip.NIPRegionSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * NipRegionService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipRegionService extends AbstractSagiaService<NIPRegionSetData> {

    /**
     * retrieves Collection of NIPRegionSetData
     * @param countryCode countryCode
     * @param language language
     * @return Collection of NIPRegionSetData
     */
    public final Collection<NIPRegionSetData> getCollection(String countryCode, String language) {
        return super.getCollection(
                NIPRegionSetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq '" + language + "' and COUNTRY eq '" + countryCode + "'");
    }

    /**
     * creates NIPRegionSetData
     * @param nipRegionSetData nipRegionSetData
     */
    public final void create(NIPRegionSetData nipRegionSetData){
        super.save(nipRegionSetData);
    }
}
