package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZQEEMAH_SURVEY_SRV")
public class ZQEEMAHSurveyMockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test(final HttpServletRequest request) {
        return "redirect:/test.jsp";
    }

    @RequestMapping(method = RequestMethod.GET, path="/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH_SURVEY_SRV/metadata.xml";
    }
}
