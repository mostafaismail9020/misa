package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.sagiaservices.data.odata.EstimatedOperatingCostForRhq;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

public class EstimatedOperatingCostForRhqService extends AbstractSagiaService<EstimatedOperatingCostForRhq>{

	    /**
	     * saves Entity
	     *
	     * @param estimatedOperatingCostForRhq EstimatedOperatingCostForRhq
	     * @return String
	     */
		public void saveEstimatedOperatingCostForRhq(EstimatedOperatingCostForRhq estimatedOperatingCostForRhq, EntityInformationModel entityInformationModel) {
			estimatedOperatingCostForRhq.setRefid(entityInformationModel.getLicense().getApplicantReferenceID());
			super.saveAndParseResponse(estimatedOperatingCostForRhq, EstimatedOperatingCostForRhq.class);
		}

	    

}
