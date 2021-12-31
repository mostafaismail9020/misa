package com.sap.ibso.eservices.soapservices.service;
import com.sap.ibso.eservices.soapservices.WebcallbackRequestData;
import com.sap.ibso.eservices.soapservices.WebcallbackResponseData;

public interface SagiaSoapService {

    WebcallbackResponseData sendWebcallback(WebcallbackRequestData requestData);

}
