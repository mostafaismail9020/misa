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
package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.core.model.SagiaDateFormatModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaDateFormatDAO;
import com.sap.ibso.eservices.core.sagia.exception.SagiaFormatException;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaDateFormatDAO extends DefaultGenericDao<SagiaDateFormatModel> implements SagiaDateFormatDAO
{
	public DefaultSagiaDateFormatDAO(String typeCode)
	{
		super(typeCode);
	}

	@Override
	public List<SagiaDateFormatModel> getDateFormats()
	{
		final Map parameters = new HashMap();
		List<SagiaDateFormatModel> results = find(parameters);

		if (CollectionUtils.isEmpty(results))
		{
			throw new SagiaFormatException("No configuration was found for date formats, don't forget to load configuration for Arabic, English");
		}

		return results;
	}
}
