package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/newseventslist")
public class SagiaNewsEventsPageController extends SagiaSearchPageController
{
    private static final String NEWS_EVENTS_LIST_PAGE = "newsevents-list-page";
    private static final Logger LOG = Logger.getLogger(SagiaNewsEventsPageController.class);

    @Override
    protected String getPageId() {
        return NEWS_EVENTS_LIST_PAGE;
    }
    @Override
    protected String getFilterParam() {
        return ":resource:News:resource:Event";
    }
}




