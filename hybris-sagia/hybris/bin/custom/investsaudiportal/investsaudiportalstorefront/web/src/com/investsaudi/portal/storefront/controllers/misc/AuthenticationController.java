/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.misc;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.investsaudi.portal.storefront.security.evaluator.impl.RequireHardLoginEvaluator;


/**
 * Controller for checking user's authentication status
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController
{
	public static final String AUTHENTICATED = "authenticated";

	@Resource(name = "requireHardLoginEvaluator")
	private RequireHardLoginEvaluator requireHardLoginEvaluator;

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ResponseEntity<String> status(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (!requireHardLoginEvaluator.evaluate(request, response))
		{
			return new ResponseEntity<String>(AUTHENTICATED, HttpStatus.OK);
		}

		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}
}
