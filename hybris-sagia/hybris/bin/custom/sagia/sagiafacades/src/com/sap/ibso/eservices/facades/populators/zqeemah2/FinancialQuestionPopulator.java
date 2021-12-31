package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.FinancialQuestionDropdown;
import com.sap.ibso.eservices.facades.data.zqeemah2.FinancialQuestion;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialQuestionData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialQuestionDropdownData;
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
public class FinancialQuestionPopulator implements Populator<FinancialQuestionData, FinancialQuestion> {
    FinancialQuestionDropdownPopulator financialQuestionDropdownPopulator;

    @Override
    public void populate(FinancialQuestionData financialQuestionData, FinancialQuestion financialQuestion) throws ConversionException {
        financialQuestion.setQuestionId(financialQuestionData.getQuestionId());
        financialQuestion.setSectionNumber(financialQuestionData.getSectionno());
        financialQuestion.setLanguage(financialQuestionData.getLanguage());
        financialQuestion.setQuestionText(financialQuestionData.getQuestionTxt());
        financialQuestion.setQuestionType(financialQuestionData.getQuestionType());
        Collection<FinancialQuestionDropdown> financialQuestions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(financialQuestionData.getNavFinQues())) {
            for (FinancialQuestionDropdownData item : financialQuestionData.getNavFinQues()) {
                FinancialQuestionDropdown financialQuestionDropdown = new FinancialQuestionDropdown();
                financialQuestionDropdownPopulator.populate(item, financialQuestionDropdown);
                financialQuestions.add(financialQuestionDropdown);
            }
        }
        financialQuestion.setFinancialQuestions(financialQuestions);
    }

    /**
     * @param financialQuestionDropdownPopulator
     */
    public void setFinancialQuestionDropdownPopulator(FinancialQuestionDropdownPopulator financialQuestionDropdownPopulator) {
        this.financialQuestionDropdownPopulator = financialQuestionDropdownPopulator;
    }
}
