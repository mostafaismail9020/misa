package com.sap.ibso.eservices.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/heartbeat")
public class SagiaHeartbeatController {
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public String get() {
        return "{}";
    }
}
