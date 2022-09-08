package com.investsaudi.portal.facades.solrfacetsearch.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.data.AutocompleteSuggestion;
import de.hybris.platform.commerceservices.search.solrfacetsearch.impl.DefaultSolrProductSearchAutocompleteService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.daos.SolrFacetSearchConfigDao;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;
import de.hybris.platform.solrfacetsearch.suggester.SolrSuggestion;
import de.hybris.platform.solrfacetsearch.suggester.exceptions.SolrAutoSuggestException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class EconomicAndInvestmentReportsAndStudiesSearchAutocompleteService extends DefaultSolrProductSearchAutocompleteService {


    @Resource
    SolrFacetSearchConfigDao solrFacetSearchConfigDao ;

    @Override
    public List<AutocompleteSuggestion> getAutocompleteSuggestions(final String input)
    {
        List<AutocompleteSuggestion> result = new ArrayList<>();
        try
        {
            final SolrFacetSearchConfigModel solrFacetSearchConfigModel = 
            solrFacetSearchConfigDao.findFacetSearchConfigByName("sagiaEconomicAndInvestmentReportsAndStudiesIndex");

            final FacetSearchConfig facetSearchConfig = getFacetSearchConfigService()
                    .getConfiguration("sagiaEconomicAndInvestmentReportsAndStudiesIndex");
            
            final IndexedType indexedType = getIndexedType(facetSearchConfig);

            final SolrIndexedTypeModel indexedTypeModel = findIndexedTypeModel(solrFacetSearchConfigModel, indexedType);

            final SolrSuggestion suggestions = getSolrAutoSuggestService()
                    .getAutoSuggestionsForQuery(getCommonI18NService().getCurrentLanguage(), indexedTypeModel, input);

            if (isLegacySuggesterSuggestions(suggestions))
            {
                result = findBestSuggestionsForLegacySuggester(suggestions, input);
            }
            else
            {
                result = findBestSuggestionsForNewSuggester(suggestions);
            }
        }
         catch (FacetConfigServiceException e) {
            e.printStackTrace();
        } catch (SolrAutoSuggestException e) {
            e.printStackTrace();
        } catch (IndexerException e) {
            e.printStackTrace();
        }

        return result;
    }
    
}
