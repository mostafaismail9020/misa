/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sagia.investsaudi.portal.core.service;

public interface SagiainvestsaudistoreService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
