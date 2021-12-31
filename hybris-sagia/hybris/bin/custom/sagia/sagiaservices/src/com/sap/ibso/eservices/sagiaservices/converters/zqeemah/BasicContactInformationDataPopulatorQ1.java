package com.sap.ibso.eservices.sagiaservices.converters.zqeemah;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class BasicContactInformationDataPopulatorQ1 extends ODataPopulator<BasicContactInformationData> {
    @Override
    public void populate(ODataModel model, BasicContactInformationData basicContactInformationData) throws ConversionException {
        super.populate(model, basicContactInformationData);
        if(null != model.get("return")){
           // basicContactInformationData.setReturnProperty(String.valueOf(model.get("return")));
        }
    }
}
