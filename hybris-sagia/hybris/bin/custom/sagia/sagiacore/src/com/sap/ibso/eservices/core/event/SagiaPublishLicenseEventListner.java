package com.sap.ibso.eservices.core.event;

import de.hybris.platform.commerceservices.model.process.SagiaPublishLicenseProcessProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

public class SagiaPublishLicenseEventListner extends AbstractEventListener<SagiaPublishLicenseEvent> {

	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	
	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}

	public void setBusinessProcessService(BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}

	@Override
	protected void onEvent(SagiaPublishLicenseEvent event) {
		
		final SagiaPublishLicenseProcessProcessModel sagiaPublishLicenseProcessProcessModel = getBusinessProcessService()
				.createProcess("sagiaPublishLicenseProcess-" + event.getSagiaLicenseModel().getCode() + "-" + System.currentTimeMillis(),
						"sagiaPublishLicenseProcess");
		sagiaPublishLicenseProcessProcessModel.setSite(event.getSite());
		sagiaPublishLicenseProcessProcessModel.setSagiaLicense(event.getSagiaLicenseModel());
		
		getModelService().save(sagiaPublishLicenseProcessProcessModel);
		getBusinessProcessService().startProcess(sagiaPublishLicenseProcessProcessModel);
		
	}
	
	

}
