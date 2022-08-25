package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.dao.NafathDAO;
import com.sap.ibso.eservices.core.sagia.services.NafathService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DefaultNafathService implements NafathService {

    private static String NAFTAH_LOGIN_URL = Config.getString("nic.naftah.login.url","https://www.iam.sa/nafath/");
    private ModelService modelService;

    private NafathDAO nafathDAO;

    @Override
    public NafathLoginModel login(String id) {
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder sb = new StringBuilder();
        sb.append("{'Action': 'SpRequest','Parameters':{'service': 'Login','id': '").append(id).append("'}}");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(sb.toString(),headers);
        NafathLoginModel nafathLoginModel = restTemplate.postForObject(NAFTAH_LOGIN_URL, entity , NafathLoginModel.class);
        modelService.save(nafathLoginModel);
        return nafathLoginModel;
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
    public NafathLoginModel getTransactionStatus(String transactionID) {
        try {
            NafathLoginModel nafathLoginModel =  nafathDAO.getLoginFromTransactionId(transactionID);
            return nafathLoginModel;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserModel getUserModelForLicense(String license) {
       SagiaLicenseModel sagiaLicenseModel = nafathDAO.getUserAssociatedWithLicense(license);
       if(sagiaLicenseModel!=null){
           return sagiaLicenseModel.getCustomer();
       }else{
           return null;
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
}
