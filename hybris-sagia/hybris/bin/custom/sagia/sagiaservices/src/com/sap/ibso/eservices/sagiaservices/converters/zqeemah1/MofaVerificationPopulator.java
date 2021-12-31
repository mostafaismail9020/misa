package com.sap.ibso.eservices.sagiaservices.converters.zqeemah1;

import com.sap.ibso.eservices.facades.data.zqeemah.ValidateMofaNumber;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class MofaVerificationPopulator extends ODataPopulator<ValidateMofaNumber> {
	@Override
    public void populate(ODataModel model, ValidateMofaNumber mofaInfo) throws ConversionException {
        super.populate(model, mofaInfo);
        if(null != model.get("Mofano")) {
        	mofaInfo.setMofaNumber(String.valueOf(model.get("Mofano")));
        }
        if(null != model.get("Mofaname")) {
        	mofaInfo.setMofaAgentName(String.valueOf(model.get("Mofaname")));
        }
        if(null != model.get("Ismofaverify")) {
        	mofaInfo.setIsMofaVerified(String.valueOf(model.get("Ismofaverify")));
        }
        
    }
}
