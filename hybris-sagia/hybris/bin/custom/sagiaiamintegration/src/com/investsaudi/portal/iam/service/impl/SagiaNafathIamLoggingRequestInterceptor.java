/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.iam.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 *
 */
public class SagiaNafathIamLoggingRequestInterceptor implements ClientHttpRequestInterceptor
{

	private static final Logger logger = LoggerFactory.getLogger(SagiaNafathIamLoggingRequestInterceptor.class);

	final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution)
			throws IOException
	{
		traceRequest(request, body);
		final ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(final HttpRequest request, final byte[] body) throws IOException
	{
		logger.info("=========================Nafath IAM Request Begin=================================");
		logger.info("URI  : " + request.getURI());
		logger.info("Method  : " + request.getMethod());
		logger.info("Request Body : " + new String(body, "UTF-8"));
		logger.info("Headers :" + request.getHeaders());
		logger.info("Request Start Time : " + dateFormat.format(new Date()));
		logger.info("=========================Nafath IAM Request End====================================");
	}

	private void traceResponse(final ClientHttpResponse response) throws IOException
	{
		BufferedReader bufferedReader = null;
		try
		{
			final StringBuilder inputStringBuilder = new StringBuilder();
			bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
			String line = bufferedReader.readLine();
			while (line != null)
			{
				inputStringBuilder.append(line);
				inputStringBuilder.append('\n');
				line = bufferedReader.readLine();
			}
			logger.info("=========================Nafath IAM Response Begin=================================");
			logger.info("Status Code  : " + response.getStatusCode());
			logger.info("Status Text  : " + response.getStatusText());
			logger.info("Response Body : " + inputStringBuilder);
			logger.info("Response Received Time : " + dateFormat.format(new Date()));
			logger.info("=========================Nafath IAM Response End====================================");

		}
		finally
		{
			IOUtils.closeQuietly(bufferedReader);
		}
	}

}
