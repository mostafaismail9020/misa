package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntitiesManagedByRhq;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

public class EntitiesManagedByRhqService extends AbstractSagiaService<EntitiesManagedByRhq>{

	    /**
	     * saves Entity
	     *
	     * @param entitiesManagedByRhqData EntitiesManagedByRhq
	     * @return String
	     */
		public void saveEntityManagedByRhqEntity(EntitiesManagedByRhq entitiesManagedByRhqData, EntityInformationModel entityInformationModel) {
			entitiesManagedByRhqData.setRefid(entityInformationModel.getLicense().getApplicantReferenceID());
			super.saveAndParseResponse(entitiesManagedByRhqData, EntitiesManagedByRhq.class);
		}

	    

}
