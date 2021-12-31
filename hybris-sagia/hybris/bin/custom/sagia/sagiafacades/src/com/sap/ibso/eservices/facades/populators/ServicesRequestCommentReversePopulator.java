package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.ServiceRequestComment;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.TextHDRData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServicesRequestCommentReversePopulator implements Populator<ServiceRequestComment, TextHDRData> {


    @Override
    public void populate(ServiceRequestComment serviceRequestComment, TextHDRData textHDRData) throws ConversionException {
        textHDRData.setSrID(serviceRequestComment.getServiceId());
        textHDRData.setSrGuid(serviceRequestComment.getGuid());
        textHDRData.setCommentDate(serviceRequestComment.getDate());
        textHDRData.setCommentTime(serviceRequestComment.getTime());
        textHDRData.setZone(serviceRequestComment.getZone());
        textHDRData.setCommentBy(serviceRequestComment.getCommentBy());
        textHDRData.setTdID(serviceRequestComment.getId());
        textHDRData.setComments(serviceRequestComment.getComments());
        textHDRData.setStage(serviceRequestComment.getStage());
    }
}
