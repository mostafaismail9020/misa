package com.sap.ibso.eservices.sagiaservices.web;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;


@Controller
@RequestMapping("/ZUI5_REAL_ESTATE_SRV")

public class ZUI5SagiaRealEstateMockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_REAL_ESTATE_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/RegionCitySet")
    public String getRegionCityMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_REAL_ESTATE_SRV/RegionCitySet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/RegionCitySet('{id}')")
    public String getCitiesMocked(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_REAL_ESTATE_SRV/" + id + ".json";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/$batch", produces = "multipart/mixed;boundary=FF01FB4ACAAF2B93B09D050CE02ACF980")
    @ResponseBody
    public String postRealEstateHistoryMocked(final HttpServletRequest request, HttpServletResponse response) throws IOException {
        return IOUtils.toString(new URL("https://localhost:9002/sagiaservices/ZUI5_REAL_ESTATE_SRV/$batch").openStream());
    }


    @RequestMapping(method = RequestMethod.GET, path = "/$batch")
    public String getRealEstateHistoryMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_REAL_ESTATE_SRV/batch.json";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/RealEstateSet('{id}')")
    public String getRealEstateByIdMocked(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_REAL_ESTATE_SRV/" + id + ".json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/RealEstateSet")
    public String getRealEstateMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_REAL_ESTATE_SRV/RealEstateSetMock.json";
    }

}
