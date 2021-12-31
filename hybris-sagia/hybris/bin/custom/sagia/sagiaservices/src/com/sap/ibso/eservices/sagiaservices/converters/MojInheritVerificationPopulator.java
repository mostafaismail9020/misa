package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import com.sap.ibso.eservices.sagiaservices.data.moj.MOJInheritSetData;

public class MojInheritVerificationPopulator extends ODataPopulator<MOJInheritSetData> {

	@Override
    public void populate(ODataModel model, MOJInheritSetData mojInfo) throws ConversionException {
        super.populate(model, mojInfo);
        if(null != model.get("DeceasedId")) {
        	mojInfo.setDeceasedId(String.valueOf(model.get("DeceasedId")));
        }
        if(null != model.get("DeedNumber")) {
        	mojInfo.setDeedNumber(String.valueOf(model.get("DeedNumber")));
        }
        if(null != model.get("DeceasedName")) {
        	mojInfo.setDeceasedName(String.valueOf(model.get("DeceasedName")));
        }
        if(null != model.get("IsMojVerified")) {
        	mojInfo.setIsMojVerified(String.valueOf(model.get("IsMojVerified")));
        }
        
    }
}
