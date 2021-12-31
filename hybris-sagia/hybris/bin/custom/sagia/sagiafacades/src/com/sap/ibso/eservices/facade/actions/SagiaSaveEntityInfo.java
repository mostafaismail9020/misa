package com.sap.ibso.eservices.facade.actions;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.facades.sagia.SagiaODataFacade;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntityInformationData;

import de.hybris.platform.commerceservices.model.process.SagiaPublishLicenseProcessProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.task.RetryLaterException;

public class SagiaSaveEntityInfo extends AbstractAction<SagiaPublishLicenseProcessProcessModel> {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaSaveEntityInfo.class);
	 
	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	
    private final String RETURN_SUCCESS =  "XXXXX updated successfully";
	
	@Resource(name = "sagiaODataFacade")
    private SagiaODataFacade sagiaODataFacade;
	
	@Override
	public Set<String> getTransitions() {
		return AbstractAction.createTransitions("OK", "NOK");
	}

	@Override
	public String execute(SagiaPublishLicenseProcessProcessModel arg0) throws RetryLaterException, Exception {
		
		try {
			baseSiteService.setCurrentBaseSite(arg0.getSite() ,  true);	
			EntityInformationData entityInformationData = sagiaODataFacade.saveEntityOData( arg0.getSagiaLicense().getEntityInformation());
			
		//	if (RETURN_SUCCESS.equals(entityInformationData.getReturn())) {
				return("OK");
		//	}
			
			}
			catch (Exception e) {
				LOG.error("License entity information  "+arg0.getSagiaLicense().getCode()+" has not been published to CRM.",e);
				return("NOK");
			}
			
			
	}

}
