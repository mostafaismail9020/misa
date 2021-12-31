package com.investsaudi.portal.facades.category.impl;

import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import de.hybris.platform.category.impl.DefaultCategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class InvestSaudiCategoryFacadeImpl implements InvestSaudiCategoryFacade {

    private static final Logger LOG = Logger.getLogger(InvestSaudiCategoryFacadeImpl.class);

    @Resource
    private DefaultCategoryService defaultCategoryService;

    @Resource
    private CategoryPopulator categoryPopulator;

    @Override
    public Collection<CategoryModel> getAllCategoriesForCategoryCode(String categoryCode) {
        return defaultCategoryService.getCategoriesForCode(categoryCode);
    }

    @Override
    public Collection<CategoryData> getAllSubCategoriesDataForCategoryCode(String categoryCode) {
        CategoryModel categoryModel = new CategoryModel();
        Collection<CategoryModel> categoryModelCollection = emptyIfNull(getAllCategoriesForCategoryCode(categoryCode));
        Optional<CategoryModel> optionalCategoryModel = categoryModelCollection.stream().findFirst();
        if (optionalCategoryModel.isPresent()) {
            categoryModel = optionalCategoryModel.get();
        }
        Collection<CategoryData> categoryDataList = new ArrayList<>();
        for (CategoryModel subCategoryModel : emptyIfNull(categoryModel.getAllSubcategories())) {
            CategoryData categoryData = new CategoryData();
            categoryPopulator.populate(subCategoryModel, categoryData);
            categoryDataList.add(categoryData);
        }
        return categoryDataList;
    }

    @Override
    public CategoryData getCategoryForCode(String code) {
        CategoryData categoryData = new CategoryData();
        try
        {
            categoryPopulator.populate(defaultCategoryService.getCategoryForCode(code), categoryData);
        } catch (Exception ignored){
            categoryData = null;
        }
        return categoryData;
    }


}
