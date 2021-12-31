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
public class UserRegisterReversePopulator implements Populator<UserRegisterData, UserRegister> {

    @Override
    public void populate(UserRegisterData userRegisterData, UserRegister userRegister) throws ConversionException {
        userRegisterData.setUserid(userRegister.getUserId());
        userRegisterData.setRef_id(userRegister.getReferenceId());
        userRegisterData.setPassword(userRegister.getPassword());
        userRegisterData.setEmail(userRegister.getEmail());
        userRegisterData.setCompany(userRegister.getCompany());
        userRegisterData.setCountry(userRegister.getCountry());
        userRegisterData.setCcodeMob(userRegister.getPhonePrefix());
        userRegisterData.setContNumber(userRegister.getContactNumber());
        userRegisterData.setContPersname(userRegister.getContactPersonName());
        userRegisterData.setReturnProperty(userRegister.getReturnProperty());
    }
}
