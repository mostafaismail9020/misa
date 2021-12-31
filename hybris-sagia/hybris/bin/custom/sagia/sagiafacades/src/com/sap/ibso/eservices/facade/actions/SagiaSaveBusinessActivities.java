package com.sap.ibso.eservices.facade.actions;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.core.enums.LicenseStatus;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.facades.sagia.SagiaODataFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemah2Facade;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemahFacade;

import de.hybris.platform.commerceservices.model.process.SagiaPublishLicenseProcessProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.task.RetryLaterException;

public class SagiaSaveBusinessActivities extends AbstractAction<SagiaPublishLicenseProcessProcessModel> {

private static final Logger LOG = LoggerFactory.getLogger(SagiaSaveBusinessActivities.class);
	   
	

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
	
	@Resource(name = "sagiaODataFacade")
    private SagiaODataFacade sagiaODataFacade;
	
	@Resource(name = "modelService")
    private ModelService modelService;
	
	@Override
	public Set<String> getTransitions() {
		return AbstractAction.createTransitions("OK", "NOK");
	}

	@Override
	public String execute(SagiaPublishLicenseProcessProcessModel arg0) throws RetryLaterException, Exception {
		try {
			baseSiteService.setCurrentBaseSite(arg0.getSite() ,  true);	
			sagiaODataFacade.savesIsicInfoOData(arg0.getSagiaLicense());
			
			// end of publishing update license	
			SagiaLicenseModel license = arg0.getSagiaLicense();
			license.setStatus(LicenseStatus.PROCESSED);
			modelService.save(license);
			}
			catch (Exception e) {
				LOG.error("License Business Activities  "+arg0.getSagiaLicense().getCode()+" has not been published to CRM.",e);
				return("NOK");
			}
			
			return "OK";
	}
	}


