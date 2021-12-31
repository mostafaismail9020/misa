package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZUI5_TEMP_BID_CERT_SRV")
class ZTEMPBIDCERTMockODataController {

    @RequestMapping(method = RequestMethod.GET, path="/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_TEMP_BID_CERT_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "LIST")
    public String getListItems(final HttpServletRequest request) {
        String type = request.getParameter("Type");
        if ("'G'".equals(type)) {
            return "redirect:/mocks/ZUI5_TEMP_BID_CERT_SRV/governmentEntities.json";
        } else if ("'C'".equals(type)) {
            return "redirect:/mocks/ZUI5_TEMP_BID_CERT_SRV/countries.json";
        }
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, path = "ZTEL_CODESET({code})")
    public String getCountryCode(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_TEMP_BID_CERT_SRV/countryPrefix.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "ZTEMPSET")
    public ResponseEntity save(final HttpServletRequest request) {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
