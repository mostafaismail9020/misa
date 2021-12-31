/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.setup;

import static com.investsaudi.constants.InvestsaudiscpiConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.investsaudi.constants.InvestsaudiscpiConstants;


@SystemSetup(extension = InvestsaudiscpiConstants.EXTENSIONNAME)
public class InvestsaudiscpiSystemSetup
{

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
	}

	private InputStream getImageStream()
	{
		return InvestsaudiscpiSystemSetup.class.getResourceAsStream("/investsaudiscpi/sap-hybris-platform.png");
	}
}
