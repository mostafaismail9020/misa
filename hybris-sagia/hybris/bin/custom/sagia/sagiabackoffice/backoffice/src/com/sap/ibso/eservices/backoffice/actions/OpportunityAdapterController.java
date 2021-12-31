package com.sap.ibso.eservices.backoffice.actions;

import com.hybris.backoffice.navigation.NavigationNode;
import com.hybris.backoffice.widgets.advancedsearch.AbstractInitAdvancedSearchAdapter;
import com.hybris.backoffice.widgets.advancedsearch.impl.AdvancedSearchData;
import com.hybris.backoffice.widgets.advancedsearch.impl.SearchConditionData;
import com.hybris.cockpitng.core.config.impl.jaxb.hybris.advancedsearch.FieldType;
import com.hybris.cockpitng.core.user.CockpitUserService;
import com.hybris.cockpitng.search.data.ValueComparisonOperator;
import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.sagia.services.TicketConfigurationService;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OpportunityAdapterController extends AbstractInitAdvancedSearchAdapter {
	                                              
	private static final String CSTICKET_TYPE_CODE = "CsTicket";
	private static final String OPPORTUNITY_CONFIGURATION_CODE = "Opportunity";
	private static final String BACKOFFICE_OPPORTUNITIES_ID = "customersupport_backoffice_explorerTree_opportunities";

	@Resource
	private  CockpitUserService cockpitUserService;
	@Resource
	private UserService userService;
	@Resource
	private TicketConfigurationService ticketConfigurationService;
	

	@Override
	public void addSearchDataConditions(AdvancedSearchData paramAdvancedSearchData, Optional<NavigationNode> paramOptional) {
        addConditions(paramAdvancedSearchData);
	}
	
	protected void addConditions(final AdvancedSearchData searchData)
	{
		addUserConditions(searchData);
		addTypeConditions(searchData);

	}

	/**
	 * Method used for add filter conditions for the current type CsTicketModel
	 * in case its filterd by the configuration in this type: case Opportunity
	 * shows the data
	 * @param searchData
	 */
	private void addTypeConditions(AdvancedSearchData searchData) {

		final List<SearchConditionData> searchResultByConfiguration = new ArrayList<SearchConditionData>();

		// create status field type
		final FieldType configurationField = createFieldType(CsTicketModel.CONFIGURATION);

		// Filter Opportunity Only
		TicketConfigurationModel opportunityConfigCode = ticketConfigurationService.getTicketConfigurationByCode(OPPORTUNITY_CONFIGURATION_CODE);

		final SearchConditionData conditionConditionData = new SearchConditionData(configurationField, opportunityConfigCode, ValueComparisonOperator.IS_NOT_EMPTY);

		searchResultByConfiguration.add(conditionConditionData);

		searchData.addConditionList(ValueComparisonOperator.AND, searchResultByConfiguration);
	}

	/**
	 * Method used for add filter conditions for the user.
	 * It checks the usergroups in current user logged in backoffice
	 * and brings only tickets related to that UserGroup.
	 *
	 * @param searchData
	 */
	private void addUserConditions(AdvancedSearchData searchData) {

		clearConditionsForAttribute(searchData, CsTicketModel.ASSIGNEDGROUP);
		clearConditionsForAttribute(searchData, CsTicketModel.ASSIGNEDAGENT);

		final UserModel currentUser = getCurrentUser();

		final FieldType assignedGroup = createFieldType(CsTicketModel.ASSIGNEDGROUP);
		final FieldType assignedAgent = createFieldType(CsTicketModel.ASSIGNEDAGENT);

		final List<SearchConditionData> conditions = new ArrayList<>();
		final SearchConditionData currentUserCondition = new SearchConditionData(assignedAgent, currentUser,
				ValueComparisonOperator.EQUALS);

		conditions.add(currentUserCondition);

		final Set<UserGroupModel> groups = userService.getAllUserGroupsForUser(currentUser);
		for (final UserGroupModel currentGroup : groups)
		{
			final SearchConditionData userGroupCondition = new SearchConditionData(assignedGroup, currentGroup,
					ValueComparisonOperator.EQUALS);
			conditions.add(userGroupCondition);
		}
		searchData.addFilterQueryRawConditionsList(ValueComparisonOperator.OR, conditions);

	}

	protected void clearConditionsForAttribute(final AdvancedSearchData searchData, final String attribute)
	{
		final List<SearchConditionData> conditions = searchData.getConditions(attribute);
		if (CollectionUtils.isNotEmpty(conditions))
		{
			conditions.clear();
		}
	}
	
	protected FieldType createFieldType(final String fieldName)
	{
		final FieldType fieldType = new FieldType();
		fieldType.setDisabled(Boolean.TRUE);
		fieldType.setSelected(Boolean.TRUE);
		fieldType.setName(fieldName);
		return fieldType;
	}
	
	protected UserModel getCurrentUser()
	{
		UserModel userModel = null;
		final String userId = cockpitUserService.getCurrentUser();
		if (StringUtils.isNotBlank(userId))
		{
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
