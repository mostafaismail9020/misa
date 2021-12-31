package com.sap.ibso.eservices.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

/**
 * The type User created event.
 */
public class UserCreatedEvent extends AbstractCommerceUserEvent<BaseSiteModel> {
    private String role;
    private String initialPassword;

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

    /**
     * Instantiates a new User created event.
     */
    public UserCreatedEvent() {
        super();
    }
}
