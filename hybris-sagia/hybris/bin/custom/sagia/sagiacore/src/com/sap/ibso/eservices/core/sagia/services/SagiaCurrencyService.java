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
package com.sap.ibso.eservices.core.sagia.services;

import java.math.BigDecimal;

/**
 * Provides access to the Currency Service
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaCurrencyService {

	/**
	 * Get a value with currency
	 * @param value - The value
	 * @return - The value with its currency.
	 */
	String getValueWithCurrency(BigDecimal value);

	/**
	 * Get a value with currency
	 * @param stringValue - The value
	 * @return - The value with its currency.
	 */
	String getValueWithCurrency(String stringValue);
}
