package com.investsaudi.portal.facades.sitemap.impl;

import com.investsaudi.portal.core.service.InvestSaudiSiteMapService;
import com.investsaudi.portal.facades.sitemap.InvestSaudiSiteMapFacade;
import com.investsaudi.portal.facades.sitemap.populator.InvestSaudiSiteMapCategoryPagePopulator;
import com.investsaudi.portal.facades.sitemap.populator.InvestSaudiSiteMapContentPagePopulator;
import com.investsaudi.portal.facades.sitemap.populator.InvestSaudiSiteMapProductPagePopulator;
import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapModel;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapData;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.SiteMapItemData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Resource;
import java.util.*;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class InvestSaudiSiteMapFacadeImpl implements InvestSaudiSiteMapFacade {
	
	private static final Logger LOG = Logger.getLogger(InvestSaudiSiteMapFacadeImpl.class);


    private static final String SEPARATOR = "/";

    @Resource
    private InvestSaudiSiteMapService investSaudiSiteMapService;

    @Resource
    private InvestSaudiSiteMapContentPagePopulator investSaudiSiteMapContentPagePopulator;

    @Resource
    private InvestSaudiSiteMapCategoryPagePopulator investSaudiSiteMapCategoryPagePopulator;

    @Resource
    private InvestSaudiSiteMapProductPagePopulator investSaudiSiteMapProductPagePopulator;
	
	@Resource
    private Converter<InvestSaudiSiteMapModel, InvestSaudiSiteMapData> investSaudiSiteMapConverter;


    @Override
    public List<SiteMapItemData> getAllActiveContentPagesForCatalogVersion() {
        List<SiteMapItemData> siteMapItemDataList = new ArrayList<>();
      LOG.info("entered into getAllActiveContentPagesForCatalogVersion()");
        List<ContentPageModel> contentPageModels = investSaudiSiteMapService.getAllActiveContentPagesForCatalogVersion();
        for (ContentPageModel contentPageModel : emptyIfNull(contentPageModels)) {
            SiteMapItemData siteMapItemData = new SiteMapItemData();
            investSaudiSiteMapContentPagePopulator.populate(siteMapItemData, contentPageModel);
            siteMapItemDataList.add(siteMapItemData);
            LOG.info("getAllActiveContentPagesForCatalogVersion" +siteMapItemDataList);
        }
        return siteMapItemDataList;
    }

    @Override
    public List<SiteMapItemData> getAllActiveCategoryPagesForCatalogVersion() {
        List<SiteMapItemData> siteMapItemDataList = new ArrayList<>();
        LOG.info("entered into getAllActiveCategoryPagesForCatalogVersion()");
        List<CategoryPageModel> categoryPageModels = investSaudiSiteMapService.getAllActiveCategoryPagesForCatalogVersion();
        for (CategoryPageModel categoryPageModel : emptyIfNull(categoryPageModels)) {
            SiteMapItemData siteMapItemData = new SiteMapItemData();
            investSaudiSiteMapCategoryPagePopulator.populate(siteMapItemData, categoryPageModel);
            siteMapItemDataList.add(siteMapItemData);
            LOG.info("getAllActiveCategoryPagesForCatalogVersion()" +siteMapItemDataList);
        }
        return siteMapItemDataList;
    }

    @Override
    public List<SiteMapItemData> getAllActiveProductPagesForCatalogVersion() {
        List<SiteMapItemData> siteMapItemDataList = new ArrayList<>();
        LOG.info("entered into getAllActiveProductPagesForCatalogVersion()");
        List<ProductModel> productModels = investSaudiSiteMapService.getAllActiveProductPagesForCatalogVersion();
        for (ProductModel productModel : emptyIfNull(productModels)) {
            SiteMapItemData siteMapItemData = new SiteMapItemData();
            investSaudiSiteMapProductPagePopulator.populate(siteMapItemData, productModel);
            siteMapItemDataList.add(siteMapItemData);
            LOG.info("getAllActiveProductPagesForCatalogVersion()" +siteMapItemDataList);
        }
        return siteMapItemDataList;
    }


    @Override
    public Map<SiteMapItemData, Map> buildSiteMapHierarchy(List<SiteMapItemData> siteMapItemDataList) {
        Map<String, Map> siteMap = new TreeMap<>();

        for (SiteMapItemData siteMapItemData : emptyIfNull(siteMapItemDataList)) {
            String[] pages = StringUtils.split(siteMapItemData.getUrl(), SEPARATOR);
            
            LOG.info("buildSiteMapHierarchy:siteMapItemData.getUrl(): "+siteMapItemData.getUrl());
            LOG.info("buildSiteMapHierarchy:pages: "+pages);
            
            buildHierarchyMap(siteMap, pages);
        }

        LOG.info("buildSiteMapHierarchy:siteMapItemData: "+siteMap);
        
        
        Map<SiteMapItemData, Map> siteValueMap = new LinkedHashMap<>();

        fillHierarchyMap(siteMap, siteValueMap, siteMapItemDataList);
        LOG.info("buildSiteMapHierarchy: " +siteValueMap);
        return siteValueMap;

    }

    private static void buildHierarchyMap(Map<String, Map> currentMap, String[] values) {
        if (values == null || values.length == 0) {
            return;
        }

        if (!currentMap.containsKey(values[0])) {
            currentMap.put(values[0], new TreeMap());
        }
        if (values.length > 1) {
            buildHierarchyMap(currentMap.get(values[0]), ArrayUtils.subarray(values, 1, values.length));
        }
    }

    private static void fillHierarchyMap(Map<String, Map> map, Map<SiteMapItemData, Map> siteValueMap, List<SiteMapItemData> siteMapItemDataList) {

        map.forEach((key, valueMap) -> {
            Optional<SiteMapItemData> siteMapItemData = siteMapItemDataList.stream().filter(
                    value -> StringUtils.endsWith(value.getUrl(), SEPARATOR + key)).findFirst();

            if (siteMapItemData.isPresent()) {
                siteValueMap.put(siteMapItemData.get(), new LinkedHashMap());
                fillHierarchyMap(valueMap, siteValueMap.get(siteMapItemData.get()), siteMapItemDataList);
            }
        });
    }
	
	@Override
    public List<InvestSaudiSiteMapData> getInvestSaudiSiteMapData() {

        List<InvestSaudiSiteMapModel> investSaudiSiteMapModels = investSaudiSiteMapService.getInvestSaudiSiteMap();
        List<InvestSaudiSiteMapData> investSaudiSiteMapDataList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(investSaudiSiteMapModels)) {
            for(InvestSaudiSiteMapModel investSaudiSiteMapModel : investSaudiSiteMapModels) {
                InvestSaudiSiteMapData investSaudiSiteMapData = investSaudiSiteMapConverter.convert(investSaudiSiteMapModel);
                investSaudiSiteMapDataList.add(investSaudiSiteMapData);
            }
        }
        return investSaudiSiteMapDataList;
    }
}
