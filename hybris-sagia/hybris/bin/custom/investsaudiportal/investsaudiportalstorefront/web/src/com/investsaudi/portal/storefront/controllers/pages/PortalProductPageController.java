/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.storefront.controllers.pages;

import com.investsaudi.portal.facades.product.InvestSaudiProductFacade;
import com.investsaudi.portal.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.util.MetaSanitizerUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Controller for opportunity details page
 */
@Controller
@RequestMapping(value = "/sectors-opportunities/*/*")
public class PortalProductPageController extends AbstractPageController {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(PortalProductPageController.class);
    private static final String ERROR_CMS_PAGE = "notFound";

    @Resource(name = "productBreadcrumbBuilder")
    private ProductBreadcrumbBuilder productBreadcrumbBuilder;

    @Resource(name = "cmsPageService")
    private CMSPageService cmsPageService;

    @Resource(name = "simpleBreadcrumbBuilder")
    private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;

    @Resource
    private InvestSaudiProductFacade investSaudiProductFacade;

    @RequestMapping(value = "/{productCode}", method = RequestMethod.GET)
    public String portalProduct(@PathVariable("productCode") final String encodedProductCode, final Model model,
                              final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException, UnsupportedEncodingException {

        final String productCode = decodeWithScheme(encodedProductCode, UTF_8);

        ProductData productData = investSaudiProductFacade.getProductForCode(productCode);

        if (productData == null) {
            final ContentPageModel errorPage = getContentPageForLabelOrId(ERROR_CMS_PAGE);
            storeCmsPageInModel(model, errorPage);
            setUpMetaDataForContentPage(model, errorPage);

            model.addAttribute(WebConstants.MODEL_KEY_ADDITIONAL_BREADCRUMB,
                    resourceBreadcrumbBuilder.getBreadcrumbs("breadcrumb.not.found"));
            GlobalMessages.addErrorMessage(model, "system.error.page.not.found");

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return ControllerConstants.Views.Pages.Error.ErrorNotFoundPage;
        }

        AbstractPageModel productPage = getPageForProduct(productCode);

        storeCmsPageInModel(model, productPage);
        storeContinueUrl(request);

        model.addAttribute(WebConstants.BREADCRUMBS_KEY, productBreadcrumbBuilder.getBreadcrumbs(productCode));

        updatePageTitle(productCode, model);

        model.addAttribute("pageType", PageType.PRODUCT.name());
        model.addAttribute("productData", productData);
        model.addAttribute("productName", productData.getName());
        model.addAttribute("categoryCode", productData.getParentCategory());

        final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(productData.getKeywords());
        final String metaDescription = MetaSanitizerUtil.sanitizeDescription(productData.getDescription());
        setUpMetaData(model, metaKeywords, metaDescription);

        return getViewForPage(productPage);
    }

    protected void updatePageTitle(final String productCode, final Model model) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveProductPageTitle(productCode));
    }

    protected AbstractPageModel getPageForProduct(final String productCode) throws CMSItemNotFoundException {
        return cmsPageService.getPageForProductCode(productCode, getCmsPreviewService().getPagePreviewCriteria());
    }
}
