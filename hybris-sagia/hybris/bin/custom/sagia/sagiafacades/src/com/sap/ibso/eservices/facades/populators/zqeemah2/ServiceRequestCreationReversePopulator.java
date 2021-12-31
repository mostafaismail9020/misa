package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.Attachment;
import com.sap.ibso.eservices.facades.data.zqeemah2.ServiceRequestCreation;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.AttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ServiceRequestCreationData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceRequestCreationReversePopulator implements Populator<ServiceRequestCreation, ServiceRequestCreationData> {
    private AttachmentReversePopulator attachmentReversePopulator;

    @Override
    public void populate(ServiceRequestCreation serviceRequestCreation, ServiceRequestCreationData serviceRequestCreationData) throws ConversionException {
        serviceRequestCreationData.setServiceReqId(serviceRequestCreation.getServiceRequestId());
        serviceRequestCreationData.setZreturn(serviceRequestCreation.getReturnProperty());
        serviceRequestCreationData.setRefid(serviceRequestCreation.getRefId());
        serviceRequestCreationData.setGuid(serviceRequestCreation.getGuid());
        serviceRequestCreationData.setAdvLicNo(serviceRequestCreation.getAdvancedLicenseNumber());
		
		  Collection<AttachmentData> attachmentDataCollection = new ArrayList<>();
		  if(CollectionUtils.isNotEmpty(serviceRequestCreation.getAttachments())){ for
		  (Attachment item : serviceRequestCreation.getAttachments()){ AttachmentData
		  attachmentData = new AttachmentData();
		  attachmentReversePopulator.populate(item, attachmentData);
		  attachmentDataCollection.add(attachmentData); } }
		 
        serviceRequestCreationData.setNavAttach(attachmentDataCollection);
    }

    /**
     * @param attachmentReversePopulator
     */
    public void setAttachmentReversePopulator(AttachmentReversePopulator attachmentReversePopulator) {
        this.attachmentReversePopulator = attachmentReversePopulator;
    }
}
