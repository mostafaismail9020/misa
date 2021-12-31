package com.investsaudi.portal.facades.sitemap.populator;

import com.investsaudi.portal.facades.product.InvestSaudiProductModelUrlResolver;
import de.hybris.platform.commercefacades.product.data.SiteMapItemData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

public class InvestSaudiSiteMapProductPagePopulator implements Populator<SiteMapItemData, ProductModel> {

    @Resource(name = "investSaudiProductModelUrlResolver")
    private InvestSaudiProductModelUrlResolver investSaudiProductModelUrlResolver;

    @Override
    public void populate(SiteMapItemData siteMapItemData, ProductModel productModel) throws ConversionException {

        siteMapItemData.setCode(productModel.getCode());
        siteMapItemData.setName(productModel.getName());
        siteMapItemData.setUrl(investSaudiProductModelUrlResolver.resolve(productModel));
    }
}
