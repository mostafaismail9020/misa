package com.sap.ibso.eservices.facades.sagia.nafath.iam.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.commons.beanutils.BeanUtils;

import com.investsaudi.portal.iam.request.CheckRequestStatusRequest;
import com.investsaudi.portal.iam.request.SendRequest;
import com.investsaudi.portal.iam.response.CheckRequestStatusResponse;
import com.investsaudi.portal.iam.response.SendRequestResponse;
import com.investsaudi.portal.iam.service.SagiaNafathIamService;
import com.sap.ibso.eservices.facades.nafath.iam.data.CheckRequestStatusData;
import com.sap.ibso.eservices.facades.nafath.iam.data.CheckRequestStatusResponseData;
import com.sap.ibso.eservices.facades.nafath.iam.data.SendRequestData;
import com.sap.ibso.eservices.facades.nafath.iam.data.SendRequestParametersData;
import com.sap.ibso.eservices.facades.nafath.iam.data.SendRequestResponseData;
import com.sap.ibso.eservices.facades.sagia.nafath.iam.SagiaNafathIamFacade;

public class SagiaNafathIamFacadeImpl implements SagiaNafathIamFacade {

	@Resource
	private SagiaNafathIamService<SendRequest, SendRequestResponse> sagiaNafathIamSendRequestServiceImpl;
	
	@Override
	public SendRequestResponseData callNafathSendRequestApi(SendRequestData requestData) throws ServiceException, IllegalAccessException, InvocationTargetException {
		requestData = new SendRequestData();
		SendRequest sendRequest = new SendRequest();
		SendRequestResponseData responseData = new SendRequestResponseData();
		SendRequestParametersData parameters = new SendRequestParametersData();
		parameters.setId("1000003366");
		parameters.setService("Login");
		requestData.setAction("SpRequest");
		requestData.setParameters(parameters);
		BeanUtils.copyProperties(sendRequest, requestData);
		SendRequestResponse response = sagiaNafathIamSendRequestServiceImpl.process(sendRequest);
		BeanUtils.copyProperties(responseData, response);
		return responseData;
	}

	@Override
	public CheckRequestStatusResponseData callNafathCheckRequestStatusApi(CheckRequestStatusData requestData)
			throws ServiceException {

		final CheckRequestStatusRequest checkRequestStatus = new CheckRequestStatusRequest();
		final CheckRequestStatusResponseData responseData = new CheckRequestStatusResponseData();
		CheckRequestStatusResponse response = sagiaNafathIamSendRequestServiceImpl.process(checkRequestStatus);
		
		BeanUtils.copyProperties(responseData, response);
		return responseData;
	}

}
