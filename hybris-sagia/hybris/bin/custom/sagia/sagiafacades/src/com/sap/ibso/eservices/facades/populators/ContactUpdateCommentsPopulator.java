package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.license.replace.ContactUpdateMessages;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContactUpdateMessagesData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ContactUpdateCommentsPopulator implements Populator<ContactUpdateMessagesData, ContactUpdateMessages> {
    @Override
    public void populate(ContactUpdateMessagesData source, ContactUpdateMessages target) throws ConversionException {
        target.setSrID(source.getSrID());
        target.setSrGuid(source.getSrGuid());
        target.setCommentDate(source.getCommentDate());
        target.setCommentTime(source.getCommentTime());
        target.setZone(source.getZone());
        target.setCommentBy(source.getCommentBy());
        target.setTdID(source.getTdID());
        target.setComments(source.getComments());
        target.setStage(source.getStage());
    }
}
