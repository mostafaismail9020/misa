package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceApplicantData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceApplicantDataPopulator extends ODataPopulator<ServiceApplicantData> {
    @Override
    public void populate(ODataModel model, ServiceApplicantData serviceApplicantData) throws ConversionException {
        super.populate(model, serviceApplicantData, SagiaPropertyNamingStrategy.UPPER_SNAKE_CASE);
    }
}
