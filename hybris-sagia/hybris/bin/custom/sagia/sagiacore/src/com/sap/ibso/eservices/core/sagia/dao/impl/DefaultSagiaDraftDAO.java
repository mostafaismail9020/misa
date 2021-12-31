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

import com.sap.ibso.eservices.core.model.SagiaDraftModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaDraftDAO;
import de.hybris.platform.core.model.order.AbstractDraftModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaDraftDAO extends DefaultGenericDao<SagiaDraftModel> implements SagiaDraftDAO {
	private FlexibleSearchService flexibleSearchService;

	public DefaultSagiaDraftDAO(String typecode) {
		super(typecode);
	}

	public List<SagiaDraftModel> getDraft(String userPk, String formId) {
		validateParameterNotNull(userPk, "User PK cannot be null");
		validateParameterNotNull(formId, "Form id cannot be null");

		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaDraftModel.USERID, userPk);
		parameters.put(SagiaDraftModel.FORMID, formId);

		return find(parameters);
	}

	@Override
	public List<SagiaDraftModel> getDrafts(final String userPk) {
		validateParameterNotNull(userPk, "User PK cannot be null");

		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaDraftModel.USERID, userPk);

		return find(parameters);
	}

	@Override
	public List<SagiaDraftModel> getDrafts(final Date date) {
		final String query = "SELECT {" + SagiaDraftModel.PK + "} FROM {" +
						SagiaDraftModel._TYPECODE + "} WHERE {" + SagiaDraftModel.CREATIONTIME + "} < ?date";

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
		flexibleSearchQuery.addQueryParameter("date", date);

		SearchResult<SagiaDraftModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
		return searchResult.getResult();
	}

	@Override
	public Long getMaxTemporaryCode() {
		final String query = "SELECT {" + AbstractDraftModel.PK + "} FROM {" + AbstractDraftModel._TYPECODE + "} ORDER BY {" + AbstractDraftModel.TEMPORARYID + "} DESC LIMIT 1";

		//temporary workaround
		final AbstractDraftModel firstResult;
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
		SearchResult<AbstractDraftModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);

		if (searchResult.getCount() <= 0) {
			return 0L;
		} else {
			firstResult = searchResult.getResult().get(0);
			if (firstResult == null) {
				return 0L;
			}
		}

		return firstResult.getTemporaryId();
	}

	@Override
	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	@Override
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
