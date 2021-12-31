package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.IsicDetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * SagiaIsicDetService
 */
public class SagiaIsicDetService extends AbstractSagiaService<IsicDetData> {
    /**
     * retrieves ISICDetailsList

     * @param flag flag
     * @param language language
     * @return a Collection of IsicDetData
     */
    public final Collection<IsicDetData> getISICDetailsList(String flag, String language) {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.put("Flag", flag == null ? "' '" : ("'" + flag + "'"));
        queryOptions.put("Lang", language == null ? "' '" : ("'" + language + "'"));
        queryOptions.put("IsicSection", "' '");
        queryOptions.put("IsicDivision", "' '");
        queryOptions.put("IsicGroup", "' '");
        queryOptions.put("IsicClass", "' '");
        return getCollection(IsicDetData.class, queryOptions);
    }

    /**
     * retrieves ISICDetailsList
     *
     * @param flag flag
     * @param language language
     * @param isicSection section
     * @param isicDivision division
     * @param isicGroup group
     * @param isicClass class
     * @return a Collection of IsicDetData
     */
    public final Collection<IsicDetData> getISICFullDetailsList(String flag, String language, String isicSection, String isicDivision, String isicGroup, String isicClass) {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.put("Flag", flag == null ? "' '" : ("'" + flag + "'"));
        queryOptions.put("Lang", language == null ? "' '" : ("'" + language + "'"));
        queryOptions.put("IsicSection",isicSection == null ? "' '" : ("'" + isicSection + "'"));
        queryOptions.put("IsicDivision", isicDivision == null ? "' '" : ("'" + isicDivision + "'"));
        queryOptions.put("IsicGroup", isicGroup == null ? "' '" : ("'" + isicGroup + "'"));
        queryOptions.put("IsicClass", isicClass == null ? "' '" : ("'" + isicClass + "'"));
        return getCollection(IsicDetData.class, queryOptions);
    }
}
