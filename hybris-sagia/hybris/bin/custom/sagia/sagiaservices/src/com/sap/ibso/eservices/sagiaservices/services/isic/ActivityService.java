
package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicActivityData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * ActivityService
 */
public class ActivityService extends AbstractSagiaService<IsicActivityData> {


    /**
     * retrieves Collection of IsicActivityData
     *
     * @param language language
     * @param parentIds parentIds
     * @param licenseType licenseType
     * @return list with all activities
     */
    public final Collection<IsicActivityData> getCollection(String language, List<String> parentIds, String licenseType) {
        Map<String, String> queryOptions = new QueryOptionsBuilder().filter(IsicUtil.buildFilterExpression(language, parentIds, licenseType)).build();
        return getCollection(IsicActivityData.class, queryOptions);
    }
}
