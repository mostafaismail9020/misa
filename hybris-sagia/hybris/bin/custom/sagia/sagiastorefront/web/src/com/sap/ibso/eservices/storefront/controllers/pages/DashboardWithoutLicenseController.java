package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.investor.exception.MissingEntityIdentifierException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.product.data.OpportunityData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.util.StringUtils;
import com.investsaudi.portal.facades.category.InvestSaudiCategoryFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/dashboard-without-license")
@RequireHardLogIn
public class DashboardWithoutLicenseController extends SagiaAbstractPageController {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardWithoutLicenseController.class);

    private static final String SAGIA_DASHBOARD_CMS_PAGE = "dashboard-without-license";
    private static final String REDIRECT_TO_DASHBOARD = REDIRECT_PREFIX + "/dashboard";

    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Resource (name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;
    
    @Resource
    private InvestSaudiCategoryFacade investSaudiCategoryFacade;

    @Resource(name = "zQeemahService")
    private ZQeemahService qeemahService;

    @Resource(name = "defaultInvestorMappingService")
    private InvestorMappingService investorMappingService;
    
    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(final Model model) throws CMSItemNotFoundException {
    	
        //Clear the cache
        sagiaDashboardWithoutLicenseFacade.evictApplicationStatus();
        
        if (sagiaDashboardWithoutLicenseFacade.hasLicense()) {
            return REDIRECT_TO_DASHBOARD;
        }
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_DASHBOARD_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_DASHBOARD_CMS_PAGE));

        MediaData dashboardMedia = sagiaCustomerFacade.getCurrentCustomer().getDashboardMedia();
        if (dashboardMedia != null) {
            model.addAttribute("dashboardBanner", dashboardMedia.getUrl());
        }

        CustomerData customerData = sagiaCustomerFacade.getCurrentCustomer();
        if (customerData.getProfilePicture() != null) {
            model.addAttribute("profilePicture", customerData.getProfilePicture().getUrl());
        }

        Optional<ApplicationStatusData> applicationStatus = qeemahService.getApplicationStatus();
        // Note: application status might be null (e.g. if the user has not yet submitted a license application)
        model.addAttribute("applicationStatus", applicationStatus.isPresent() ? applicationStatus.get() : null);

        String entityId = null;
        try {
            entityId = investorMappingService.getEntityId();
        } catch(MissingEntityIdentifierException e) {
            LOG.info("User is not yet associated with an entity identifier",e);
        }
        model.addAttribute("entityId", entityId);

        String entityStatus = null;
        try {
            entityStatus = entityId != null ? getEntityStatus() : null;
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
        model.addAttribute("entityStatus", entityStatus);

        String entityStatusDescription = null;
        try {
            entityStatusDescription = entityId != null ? getEntityStatusDescription() : null;
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
        model.addAttribute("getEntityStatusDescription", entityStatusDescription);
        model.addAttribute("currentCustomerSector", customerData.getSector());
        if(Objects.nonNull(customerData.getSector()) && Objects.nonNull(customerData.getSector().getSectorCode()) && !customerData.getSector().getSectorCode().isEmpty()){
        	model.addAttribute("customerSectorCategory",investSaudiCategoryFacade.getCategoryForCode(customerData.getSector().getSectorCode()));
        	List<OpportunityData> featuredOpportunities = investSaudiProductFacade.getFeaturedOpportunitiesByCategory(3, customerData.getSector().getSectorCode() );
        	model.addAttribute("featuredOpportunities", featuredOpportunities);
        }
        boolean hasUserAppliedForLicense = StringUtils.hasLength(((CustomerModel) userService.getCurrentUser()).getApplicationServiceRequestID());
        model.addAttribute("hasUserAppliedForLicense", hasUserAppliedForLicense);
        model.addAttribute("userOpportunityTickets", sagiaCustomerFacade.getUserRaisedOpportunities(((CustomerModel) userService.getCurrentUser()).getContactEmail()));

        //model.addAttribute("displayTutorial", sagiaCustomerFacade.displayDashboardNoLicenseTutorial());

        return getViewForPage(model);
    }

    @RequestMapping(value = "/getUnifiedLicenseUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getUnifiedLicenseUrl() {
        return sagiaConfigurationFacade.getUnifiedLicenseUrl() != null ? sagiaConfigurationFacade.getUnifiedLicenseUrl() : "";
    }
}
