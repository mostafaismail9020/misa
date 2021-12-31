
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.AnnualFundAssetsModel;
import com.sap.ibso.eservices.facades.data.AnnualFundAssetsData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class AnnualFundAssetsPopulator
		implements Populator<AnnualFundAssetsModel, AnnualFundAssetsData> {

	@Override
	public void populate(final AnnualFundAssetsModel source, final AnnualFundAssetsData target)
			throws ConversionException {
		target.setYear(source.getYear());
		target.setTotalNoOfInvestmentFund(source.getTotalNoOfInvestmentFund());
		target.setTotalInvestmentFundAssets(source.getTotalInvestmentFundAssets());
		target.setForeignInvestmentFund(source.getForeignInvestmentFund());
		target.setDomesticInvestmentFund(source.getDomesticInvestmentFund());
	}

}
