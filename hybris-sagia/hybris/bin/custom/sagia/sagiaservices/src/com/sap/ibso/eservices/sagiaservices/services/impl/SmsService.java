package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionRequest;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionResponse;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.io.IOException;
import java.net.URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * SmsService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SmsService {
    private static final Logger LOG = LoggerFactory.getLogger(SmsService.class);

    private HttpURLConnectionService httpURLConnectionService;
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    private static final String REQUEST_TYPE_GET = "GET";

    /**
     * sends sms
     * @param mobileNumber mobileNumber
     * @param code code
     * @throws IOException exception
     */
    public void send(String mobileNumber, String code) throws IOException {
        String urlString = new StringBuilder(sagiaConfigurationFacade.getSmsServiceUrl())
                .append("username=").append(sagiaConfigurationFacade.getSmsServiceUser())
                .append("&password=").append(sagiaConfigurationFacade.getSmsServicePassword())
                .append("&Sender=").append("MISA")
                .append("&Text=").append("Your%20OTP%20code%20is%3A").append(code)
                .append("%20to%20login%20Investor%20Eservices")
                .append("&number=").append(mobileNumber).toString();
                
                
//        HttpURLConnectionResponse httpURLConnectionResponse =
//                httpURLConnectionService.execute(new HttpURLConnectionRequest(REQUEST_TYPE_GET, new URL(url)));
//        String response = new String(httpURLConnectionResponse.getPayload());
//        LOG.info("response from SMS={}", response);
//        if(!response.contains("OK") || !response.contains("Accepted")
//                || httpURLConnectionResponse.getResponseCode() != 200) {
//            throw new IOException("SMS Could not be sent");
//        }
        LOG.info("The SMS URL formed for Phone number " +mobileNumber+ " is "+ urlString);
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
       
        // By default it is GET request
        con.setRequestMethod(REQUEST_TYPE_GET);
       
        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
       
        int responseCode = con.getResponseCode();
        LOG.info("Response code : "+ responseCode);
        if(responseCode != 200) {
    	  	throw new IOException("SMS Could not be sent");
  		}
       
        // Reading response from input Stream
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String output;
//        StringBuffer response = new StringBuffer();
//       
//        while ((output = in.readLine()) != null) {
//         response.append(output);
//        }
//        in.close();
//       
//        //printing result from response
//        LOG.info(response.toString());
    }
    
    /**
     * sends sms
     * @param mobileNumber mobileNumber
     * @param code code
     * @throws IOException exception
     */
    public void sendSmsInvestSaudi(String mobileNumber, String code) throws IOException {
        String urlString = new StringBuilder(sagiaConfigurationFacade.getSmsServiceUrl())
                .append("username=").append(sagiaConfigurationFacade.getSmsServiceUser())
                .append("&password=").append(sagiaConfigurationFacade.getSmsServicePassword())
                .append("&Sender=").append("MISA")
                .append("&Text=").append("Your%20OTP%20code%20is%3A").append(code)
                .append("%20to%20login%20Invest%20Saudi")
                .append("&number=").append(mobileNumber).toString();
                
        LOG.info("The SMS URL formed for Phone number " +mobileNumber+ " is "+ urlString);
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
       
        // By default it is GET request
        con.setRequestMethod(REQUEST_TYPE_GET);
       
        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
       
        int responseCode = con.getResponseCode();
        LOG.info("Response code : "+ responseCode);
        if(responseCode != 200) {
    	  	throw new IOException("SMS Could not be sent");
  		}
       
    }

    /**
     * @param httpURLConnectionService httpURLConnectionService
     */
    @Required
    public void setHttpURLConnectionService(HttpURLConnectionService httpURLConnectionService) {
        this.httpURLConnectionService = httpURLConnectionService;
    }

    /**
     * @param sagiaConfigurationFacade sagiaConfigurationFacade
     */
    @Required
    public void setSagiaConfigurationFacade(SagiaConfigurationFacade sagiaConfigurationFacade) {
        this.sagiaConfigurationFacade = sagiaConfigurationFacade;
    }
}
