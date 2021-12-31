
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.QuarterlyFundAssetsModel;
import com.sap.ibso.eservices.facades.data.QuarterlyFundAssetsData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class QuarterlyFundAssetsPopulator
		implements Populator<QuarterlyFundAssetsModel, QuarterlyFundAssetsData> {

	@Override
	public void populate(final QuarterlyFundAssetsModel source, final QuarterlyFundAssetsData target)
			throws ConversionException {
		target.setPeriod(source.getPeriod());
		target.setTotalNoOfInvestmentFund(source.getTotalNoOfInvestmentFund());
		target.setTotalInvestmentFundAssets(source.getTotalInvestmentFundAssets());
		target.setForeignInvestmentFund(source.getForeignInvestmentFund());
		target.setDomesticInvestmentFund(source.getDomesticInvestmentFund());
	}

}
