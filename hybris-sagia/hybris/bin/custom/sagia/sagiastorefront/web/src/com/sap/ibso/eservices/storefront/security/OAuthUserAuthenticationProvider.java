package com.sap.ibso.eservices.storefront.security;

import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import de.hybris.platform.core.Constants;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.spring.security.CoreUserDetails;

public class OAuthUserAuthenticationProvider extends AcceleratorAuthenticationProvider {

	private static final Logger LOG = Logger.getLogger(OAuthUserAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
		bruteForceAttackCheck(username);
		return authenticateUser(authentication, username);
	}

	private Authentication authenticateUser(Authentication authentication, final String username) {
		if (Registry.hasCurrentTenant() && JaloConnection.getInstance().isSystemInitialized()) {
			UserDetails userDetails = null;

			try {
				userDetails = this.retrieveUser(username);
			} catch (UsernameNotFoundException var6) {
				throw new BadCredentialsException(
						this.messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"), var6);
			}

			this.getPreAuthenticationChecks().check(userDetails);
			User user = UserManager.getInstance().getUserByLogin(userDetails.getUsername());
			this.additionalAuthenticationChecks(userDetails, (AbstractAuthenticationToken) authentication);
			JaloSession.getCurrentSession().setUser(user);
			return this.createSuccessAuthentication(authentication, userDetails);
		} else {
			return this.createSuccessAuthentication(authentication, new CoreUserDetails("systemNotInitialized",
					"systemNotInitialized", true, false, true, true, Collections.EMPTY_LIST, (String) null));
		}
	}

	private void bruteForceAttackCheck(final String username) {
		final boolean isBruteForceAttack = getBruteForceAttackCounter().isAttack(username);
		UserModel userModel = null;

		// throw BadCredentialsException if user does not exist
		try
		{
			userModel = getUserService().getUserForUID(StringUtils.lowerCase(username));
		}
		catch (final UnknownIdentifierException e)
		{
			if (isBruteForceAttack)
			{
				LOG.warn("Brute force attack attempt for non existing user name " + username);
			}
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS), e);
		}

		// throw BadCredentialsException if it's brute force attack
		if (isBruteForceAttack)
		{
			userModel.setLoginDisabled(true);
			getModelService().save(userModel);
			getBruteForceAttackCounter().resetUserCounter(userModel.getUid());
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
		}

		// throw BadCredentialsException if the user does not belong to customer user group
		if (!getUserService().isMemberOfGroup(userModel, getUserService().getUserGroupForUID(Constants.USER.CUSTOMER_USERGROUP)))
		{
			throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
		}
	}

	@Override
	protected void additionalAuthenticationChecks(final UserDetails details, final AbstractAuthenticationToken authentication)
			throws AuthenticationException
	{
		// Check if the user is in role admingroup
		if (getAdminAuthority() != null && details.getAuthorities().contains(getAdminAuthority()))
		{
			throw new LockedException("Login attempt as " + Constants.USER.ADMIN_USERGROUP + " is rejected");
		}
	}
}
