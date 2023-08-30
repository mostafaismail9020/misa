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

import com.investsaudi.portal.iam.request.CheckRequestStatus;
import com.investsaudi.portal.iam.request.SendRequestParameters;
import com.investsaudi.portal.iam.response.CheckRequestStatusResponse;

/**
 *
 */
public class SagiaNafathIamCheckRequestStatusServiceImpl extends SagiaNafathIamAbstractService<CheckRequestStatus, CheckRequestStatusResponse>
{

	private static final Logger logger = LoggerFactory.getLogger(SagiaNafathIamCheckRequestStatusServiceImpl.class);

	@Override
	public CheckRequestStatusResponse process(CheckRequestStatus requestPayload) throws ServiceException
	{
		try 
		{
			SendRequestParameters parameters = new SendRequestParameters();
			parameters.setId("1000003366");
			parameters.setTransId("878888888");
			parameters.setRandom("80");
			requestPayload.setAction("CheckSpRequest");
			requestPayload.setParameters(parameters);
			System.out.println("Request payload" + (null != requestPayload ? requestPayload.getAction() : requestPayload));
			final HttpHeaders headers = addHeadersToServiceRequest(getApiKey(), getApiValue());
			final HttpEntity<CheckRequestStatus> request = new HttpEntity<CheckRequestStatus>(requestPayload, headers);
			final ResponseEntity<CheckRequestStatusResponse> responseEntity = getRestTemplate().exchange(
					new URI(getKey()), HttpMethod.POST, request, CheckRequestStatusResponse.class);
			return responseEntity.getBody();
		}
		catch(final URISyntaxException e)
		{
			logger.error("Nafath IAM API Exception", e);
			throw new ServiceException(e.getReason(), e.getMessage(), e.getCause());
		}
	}
	
	
}
