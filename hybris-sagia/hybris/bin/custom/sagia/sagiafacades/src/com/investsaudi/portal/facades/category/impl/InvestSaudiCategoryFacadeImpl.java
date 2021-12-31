package com.investsaudi.portal.facades.category.impl;

import com.investsaudi.portal.core.model.SectorFactsFiguresComponentModel;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;
import de.hybris.platform.category.impl.DefaultCategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.converters.populator.CategoryPopulator;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;


/**
 * 
 * @author BSV3KOR
 *
 */
public class InvestSaudiCategoryFacadeImpl implements InvestSaudiCategoryFacade {

    private static final Logger LOG = Logger.getLogger(InvestSaudiCategoryFacadeImpl.class);

    @Resource
    private DefaultCategoryService defaultCategoryService;

    @Resource
    private CategoryPopulator categoryPopulator;

    
    /**
     * 
     * @param categoryCode
     * @return
     */
    @Override
    public Collection<CategoryModel> getAllCategoriesForCategoryCode(String categoryCode) {
        return defaultCategoryService.getCategoriesForCode(categoryCode);
    }

    /**
     * 
     * @param categoryCode
     * @return
     */
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

    /**
     * 
     * @param code
     * @return
     */
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

    /**
     * 
     * @param categoryCode
     * @return
     */
    @Override
    public Collection<CategoryData> getAllMainCategories(String categoryCode) {
        CategoryData categoryData = null;
        Collection<CategoryData> categoryDataList = new ArrayList<>();
        CategoryModel superCategory = defaultCategoryService.getCategoryForCode(categoryCode);         
        Collection<CategoryModel> categoryModelCollection = emptyIfNull(superCategory.getCategories());
        
        for (CategoryModel categoryModel : emptyIfNull(categoryModelCollection)) {   
        	 categoryData = new CategoryData();
             categoryPopulator.populate(categoryModel, categoryData);
             categoryDataList.add(categoryData);
        }
        
        return categoryDataList;                       
    }
    
    /**
     * 
     * @param categoryCode
     * @return
     */
    @Override
    public Map<String, List<CategoryData>> getAllCategoriesDataForCategoryCode(String categoryCode) {    	
    	 CategoryData categoryData = null;
    	 CategoryData subCategoryData = null;
         Map<String, List<CategoryData>> categoryMap = new HashMap<String, List<CategoryData>>();
         
         CategoryModel superCategory = defaultCategoryService.getCategoryForCode(categoryCode);         
         Collection<CategoryModel> categoryModelCollection = emptyIfNull(superCategory.getCategories());
         
         for (CategoryModel categoryModel : emptyIfNull(categoryModelCollection)) {        	          	
         	  categoryData = new CategoryData();
              categoryPopulator.populate(categoryModel, categoryData);
                           
              if (null != categoryModel.getCategories())
              {
            	  List<CategoryData> subCategoryDataList = new ArrayList<CategoryData>();
            	  subCategoryDataList.add(categoryData);
            	  for (CategoryModel subCategoryModel : emptyIfNull(categoryModel.getCategories())) 
            	  {	            		  	            		  	            			  
            		  subCategoryData = new CategoryData();
                      categoryPopulator.populate(subCategoryModel, subCategoryData);	                  
	                  subCategoryDataList.add(subCategoryData);                    
            	  }
            	  categoryMap.put(categoryData.getCode(),  subCategoryDataList);
              }
              else {
            	  List<CategoryData> categoryDataList = new ArrayList<CategoryData>();
            	  categoryDataList.add(categoryData);
            	  categoryMap.put(categoryData.getCode(),  categoryDataList);
              }  
         }
         
         return categoryMap;
    }
    
}
