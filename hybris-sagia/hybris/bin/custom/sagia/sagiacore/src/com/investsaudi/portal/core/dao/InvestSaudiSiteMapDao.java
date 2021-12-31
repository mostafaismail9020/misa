package com.investsaudi.portal.core.dao;

import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.io.Serializable;
import java.util.List;

public interface InvestSaudiSiteMapDao extends Serializable {


    List<ContentPageModel> getAllActiveContentPagesForCatalogVersion(CatalogVersionModel catalogVersionModel);

    List<CategoryPageModel> getAllActiveCategoryPagesForCatalogVersion(CatalogVersionModel catalogVersionModel);

    List<ProductModel> getAllActiveProductPagesForCatalogVersion(CatalogVersionModel catalogVersionModel);
	
	List<InvestSaudiSiteMapModel> getInvestSaudiSiteMapBySearch();
}
