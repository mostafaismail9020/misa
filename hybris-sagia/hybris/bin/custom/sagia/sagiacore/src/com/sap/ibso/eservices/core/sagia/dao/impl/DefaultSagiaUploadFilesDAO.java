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

import com.sap.ibso.eservices.core.model.SagiaUploadFilesDataModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUploadFilesDAO;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.core.sagia.dao.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaUploadFilesDAO implements SagiaUploadFilesDAO
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<SagiaUploadFilesDataModel> getFilesUploadedFilesRecords(final Date date)
	{
		final String query = "SELECT {" + SagiaUploadFilesDataModel.PK + "} FROM {" +
						SagiaUploadFilesDataModel._TYPECODE + "} WHERE {" + SagiaUploadFilesDataModel.UPLOADTIME + "} < ?date";

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);
		flexibleSearchQuery.addQueryParameter("date", date);

		SearchResult<SagiaUploadFilesDataModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
		return searchResult.getResult();
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
