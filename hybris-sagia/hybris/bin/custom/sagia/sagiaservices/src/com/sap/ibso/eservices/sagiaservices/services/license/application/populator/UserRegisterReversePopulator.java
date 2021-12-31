package com.sap.ibso.eservices.sagiaservices.services.license.application.populator;

import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.UserRegisterData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * Populates an OData model with user register data.
 */
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class UserRegisterReversePopulator extends ODataReversePopulator<UserRegisterData>
{
    @Override
    public void populate(UserRegisterData userRegisterData, ODataModel model) throws ConversionException
    {
        model.set("Userid", userRegisterData.getUserid());
        model.set("Ref_id", userRegisterData.getRef_id());
        model.set("Password", userRegisterData.getPassword());
        model.set("Email", userRegisterData.getEmail());
        model.set("Company", userRegisterData.getCompany());
        model.set("Country", userRegisterData.getCountry());
        model.set("CcodeMob", userRegisterData.getCcodeMob());
        model.set("ContNumber", userRegisterData.getContNumber());
        model.set("ContPersname", userRegisterData.getContPersname());
        model.set("Return", userRegisterData.getReturnProperty());
    }
}
