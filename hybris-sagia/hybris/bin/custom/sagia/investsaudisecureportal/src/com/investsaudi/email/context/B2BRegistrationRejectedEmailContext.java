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
package com.investsaudi.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import com.investsaudi.model.B2BRegistrationRejectedProcessModel;


/**
 * Email context used to render B2B reject emails with a "decline reason" message
 */
public class B2BRegistrationRejectedEmailContext extends B2BRegistrationEmailContext
{

	private String rejectReason;

	/**
	 * @return the rejectReason
	 */
	public String getRejectReason()
	{
		return rejectReason;
	}

	/**
	 * @param rejectReason
	 *           the rejectReason to set
	 */
	public void setRejectReason(final String rejectReason)
	{
		this.rejectReason = rejectReason;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext#init(de.hybris.platform.
	 * processengine.model.BusinessProcessModel, de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel)
	 */
	@Override
	public void init(final StoreFrontCustomerProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(businessProcessModel, emailPageModel);
		if (businessProcessModel instanceof B2BRegistrationRejectedProcessModel)
		{
			final B2BRegistrationRejectedProcessModel registrationProcessModel = (B2BRegistrationRejectedProcessModel) businessProcessModel;
			setRejectReason(registrationProcessModel.getRejectReason());
		}
	}

}