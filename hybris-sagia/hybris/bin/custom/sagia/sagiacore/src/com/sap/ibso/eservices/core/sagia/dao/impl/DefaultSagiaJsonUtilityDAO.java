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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonDraftModel;
import com.sap.ibso.eservices.core.model.SagiaJsonUtilityModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaJsonUtilityDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaJsonUtilityDAO extends DefaultGenericDao<SagiaJsonUtilityModel> implements SagiaJsonUtilityDAO
{
	private FlexibleSearchService flexibleSearchService;

	public DefaultSagiaJsonUtilityDAO(String typecode) {
		super(typecode);
	}

	@Override
	public List<SagiaJsonUtilityModel> getJsonUtility(final String userPk, final String serviceId)
	{
		validateParameterNotNull(userPk, "User PK cannot be null");
		validateParameterNotNull(serviceId, "Service id cannot be null");

		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaJsonUtilityModel.USERID, userPk);
		parameters.put(SagiaJsonUtilityModel.SERVICEID, serviceId);

		return find(parameters);
	}

	@Override
	public List<SagiaJsonUtilityModel> getJsonUtilities(final String userPk)
	{
		validateParameterNotNull(userPk, "User PK cannot be null");

		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaJsonUtilityModel.USERID, userPk);

		return find(parameters);
	}

	@Override
	public List<SagiaJsonUtilityModel> getJsonUtilities(final Date date)
	{
		final String query = "SELECT {" + SagiaJsonDraftModel.PK + "} FROM {" +
						SagiaJsonUtilityModel._TYPECODE + "} WHERE {" + SagiaJsonUtilityModel.CREATIONTIME + "} < ?date";

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
		flexibleSearchQuery.addQueryParameter("date", date);

		SearchResult<SagiaJsonUtilityModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
		return searchResult.getResult();
	}

	@Override
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Override
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
