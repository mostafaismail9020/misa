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

import com.investsaudi.model.B2BRegistrationModel;
import com.investsaudi.model.scpi.outbound.process.ScpiOutB2BCustomerProcessModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * Action called when a registration request has been approved
 */
public class RegistrationApprovedAutomatedWorkflowTemplateJob extends AbstractAutomatedWorkflowTemplateJob
{

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(RegistrationApprovedAutomatedWorkflowTemplateJob.class);
	private static final String COUNTRY_CODE = "+966";


	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "businessProcessService")
	private BusinessProcessService businessProcessService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob#perform(de.hybris.platform.workflow.model.
	 * WorkflowActionModel)
	 */
	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel workflowAction)
	{

		final B2BRegistrationModel registration = getRegistrationAttachment(workflowAction);
		final CustomerModel customer = getCustomer(registration);

		final B2BCustomerModel b2BCustomer = createB2BCustomerModel(customer, registration);

		//Delete temporary customer attached to workflow
		getModelService().remove(customer);

		//persist the newly created b2bCustomer
		getModelService().save(b2BCustomer);

		if(Config.getBoolean("b2bcustomer.scpi.interface.enable",true)) {
			final ScpiOutB2BCustomerProcessModel scpiOutB2BCustomerProcessModel = (ScpiOutB2BCustomerProcessModel) getBusinessProcessService().createProcess(
					"scpiOutB2BCustomerProcess-" + b2BCustomer.getUid() + "-" + System.currentTimeMillis(),
					"scpiOutB2BCustomerProcess");
			scpiOutB2BCustomerProcessModel.setB2bCustomer(b2BCustomer);
			getModelService().save(scpiOutB2BCustomerProcessModel);
			getBusinessProcessService().startProcess(scpiOutB2BCustomerProcessModel);
		}
		return defaultDecision(workflowAction);

	}

	/**
	 * Creates an instance of {@link B2BCustomerModel} out of {@link CustomerModel}.
	 *
	 * @param customer
	 *           CustomerModel data
	 * @return An instance of {@link B2BCustomerModel}
	 */

	protected B2BCustomerModel createB2BCustomerModel(final CustomerModel customer, final B2BRegistrationModel registration)
	{

		final B2BCustomerModel b2bCustomer = getModelService().create(B2BCustomerModel.class);

		b2bCustomer.setEmail(customer.getUid());
		b2bCustomer.setName(customer.getName());
		b2bCustomer.setTitle(customer.getTitle());
		b2bCustomer.setUid(customer.getUid());

		b2bCustomer.setMobileNumber(customer.getMobileNumber());
		b2bCustomer.setMobileCountryCode(COUNTRY_CODE);
		b2bCustomer.setCompany(customer.getCompany());
		b2bCustomer.setUserNameEmail(customer.getUserNameEmail());
		b2bCustomer.setCountry(customer.getCountry());
		b2bCustomer.setSector(customer.getSector());
		b2bCustomer.setOtherUserEntity(customer.getOtherUserEntity());
	
		Set<PrincipalGroupModel> mergedSet = new HashSet<PrincipalGroupModel>(); 
		
        mergedSet.addAll(customer.getGroups()); 
        mergedSet.addAll(b2bCustomer.getGroups()); 
        
        b2bCustomer.setGroups(mergedSet);

		b2bCustomer.setDefaultB2BUnit(registration.getDefaultB2BUnit());

		return b2bCustomer;
	}

}