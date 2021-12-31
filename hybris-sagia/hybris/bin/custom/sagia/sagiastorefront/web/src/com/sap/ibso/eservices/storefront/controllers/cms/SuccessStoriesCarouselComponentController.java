package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import com.investsaudi.portal.core.model.SuccessStoriesCarouselComponentModel;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SuccessStoriesCarouselComponentController")
@RequestMapping("/view/SuccessStoriesCarouselComponentController")
public class SuccessStoriesCarouselComponentController extends AbstractAcceleratorCMSComponentController<SuccessStoriesCarouselComponentModel>{
	
	 private static final Logger LOGGER = Logger.getLogger(SuccessStoriesCarouselComponentController.class);
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final SuccessStoriesCarouselComponentModel component)
	{
		LOGGER.info(" Entered into SuccessStoriesCarouselComponentController ");
		model.addAttribute("Videos", component.getVideoComponents());
		LOGGER.info(" Exit into SuccessStoriesCarouselComponentController ");
	}
		
}
