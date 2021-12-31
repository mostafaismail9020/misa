package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@Controller
@RequestMapping("/util")
public class SagiaUtilController extends SagiaAbstractPageController {
    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @RequestMapping(value = "/dismiss-tutorial", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public void dismissTutorial() {
        sagiaCustomerFacade.dismissDashboardTutorial();
    }
}
