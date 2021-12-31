package com.investsaudi.portal.facades.sitemap.populator;

import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.SiteMapItemData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class InvestSaudiSiteMapContentPagePopulator implements Populator<SiteMapItemData, ContentPageModel> {

    @Override
    public void populate(SiteMapItemData siteMapItemData, ContentPageModel contentPageModel) throws ConversionException {
        siteMapItemData.setCode(contentPageModel.getUid());
        siteMapItemData.setName(contentPageModel.getName());
        siteMapItemData.setUrl(contentPageModel.getLabel());
    }
}
