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
package com.sap.ibso.eservices.sagiaservices.constants;

/**
 * Global class for all SagiaServices constants. You can add global constants for your extension into this class.
 */
@SuppressWarnings(
{ "deprecation", "PMD", "squid:CallToDeprecatedMethod" })
public final class SagiaservicesConstants extends GeneratedSagiaservicesConstants
{
	@SuppressWarnings("squid:S2387")
	public static final String EXTENSIONNAME = "sagiaservices";
	public static final String PRODUCT = "P";
	public static final String CATEGORY = "C";

	public static final String GENERIC_RECO_TYPE = "G";
	public static final String RESTRICTED_RECO_TYPE = "R";

	public static final String APPLICATION_JSON = "application/json";
	public static final String MULTIPART_MIXED = "multipart/mixed";
	public static final String ACCEPT = "Accept";
	public static final String ACCEPT_LANGUAGE = "Accept-Language";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String X_REQUESTED_WITH = "X-Requested-With";
	public static final String X_REQUESTED_WITH_VALUE_X = "X";
	public static final String FORMAT_JSON = "json";

	public static final String REQUEST_TYPE_GET = "GET";
	public static final String REQUEST_TYPE_POST = "POST";
	public static final String REQUEST_TYPE_PUT = "PUT";
	public static final String REQUEST_TYPE_DELETE = "DELETE";

	public static final String REQUEST_PARAMETER_FORMAT = "$format";
	public static final String REQUEST_PARAMETER_EXPAND = "$expand";
	public static final String REQUEST_PARAMETER_FILTER = "$filter";

	public static final String USER_TEST_USERNAME = "TUSER10";
	public static final String USER_TEST_PASS = "welcome20";

	public static final String YES = "Y";
	public static final String NO = "N";

	public static final String PROCESS_TYPE = "PROCESS_TYPE";
	public static final String CAT_CODE1 = "CAT_CODE1";
	public static final String CAT_CODE2 = "CAT_CODE2";

	public static final String COMMON_DATE_FORMAT = "dd.MM.yyyy";
	public static final String COMMON_DATETIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

	private SagiaservicesConstants()
	{
		//empty to avoid instantiating this constant class
	}
}
