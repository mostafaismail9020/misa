package com.investsaudi.portal.facades.sitemap;

import de.hybris.platform.commercefacades.product.data.SiteMapItemData;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface InvestSaudiSiteMapFacade extends Serializable {

    List<SiteMapItemData> getAllActiveContentPagesForCatalogVersion();

    List<SiteMapItemData> getAllActiveCategoryPagesForCatalogVersion();

    List<SiteMapItemData> getAllActiveProductPagesForCatalogVersion();

    Map<SiteMapItemData, Map> buildSiteMapHierarchy(List<SiteMapItemData> siteMapItemDataList);
}
