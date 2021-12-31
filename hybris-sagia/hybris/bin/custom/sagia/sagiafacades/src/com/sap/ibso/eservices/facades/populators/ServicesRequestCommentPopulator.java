package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.ServiceRequestComment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TextHDRData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServicesRequestCommentPopulator implements Populator<TextHDRData, ServiceRequestComment> {

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(TextHDRData textHDRData, ServiceRequestComment serviceRequestComment) throws ConversionException {
        serviceRequestComment.setDateData(sagiaFormatProvider.getLocalizedDateData(textHDRData.getCommentDate()));

        serviceRequestComment.setServiceId(textHDRData.getSrID());
        serviceRequestComment.setGuid(textHDRData.getSrGuid());
        serviceRequestComment.setDate(textHDRData.getCommentDate());
        serviceRequestComment.setTime(textHDRData.getCommentTime());
        serviceRequestComment.setZone(textHDRData.getZone());
        serviceRequestComment.setCommentBy(textHDRData.getCommentBy());
        serviceRequestComment.setId(textHDRData.getTdID());
        serviceRequestComment.setComments(textHDRData.getComments());
        serviceRequestComment.setStage(textHDRData.getStage());
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider()
    {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider)
    {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
