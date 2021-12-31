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
package com.sap.ibso.eservices.storefront.controllers.pages.abs;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages.abs
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public abstract class SagiaAbstractLoginPageController extends AbstractLoginPageController
{
	@Resource(name = "sagiaFormatProvider")
	private SagiaFormatProvider provider;

	@ModelAttribute("uiDateFormat")
	public String getUiDateFormat()
	{
		return provider.getUIDateFormat();
	}

	@ModelAttribute("uiTimeFormat")
	public String getUiTimeFormat()
	{
		return provider.getUITimeFormat();
	}

	@ModelAttribute("uiDateFormatUAQ")
	public String getUiDateFormatUAQ()
	{
		return provider.getUIDateFormatUAQ();
	}

	@ModelAttribute("uiTimeFormatUAQ")
	public String getUiTimeFormatUAQ()
	{
		return provider.getUITimeFormatUAQ();
	}
	
	public SagiaFormatProvider getProvider()
	{
		return provider;
	}

	public void setProvider(final SagiaFormatProvider provider)
	{
		this.provider = provider;
	}
}
