/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.variants.model.VariantProductModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Source of categories for CategoryCodeValueProvider. Does not include ClassificationClasses by default. Optional root
 * category is used to restrict categories found to categories that have a path to the root.
 */
public class SagiaCategorySource extends DefaultCategorySource
{
    @Override
    public Collection<CategoryModel> getCategoriesForConfigAndProperty(final IndexConfig indexConfig,
                                                                       final IndexedProperty indexedProperty, final Object model)
    {
        final Set<ProductModel> products = getProducts(model);
        final Set<CategoryModel> directCategories = getDirectSuperCategories(products);

        if (directCategories != null && !directCategories.isEmpty())
        {
            // Lookup the root categories - null if no root categories
            //	final Set<CategoryModel> rootCategories = lookupRootCategories(indexConfig.getCatalogVersions());
            final Collection<CatalogVersionModel> catalogVersions = Collections.singletonList(((ProductModel) model)
                    .getCatalogVersion());
            final Set<CategoryModel> rootCategories = lookupRootCategories(catalogVersions);

            final Set<CategoryModel> allCategories = new HashSet<CategoryModel>();
            for (final CategoryModel category : directCategories)
            {
                allCategories.addAll(getAllCategories(category, rootCategories));
            }

            if(CollectionUtils.isNotEmpty(allCategories)){
                allCategories.removeIf(cat -> cat.getCode().equals(this.getRootCategory()));
            }

            return allCategories;
        }
        else
        {
            return Collections.emptyList();
        }
    }
}
