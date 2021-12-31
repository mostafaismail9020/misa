package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZUI5_NIP_SRV")
public class ZUI5NipSrvMockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPHeaderSet")
    public String getNIPHeaderSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPHeaderSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPHeaderSet")
    public ResponseEntity saveNIPHeaderSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPCountrySet")
    public String getNIPCountrySet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPCountrySet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPCountrySet")
    public ResponseEntity saveNIPCountrySet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPCitySet")
    public String getNIPCitySet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPCitySet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPCitySet")
    public ResponseEntity saveNIPCitySet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPRegionSet")
    public String getNIPRegionSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPRegionSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPRegionSet")
    public ResponseEntity saveNIPRegionSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPLegalStatusSet")
    public String getNIPLegalStatusSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPLegalStatusSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPLegalStatusSet")
    public ResponseEntity saveNIPLegalStatusSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPISICSectionSet")
    public String getNIPISICSectionSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPISICSectionSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPISICSectionSet")
    public ResponseEntity saveNIPISICSectionSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/NIPISICDivisionSet")
    public String getNIPISICDivisionSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NIP_SRV/NIPISICDivisionSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NIPISICDivisionSet")
    public ResponseEntity saveNIPISICDivisionSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }
}
