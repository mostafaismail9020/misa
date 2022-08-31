package com.sap.ibso.eservices.sagiaservices.solrfacetsearch.populators;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SearchQueryPageableData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchRequest;
import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchSolrQueryPopulator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;

public class InvestmentHighlightsReportSearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE> extends  SearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE> {

    private static final Logger LOG = Logger.getLogger(InvestmentHighlightsReportSearchSolrQueryPopulator.class);

    @Resource
    CatalogVersionService catalogVersionService ;

    @Override
    public void populate(final SearchQueryPageableData<SolrSearchQueryData> source,
                         final SolrSearchRequest<FacetSearchConfig, IndexedType, INDEXED_PROPERTY_TYPE, SearchQuery, INDEXED_TYPE_SORT_TYPE> target)
    {
        // Setup the SolrSearchRequest
        target.setSearchQueryData(source.getSearchQueryData());
        target.setPageableData(source.getPageableData());
        ArrayList catloagVersion = new ArrayList<CatalogVersionModel>();
        catloagVersion.add(catalogVersionService.getCatalogVersion("sagiaContentCatalog","Online"));
        target.setCatalogVersions(new ArrayList<CatalogVersionModel>(catloagVersion));

        try
        {
            target.setFacetSearchConfig(getFacetSearchConfig());
        }
        catch (final NoValidSolrConfigException e)
        {
            LOG.error("No valid solrFacetSearchConfig found for the current context", e);
            throw new ConversionException("No valid solrFacetSearchConfig found for the current context", e);
        }
        catch (final FacetConfigServiceException e)
        {
            LOG.error(e.getMessage(), e);
            throw new ConversionException(e.getMessage(), e);
        }

        // We can only search one core so select the indexed type
        target.setIndexedType(getIndexedType(target.getFacetSearchConfig()));

        // Create the solr search query for the config and type (this sets-up the default page size and sort order)
        SearchQuery searchQuery;

        if (target.getFacetSearchConfig().getSearchConfig().isLegacyMode())
        {
            searchQuery = createSearchQueryForLegacyMode(target.getFacetSearchConfig(), target.getIndexedType(),
                    source.getSearchQueryData().getSearchQueryContext(), source.getSearchQueryData().getFreeTextSearch());
        }
        else
        {
            searchQuery = createSearchQuery(target.getFacetSearchConfig(), target.getIndexedType(),
                    source.getSearchQueryData().getSearchQueryContext(), source.getSearchQueryData().getFreeTextSearch());
        }

        searchQuery.setCatalogVersions(target.getCatalogVersions());
        searchQuery.setCurrency(getCommonI18NService().getCurrentCurrency().getIsocode());
        searchQuery.setLanguage(getCommonI18NService().getCurrentLanguage().getIsocode());

        // enable spell checker
        searchQuery.setEnableSpellcheck(true);

        target.setSearchQuery(searchQuery);
    }
}
