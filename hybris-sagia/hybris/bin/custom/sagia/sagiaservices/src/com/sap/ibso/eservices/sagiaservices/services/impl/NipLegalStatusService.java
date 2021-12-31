package com.sap.ibso.eservices.sagiaservices.services.impl;


import com.sap.ibso.eservices.sagiaservices.data.nip.NIPLegalStatusSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

/**
 * NipLegalStatusService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipLegalStatusService extends AbstractSagiaService<NIPLegalStatusSetData> {

    /**
     * retrieves Collection of NIPLegalStatusSetData
     * @param language language
     * @return Collection of NIPLegalStatusSetData
     */
    public final Collection<NIPLegalStatusSetData> getCollection(String language) {
        // $skip=0&$top=500&$inlinecount=allpages&$filter=LANGUAGE%20eq%27E%27
        return super.getCollection(
                NIPLegalStatusSetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq'" + language + "'"
                );
    }


    /**
     * creates NIPLegalStatusSetData
     * @param nipLegalStatusSetData nipLegalStatusSetData
     */
    public final void create(NIPLegalStatusSetData nipLegalStatusSetData){
        super.save(nipLegalStatusSetData);
    }
}
