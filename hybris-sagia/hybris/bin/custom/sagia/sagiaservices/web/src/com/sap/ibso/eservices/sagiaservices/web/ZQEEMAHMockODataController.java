package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZQEEMAH_SRV")
class ZQEEMAHMockODataController {
    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test(final HttpServletRequest request) {
        return "redirect:/test.jsp";
    }

    @RequestMapping(method = RequestMethod.GET, path="/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/inventory", "/ZFM_CRM_ID_STATUS"})
    public String getInventoryMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH_SRV/ZFM_CRM_ID_STATUS.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ZBASIC_ORG_INFO_ENT('{id}')")
    public String getOrgInfoMocked(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZQEEMAH_SRV/ZBASIC_ORG_INFO_ENT.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/ZBASIC_ORG_INFO_ENT")
    public ResponseEntity saveOrgInfoMocked(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/ZBASIC_ORG_INFO_ENT('{id}')")
    public ResponseEntity updateOrgInfoMocked(@PathVariable("id") String id, final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ZFM_CRM_QMH_DROPDOWN")
    public String getOrgInfoDictionaryMocked(@RequestParam(value = "lvkey", required = false) String language,
                                             @RequestParam(value = "lv_flag", required = false) String flag,
                                             @RequestParam(value = "lv_region", required = false) String region,
                                             final HttpServletRequest request) {
        if ("'LS'".equals(flag)) {
            return "redirect:/mocks/ZQEEMAH_SRV/ZBASIC_LEGAL_STATUS.json";
        } else if ("'RG'".equals(flag)) {
            return "redirect:/mocks/ZQEEMAH_SRV/ZBASIC_REGION.json";
        }
        return "redirect:/mocks/ZQEEMAH_SRV/ZBASIC_CITY.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ZCRM_TELECODE_ENT(CounKey='RO')")
    public String getZCRMTelecodeEnt() {
        return "redirect:/mocks/ZQEEMAH_SRV/ZCRM_TELECODE_ENT(CounKey='RO').json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/REGISTER_USER_ENT")
    public String getRegisterUserEnt() {
        return "redirect:/mocks/ZQEEMAH_SRV/REGISTER_USER_ENT.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/REGISTER_USER_ENT**")
    public String getRegisterUserEntByInvId(@RequestParam(value = "investorId", required = true) String investorId) {
        return "redirect:/mocks/ZQEEMAH_SRV/REGISTER_USER_ENT_BY_INV_ID.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/IsicDet")
    public String getIsicDet() {
        return "redirect:/mocks/ZQEEMAH_SRV/IsicDetPs.json";
    }
}
