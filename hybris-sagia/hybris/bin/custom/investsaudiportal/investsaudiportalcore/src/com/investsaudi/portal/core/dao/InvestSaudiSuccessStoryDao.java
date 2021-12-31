package com.investsaudi.portal.core.dao;

import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;

public interface InvestSaudiSuccessStoryDao extends Dao {

    /**
     * returns  List<OpportunityProductModel> for product code both catalog versions
     *
     * @param code the productCode
     * @return List<OpportunityProductModel> list class model with both catalog versions
     */
    List<SuccessStoryProductModel> findProductsByCode(final String code);

    /**
     * returns the List<SuccessStoryProductModel> with attribute feature true limited by the int parameter
     *
     * @return List<SuccessStoryProductModel> list model class with both catalog versions
     */
    List<SuccessStoryProductModel> getSuccessStories();

    /**
     * returns the List<SuccessStoryProductModel> with attribute feature true limited by the int parameter
     *
     * @return List<SuccessStoryProductModel> list model class with both catalog versions
     */
    List<SuccessStoryProductModel> getSuccessStoriesByCategoryCode(String categoryCode);


    /**
     * returns the List<SuccessStoryProductModel> with attribute feature true limited by the int parameter
     *
     * @param text text parameter
     * @param categories List<String> categories code to be filter
     * @param paginationData paginationData parameter
     * @return SearchPageData<OpportunityProductModel> SearchPageData for OpportunityProductModel
     */
    SearchPageData<SuccessStoryProductModel> searchSuccessStoriesByNameAndSectors(String text, List<String> categories, PaginationData paginationData);

}
