/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.sagiaasmaddon.restrictions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;


/**
 * Evaluates list of provided path restrictions.
 */
public class AssistedServicePathRestrictionEvaluator
{
	private List<AssistedServicePathRestriction> restrictions;

	public boolean evaluate(final HttpServletRequest httpservletrequest, final HttpServletResponse httpservletresponse)
	{
		for (final AssistedServicePathRestriction restriction : getRestrictions())
		{
			if (!restriction.evaluate(httpservletrequest, httpservletresponse))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * @return the restrictions
	 */
	protected List<AssistedServicePathRestriction> getRestrictions()
	{
		return restrictions;
	}

	/**
	 * @param restrictions
	 *           the restrictions to set
	 */
	@Required
	public void setRestrictions(final List<AssistedServicePathRestriction> restrictions)
	{
		this.restrictions = restrictions;
	}

}