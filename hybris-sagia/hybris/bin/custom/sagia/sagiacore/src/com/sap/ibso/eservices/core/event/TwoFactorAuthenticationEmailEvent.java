package com.sap.ibso.eservices.core.event;

import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;

public class TwoFactorAuthenticationEmailEvent extends AbstractCommerceUserEvent<BaseSiteModel> {
	
	private String token;
	private String type;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Default constructor
	 */
	public TwoFactorAuthenticationEmailEvent()
	{
		super();
	}

	/**
	 * Parameterized Constructor
	 * 
	 * @param token
	 */
	public TwoFactorAuthenticationEmailEvent(final String token)
	{
		super();
		this.token = token;
	}
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param token
	 */
	public TwoFactorAuthenticationEmailEvent(final String token, final String type)
	{
		super();
		this.token = token;
		this.type = type;
	}

	/**
	 * @return the token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * @param token
	 *           the token to set
	 */
	public void setToken(final String token)
	{
		this.token = token;
	}
}
