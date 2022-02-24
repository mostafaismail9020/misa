package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.BrandPresenceInMENARegion;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

public class BrandPresenceInMENARegionService extends AbstractSagiaService<BrandPresenceInMENARegion>{

	    /**
	     * saves Entity
	     *
	     * @param brandPresenceInMENARegion BrandPresenceInMENARegion
	     * @return String
	     */
		public void saveBrandPresenceInMENARegion(BrandPresenceInMENARegion brandPresenceInMENARegion, EntityInformationModel entityInformationModel) {
			brandPresenceInMENARegion.setRefid(entityInformationModel.getLicense().getApplicantReferenceID());
			super.saveAndParseResponse(brandPresenceInMENARegion, BrandPresenceInMENARegion.class);
		}

	    

}
