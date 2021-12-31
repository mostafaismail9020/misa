package com.investsaudi.facades.populators;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.site.BaseSiteService;


/**
 *
 */
public class SagiaB2BProductPopulator implements Populator<ProductModel, ProductData> {
	
	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
    private SiteBaseUrlResolutionService siteBaseUrlResolutionService;
	
    @Override
    public void populate(ProductModel source, ProductData target) {
    	List<MediaData> specifications = new ArrayList<>();
    	
    	String sitUrl =  siteBaseUrlResolutionService.getWebsiteUrlForSite(baseSiteService.getCurrentBaseSite(), true,"/") ;
    
		if (!CollectionUtils.isEmpty(source.getData_sheet()))
		{
			for (MediaModel model : source.getData_sheet())
			{
				
				MediaData  mediaData = mediaConverter.convert(model);
				mediaData.setFileName(model.getRealFileName());
				mediaData.setUrl(sitUrl+mediaData.getUrl());
				specifications.add(mediaData);
			}
		}
		target.setSpecifications(specifications);
    }
}