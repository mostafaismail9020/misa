package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICDivisionSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * NipISICDivisionService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipISICDivisionService extends AbstractSagiaService<NIPISICDivisionSetData> {

    /**
     * retrieves Collection of NIPISICDivisionSetData
     * @param language language
     * @return Collection of NIPISICDivisionSetData
     */
    public final Collection<NIPISICDivisionSetData> getCollection(String language) {
        return super.getCollection(
                NIPISICDivisionSetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq'" + language + "'");
    }

    /**
     * retrieves Collection of NIPISICDivisionSetData by sectionCode
     * @param sectionCode sectionCode
     * @param language language
     * @return Collection of NIPISICDivisionSetData
     */
    public final Collection<NIPISICDivisionSetData> getCollection(String sectionCode, String language) {
        return super.getCollection(
                NIPISICDivisionSetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq'" + language + "' and ISIC_SECTION eq '" + sectionCode + "'");
    }

    /**
     * creates NIPISICDivisionSetData
     * @param nipisicDivisionSetData nipisicDivisionSetData
     */
    public final void create(NIPISICDivisionSetData nipisicDivisionSetData){
        super.save(nipisicDivisionSetData);
    }
}
