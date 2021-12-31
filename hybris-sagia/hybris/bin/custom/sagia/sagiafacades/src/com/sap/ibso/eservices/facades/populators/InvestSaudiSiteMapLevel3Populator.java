package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapLevel3Model;
import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapLevel4Model;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapLevel3Data;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapLevel4Data;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class InvestSaudiSiteMapLevel3Populator implements Populator<InvestSaudiSiteMapLevel3Model, InvestSaudiSiteMapLevel3Data> {

    private Converter<InvestSaudiSiteMapLevel4Model, InvestSaudiSiteMapLevel4Data> investSaudiSiteMapLevel4Converter;

    @Override
    public void populate(InvestSaudiSiteMapLevel3Model source, InvestSaudiSiteMapLevel3Data target) throws ConversionException {

      if(source != null) {
        target.setSiteMapLevel3Id(source.getSiteMapLevel3Id());
        target.setSiteMapLevel3Name(source.getSiteMapLevel3Name());
        target.setSiteMapLevel3Url(source.getSiteMapLevel3Url());
        target.setSiteMapLevel4Links(getInvestSaudiSiteMapLevel4Data(source.getSiteMapLevel4Links()));
	  }
    }

    /**
     *
     * @param investSaudiSiteMapLevel4Models
     * @return
     */
    public List<InvestSaudiSiteMapLevel4Data> getInvestSaudiSiteMapLevel4Data(List<InvestSaudiSiteMapLevel4Model> investSaudiSiteMapLevel4Models) {
        List<InvestSaudiSiteMapLevel4Data> siteMapLevel4Data = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(investSaudiSiteMapLevel4Models)) {
            for (InvestSaudiSiteMapLevel4Model siteMapLevel4Model : investSaudiSiteMapLevel4Models) {
                InvestSaudiSiteMapLevel4Data investSaudiSiteMapLevel4Data = getInvestSaudiSiteMapLevel4Converter().convert(siteMapLevel4Model);
                siteMapLevel4Data.add(investSaudiSiteMapLevel4Data);
            }
        }
        return siteMapLevel4Data;
    }

    public Converter<InvestSaudiSiteMapLevel4Model, InvestSaudiSiteMapLevel4Data> getInvestSaudiSiteMapLevel4Converter() {
        return investSaudiSiteMapLevel4Converter;
    }

    public void setInvestSaudiSiteMapLevel4Converter(Converter<InvestSaudiSiteMapLevel4Model, InvestSaudiSiteMapLevel4Data> investSaudiSiteMapLevel4Converter) {
        this.investSaudiSiteMapLevel4Converter = investSaudiSiteMapLevel4Converter;
    }
}
