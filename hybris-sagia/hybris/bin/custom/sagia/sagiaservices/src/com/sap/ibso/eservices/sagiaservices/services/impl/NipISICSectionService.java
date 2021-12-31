package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICSectionSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * NipISICSectionService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipISICSectionService extends AbstractSagiaService<NIPISICSectionSetData> {

    /**
     * retrieves Collection of NIPISICSectionSetData
     *
     * @param language language
     * @return Collection of NIPISICSectionSetData
     */
    public final Collection<NIPISICSectionSetData> getCollection(String language) {
        return super.getCollection(
                NIPISICSectionSetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq'" + language + "'");
    }


    /**
     * creates NIPISICSectionSetData
     * @param nipisicSectionSetData nipisicSectionSetData
     */
    public final void create(NIPISICSectionSetData nipisicSectionSetData){
        super.save(nipisicSectionSetData);
    }
}
