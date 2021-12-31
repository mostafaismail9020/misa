package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah.UserRegister;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.UserRegisterData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class UserRegisterPopulator implements Populator<UserRegisterData, UserRegister> {

    @Override
    public void populate(UserRegisterData userRegisterData, UserRegister userRegister) throws ConversionException {
        userRegister.setUserId(userRegisterData.getUserid());
        userRegister.setReferenceId(userRegisterData.getRef_id());
        userRegister.setPassword(userRegisterData.getPassword());
        userRegister.setEmail(userRegisterData.getEmail());
        userRegister.setCompany(userRegisterData.getCompany());
        userRegister.setCountry(userRegisterData.getCountry());
        userRegister.setPhonePrefix(userRegisterData.getCcodeMob());
        userRegister.setContactNumber(userRegisterData.getContNumber());
        userRegister.setContactPersonName(userRegisterData.getContPersname());
        userRegister.setReturnProperty(userRegisterData.getReturnProperty());
    }
}
