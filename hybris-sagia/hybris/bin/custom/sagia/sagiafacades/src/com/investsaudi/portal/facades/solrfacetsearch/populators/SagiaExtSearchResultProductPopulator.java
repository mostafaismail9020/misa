package com.investsaudi.portal.facades.solrfacetsearch.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class SagiaExtSearchResultProductPopulator extends SearchResultProductPopulator {
    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        List superCategories = this.getValue(source, "category");
        if (CollectionUtils.isNotEmpty(superCategories)) {
            target.setParentCategory(superCategories.get(0).toString());
        }

        List sagiaRegions = this.<List>getValue(source, "sagiaRegion");
        if (CollectionUtils.isNotEmpty(sagiaRegions)) {
            target.setSagiaRegion(sagiaRegions.get(0).toString());
        }
    }
}