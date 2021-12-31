package com.sap.ibso.eservices.sagiaservices.services.license.application.populator;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.UserRegisterData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class UserRegisterPopulator extends ODataPopulator<UserRegisterData>
{
    @Override
    public void populate(ODataModel model, UserRegisterData userRegisterData) throws ConversionException
    {
        super.populate(model, userRegisterData);
        // Map "Return" property (as it is a Java keyword) to "returnProperty"
        userRegisterData.setReturnProperty((String) model.get("Return"));
    }
}
