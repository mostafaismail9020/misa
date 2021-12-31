package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.FinancialPost;
import com.sap.ibso.eservices.facades.data.zqeemah2.FininvisidPost;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialPostData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FininvisidPostData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class FininvisidPostPopulator implements Populator<FininvisidPostData, FininvisidPost> {
    @Override
    public void populate(FininvisidPostData fininvisidPostData, FininvisidPost fininvisidPost) throws ConversionException {
        fininvisidPost.setInvestorId(fininvisidPostData.getInvestorid());
        List<FinancialPost> financialPostCollection = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(fininvisidPost.getFinancialPostData())){
            for (FinancialPostData financialPostData : fininvisidPostData.getNavFinqPost()) {
                FinancialPost financialPost = new FinancialPost();
                financialPost.setAnswer(financialPostData.getAnswer());
                financialPost.setAnswerkey(financialPostData.getAnswerkey());
                financialPost.setQuestionId(financialPostData.getQuestionId());
                financialPost.setQuestionText(financialPostData.getQuestionTxt());
                financialPostCollection.add(financialPost);
            }
        }
        fininvisidPost.setFinancialPostData(financialPostCollection);
    }
}
