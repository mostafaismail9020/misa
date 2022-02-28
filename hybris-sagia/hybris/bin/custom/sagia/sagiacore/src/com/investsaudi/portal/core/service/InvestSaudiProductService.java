package com.investsaudi.portal.core.service;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.investsaudi.portal.core.model.SuccessStoryProductModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.io.Serializable;
import java.util.List;

public interface InvestSaudiProductService extends Serializable {
    /**
     * returns  OpportunityProductModel for product code
     *
     * @param code the productCode
     * @return OpportunityProductModel class model
     */
    OpportunityProductModel getOpportunityForCode(String code);
	
	OpportunityProductModel getOpportunityForCodeAndCatalogVersion(CatalogVersionModel catalogVersion, String code);

    /**
     * returns  SuccessStoryProductModel for product code
     * @param code the productCode
     * @return SuccessStoryProductModel class model
     */
    SuccessStoryProductModel getSuccessStoryForCode(String code);

    /**
     * returns the List<OpportunityProductModel> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     * @return List<OpportunityProductModel> list class model
     */
    List<OpportunityProductModel> getFeaturedOpportunities(int limit);



    /**
     * returns the List<SuccessStoryProductModel> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     * @return List<SuccessStoryProductModel> list class model
     */
    List<SuccessStoryProductModel> getSuccessStories(int limit);

    /**
     * returns the List<SuccessStoryProductModel> limited by the int parameter
     *
     * @param limit int parameter
     *  @param categoryCode string parameter
     * @return List<SuccessStoryProductModel> list class model
     */
    public List<SuccessStoryProductModel> getSuccessStoriesByCategory(int limit, String categoryCode);

    /**
     * returns the List<OpportunityProductModel> with attribute feature true limited by the int parameter
     *
     * @param limit int parameter
     *  @param categoryCode string parameter
     * @return List<OpportunityProductModel> list class model
     */
    public List<OpportunityProductModel> getOpportunitiesByCategory(int limit, String categoryCode);

    /**
     * returns the List<OpportunityProductModel> with attribute feature true limited by the int parameter
     *
     * @param categories  list of categories code for filter
     * @param pageSize    int parameter for pageSize
     * @param currentPage int parameter for currentPage
     * @param text        text parameter
     * @return SearchPageData<OpportunityProductModel> SearchPageData for OpportunityProductModel
     */
    SearchPageData<OpportunityProductModel> searchOpportunityByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize);

    /**
     * returns the List<SuccessStoryProductModel> with attribute feature true limited by the int parameter
     *
     * @param categories  list of categories code for filter
     * @param pageSize    int parameter for pageSize
     * @param currentPage int parameter for currentPage
     * @param text        text parameter
     * @return SearchPageData<SuccessStoryProductModel> SearchPageData for SuccessStoryProductModel
     */
    SearchPageData<SuccessStoryProductModel> searchSuccessStoriesByNameAndSectors(String text, List<String> categories, int currentPage, int pageSize);

    /**
     * Return Product Type
     * @param code
     * @return
     */
    String getProductTypeForCode(String code);
    
    SearchPageData<OpportunityProductModel> searchOpportunityByRegion(PaginationData paginationData, String regionId);
}
