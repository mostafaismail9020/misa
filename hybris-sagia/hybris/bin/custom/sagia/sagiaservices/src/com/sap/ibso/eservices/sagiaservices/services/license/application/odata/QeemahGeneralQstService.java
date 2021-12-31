package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.QeemahGeneralQstData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Collection;

/**
 * QeemahGeneralQstService
 */
public class QeemahGeneralQstService extends AbstractSagiaService<QeemahGeneralQstData> {
    /**
     * retrieves Collection of QeemahGeneralQstData
     * @param language language
     * @param refId refId
     * @param questionId questionId
     * @return Collection of QeemahGeneralQstData
     */
    public Collection<QeemahGeneralQstData> getCollection(String language, String refId, String questionId) {
        QueryOptionsBuilder queryOptionsBuilder = new QueryOptionsBuilder()
                .expand("NavGeneralQuest")
                .filter("Refid eq '" + refId + "' and Language eq '" + language + "' and QuestionId eq '" + questionId + "'");
        return super.getCollection(QeemahGeneralQstData.class, queryOptionsBuilder.build());
    }
}
