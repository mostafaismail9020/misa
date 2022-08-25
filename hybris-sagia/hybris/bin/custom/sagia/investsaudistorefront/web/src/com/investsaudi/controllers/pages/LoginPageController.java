/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.controllers.pages;

import com.sap.ibso.eservices.core.enums.NafathStatus;
import com.sap.ibso.eservices.core.jalo.SagiaLicense;
import com.sap.ibso.eservices.facades.data.NafathLoginData;
import com.sap.ibso.eservices.facades.sagia.NafathFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import com.investsaudi.controllers.ControllerConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.hybris.platform.cmsfacades.data.UserData;
import org.apache.commons.lang.StringUtils;
import org.apache.regexp.RE;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginPageController extends AbstractLoginPageController
{
	private HttpSessionRequestCache httpSessionRequestCache;

	private static final String HYBRIS_USERNAME = "hybrisusername";
	private static final String HYBRIS_PASS = "hybrispassword";


	@Resource
	private AutoLoginStrategy autoLoginStrategy;

	@Resource
	private NafathFacade nafathFacade;

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/";
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}


	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
	{
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
			@RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
		if (!loginError)
		{
			storeReferer(referer, request, response);
		}
		return getDefaultLoginPage(loginError, session, model);
	}

	protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
	{
		if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
				&& StringUtils.contains(referer, request.getServerName()))
		{
			httpSessionRequestCache.saveRequest(request, response);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@RequestHeader(value = "referer", required = false) final String referer, final RegisterForm form,
			final BindingResult bindingResult, final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getRegistrationValidator().validate(form, bindingResult);
		return processRegisterUserRequest(referer, form, bindingResult, model, request, response, redirectModel);
	}

	@RequestMapping(value = "/register/termsandconditions")
	public String getTermsAndConditions(final Model model) throws CMSItemNotFoundException
	{
		final ContentPageModel pageForRequest = getCmsPageService().getPageForLabel("/termsAndConditions");
		storeCmsPageInModel(model, pageForRequest);
		setUpMetaDataForContentPage(model, pageForRequest);
		return ControllerConstants.Views.Fragments.Checkout.TermsAndConditionsPopup;
	}

	@RequestMapping(value = "/nafathLogin", method = RequestMethod.GET)
	public String loginWithNafath(@RequestParam(value = "nationalID") final String nationalID, final Model model,
								  final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) throws CMSItemNotFoundException {
		getSessionService().setAttribute("nationalID", nationalID);
		NafathLoginData loginData = nafathFacade.login(nationalID);
		getSessionService().setAttribute("transactionID", loginData.getTransactionId());
		getSessionService().setAttribute("randomNafathText", loginData.getRandom());
		if(loginData.getStatus().equals(NafathStatus.REJECTED) || loginData.getStatus().equals(NafathStatus.EXPIRED)){
			return getDefaultLoginPage(true, session, model);
		}
		return "LoginPageUI";
	}

	@RequestMapping(value = "/checkNafathLoginStatus", method = RequestMethod.GET)
	public String loginStatusCheck(final Model model,
								   final HttpServletRequest request, final HttpServletResponse response, final HttpSession session){
		String transactionID = getSessionService().getAttribute("transactionID");
		NafathLoginData loginStatus = nafathFacade.checkStatus(transactionID);
		return loginStatus.getStatus().toString();
	}

	@RequestMapping(value = "/nafathLicenses", method = RequestMethod.GET)
	public String displayLicenses(final Model model){
		String nationalId = getSessionService().getAttribute("nationalID");
		String transactionId = getSessionService().getAttribute("transactionID");
		String randomNafathText = getSessionService().getAttribute("randomNafathText");
		NafathLoginData loginStatus = nafathFacade.checkStatus(transactionId);
		if(loginStatus.getStatus().equals(NafathStatus.COMPLETED)){
			//TODO: call CRM API here
			List<SagiaLicense> response = null;
			if(response.size() == 1){
				getSessionService().setAttribute("licenseList",response);
				return REDIRECT_PREFIX+"/loginNafathUser";
			}else{
				getSessionService().setAttribute("licenseList",response);
				//TODO : call login selection page here
				return "/loginSelectionUI";
			}
		}
		//TODO: this case outcome is yet to be decided
		return "/defaultLoginpage";
	}

	@RequestMapping(value = "/loginNafathUser", method = RequestMethod.GET)
	public String loginNafathUser(String uiSelectionForm, final Model mode,final HttpSession session, final HttpServletRequest request, final HttpServletResponse response){
		//TODO create a UI selectin form to get the license from that form if it's not more than one in the session list
		List<String> licenseIds = getSessionService().getAttribute("licenseList");
		try {
			UserData user = nafathFacade.getuserForLicense(licenseIds.get(0));
		}catch (RuntimeException e){
			return REDIRECT_PREFIX+"/login";
		}
		autoLoginStrategy.login((String) session.getAttribute(HYBRIS_USERNAME),
						(String) session.getAttribute(HYBRIS_PASS), request, response);
		return "redirect:/";
	}
}
