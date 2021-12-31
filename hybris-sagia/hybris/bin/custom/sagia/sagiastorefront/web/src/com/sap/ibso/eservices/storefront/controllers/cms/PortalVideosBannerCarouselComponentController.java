package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import com.investsaudi.portal.core.model.PortalVideosCarouselComponentModel;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("PortalVideosBannerCarouselComponentController")
@RequestMapping("/view/PortalVideosBannerCarouselComponentController")
public class PortalVideosBannerCarouselComponentController extends AbstractAcceleratorCMSComponentController<PortalVideosCarouselComponentModel>

{
 	private static final Logger LOGGER = Logger.getLogger(PortalVideosBannerCarouselComponentController.class);
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PortalVideosCarouselComponentModel component)
	{
		LOGGER.info(" Entered into PortalVideosBannerCarouselComponentController ");
		model.addAttribute("videos", component.getVideos());
		LOGGER.info(" Exit into PortalVideosBannerCarouselComponentController ");
	}	
}
