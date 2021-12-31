package com.sap.ibso.eservices.sagiaservices.converters.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialQuestionData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialQuestionDropdownData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.*;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class FinancialQuestionDataPopulator extends ODataPopulator<FinancialQuestionData> {
    FinancialQuestionDropdownDataPopulator financialQuestionDropdownDataPopulator;

    @Override
    public void populate(ODataModel model, FinancialQuestionData financialQuestionData) throws ConversionException {
        super.populate(model, financialQuestionData);
        final Map<String, Object> map = model.get();
        final ODataFeed questions = (ODataFeed) map.get("NavFinQues");
        if (questions != null && questions.getEntries() != null && !questions.getEntries().isEmpty()) {
            final Collection<FinancialQuestionDropdownData> violationNavDataSet = new ArrayList<>();
            for(ODataEntry oDataEntry : questions.getEntries()) {
                final FinancialQuestionDropdownData question = new FinancialQuestionDropdownData();
                financialQuestionDropdownDataPopulator.populate(new ODataModel(oDataEntry), question);
                violationNavDataSet.add(question);
            }
            financialQuestionData.setNavFinQues(violationNavDataSet);
        }
    }

    /**
     * @param financialQuestionDropdownDataPopulator
     */
    public void setFinancialQuestionDropdownDataPopulator(FinancialQuestionDropdownDataPopulator financialQuestionDropdownDataPopulator) {
        this.financialQuestionDropdownDataPopulator = financialQuestionDropdownDataPopulator;
    }
}
