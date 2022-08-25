package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.enums.NafathStatus;
import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.core.sagia.services.NafathService;
import com.sap.ibso.eservices.facades.data.NafathLoginData;
import com.sap.ibso.eservices.facades.sagia.NafathFacade;
import de.hybris.platform.cmsfacades.data.UserData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class DefaultNafathFacade implements NafathFacade {

    private NafathService nafathService;

    private Converter<NafathLoginModel, NafathLoginData> nafathLoginConverter;

    private Converter<UserModel, UserData> cmsUserModelToDataConverter;


    @Override
    public NafathLoginData login(String nationalID) {
        return getNafathLoginConverter().convert(nafathService.login(nationalID));
    }

    @Override
    public NafathLoginData checkStatus(String transactionID) {
        return getNafathLoginConverter().convert(nafathService.getTransactionStatus(transactionID));
    }

    @Override
    public UserData getUserForLicense(String license) {
        return getCmsUserModelToDataConverter().convert(nafathService.getUserModelForLicense(license));
    }

    public NafathService getNafathService() {
        return nafathService;
    }

    public void setNafathService(NafathService nafathService) {
        this.nafathService = nafathService;
    }

    public Converter<NafathLoginModel, NafathLoginData> getNafathLoginConverter() {
        return nafathLoginConverter;
    }

    public void setNafathLoginConverter(Converter<NafathLoginModel, NafathLoginData> nafathLoginConverter) {
        this.nafathLoginConverter = nafathLoginConverter;
    }

    public Converter<UserModel, UserData> getCmsUserModelToDataConverter() {
        return cmsUserModelToDataConverter;
    }

    public void setCmsUserModelToDataConverter(Converter<UserModel, UserData> cmsUserModelToDataConverter) {
        this.cmsUserModelToDataConverter = cmsUserModelToDataConverter;
    }
}
