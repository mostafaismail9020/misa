package com.sap.ibso.eservices.backoffice.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.hybris.backoffice.navigation.NavigationNode;
import com.hybris.backoffice.widgets.advancedsearch.AbstractInitAdvancedSearchAdapter;
import com.hybris.backoffice.widgets.advancedsearch.impl.AdvancedSearchData;
import com.hybris.backoffice.widgets.advancedsearch.impl.SearchConditionData;
import com.hybris.backoffice.widgets.advancedsearch.impl.SearchConditionDataList;
import com.hybris.cockpitng.search.data.ValueComparisonOperator;
import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.sagia.services.TicketConfigurationService;

import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.model.CsTicketModel;

import com.hybris.cockpitng.core.config.impl.jaxb.hybris.advancedsearch.FieldType;
import com.hybris.cockpitng.core.user.CockpitUserService;

public class ServicerequestAdapterController extends AbstractInitAdvancedSearchAdapter {

	private static final String CSTICKET_TYPE_CODE = "CsTicket";
	private static final String SERVICE_REQUEST_CONFIGURATION_CODE = "Service Request";
	private static final String BACKOFFICE_OPPORTUNITIES_ID = "customersupport_backoffice_explorerTree_servicerequests";

	private ArrayList<SearchConditionData> searchConditionData;

	@Resource
	private CockpitUserService cockpitUserService;
	@Resource
	private UserService userService;

	@Resource
	private TicketConfigurationService ticketConfigurationService;

	@Override
	public void addSearchDataConditions(AdvancedSearchData paramAdvancedSearchData,
			Optional<NavigationNode> paramOptional) {

		addUserCondition(paramAdvancedSearchData);

	}

	protected void addUserCondition(final AdvancedSearchData searchData) {
		clearConditionsForAttribute(searchData, CsTicketModel.ASSIGNEDGROUP);
		clearConditionsForAttribute(searchData, CsTicketModel.ASSIGNEDAGENT);

		final UserModel currentUser = getCurrentUser();

		final FieldType fieldType = createFieldType(CsTicketModel.ASSIGNEDGROUP);
		final FieldType fieldMyTicketType = createFieldType(CsTicketModel.ASSIGNEDAGENT);

		final List<SearchConditionData> conditions = new ArrayList<>();
		final SearchConditionData currentUserCondition = new SearchConditionData(fieldMyTicketType, currentUser,
				ValueComparisonOperator.EQUALS);

		conditions.add(currentUserCondition);

		final Set<UserGroupModel> groups = userService.getAllUserGroupsForUser(currentUser);
		for (final UserGroupModel currentGroup : groups) {
			final SearchConditionData userGroupCondition = new SearchConditionData(fieldType, currentGroup,
					ValueComparisonOperator.EQUALS);
			conditions.add(userGroupCondition);
		}
		searchData.addFilterQueryRawConditionsList(ValueComparisonOperator.OR, conditions);

		final List<SearchConditionData> searchResultByConfiguration = new ArrayList<SearchConditionData>();

		// create status field type
		final FieldType configurationTicketType = createFieldType(CsTicketModel.CONFIGURATION);

		// Filter Service Request Only
		TicketConfigurationModel opportunityconfiguration = ticketConfigurationService
				.getTicketConfigurationByCode(SERVICE_REQUEST_CONFIGURATION_CODE);

		final SearchConditionData conditionConditionData = new SearchConditionData(configurationTicketType,
				opportunityconfiguration, ValueComparisonOperator.IS_NOT_EMPTY);
		searchResultByConfiguration.add(conditionConditionData);

		searchData.addConditionList(ValueComparisonOperator.AND, searchResultByConfiguration);

	}

	protected void clearConditionsForAttribute(final AdvancedSearchData searchData, final String attribute) {
		final List<SearchConditionData> conditions = searchData.getConditions(attribute);
		if (CollectionUtils.isNotEmpty(conditions)) {
			conditions.clear();
		}
	}

	protected FieldType createFieldType(final String fieldName) {
		final FieldType fieldType = new FieldType();
		fieldType.setDisabled(Boolean.TRUE);
		fieldType.setSelected(Boolean.TRUE);
		fieldType.setName(fieldName);
		return fieldType;
	}

	protected UserModel getCurrentUser() {
		UserModel userModel = null;
		final String userId = cockpitUserService.getCurrentUser();
		if (StringUtils.isNotBlank(userId)) {
			userModel = userService.getUserForUID(userId);
		}
		return userModel;
	}

	@Override
	public String getNavigationNodeId() {
		return BACKOFFICE_OPPORTUNITIES_ID;
	}

	@Override
	public String getTypeCode() {
		return CSTICKET_TYPE_CODE;
	}

}
