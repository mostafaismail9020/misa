/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.iam.service;

public interface SagiaiamintegrationService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
