/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.services.http;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


/**
 *
 */
public class HttpURLConnectionResponse
{
	private static final byte[] ZERO_BYTES = new byte[0];

	protected Map<String, List<String>> headerFields = Collections.emptyMap();
	protected IOException ioException;
	protected byte[] payload = ZERO_BYTES;
	protected byte[] payloadError = ZERO_BYTES;
	protected int responseCode = -1;
	protected long timeEnd;
	protected final long timeStart = System.currentTimeMillis();

	/**
	 * @return
	 */
	public long getDuration()
	{
		if (this.timeEnd == 0)
		{
			this.timeEnd = System.currentTimeMillis();
		}
		return this.timeEnd - this.timeStart;
	}

	/**
	 * @return All header keys and values.
	 * @see HttpURLConnection#getHeaderFields()
	 * @see #getHeaderField(String)
	 */
	public Map<String, List<String>> getHeaderFields()
	{
		return headerFields;
	}

	/**
	 * Return all values set for the header key.
	 *
	 * @param headerKey
	 *           Case insensitive header key.
	 * @return {@link List} of {@link String}
	 */
	@Nonnull
	public List<String> getHeaderField(final String headerKey)
	{
		return headerFields.entrySet().stream() //
				.filter(e -> headerKey.equalsIgnoreCase(e.getKey())) //
				.map(Entry::getValue) //
				.flatMap(List::stream) //
				.collect(Collectors.toList());
	}

	/**
	 * @return
	 */
	public IOException getIOException()
	{
		return this.ioException;
	}

	/**
	 * @return
	 */
	public byte[] getPayload()
	{
		return this.payload;
	}

	/**
	 * @return
	 */
	public byte[] getPayloadError()
	{
		return this.payloadError;
	}

	/**
	 * @return
	 */
	public int getResponseCode()
	{
		return this.responseCode;
	}

	/**
	 * @param headerFields
	 */
	public void setHeaderFields(final Map<String, List<String>> headerFields)
	{
		this.headerFields = headerFields;
	}

	/**
	 * @param ioException
	 */
	public void setIOException(final IOException ioException)
	{
		this.ioException = ioException;
	}

	/**
	 * @param payload
	 */
	public void setPayload(final byte[] payload)
	{
		this.payload = payload;
	}

	/**
	 * @param payloadError
	 */
	public void setPayloadError(final byte[] payloadError)
	{
		this.payloadError = payloadError;
	}

	/**
	 * @param responseCode
	 */
	public void setResponseCode(final int responseCode)
	{
		this.responseCode = responseCode;
	}

}
