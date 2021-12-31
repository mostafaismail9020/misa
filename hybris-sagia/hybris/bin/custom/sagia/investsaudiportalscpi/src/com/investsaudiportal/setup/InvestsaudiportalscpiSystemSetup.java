/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudiportal.setup;

import static com.investsaudiportal.constants.InvestsaudiportalscpiConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.investsaudiportal.constants.InvestsaudiportalscpiConstants;

@SystemSetup(extension = InvestsaudiportalscpiConstants.EXTENSIONNAME)
public class InvestsaudiportalscpiSystemSetup
{
	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
	}

	private InputStream getImageStream()
	{
		return InvestsaudiportalscpiSystemSetup.class.getResourceAsStream("/investsaudiportalscpi/sap-hybris-platform.png");
	}
}
