package com.sap.ibso.eservices.storefront.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Authentication strategy for Oauth users.
 */
public interface OAuthUserAutoLoginStrategy
{
	void login(String username, HttpServletRequest request, HttpServletResponse response);
}
