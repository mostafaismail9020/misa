package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.IsicInfoData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * IsicMasterDataService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class IsicInformationService extends AbstractSagiaService<IsicInfoData>{

	    /**
	     * saves Entity
	     *
	     * @param basicContactInfoData entityInformationData
	     * @return String
	     */
	    public IsicInfoData saveEntity(IsicInfoData isicInfoData,EntityInformationModel entityInformationModel) {
	        return super.saveAndParseResponse(isicInfoData, IsicInfoData.class);
	    }

	    

}
