
package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicDivisionData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * DivisionService
 */
public class DivisionService extends AbstractSagiaService<IsicDivisionData> {

    /**
     * retrieves Collection of IsicDivisionData
     * @param language language
     * @param parentIds parentIds
     * @param licenseType licenseType
     * @return list with all divisions
     */
    public final Collection<IsicDivisionData> getCollection(String language, List<String> parentIds, String licenseType) {
        Map<String, String> queryOptions = new QueryOptionsBuilder().filter(IsicUtil.buildFilterExpression(language, parentIds, licenseType)).build();
        return getCollection(IsicDivisionData.class, queryOptions);
    }
}
