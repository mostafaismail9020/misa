package com.sap.ibso.eservices.core.odata.persistence.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.investsaudi.portal.core.model.OpportunityProductModel;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.odata2services.odata.persistence.hook.PostPersistHook;
import com.sap.ibso.eservices.core.util.SagiaKeyStakeHoldersImageUtil;
import com.sap.ibso.eservices.core.util.SagiaValueChainImageUtil;

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
			 
			 //ValueChain attachment logic
			 try {
			 SagiaValueChainImageUtil sagiaValueChainImageUtil = new SagiaValueChainImageUtil();
			 sagiaValueChainImageUtil.generateValueChainImages(opportunity);
			 }
			 
			 catch (Exception e) {
			        LOG.error("Value chain Image Update in Opportunity failed with error: ", e);
			    }
			 
		}
		
	}

}
