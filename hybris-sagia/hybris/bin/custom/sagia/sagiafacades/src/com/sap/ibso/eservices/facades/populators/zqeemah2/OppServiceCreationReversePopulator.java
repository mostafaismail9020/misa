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
public class OppServiceCreationReversePopulator implements Populator<OppServiceCreation, OppServiceCreationData> {
    private AttachmentReversePopulator attachmentReversePopulator;

    @Override
    public void populate(OppServiceCreation oppServiceCreation, OppServiceCreationData oppServiceCreationData) throws ConversionException {
        oppServiceCreationData.setOppid(oppServiceCreation.getOppId());
        oppServiceCreationData.setZreturn(oppServiceCreation.getReturnProperty());
        oppServiceCreationData.setRefid(oppServiceCreation.getRefId());
        oppServiceCreationData.setGuid(oppServiceCreation.getGuid());
        oppServiceCreationData.setAdvLicNo(oppServiceCreation.getAdvancedLicenseNumber());
        Collection<AttachmentData> attachmentDataCollection = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(oppServiceCreation.getAttachments())){
            for (Attachment item : oppServiceCreation.getAttachments()){
                AttachmentData attachmentData = new AttachmentData();
                attachmentReversePopulator.populate(item, attachmentData);
                attachmentDataCollection.add(attachmentData);
            }
        }
        oppServiceCreationData.setNavAttach(attachmentDataCollection);
    }

    /**
     * @param attachmentReversePopulator
     */
    public void setAttachmentReversePopulator(AttachmentReversePopulator attachmentReversePopulator) {
        this.attachmentReversePopulator = attachmentReversePopulator;
    }
}
