/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.iam.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.avalon.framework.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.investsaudi.portal.iam.request.SendRequest;
import com.investsaudi.portal.iam.request.SendRequestParameters;
import com.investsaudi.portal.iam.response.SendRequestResponse;

/**
 *
 */
public class SagiaNafathIamSendRequestServiceImpl extends SagiaNafathIamAbstractService<SendRequest, SendRequestResponse>
{

	private static final Logger logger = LoggerFactory.getLogger(SagiaNafathIamSendRequestServiceImpl.class);

	@Override
	public SendRequestResponse process(SendRequest requestPayload) throws ServiceException
	{
		try 
		{
			SendRequestParameters parameters = new SendRequestParameters();
			parameters.setId("1000003366");
			parameters.setService("Login");
			requestPayload.setAction("SpRequest");
			requestPayload.setParameters(parameters);
			System.out.println("Request payload" + (null != requestPayload ? requestPayload.getAction() : requestPayload));
			final HttpHeaders headers = addHeadersToServiceRequest(getApiKey(), getApiValue());
			final HttpEntity<SendRequest> request = new HttpEntity<SendRequest>(requestPayload, headers);
			final ResponseEntity<SendRequestResponse> responseEntity = getRestTemplate().exchange(
					new URI(getKey()), HttpMethod.POST, request, SendRequestResponse.class);
			return responseEntity.getBody();
		}
		catch(final URISyntaxException e)
		{
			logger.error("Nafath IAM API Exception", e);
			throw new ServiceException(e.getReason(), e.getMessage(), e.getCause());
		}
	}
	
	
}
