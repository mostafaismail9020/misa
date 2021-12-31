package com.sap.ibso.eservices.soapservices.ws.service.impl;
import com.sap.ibso.eservices.soapservices.pojo.webcallback.CreateCallBack;
import com.sap.ibso.eservices.soapservices.pojo.webcallback.CreateCallBackResponse;
import  com.sap.ibso.eservices.soapservices.ws.service.WebCallBackService;
import org.apache.log4j.Logger;
import org.springframework.ws.client.core.WebServiceTemplate;


public class DefaultWebCallService implements WebCallBackService {
    private static final Logger LOG = Logger.getLogger(DefaultWebCallService.class);

    /**
     * The ws template.
     */
    private WebServiceTemplate wsTemplateCallBack;

    public CreateCallBackResponse createCallBack(final CreateCallBack request){
        CreateCallBackResponse response = null;
        LOG.debug("Marshalling and sending the message");
        try {
            response = (CreateCallBackResponse) wsTemplateCallBack.marshalSendAndReceive(request);
        } catch(Exception ex){
            LOG.error(ex.toString());
        }
        return response;
    }

    public WebServiceTemplate getWsTemplateCallBack() {
        return wsTemplateCallBack;
    }

    public void setWsTemplateCallBack(WebServiceTemplate wsTemplateCallBack) {
        this.wsTemplateCallBack = wsTemplateCallBack;
    }


}
