/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.services.evaluator.impl;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import com.investsaudi.model.restrictions.CMSSecurePortalRestrictionModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Evaluates a user restriction accordingly to context information.
 * <p/>
 */
public class CMSSecurePortalRestrictionEvaluator implements CMSRestrictionEvaluator<CMSSecurePortalRestrictionModel>
{
	private final static Logger LOG = Logger.getLogger(CMSSecurePortalRestrictionEvaluator.class);
	private CMSSiteService cmsSiteService;

	@Override
	public boolean evaluate(final CMSSecurePortalRestrictionModel cmsUserRestriction, final RestrictionData context)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("isEnableRegistration: " + cmsSiteService.getCurrentSite().isEnableRegistration());
		}
		return cmsSiteService.getCurrentSite().isEnableRegistration();
	}

	protected CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	@Required
	public void setCmsSiteService(CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

}
