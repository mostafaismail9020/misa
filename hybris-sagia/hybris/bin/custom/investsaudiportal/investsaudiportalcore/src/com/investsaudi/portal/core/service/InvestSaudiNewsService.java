package com.investsaudi.portal.core.service;

import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import java.util.Collection;

public interface InvestSaudiNewsService {
    /**
     * returns all the InvestSaudiNewsComponents present in the System
     *
     *
     * @return a collection of all InvestSaudiNewsComponentsModel
     */
    SearchPageData<InvestSaudiNewsComponentModel> getAllNews(PaginationData paginationData);

    /**
     * returns  the first number InvestSaudiNewsComponents ordered by newsDate
     *
     * @param number the number of InvestSaudiNewsComponentsModel to retrieve
     * @return a collection ot the first number InvestSaudiNewsComponentsModel
     */
    Collection<InvestSaudiNewsComponentModel> getNews(int number);

}
