package com.sap.ibso.eservices.storefront.security;

import com.sap.ibso.eservices.sagiaservices.auth.CredentialVerificationService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.commons.lang3.BooleanUtils;
import javax.servlet.http.HttpServletRequest;
import java.lang.Boolean;


import java.util.Locale;

/**
 * Supports user name and password verification against an external system for migrated E-Service Fiori application users
 * at (first) Hybris login.
 */
public class SagiaAuthenticationProvider extends AcceleratorAuthenticationProvider
{
    private static final String J_SPRING_SECURITY_CHECK = "j_spring_security_check";

	private static final String RECAPTCHA_CHALLANGE_ANSWERED = "recaptchaChallangeAnswered";

	private static final Logger LOGGER = LogManager.getLogger();

    private CredentialVerificationService credentialVerificationService;

    /**
     * For a user who is a customers and marked for password migration the provided user name and password are verified
     * against an external system. If the verification was successful the password is stored for the user in Hybris and
     * the user is unmarked for password migration. Thereafter Hybris authentication proceeds.
     *
     * @param authentication the authentication information
     * @return the verified authentication information
     * @throws AuthenticationException if authentication was not successful
     */
    /* Suppress Sonar warning: squid:S134 | control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply.
     * Reason: Code readability seems still to be manageable in the given context.
     */
    @SuppressWarnings({"squid:S134"})
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
    	handleInvalidCaptcha();

        LOGGER.error("Before login for user: " + authentication.getName());
        if (!(authentication instanceof UsernamePasswordAuthenticationToken) ||
                StringUtils.isEmpty(authentication.getName()) ||
                !(authentication.getCredentials() instanceof String))
        {
            // Let the super-class handle these cases
            return super.authenticate(authentication);
        }

        // Update authentication token by lowercasing the username
        authentication = new UsernamePasswordAuthenticationToken(authentication.getName().toLowerCase(Locale.ENGLISH), authentication.getCredentials());

        UserModel user;
        try
        {
            user = getUserService().getUserForUID(authentication.getName());
        }
        catch(Exception e)
        {
            // User cannot be retrieved
            LOGGER.error(e.getMessage(), e);
            // Let the super-class handle this case
            return super.authenticate(authentication);
        }

        boolean proceedAuthentication = false;
        try
        {
            if (user instanceof CustomerModel)
            {
                CustomerModel customer = (CustomerModel) user;

                // Check migration flag
                if (Boolean.TRUE.equals(customer.getPasswordMigration()))
                {
                    String password = (String) authentication.getCredentials();

                    proceedAuthentication = credentialVerificationService.isValid(authentication.getName(), password);

                    if (proceedAuthentication)
                    {
                        customer.setPassword(password);
                        customer.setPasswordMigration(false);
                        getModelService().save(customer);
                    }

                    // Password is migrated (at this point)
                }
                else
                {
                    // Password migration flag is not true
                    proceedAuthentication = true;
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            throw new SagiaAuthenticationException(messages.getMessage("login.error.technical.error", "Login is not possible due to technical error.")); // technical issue occurred
        }

        if (!proceedAuthentication)
        {
            throw new BadCredentialsException(messages.getMessage(CORE_AUTHENTICATION_PROVIDER_BAD_CREDENTIALS, BAD_CREDENTIALS));
        }

        // Proceed with login
        Authentication auth = super.authenticate(authentication);
        LOGGER.error("Successful login for user: " + authentication.getName());

        return auth;
    }

	private void handleInvalidCaptcha() throws SagiaAuthenticationException {
		if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes)
		{
			final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();

			//This check for J_SPRING_SECURITY_CHECK is added, so that the captcha is checked only for login and not for 2-factor authentication
			if(request.getServletPath().contains(J_SPRING_SECURITY_CHECK) && request.getAttribute(RECAPTCHA_CHALLANGE_ANSWERED) != null && BooleanUtils.isFalse((Boolean)request.getAttribute(RECAPTCHA_CHALLANGE_ANSWERED))) {
				LOGGER.error("handleInvalidCaptcha -> Invalid captcha. [{}] , [{}] ",request.getServletPath().contains(J_SPRING_SECURITY_CHECK),request.getAttribute(RECAPTCHA_CHALLANGE_ANSWERED));
	            throw new SagiaAuthenticationException("Invalid captcha, Please Try Again"); // technical issue occurred

			}
		}
	}

    /**
     * Set a credential verification service used for external authentication
     *
     * @param credentialVerificationService the credential verification service
     */
    @Required
    public void setCredentialVerificationService(CredentialVerificationService credentialVerificationService)
    {
        this.credentialVerificationService = credentialVerificationService;
    }
}
