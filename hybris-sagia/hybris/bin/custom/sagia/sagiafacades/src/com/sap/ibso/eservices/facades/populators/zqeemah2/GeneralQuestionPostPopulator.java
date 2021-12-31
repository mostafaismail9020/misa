package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.GeneralQuestionPost;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.GeneralQuestionPostData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GeneralQuestionPostPopulator implements Populator<GeneralQuestionPostData, GeneralQuestionPost> {
    @Override
    public void populate(GeneralQuestionPostData generalQuestionPostData, GeneralQuestionPost generalQuestionPost) throws ConversionException {
        generalQuestionPost.setQuestionId(generalQuestionPostData.getQuestionId());
		generalQuestionPost.setAnswerKey(generalQuestionPostData.getAnswerkey());
		generalQuestionPost.setQuestionText(generalQuestionPostData.getQuestionTxt());
		generalQuestionPost.setAnswer(generalQuestionPostData.getAnswer());
    }
}
