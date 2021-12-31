package com.sap.ibso.eservices.sagiaservices.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ZESRV_ENH_ODATA_SRV")
public class ZESRV_ENH_MOCK_ODATA_Controller {

	@RequestMapping(method = RequestMethod.GET, path = "/$metadata")
	public String metadata(final HttpServletRequest request) {
		return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/metadata.xml";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/InvestorNotificationSet")
	public String getInvestorNotificationSet(final HttpServletRequest request,
			@RequestParam(value = "$filter", required = false) String filter) {

		if(filter.contains("TransactionId")) {
			return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/singleNotification.json";
		}
		if(filter.contains("NotificationType eq 'SY'")) {
			return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/surveyNotifications.json";
		}
		if(filter.contains("NotificationType eq 'WL'")) {
			return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/warningLetterNotifications.json";
		}
		if(filter.contains("NotificationType eq 'VI'")) {
			return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/viNotifications.json";
		}
		return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/investorNotifications.json";
	}


    @RequestMapping(method = RequestMethod.GET, path = "/SurveyHDRSet(Surveyid='{srId}',Language='{lang}',SurveyVersion='{version}')")
    public String getSingleSurvey(
            @PathVariable("srId") String srId, @PathVariable("lang") String lang, @PathVariable("version") String version) {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/singleSurvey.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/SurveyHDRSet")
    public ResponseEntity submitSurvey(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LegalInquiryHDRSet")
    public String legalInquiryHDRSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/LegalInquiryHDRSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/LegalInquiryHDRSet")
    public ResponseEntity legalInquiryHDRSave(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LegalInquiryHDRSet('{srId}')")
    public String legalInquiryHDR(@PathVariable("srId") String srId) {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/LegalInquiryHDR.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LegalInquiryDropdownSet")
    public String legalInquiryDropdownSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/LegalInquiryDropdownSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ISICSectionSet")
    public String isicSectionSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/ISICSectionSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ISICDivisionSet")
    public String isicDivisionSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/ISICDivisionSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ISICGroupSet")
    public String isicGroupSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/ISICGroupSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ISICClassSet")
    public String isicClassSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/ISICClassSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ISICBranchSet")
    public String isicBranchSet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/ISICBranchSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ISICActivitySet")
    public String isicActivitySet() {
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/ISICActivitySet.json";
    }

	@RequestMapping(method = RequestMethod.GET, path="/SupportVisitSet({SrId})")
	public String getSupportVisitSet(final HttpServletRequest request, @PathVariable("SrId") String srId) {
		return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/SupportVisitSet(27000045).json";
	}

	@RequestMapping(method = RequestMethod.GET, path="SupportVisitSet")
	public String getSupportVisitSets(final HttpServletRequest request) {
		return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/SupportVisitSets.json";
	}

    @RequestMapping(method = RequestMethod.POST, path="/SupportVisitSet")
    public ResponseEntity createSupportVisitSets(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
	}

    @RequestMapping(method = RequestMethod.GET, path = "/BPAddrSet(Bpid='{bpId}')**")
    public String getBPAddrSet(){
        return "redirect:/mocks/ZESRV_ENH_ODATA_SRV/BPAddrSet.json";
    }
}
