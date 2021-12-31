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
package com.sap.ibso.eservices.sagiasecchat.controllers;

import com.sap.ibso.eservices.sagiasecchat.model.components.SecChatComponentModel;


/**
 */
public interface SagiasecchatControllerConstants
{
	interface Cms
	{
		String _Prefix = "/view/";
		String _Suffix = "Controller";

		String SecChatComponent = _Prefix + SecChatComponentModel._TYPECODE + _Suffix;
		String SecTextChatComponent = _Prefix +  _Suffix;
		String SecVideoChatComponent = _Prefix + _Suffix;

	}
	// implement here controller constants used by this extension
	String SUPPORT_TICKETS_PAGE = "support-tickets";
	String ADD_SUPPORT_TICKET_PAGE = "add-support-ticket";
	String UPDATE_SUPPORT_TICKET_PAGE = "update-support-ticket";
	String TEXT_SUPPORT_TICKETING_HISTORY = "text.account.supporttickets.history";
	String TEXT_SUPPORT_TICKETING_UPDATE = "text.account.supporttickets.updateSupportTicket";
	String TEXT_SUPPORT_TICKETING_ADD = "text.account.supporttickets.addSupportTicket";
	String SEC_BASE_URL = "sagiasecchat.sec.baseUrl";
	String TICKET_URL_SUFIX = "sagiasecchat.sec.serviceTicketUrl";
	String TICKET_TYPES_URL_SUFIX = "sagiasecchat.sec.serviceTicketTypesUrl";
	String SEC_SERVICE_TIMEOUT = "sagiasecchat.sec.timeout";
	String TICKETS_DESCRIPTION_LENGHT = "sagiasecchat.ticket.description.lenght";
}
