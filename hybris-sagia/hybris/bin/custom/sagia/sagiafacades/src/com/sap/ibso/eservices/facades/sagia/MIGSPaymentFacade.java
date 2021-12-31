package com.sap.ibso.eservices.facades.sagia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.net.MalformedURLException;

/**
 * Provides access to MIGSPaymentFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface MIGSPaymentFacade {

    /**
     * pay Transaction
     * @return processing response
     * @throws RestClientException exception
     */
    ResponseEntity<String> payTransaction() throws RestClientException;

    /**
     * check 3DSecure Enrollment
     * @param sessionId sessionId
     * @param amount amount
     * @param currency currency
     * @param url url
     * @return processing response
     * @throws MalformedURLException exception
     */
    String check3DSecureEnrollment(String sessionId,String amount, String currency, String url) throws MalformedURLException;

    /**
     * process 3DSecure
     * @param pares pares
     * @return processing response
     */
    ResponseEntity<String> process3DSecure(String pares);

    /**
     * clear PaymentSessions
     */
    void clearPaymentSessions();

    /**
     * send TransactionID To CRM
     * @param transactionId transactionId
     */
    void sendTransactionIDToCRM(String transactionId);

    /**
     * Method to publish errors in the payment Api to save them in the database asynchronously.
     *
     * @param url
     * @param errorMessage
     * @param httpMethod
     * @param requestBody
     * @param responseBody
     */
    void publishPaymentApiErrorEvent(String url, String errorMessage, String httpMethod, String requestBody, String responseBody);
}
