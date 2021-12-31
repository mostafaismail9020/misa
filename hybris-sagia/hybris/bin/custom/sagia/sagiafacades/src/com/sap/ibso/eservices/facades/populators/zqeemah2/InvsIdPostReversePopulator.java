package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.GeneralQuestionPost;
import com.sap.ibso.eservices.facades.data.zqeemah2.InvsIdPost;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.GeneralQuestionPostData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.InvsIdPostData;
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
public class InvsIdPostReversePopulator implements Populator<InvsIdPost, InvsIdPostData> {
    @Override
    public void populate(InvsIdPost invsIdPost, InvsIdPostData invsIdPostData) throws ConversionException {
        invsIdPostData.setInvestorid(invsIdPost.getInvestorid());
        Collection<GeneralQuestionPostData> generalQuestionPosts = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(invsIdPost.getGeneralQuestionPosts())){
            for(GeneralQuestionPost question : invsIdPost.getGeneralQuestionPosts()){
                GeneralQuestionPostData generalQuestionPostData = new GeneralQuestionPostData();
                generalQuestionPostData.setQuestionId(question.getQuestionId());
                generalQuestionPostData.setQuestionTxt(question.getQuestionText());
                generalQuestionPostData.setAnswer(question.getAnswer());
                generalQuestionPostData.setAnswerkey(question.getAnswerKey());
                generalQuestionPosts.add(generalQuestionPostData);
            }
        }
        invsIdPostData.setNavGeqPost(generalQuestionPosts);
    }
}
