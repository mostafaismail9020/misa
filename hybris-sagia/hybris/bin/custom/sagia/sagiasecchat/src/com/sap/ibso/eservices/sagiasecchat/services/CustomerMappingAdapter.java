/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiasecchat.services;

import com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants;
import com.sap.ibso.eservices.sagiasecchat.data.CustomerInfo;
import com.sap.ibso.eservices.sagiasecchat.sapcp.service.SapCpServiceFactory;

import de.hybris.platform.apiregistryservices.exceptions.CredentialException;
import de.hybris.platform.apiregistryservices.services.ApiRegistryClientService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.util.Config;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import rx.Observable;

import java.util.List;
import java.util.Map;

/**
 *
 * Customer Mapping adapter for retrieving customer id from YaaS in a blocking
 * way but not to affect other YaaS Client
 *
 */
public class CustomerMappingAdapter implements CustomerMappingClient {
	
	
	//private SecYaasServiceFactory yaasServiceFactory;

	//private SapCpServiceFactory sapCpServiceFactory;

	private ApiRegistryClientService apiRegistryClientService;
	
	private ConfigurationService configurationService;

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	@Required
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	

	@Override
	public Observable<List<CustomerInfo>> getCustomer(final String lang, final String mixinQuery, final String id) {
		try {
			return getAdaptee().getCustomer(lang, mixinQuery, id);
		} catch (CredentialException e) {
			
		}
		return null;
	}

	public Observable<List<CustomerInfo>> getCustomer(final String lang, final String id) {
		
		return getCustomer(lang, StringUtils.defaultIfEmpty(Config.getParameter(SagiasecchatConstants.MIXIN_QUERY_KEY),
				SagiasecchatConstants.MIXIN_QUERY_DEFAULT_VALUE), id);
	}

	public CustomerMappingClient getAdaptee() throws CredentialException {
		
	   return apiRegistryClientService.lookupClient(com.sap.ibso.eservices.sagiasecchat.services.CustomerMappingClient.class);	
	}
	
	

	public ApiRegistryClientService getApiRegistryClientService() {
		return apiRegistryClientService;
	}

	public void setApiRegistryClientService(ApiRegistryClientService apiRegistryClientService) {
		this.apiRegistryClientService = apiRegistryClientService;
	}

	
}
