package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtServiceUploadData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GovtServiceSupportingDocumentsPopulator extends ODataPopulator<GovtServiceUploadData> {
    @Override
    public void populate(ODataModel model, GovtServiceUploadData uploadData) throws ConversionException {
        super.populate(model, uploadData);
        uploadData.setDockeyID(String.valueOf(model.get("Dockey_ID")));
    }
}

