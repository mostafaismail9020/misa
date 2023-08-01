package com.sap.ibso.eservices.storefront.controllers.cms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.investsaudi.portal.core.model.InvestSaudiHomePageIconsComponentModel;

/*
*   This controller is used to display the icons on the home page
*
*/

@Controller("InvestSaudiHomePageIconsController")
@RequestMapping("/view/InvestSaudiHomePageIconsController")
public class InvestSaudiHomePageIconsController extends AbstractAcceleratorCMSComponentController<InvestSaudiHomePageIconsComponentModel> {
	
	private static final Logger LOGGER = Logger.getLogger(FeaturedOpportunitiesCarouselComponentController.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, InvestSaudiHomePageIconsComponentModel component) {
    	LOGGER.debug(" Entered into InvestSaudiHomePageIconsController ");
		model.addAttribute("homepageIcons", component.getIcons());
		LOGGER.debug(" Exit into InvestSaudiHomePageIconsController ");
    }
}
