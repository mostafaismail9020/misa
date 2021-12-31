package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapLevel2Model;
import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapLevel3Model;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapLevel2Data;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapLevel3Data;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class InvestSaudiSiteMapLevel2Populator implements Populator<InvestSaudiSiteMapLevel2Model, InvestSaudiSiteMapLevel2Data> {

    private Converter<InvestSaudiSiteMapLevel3Model, InvestSaudiSiteMapLevel3Data> investSaudiSiteMapLevel3Converter;

    @Override
    public void populate(final InvestSaudiSiteMapLevel2Model source, final InvestSaudiSiteMapLevel2Data target) throws ConversionException {

       if(source != null) {
        target.setSiteMapLevel2Id(source.getSiteMapLevel2Id());
        target.setSiteMapLevel2Name(source.getSiteMapLevel2Name());
        target.setSiteMapLevel2Url(source.getSiteMapLevel2Url());
        target.setSiteMapLevel3Links(getInvestSaudiSiteMapLevel3Data(source.getSiteMapLevel3Links()));
	   }
    }

    /**
     *
     * @param investSaudiSiteMapLevel3Models
     * @return
     */
    public List<InvestSaudiSiteMapLevel3Data> getInvestSaudiSiteMapLevel3Data(List<InvestSaudiSiteMapLevel3Model> investSaudiSiteMapLevel3Models) {

        List<InvestSaudiSiteMapLevel3Data> siteMapLevel3Data = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(investSaudiSiteMapLevel3Models)) {
            for (InvestSaudiSiteMapLevel3Model siteMapLevel3Model : investSaudiSiteMapLevel3Models) {
                InvestSaudiSiteMapLevel3Data investSaudiSiteMapLevel3Data = getInvestSaudiSiteMapLevel3Converter().convert(siteMapLevel3Model);
                siteMapLevel3Data.add(investSaudiSiteMapLevel3Data);
            }
        }
        return siteMapLevel3Data;
    }

    public Converter<InvestSaudiSiteMapLevel3Model, InvestSaudiSiteMapLevel3Data> getInvestSaudiSiteMapLevel3Converter() {
        return investSaudiSiteMapLevel3Converter;
    }

    public void setInvestSaudiSiteMapLevel3Converter(Converter<InvestSaudiSiteMapLevel3Model, InvestSaudiSiteMapLevel3Data> investSaudiSiteMapLevel3Converter) {
        this.investSaudiSiteMapLevel3Converter = investSaudiSiteMapLevel3Converter;
    }
}
