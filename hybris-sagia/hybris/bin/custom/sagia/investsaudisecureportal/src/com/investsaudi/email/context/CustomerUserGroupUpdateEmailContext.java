package com.investsaudi.email.context;

import com.investsaudi.model.CustomerUserGroupUpdateEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

public class CustomerUserGroupUpdateEmailContext extends AbstractEmailContext<CustomerUserGroupUpdateEmailProcessModel>
{
	private String userGroup;

	@Override
	public void init(final CustomerUserGroupUpdateEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(businessProcessModel, emailPageModel);
		CustomerModel customerModel = getCustomer(businessProcessModel);

		put("email", customerModel.getUserNameEmail());
		if (businessProcessModel instanceof CustomerUserGroupUpdateEmailProcessModel)
		{
			final CustomerUserGroupUpdateEmailProcessModel processModel = (CustomerUserGroupUpdateEmailProcessModel) businessProcessModel;
			this.userGroup = ((CustomerUserGroupUpdateEmailProcessModel)processModel).getUserGroup();
		}
	}

	@Override
	protected BaseSiteModel getSite(CustomerUserGroupUpdateEmailProcessModel businessProcessModel) {
		return businessProcessModel.getSite();
	}

	@Override
	protected CustomerModel getCustomer(CustomerUserGroupUpdateEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCustomer();
	}

	@Override
	protected LanguageModel getEmailLanguage(CustomerUserGroupUpdateEmailProcessModel businessProcessModel) {
		return businessProcessModel.getLanguage();
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

}