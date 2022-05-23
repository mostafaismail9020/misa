package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.FundAssetsModel;
import com.sap.ibso.eservices.facades.data.FundAssetsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class FundAssetsPopulator implements Populator<FundAssetsModel, FundAssetsData> {
    @Override
    public void populate(FundAssetsModel source, FundAssetsData target) throws ConversionException {
        target.setUid(source.getUid());
        target.setYear(source.getYear());
        target.setTotalNoOfInvestmentFund(source.getTotalNoOfInvestmentFund());
        target.setTotalNoOfInvestmentFundGrowthRate(source.getTotalNoOfInvestmentFundGrowthRate());
        target.setTotalInvestmentFundAssets(source.getTotalInvestmentFundAssets());
        target.setTotalInvestmentFundAssetGrowthRate(source.getTotalInvestmentFundAssetsGrowthRate());
        target.setForeignInvestmentFund(source.getForeignInvestmentFund());
        target.setForeignInvestmentFundGrowthRate(source.getForeignInvestmentFundGrowthRate());
        target.setDomesticInvestmentFund(source.getDomesticInvestmentFund());
        target.setDomesticInvestmentFundGrowthRate(source.getDomesticInvestmentFundGrowthRate());
    }
}
