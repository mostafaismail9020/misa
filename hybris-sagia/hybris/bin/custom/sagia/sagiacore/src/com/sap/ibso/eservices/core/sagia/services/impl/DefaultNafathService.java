package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.enums.NafathStatus;
import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.dao.NafathDAO;
import com.sap.ibso.eservices.core.sagia.services.NafathService;
import com.sap.ibso.eservices.facades.data.NafathLoginData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DefaultNafathService implements NafathService {

    private static String NAFTAH_LOGIN_URL = Config.getString("nic.naftah.login.url","https://www.iam.sa/nafath/");
    private static final Logger log = LoggerFactory.getLogger(DefaultNafathService.class);

    private ModelService modelService;

    private NafathDAO nafathDAO;

    private static final String NAFATH_API_KEY = "09d2298c-03d0-4f9b-8265-76c039f609c0";

    @Override
    public NafathLoginModel login(String id) {
        if(Config.getString("nic.nafath.login.api.mock", "false").equals("false")){
            RestTemplate restTemplate = new RestTemplate();

            StringBuilder sb = new StringBuilder();
            sb.append("{'Action': 'SpRequest','Parameters':{'service': 'Login','id': '").append(id).append("'}}");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "ApiKey "+NAFATH_API_KEY);
            HttpEntity<String> entity = new HttpEntity<>(sb.toString(),headers);
            ResponseEntity<NafathLoginData> response = restTemplate.exchange(NAFTAH_LOGIN_URL, HttpMethod.GET, entity , NafathLoginData.class);
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                NafathLoginData data = response.getBody();
                NafathLoginModel model = createNafathLoginInstance(data);
                modelService.save(model);
                return model;
            }else {
                NafathLoginData loginData = new NafathLoginData();
                loginData.setStatus(NafathStatus.REJECTED);
                NafathLoginModel model = createNafathLoginInstance(loginData);
                return model;
            }
        }else {
            NafathLoginData testLoginData = new NafathLoginData();
            testLoginData.setStatus(NafathStatus.STARTED);
            testLoginData.setNationalId(id);
            testLoginData.setTransactionId("testTransactionId-"+ Math.random());
            testLoginData.setRandom(Math.random()+"-"+Math.random());
            NafathLoginModel model = createNafathLoginInstance(testLoginData);
            modelService.save(model);
            return model;
        }

    }

    @Override
    public boolean removeOldLoginRecords(Integer daysOld) {
        try {
            List<NafathLoginModel> nafathLogins = nafathDAO.getOldLoginRecords(daysOld);
            modelService.removeAll(nafathLogins);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public NafathLoginModel getTransactionStatus(String transactionID, String nationalID, String randomText) {
        try {
            NafathLoginModel nafathLoginModel =  nafathDAO.getLoginFromTransactionId(transactionID, nationalID, randomText);
            return nafathLoginModel;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserModel getUserModelForLicense(String license) {
       SagiaLicenseModel sagiaLicenseModel = nafathDAO.getLicense(license);
       if(sagiaLicenseModel!=null){
           return sagiaLicenseModel.getCustomer();
       }else{
           return null;
       }
    }

    @Override
    public void updateNafathLoginStatus(String transactionID, String status) {
        //TODO: please update updateNafathPostRequestServiceRequest to send the nationalID and random text too and replace them with null below
        NafathLoginModel nafathLoginModel =  nafathDAO.getLoginFromTransactionId(transactionID, null , null );
        if(nafathLoginModel != null) {
            NafathStatus nafathStatus = NafathStatus.valueOf(status);
            nafathLoginModel.setStatus(nafathStatus);
            modelService.save(nafathLoginModel);
        } else {
            log.warn("No Nafath Login found for transaction ID: [{}]", transactionID);
        }
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public NafathDAO getNafathDAO() {
        return nafathDAO;
    }

    public void setNafathDAO(NafathDAO nafathDAO) {
        this.nafathDAO = nafathDAO;
    }

    private NafathLoginModel createNafathLoginInstance(NafathLoginData data){
        NafathLoginModel newInstance = new NafathLoginModel();
        newInstance.setStatus(data.getStatus());
        newInstance.setRandom(data.getRandom());
        newInstance.setNationalId(data.getNationalId());
        newInstance.setTransactionId(data.getTransactionId());
        return newInstance;
    }

}
