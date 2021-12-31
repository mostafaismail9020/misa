
package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicGroupData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * GroupService
 */
public class GroupService extends AbstractSagiaService<IsicGroupData> {

    /**
     * retrieves Collection of IsicGroupData
     * @param language language
     * @param parentIds parentIds
     * @return list with all groups
     */
    public final Collection<IsicGroupData> getCollection(String language, List<String> parentIds) {
        Map<String, String> queryOptions = new QueryOptionsBuilder().filter(IsicUtil.buildFilterExpression(language, parentIds)).build();
        return getCollection(IsicGroupData.class, queryOptions);
    }
}
