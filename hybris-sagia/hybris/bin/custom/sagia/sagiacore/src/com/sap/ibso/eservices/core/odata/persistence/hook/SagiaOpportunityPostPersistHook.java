package com.sap.ibso.eservices.core.odata.persistence.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.investsaudi.portal.core.model.OpportunityProductModel;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.odata2services.odata.persistence.hook.PostPersistHook;
import com.sap.ibso.eservices.core.util.SagiaKeyStakeHoldersImageUtil;

public class SagiaOpportunityPostPersistHook implements PostPersistHook {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(SagiaOpportunityPostPersistHook.class);

	@Override
	public void execute(ItemModel item) {
		LOG.info("Entering SagiaOpportunityPostPersistHook#execute");		
		
		if (item instanceof OpportunityProductModel) {
			OpportunityProductModel opportunity = (OpportunityProductModel) item;

			 try {
				 SagiaKeyStakeHoldersImageUtil sagiaKeyStakeHoldersImageUtil = new SagiaKeyStakeHoldersImageUtil();
				 sagiaKeyStakeHoldersImageUtil.generateKeyStakeholdersImages(opportunity);
			    } catch (Exception e) {
			        LOG.error("Keystakeholders Image Update in Opportunity failed with error: ", e);
			    }
		}
		
	}

}
