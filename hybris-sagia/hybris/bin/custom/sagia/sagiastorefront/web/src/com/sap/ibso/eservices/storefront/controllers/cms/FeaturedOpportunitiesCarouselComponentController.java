package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;

import com.investsaudi.portal.core.model.FeaturedOpportunitiesCarouselComponentModel;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("FeaturedOpportunitiesCarouselComponentController")
@RequestMapping("/view/FeaturedOpportunitiesCarouselComponentController")
public class FeaturedOpportunitiesCarouselComponentController 
	extends AbstractAcceleratorCMSComponentController<FeaturedOpportunitiesCarouselComponentModel>{
	
	 private static final Logger LOGGER = Logger.getLogger(FeaturedOpportunitiesCarouselComponentController.class);
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final FeaturedOpportunitiesCarouselComponentModel component)
	{
		LOGGER.info(" Entered into FeaturedOpportunitiesCarouselComponentController ");
		model.addAttribute("featuredProducts", component.getProducts());
		LOGGER.info(" Exit into FeaturedOpportunitiesCarouselComponentController ");
	}
		
}

