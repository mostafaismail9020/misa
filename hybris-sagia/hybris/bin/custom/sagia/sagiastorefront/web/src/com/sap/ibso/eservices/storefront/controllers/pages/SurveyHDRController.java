package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SurveyData;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/questionnaires")

public class SurveyHDRController extends AbstractPageController {
    private static final Logger LOG = Logger.getLogger(SurveyHDRController.class);
    private static final String QUESTIONNAIRES_CMS_PAGE = "questionnaires";

    @Resource(name = "sagiaAccountFacade")
    private SagiaAccountFacade sagiaAccountFacade;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getSurveyHDRById(@PathVariable("id") String id, HttpServletRequest request, Model model) throws CMSItemNotFoundException {
        try {
            if ("''".equals(id)) {
                id = "";
            }
            SurveyData surveyHDRData = sagiaAccountFacade.getSurveyHDRById(id);
            model.addAttribute("surveyData", surveyHDRData);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        storeCmsPageInModel(model, getContentPageForLabelOrId(QUESTIONNAIRES_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(QUESTIONNAIRES_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);

    }
}



