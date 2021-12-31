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
package com.investsaudi.jalo.restrictions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.localization.Localization;

import org.apache.log4j.Logger;


public class CMSSecurePortalRestriction extends GeneratedCMSSecurePortalRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(CMSSecurePortalRestriction.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		return super.createItem(ctx, type, allAttributes);
	}

	/**
	 * @deprecated Since 5.4. use
	 *             {@link com.investsaudi.model.restrictions.CMSSecurePortalRestrictionModel#getDescription()}
	 *             instead.
	 */
	@Deprecated
	@Override
	public String getDescription(SessionContext sessionContext)
	{
		return Localization.getLocalizedString("type.CMSSecurePortalRestriction.description.text");
	}
}
