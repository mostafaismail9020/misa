package com.sap.ibso.eservices.facade.actions;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.facades.sagia.SagiaODataFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemah2Facade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemahFacade;
import com.sap.ibso.eservices.sagiaservices.data.odata.BasicContactInformationData;

import de.hybris.platform.commerceservices.model.process.SagiaPublishLicenseProcessProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.task.RetryLaterException;

public class SagiaSaveContactPerson extends AbstractAction<SagiaPublishLicenseProcessProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SagiaSaveContactPerson.class);
	   
	
	
	private final String RETURN_SUCCESS =  "Contact Information updated successfully";
	
	@Resource(name = "sagiaODataFacade")
    private SagiaODataFacade sagiaODataFacade;
	
	

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	
	
	@Override
	public Set<String> getTransitions() {
		return AbstractAction.createTransitions("OK", "NOK");
	}

	@Override
	public String execute(SagiaPublishLicenseProcessProcessModel arg0) throws RetryLaterException, Exception {
		
		try {
			baseSiteService.setCurrentBaseSite(arg0.getSite() ,  true);	
			BasicContactInformationData basicContactInformationData = sagiaODataFacade.saveContactPersonOData(arg0.getSagiaLicense().getContactPerson());
	        
			if (RETURN_SUCCESS.equals(basicContactInformationData.getReturn())) {
				return("OK");
			}
			
			}
			catch (Exception e) {
				LOG.error("License Contact Person  "+arg0.getSagiaLicense().getCode()+" has not been published to CRM.",e);
				return("NOK");
			}
			
			return "NOK";
	}
	
	}


