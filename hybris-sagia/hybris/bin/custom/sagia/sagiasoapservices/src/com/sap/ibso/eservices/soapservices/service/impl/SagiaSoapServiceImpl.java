package com.sap.ibso.eservices.soapservices.service.impl;
import com.sap.ibso.eservices.soapservices.WebcallbackRequestData;
import com.sap.ibso.eservices.soapservices.WebcallbackResponseData;
import com.sap.ibso.eservices.soapservices.service.SagiaSoapService;
import com.sap.ibso.eservices.soapservices.pojo.webcallback.CreateCallBackResponse;
import com.sap.ibso.eservices.soapservices.ws.service.WebCallBackService;
import com.sap.ibso.eservices.soapservices.pojo.webcallback.CreateCallBack;
import org.apache.log4j.Logger;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceException;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class SagiaSoapServiceImpl implements SagiaSoapService {
    final private static Logger LOG = Logger.getLogger(SagiaSoapServiceImpl.class);

    final private static String WEBSERVICE_DOWN = "Request can not be processed at the moment. Webservice unavailable";
    private WebCallBackService webCallBackService;

    private Jaxb2Marshaller marshaller;

    @Override
    public WebcallbackResponseData sendWebcallback(WebcallbackRequestData requestData){
        CreateCallBackResponse response = null;
        try{
            response = getWebCallBackService().createCallBack(createCallBackRequest(requestData));
        } catch (WebServiceException e) {
            LOG.error(e);
        }

        final WebcallbackResponseData result = new WebcallbackResponseData();
        if(response != null) {
            if(response.getResponse() != null) {
                result.setResponse(response.getResponse());
            } else {
                final String responseAsString = getResponseAsString(response);
                result.setResponse(responseAsString);
                LOG.info(responseAsString);
            }
        } else {
            LOG.error(WEBSERVICE_DOWN);
            return null;
        }
        return result;
    }

    private CreateCallBack createCallBackRequest(WebcallbackRequestData requestData){
        CreateCallBack createCallBack = new CreateCallBack();
		LOG.info("#########Webcallback request 2####### Number: "+requestData.getNumber()+" **** CallTime: "+requestData.getCallTime()+" ^^^^^^ Queue: "+requestData.getQueue()+" **** Notes: "+requestData.getNotes());
        createCallBack.setNumber(requestData.getNumber());
        createCallBack.setCallTime(requestData.getCallTime());
        createCallBack.setQueue(requestData.getQueue());
        createCallBack.setNotes(requestData.getNotes());
        return createCallBack;
    }

    private String getResponseAsString(final CreateCallBackResponse response) {
        final StringWriter stringWriter = new StringWriter();
        final StreamResult streamResult = new StreamResult(stringWriter);
        getMarshaller().marshal(response, streamResult);

        return stringWriter.toString();
    }

    public WebCallBackService getWebCallBackService() {
        return webCallBackService;
    }

    public void setWebCallBackService(WebCallBackService webCallBackService) {
        this.webCallBackService = webCallBackService;
    }

    public Jaxb2Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }


}
