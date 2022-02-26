package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;
import com.investsaudi.portal.core.model.ExploreSaudiCarouselComponentModel;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("ExploreSaudiCarouselComponentController")
@RequestMapping("/view/ExploreSaudiCarouselComponentController")
public class ExploreSaudiCarouselComponentController 
		extends AbstractAcceleratorCMSComponentController<ExploreSaudiCarouselComponentModel>{
	
	 private static final Logger LOGGER = Logger.getLogger(ExploreSaudiCarouselComponentController.class);
	 
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ExploreSaudiCarouselComponentModel component)
	{
		LOGGER.debug(" Entered into ExploreSaudiCarouselComponentController ");
		model.addAttribute("ExploreSaudi", component.getExploreSaudi());
		LOGGER.debug(" Exit into ExploreSaudiCarouselComponentController ");
	}
		
}
