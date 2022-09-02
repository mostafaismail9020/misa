/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.sagiaservices.solrfacetsearch.impl;

import de.hybris.platform.commerceservices.search.ProductSearchAutocompleteService;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.AutocompleteSuggestion;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.SolrFacetSearchConfigSelectionStrategy;
import de.hybris.platform.commerceservices.search.solrfacetsearch.strategies.exceptions.NoValidSolrConfigException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedType;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.indexer.SolrIndexedTypeCodeResolver;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;
import de.hybris.platform.solrfacetsearch.suggester.SolrAutoSuggestService;
import de.hybris.platform.solrfacetsearch.suggester.SolrSuggestion;
import de.hybris.platform.solrfacetsearch.suggester.exceptions.SolrAutoSuggestException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Default implementation of the AutocompleteService
 */
public class DefaultSolrEconomicAndInvestmentMonitorSearchAutocompleteService implements ProductSearchAutocompleteService<AutocompleteSuggestion>
{
	private static final Logger LOG = Logger.getLogger(DefaultSolrEconomicAndInvestmentMonitorSearchAutocompleteService.class);

	private FacetSearchConfigService facetSearchConfigService;
	private CommonI18NService commonI18NService;
	private SolrAutoSuggestService solrAutoSuggestService;
	private SolrIndexedTypeCodeResolver solrIndexedTypeCodeResolver;
	private SolrFacetSearchConfigSelectionStrategy solrFacetSearchConfigSelectionStrategy;

	@Override
	public List<AutocompleteSuggestion> getAutocompleteSuggestions(final String input)
	{
		List<AutocompleteSuggestion> result = new ArrayList<>();
		try
		{
			final SolrFacetSearchConfigModel solrFacetSearchConfigModel = getSolrFacetSearchConfigSelectionStrategy()
					.getCurrentSolrFacetSearchConfig();

			final FacetSearchConfig facetSearchConfig = getFacetSearchConfigService()
					.getConfiguration(solrFacetSearchConfigModel.getName());
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
		catch (final SolrAutoSuggestException | FacetConfigServiceException | IndexerException | NoValidSolrConfigException e)
		{
			LOG.warn("Error retrieving autocomplete suggestions", e);
		}

		return result;
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

	protected SolrIndexedTypeModel findIndexedTypeModel(final SolrFacetSearchConfigModel facetSearchConfigModel,
			final IndexedType indexedType) throws IndexerException
	{
		if (indexedType == null)
		{
			throw new IndexerException("indexedType is NULL!");
		}
		for (final SolrIndexedTypeModel type : facetSearchConfigModel.getSolrIndexedTypes())
		{
			if (solrIndexedTypeCodeResolver.resolveIndexedTypeCode(type).equals(indexedType.getUniqueIndexedTypeCode()))
			{
				return type;
			}
		}
		throw new IndexerException("Could not find matching model for type: " + indexedType.getCode());
	}

	protected boolean isLegacySuggesterSuggestions(final SolrSuggestion solrSuggestion)
	{
		return solrSuggestion != null && MapUtils.isNotEmpty(solrSuggestion.getSuggestions());
	}

	protected List<AutocompleteSuggestion> findBestSuggestionsForLegacySuggester(final SolrSuggestion solrSuggestion,
			final String input)
	{
		final String trimmedInput = input.trim();

		final String lastTerm;
		final String precedingTerms;

		// Only provide suggestions for the last 'word' in the input
		final int indexOfLastSpace = trimmedInput.lastIndexOf(' ');
		if (indexOfLastSpace >= 0)
		{
			lastTerm = trimmedInput.substring(indexOfLastSpace + 1);
			precedingTerms = trimmedInput.substring(0, indexOfLastSpace).trim();
		}
		else
		{
			lastTerm = trimmedInput;
			precedingTerms = null;
		}

		// Get the suggestions for the last term
		final String lowerCaseLastTerm = lastTerm.toLowerCase(Locale.ROOT);
		Collection<String> suggestions = solrSuggestion.getSuggestions().get(lowerCaseLastTerm);
		if (suggestions == null)
		{
			final Collection<String> altSuggestions = new ArrayList<>();

			final Map<String, Collection<String>> values = solrSuggestion.getSuggestions();
			for (final Map.Entry<String, Collection<String>> entry : values.entrySet())
			{
				if ((lowerCaseLastTerm.contains(entry.getKey()) || entry.getKey().contains(lowerCaseLastTerm))
						&& entry.getValue() != null)
				{
					altSuggestions.addAll(entry.getValue());
				}
			}

			suggestions = altSuggestions;
		}

		return buildSuggestions(precedingTerms, suggestions);
	}

	protected List<AutocompleteSuggestion> findBestSuggestionsForNewSuggester(final SolrSuggestion solrSuggestion)
	{
		if (CollectionUtils.isEmpty(solrSuggestion.getCollates()))
		{
			return Collections.emptyList();
		}

		return buildSuggestions(null, solrSuggestion.getCollates());
	}


	protected List<AutocompleteSuggestion> buildSuggestions(final String precedingTerms, final Collection<String> suggestions)
	{
		final List<AutocompleteSuggestion> target = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(suggestions))
		{
			for (final String suggestion : suggestions)
			{
				final AutocompleteSuggestion autocompleteSuggestion = new AutocompleteSuggestion();
				autocompleteSuggestion.setTerm(precedingTerms == null ? suggestion : (precedingTerms + " " + suggestion));

				target.add(autocompleteSuggestion);
			}
		}

		return target;
	}

	protected FacetSearchConfigService getFacetSearchConfigService()
	{
		return facetSearchConfigService;
	}

	@Required
	public void setFacetSearchConfigService(final FacetSearchConfigService facetSearchConfigService)
	{
		this.facetSearchConfigService = facetSearchConfigService;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	protected SolrAutoSuggestService getSolrAutoSuggestService()
	{
		return solrAutoSuggestService;
	}

	@Required
	public void setSolrAutoSuggestService(final SolrAutoSuggestService solrAutoSuggestService)
	{
		this.solrAutoSuggestService = solrAutoSuggestService;
	}

	protected SolrIndexedTypeCodeResolver getSolrIndexedTypeCodeResolver()
	{
		return solrIndexedTypeCodeResolver;
	}

	@Required
	public void setSolrIndexedTypeCodeResolver(final SolrIndexedTypeCodeResolver solrIndexedTypeCodeResolver)
	{
		this.solrIndexedTypeCodeResolver = solrIndexedTypeCodeResolver;
	}

	protected SolrFacetSearchConfigSelectionStrategy getSolrFacetSearchConfigSelectionStrategy()
	{
		return solrFacetSearchConfigSelectionStrategy;
	}

	@Required
	public void setSolrFacetSearchConfigSelectionStrategy(
			final SolrFacetSearchConfigSelectionStrategy solrFacetSearchConfigSelectionStrategy)
	{
		this.solrFacetSearchConfigSelectionStrategy = solrFacetSearchConfigSelectionStrategy;
	}
}
