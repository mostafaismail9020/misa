package com.sap.ibso.eservices.storefront.controllers.pages;


import com.sap.ibso.eservices.core.sagia.services.SagiaDashboardSectionsService;
import com.sap.ibso.eservices.facades.sagia.SagiaDashboardWithoutLicenseFacade;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping(value = "/dashboard-edit")
public class InvestorDashboardEditController extends AbstractPageController {
    @Resource(name = "sagiaDashboardWithoutLicenseFacade")
    private SagiaDashboardWithoutLicenseFacade sagiaDashboardWithoutLicenseFacade;

    @Resource(name = "sagiaCustomerFacade")
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Resource(name = "sagiaConfigurationFacade")
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource(name = "sagiaDashboardSectionsService")
    private SagiaDashboardSectionsService sagiaDashboardSectionsService;

    @Resource(name = "userService")
    private UserService userService;

    private static final String SAGIA_DASHBOARD_EDIT_CMS_PAGE = "dashboard-edit";
    private static final String REDIRECT_TO_DASHBOARD_WITHOUT_LICENSE = REDIRECT_PREFIX + "/dashboard-without-license";
    private static final String REDIRECT_TO_DASHBOARD_EDIT_PAGE = REDIRECT_PREFIX + "/dashboard-edit";

    /**
     * Displays the dashboard edit page
     * @param model - Model for the view
     * @return - Dashboard URL
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    public String getDashboard(final Model model) throws CMSItemNotFoundException {
        if (!sagiaDashboardWithoutLicenseFacade.hasLicense()) {
            return REDIRECT_TO_DASHBOARD_WITHOUT_LICENSE;
        }

        if(MapUtils.isEmpty(sagiaDashboardSectionsService.getUserDashboardSections())) {
            sagiaDashboardSectionsService.initializeDashboardSections();
        }

        model.addAttribute("enableSalaryAndEmployment", sagiaConfigurationFacade.isEnabledSalaryAndEmploymentOnDashboard());

        CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();
        model.addAttribute("customerLastLogon", customerModel.getLastSuccessLogin());

        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_DASHBOARD_EDIT_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_DASHBOARD_EDIT_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * controller method, that updates the dashboard sections map for current user.
     * @param map with profile sections
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void setProfileSections(@RequestBody Map<String, ArrayList<String>> map) {
        sagiaDashboardSectionsService.updateUserDashboardSections(map);
    }

    /**
     * controller method, that updates the dashboard picture for current user and redirects to dashboard-edit page.
     * @param file that is uploaded
     */
    @RequestMapping(value = "/update-dashboardPic", method = RequestMethod.POST, consumes = "multipart/form-data")
    @RequireHardLogIn
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        sagiaCustomerFacade.updateDashboardPicture(file);
        return REDIRECT_TO_DASHBOARD_EDIT_PAGE;
    }
}

