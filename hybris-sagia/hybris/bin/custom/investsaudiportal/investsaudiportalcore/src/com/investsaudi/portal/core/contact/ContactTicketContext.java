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
package com.investsaudi.portal.core.contact;

import com.investsaudi.portal.core.model.ContactTicketModel;
import de.hybris.platform.ticket.email.context.AbstractTicketContext;
import de.hybris.platform.ticket.events.model.CsTicketEventModel;
import de.hybris.platform.ticket.model.CsTicketModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 *
 */
public class ContactTicketContext extends AbstractTicketContext {
	
	private static final FastDateFormat fdf = FastDateFormat.getInstance("MM/dd/YYYY HH:mm:ss");
	
    public ContactTicketContext(final CsTicketModel ticket, final CsTicketEventModel event) {
        super(ticket, event);
    }

    @Override
    public String getName() {
        return getContactTicketModel().getName();
    }

    @Override
    public String getTo() {
        return getContactTicketModel().getSendEmailTo();
    }
    
    public String getEmail() {
		return getContactTicketModel().getEmail();
	}

	public String getMobile() {
		return getContactTicketModel().getMobile();
	}

	public String getCompany() {
		return getContactTicketModel().getCompany();
	}

	public String getJobTitle() {
		return getContactTicketModel().getJobTitle();
	}

	public String getContactSubject() {
		return StringUtils.defaultIfBlank(getContactTicketModel().getContactSubject(), "General Enquiry");
	}

	public String getSector() {
		return getContactTicketModel().getSectorCategory();
	}

	public String getDate() {
		return fdf.format(getContactTicketModel().getCreationtime());
	}

    private ContactTicketModel getContactTicketModel() {
        CsTicketModel ticket = getTicket();
        if (ticket instanceof ContactTicketModel) {
            return (ContactTicketModel) ticket;
        }
        throw new UnsupportedOperationException("ticket is not of type ContactTicketModel, but of type" + ticket
            .getClass().getName());
    }
}
