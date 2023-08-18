package com.sap.ibso.eservices.core.interceptors;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

//Interceptor to associate root category to super categories coming in to commerce from C4C
public class SagiaOpportunityProductPrepareInterceptor implements PrepareInterceptor<OpportunityProductModel>   {
	
	private static final Logger LOG = LoggerFactory.getLogger(SagiaOpportunityProductPrepareInterceptor.class);
	private static final String SAGIA_PRODUCT_CATALOG = "sagiaProductCatalog";
	private static final String SAGIA_PRODUCT_CATALOG_VERSION = "Online";
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CatalogVersionService catalogVersionService;

	@Override
	public void onPrepare(OpportunityProductModel opportunity, InterceptorContext context) throws InterceptorException {

		String origin = opportunity.getSystemOrigin();
		CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SAGIA_PRODUCT_CATALOG, SAGIA_PRODUCT_CATALOG_VERSION);
		if(Objects.nonNull(origin) && origin.equalsIgnoreCase("C4C"))
		{
             Collection<CategoryModel> existingSupercategories = opportunity.getSupercategories();
             
             for(CategoryModel category :existingSupercategories)
             {
            	 Collection<CategoryModel> rootCategories = categoryService.getRootCategoriesForCatalogVersion(catalogVersion);

                 List<CategoryModel> supercategoriesList = rootCategories.stream()
                     .collect(Collectors.toList());
                 
                 supercategoriesList.stream()
                 .map(CategoryModel::getCode)
                 .forEach(code -> LOG.info("Supercategory Code: " + code));
            	 
                 boolean hasExistingSupercategories = category.getSupercategories().containsAll(supercategoriesList);

            	 if (!hasExistingSupercategories) {
					category.setSupercategories(supercategoriesList);
				}
            	 else {
            		 
            		LOG.info("Opportunity category {} already has root supercategory associated ",opportunity.getCode()); 
            	 }
             }
		}
		
	}

}
