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

import com.hybris.charon.RawResponse;
import com.sap.ibso.eservices.sagiasecchat.constants.SagiasecchatConstants;
import com.sap.ibso.eservices.sagiasecchat.data.TicketData;
import com.sap.ibso.eservices.sagiasecchat.data.TicketSecData;
import com.sap.ibso.eservices.sagiasecchat.data.TicketType;
import com.sap.ibso.eservices.sagiasecchat.data.TranscriptSec;
import com.sap.ibso.eservices.sagiasecchat.sapcp.service.SapCpServiceFactory;

import de.hybris.platform.apiregistryservices.exceptions.CredentialException;
import de.hybris.platform.apiregistryservices.services.ApiRegistryClientService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import rx.Observable;

import java.util.List;

/**
 * Impl for non-blocking calls for TicketServiceClient
 */
public class TicketServiceClientAdapter implements TicketServiceClient {

	private ApiRegistryClientService apiRegistryClientService;
	private static final Logger LOG = Logger.getLogger(TicketServiceClientAdapter.class);

	@Override
	public Observable<TicketData> getTicketDetails(String lang, String ticketId) {
		return getAdaptee().getTicketDetails(lang, ticketId);
	}


	@Override
	public Observable<RawResponse<List<TicketData>>> getTickets(String lang, String sort, int pageNumber, int pageSize,
			String customerId) {
		return getAdaptee().getTickets(lang, sort, pageNumber, pageSize, customerId);
	}

	@Override
	public Observable<RawResponse> createTicket(final String lang, final TicketSecData ticketData) {
		return getAdaptee().createTicket(lang, ticketData).doOnError(TicketServiceClientAdapter::logError);
	}

	protected static void logError(final Throwable error) {
		LOG.error("Error during ticket operation", error);
	}

	@Override
	public Observable<RawResponse> addMessage(String lang, String ticketId, TranscriptSec transcript) {
		return getAdaptee().addMessage(lang, ticketId, transcript);
	}

	@Override
	public Observable<List<TicketType>> getTicketTypes(String lang) {
		return getAdaptee().getTicketTypes(lang);
	}

	public TicketServiceClient getAdaptee() {
		try {
		
			return apiRegistryClientService.lookupClient(com.sap.ibso.eservices.sagiasecchat.services.TicketServiceClient.class);
							
		} catch (CredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public ApiRegistryClientService getApiRegistryClientService() {
		return apiRegistryClientService;
	}

	public void setApiRegistryClientService(ApiRegistryClientService apiRegistryClientService) {
		this.apiRegistryClientService = apiRegistryClientService;
	}
	

}
