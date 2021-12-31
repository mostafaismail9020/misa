
package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicBranchData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BranchService
 */
public class BranchService extends AbstractSagiaService<IsicBranchData> {

    /**
     * retrieves Collection of IsicBranchData
     *
     * @param language language
     * @param parentIds parentIds
     * @return list with all branches
     */
    public final Collection<IsicBranchData> getCollection(String language, List<String> parentIds) {
        Map<String, String> queryOptions = new QueryOptionsBuilder().filter(IsicUtil.buildFilterExpression(language, parentIds)).build();
        return getCollection(IsicBranchData.class, queryOptions);
    }
}
