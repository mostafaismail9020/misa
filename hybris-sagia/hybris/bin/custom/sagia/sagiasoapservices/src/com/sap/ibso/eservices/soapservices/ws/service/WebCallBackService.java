package com.sap.ibso.eservices.soapservices.ws.service;
import com.sap.ibso.eservices.soapservices.pojo.webcallback.CreateCallBack;
import com.sap.ibso.eservices.soapservices.pojo.webcallback.CreateCallBackResponse;


public interface WebCallBackService {
    /**
     * Create callback
     *  * Created by Iulian Satala on 10/17/2018.
     *
     */
    public CreateCallBackResponse createCallBack(CreateCallBack response);


}
