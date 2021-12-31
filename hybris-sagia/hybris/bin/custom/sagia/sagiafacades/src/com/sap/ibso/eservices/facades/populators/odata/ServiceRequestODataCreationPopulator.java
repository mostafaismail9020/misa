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
public class ServiceRequestODataCreationPopulator implements Populator<ODataServiceRequestCreationData, ServiceRequestCreation> {
  
    @Override
    public void populate(ODataServiceRequestCreationData serviceRequestCreationData, ServiceRequestCreation serviceRequestCreation) throws ConversionException {
        serviceRequestCreation.setObjectid(serviceRequestCreationData.getObjectid());
        serviceRequestCreation.setReturn(serviceRequestCreationData.getReturn());
        serviceRequestCreation.setRefid(serviceRequestCreationData.getRefid());
        serviceRequestCreation.setGuid(serviceRequestCreationData.getGuid());
        serviceRequestCreation.setAdvlicno(serviceRequestCreationData.getAdvlicno());
        
       
    }

  
}
