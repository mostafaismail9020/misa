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
package com.sap.ibso.eservices.sagiasecchat.sapcp.service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants.SAPCP_CLIENT_SCOPE;
import static com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants.SAPCP_CLIENT_URL;
import static com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants.SAPCP_OAUTH_CLIENTID;
import static com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants.SAPCP_OAUTH_CLIENTSECRET;
import static com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants.SAPCP_OAUTH_URL;
import static com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants.SAPCP_TENANT;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants;
import com.sap.ibso.eservices.sagiasecchat.services.TicketServiceClient;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.SystemException;

/**
 * 
 * SAP Cloud Platform Service Factory to provide the ability to access the
 * SEC endpoint [Ticket Service & Customer Service].
 *
 */
public class SapCpServiceFactory {

	private static final Logger LOG = LoggerFactory.getLogger(SapCpServiceFactory.class);
	
	private ConfigurationService configurationService;

	

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	@Required
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public <T> T lookupClient(final Class<T> clientType) {

		checkArgument(clientType != null, "serviceType must not be null");

		String appId = null;
		final Map<String, String> sapCpConfig = new HashMap<>();
		if (null == readProperty(SagiasecchatConstants.OAUTH_URL)) {
			throw new SystemException("Failed to find SAP CP oAuth credential configuration for the given serviceType :"
					+ clientType.getSimpleName());
		}
		sapCpConfig.put(SAPCP_OAUTH_URL, readProperty(SagiasecchatConstants.OAUTH_URL));
		sapCpConfig.put(SAPCP_OAUTH_CLIENTID, readProperty(SagiasecchatConstants.OAUTH_CLIENTID));
		sapCpConfig.put(SAPCP_OAUTH_CLIENTSECRET, readProperty(SagiasecchatConstants.OAUTH_CLIENTSECRET));
		sapCpConfig.put(SAPCP_TENANT, readProperty(SagiasecchatConstants.TENANT));
		LOG.debug("OAuth credentials SAP CP Oauth url=%s", sapCpConfig.get(SAPCP_OAUTH_URL));
		sapCpConfig.put(SAPCP_CLIENT_SCOPE, readProperty(SagiasecchatConstants.TENANT));
		if (clientType.isAssignableFrom(TicketServiceClient.class)) {
			sapCpConfig.put(SAPCP_CLIENT_URL, readProperty(SagiasecchatConstants.TICKET_CLIENT_URL));
			appId = readProperty(SagiasecchatConstants.TICKET_APP_ID);
			LOG.debug("Ticket Service configuration from properties file, client URL = %s",
					sapCpConfig.get(SAPCP_CLIENT_URL));
		} else {
			sapCpConfig.put(SAPCP_CLIENT_URL, readProperty(SagiasecchatConstants.BP_CLIENT_URL));
			appId = readProperty(SagiasecchatConstants.BP_APP_ID);
			LOG.debug("Customer Service configuration from properties file, client URL = %s",
					sapCpConfig.get(SAPCP_CLIENT_URL));
		}
		//c getCharonFactory().client(appId, serviceType, sapCpConfig, builder -> builder.build());
		return null;
	}

	/**
	 * Reading properties from configuration file
	 * 
	 * @param parameter
	 *            Key value
	 * @return corresponding value
	 */
	private String readProperty(String parameter) {
		return getConfigurationService().getConfiguration().getString(parameter);
	}

}
