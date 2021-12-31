package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/termsAndConditions")
public class SagiaTermsAndConditionsController extends SagiaAbstractPageController {
    @RequireHardLogIn
    public String getTermsAndConditions(final Model model) throws CMSItemNotFoundException {
        final ContentPageModel pageForRequest = getCmsPageService().getPageForLabel("/termsAndConditions");
        storeCmsPageInModel(model, pageForRequest);
        setUpMetaDataForContentPage(model, pageForRequest);
        return ControllerConstants.Views.Fragments.Checkout.TermsAndConditionsPopup;
    }
}
