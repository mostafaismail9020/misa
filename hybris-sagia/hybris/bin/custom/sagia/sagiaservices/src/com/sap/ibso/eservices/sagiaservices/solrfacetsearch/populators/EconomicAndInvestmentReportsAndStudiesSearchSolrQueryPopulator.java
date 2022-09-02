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
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

public class EconomicAndInvestmentReportsAndStudiesSearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE> extends  SearchSolrQueryPopulator<INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE> {

    private static final Logger LOG = Logger.getLogger(EconomicAndInvestmentReportsAndStudiesSearchSolrQueryPopulator.class);

    @Resource
    CatalogVersionService catalogVersionService ;

    @Resource
    FacetSearchConfigService facetSearchConfigService;

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

    /**
     * Resolves suitable {@link FacetSearchConfig} for the query based on the configured strategy bean.<br>
     *
     * @return {@link FacetSearchConfig} that is converted from {@link SolrFacetSearchConfigModel}
     * @throws NoValidSolrConfigException
     *            , FacetConfigServiceException
     */
    protected FacetSearchConfig getFacetSearchConfig() throws NoValidSolrConfigException, FacetConfigServiceException
    {

        return facetSearchConfigService.getConfiguration("sagiaEconomicAndInvestmentReportsAndStudiesIndex");
    }

    protected IndexedType getIndexedType(final FacetSearchConfig config)
    {
        final IndexConfig indexConfig = config.getIndexConfig();

        // Strategy for working out which of the available indexed types to use
        final Collection<IndexedType> indexedTypes = indexConfig.getIndexedTypes().values();
        if (indexedTypes != null && !indexedTypes.isEmpty())
        {
            // When there are multiple - select the first
            return indexedTypes.iterator().next();
        }

        // No indexed types
        return null;
    }
}
