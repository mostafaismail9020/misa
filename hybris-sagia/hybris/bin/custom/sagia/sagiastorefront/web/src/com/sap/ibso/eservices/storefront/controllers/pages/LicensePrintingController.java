package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.impl.DefaultLicensePrintingFacade;
import com.sap.ibso.eservices.storefront.util.SagiaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/my-sagia/license")
public class LicensePrintingController {

    @Resource(name = "sagiaLicensePrintingFacade")
    private DefaultLicensePrintingFacade sagiaLicensePrintingFacade;

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public void printLicense(final Model model, HttpServletRequest request, final HttpServletResponse response) {
        SagiaUtils.writeByteArray(response, sagiaLicensePrintingFacade.getLicense());
    }
}
