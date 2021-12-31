package com.investsaudi.portal.core.service.impl;

import com.investsaudi.portal.core.dao.InvestSaudiSiteMapDao;
import com.investsaudi.portal.core.service.InvestSaudiSiteMapService;
import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.product.ProductModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class InvestSaudiSiteMapServiceImpl implements InvestSaudiSiteMapService {

    @Resource
    private InvestSaudiSiteMapDao investSaudiSiteMapDao;

    @Resource
    private CatalogVersionService catalogVersionService;

    private static final String CATALOG_ID = "investsaudiportalContentCatalog";
    private static final String ONLINE = "Online";
    private static final String HOMEPAGE_ID = "homepage";
    private static final String SECTOR_PAGE_ID = "sector";
    private static final String NOT_FOUND_ID = "notFound";
    private static final String SITE_MAP_ID = "sitemap";
    private static final String PRODUCT_CATALOG_ID = "investsaudiportalProductCatalog";

    @Override
    public List<ContentPageModel> getAllActiveContentPagesForCatalogVersion() {
        return removeHomePageFromSiteMapContentPages(investSaudiSiteMapDao.getAllActiveContentPagesForCatalogVersion(catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE)));
    }

    @Override
    public List<CategoryPageModel> getAllActiveCategoryPagesForCatalogVersion() {
        return removeSectorPageFromSiteMapCategoryPages(investSaudiSiteMapDao.getAllActiveCategoryPagesForCatalogVersion(catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE)));
    }

    @Override
    public List<ProductModel> getAllActiveProductPagesForCatalogVersion() {
        return investSaudiSiteMapDao.getAllActiveProductPagesForCatalogVersion(catalogVersionService.getCatalogVersion(PRODUCT_CATALOG_ID, ONLINE));
    }

    private List<ContentPageModel> removeHomePageFromSiteMapContentPages(List<ContentPageModel> contentPageModels) {
        return contentPageModels.stream()
                .filter(contentPageModel -> !contentPageModel.getUid().contains(HOMEPAGE_ID))
                .filter(contentPageModel -> !contentPageModel.getUid().contains(NOT_FOUND_ID))
                .filter(contentPageModel -> !contentPageModel.getUid().contains(SITE_MAP_ID))
                .collect(Collectors.toList());
    }

    private List<CategoryPageModel> removeSectorPageFromSiteMapCategoryPages(List<CategoryPageModel> categoryPageModels) {
        return categoryPageModels.stream()
                .filter(categoryPageModel -> !categoryPageModel.getUid().contains(SECTOR_PAGE_ID))
                .collect(Collectors.toList());
    }
	
	@Override
    public List<InvestSaudiSiteMapModel> getInvestSaudiSiteMap() {
        List<InvestSaudiSiteMapModel> investSaudiSiteMapModels = investSaudiSiteMapDao.getInvestSaudiSiteMapBySearch();
        return investSaudiSiteMapModels;
    }
}
