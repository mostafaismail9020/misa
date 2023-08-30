/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.iam.service;

import org.apache.avalon.framework.service.ServiceException;


/**
 *
 */
public interface SagiaNafathIamService<X, Y>
{

	/**
	 * It will return the response based on the service and request.
	 *
	 * @param request
	 *           the request
	 * @return response
	 * @throws ServiceException
	 *            Service exception if there is error in the response
	 */
	Y process(final X request) throws ServiceException;

	/**
	 * It will return if the service to be invoked as mock.
	 *
	 * @return boolean
	 */
	Boolean isMocked();

	/**
	 * It will return the service uri.
	 *
	 * @return the service uri
	 */
	String getServiceUri();
}
