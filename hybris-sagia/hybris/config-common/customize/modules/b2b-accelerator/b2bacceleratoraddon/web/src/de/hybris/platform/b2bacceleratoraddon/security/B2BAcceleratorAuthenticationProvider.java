/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bacceleratoraddon.security;

import de.hybris.platform.acceleratorstorefrontcommons.security.AbstractAcceleratorAuthenticationProvider;
import de.hybris.platform.b2b.constants.B2BConstants;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.commons.lang3.BooleanUtils;
import de.hybris.platform.b2bacceleratoraddon.security.InvestSaudiAuthenticationException;



/**
 * Derived authentication provider supporting additional authentication checks. See
 * {@link de.hybris.platform.spring.security.RejectUserPreAuthenticationChecks}.
 *
 * <ul>
 * <li>prevent login without password for users created via CSCockpit</li>
 * <li>prevent login as user in group admingroup</li>
 * <li>prevent login as user if not authorised for B2B</li>
 * </ul>
 *
 * any login as admin disables SearchRestrictions and therefore no page can be viewed correctly
 */
public class B2BAcceleratorAuthenticationProvider extends AbstractAcceleratorAuthenticationProvider
{

	private B2BUserGroupProvider b2bUserGroupProvider;
	
	private static final String J_SPRING_SECURITY_CHECK = "j_spring_security_check";

	private static final String RECAPTCHA_CHALLANGE_ANSWERED = "recaptchaChallangeAnswered";

	/**
	 * @see de.hybris.platform.acceleratorstorefrontcommons.security.AbstractAcceleratorAuthenticationProvider#additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails,
	 *      org.springframework.security.authentication.AbstractAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(final UserDetails details, final AbstractAuthenticationToken authentication)
			throws AuthenticationException
	{
		super.additionalAuthenticationChecks(details, authentication);

		final UserModel userModel = getUserService().getUserForUID(StringUtils.lowerCase(details.getUsername()));
		final UserGroupModel b2bgroup = getUserService().getUserGroupForUID(B2BConstants.B2BGROUP);

		// check if the customer is B2B type
		if (!getUserService().isMemberOfGroup(userModel, b2bgroup))
		{
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
		}

		// check if the customer is authorized
		if (!getB2bUserGroupProvider().isUserAuthorized(details.getUsername()))
		{
			throw new InsufficientAuthenticationException(
					messages.getMessage("checkout.error.invalid.accountType", "You are not allowed to login"));
		}

		// check if the customer account is active
		if (!getB2bUserGroupProvider().isUserEnabled(details.getUsername()))
		{
			throw new DisabledException(
					"User " + details.getUsername() + " is disabled... " + messages.getMessage("text.company.manage.units.disabled"));
		}
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		this.handleInvalidCaptcha();
		return super.authenticate(authentication);
	}

	protected B2BUserGroupProvider getB2bUserGroupProvider()
	{
		return b2bUserGroupProvider;
	}

	public void setB2bUserGroupProvider(final B2BUserGroupProvider b2bUserGroupProvider)
	{
		this.b2bUserGroupProvider = b2bUserGroupProvider;
	}
	
	private void handleInvalidCaptcha() throws InvestSaudiAuthenticationException {
		if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes)
		{
			final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			
			//This check for J_SPRING_SECURITY_CHECK is added, so that the captcha is checked only for login and not for 2-factor authentication
			if(request.getServletPath().contains(J_SPRING_SECURITY_CHECK) && request.getAttribute(RECAPTCHA_CHALLANGE_ANSWERED) != null && BooleanUtils.isFalse((Boolean)request.getAttribute(RECAPTCHA_CHALLANGE_ANSWERED))) {
				
	            throw new InvestSaudiAuthenticationException("Invalid captcha, Please Try Again"); // technical issue occurred

			}
		}
	}
}
