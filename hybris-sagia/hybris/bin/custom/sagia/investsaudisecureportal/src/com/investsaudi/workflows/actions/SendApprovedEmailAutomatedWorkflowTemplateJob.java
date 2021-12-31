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
package com.investsaudi.workflows.actions;

import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.commerceservices.security.SecureTokenService;
import de.hybris.platform.core.model.user.CustomerModel;
import com.investsaudi.model.B2BRegistrationApprovedProcessModel;
import com.investsaudi.model.B2BRegistrationModel;

import org.springframework.beans.factory.annotation.Required;


/**
 * Automated action responsible for sending an email by launching a process
 */
public class SendApprovedEmailAutomatedWorkflowTemplateJob extends
		SendEmailAutomatedWorkflowTemplateJob<B2BRegistrationApprovedProcessModel>
{

	private SecureTokenService secureTokenService;

	/**
	 * @param secureTokenService
	 *           the secureTokenService to set
	 */
	@Required
	public void setSecureTokenService(final SecureTokenService secureTokenService)
	{
		this.secureTokenService = secureTokenService;
	}

	/**
	 * @return the secureTokenService
	 */
	public SecureTokenService getSecureTokenService()
	{
		return secureTokenService;
	}

	@Override
	protected B2BRegistrationApprovedProcessModel createProcessModel(final CustomerModel customerModel,
			final B2BRegistrationModel registrationModel)
	{

		final B2BRegistrationApprovedProcessModel process = super.createProcessModel(customerModel, registrationModel);

		// We don't want this token to expire. When using the forget password screen, the user is expecting a 
		// response now which means that the 30 minutes default is acceptable. In the case of an approbation, it
		// could occur at any time and the user could look at his email much later on. So we pretend that the
		// email was created 1 year in the future
		final SecureToken token = new SecureToken(customerModel.getUid(), System.currentTimeMillis() + 31536000000L);
		final String encryptedToken = getSecureTokenService().encryptData(token);

		// Assign the token to the user
		customerModel.setToken(encryptedToken);
		getModelService().save(customerModel);

		process.setPasswordResetToken(encryptedToken);

		return process;

	}

}