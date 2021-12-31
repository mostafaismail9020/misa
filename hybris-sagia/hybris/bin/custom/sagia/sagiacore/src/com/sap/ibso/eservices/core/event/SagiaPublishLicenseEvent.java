package com.sap.ibso.eservices.core.event;

import com.sap.ibso.eservices.core.model.SagiaLicenseModel;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

@SuppressWarnings("serial")
public class SagiaPublishLicenseEvent extends AbstractCommerceUserEvent<BaseSiteModel> {
	
	private SagiaLicenseModel  sagiaLicenseModel;
	
	public SagiaLicenseModel getSagiaLicenseModel() {
		return sagiaLicenseModel;
	}
	
	public void setSagiaLicenseModel(SagiaLicenseModel sagiaLicenseModel) {
		this.sagiaLicenseModel = sagiaLicenseModel;
	}

}
