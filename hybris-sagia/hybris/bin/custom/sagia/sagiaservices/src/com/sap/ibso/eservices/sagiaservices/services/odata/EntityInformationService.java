package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntityInformationData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * IsicMasterDataService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class EntityInformationService extends AbstractSagiaService<EntityInformationData>{

	    /**
	     * saves Entity
	     *
	     * @param basicContactInfoData entityInformationData
	     * @return String
	     */
	    public EntityInformationData saveEntity(EntityInformationData entityInformationData,EntityInformationModel entityInformationModel) {
	    	entityInformationData.setRefid(entityInformationModel.getLicense().getApplicantReferenceID());
	        return super.saveAndParseResponse(entityInformationData, EntityInformationData.class);
	    }
}
