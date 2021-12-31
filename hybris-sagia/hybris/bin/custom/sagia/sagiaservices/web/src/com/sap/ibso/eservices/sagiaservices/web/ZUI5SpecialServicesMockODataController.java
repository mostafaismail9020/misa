package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/ZUI5_SPECIAL_SERVICES_SRV")
public class ZUI5SpecialServicesMockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SPCHECKHISTORY")
    public String getSpecialServices(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SPCHECKHISTORY.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SPCOUNTRY")
    public String getSpecialServicesCountries(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SPCOUNTRY.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SPREGION")
    public String getSpecialServicesRegions(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SPREGION.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SPGETATTACHMENTS")
    public String getSpecialServicesAttachments(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SPGETATTACHMENTS.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SPCHECKHISTORY({id})")
    public String getSpCheckHistory(@PathVariable("id") Optional<Integer> id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SPCHECKHISTORY_SINGLE.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/SERVICEHEADERSET('{id}')")
    public String getSpecialService(@PathVariable("id") Optional<Integer> id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SERVICEHEADERSET('9000100').json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SPCHECKAPPLICANTS")
    public String getSpecialServiceApplicants(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SPECIAL_SERVICES_SRV/SPCHECKAPPLICANTS.json";
    }
}
