package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapLevel2Model;
import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapModel;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapData;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapLevel2Data;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class InvestSaudiSiteMapPopulator implements Populator<InvestSaudiSiteMapModel, InvestSaudiSiteMapData> {

    private Converter<InvestSaudiSiteMapLevel2Model, InvestSaudiSiteMapLevel2Data> investSaudiSiteMapLevel2Converter;

    @Override
    public void populate(final InvestSaudiSiteMapModel source, final InvestSaudiSiteMapData target) throws ConversionException {

       if(source != null) {  
        target.setMapCode(source.getMapCode());
        target.setMapName(source.getMapName());
        InvestSaudiSiteMapLevel2Data investSaudiSiteMapLevel2Data = getInvestSaudiSiteMapLevel2Converter().convert(source.getSiteMapLevel1());
        target.setSiteMapLevel1(investSaudiSiteMapLevel2Data);
        target.setSiteMapLevel2(getInvestSaudiSiteMapLevel2Data(source.getSiteMapLevel2()));
	   }
    }


    /**
     *
     * @param investSaudiSiteMapLevel2Models
     * @return
     */
    public List<InvestSaudiSiteMapLevel2Data> getInvestSaudiSiteMapLevel2Data(List<InvestSaudiSiteMapLevel2Model> investSaudiSiteMapLevel2Models) {
        List<InvestSaudiSiteMapLevel2Data> siteMapLevel2Data = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(investSaudiSiteMapLevel2Models)) {
            for (InvestSaudiSiteMapLevel2Model siteMapLevel2Model : investSaudiSiteMapLevel2Models) {
                InvestSaudiSiteMapLevel2Data investSaudiSiteMapLevel2Data = getInvestSaudiSiteMapLevel2Converter().convert(siteMapLevel2Model);
                siteMapLevel2Data.add(investSaudiSiteMapLevel2Data);
            }
        }
        return siteMapLevel2Data;
    }


    public Converter<InvestSaudiSiteMapLevel2Model, InvestSaudiSiteMapLevel2Data> getInvestSaudiSiteMapLevel2Converter() {
        return investSaudiSiteMapLevel2Converter;
    }

    public void setInvestSaudiSiteMapLevel2Converter(Converter<InvestSaudiSiteMapLevel2Model, InvestSaudiSiteMapLevel2Data> investSaudiSiteMapLevel2Converter) {
        this.investSaudiSiteMapLevel2Converter = investSaudiSiteMapLevel2Converter;
    }
}
