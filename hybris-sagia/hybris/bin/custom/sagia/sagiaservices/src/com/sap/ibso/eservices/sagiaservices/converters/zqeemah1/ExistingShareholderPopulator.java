package com.sap.ibso.eservices.sagiaservices.converters.zqeemah1;

import com.sap.ibso.eservices.facades.data.zqeemah.ValidateSareholder;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ExistingShareholderPopulator extends ODataPopulator<ValidateSareholder> {
    @Override
    public void populate(ODataModel model, ValidateSareholder shareholderInfo) throws ConversionException {
        super.populate(model, shareholderInfo);
        if(null != model.get("Bpno")) {
            shareholderInfo.setBpNumber(String.valueOf(model.get("Bpno")));
        }
        if(null != model.get("Bpname")) {
            shareholderInfo.setBpName(String.valueOf(model.get("Bpname")));
        }
        if(null != model.get("Return")) {
            shareholderInfo.setReturnProperty(String.valueOf(model.get("Return")));
        }
        if(null != model.get("Companycountry")){
            shareholderInfo.setCompanyCountry(String.valueOf(model.get("Companycountry")));
        }
        if(null != model.get("BpType")) {
            shareholderInfo.setBpType(String.valueOf(model.get("BpType")));
        }
    }
}
