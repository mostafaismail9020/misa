package com.sap.ibso.eservices.sagiaservices.converters.odata;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.odata.BrandPresenceInMENARegion;
import com.sap.ibso.eservices.sagiaservices.data.odata.EntitiesManagedByRhq;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class BrandPresenceInMENARegionODataPopulator extends ODataPopulator<BrandPresenceInMENARegion> {
    @Override
    public void populate(ODataModel model, BrandPresenceInMENARegion brandPresence) throws ConversionException {
        super.populate(model, brandPresence);
        if(null != model.get("return")){
           // basicContactInformationData.setReturnProperty(String.valueOf(model.get("return")));
        }
    }
}
