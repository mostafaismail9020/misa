/**
 *
 */
package com.sap.ibso.eservices.sagiaservices.core.v2.controller;


import de.hybris.platform.commercefacades.user.data.CustomerData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sap.ibso.eservices.core.sagia.dto.CustomerWsDTO;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;

import io.swagger.annotations.Api;



/**
 * @author gandrade
 *
 */
@Controller
@Api(tags = "CustomersControllerV2")
@RequestMapping(value = "/{baseSiteId}/customers")
public class CustomersController extends BaseController
{

	private static final Logger LOG = Logger.getLogger(ProductsController.class);

	private ResourceServerTokenServices tokenServices;

	private final TokenExtractor tokenExtractor = new BearerTokenExtractor();

	private static final String ANONYMOUS_USER = "anonymous";

	private AuthenticationManager authenticationManager;

	@Resource(name = "sagiaCustomerFacade")
	private SagiaCustomerFacade sagiaCustomerFacade;

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseBody
	public CustomerWsDTO getProfile(final HttpServletRequest request) throws Exception
	{
		String userId = null;
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		try
		{

			if (authentication == null)
			{
				userId = ANONYMOUS_USER;
			}
			else
			{

				final Object auth = authentication.getPrincipal();

				if (auth == null)
				{
					throw new InvalidTokenException("Invalid token: " + authentication.getPrincipal());
				}

				userId = auth.toString();
			}

		}
		catch (final Exception failed)
		{
			LOG.info("Authentication request failed: " + failed);
		}

		final CustomerData customer = sagiaCustomerFacade.getSagiaCustomer(userId);

		return getDataMapper().map(customer, CustomerWsDTO.class,
				"uid, name, profilePicture, dashboardMedia, qeemahEmail, qeemahEmailStatus, regEmailStatus,isOutstandingFee, customerId,title,contactEmail,company,mobileCountryCode,userNameEmail,mobileNumber,sector,country,applicantReferenceID,entityID,internetUserID,applicationServiceRequestID,regEmailStatus, mobileStatus, qeemahEmailStatus, department, otherUserEntity");


	}


}
