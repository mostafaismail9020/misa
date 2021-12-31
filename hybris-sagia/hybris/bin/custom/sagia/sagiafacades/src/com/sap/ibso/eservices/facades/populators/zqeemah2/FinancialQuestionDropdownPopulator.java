package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.FinancialQuestionDropdown;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FinancialQuestionDropdownData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class FinancialQuestionDropdownPopulator implements Populator<FinancialQuestionDropdownData, FinancialQuestionDropdown> {
    @Override
    public void populate(FinancialQuestionDropdownData financialQuestionDropdownData, FinancialQuestionDropdown financialQuestionDropdown) throws ConversionException {
        financialQuestionDropdown.setKey(financialQuestionDropdownData.getZkey());
		financialQuestionDropdown.setValue(financialQuestionDropdownData.getValue());
    }
}
