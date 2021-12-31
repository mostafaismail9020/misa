package com.investsaudi.portal.facades.sitemap.populator;

import javax.annotation.Resource;
import java.util.Optional;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.category.impl.DefaultCategoryService;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel;
import de.hybris.platform.cms2.model.restrictions.CMSCategoryRestrictionModel;
import de.hybris.platform.commercefacades.product.data.SiteMapItemData;
import de.hybris.platform.commerceservices.url.impl.DefaultCategoryModelUrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvestSaudiSiteMapCategoryPagePopulator implements Populator<SiteMapItemData, CategoryPageModel> {

    private static final Logger log = LoggerFactory.getLogger(InvestSaudiSiteMapCategoryPagePopulator.class);

    private static final String CATALOG_ID = "investsaudiportalProductCatalog";
    private static final String ONLINE = "Online";

    @Resource(name = "sectorCategoryModelUrlResolver")
    private DefaultCategoryModelUrlResolver sectorCategoryModelUrlResolver;

    @Resource
    private DefaultCategoryService defaultCategoryService;

    @Resource
    private CatalogVersionService catalogVersionService;

    @Override
    public void populate(SiteMapItemData siteMapItemData, CategoryPageModel categoryPageModel)
        throws ConversionException {
        siteMapItemData.setCode(categoryPageModel.getUid());
        siteMapItemData.setName(categoryPageModel.getName());

        if (CollectionUtils.isNotEmpty(categoryPageModel.getRestrictions())) {
            Optional<AbstractRestrictionModel> restrictions = categoryPageModel.getRestrictions().stream()
                .filter(restriction -> restriction instanceof CMSCategoryRestrictionModel).findFirst();

            if (restrictions.isPresent() && restrictions.get() instanceof CMSCategoryRestrictionModel) {
                CMSCategoryRestrictionModel categoryRestriction =
                    (CMSCategoryRestrictionModel) restrictions.get();

                if (CollectionUtils.isNotEmpty(categoryRestriction.getCategoryCodes())) {
                    Optional<String> categoryModel = categoryRestriction.getCategoryCodes().stream().findFirst();

                    if (categoryModel.isPresent()) {
                        try {
                            siteMapItemData.setUrl(sectorCategoryModelUrlResolver.resolve(defaultCategoryService
                                .getCategoryForCode(catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE),
                                    categoryModel.get())));
                        } catch (Exception e) {
                            log.warn("Category with code {} not found", categoryModel.get(), e);
                        }
                    }
                }
            }
        }
    }
}
