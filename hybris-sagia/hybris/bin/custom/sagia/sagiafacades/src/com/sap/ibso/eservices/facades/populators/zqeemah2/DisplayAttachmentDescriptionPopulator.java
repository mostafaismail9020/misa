package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.DisplayAttachmentDescription;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DisplayAttachmentDescriptionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class DisplayAttachmentDescriptionPopulator implements Populator<DisplayAttachmentDescriptionData, DisplayAttachmentDescription> {
    @Override
    public void populate(DisplayAttachmentDescriptionData displayAttachmentDescriptionData, DisplayAttachmentDescription displayAttachmentDescription) throws ConversionException {
        displayAttachmentDescription.setRefid(displayAttachmentDescriptionData.getRefid());
		displayAttachmentDescription.setQuestionTextEnglish(displayAttachmentDescriptionData.getQuestiontexten());
		displayAttachmentDescription.setAttachmentCode(displayAttachmentDescriptionData.getAttachcode());
		displayAttachmentDescription.setAttachmentDescriptionEnglish(displayAttachmentDescriptionData.getAttachdescen());
		displayAttachmentDescription.setAttachmentDescriptionArabic(displayAttachmentDescriptionData.getAttachdescar());
    }
}
