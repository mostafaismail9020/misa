package com.sap.ibso.eservices.sagiaservices.web;

import org.fest.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.SplittableRandom;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;
import static com.sap.ibso.eservices.sagiaservices.services.impl.GovtHeaderService.GOVT_BRANCH_SET;

@Controller
@RequestMapping("/ZUI5_SAGIA_SRV")
public class ZUI5SagiaMockODataController {

    private static boolean newConvertToNationals = false;
    private static int stageLicenseCancellation = 1;
    private static String GOVT_SERV_CONTENT_HDR_NAV="GovtServicesToContentHDRNav";

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String test(final HttpServletRequest request) {
        return "redirect:/test.jsp";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/$metadata")
    public String metadata(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/metadata.xml";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/HomeHDRs")
    public String getHomeHDRsMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/HomeHDRs.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/HomeHDRs('{id}')")
    public String getHomeHDRsMockedData(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/HomeHDRs.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ServiceBpHDRs")
    public String getServiceHdrMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/ServiceHDRsBp.json";
    }

    @RequestMapping(method = RequestMethod.POST, path="/ServiceBpHDRs")
    public ResponseEntity saveServiceHdrMocked(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path="/ServiceBpHDRs({id})")
    public String getServiceHdrMockedSingle(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/ServiceHDRsBp1.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/SurveyHDRs('{id}')")
    public String getSurveyHDRsMocked(@PathVariable("id") String id,final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/SurveyHDRsMockData.json";
    }

    @RequestMapping(method = RequestMethod.POST, path="/SurveyHDRs")
    public ResponseEntity saveSurveyHDRs(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/CustomizingGetList")
    public String getCustomizingGetListMocked(final HttpServletRequest request) {

        String filterValue = request.getParameter("$filter");

        if(filterValue.contains("ZSR8")){
            return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/CustomizingGetListZSR8.json";
        }
        if(convToNationalsInfoContext(request)) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/convNationals/ServiceInfoDetails.json";
        }
        if(convToNationalsSupportingAttachmentsContext(request)) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/convNationals/supportingAttachments.json";
        }

        if(complaintsSupportingAttachmentsContext(request)) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/complaints/supportingAttachments.json";
        }

        if(filterValue.contains("ATTACHMENT") && filterValue.contains("ZSR4")){
            return "redirect:/mocks/ZUI5_SAGIA_SRV/CustomizingGetListRenewLicenceAttachments.json";
        }

        if(filterValue.contains("ZSR6")){
            return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/CustomizingGetListZSR6.json";
        }

        if(filterValue.contains("ZSR9")){
            return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/CustomizingGetListZSR9.json";
        }

        if(filterValue.contains("ZS10")){
            if(filterValue.contains("ATTACHMENT")){
                int fieldKeyIndex = filterValue.indexOf("Fieldkey eq '")+13;
                return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/ZS10/CustomizingGetListAttachmentsZS10_ZMOCI_"+filterValue.substring(fieldKeyIndex,filterValue.indexOf("'", fieldKeyIndex))+".json";
            }
        }

        return "redirect:/mocks/ZUI5_SAGIA_SRV/CustomizingGetList.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/Appointments")
    public String getAppointmentsMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/Appointments.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/Appointments")
    public ResponseEntity saveAppointmentsMocked(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Appointments({id})")
    public String getAppointmentMocked(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/Appointment.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ApptAvails")
    public String getAppointmentCalendarSlots(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/ApptAvails.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/GovtHeaders('{id}')")
    public String getGovtHeaders(final HttpServletRequest request, @PathVariable("id") String id) {
        if(request.getParameter(REQUEST_PARAMETER_EXPAND)!= null && request.getParameter(REQUEST_PARAMETER_EXPAND).equals(GOVT_BRANCH_SET))
            return "redirect:/mocks/ZUI5_SAGIA_SRV/govtHeaders/GovtBranchSet(one_branch).json";

        return "redirect:/mocks/ZUI5_SAGIA_SRV/govtHeaders/GovtHeaders.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/GovtDropdowns")
    public String getGovtDropdowns(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/GovtDropdowns.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/GlobalVals")
    public ResponseEntity checkAvailability(final HttpServletRequest request) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/AmendHeaders")
    public String getAmendHeaders(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/AmendHeaders.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/AmendHeaders")
    public ResponseEntity saceAmendHeaders(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/AmendHeaders({srId})")
    public String getAmendLicense(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/AmendLicense.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/AmendProducts")
    public String getAmendProducts(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/AmendProducts.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/AmendShareHolders({srId})")
    public String getAmendLicenseShareholder(@PathVariable("srId") String srId, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/AmendLicenseShareholder.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ServiceReqHDRs")
    public String getServicesRequestHDRs(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/ServicesRequestHDRs.json";
    }

    @RequestMapping(method = RequestMethod.POST, path="/ServiceReqHDRs")
    public ResponseEntity saveServicesRequestHDRs(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path="/ServiceReqHDRs('{id}')")
    public String getServiceRequestHDRs(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/ServiceRequestHDRs.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LicenseReplaceMents")
    public String getLicenseReplaceMentsMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/LicenseReplaceMents.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/LicenseReplaceMents")
    public ResponseEntity saveLicenseReplaceMentsMocked(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ContentDetails(ObjectId='{objectId}',DocumentID='{documentId}')/**")
    public String getLicenseRenewFileMocked(
            @PathVariable("objectId") String objectId,
            @PathVariable("documentId") String documentId,
            final HttpServletRequest request) {
        if((objectId.equals("40000229")) && (documentId.equals("00155D565E0B1ED7B7C86082AE0D40CA"))) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/convNationals/Articles_Of_Association.pdf";
        }
        return "redirect:/mocks/ZUI5_SAGIA_SRV/ContentDetails(ObjectId='1000619',DocumentID='005056B11C891ED8928B6BCA1090EAEC').JPG";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LicenseReplaceMents('{id}')")
    public String getLicenseReplaceMentMocked(@PathVariable("id") String id, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/LicenseReplaceMent.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LicenseReplaceMents('create')")
    public String getLicenseReplacementCustomizingGetListMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/LicenseReplaceMents('create').json";
    }


    @RequestMapping(method = RequestMethod.GET, path="/FinanceHDRS('{srId}')")
    public String getFinanceHdrs(final HttpServletRequest request, @PathVariable("srId") String srId) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/financeHDRS/FinanceHDRSMockData('"+srId+"').json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/FinanceHDRS")
    public String getFinanceEntities(final HttpServletRequest request) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/financeHDRS/FinanceHDRSMockData.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/CategorizationSchemaGetList")
    public String getCategorizationSchemaGetList(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/CategorizationSchemaGetList.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/FollowupServices")
    public String getFollowUpServicesData(final HttpServletRequest request,
                                          @RequestParam(value = "$filter", required = false) String filter)
    {
        if (Strings.isEmpty(filter) || "ZFLUP_02".equals(filter)) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/FollowUpServicesViolationReplies.json";
        }
        return "redirect:/mocks/ZUI5_SAGIA_SRV/FollowUpServicesWarningLetter.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/FollowupServices('{srId}')")
    public String getFollowUpServicesSingle(final HttpServletRequest request, @PathVariable("srId") String srId) {

        final Integer n = new SplittableRandom().nextInt(0, 2);
        return "redirect:/mocks/ZUI5_SAGIA_SRV/FollowUpServicesViolationReplies('" + n + "').json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ComplaintsAndEnquiryHdrs")
    public String getComplaintsList(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/complaints/ComplaintsAndEnquiry.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/ComplaintsAndEnquiryHdrs('{srId}')")
    public String getSingleComplaint(final HttpServletRequest request, @PathVariable("srId") String srId) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/complaints/singleComplaint.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/ComplaintsAndEnquiryHdrs")
    public ResponseEntity SaveComplaintsList(final HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ConvToNationals")
    public String getConvertToNationalsHistory(final HttpServletRequest request) {
        if(newConvertToNationals) {
            return "redirect:/mocks/ZUI5_SAGIA_SRV/convNationals/ConvToNationalsAfterNewConvert.json";
        }
        return "redirect:/mocks/ZUI5_SAGIA_SRV/convNationals/ConvToNationalsList.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ConvToNationals('{srID}')")
    public String getOneConvertToNationals(@PathVariable("srID") String srID, final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/convNationals/ConvToNationals" + "('" + srID + "')" + ".json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/ConvToNationals")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity convertToNations(@RequestBody String postPayload, final HttpServletRequest request) {

        if(postPayload.contains("\"ReApply\":true")) {
            System.out.println("resubmitted");
        }
        else{
            this.newConvertToNationals = true;
        }
        return ControllerUtil.createResponsePayload(postPayload);
    }

    private boolean convToNationalsInfoContext(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("$filter")) {
            String filterValue = request.getParameter("$filter");
            if(filterValue.equals("(Fieldname eq 'INFO' and Scenario eq 'ZSR7')")) {
                return true;
            }
        }
        return false;
    }

    private boolean convToNationalsSupportingAttachmentsContext(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("$filter")) {
            String filterValue = request.getParameter("$filter");
            if(filterValue.equals("(Fieldname eq 'ATTACHMENT' and Scenario eq 'ZSR7')")) {
                return true;
            }
        }
        return false;
    }

    private boolean complaintsSupportingAttachmentsContext(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("$filter")) {
            String filterValue = request.getParameter("$filter");
            if(filterValue.equals("(Fieldname eq 'ATTACHMENT' and Scenario eq 'ZRVR')")) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/GovtServices")
    public ResponseEntity postGovtServices(HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/GovtServices")
    public String getGovtServices(HttpServletRequest request) {

        String filterValue = request.getParameter("$filter");
        if(filterValue.contains("ServiceType")){
            int fieldKeyIndex = filterValue.indexOf("ServiceType eq '")+16;
            return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/ZS10/GovtServicesZS10_"+filterValue.substring(fieldKeyIndex,filterValue.indexOf("'", fieldKeyIndex))+".json";
        }

        return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/GovtServicesMockData.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/GovtServices('{serviceId}')")
    public String getGovtServices(final HttpServletRequest request, @PathVariable("serviceId") String serviceId) {
        if(request.getParameter(REQUEST_PARAMETER_EXPAND)!= null && request.getParameter(REQUEST_PARAMETER_EXPAND).contains(GOVT_SERV_CONTENT_HDR_NAV))
            return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/SingleGovtServicesAttachmentsMockData.json";

        return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/SingleGovtServicesMockData.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/BpGovtServices")
    public ResponseEntity postBPGovtServices(HttpServletRequest request) {
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path="/BpGovtServices")
    public String getBpGovtServicesMocked(final HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/BpGovtServicesMockData.json";
    }

    @RequestMapping(method = RequestMethod.GET, path="/BpGovtServices('{serviceId}')")
    public String getBpGovtServicesByServiceIdMocked(final HttpServletRequest request, @PathVariable("serviceId") String serviceId) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/govtServices/BpGovtServicesMockData.json";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/GlobalLicenseCancellationSet(SrID='{srId}',Stage='{stage}')")
    public String getGlobalLicenseCancellationSetMocked(final HttpServletRequest request) {
        switch (stageLicenseCancellation){
            case 1:
                return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/GlobalLicenseCancellationSet_stage1.json";
            case 2:
                return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/GlobalLicenseCancellationSet_stage2.json";
            case 3:
                return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/GlobalLicenseCancellationSet_stage3.json";
            case 4:
                return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/GlobalLicenseCancellationSet_stage4.json";
            case 5:
                stageLicenseCancellation = 1;
                return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/GlobalLicenseCancellationSet_stage5.json";
                default:
                    return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/GlobalLicenseCancellationSet_stage1.json";

        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LicenseClearanceSet(SrID='{srId}',Stage='{stage}')")
    public String getLicenseClearanceSetMocked(final HttpServletRequest request, @PathVariable("srId") String srId, @PathVariable("stage") String stage) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/LicenseClearanceSet.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LicenseCancellationSet(SrID='{srId}',Stage='{stage}')")
    public String getLicenseCancellationSetMocked(final HttpServletRequest request, @PathVariable("srId") String srId, @PathVariable("stage") String stage) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/LicenseCancellationSet.json";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/LicenseClearanceSet")
    public ResponseEntity postLicenseClearanceSet(HttpServletRequest request) {
        stageLicenseCancellation = 2;
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/LicenseCancellationSet")
    public ResponseEntity postLicenseCancellationSet(HttpServletRequest request) {
        stageLicenseCancellation = 5;
        return ControllerUtil.createResponsePayload(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/LicenseCancellationSet")
    public String getLicenseCancellationSets(HttpServletRequest request) {
        return "redirect:/mocks/ZUI5_SAGIA_SRV/cancel/LicenseCancellationSet.json";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/LicenseClearanceSet(SrID='{srId}',Stage='{stage}')")
    public ResponseEntity updateLicenseClearanceSet(final HttpServletRequest request,  @PathVariable("srId") String srId, @PathVariable("stage") String stage) {
        stageLicenseCancellation++;
        return ControllerUtil.createResponsePayload(request);
    }

}


