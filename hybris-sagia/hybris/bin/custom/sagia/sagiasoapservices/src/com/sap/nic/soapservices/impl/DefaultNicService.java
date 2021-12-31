package com.sap.nic.soapservices.impl;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.axis2.java.security.TrustAllTrustManager;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.security.SSLProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.log4j.Logger;

import com.sap.nic.NICUserData;
import com.sap.nic.client.NICService.APIStub;
import com.sap.nic.client.NICService.APIStub.GetInvestorInfo;
import com.sap.nic.client.NICService.APIStub.GetInvestorInfoResponse;
import com.sap.nic.client.NICService.APIStub.NIC_Request;
import com.sap.nic.client.NICService.APIStub.NIC_Respnse;
import com.sap.nic.soapservices.NicService;

import de.hybris.platform.util.Config;

public class DefaultNicService implements NicService {

	private static final Logger LOG = Logger.getLogger(DefaultNicService.class);
	public static final String NIC_URL = Config.getString("nic.webservice.url", "https://rhoysrdev1.mysagia.gov:443/NIC_GetService/API.asmx");
	 
	public NICUserData getUserDetailsFromNIC(int idType, String idNumber, String dob){
		
			try {
				
				 SSLContext sslCtx = SSLContext.getInstance("TLS");
				 sslCtx.init(null, new TrustManager[] {new TrustAllTrustManager()}, null);
				APIStub client = new APIStub(NIC_URL);
				client._getServiceClient().getOptions().setProperty(
						HTTPConstants.CUSTOM_PROTOCOL_HANDLER,
						new Protocol("https", (ProtocolSocketFactory)new SSLProtocolSocketFactory(sslCtx), 443));
				
				GetInvestorInfo investorInfo = new GetInvestorInfo();
				NIC_Request request = new NIC_Request();
				request.setID_Type(idType);
				request.setID_Number(idNumber);
				request.setDOB(dob);
				investorInfo.setRequest(request);
				LOG.info("***** NIC Request 1: idType: "+ idType+" ^^^idNumber:"+idNumber+" @@@dob:"+dob+"***");				
				//investorInfo.
				GetInvestorInfoResponse response = client.getInvestorInfo(investorInfo);
				if(response != null && response.getGetInvestorInfoResult() != null)
				{
					NIC_Respnse nicResponse = response.getGetInvestorInfoResult();
					LOG.debug(nicResponse.getResult());
					LOG.debug(nicResponse.getExpiryDate());
					LOG.debug(nicResponse.getFirstName_AR());
					LOG.debug(nicResponse.getThirdName_EN());
					LOG.debug(nicResponse.getGender());
					LOG.debug(nicResponse.getLastName_AR());
					return populateNICUserData(response.getGetInvestorInfoResult());
				}
			} catch (Exception e) {
				LOG.debug("NIC_ERROR: ", e);
			}
		return null;
	}

	private NICUserData populateNICUserData(NIC_Respnse nicResponse) {
		NICUserData nicUserData = new NICUserData();
		nicUserData.setResult(nicResponse.getResult());
		nicUserData.setExpiryDate(nicResponse.getExpiryDate());
		
		String firstNameAR = " ";
		String secondNameAR = " ";
		String thirdNameAR = " " ;
		String lastNameAR = " ";
		
		if (nicResponse.getFirstName_AR()!= null && !"".equals(nicResponse.getFirstName_AR()) && !"null".equals(nicResponse.getFirstName_AR())) {
			firstNameAR = nicResponse.getFirstName_AR() ; 
		}
		if (nicResponse.getSecondName_AR()!= null && !"".equals(nicResponse.getSecondName_AR()) && !"null".equals(nicResponse.getSecondName_AR())) {
			secondNameAR = nicResponse.getSecondName_AR() ; 
		}
		if (nicResponse.getThirdName_AR()!= null && !"".equals(nicResponse.getThirdName_AR()) && !"null".equals(nicResponse.getThirdName_AR())) {
			thirdNameAR = nicResponse.getThirdName_AR() ; 
		}
		if (nicResponse.getLastName_AR()!= null && !"".equals(nicResponse.getLastName_AR()) && !"null".equals(nicResponse.getLastName_AR())) {
			lastNameAR = nicResponse.getLastName_AR() ; 
		}
		
		nicUserData.setFirstName_AR(firstNameAR + " "+secondNameAR);
		nicUserData.setSecondName_AR(secondNameAR);
		nicUserData.setLastName_AR(thirdNameAR + " "+lastNameAR);
		nicUserData.setFirstName_EN(nicResponse.getFirstName_EN());
		nicUserData.setSecondName_EN(nicResponse.getSecondName_EN());
		nicUserData.setFullName_EN(nicResponse.getFirstName_EN()+ " "+nicResponse.getSecondName_EN()+" "+nicResponse.getThirdName_EN()+" "+nicResponse.getLastName_EN());
		nicUserData.setPremium_Residency(nicResponse.getPremium_Residency());
		nicUserData.setGender(nicResponse.getGender());
		nicUserData.setIssueDate(nicResponse.getIssueDate());
		return nicUserData;
	}

}
