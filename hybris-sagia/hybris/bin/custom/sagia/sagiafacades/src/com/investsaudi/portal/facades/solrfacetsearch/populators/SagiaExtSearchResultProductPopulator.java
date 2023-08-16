package com.investsaudi.portal.facades.solrfacetsearch.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class SagiaExtSearchResultProductPopulator extends SearchResultProductPopulator {

    private static final String FORMAT_TO_REMOVE = "<em class=\"search-results-highlight\">";
    private static final String FORMAT_TO_REMOVE_2 = "</em>";

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

        if(StringUtils.isNotBlank(target.getName())){
            target.setName(target.getName().replaceAll(FORMAT_TO_REMOVE, "").replaceAll(FORMAT_TO_REMOVE_2,""));
        }
        target.setResource(this.getValue(source, "resource"));
        target.setImageUrl(this.getValue(source, "picture"));
    }
}