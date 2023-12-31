package com.sap.ibso.eservices.storefront.security.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.sap.ibso.eservices.storefront.security.OAuthUserAutoLoginStrategy;

import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.commercefacades.customer.CustomerFacade;


public class DefaultOAuthUserAutoLoginStrategy implements OAuthUserAutoLoginStrategy
{
	
	private static final Logger LOG = Logger.getLogger(DefaultOAuthUserAutoLoginStrategy.class);

	@Resource(name="oauthUserAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomerFacade customerFacade;
	
	@Autowired
	private GUIDCookieStrategy guidCookieStrategy;
	
	@Autowired
	private RememberMeServices rememberMeServices;
	
	@Override
	public void login(final String username, final HttpServletRequest request, final HttpServletResponse response)
	{
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null);
		token.setDetails(new WebAuthenticationDetails(request));
		try
		{
			final Authentication authentication = authenticationProvider.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			customerFacade.loginSuccess();
			guidCookieStrategy.setCookie(request, response);
			rememberMeServices.loginSuccess(request, response, token);
		}
		catch (Exception exc)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Failure during autoLogin", exc);
		}
	}

}
