package com.investsaudi.portal.facades.category;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;

import java.util.Map;
import java.util.List;

import java.io.Serializable;
import java.util.Collection;

public interface InvestSaudiCategoryFacade extends Serializable {
    /**
     * returns all subcategories for the category
     *
     * @param categoryCode the categoryCode
     * @return Collection<CategoryModel
     */
    Collection<CategoryModel> getAllCategoriesForCategoryCode(final String categoryCode);

    /**
     * returns all subcategories for the categoryCode
     *
     * @param categoryCode the categoryModel
     * @return Collection<CategoryData
     */
    Collection<CategoryData> getAllSubCategoriesDataForCategoryCode(final String categoryCode);

    /**
     * Get Category for code
     * @param code
     * @return category
     */
    CategoryData getCategoryForCode(String code);

    /**
     * 
     * @param categoryCode
     * @return
     */
    Collection<CategoryData> getAllMainCategories(String categoryCode);
    
    /**
     * 
     * @param categoryCode
     * @return
     */
    Map<String, List<CategoryData>> getAllCategoriesDataForCategoryCode(final String categoryCode);
}
