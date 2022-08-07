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
package com.investsaudi.facades.impl;

import com.investsaudi.constants.InvestsaudisecureportalConstants;
import com.investsaudi.data.B2BRegistrationData;
import com.investsaudi.data.SagiaB2BUnitData;
import com.investsaudi.data.SagiaUserData;
import com.investsaudi.exceptions.CustomerAlreadyExistsException;
import com.investsaudi.exceptions.PhoneNumberUsedException;
import com.investsaudi.facades.B2BRegistrationFacade;
import com.investsaudi.facades.B2BRegistrationWorkflowFacade;
import com.investsaudi.model.B2BRegistrationModel;
import com.investsaudi.services.B2BRegistrationService;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaCountryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaNotificationService;
import com.sap.ibso.eservices.core.sagia.services.SagiaSectorService;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.B2BUnitService;
import de.hybris.platform.catalog.model.CompanyModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.util.Config;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Default implementation of {@link B2BRegistrationFacade}
 */
public class DefaultB2BRegistrationFacade implements B2BRegistrationFacade
{

	private static final Logger LOG = Logger.getLogger(DefaultB2BRegistrationFacade.class);
	private static final String COUNTRY_CODE = "+966";
	private static final String DEFAULT_COMAPANY_NAME = "BSU";
	private static final String DEFAULT_COMAPANY_ADDRESS = "BSU KSA";
	private static final String DEFAULT_COUNTRY_CODE = "SA";
	private static final String DEFAULT_TITLE_CODE = "mr";
	private static final String USER_GROUPS_KEY = "user.group.for.new.user";


	private CMSSiteService cmsSiteService;

	private CommonI18NService commonI18NService;

	private ModelService modelService;

	private BaseStoreService baseStoreService;

	private SagiaUserService userService;

	private B2BRegistrationWorkflowFacade b2bRegistrationWorkflowFacade;

	private WorkflowTemplateService workflowTemplateService;

	private SagiaCountryService sagiaCountryService;

	private SagiaSectorService sagiaSectorService;

	@Resource
	private B2BUnitService b2bUnitService ;
	@Resource
	private B2BRegistrationService b2bRegistrationService;
	@Resource
	private EventService eventService;
	@Resource
	private ConfigurationService configurationService;
	@Resource
	private SagiaNotificationService sagiaNotificationService;




	/**
	 * @param sagiaSectorService the sagiaSectorService to set
	 */
	@Required
	public void setSagiaSectorService(SagiaSectorService sagiaSectorService)
	{
		this.sagiaSectorService = sagiaSectorService;
	}

	/**
	 * @param sagiaCountryService the sagiaCountryService to set
	 */
	@Required
	public void setSagiaCountryService(SagiaCountryService sagiaCountryService)
	{
		this.sagiaCountryService = sagiaCountryService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}



	/**
	 * @param b2bRegistrationWorkflowFacade
	 *           the b2bRegistrationWorkflowFacade to set
	 */
	@Required
	public void setB2bRegistrationWorkflowFacade(final B2BRegistrationWorkflowFacade b2bRegistrationWorkflowFacade)
	{
		this.b2bRegistrationWorkflowFacade = b2bRegistrationWorkflowFacade;
	}

	/**
	 * @param workflowTemplateService
	 *           the workflowTemplateService to set
	 */
	@Required
	public void setWorkflowTemplateService(final WorkflowTemplateService workflowTemplateService)
	{
		this.workflowTemplateService = workflowTemplateService;
	}



	public void setUserService(SagiaUserService userService) {
		this.userService = userService;
	}

	/**
     * validates UniqueValue
     * @param userName          userName
     * @param email             email
     * @param mobileNumber      mobileNumber
     * @param mobileCountryCode mobileCountryCode
     * @return boolean
     */
	@Override
    public boolean validateUniqueValue(final String userName, final String email, final String mobileNumber, final String mobileCountryCode) {
        return userService.validateUniqueness(userName, email, mobileNumber, mobileCountryCode);
    }

	/*
	 * (non-Javadoc)
	 *
	 * @see com.investsaudi.facades.B2BRegistrationFacade#register(com.investsaudi .data .B2BRegistrationData)
	 */
	@Override
	public void register(final B2BRegistrationData data) throws CustomerAlreadyExistsException,PhoneNumberUsedException
	{

		final Transaction tx = Transaction.current();
		tx.begin();

		boolean success = false;

		try
		{
			LOG.debug(String.format("Data has user with uid '%s'", data.getEmail()));
			data.setEmail(data.getEmail().toLowerCase());
			LOG.debug(String.format("Process data with user with uid '%s'", data.getEmail()));

			boolean userExists = userService.validateUniqueness("", data.getEmail(), "", "");
			// Check if a user using the same email exist, if so we need to abort the current operation!
			//final boolean userExists = userService.isUserExisting(data.getEmail());
			if (!userExists)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug(String.format("user with uid '%s' already exists!", data.getEmail()));
				}
				throw new CustomerAlreadyExistsException(String.format("User with uid '%s' already exists!", data.getEmail()));
			}

			boolean userMobileExists = userService.validateUniqueness("", "", data.getTelephone(), data.getTelephoneExtension());
			  // Check if a user using the same email exist, if so we need to abort the
			//if (userService.getCustomerByMobileNumber(data.getTelephone(), data.getTelephoneExtension()) != null ) {
			if (!userMobileExists) {
				if (LOG.isDebugEnabled())
				{
					LOG.debug(String.format("user with phone number '%s' already exists!", data.getTelephone()));
				}
				throw new PhoneNumberUsedException(String.format("User with phone number '%s' already exists!", data.getTelephone()));
			}

			// Save the registration model so that it is accessible to the workflow actions. The registration model will be deleted as part of the cleanup
			// of the workflow.
			final B2BRegistrationModel registration = toRegistrationModel(data);
			modelService.save(registration);

			// Save the customer. At this point, the customer is saved to generate emails and initiate the workflow. This customer will be deleted as part of the
			// cleanup of the workflow IF he is rejected. If approved, the customer will be deleted by the "approve workflow action" and will be re-created
			// as a B2BCustomer and assigned to the proper B2BUnit. At this stage, we can't create a B2BCustomer since we don't even have a B2BUnit (organization!).
			final CustomerModel customer = toCustomerModel(data);
			modelService.save(customer);

			final WorkflowTemplateModel workflowTemplate = workflowTemplateService
					.getWorkflowTemplateForCode(InvestsaudisecureportalConstants.Workflows.REGISTRATION_WORKFLOW);

			if (LOG.isDebugEnabled())
			{
				LOG.debug(String.format("Created WorkflowTemplateModell using name '%s'",
						InvestsaudisecureportalConstants.Workflows.REGISTRATION_WORKFLOW));
			}

			// Start the workflow
			b2bRegistrationWorkflowFacade.launchWorkflow(workflowTemplate, registration);

			tx.commit();
			success = true;

		}
		finally
		{
			if (!success)
			{
				tx.rollback();
			}
		}

	}

	@Override
	public void createChildUnit(SagiaB2BUnitData data){
			 b2bRegistrationService.createChildUnit(data);
	}

	@Override
	public B2BCustomerModel createUser(SagiaUserData data) throws PhoneNumberUsedException, CustomerAlreadyExistsException {
		B2BCustomerModel customerModel = null;
		try {
			String initialPassword = generateRandomPassword();
			data.setInitialPassword(initialPassword);
			customerModel = b2bRegistrationService.createUser(data);
			sagiaNotificationService.sendUserCreatedNotification(customerModel, data.getRole(), initialPassword);
		} catch (ModelSavingException msexp) {
			String exceptionMessage = msexp.getLocalizedMessage();
			if (exceptionMessage.contains("DuplicateKeyException") && exceptionMessage.contains("mobileNumberIndex")) {
				throw new PhoneNumberUsedException(msexp.getMessage());
			} else if (exceptionMessage.contains("duplicate user for uid") || exceptionMessage.contains("ambiguous unique keys") ||exceptionMessage.contains("AmbiguousUniqueKeysException") ) {
				throw new CustomerAlreadyExistsException(msexp.getMessage());
			}
			throw msexp;
		}
		return customerModel;
	}

	private String generateRandomPassword() {
		return RandomStringUtils.randomAlphanumeric(Config.getInt("default.password.length", 8));
	}

	@Override
	public List<SagiaB2BUnitData> getChildUnitsForCurrentUser(){
		List<SagiaB2BUnitData> childUnits= null;
		UserModel userModel = userService.getCurrentUser();
		if(userModel instanceof B2BCustomerModel) {
			childUnits = b2bRegistrationService.getChildUnits(((B2BCustomerModel) userModel).getDefaultB2BUnit());
		}
		return childUnits;
	}

	@Override
	public SagiaB2BUnitData getB2BUnitforCrurrentUser(){
		return b2bRegistrationService.getB2BUnitforCrurrentUser();
	}

	@Override
	public List<String> getUserGroupForNewUser(){
		final String ugForNewUser = configurationService.getConfiguration()
				.getString(USER_GROUPS_KEY, "B2BCustomer");
		String userGroups[] = ugForNewUser.split(",");
		return Arrays.asList(userGroups);
	}

	/**
	 * Converts a {@link B2BRegistrationData} into a {@link CustomerModel}. Only keeps the most important fields to
	 * generate emails, the rest is ignored as this customer is to be deleted as part of the workflow execution
	 *
	 * @param data
	 *           The registration data
	 * @return An unsaved instance of {@link CustomerModel}
	 */
	protected CustomerModel toCustomerModel(final B2BRegistrationData data)
	{

		final CustomerModel model = modelService.create(CustomerModel.class);

		model.setName(WordUtils.capitalizeFully(data.getName()));
		model.setUid(data.getEmail().toLowerCase());
		model.setLoginDisabled(true);

		final TitleModel title = userService.getTitleForCode(DEFAULT_TITLE_CODE);
		model.setTitle(title);

		final UserGroupModel investSaudiLobGroup = userService.getUserGroupForUID(data.getLob());
		// B2BUnit is mandatory
		final CompanyModel b2bUnit =  b2bUnitService.getUnitForUid(data.getUserEntity());
		Set<PrincipalGroupModel> groups = new HashSet<PrincipalGroupModel>();
		groups.add(b2bUnit);
		groups.add(investSaudiLobGroup);
		model.setGroups(groups);
		model.setOtherUserEntity(data.getOtherUserEntity());
		model.setDepartment(data.getDepartment());
		model.setMobileNumber(data.getTelephone());
		model.setMobileCountryCode(data.getTelephoneExtension());
		model.setCompany(DEFAULT_COMAPANY_NAME);
		model.setUserNameEmail(data.getEmail().toLowerCase());
		final SagiaCountryModel sagiaCountry = sagiaCountryService.getCountryForCode(DEFAULT_COUNTRY_CODE);
		model.setCountry(sagiaCountry);
		final SagiaSectorModel sagiaSector = sagiaSectorService.getSectorForCode("C");
		model.setSector(sagiaSector);

		return model;

	}

	/**
	 * Converts a {@link B2BRegistrationData} into a {@B2BRegistrationModel}
	 *
	 * @param data
	 *           The registration data
	 * @return An unsaved instance of type {@B2BRegistrationModel}
	 */
	protected B2BRegistrationModel toRegistrationModel(final B2BRegistrationData data)
	{

		final B2BRegistrationModel model = modelService.create(B2BRegistrationModel.class);

		// Use reflection to copy most properties and ignore these since we want to manage them manually
		BeanUtils.copyProperties(data, model, new String[]
		{ "titleCode", "companyAddressCountryIso", "companyAddressRegion", "baseStore", "cmsSite", "currency", "language","userEntity" });

		// Title is mandatory
		final TitleModel title = userService.getTitleForCode(DEFAULT_TITLE_CODE);
		model.setTitle(title);

		// Country is mandatory
		//[companyAddressCity, companyAddressPostalCode, companyAddressStreet, companyName]

		  final CountryModel country =
		  commonI18NService.getCountry(DEFAULT_COUNTRY_CODE);
		  model.setCompanyAddressCountry(country);
		  model.setCompanyAddressCity(DEFAULT_COMAPANY_ADDRESS);
		  model.setCompanyAddressPostalCode(DEFAULT_COMAPANY_ADDRESS);
		  model.setCompanyAddressStreet(DEFAULT_COMAPANY_ADDRESS);
		  model.setCompanyName(DEFAULT_COMAPANY_NAME);

		// B2BUnit is mandatory
		final B2BUnitModel b2bUnit =  (B2BUnitModel) b2bUnitService.getUnitForUid(data.getUserEntity());
	    model.setDefaultB2BUnit(b2bUnit);
		// Region is optional

		  if (StringUtils.isNotBlank(data.getCompanyAddressRegion())) {

			  final RegionModel region = commonI18NService.getRegion(country,
		      data.getCompanyAddressRegion()); model.setCompanyAddressRegion(region);

		  }


		// Get these from current context

		model.setBaseStore(baseStoreService.getCurrentBaseStore());
		model.setCmsSite(cmsSiteService.getCurrentSite());
		model.setCurrency(commonI18NService.getCurrentCurrency());
		model.setLanguage(commonI18NService.getCurrentLanguage());

		return model;

	}

}