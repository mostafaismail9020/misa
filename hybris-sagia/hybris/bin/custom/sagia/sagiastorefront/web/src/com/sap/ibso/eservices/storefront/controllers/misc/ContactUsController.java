/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.misc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.investsaudi.portal.facades.contact.ContactTicketFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.customerticketingfacades.data.ContactTicketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import de.hybris.platform.ticket.model.CsTicketModel;

@Controller
public class ContactUsController extends AbstractController {

    private final static Logger log = LoggerFactory.getLogger(ContactUsController.class);

    @Autowired
    private ContactTicketFacade contactTicketFacade;

    @RequestMapping(value = "/contactus", method = {RequestMethod.POST,RequestMethod.GET}, consumes = {"application/json"})
    public @ResponseBody String contactus(@RequestBody ContactTicketData ticket
                            ) {

        try {
            CsTicketModel contactTicket  = contactTicketFacade.saveTicket(ticket);
            return String.format("success_%s", contactTicket.getTicketID());
        } catch (Exception e) {
            log.error("Error generating contact ticket", e);
            return "error";
        }
    }
}
