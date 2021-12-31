package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author BSV3KOR
 *
 */
@Controller
@RequestMapping(value = "/investor")
public class InvestorGuideController extends AbstractPageController {
   
    private static final Logger LOG = Logger.getLogger(InvestorGuideController.class);

    private static final String INCENTIVES_INVESTOR_PAGE = "/investor/incentives";
    
    private static final String INVESTOR_GUIDE_PAGE = "/investor/guide";
    
    
    @Resource
    private ContentPageBreadcrumbBuilder  contentPageBreadcrumbBuilder;
    
    
    @RequestMapping(value = "/incentives", method = {RequestMethod.GET})
    public String aboutSaudiPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(INCENTIVES_INVESTOR_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	storeContentPageTitleInModel(model, contentPageModel.getTitle());
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        return getViewForPage(model);        
    }
    
    @RequestMapping(value = "/guide", method = {RequestMethod.GET})
    public String livingInSaudiPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(INVESTOR_GUIDE_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	storeContentPageTitleInModel(model, contentPageModel.getTitle());
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        return getViewForPage(model);        
    }
    
}
