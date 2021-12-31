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

import de.hybris.platform.assistedserviceservices.constants.AssistedserviceservicesConstants;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Path restriction implementation for Assisted Service Sales Group. Paths from provided list are restricted.
 */
public class AssistedServiceSalesGroupPathRestriction extends AssistedServicePathRestriction
{
	@Override
	public boolean evaluate(final HttpServletRequest httpservletrequest, final HttpServletResponse httpservletresponse)
	{
		if (pathMatches(httpservletrequest) && getAssistedServiceFacade().isAssistedServiceAgentLoggedIn())
		{
			final UserGroupModel managerGroup = getUserService().getUserGroupForUID(
					AssistedserviceservicesConstants.AS_MANAGER_AGENT_GROUP_UID);
			final UserModel agent = getAssistedServiceFacade().getAsmSession().getAgent();
			// restrict access in case agent is not in manager group
			if (!getUserService().isMemberOfGroup(agent, managerGroup))
			{
				sendRedirectToPreviousPage(httpservletrequest, httpservletresponse);
				return false;
			}
		}
		return true;
	}
}