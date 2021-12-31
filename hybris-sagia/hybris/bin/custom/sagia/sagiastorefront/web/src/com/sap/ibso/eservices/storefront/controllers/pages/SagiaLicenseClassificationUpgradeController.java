package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.sagia.AverageProcessingTimeFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaClassificationFacade;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZListData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.classification.ClassificationUpgradeService;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import com.sap.ibso.eservices.storefront.interceptors.beforecontroller.LicenseRequired;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/my-sagia/license/classifications")
public class SagiaLicenseClassificationUpgradeController extends SagiaAbstractPageController {
    private static final String SAGIA_LICENSE_CLASSIFICATION_UPGRADE_CMS_PAGE = "license-classification-upgrade";
    private static final String SAGIA_LICENSE_NEW_CLASSIFICATION_UPGRADE_CMS_PAGE = "license-new-classification-upgrade";
    private static final String SAGIA_LICENSE_INVALID_CLASSIFICATION_UPGRADE_CMS_PAGE = "license-invalid-classification-upgrade";

    @Resource(name = "averageProcessingTimeFacade")
    private AverageProcessingTimeFacade averageProcessingTimeFacade;

    @Resource(name = "sagiaClassificationFacade")
    private SagiaClassificationFacade sagiaClassificationFacade;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Resource(name = "sagiaLicenseFacade")
    private SagiaLicenseFacade licenseFacade;

    @Resource(name = "classificationUpgradeService")
    private ClassificationUpgradeService classificationUpgradeService;

    /**
     * read Classification Upgrade history list
     *
     * @param model model
     * @throws CMSItemNotFoundException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String getClassificationUpgradeList(final Model model) throws CMSItemNotFoundException {
        Collection<ZListData> classificationUpgradeList = classificationUpgradeService.getClassificationUpgradeList();
        model.addAttribute(ENTITY_STATUS, getSessionService().getCurrentSession().getAttribute(ENTITY_STATUS));
        model.addAttribute("hasInvalidLicense", licenseFacade.hasInvalidLicense(zui5SagiaFacade.getHomeHDRData()));
        model.addAttribute("classificationUpgrade_list", classificationUpgradeList);
        model.addAttribute("classifications", sagiaClassificationFacade.getClassificationsList());
        model.addAttribute("latestClassificationUpgrade", sagiaClassificationFacade.getLatestClassificationUpgradeRequest(classificationUpgradeList));
        model.addAttribute("currentClassification", sagiaClassificationFacade.getCurrentClassification());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_CLASSIFICATION_UPGRADE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_CLASSIFICATION_UPGRADE_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }

    /**
     * @param model model
     * @return page for creating a new Classification Upgrade request
     * @throws CMSItemNotFoundException
     * @throws IOException
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @RequireHardLogIn
    @LicenseRequired
    public String newClassificationUpgrade(final Model model) throws CMSItemNotFoundException {
        boolean hasInvalidLicense = licenseFacade.hasInvalidLicense(zui5SagiaFacade.getHomeHDRData());
        model.addAttribute("hasInvalidLicense", hasInvalidLicense);
        if(hasInvalidLicense) {
            storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_INVALID_CLASSIFICATION_UPGRADE_CMS_PAGE));
            setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_INVALID_CLASSIFICATION_UPGRADE_CMS_PAGE));
            model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
            return getViewForPage(model);
        }
        model.addAttribute("currentClassification", sagiaClassificationFacade.getCurrentClassification());
        model.addAttribute("classifications", sagiaClassificationFacade.getClassificationsList());
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_CLASSIFICATION_UPGRADE_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_LICENSE_NEW_CLASSIFICATION_UPGRADE_CMS_PAGE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }
}
