package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZESRV_PAYMENT_DETAILS_ODATA_SRV")
public class ZESRVPaymentDetailsMockODataController {

    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZESRV_PAYMENT_DETAILS_ODATA_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SalesOrderPaymentSet")
    public String getSalesOrderPaymentSet() {
        return "redirect:/mocks/ZESRV_PAYMENT_DETAILS_ODATA_SRV/SalesOrderPaymentSetJson.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SalesOrderPaymentDetailsSet('{id}')")
    public String getSalesOrderPaymentDetailsSet(@PathVariable("id") String id) {
        return "redirect:/mocks/ZESRV_PAYMENT_DETAILS_ODATA_SRV/SalesOrderPaymentDetailsSetJson("+id+").json";
    }
}
