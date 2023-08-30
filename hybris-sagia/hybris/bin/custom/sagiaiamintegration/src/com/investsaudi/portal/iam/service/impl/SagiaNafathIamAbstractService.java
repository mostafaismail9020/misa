/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.iam.service.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.investsaudi.portal.iam.service.SagiaNafathIamService;

/**
 *
 */
public abstract class SagiaNafathIamAbstractService<X extends Object, Y extends Object> implements SagiaNafathIamService<X, Y>
{

	@Resource
	private ConfigurationService configurationService;

	private String key;
	private String apiKey;
	private String apiValue;

	@Resource
	private SagiaNafathIamLoggingRequestInterceptor nafathIamLoggingRequestInterceptor;

	protected HttpHeaders addHeadersToServiceRequest(String apiKey, String apiValue)
	{
//		final byte[] plainApiValueBytes = apiValue.getBytes();
//		final byte[] base64ApiValueBytes = Base64.encodeBase64(plainApiValueBytes);
//		apiValue = new String(base64ApiValueBytes);
		final HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "ApiKey " + apiValue);
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		return headers;
	}

	protected RestTemplate getRestTemplate()
	{
		final RestTemplate restTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(nafathIamLoggingRequestInterceptor);
		restTemplate.getInterceptors().addAll(interceptors);
		return restTemplate;
	}


	@Override
	public Boolean isMocked()
	{
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public String getServiceUri()
	{
		// XXX Auto-generated method stub
		return configurationService.getConfiguration().getString(getKey());
	}

	/**
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key
	 *           the key to set
	 */
	public void setKey(final String key)
	{
		this.key = key;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey()
	{
		return apiKey;
	}

	/**
	 * @param apiKey
	 *           the apiKey to set
	 */
	public void setApiKey(final String apiKey)
	{
		this.apiKey = apiKey;
	}

	/**
	 * @return the apiValue
	 */
	public String getApiValue()
	{
		return apiValue;
	}

	/**
	 * @param apiValue
	 *           the apiValue to set
	 */
	public void setApiValue(final String apiValue)
	{
		this.apiValue = apiValue;
	}




}
