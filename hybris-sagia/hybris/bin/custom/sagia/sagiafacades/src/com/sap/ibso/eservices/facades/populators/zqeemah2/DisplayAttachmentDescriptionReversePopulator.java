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
public class DisplayAttachmentDescriptionReversePopulator implements Populator<DisplayAttachmentDescription, DisplayAttachmentDescriptionData> {
    @Override
    public void populate(DisplayAttachmentDescription displayAttachmentDescription, DisplayAttachmentDescriptionData displayAttachmentDescriptionData) throws ConversionException {
        displayAttachmentDescriptionData.setRefid(displayAttachmentDescription.getRefid());
		displayAttachmentDescriptionData.setQuestiontexten(displayAttachmentDescription.getQuestionTextEnglish());
		displayAttachmentDescriptionData.setAttachcode(displayAttachmentDescription.getAttachmentCode());
		displayAttachmentDescriptionData.setAttachdescen(displayAttachmentDescription.getAttachmentDescriptionEnglish());
		displayAttachmentDescriptionData.setAttachdescar(displayAttachmentDescription.getAttachmentDescriptionArabic());
    }
}
