package com.sap.ibso.eservices.sagiaservices.converters.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.AttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppServiceCreationData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.Collection;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class OppServiceCreationDataReversePopulator extends ODataReversePopulator<OppServiceCreationData> {
    @Override
    public void populate(OppServiceCreationData oppServiceCreationData, ODataModel model) throws ConversionException {
        super.populate(oppServiceCreationData, model);
        Collection<AttachmentData> attachmentData = new ArrayList<>();
        model.put("NavAttach", attachmentData);
    }
}
