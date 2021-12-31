package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.investsaudi.portal.facades.sitemap.InvestSaudiSiteMapFacade;
import com.sap.ibso.eservices.facades.data.InvestSaudiSiteMapData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMethod;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;

import java.util.List;

@Controller
public class PortalSiteMapController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(PortalSiteMapController.class);

	protected static final String SITE_MAP_LABEL = "/sitemap";

	public static final String BREADCRUMBS_KEY = "breadcrumbs";   

	@Resource(name = "contentPageBreadcrumbBuilder")
	private ContentPageBreadcrumbBuilder contentPageBreadcrumbBuilder;  

	@Resource
	private CMSSiteService cmsSiteService;

	@Resource
	private InvestSaudiSiteMapFacade investSaudiSiteMapFacade;
	
	
	@RequestMapping(value = "/sitemap", method = {RequestMethod.GET})
	public String getSagiaSitemap(final Model model, final HttpServletRequest request, final HttpServletResponse response) 
		throws CMSItemNotFoundException 
	{	
	    	LOG.info("entered into SagiaSitemap");

			List<InvestSaudiSiteMapData> investSaudiSiteMapData = investSaudiSiteMapFacade.getInvestSaudiSiteMapData();
			model.addAttribute("investSaudiSiteMapData", investSaudiSiteMapData);

	    	final ContentPageModel contentPage = getContentPageForLabelOrId(SITE_MAP_LABEL);
	    	storeCmsPageInModel(model, contentPage);
	    	setUpMetaDataForContentPage(model, contentPage);
	    	
	    	storeContentPageTitleInModel(model, contentPage.getTitle());
	    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPage));

	    	LOG.info("exit from SagiaSitemap");
	    	return getViewForPage(model);	    	
	}
}
