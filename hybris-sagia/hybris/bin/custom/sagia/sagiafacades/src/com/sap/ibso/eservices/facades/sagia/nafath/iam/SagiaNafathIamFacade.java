package com.sap.ibso.eservices.facades.sagia.nafath.iam;

import java.lang.reflect.InvocationTargetException;

import org.apache.avalon.framework.service.ServiceException;

import com.sap.ibso.eservices.facades.nafath.iam.data.CheckRequestStatusData;
import com.sap.ibso.eservices.facades.nafath.iam.data.CheckRequestStatusResponseData;
import com.sap.ibso.eservices.facades.nafath.iam.data.SendRequestData;
import com.sap.ibso.eservices.facades.nafath.iam.data.SendRequestResponseData;

public interface SagiaNafathIamFacade {

	public SendRequestResponseData callNafathSendRequestApi(final SendRequestData requestData) throws 
																		ServiceException, IllegalAccessException, InvocationTargetException;
	
	public CheckRequestStatusResponseData callNafathCheckRequestStatusApi(final CheckRequestStatusData requestData) throws ServiceException;
}
