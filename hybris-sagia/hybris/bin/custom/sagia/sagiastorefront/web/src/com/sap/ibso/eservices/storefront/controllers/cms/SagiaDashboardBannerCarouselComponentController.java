package com.sap.ibso.eservices.storefront.controllers.cms;

import com.sap.ibso.eservices.core.model.SagiaDashboardBannerCarouselComponentModel;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller("SagiaDashboardBannerCarouselComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.SagiaDashboardBannerCarouselComponent)
public class SagiaDashboardBannerCarouselComponentController extends AbstractCMSComponentController<SagiaDashboardBannerCarouselComponentModel> {

	private static final Logger LOGGER = Logger.getLogger(SagiaDashboardBannerCarouselComponentController.class);
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final SagiaDashboardBannerCarouselComponentModel component) {
		LOGGER.info(" Entered into SagiaDashboardBannerCarouselComponentController ");
		model.addAttribute("bannerImages", component.getBannerImage());
		LOGGER.info(" Exit into SagiaDashboardBannerCarouselComponentController ");
    }
	
	@Override
    protected String getView(final SagiaDashboardBannerCarouselComponentModel component) {
        return ControllerConstants.Views.Cms.RealTime.SagiaDashboardBannerCarouselComponent;
    }
}
