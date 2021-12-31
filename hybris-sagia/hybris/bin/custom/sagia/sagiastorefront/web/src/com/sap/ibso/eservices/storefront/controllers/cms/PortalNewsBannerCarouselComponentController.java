package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import com.investsaudi.portal.core.model.PortalNewsBannerCarouselComponentModel;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("PortalNewsBannerCarouselComponentController")
@RequestMapping("/view/PortalNewsBannerCarouselComponentController")
public class PortalNewsBannerCarouselComponentController extends AbstractAcceleratorCMSComponentController<PortalNewsBannerCarouselComponentModel>{
	
	 private static final Logger LOGGER = Logger.getLogger(PortalNewsBannerCarouselComponentController.class);
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PortalNewsBannerCarouselComponentModel component)
	{
		LOGGER.info(" Entered into PortalNewsBannerCarouselComponentController ");
		model.addAttribute("portalNews", component.getNews());
		LOGGER.info(" Exit into PortalNewsBannerCarouselComponentController ");
	}
		
}
