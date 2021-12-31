
package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicClassData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * ClassService
 */
public class ClassService extends AbstractSagiaService<IsicClassData> {

	/**
     * retrieves Collection of IsicClassData
     * @param language language
     * @param parentIds parentIds
	 * @return list with all classes
	 */
    public final Collection<IsicClassData> getCollection(String language, List<String> parentIds) {
        Map<String, String> queryOptions = new QueryOptionsBuilder().filter(IsicUtil.buildFilterExpression(language, parentIds)).build();
        return getCollection(IsicClassData.class, queryOptions);
    }
}
