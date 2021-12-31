package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtDropdown;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class AmanahPopulator extends ODataPopulator<GovtDropdown>
{
    @Override
    public void populate(ODataModel model, GovtDropdown govtDropdown) throws ConversionException {
        super.populate(model, govtDropdown);
        govtDropdown.setAmanah(model.get("Amanah").toString());
    }
}
