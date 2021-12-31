
package com.sap.ibso.eservices.sagiaservices.services.isic;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicSectionData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;
import java.util.Map;

/**
 * SectionService
 */
public class SectionService extends AbstractSagiaService<IsicSectionData> {

	/**
     * retrieves Collection of IsicSectionData
     * @param language language
     * @param licenseType licenseType
	 * @return list with all sections
	 */
    public final Collection<IsicSectionData> getCollection(String language, String licenseType) {
        Map<String, String> queryOptions = new QueryOptionsBuilder().filter(IsicUtil.buildFilterExpression(language, licenseType)).build();
        return getCollection(IsicSectionData.class, queryOptions);
    }
}
