package com.investsaudi.portal.core.service;

import com.sap.ibso.eservices.core.model.InvestSaudiSiteMapModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.io.Serializable;
import java.util.List;

public interface InvestSaudiSiteMapService extends Serializable {

    List<ContentPageModel> getAllActiveContentPagesForCatalogVersion();

    List<CategoryPageModel> getAllActiveCategoryPagesForCatalogVersion();

    List<ProductModel> getAllActiveProductPagesForCatalogVersion();
	
	List<InvestSaudiSiteMapModel> getInvestSaudiSiteMap();
}
