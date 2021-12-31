package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import com.investsaudi.portal.core.model.PortalEventsCarouselComponentModel;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("PortalEventsBannerCarouselComponentController")
@RequestMapping("/view/PortalEventsBannerCarouselComponentController")
public class PortalEventsBannerCarouselComponentController extends AbstractAcceleratorCMSComponentController<PortalEventsCarouselComponentModel>
{
     
	 private static final Logger LOGGER = Logger.getLogger(PortalEventsBannerCarouselComponentController.class);
		@Override
		protected void fillModel(final HttpServletRequest request, final Model model, final PortalEventsCarouselComponentModel component)
		{
			LOGGER.info(" Entered into PortalEventsBannerCarouselComponentController ");
			model.addAttribute("events", component.getEvents());
			LOGGER.info(" Exit into PortalEventsBannerCarouselComponentController ");
		}
	
}
