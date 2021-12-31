package com.sap.ibso.eservices.facades.populators.odata;

import com.sap.ibso.eservices.facades.data.odata.ServiceRequestCreation;
import com.sap.ibso.eservices.sagiaservices.data.odata.ODataServiceRequestCreationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceRequestCreationReverseODataPopulator implements Populator<ServiceRequestCreation, ODataServiceRequestCreationData> {
   
    @Override
    public void populate(ServiceRequestCreation serviceRequestCreation, ODataServiceRequestCreationData serviceRequestCreationData) throws ConversionException {
        serviceRequestCreationData.setObjectid(serviceRequestCreation.getObjectid());
        serviceRequestCreationData.setReturn(serviceRequestCreation.getReturn());
        serviceRequestCreationData.setRefid(serviceRequestCreation.getRefid());
        serviceRequestCreationData.setGuid(serviceRequestCreation.getGuid());
        serviceRequestCreationData.setAdvlicno(serviceRequestCreation.getAdvlicno());
       
    }

    
}
