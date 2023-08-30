/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.iam.setup;

import static com.investsaudi.portal.iam.constants.SagiaiamintegrationConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.investsaudi.portal.iam.constants.SagiaiamintegrationConstants;
import com.investsaudi.portal.iam.service.SagiaiamintegrationService;


@SystemSetup(extension = SagiaiamintegrationConstants.EXTENSIONNAME)
public class SagiaiamintegrationSystemSetup
{
	private final SagiaiamintegrationService sagiaiamintegrationService;

	public SagiaiamintegrationSystemSetup(final SagiaiamintegrationService sagiaiamintegrationService)
	{
		this.sagiaiamintegrationService = sagiaiamintegrationService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		sagiaiamintegrationService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return SagiaiamintegrationSystemSetup.class.getResourceAsStream("/sagiaiamintegration/sap-hybris-platform.png");
	}
}
