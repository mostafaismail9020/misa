package com.sap.ibso.eservices.core.sagia.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeRequirementModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLicenseTypeRequirementDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultSagiaLicenseTypeRequirementDAO extends DefaultGenericDao<SagiaLicenseTypeRequirementModel> implements SagiaLicenseTypeRequirementDAO {

	private FlexibleSearchService flexibleSearchService;

	private static final String LICENSE_REQ_FOR_LIST = "SELECT {" + SagiaLicenseTypeRequirementModel.PK + "} FROM {"
			+ SagiaLicenseTypeRequirementModel._TYPECODE + "} WHERE {"
			+ SagiaLicenseTypeRequirementModel.SPLREQUIREMENTID + "} in (?listSplRequirement) AND {"
			+ SagiaLicenseTypeRequirementModel.ACTIVE + "} = 1";

	public DefaultSagiaLicenseTypeRequirementDAO(String typecode) {
		super(typecode);
	}

	@Override
	public SagiaLicenseTypeRequirementModel getLicenseTypeRequirementForID(String splRequirementId) {
		validateParameterNotNull(splRequirementId, "splRequirementId  must not be null!");

		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(SagiaLicenseTypeRequirementModel.SPLREQUIREMENTID, splRequirementId);
		parameters.put(SagiaLicenseTypeRequirementModel.ACTIVE, true);

		List<SagiaLicenseTypeRequirementModel> parameterList = find(parameters);
		if (CollectionUtils.isNotEmpty(parameterList)) {
			return (SagiaLicenseTypeRequirementModel) parameterList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<SagiaLicenseTypeRequirementModel> getLicenseTypeRequirementForListId(
			List<String> splRequirementListId) {

		if (CollectionUtils.isEmpty(splRequirementListId)) {
			return null;
		}

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(LICENSE_REQ_FOR_LIST);
		flexibleSearchQuery.addQueryParameter("listSplRequirement", splRequirementListId);
		SearchResult<SagiaLicenseTypeRequirementModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
		return searchResult.getResult();
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
