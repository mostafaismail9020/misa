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
public class ServiceRequestCreationPopulator implements Populator<ServiceRequestCreationData, ServiceRequestCreation> {
    AttachmentPopulator attachmentPopulator = new AttachmentPopulator();
    @Override
    public void populate(ServiceRequestCreationData serviceRequestCreationData, ServiceRequestCreation serviceRequestCreation) throws ConversionException {
        serviceRequestCreation.setServiceRequestId(serviceRequestCreationData.getServiceReqId());
        serviceRequestCreation.setReturnProperty(serviceRequestCreationData.getZreturn());
        serviceRequestCreation.setRefId(serviceRequestCreationData.getRefid());
        serviceRequestCreation.setGuid(serviceRequestCreationData.getGuid());
		
		  Collection<Attachment> attachments = new ArrayList<>();
		  if(CollectionUtils.isNotEmpty(serviceRequestCreationData.getNavAttach())){
		  for (AttachmentData item: serviceRequestCreationData.getNavAttach()) {
		  Attachment attachment = new Attachment(); attachmentPopulator.populate(item,
		  attachment); attachments.add(attachment); } }
		  serviceRequestCreation.setAttachments(attachments);
		 
    }

    /**
     * @param attachmentPopulator
     */
    public void setAttachmentPopulator(AttachmentPopulator attachmentPopulator) {
        this.attachmentPopulator = attachmentPopulator;
    }
}
