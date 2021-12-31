package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZQEEMAH_SHRHLDR_SRV")
public class ZQEEMAHShrhldrMockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test(final HttpServletRequest request) {
        return "redirect:/test.jsp";
    }

    @RequestMapping(method = RequestMethod.GET, path="/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH_SHRHLDR_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ZSHAREHOLDER_INF")
    public String getShareholderInfo(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH_SHRHLDR_SRV/ShareholderInfo.json";
    }
}
