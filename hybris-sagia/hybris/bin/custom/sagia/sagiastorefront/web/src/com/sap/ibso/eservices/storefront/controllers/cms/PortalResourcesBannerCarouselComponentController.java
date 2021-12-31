package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import com.investsaudi.portal.core.model.PortalResourceCarouselComponentModel;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("PortalResourcesBannerCarouselComponentController")
@RequestMapping("/view/PortalResourcesBannerCarouselComponentController")
public class PortalResourcesBannerCarouselComponentController extends AbstractAcceleratorCMSComponentController<PortalResourceCarouselComponentModel>

{
	private static final Logger LOGGER = Logger.getLogger(PortalResourcesBannerCarouselComponentController.class);
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PortalResourceCarouselComponentModel component)
	{
		LOGGER.info(" Entered into PortalResourcesBannerCarouselComponentController ");
		model.addAttribute("resources", component.getResources());
		LOGGER.info(" Exit into PortalResourcesBannerCarouselComponentController ");
	}

}
