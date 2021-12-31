package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZQEEMAH2_EXT_SRV")
public class ZQEEMAH2MockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test(final HttpServletRequest request) {
        return "redirect:/test.jsp";
    }

    @RequestMapping(method = RequestMethod.GET, path="/DiffQeemahSet")
    public String getDiffQeemahSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/DiffQeemahSet(Refid='6000003629').json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path="/QeemahGeneralQstSet")
    public String getQeemahGeneralQstSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/QeemahGeneralQstSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ISICDetails**")
    public String getISICDetails(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/ISICDetails.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/FinancialQuesSet")
    public String getFinancialQuesSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/FinancialQuesSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/invsidpostSet")
    public String getInvsidpostSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/invsidpostSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path="/invsidpostSet")
    public ResponseEntity saveInvsidpostSet(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path="/Dropdown")
    public String getDropdown(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/Dropdown.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/displayattachdescripSet")
    public String getDisplayattachdescripSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/displayattachdescripSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/LegalStatusSet")
    public String getLegalStatusSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/LegalStatusSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/AnsQuesSectionSet")
    public String getAnsQuesSectionSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/AnsQuesSectionSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ProductSet")
    public String getProductSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/ProductSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ProductId")
    public String getProductId(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/ProductId.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ShareholderSet")
    public String getShareholderSet(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/ShareholderSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ZBASIC_CONT_FILE_ENT")
    public String getZBasicContFileEnt(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/ZBASIC_CONT_FILE_ENT.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ZFM_CRM_QMH_DROPDOWN")
    public String getZFMCRMQMHDROPDOWN(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/ZFM_CRM_QMH_DROPDOWN.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/fininvisidpostset")
    public String getFininvisidpostset(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH2_EXT_SRV/fininvisidpostset.json";
    }
}
