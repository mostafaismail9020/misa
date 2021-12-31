package com.sap.ibso.eservices.storefront.controllers.pages;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.populators.AfterPaymentPopulator;
import com.sap.ibso.eservices.facades.sagia.MIGSPaymentFacade;
import com.sap.ibso.eservices.facades.sagia.PaymentFacade;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.document.DocumentService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaRuntimeException;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.controllers.SagiaConstants;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.core.model.user.CustomerModel;

@Controller
@RequestMapping("/payment")
public class SagiaPaymentController extends SagiaAbstractPageController {

    private static final Logger LOG = Logger.getLogger(SagiaPaymentController.class);
    private static final String SAGIA_PAYMENT_DETAILS_CMS_PAGE = "sagia-payment-details";
    private static final String SAGIA_PAYMENTS_SUCCESS_CMS_PAGE = "payments-success";
    private static final String SAGIA_PAYMENTS_FAILURE_CMS_PAGE = "payments-failure";

    private static final String TRANSACTION_ID_ATTRIBUTE = "transaction_id";
    private static final String SERVICE_ID_ATTRIBUTE = "serviceId";
    private static final String SECURE3D_FORM_ATTRIBUTE = "secure3DForm";
    private static final String PAYMENT_MAP_ATTRIBUTE = "paymentMap";
    private static final String SUCCESS_STATUS = "success";

    @Resource(name = "sagiaPaymentFacade")
    private PaymentFacade sagiaPaymentFacade;

    @Resource(name = "MIGSPaymentFacade")
    private MIGSPaymentFacade migsPaymentFacade;

    @Resource(name = "zQeemahService")
    private ZQeemahService qeemahService;

    @Resource(name = "sagiaEServicesDocumentService")
    private DocumentService documentService;

    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;

    @Resource(name = "afterPaymentPopulator")
    private AfterPaymentPopulator afterPaymentPopulator;

    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getPaymentDetails(@PathVariable final String id, final Model model) throws CMSItemNotFoundException {
        PaymentData paymentData = sagiaPaymentFacade.getPayment(id);
        model.addAttribute("paymentData", paymentData);

        Boolean hasInvoice = true;
        byte[] pdf = documentService.getSadadSalesOrderInvoice(id);
        if (pdf == null || pdf.length == 0) {
            hasInvoice = false;
        }
        model.addAttribute("hasInvoice", hasInvoice);
        model.addAttribute("MIGS_Session_JS", configurationService.getConfiguration().getString(SagiaConstants.MIGS_SESSION_URL));

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_PAYMENT_DETAILS_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_PAYMENT_DETAILS_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * checks card enrollement for 3D Secure
     * if true sets sets the secure3dForm on an attribute and returns the 3dSecureForm flag in success (to redirect to that page)
     * if false calls payTransaction directly
     *
     * @param request
     * @param map
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/payLicense", method = RequestMethod.POST)
    @ResponseBody
    public String check3DSecure(HttpServletRequest request, @RequestBody Map<String, String> map) throws JSONException {
        logCookies(request);
        getSessionService().setAttribute(PAYMENT_MAP_ATTRIBUTE, map);
        HashMap<String, String> responseMap = new HashMap<>();
        String secure3DForm;
        try {
            secure3DForm = migsPaymentFacade.check3DSecureEnrollment(map.get("sessionId"), map.get("amount"), map.get("currency"), request.getRequestURL().toString());
            if (StringUtils.equalsIgnoreCase("PAY", secure3DForm)) {
                return payTransaction();
            } else if(StringUtils.isNotBlank(secure3DForm)) {
                getSessionService().setAttribute(SECURE3D_FORM_ATTRIBUTE, secure3DForm);
                responseMap.put(SUCCESS_STATUS, SECURE3D_FORM_ATTRIBUTE);
                return new Gson().toJson(responseMap);
            }
        } catch (RestClientException | MalformedURLException e) {
            LOG.error(e.getMessage(), e);
        }
        responseMap.put(SUCCESS_STATUS, "false");
        return new Gson().toJson(responseMap);
    }

    /**
     * executes the pay transaction to MIGS and returns a repsonseMap depending on the API Response
     *
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/payTransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String payTransaction() throws JSONException {
        HashMap<String, String> responseMap = new HashMap<>();
        ResponseEntity<String> response;
        try {
            response = migsPaymentFacade.payTransaction();

            if (Objects.nonNull(response) && Objects.equals(response.getStatusCode(), HttpStatus.CREATED)) {
                JSONObject responseJson = new JSONObject(response.getBody());
                if (StringUtils
                        .equalsIgnoreCase("APPROVED", responseJson.getJSONObject("response").get("gatewayCode").toString())) {

                    String transactionId = (String) ((JSONObject) responseJson.get("transaction")).get("id");
                    if (StringUtils.isNotBlank(transactionId)) {
                        getSessionService().setAttribute(TRANSACTION_ID_ATTRIBUTE, transactionId);
                        //migsPaymentFacade.sendTransactionIDToCRM(transactionId);
                        sagiaPaymentFacade.savePayment(afterPaymentPopulator.createPaymentSuccessfulDataFromMap(getSessionService().getAttribute(PAYMENT_MAP_ATTRIBUTE), transactionId));
                    }
                    responseMap.put(SUCCESS_STATUS, "true");
                    return new Gson().toJson(responseMap);
                }
            }
        } catch (RestClientException e) {
            String responseBodyAsString = ((HttpClientErrorException) e).getResponseBodyAsString();
            if (LOG.isDebugEnabled()) {
                LOG.debug("Exception from response : " + responseBodyAsString);
            }
            return responseBodyAsString;
        }
        responseMap.put(SUCCESS_STATUS, "false");
        return new Gson().toJson(responseMap);
    }

    /**
     * Returns success page after the pay transaction is finished.
     *
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String getSuccessPage(final Model model) {
        Boolean isOutstandingFee = false;
        Boolean billPaymentSuccess = false;
        if (getSessionService().getAttribute(TRANSACTION_ID_ATTRIBUTE) != null) {
            String transactionId = getSessionService().getAttribute(TRANSACTION_ID_ATTRIBUTE);
            try {
                storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_PAYMENTS_SUCCESS_CMS_PAGE));
                setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_PAYMENTS_SUCCESS_CMS_PAGE));
                model.addAttribute(TRANSACTION_ID_ATTRIBUTE, transactionId);

                sagiaDashboardWithoutLicenseFacade.evictApplicationStatus();
                if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
                    LOG.info("######### hasLicense: true");
                    billPaymentSuccess = true;
                    CustomerModel customer = (CustomerModel) userService.getCurrentUser();
                    if (customer != null && Boolean.TRUE.equals(customer.getIsOutstandingFee())) {
                        isOutstandingFee = true;
                    }

                } else {
                    Optional<ApplicationStatusData> applicationStatus = qeemahService.getApplicationStatus();
                    String newLicenseId = applicationStatus.isPresent() ? applicationStatus.get().getLeadId() : null;
                    model.addAttribute(SERVICE_ID_ATTRIBUTE, newLicenseId);
                    //model.addAttribute("billPaymentSuccess", billPaymentSuccess);
                }
                model.addAttribute("billPaymentSuccess", billPaymentSuccess);
                model.addAttribute("isOutstandingFee", isOutstandingFee);

            } catch (Exception e) {
                throw new SagiaRuntimeException("Payment was completed successfully but an unexpected error occurred afterwards ", e);
            } finally {
                migsPaymentFacade.clearPaymentSessions();
            }
            return getViewForPage(model);
        }
        return REDIRECT_PREFIX + "/";
    }

    @RequestMapping(value = "/secure3DForm", method = RequestMethod.GET)
    public String getSecure3DForm(final Model model, final HttpServletRequest request) {
        logCookies(request);
        final String secure3DStatus = getSessionService().getAttribute(SECURE3D_FORM_ATTRIBUTE);
        // Secure3D status is not null or error.
        if (StringUtils.isNotBlank(secure3DStatus)) {
            model.addAttribute(SECURE3D_FORM_ATTRIBUTE, secure3DStatus);
            return "pages/payment/secure3DForm";
        }
        return REDIRECT_PREFIX + "/";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getErrorPage(final Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_PAYMENTS_FAILURE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_PAYMENTS_FAILURE_CMS_PAGE));
        migsPaymentFacade.clearPaymentSessions();
        return getViewForPage(model);
    }

    /**
     * Processes the PaRes code from 3DSecure form after successful check
     *
     * @param paRes paRes
     * @param model model
     * @return String
     */
    @RequestMapping(value = "/process3DSecure", method = RequestMethod.POST)
    public String process3DSecure(@RequestParam(name = "PaRes") Optional<String> paRes, final HttpServletRequest request, final Model model) {
        ResponseEntity<String> response = null;
        try {
            logCookies(request);
            LOG.debug("callbck process3DSecure, paRes.isPresent():" + paRes.isPresent() + " user: " + getCurrentUserID());
            if (paRes.isPresent()) {
                response = migsPaymentFacade.process3DSecure(paRes.get());
                if (response != null && response.getStatusCode().equals(HttpStatus.CREATED)) {
                    LOG.debug("process3DSecure response CREATED");
                    JSONObject responseJson = new JSONObject(response.getBody());
//                    if ("APPROVED".equalsIgnoreCase(responseJson.getJSONObject("response").get("gatewayCode").toString())) {
                    if (StringUtils.equalsIgnoreCase("APPROVED", responseJson.getJSONObject("response").get("gatewayCode").toString())) {
                    String transactionId = (String) ((JSONObject) responseJson.get("transaction")).get("id");
                    if (transactionId != null) {
                        getSessionService().setAttribute(TRANSACTION_ID_ATTRIBUTE, transactionId);
                        //migsPaymentFacade.sendTransactionIDToCRM(transactionId);
                        sagiaPaymentFacade.savePayment(afterPaymentPopulator.createPaymentSuccessfulDataFromMap(getSessionService().getAttribute(PAYMENT_MAP_ATTRIBUTE), transactionId));
                    } else {
                        LOG.error("in process3DSecure response transaction id missing, hence payment transaction has not created in the hybris, user: " + getCurrentUserID());
                    }
                    return REDIRECT_PREFIX + "/payment/success";
                    }
                }
            }
        } catch (JSONException e) {
            LOG.error(e.getMessage(), e);
        }
        return REDIRECT_PREFIX + "/payment/error";
    }

    @RequireHardLogIn
    @RequestMapping(value = "/pdf/{srId}", method = RequestMethod.GET)
    public void getPdfDocument(@PathVariable String srId, final Model model, HttpServletRequest request, final HttpServletResponse response) {
        byte[] pdf = documentService.getSadadSalesOrderInvoice(srId);
        if (pdf == null || pdf.length == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setHeader("Content-Disposition", "inline;filename=invoice.pdf");
        response.setContentType("application/pdf");
        response.setDateHeader("Expires", -1);
        try {
            response.getOutputStream().write(pdf);
            response.getOutputStream().flush();
        } catch (final IOException e) {
            LOG.info("Unable to write invoice PDF on output stream" + e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String getCurrentUserID() {
        return userService.getCurrentUser().getUid();
    }

    private void logCookies(final HttpServletRequest httpRequest) {
        if (LOG.isDebugEnabled()) {
            final Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (final Cookie cookie : cookies) {
                    writeDebugLog("COOKIE Name: [", cookie.getName(), "] Path: [", cookie.getPath(), "] Value: [", cookie.getValue(), "]");
                }
            }
        }
    }

    private void writeDebugLog(final String... messages) {
        LOG.debug(String.join(" ", messages));
    }
}
