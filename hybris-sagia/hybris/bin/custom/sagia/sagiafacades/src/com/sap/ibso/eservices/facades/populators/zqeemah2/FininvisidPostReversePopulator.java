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

public class FininvisidPostReversePopulator implements Populator<FininvisidPost, FininvisidPostData> {
    @Override
    public void populate(FininvisidPost fininvisidPost, FininvisidPostData fininvisidPostData) throws ConversionException {
        fininvisidPostData.setInvestorid(fininvisidPost.getInvestorId());
        List<FinancialPostData> financialPostDataCollection = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(fininvisidPost.getFinancialPostData())){
            for (FinancialPost financialPost : fininvisidPost.getFinancialPostData()) {
                FinancialPostData financialPostData = new FinancialPostData();
                financialPostData.setAnswer(financialPost.getAnswer());
                financialPostData.setAnswerkey(financialPost.getAnswerkey());
                financialPostData.setQuestionId(financialPost.getQuestionId());
                financialPostData.setQuestionTxt(financialPost.getQuestionText());
                financialPostDataCollection.add(financialPostData);
            }
        }
        fininvisidPostData.setNavFinqPost(financialPostDataCollection);
    }
}
