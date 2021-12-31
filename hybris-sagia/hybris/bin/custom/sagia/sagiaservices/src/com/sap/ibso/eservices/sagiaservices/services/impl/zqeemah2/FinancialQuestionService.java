package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialQuestionData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;

/**
 * FinancialQuestionService
 */
public class FinancialQuestionService extends AbstractSagiaService<FinancialQuestionData> {

    /**
     * retrieves Questions
     * @param language language
     * @return Collection of FinancialQuestionData
     */
    public Collection<FinancialQuestionData> getQuestions(String language){
        QueryOptionsBuilder query = new QueryOptionsBuilder()
                .expand("NavFinQues")
                .filter("Language eq '" + language + "'");
        return super.getCollection(FinancialQuestionData.class, query.build());
    }


}
