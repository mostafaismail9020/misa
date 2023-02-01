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
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalVals;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * GlobalValsService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public final class GlobalValsService extends AbstractSagiaService<GlobalVals>
{
	public static final String EQ = " eq '";
	public static final String AND = "' and ";
	private static final String VIOLATION_REPLIES_SERVICE_TYPE = "ZFLUP_02";
	private static final String WARNING_LETTER_EXTENSION_SERVICE_TYPE = "ZFLUP_03";
	private static final String SERVICE_TYPE = "ServiceType";
	private static final String SCENARIO = "Scenario";

	private static final String SCENARIO_CONTACT_UPDATE = "ZSR1";
    private static final String SCENARIO_LA = "ZSR5";
	private static final String SCENARIO_WL = "ZS15";
	private static final String SCENARIO_LR = "ZSR8";
	private static final String SCENARIO_LICENSE_RENEWAL = "ZSR4";
	private static final String SCENARIO_IGNITE_SERVICES = "ZS32";
	/**
	 * checks WarningLetterCreateAvalability
	 */
	public void checkWarningLetterCreateAvalability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_WL + AND + SERVICE_TYPE + EQ + WARNING_LETTER_EXTENSION_SERVICE_TYPE + "'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}

	/**
	 * checks ViolationRepliesCreateAvalability
	 */
	public void checkViolationRepliesCreateAvalability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_WL + AND + SERVICE_TYPE + EQ + VIOLATION_REPLIES_SERVICE_TYPE + "'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}

	/**
	 * checks LicenseReplacementAvalability
	 */
	public void checkLicenseReplacementAvalability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_LR +"'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}

	/**
	 * checks LicenseAmendmentAvailability
	 */
	public void checkLicenseAmendmentAvailability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_LA +"'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}

	/**
	 * checks contact update availability
	 */
	public void checkContactUpdateAvailability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_CONTACT_UPDATE +"'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}

	/**
	 * checks LicenseRenewalAvailability
	 */
	public void checkLicenseRenewalAvailability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_LICENSE_RENEWAL +"'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}

	/**
	 * checks IgniteServicesAvailability
	 */
	public void checkIgniteServicesAvailability() {
		final String filterParameters = SCENARIO + EQ + SCENARIO_IGNITE_SERVICES +"'";
		checkValidResponseCode(null, SagiaservicesConstants.REQUEST_PARAMETER_FILTER, filterParameters);
	}
}
