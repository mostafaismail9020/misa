package com.sap.ibso.eservices.sagiaservices.converters.odata;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.odata.IsicInfoData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class IsicInformationODataPopulator extends ODataPopulator<IsicInfoData> {
    @Override
    public void populate(ODataModel model, IsicInfoData isicInfoData) throws ConversionException {
        super.populate(model, isicInfoData);
        if(null != model.get("return")){
           // basicContactInformationData.setReturnProperty(String.valueOf(model.get("return")));
        }
    }
}
