package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovDocWasselCheck;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GovDocWasselCheckPopulator extends ODataPopulator<SagiaGovDocWasselCheck> {

    /**
     *  Populates the SagiaGovDocWasselCheck POJO with data from CRM
     *
     * @param model - OData Model(wrapper for a map) that holds the response entity from the CRM
     * @param sagiaGovDocWasselCheck - POJO for populating the data from the model
     * @param propertyNamingStrategy - The naming strategy of populator
     * @throws ConversionException
     */
    @Override
    public void populate(ODataModel model, SagiaGovDocWasselCheck sagiaGovDocWasselCheck, PropertyNamingStrategy propertyNamingStrategy) throws ConversionException {
        super.populate(model, sagiaGovDocWasselCheck, SagiaPropertyNamingStrategy.ALL_TO_UPPER_CASE);
    }
}
