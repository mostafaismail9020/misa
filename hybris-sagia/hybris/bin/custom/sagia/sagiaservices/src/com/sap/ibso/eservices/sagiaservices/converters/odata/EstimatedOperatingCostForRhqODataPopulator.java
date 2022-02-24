package com.sap.ibso.eservices.sagiaservices.converters.odata;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.odata.BrandPresenceInMENARegion;
import com.sap.ibso.eservices.sagiaservices.data.odata.EstimatedOperatingCostForRhq;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class EstimatedOperatingCostForRhqODataPopulator extends ODataPopulator<EstimatedOperatingCostForRhq> {
    @Override
    public void populate(ODataModel model, EstimatedOperatingCostForRhq estimatedCost) throws ConversionException {
        super.populate(model, estimatedCost);
        if(null != model.get("return")){
           // basicContactInformationData.setReturnProperty(String.valueOf(model.get("return")));
        }
    }
}
