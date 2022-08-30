/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.facades.solrfacetsearch.populators;

import com.sap.ibso.eservices.facades.data.InvestSaudiResourceComponentData;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.InvestSaudiResourceComponentSearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.SpellingSuggestionData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;


/**
 */
public class InvestSaudiResourceComponentSearchPagePopulator<QUERY, STATE, RESULT, ITEM extends InvestSaudiResourceComponentData, SCAT, CATEGORY>
		implements Populator<InvestSaudiResourceComponentSearchPageData<QUERY, RESULT>, InvestSaudiResourceComponentSearchPageData<STATE, ITEM>>
{
	private Converter<QUERY, STATE> searchStateConverter;
	private Converter<BreadcrumbData<QUERY>, BreadcrumbData<STATE>> breadcrumbConverter;
	private Converter<FacetData<QUERY>, FacetData<STATE>> facetConverter;
	private Converter<SpellingSuggestionData<QUERY>, SpellingSuggestionData<STATE>> spellingSuggestionConverter;
	private Converter<RESULT, ITEM> searchResultInvestSaudiResourceComponentConverter;


	protected Converter<QUERY, STATE> getSearchStateConverter()
	{
		return searchStateConverter;
	}

	@Required
	public void setSearchStateConverter(final Converter<QUERY, STATE> searchStateConverter)
	{
		this.searchStateConverter = searchStateConverter;
	}

	protected Converter<BreadcrumbData<QUERY>, BreadcrumbData<STATE>> getBreadcrumbConverter()
	{
		return breadcrumbConverter;
	}

	@Required
	public void setBreadcrumbConverter(final Converter<BreadcrumbData<QUERY>, BreadcrumbData<STATE>> breadcrumbConverter)
	{
		this.breadcrumbConverter = breadcrumbConverter;
	}

	protected Converter<FacetData<QUERY>, FacetData<STATE>> getFacetConverter()
	{
		return facetConverter;
	}

	@Required
	public void setFacetConverter(final Converter<FacetData<QUERY>, FacetData<STATE>> facetConverter)
	{
		this.facetConverter = facetConverter;
	}

	protected Converter<RESULT, ITEM> getSearchResultInvestSaudiResourceComponentConverter()
	{
		return searchResultInvestSaudiResourceComponentConverter;
	}

	@Required
	public void setSearchResultInvestSaudiResourceComponentConverter(final Converter<RESULT, ITEM> searchResultInvestSaudiResourceComponentConverter)
	{
		this.searchResultInvestSaudiResourceComponentConverter = searchResultInvestSaudiResourceComponentConverter;
	}

	protected Converter<SpellingSuggestionData<QUERY>, SpellingSuggestionData<STATE>> getSpellingSuggestionConverter()
	{
		return spellingSuggestionConverter;
	}

	@Required
	public void setSpellingSuggestionConverter(final Converter<SpellingSuggestionData<QUERY>, SpellingSuggestionData<STATE>> spellingSuggestionConverter)
	{
		this.spellingSuggestionConverter = spellingSuggestionConverter;
	}

	@Override
	public void populate(final InvestSaudiResourceComponentSearchPageData<QUERY, RESULT> source,
			final InvestSaudiResourceComponentSearchPageData<STATE, ITEM> target)
	{
		target.setFreeTextSearch(source.getFreeTextSearch());
		target.setCategoryCode(source.getCategoryCode());

		if (source.getBreadcrumbs() != null)
		{
			target.setBreadcrumbs(Converters.convertAll(source.getBreadcrumbs(), getBreadcrumbConverter()));
		}

		target.setCurrentQuery(getSearchStateConverter().convert(source.getCurrentQuery()));

		if (source.getFacets() != null)
		{
			target.setFacets(Converters.convertAll(source.getFacets(), getFacetConverter()));
		}

		target.setPagination(source.getPagination());

		if (source.getResults() != null)
		{
			target.setResults(Converters.convertAll(source.getResults(), getSearchResultInvestSaudiResourceComponentConverter()));
		}

		target.setSorts(source.getSorts());

		if (source.getSpellingSuggestion() != null)
		{
			target.setSpellingSuggestion(getSpellingSuggestionConverter().convert(source.getSpellingSuggestion()));
		}

		target.setKeywordRedirectUrl(source.getKeywordRedirectUrl());
	}
}
