package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.ContentHDR;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ContentHDRReversePopulator implements Populator<ContentHDR, ContentHDRData> {
    /**
     * Populate from ContentHDR to ContentHDRData
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(ContentHDR source, ContentHDRData target) throws ConversionException {
        target.setObjectId(source.getObjectId());
        target.setObjectGuid(source.getObjectGuid());
        target.setFilename(source.getFilename());
        target.setFilesize(source.getFilesize());
        target.setMimetype(source.getMimetype());
        target.setDocumentId(source.getDocumentId());
        target.setContenttype(source.getContenttype());
        target.setCrtdby(source.getCrtdby());
        target.setCrtdon(source.getCrtdon());
        target.setStage(source.getStage());
        target.setDockeyid(source.getDockeyid());
        target.setShDocId(source.getShDocId());

    }
}


