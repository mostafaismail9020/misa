package com.investsaudi.portal.facades.product;

import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.SuccessStoryData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import de.hybris.platform.core.servicelayer.data.PaginationData;

import java.io.Serializable;
import java.util.List;

public interface InvestSaudiProductFacade extends Serializable {
    /**
     * returns the productData for the product code
     *
     * @param code the productCode
     * @return ProductData data class
     */

    ProductData getProductForCode(String code);

    /**
     * returns the List<ProductData> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     * @return List<OpportunityData> list data class
     */
    List<OpportunityData> getFeaturedOpportunities(int limit);

    /**
     *  returns the List<ProductData> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     * @param categoryCode category code
     * @return List<OpportunityData> list data class
     */
    List<OpportunityData> getFeaturedOpportunitiesByCategory(int limit, String categoryCode);

    /**
     * returns the List<ProductData> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     * @return List<SuccessStoryData> list data class
     */
    List<SuccessStoryData> getSuccessStories(int limit);

    /**
     *  returns the List<ProductData> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     * @param categoryCode category code
     * @return List<SuccessStoryData> list data class
     */
    List<SuccessStoryData> getSuccessStoriesByCategory(int limit, String categoryCode);

    /**
     * returns the List<ProductData> with attribute feature true limited by the int parameter
     *
     * @param categories  list of categories code for filter
     * @param pageSize    int parameter for pageSize
     * @param currentPage int parameter for currentPage
     * @param text        text parameter
     * @return SearchPageData<OpportunityData> SearchPageData for ProductData
     */
    SearchPageData<OpportunityData> searchOpportunityByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize);

    /**
     * returns the List<ProductData> with attribute feature true limited by the int parameter
     *
     * @param categories  list of categories code for filter
     * @param pageSize    int parameter for pageSize
     * @param currentPage int parameter for currentPage
     * @param text        text parameter
     * @return SearchPageData<SuccessStoryData> SearchPageData for ProductData
     */
    SearchPageData<SuccessStoryData> searchSuccessStoriesByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize);

    SearchPageData<OpportunityData> searchOpportunityByRegion(PaginationData paginationData, String regionId);
}
