package com.sap.ibso.eservices.sagiaservices.converters.odata;

import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.data.odata.ODataServiceRequestCreationData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ServiceRequestCreationData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
                                             
public class ServiceRequestCreationODataReversePopulator extends ODataReversePopulator<ODataServiceRequestCreationData> {
    @Override
      
    public void populate(ODataServiceRequestCreationData serviceRequestCreationData, ODataModel model) throws ConversionException {
        super.populate(serviceRequestCreationData, model);
        
    }
}