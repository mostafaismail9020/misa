package com.investsaudi.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/cms")
//@RequireHardLogIn //should NOT require
public class InvestSaudiCMSController extends AbstractPageController
{
    //for help /cms/sagia-cms-help-and-informations

    @RequestMapping(method = RequestMethod.GET, path = "/{pageId}")
    public String getPage(@PathVariable("pageId") String pageId, final Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(pageId));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(pageId));
        return getViewForPage(model);
    }
}