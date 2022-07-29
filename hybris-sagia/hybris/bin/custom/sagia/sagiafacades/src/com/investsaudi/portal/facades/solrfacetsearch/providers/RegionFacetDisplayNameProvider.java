package com.investsaudi.portal.facades.solrfacetsearch.providers;

import com.investsaudi.portal.core.dao.InvestSaudiProvinceRegionDao;
import com.investsaudi.portal.core.model.ProvinceComponentModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.commons.lang.StringUtils;

public class RegionFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider {

    InvestSaudiProvinceRegionDao investSaudiProvinceRegionDao;

    @Override
    public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue) {
        ProvinceComponentModel model = investSaudiProvinceRegionDao.getProvinceRegionDetails(facetValue);
        if (model != null && StringUtils.isNotBlank(model.getName())) {
            return model.getName();
        }
        return facetValue;
    }


    public void setInvestSaudiProvinceRegionDao(InvestSaudiProvinceRegionDao investSaudiProvinceRegionDao) {
        this.investSaudiProvinceRegionDao = investSaudiProvinceRegionDao;
    }
}
