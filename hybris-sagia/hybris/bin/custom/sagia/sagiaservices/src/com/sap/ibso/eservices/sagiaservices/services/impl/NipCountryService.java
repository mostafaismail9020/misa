package com.sap.ibso.eservices.sagiaservices.services.impl;


import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCountrySetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * NipCountryService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class NipCountryService extends AbstractSagiaService<NIPCountrySetData> {
    /**
     * retrieves Collection of NIPCountrySetData
     * @param language language
     * @return Collection of NIPCountrySetData
     */
    public final Collection<NIPCountrySetData> getCollection(String language) {
        return super.getCollection(
                NIPCountrySetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq'" + language + "'");
    }

    /**
     * retrieves Collection of NIPCountrySetData by nationalityType
     * @param  nationalityType nationalityType
     * @param language language
     * @return Collection of NIPCountrySetData
     */
    public final Collection<NIPCountrySetData> getCollection(String nationalityType, String language) {
        return super.getCollection(
                NIPCountrySetData.class,
                "$skip", "0",
                "$top", "500",
                "$inlinecount", "allpages",
                "$filter", "LANGUAGE eq'" + language + "' and NATIONALITY_CAT eq '" + nationalityType + "'");
    }

    /**
     * creates NIPCountrySetData
     * @param nipCountrySetData nipCountrySetData
     */
    public final void create(NIPCountrySetData nipCountrySetData) {
        super.save(nipCountrySetData);
    }
}
