package com.sap.ibso.eservices.sagiaservices.services.isic;

import java.util.Collection;

import com.sap.ibso.eservices.sagiaservices.data.IsicTextsSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

/**
 * IsicTextsService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class IsicTextsService extends AbstractSagiaService<IsicTextsSetData> {
   
	/**
     * getDeltaCollection
     * @return collection
     */
    public Collection<IsicTextsSetData> getDeltaCollection(){
        QueryOptionsBuilder query = new QueryOptionsBuilder().filter("Delta eq 'X'");
        return super.getCollection(IsicTextsSetData.class, query.build());
    }
    
    public Collection<IsicTextsSetData> getCollection(){
        return super.getCollection(IsicTextsSetData.class);
    }

}
