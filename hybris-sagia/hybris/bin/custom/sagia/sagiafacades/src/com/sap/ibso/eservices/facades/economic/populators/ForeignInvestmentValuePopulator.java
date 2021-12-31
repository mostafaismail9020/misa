
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.ForeignInvestmentValueModel;
import com.sap.ibso.eservices.facades.data.ForeignInvestmentValueData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ForeignInvestmentValuePopulator
		implements Populator<ForeignInvestmentValueModel, ForeignInvestmentValueData> {

	@Override
	public void populate(final ForeignInvestmentValueModel source, final ForeignInvestmentValueData target)
			throws ConversionException {
		target.setValue1(source.getValue1());
		target.setValue2(source.getValue2());
		target.setValue3(source.getValue3());
		target.setLabel1(source.getLabel1());
		target.setLabel2(source.getLabel2());
		target.setLabel3(source.getLabel3());

	}

}
