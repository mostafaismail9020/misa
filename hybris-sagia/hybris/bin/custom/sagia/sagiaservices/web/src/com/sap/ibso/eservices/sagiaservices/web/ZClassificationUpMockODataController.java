package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZCLASSIFICATION_UP_SRV")
public class ZClassificationUpMockODataController {

    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZCLASSIFICATION_UP_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ZATT_LISTSET")
    public String getZattListSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZCLASSIFICATION_UP_SRV/ZattListSetMockData.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/APPEAL")
    public String getAppeal(final HttpServletRequest request) {
        return "redirect:/mocks/ZCLASSIFICATION_UP_SRV/AppealMockData.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ATTACHMENTDETAILSSET(OBJECT_ID='{objectId}',DOC_GUID='{guId}')/$value")
    public String getAttachmentDetailsSet(final HttpServletRequest request, @PathVariable("objectId") String objectId, @PathVariable("guId") String guId) {
        return "redirect:/mocks/ZCLASSIFICATION_UP_SRV/pdf.txt";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ZCLASS_CONTSET(ObjectID='{objectId}',DocGUID='{guId}')/$value")
    public String getZClassContSet(final HttpServletRequest request, @PathVariable("objectId") String objectId, @PathVariable("guId") String guId) {
        return "redirect:/mocks/ZCLASSIFICATION_UP_SRV/pdf.txt";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ZCLASS_DETSET('{id}')")
    public String getZClassDetSet(final HttpServletRequest request, @PathVariable("id") String id) {
        return "redirect:/mocks/ZCLASSIFICATION_UP_SRV/ZClassDetSetMockData.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/ZCLASS_DETSET")
    public ResponseEntity saveZClassDetSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }
}
