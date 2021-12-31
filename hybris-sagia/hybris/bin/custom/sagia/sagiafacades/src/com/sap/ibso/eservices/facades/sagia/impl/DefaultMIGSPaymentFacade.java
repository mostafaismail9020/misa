package com.sap.ibso.eservices.facades.sagia.impl;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.sap.ibso.eservices.core.event.SagiaPaymentApiErrorEvent;
import com.sap.ibso.eservices.core.event.SagiaPaymentSessionEmailEvent;
import com.sap.ibso.eservices.core.model.SagiaPaymentSessionModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaPaymentSessionService;
import com.sap.ibso.eservices.facades.sagia.MIGSPaymentFacade;
import com.sap.ibso.eservices.sagiaservices.license.PaymentAssignmentService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * DefaultMIGSPaymentFacade
 */
public class DefaultMIGSPaymentFacade implements MIGSPaymentFacade {


    public static final String KEY_3DSECURE = "3DSecure";
    private static final String API_USER = "migs.api.user";
    private static final String API_PASS = "migs.api.password";
    private static final String API_URL = "migs.api.url";
    private static final String MERCHANT_ID = "migs.merchantId";

    private static final String URL_MERCHANT = "/merchant/";
    private static final String URL_3DSECUREID = "/3DSecureId/";
    private static final String SECUREID3D = "3DSecureID";

    private static final String TRANSACTIONID_PREFIX = "TN";
    private static final String TRANSACTION_ID_ATTRIBUTE = "transaction_id";
    private static final String SECURE3D_FORM_ATTRIBUTE = "secure3DForm";
    private static final String PAYMENT_MAP_ATTRIBUTE = "paymentMap";

    private static final String SAGIA_LICENSE_SERVICE_REQUEST_ID = "LicenseServiceRequestId";

    private static final String PROCEED_STATUS = "PROCEED";


    private static final Logger LOG = Logger.getLogger(DefaultMIGSPaymentFacade.class);
    public static final String GATEWAY_RECOMMENDATION_KEY = "gatewayRecommendation";
    public static final String PA_RES_STATUS_KEY = "paResStatus";
    public static final String SESSION_ID = "sessionId";
    public static final String AMOUNT = "amount";
    public static final String CURRENCY = "currency";

    @Resource
    private ConfigurationService configurationService;
    private KeyGenerator keyGenerator;

    @Autowired
    private SessionService sessionService;

    @Resource
    private UserService userService;

    @Autowired
    private PaymentAssignmentService paymentAssignmentService;

    private SagiaPaymentSessionService sagiaPaymentSessionService;
    private EventService eventService;


    /**
     * checks if card is enrolled for 3D
     * if true returns the 3DSecure form
     * else returns null
     *
     * @param sessionId  sessionId
     * @param amount     amount
     * @param currency   currency
     * @param requestUrl requestUrl
     * @return String
     */
    public String check3DSecureEnrollment(String sessionId, String amount, String currency, String requestUrl) {
        String secure3DForm = null;
        LOG.info("##### Request URL : " + requestUrl);
        int indexOf = requestUrl.indexOf("/payment");
        String responseUrl = requestUrl.substring(0, indexOf) + "/payment/process3DSecure";
        LOG.info("***** response URL : " + responseUrl);
        String secure3DId = get3DSecureID(true);
        HttpHeaders headers = this.createRequestHeaders();
        if (StringUtils.isNotBlank(secure3DId)) {
            CustomerModel customerModel = (CustomerModel) this.getCurrentUser();
            Map<String, String> paymentMap = new HashMap<>(getSessionService().getAttribute(PAYMENT_MAP_ATTRIBUTE));
            this.getSagiaPaymentSessionService().saveSagiaPaymentSession(customerModel, secure3DId,
                    new Gson().toJson(paymentMap),
                    getSessionService().getAttribute(TRANSACTION_ID_ATTRIBUTE));
        }

        String requestBody = "{" +
                "\"apiOperation\" : \"CHECK_3DS_ENROLLMENT\"," +
                "\"3DSecure\" : {" +
                "\"authenticationRedirect\" : {\"responseUrl\" :  \"" + responseUrl + "\"}}," +
                "\"order\" : {" +
                "\"amount\" : \"" + amount + "\", \"currency\" : \"" + currency + "\"" +
                "}," +
                "\"session\" : { \"id\" : \"" + sessionId + "\"} }";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);


        RestTemplate restTemplate = new RestTemplate();
        String url = configurationService.getConfiguration().getString(API_URL) + URL_MERCHANT + configurationService.getConfiguration().getString(MERCHANT_ID) + URL_3DSECUREID + secure3DId;
        LOG.debug("sending request for checking 3D enrollment for \n user: " + getCurrentUserID() + " \n requestURL:" + url + " \n requestBody: " + request);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
            LOG.debug("received response for checking 3D enrollment user:" + getCurrentUserID() + " responseBody: " + response);
            JSONObject responseJson = new JSONObject(response.getBody());

            if (response.getStatusCode().equals(HttpStatus.CREATED)) {
                JSONObject secure3D = ((JSONObject) responseJson.get("3DSecure"));

                String veResEnrolled = secure3D.getString("veResEnrolled");
                String gatewayRecommendation = getGatewayRecommendation(responseJson);
                LOG.debug(new StringBuilder("(3Dsecure enable)veResEnrolled: ").append(veResEnrolled).toString());

                if (StringUtils.equalsIgnoreCase(PROCEED_STATUS, gatewayRecommendation)
                        && StringUtils.equalsIgnoreCase("Y", veResEnrolled)) {
                    JSONObject authenticationRedirect = ((JSONObject) ((JSONObject) secure3D.get("authenticationRedirect")).get("simple"));
                    secure3DForm = authenticationRedirect.getString("htmlBodyContent").replaceAll("\\\\", "");
                } else if (StringUtils.equalsIgnoreCase(PROCEED_STATUS, gatewayRecommendation)
                        && Arrays.asList("N", "U").stream().anyMatch(p ->
                        StringUtils.equalsIgnoreCase(veResEnrolled, p))) {
                    secure3DForm = "PAY";
                } else {
                    String lineSeparator = System.getProperty("line.separator");
                    StringBuilder eMessage = new StringBuilder("Couldn't process secureId. Payment gateway returned the following values:")
                            .append(lineSeparator).append("Gateway recommendation: ").append(gatewayRecommendation)
                            .append(lineSeparator).append("veResEnrolled: ").append(veResEnrolled);
                    this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.POST.toString(),
                            requestBody, StringUtils.EMPTY);

                }
            } else {
                StringBuilder eMessage = new StringBuilder("Couldn't process secureId. Response status code value: ");
                if (Objects.nonNull(response)) {
                    eMessage.append(response.getStatusCode());
                } else {
                    eMessage.append("null");
                }
                this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.POST.toString(),
                        requestBody, StringUtils.EMPTY);
            }
        } catch (RestClientException | JSONException e) {
            String responseBodyAsString = StringUtils.EMPTY;
            StringBuilder eMessage = new StringBuilder("Checking 3D enrollment failed, Exception from response : ");
            if (e instanceof HttpClientErrorException) {
                responseBodyAsString = ((HttpClientErrorException) e).getResponseBodyAsString();
                LOG.debug(eMessage.append(responseBodyAsString).toString());
            } else {
                LOG.debug(eMessage.append(e.getMessage()).toString());
            }
            this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.PUT.toString(),
                    requestBody, responseBodyAsString);
        }
        return secure3DForm;
    }

    /**
     * Getting gateway recommendation from JSON response
     *
     * @param responseJson
     * @return
     * @throws JSONException
     */
    private String getGatewayRecommendation(JSONObject responseJson) throws JSONException {
        if (Objects.nonNull(responseJson) && Objects.nonNull(responseJson.getJSONObject("response"))) {
            return responseJson.getJSONObject("response").getString(GATEWAY_RECOMMENDATION_KEY);
        }
        return StringUtils.EMPTY;
    }

    /**
     * submits the pay transaction
     * returns the response entity from the API
     *
     * @return
     * @throws RestClientException
     */
    @Override
    public ResponseEntity<String> payTransaction() throws RestClientException {
        Map<String, String> map = getSessionService().getAttribute(PAYMENT_MAP_ATTRIBUTE);
        String secureId3D = getSessionService().getAttribute(SECUREID3D);
        String sessionId = map.get(SESSION_ID);
        String amount = map.get(AMOUNT);
        String currency = map.get(CURRENCY);
        if (StringUtils.isNotBlank(sessionId)
                && StringUtils.isNotBlank(amount)
                && StringUtils.isNotBlank(currency)) {
            HttpHeaders headers = this.createRequestHeaders();

            String orderId = generateOrderCode();
            // transaction id is order id with prefix as orders has only one transaction in this case
            String transactionId = TRANSACTIONID_PREFIX + orderId;

            String requestBody;

            if (StringUtils.isNotBlank(secureId3D)) {
                LOG.debug("3D secure pay transaction");
                requestBody = createPayRequestBody(true, amount, currency, sessionId);
            } else {
                requestBody = createPayRequestBody(false, amount, currency, sessionId);
            }
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            String url = configurationService.getConfiguration().getString(API_URL) + URL_MERCHANT + configurationService.getConfiguration().getString(MERCHANT_ID) + "/order/" + orderId + "/transaction/" + transactionId;
            try {
                LOG.debug("sending request for pay transaction \n user: " + getCurrentUserID() + " \n requestURL:" + url + " \n requestBody: " + request);
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
                LOG.debug("received response for pay transaction user:" + getCurrentUserID() + " responseBody: " + response);
                checkPossiblePayTransactionApiErrors(requestBody, url, response);
                return response;

            } catch (RestClientException e) {
                String responseBodyAsString = ((HttpClientErrorException) e).getResponseBodyAsString();
                StringBuilder eMessage = new StringBuilder("Error executing payTransaction. ")
                        .append(e.getMessage());
                // Sending Api payment error event to process it asynchronously.
                this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.PUT.toString(), requestBody, responseBodyAsString);
                throw e;
            }
        }
        return null;
    }

    /**
     * Checks if this.payTransaction method has API errors and release events in the case of any.
     *
     * @param requestBody
     * @param url
     * @param response
     */
    private void checkPossiblePayTransactionApiErrors(String requestBody, String url, ResponseEntity<String> response) {
        // Check if response is null or response status code is not CREATED.
        if (Objects.isNull(response) || !Objects.equals(response.getStatusCode(), HttpStatus.CREATED)) {
            StringBuilder eMessage = new StringBuilder("Couldn't process pay transaction. Response status code value: ");
            String responseBodyAsString = StringUtils.EMPTY;
            if (Objects.nonNull(response)) {
                eMessage.append(response.getStatusCode());
                responseBodyAsString = response.getBody();
            } else {
                eMessage.append("null");
            }
            this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.PUT.toString(), requestBody, responseBodyAsString);
        } else {
            try {
                JSONObject responseJson = new JSONObject(response.getBody());
                final String gatewayCode = responseJson.getJSONObject("response").get("gatewayCode").toString();
                // Check if gateway code is NOT approved.
                if (!StringUtils
                        .equalsIgnoreCase("APPROVED", gatewayCode)) {
                    StringBuilder eMessage = new StringBuilder("Pay Transaction not approved, gatewayCode value: ").append(gatewayCode);
                    this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.PUT.toString(), requestBody, response.getBody());
                }
            } catch (JSONException e) {
                StringBuilder eMessage = new StringBuilder("Error executing payTransaction, while transforming json response. ")
                        .append(e.getMessage());
                this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.PUT.toString(), requestBody, response.getBody());
            }
        }
    }

    /**
     * processes the result of the 3D Secure
     * return the response if it is succesful
     * returns null if it fails
     *
     * @param paRes
     * @return
     */
    @Override
    public ResponseEntity<String> process3DSecure(String paRes) {
        HttpHeaders headers = this.createRequestHeaders();
        String requestBody = "{" +
                "\"apiOperation\" : \"PROCESS_ACS_RESULT\"," +
                "\"3DSecure\" : {" +
                "\"paRes\" :  \"" + paRes + "\"} }";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = new StringBuilder(configurationService.getConfiguration().getString(API_URL))
                .append(URL_MERCHANT)
                .append(configurationService.getConfiguration().getString(MERCHANT_ID))
                .append(URL_3DSECUREID)
                .append(get3DSecureID(false)).toString();

        LOG.debug("sending request for processing 3D Secure for \n user: " + getCurrentUserID() + " \n requestURL:" + url + " \n RequestBody: " + request);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            LOG.debug("received response for processing 3D Secure, user:" + getCurrentUserID() + " responseBody: " + response);

            JSONObject responseJson = new JSONObject(response.getBody());
            if (Objects.equals(response.getStatusCode(), HttpStatus.CREATED)) {
                JSONObject secure3D = ((JSONObject) responseJson.get(KEY_3DSECURE));
                String paResStatus = secure3D.getString(PA_RES_STATUS_KEY);
                String gatewayRecommendation = getGatewayRecommendation(responseJson);
                if (StringUtils.equalsIgnoreCase(PROCEED_STATUS, gatewayRecommendation) &&
                        Arrays.asList("Y", "U", "A").stream().anyMatch(p ->
                                StringUtils.equalsIgnoreCase(paResStatus, p))) {
                    return payTransaction();
                } else {
                    String lineSeparator = System.getProperty("line.separator");
                    StringBuilder eMessage = new StringBuilder("Couldn't process secureId. Payment gateway returned the following values:")
                            .append(lineSeparator).append("Gateway recommendation: ").append(gatewayRecommendation)
                            .append(lineSeparator).append("paResStatus: ").append(paResStatus);
                    this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.POST.toString(),
                            requestBody, StringUtils.EMPTY);

                }
            } else {
                StringBuilder eMessage = new StringBuilder("Couldn't process secureId. Response status code value: ");
                if (Objects.nonNull(response)) {
                    eMessage.append(response.getStatusCode());
                } else {
                    eMessage.append("null");
                }
                this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.POST.toString(),
                        requestBody, StringUtils.EMPTY);
            }
        } catch (RestClientException | JSONException e) {
            String responseBodyAsString = ((HttpClientErrorException) e).getResponseBodyAsString();
            StringBuilder eMessage = new StringBuilder("Error processing 3D secure id. ")
                    .append(((HttpClientErrorException) e).getMessage());
            if (LOG.isDebugEnabled()) {
                responseBodyAsString = ((HttpClientErrorException) e).getResponseBodyAsString();
                LOG.debug("Exception from response : " + responseBodyAsString);
            }
            // Sending Api payment error event to process it asynchronously.
            this.publishPaymentApiErrorEvent(url, eMessage.toString(), HttpMethod.POST.toString(), requestBody, responseBodyAsString);
        }
        return null;
    }

    private UserModel getCurrentUser() {
        return userService.getCurrentUser();
    }

    private String getCurrentUserID() {
        return this.getCurrentUser().getUid();
    }

    /**
     * updates license application with  payment transaction Id
     *
     * @param transactionId transactionId
     */
    public void sendTransactionIDToCRM(String transactionId) {
        String serviceRequestId = getSessionService().getAttribute(SAGIA_LICENSE_SERVICE_REQUEST_ID);
        try {
            paymentAssignmentService.assignPaymentInformation(serviceRequestId, PaymentAssignmentService.ServiceType.QEEMAH_2, transactionId);
        } catch (Exception e) {
            LOG.error("Transaction : " + transactionId + " was not successfully sent to CRM", e);
        }
    }

    /**
     * clears the session attributes for the payment process
     */
    public void clearPaymentSessions() {
        getSessionService().removeAttribute(TRANSACTION_ID_ATTRIBUTE);
        getSessionService().removeAttribute(PAYMENT_MAP_ATTRIBUTE);
        getSessionService().removeAttribute(SECURE3D_FORM_ATTRIBUTE);
        getSessionService().removeAttribute(SAGIA_LICENSE_SERVICE_REQUEST_ID);
    }

    /**
     * Generates a 3dSecureID for transaction, refreshes when a new check is performed
     *
     * @param reload
     * @return
     */
    private String get3DSecureID(boolean reload) {
        LOG.debug("get3DSecureID with reload: " + reload + " current session id: " + getSessionService().getCurrentSession().getSessionId());

        String secureId3D = getSessionService().getAttribute(SECUREID3D);
        
        
        if (!reload && StringUtils.isBlank(secureId3D)) {
            LOG.error("3DSecureID value not found in the session which expected to be in the session, current user: " + getCurrentUserID());
            secureId3D = getSagiaPaymentSessionByCustomer();
            //Map<String, String> paymentMap = new HashMap<>(getSessionService().getAttribute(PAYMENT_MAP_ATTRIBUTE));
            //this.eventService.publishEvent(new SagiaPaymentSessionEmailEvent((CustomerModel) this.getCurrentUser(), secureId3D, new Gson().toJson(paymentMap)));
        }
        
        if (StringUtils.isNotBlank(secureId3D) && !reload) {
            return secureId3D;
        }

        secureId3D = generateOrderCode();
        getSessionService().setAttribute(SECUREID3D, secureId3D);
        return secureId3D;
    }


    @Override
    public void publishPaymentApiErrorEvent(String url, String errorMessage, String httpMethod, String requestBody, String responseBody) {
        this.eventService.publishEvent(
                new SagiaPaymentApiErrorEvent((CustomerModel) this.getCurrentUser(), url, errorMessage,
                        httpMethod, requestBody, responseBody));
    }

    private String getSagiaPaymentSessionByCustomer() {
        String secureId3D = StringUtils.EMPTY;
        UserModel currentUser = userService.getCurrentUser();
        if (Objects.nonNull(currentUser) && currentUser instanceof CustomerModel) {
            LOG.info("Getting 3DSecureID from database for customer id: " + currentUser.getUid());
            Optional<SagiaPaymentSessionModel> sagiaPaymentSessionOpt = getSagiaPaymentSessionService().getSagiaPaymentSessionByCustomer((CustomerModel) currentUser);
            if (sagiaPaymentSessionOpt.isPresent()) {
                secureId3D = sagiaPaymentSessionOpt.get().getSecureId3D();
                getSessionService().setAttribute(SECUREID3D, secureId3D);
                // Setting payment map to have the same information retrieved from database
                Map<String, String> paymentMap = new HashMap<>();
                paymentMap = new Gson().fromJson(sagiaPaymentSessionOpt.get().getPaymentMap(), HashMap.class);
                if (Objects.nonNull(paymentMap)) {
                    getSessionService().setAttribute(PAYMENT_MAP_ATTRIBUTE, paymentMap);
                }
                if (StringUtils.isNotBlank(sagiaPaymentSessionOpt.get().getTransactionId())) {
                    getSessionService().setAttribute(TRANSACTION_ID_ATTRIBUTE, sagiaPaymentSessionOpt.get().getTransactionId());
                }
            } else {
                LOG.error("No session in database for customer id: " + currentUser.getUid());
            }
        } else {
            LOG.error("The current user is not a customer or is null: " + currentUser);
        }
        return secureId3D;
    }

    private HttpHeaders createRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        String authorization = configurationService.getConfiguration().getString(API_USER) + ":" + configurationService.getConfiguration().getString(API_PASS);
        byte[] bytes = authorization.getBytes();
        headers.add("Authorization", "Basic " + new String(Base64.encodeBase64(bytes)));
        return headers;
    }

    private String createPayRequestBody(boolean is3DSecure, String amount, String currency, String sessionId) {
        String secure3DId = get3DSecureID(false);
        String requestBody = "{" +
                "\"apiOperation\" : \"PAY\"," +
                "\"order\" : {" +
                "\"amount\" : \"" + amount + "\", \"currency\" : \"" + currency + "\"" +
                "}," +
                "\"session\" : { \"id\" : \"" + sessionId + "\"}";
        if (is3DSecure) {
            requestBody = requestBody.concat(",\"3DSecureId\" : \"" + secure3DId + "\"}");
        } else {
            requestBody = requestBody.concat("}");
        }
        return requestBody;
    }

    private String generateOrderCode() {
        final Object generatedValue = keyGenerator.generate();
        if (generatedValue instanceof String) {
            return (String) generatedValue;
        } else {
            return String.valueOf(generatedValue);
        }
    }

    /**
     * @return
     */
    public SessionService getSessionService() {
        return sessionService;
    }

    /**
     * @param sessionService
     */
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * @return
     */
    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }

    /**
     * @param keyGenerator
     */
    public void setKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public SagiaPaymentSessionService getSagiaPaymentSessionService() {
        return sagiaPaymentSessionService;
    }

    public void setSagiaPaymentSessionService(SagiaPaymentSessionService sagiaPaymentSessionService) {
        this.sagiaPaymentSessionService = sagiaPaymentSessionService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
