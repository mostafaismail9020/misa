package com.sap.ibso.eservices.sagiaservices.services.license.application.populator;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ApplicationStatusPopulator extends ODataPopulator<ApplicationStatusData>
{
    @Override
    public void populate(ODataModel model, ApplicationStatusData applicationStatusData) throws ConversionException
    {
        super.populate(model, applicationStatusData);
        // Convert "lvDate" property of type String into "lvLocalDate" property of type LocalDate
        applicationStatusData.setLvLocalDate(LocalDate.parse(applicationStatusData.getLvDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
