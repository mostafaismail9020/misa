/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.WarningLettersInfos;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * WarningLettersInfosService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class WarningLettersInfosService extends AbstractSagiaService<WarningLettersInfos>
{
	private static final String WARNING_LETTER_EXTENSION_SERVICE_TYPE = "ZFLUP_03";
	private static final String VIOLATION_REPLIES_SERVICE_TYPE = "ZFLUP_02";
	private static final String SERVICE_TYPE = "ServiceType";
	private static final String WARNING_LETTERS_EXPAND = "WarningInfosToViolationsNav";


	/**
	 * retrieves WarningLettersWarningLetterExtension
	 * @return Collection of WarningLettersInfos
	 */
	public Collection<WarningLettersInfos> getWarningLettersWarningLetterExtension() {
		final String parameters = SERVICE_TYPE + " eq '" + WARNING_LETTER_EXTENSION_SERVICE_TYPE + "'";
		return getCollection(WarningLettersInfos.class, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, parameters, REQUEST_PARAMETER_EXPAND, WARNING_LETTERS_EXPAND);
	}

	/**
	 * retrieves WarningLettersViolationReplies
	 * @return Collection of WarningLettersInfos
	 */
	public Collection<WarningLettersInfos> getWarningLettersViolationReplies() {
		final String parameters = SERVICE_TYPE + " eq '" + VIOLATION_REPLIES_SERVICE_TYPE + "'";
		return getCollection(WarningLettersInfos.class, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, parameters, REQUEST_PARAMETER_EXPAND, WARNING_LETTERS_EXPAND);
	}
}
