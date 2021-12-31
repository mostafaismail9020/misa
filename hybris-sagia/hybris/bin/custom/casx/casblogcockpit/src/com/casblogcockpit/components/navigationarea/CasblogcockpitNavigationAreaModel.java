/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.casblogcockpit.components.navigationarea;

import de.hybris.platform.cockpit.components.navigationarea.DefaultNavigationAreaModel;
import de.hybris.platform.cockpit.session.impl.AbstractUINavigationArea;

import com.casblogcockpit.session.impl.CasblogcockpitNavigationArea;


/**
 * Casblogcockpit navigation area model.
 */
public class CasblogcockpitNavigationAreaModel extends DefaultNavigationAreaModel
{
	public CasblogcockpitNavigationAreaModel()
	{
		super();
	}

	public CasblogcockpitNavigationAreaModel(final AbstractUINavigationArea area)
	{
		super(area);
	}

	@Override
	public CasblogcockpitNavigationArea getNavigationArea()
	{
		return (CasblogcockpitNavigationArea) super.getNavigationArea();
	}
}
