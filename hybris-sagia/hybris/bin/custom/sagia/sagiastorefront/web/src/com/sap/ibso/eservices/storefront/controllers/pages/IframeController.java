package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IframeController extends SagiaAbstractPageController {

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String getVerifyPage()  {
        return "iframe/verifyIframe";
    }

    @RequestMapping(value = "/appt", method = RequestMethod.GET)
    public String getApptPage()  {
        return "iframe/apptIframe";
    }

}
