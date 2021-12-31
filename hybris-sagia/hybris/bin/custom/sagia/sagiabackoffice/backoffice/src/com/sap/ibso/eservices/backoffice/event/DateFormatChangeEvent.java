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
package com.sap.ibso.eservices.backoffice.event;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package sagia.services.event
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DateFormatChangeEvent extends AbstractEvent implements ClusterAwareEvent
{
	@Override
	public boolean publish(final int i, final int i1)
	{
		return true;
	}
}
