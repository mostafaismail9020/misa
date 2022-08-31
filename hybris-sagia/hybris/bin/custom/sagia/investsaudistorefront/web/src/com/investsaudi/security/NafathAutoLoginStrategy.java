package com.investsaudi.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Authentication strategy for Oauth users.
 */
public interface NafathAutoLoginStrategy
{
	void login(String username, HttpServletRequest request, HttpServletResponse response);
}
