package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapLevel4Model;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapLevel4Data;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class InvestSaudiSiteMapLevel4Populator implements Populator<InvestSaudiSiteMapLevel4Model, InvestSaudiSiteMapLevel4Data> {

    @Override
    public void populate(InvestSaudiSiteMapLevel4Model source, InvestSaudiSiteMapLevel4Data target) throws ConversionException {

      if(source != null) {
        target.setSiteMapLevel4Id(source.getSiteMapLevel4Id());
        target.setSiteMapLevel4Name(source.getSiteMapLevel4Name());
        target.setSiteMapLevel4Url(source.getSiteMapLevel4Url());
	  }
    }
}
