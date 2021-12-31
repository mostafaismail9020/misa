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
package com.sap.ibso.eservices.core.sagia.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.sap.ibso.eservices.core.sagia.services.SagiaCurrencyService;
import org.apache.commons.lang.StringUtils;
import org.fest.util.Strings;

/**
 * Default implementation of CurrencyService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaCurrencyService implements SagiaCurrencyService {


	private DecimalFormat df = new DecimalFormat("#,###.##");
	@Override
	public String getValueWithCurrency(final BigDecimal value)
	{
		final BigDecimal roundedValue = value.setScale(2, RoundingMode.DOWN);
		return df.format(roundedValue);
	}

	@Override
	public String getValueWithCurrency(final String stringValue)
	{
		if(!Strings.isEmpty(stringValue)){
			final BigDecimal roundedValue = new BigDecimal(stringValue).setScale(2, RoundingMode.DOWN);
			return df.format(roundedValue);
		}
		return StringUtils.EMPTY;
	}
}
