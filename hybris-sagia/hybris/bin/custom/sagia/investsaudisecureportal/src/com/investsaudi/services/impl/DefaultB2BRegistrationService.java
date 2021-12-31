/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.services.impl;

import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.data.SagiaUserData;
import com.investsaudi.facades.impl.DefaultB2BRegistrationFacade;
import com.investsaudi.facades.populators.SagiaB2BUnitPopulator;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.catalog.model.CompanyModel;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.*;
import com.investsaudi.dao.B2BRegistrationDao;
import com.investsaudi.services.B2BRegistrationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.Config;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Lists;

import javax.annotation.Resource;


/**
 * Default implementation of {@link B2BRegistrationService}
 */
public class DefaultB2BRegistrationService implements B2BRegistrationService
{
	private static final Logger LOG = Logger.getLogger(DefaultB2BRegistrationFacade.class);
	public static final String DEFAULT_PASSWORD = "Newuser@1234";
	private B2BRegistrationDao registrationDao;

	private EmailService emailService;

	@Resource
	private ModelService modelService;
	@Resource
	private SagiaUserService userService;
	@Resource
	private B2BUnitService b2bUnitService ;

	@Resource
	private SagiaB2BUnitPopulator sagiaB2BUnitPopulator;
	@Resource
	private SearchRestrictionService searchRestrictionService;
	@Resource
	private SessionService sessionService;


	/**
	 * @param registrationDao
	 *           the registrationDao to set
	 */
	@Required
	public void setRegistrationDao(final B2BRegistrationDao registrationDao)
	{
		this.registrationDao = registrationDao;
	}

	/**
	 * @param emailService
	 *           the emailService to set
	 */
	@Required
	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.investsaudi.services.B2BRegistrationService#getEmployeesInUserGroup(java.lang.String)
	 */
	@Override
	public List<EmployeeModel> getEmployeesInUserGroup(final String userGroup)
	{
		return registrationDao.getEmployeesInUserGroup(userGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.investsaudi.services.B2BRegistrationService#getEmailAddressesOfEmployees(java.util.List)
	 */
	@Override
	public List<EmailAddressModel> getEmailAddressesOfEmployees(final List<EmployeeModel> employees)
	{

		final List<EmailAddressModel> emails = new ArrayList<>();

		for (final EmployeeModel employee : employees)
		{
			for (final AddressModel address : Lists.newArrayList(employee.getAddresses()))
			{
				if (BooleanUtils.isTrue(address.getContactAddress()))
				{
					if (StringUtils.isNotBlank(address.getEmail()))
					{
						final EmailAddressModel emailAddress = emailService.getOrCreateEmailAddressForEmail(address.getEmail(),
								employee.getName());
						emails.add(emailAddress);
					}
					break;
				}
			}
		}

		return emails;

	}

	@Override
	public void createChildUnit(SagiaB2BUnitData data)  {
		LOG.info(String.format("Trying to create new Child Unit with Id %s", data.getId()));
		boolean success = Boolean.FALSE;
		final B2BUnitModel model = modelService.create(B2BUnitModel.class);
		model.setUid(data.getId());
		model.setName(data.getName());
		model.setLocName(data.getName());
		UserModel currentUser  = userService.getCurrentUser();
		if(currentUser instanceof B2BCustomerModel){
			B2BUnitModel parentUnit = ((B2BCustomerModel)currentUser).getDefaultB2BUnit();
			b2bUnitService.addMember(parentUnit,model);
			modelService.save(model);
			modelService.refresh(parentUnit);
			LOG.info(String.format("New Child Unit with Id %s saved into database", data.getId()));
		}
	}

	@Override
	public List<SagiaB2BUnitData> getChildUnits(B2BUnitModel b2BUnitModel)  {
		SagiaB2BUnitData unitData = null;
		List<SagiaB2BUnitData> childUnits = new ArrayList<SagiaB2BUnitData>();
		Set<PrincipalModel> members = b2BUnitModel.getMembers();
		for(PrincipalModel member:members){
			if(member instanceof B2BUnitModel){
				unitData = new SagiaB2BUnitData();
				sagiaB2BUnitPopulator.populate(((B2BUnitModel)member), unitData);
				childUnits.add(unitData);
			}
		}
		return childUnits;
	}

	@Override
	public SagiaB2BUnitData getB2BUnitforCrurrentUser(){
		SagiaB2BUnitData unitData = null;
		UserModel userModel = userService.getCurrentUser();
		if(userModel instanceof B2BCustomerModel) {
			unitData = new SagiaB2BUnitData();
			B2BUnitModel unit  = ((B2BCustomerModel) userModel).getDefaultB2BUnit();
			sagiaB2BUnitPopulator.populate(unit, unitData);
		}
		return unitData;
	}

	@Override
	public B2BCustomerModel createUser(SagiaUserData data)  {
		LOG.info(String.format("Trying to create new user with email %s", data.getEmail()));
		boolean success = Boolean.FALSE;
		B2BCustomerModel currentUser  = (B2BCustomerModel) userService.getCurrentUser();
		B2BCustomerModel model = modelService.create(B2BCustomerModel.class);
		model.setUid(data.getEmail());
		model.setUserNameEmail(data.getEmail());
		model.setEmail(data.getEmail());
		final TitleModel title = userService.getTitleForCode(data.getTitle());
		model.setTitle(title);
		model.setName(data.getFirstName().concat(" ").concat(data.getLastName()));
		model.setMobileCountryCode(currentUser.getMobileCountryCode());
		model.setMobileNumber(data.getMobileNumber());
		model.setSector(currentUser.getSector());
		model.setCountry(currentUser.getCountry());
		model.setCompany(currentUser.getCompany());
		model.setSessionLanguage(currentUser.getSessionLanguage());
        model.setPassword(data.getInitialPassword());

        Set<PrincipalGroupModel> userGroups = new HashSet<PrincipalGroupModel>();
		UserGroupModel b2bCustomerGroup = userService.getUserGroupForUID("b2bcustomergroup");
		UserGroupModel userGroupModel = userService.getUserGroupForUID(data.getRole());
		userGroups.add(userGroupModel);
		userGroups.add(b2bCustomerGroup); // Standard B2bUG is required to login in site.

		model.setGroups(userGroups);
		CompanyModel b2bUnitForUser = null;
		if(StringUtils.equals(SagiaCoreConstants.WORKFLOW_ADMIN_ID,data.getRole())
				|| StringUtils.equals(SagiaCoreConstants.WORKFLOW_APPROVER_ID,data.getRole())){
			b2bUnitForUser = currentUser.getDefaultB2BUnit();
		}else{
			 b2bUnitForUser = getParentUnit(data.getParentUnit());
		}

		model.setDefaultB2BUnit((B2BUnitModel) b2bUnitForUser);
		b2bUnitService.addMember(b2bUnitForUser,model);
		modelService.save(model);
		LOG.info(String.format("New user with email %s saved into database", data.getEmail()));
		return model;
	}

	private CompanyModel getParentUnit(final String unitId)
	{
		return sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public CompanyModel execute()
			{
				searchRestrictionService.disableSearchRestrictions();
				CompanyModel b2bUnitForUser = b2bUnitService.getUnitForUid(unitId);
				searchRestrictionService.enableSearchRestrictions();
				return b2bUnitForUser;
			}
		});
	}
}
