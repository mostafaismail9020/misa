package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.Attachment;
import com.sap.ibso.eservices.facades.data.zqeemah2.OppServiceCreation;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.AttachmentData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppServiceCreationData;
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
public class OppServiceCreationPopulator implements Populator<OppServiceCreationData, OppServiceCreation> {
    AttachmentPopulator attachmentPopulator = new AttachmentPopulator();
    @Override
    public void populate(OppServiceCreationData oppServiceCreationData, OppServiceCreation oppServiceCreation) throws ConversionException {
        oppServiceCreation.setOppId(oppServiceCreationData.getOppid());
        oppServiceCreation.setReturnProperty(oppServiceCreationData.getZreturn());
        oppServiceCreation.setRefId(oppServiceCreationData.getRefid());
        oppServiceCreation.setGuid(oppServiceCreationData.getGuid());
        Collection<Attachment> attachments = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(oppServiceCreationData.getNavAttach())){
            for (AttachmentData item: oppServiceCreationData.getNavAttach()) {
                Attachment attachment = new Attachment();
                attachmentPopulator.populate(item, attachment);
                attachments.add(attachment);
            }
        }
        oppServiceCreation.setAttachments(attachments);
    }

    /**
     * @param attachmentPopulator
     */
    public void setAttachmentPopulator(AttachmentPopulator attachmentPopulator) {
        this.attachmentPopulator = attachmentPopulator;
    }
}
