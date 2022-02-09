package com.investsaudi.portal.core.dao;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.Dao;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

public interface InvestSaudiOpportunityDao extends Dao {

    /**
     * returns  List<OpportunityProductModel> for product code both catalog versions
     *
     * @param code the productCode
     * @return List<OpportunityProductModel> list class model with both catalog versions
     */
    List<OpportunityProductModel> findProductsByCode(final String code);
	
	OpportunityProductModel findProductByCodeAndCatalogVersion(final CatalogVersionModel catalogVersion, final String code);


    /**
     * returns the List<OpportunityProductModel> with attribute feature true limited by the int parameter
     *
     * @return List<OpportunityProductModel> list model class with both catalog versions
     */
    List<OpportunityProductModel> getFeaturedOpportunities();

    /**
     * returns the List<OpportunityProductModel> with attribute feature true limited by the int parameter
     *
     * @return List<OpportunityProductModel> list model class with both catalog versions
     */
    List<OpportunityProductModel> getFeaturedOpportunitiesByCategoryCode(String category);

    /**
     * returns the List<OpportunityProductModel> with attribute feature true limited by the int parameter
     *
     * @param text text parameter
     * @param categories List<String> categories code to be filter
     * @param paginationData paginationData parameter
     * @return SearchPageData<OpportunityProductModel> SearchPageData for OpportunityProductModel
     */
    SearchPageData<OpportunityProductModel> searchOpportunityByNameAndSectors(String text, List<String> categories, PaginationData paginationData);
}
