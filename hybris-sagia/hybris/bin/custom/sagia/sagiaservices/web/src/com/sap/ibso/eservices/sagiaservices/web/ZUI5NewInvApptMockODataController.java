package com.sap.ibso.eservices.sagiaservices.web;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/ZUI5_NEW_INV_APPT_SRV")
public class ZUI5NewInvApptMockODataController {
    private static final Logger LOG = Logger.getLogger(ZUI5NewInvApptMockODataController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NEW_INV_APPT_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ApptAvailSet")
    public String getNationalInvestorTimeSlot(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_NEW_INV_APPT_SRV/ApptAvailSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/ApptAvailSet")
    public ResponseEntity saveApptAvailSet(final HttpServletRequest request) {
        return createResponsePayload(request);
    }

    private ResponseEntity createResponsePayload(HttpServletRequest request) {
        try {
            return new ResponseEntity(IOUtils.toString(request.getReader()), HttpStatus.CREATED);
        } catch (IOException e) {
            LOG.warn(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.CREATED);
        }

    }


}
