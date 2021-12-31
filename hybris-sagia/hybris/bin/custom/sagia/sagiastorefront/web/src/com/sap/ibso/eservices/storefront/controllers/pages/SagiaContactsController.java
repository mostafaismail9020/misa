/**
 * ***********************************************************************
 * Copyright (c) 2017, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * Moscow, Russian Federation
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateForm;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateHistory;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */

@Controller
@RequestMapping("/contacts")
public class SagiaContactsController extends SagiaAbstractPageController {

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    private static final String CONTACT_UPDATE_HISTORY_CMS_PAGE = "contact-update-history";

    @RequestMapping(path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequireHardLogIn
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateContacts(@RequestBody ContactUpdateForm contactUpdateForm) {
        sagiaAccountFacade.updateContacts(contactUpdateForm);
    }

    @RequestMapping(path = "/check", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void checkContactUpdateAvailability() {
        sagiaAccountFacade.checkContactUpdateAvailability();
    }

    @RequestMapping(path= {"/history","/history/display/{srId}"}, method = RequestMethod.GET)
    @RequireHardLogIn
    public String getHistory(final Model model, @PathVariable(name = "srId", required=false) String srId) throws CMSItemNotFoundException {
        Collection<ContactUpdateHistory> contactUpdateHistory = sagiaAccountFacade.getAllContactUpdateHistory();

        if(CollectionUtils.isNotEmpty(contactUpdateHistory)){
            ContactUpdateHistory entry;
            if(Strings.isNotEmpty(srId)){
                Optional<ContactUpdateHistory> selectedContactHistory = contactUpdateHistory.stream().filter(historyEntry -> srId.equals(historyEntry.getSrId())).findFirst();
                if(selectedContactHistory.isPresent()){
                    entry = sagiaAccountFacade.getContactUpdateHistoryEntry(srId);
                    model.addAttribute("fromServiceRequestOverview",true);
                }
                else{
                    entry = sagiaAccountFacade.getContactUpdateHistoryEntry(contactUpdateHistory.stream().findFirst().get().getSrId());
                }
            }
            else{
                entry = sagiaAccountFacade.getContactUpdateHistoryEntry(contactUpdateHistory.stream().findFirst().get().getSrId());
            }
            model.addAttribute("first", entry);
        }

        model.addAttribute("contactUpdateHistory", contactUpdateHistory);
        storeCmsPageInModel(model, getContentPageForLabelOrId(CONTACT_UPDATE_HISTORY_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(CONTACT_UPDATE_HISTORY_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Ajax request to get a specific financial data. The request is made when the entity is changed
     * from the history list in the page.
     *
     * @param id - The id of the financial entity
     * @return - The financial entity with respective id in JSON format
     */
    @RequestMapping(path = "/history/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequireHardLogIn
    public String getContactUpdateHistoryEntry(@PathVariable String id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        return gson.toJson(sagiaAccountFacade.getContactUpdateHistoryEntry(id));
    }

    public SagiaAccountFacade getSagiaAccountFacade() {
        return sagiaAccountFacade;
    }
}
