package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.investsaudi.portal.core.service.utils.PaginationUtils;
import com.investsaudi.portal.core.service.InvestSaudiProvinceRegionService;
import com.investsaudi.portal.core.model.ProvinceComponentModel;
import com.investsaudi.portal.core.model.ProvisionStrategicDetailsComponentModel;
import com.investsaudi.portal.core.model.ProvisionStrategicSectorComponentModel;
import com.investsaudi.portal.core.model.ProvinceKeyFactComponentModel;
import com.investsaudi.portal.core.model.ProvinceKeyStrengthComponentModel;
import com.investsaudi.portal.core.model.ProvinceInvestmentOpportunitiesComponentModel;


import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.util.Config;

import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import de.hybris.platform.commercefacades.product.data.OpportunityData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;

import com.sap.ibso.eservices.storefront.controllers.pages.portal.DefaultPageController;

import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiEventsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiMediaCenterService;
import com.investsaudi.portal.core.service.utils.PaginationUtils;
import de.hybris.platform.core.servicelayer.data.SearchPageData;


@Controller
@RequestMapping(value = "/meetTheKingdom")
public class MeetTheKingdomController extends DefaultPageController {
   
    private static final Logger LOG = Logger.getLogger(MeetTheKingdomController.class);
    
    private static final String ABOUT_KINGDOM_PAGE = "/aboutKingdom";
    
    private static final String LIVING_IN_SAUDI_PAGE = "/livingInSaudi";
    
    private static final String MEET_REGIONS_PAGE = "/meetRegions";
    
    protected static final String PROVINCE_CODE_PATH_VARIABLE_PATTERN = "/province/{provinceId}";
    
    private static final String PROVINCE_DETAILS_PAGE = "/province";
    
    @Autowired
    private ContentPageBreadcrumbBuilder  contentPageBreadcrumbBuilder;
    
    @Resource
    private InvestSaudiProvinceRegionService investSaudiProvinceRegionService;
    
    @Resource
    private InvestSaudiMediaCenterService investSaudiMediaCenterService;
    
    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;    
    
    
    @RequestMapping(value = "/aboutKingdom", method = {RequestMethod.GET})
    public String aboutSaudiPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(ABOUT_KINGDOM_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        return getViewForPage(model);        
    }
    
    @RequestMapping(value = "/livingInSaudi", method = {RequestMethod.GET})
    public String livingInSaudiPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(LIVING_IN_SAUDI_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        return getViewForPage(model);        
    }
    
    @RequestMapping(value = "/meetRegions", method = {RequestMethod.GET})
    public String meetRegionsPage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException {

    	ContentPageModel contentPageModel = getContentPageForLabelOrId(MEET_REGIONS_PAGE);
    	storeCmsPageInModel(model, contentPageModel);
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
        return getViewForPage(model);        
    }
    
    @RequestMapping(value = PROVINCE_CODE_PATH_VARIABLE_PATTERN, method = {RequestMethod.GET})
    public String provinceRegionPage(@PathVariable("provinceId") final String provinceId, final Model model,
			final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException 
    {
    	LOG.info("provinceId =" + provinceId);
    	
    	ProvinceComponentModel provinceDetails = null;
    	
        if (null != provinceId) {
        	provinceDetails = investSaudiProvinceRegionService.getProvinceRegionDetails(provinceId);
	    }
                    
        model.addAttribute("provinceDetails", provinceDetails);
        
        if(null != provinceDetails.getStrategicDetails()) {
        	ProvisionStrategicDetailsComponentModel strategicDetails = provinceDetails.getStrategicDetails();
        	if(null != strategicDetails.getSectors() && CollectionUtils.isNotEmpty(strategicDetails.getSectors())) {
        	List<ProvisionStrategicSectorComponentModel> sectors = strategicDetails.getSectors();
        	model.addAttribute("sectors", sectors);
        	}
        	 model.addAttribute("strategicDetails", strategicDetails);
        }
        
        if(null != provinceDetails.getKeyFacts() && CollectionUtils.isNotEmpty(provinceDetails.getKeyFacts())) {
        	List<ProvinceKeyFactComponentModel> keyFacts = provinceDetails.getKeyFacts();
        	model.addAttribute("keyFacts", keyFacts);
        }
        
        
        if(null != provinceDetails.getKeyStrengths() && CollectionUtils.isNotEmpty(provinceDetails.getKeyStrengths())) {
        	List<ProvinceKeyStrengthComponentModel> keyStrengths = provinceDetails.getKeyStrengths();
        	model.addAttribute("keyStrengths", keyStrengths);
        }
        
        if(null != provinceDetails.getInvestmentOpportunities() && CollectionUtils.isNotEmpty(provinceDetails.getInvestmentOpportunities())) {
        	List<ProvinceInvestmentOpportunitiesComponentModel> investmentOpportunities = provinceDetails.getInvestmentOpportunities();
        	model.addAttribute("investmentOpportunities", investmentOpportunities);
        }
        
        SearchPageData<InvestSaudiNewsComponentModel> regionNewsData = investSaudiMediaCenterService.getRegionNews(PaginationUtils.createPaginationData(0, 3), provinceId);
        model.addAttribute("regionNewsData", regionNewsData);
        
        SearchPageData<InvestSaudiResourceComponentModel> regionResourcesData = investSaudiMediaCenterService.getRegionResources(PaginationUtils.createPaginationData(0, 3), provinceId);
        model.addAttribute("regionResourcesData", regionResourcesData);
        
        SearchPageData<InvestSaudiEventsComponentModel> regionEventsData = investSaudiMediaCenterService.getRegionEvents(PaginationUtils.createPaginationData(0, 3), provinceId);
        model.addAttribute("regionEventsData", regionEventsData);
        
        SearchPageData<OpportunityData> regionProductsData = investSaudiProductFacade.searchOpportunityByRegion(PaginationUtils.createPaginationData(0, 3), provinceId);
        model.addAttribute("regionProductsData", regionProductsData);
        
        ContentPageModel contentPageModel = getContentPageForLabelOrId(PROVINCE_DETAILS_PAGE);
        storeCmsPageInModel(model, contentPageModel);
        storeContentPageTitleInModel(model, contentPageModel.getTitle());
        return getViewForPage(model);        
    }
    
}
