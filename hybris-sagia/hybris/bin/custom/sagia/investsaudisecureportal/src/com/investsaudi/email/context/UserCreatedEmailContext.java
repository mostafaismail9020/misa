package com.investsaudi.email.context;

import com.investsaudi.model.UserCreatedEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;

/**
 * The type User created email context.
 */
public class UserCreatedEmailContext extends AbstractEmailContext<UserCreatedEmailProcessModel> {
    private String role;
    private String initialPassword;

    @Override
    public void init(final UserCreatedEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        this.role = businessProcessModel.getRole();
        this.initialPassword = businessProcessModel.getInitialPassword();
        CustomerModel customerModel = getCustomer(businessProcessModel);
        put("email", customerModel.getUserNameEmail());
    }

    @Override
    protected BaseSiteModel getSite(UserCreatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(UserCreatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(UserCreatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets initial password.
     *
     * @return the initial password
     */
    public String getInitialPassword() {
        return initialPassword;
    }

    /**
     * Sets initial password.
     *
     * @param initialPassword the initial password
     */
    public void setInitialPassword(String initialPassword) {
        this.initialPassword = initialPassword;
    }
}
