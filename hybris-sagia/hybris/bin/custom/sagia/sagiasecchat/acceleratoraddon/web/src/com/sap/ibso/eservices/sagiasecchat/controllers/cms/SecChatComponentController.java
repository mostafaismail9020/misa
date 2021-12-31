/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiasecchat.controllers.cms;

import static com.sap.ibso.eservices.sagiasecchat.controllers.SagiasecchatControllerConstants.Cms.SecChatComponent;

import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.storesession.StoreSessionFacade;
import de.hybris.platform.core.model.user.UserModel;
import com.sap.ibso.eservices.sagiasecchat.model.components.SecChatComponentModel;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.localization.Localization;
import com.sap.ibso.eservices.sagiasecchat.services.ChatQueueService;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("SecChatComponentController")
@RequestMapping(value = SecChatComponent)
public class SecChatComponentController extends AbstractCMSAddOnComponentController<SecChatComponentModel>
{
	public static final String SECADDON_CHAT_TITLE_TEXT = "sagiasecchat.chat.title.text";
	public static final String SECADDON_CHAT_TITLE_VIDEO = "sagiasecchat.chat.title.video";
	public static final String CHAT_ADDON_FRAGMENT_PATH = "addon:/sagiasecchat/cms/secchatwindow";
	public static final String PRIMARY_CONTACT = "primaryContact";

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "storeSessionFacade")
	private StoreSessionFacade storeSessionFacade;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "chatQueueService")
	private ChatQueueService chatQueueService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final SecChatComponentModel component)
	{
		final String customerName = customerFacade.getCurrentCustomer().getName();
		final String customerEmail = customerFacade.getCurrentCustomer().getEmail();

		final UserModel user = getUserService().getCurrentUser();

		if (getUserService().isAnonymousUser(user))
		{
			model.addAttribute("customerName", "");
			model.addAttribute("customerEmail", "");
			model.addAttribute("showLogin",true);

		}
		else
		{
			model.addAttribute("customerName", customerName);
			model.addAttribute("showLogin",component.getShowLogin());
			final ProfileCompanyRepresentativeData contactPerson = sessionService.getAttribute(PRIMARY_CONTACT);
			if(contactPerson != null && contactPerson.getEmail() != null) {
				final String email = contactPerson.getEmail();
				model.addAttribute("customerEmail", email);
			} else {
				model.addAttribute("customerEmail", customerEmail);
			}
		}

		model.addAttribute("chatQueue", chatQueueService.getQueueForIsoCode(storeSessionFacade.getCurrentLanguage().getIsocode()).getChatQueue());
		model.addAttribute("authenticationType",component.getAuthenticationType());
		model.addAttribute("chatEcfModulePath", component.getChatEcfModulePath());
		model.addAttribute("chatCctrUrl", component.getChatCctrUrl());
		model.addAttribute("chatBootstrapUrl", component.getChatBootstrapUrl());
		model.addAttribute("currentLanguage", chatQueueService.getQueueForIsoCode(storeSessionFacade.getCurrentLanguage().getIsocode()).getMappedIsoCode());
		model.addAttribute("textChatTitle", Localization.getLocalizedString(SECADDON_CHAT_TITLE_TEXT));
		model.addAttribute("videoChatTitle", Localization.getLocalizedString(SECADDON_CHAT_TITLE_VIDEO));
		model.addAttribute("videoChatEnabled", component.getVideoChatEnabled());
		model.addAttribute("position", chatQueueService.getQueueForIsoCode(storeSessionFacade.getCurrentLanguage().getIsocode()).getPosition());
	}

	/**
	 * Method for getting fragment's JSP renderer as response on GET or POSTs request
	 *
	 * @param model
	 * @param allRequestParams
	 *           all request parameters
	 * @return fragment with populated data and renderer
	 */
	@RequestMapping(value = "/chatFragment", method = { RequestMethod.POST, RequestMethod.GET })
	public String getChatFragment(final Model model, @RequestParam final Map<String, String> allRequestParams)
	{
		return CHAT_ADDON_FRAGMENT_PATH;
	}

}
