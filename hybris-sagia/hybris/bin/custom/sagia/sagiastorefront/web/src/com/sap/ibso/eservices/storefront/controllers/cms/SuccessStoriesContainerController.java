package com.sap.ibso.eservices.storefront.controllers.cms;

import javax.servlet.http.HttpServletRequest;

import com.investsaudi.portal.core.model.SuccessStoriesContainerModel;
import com.investsaudi.portal.core.model.MeetTheKingdomKeyReasonsContainerModel;
import com.investsaudi.portal.core.model.MeetTheKingdomMacroEconomicContainerModel;
import com.sap.ibso.eservices.storefront.controllers.cms.SuccessStoriesContainerController;

import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;
import de.hybris.platform.acceleratorcms.model.components.CMSTabParagraphContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("SuccessStoriesContainerController")
@RequestMapping("/view/SuccessStoriesContainerController")
public class SuccessStoriesContainerController extends AbstractAcceleratorCMSComponentController<SuccessStoriesContainerModel>
{
	private static final Logger LOG = LoggerFactory.getLogger(SuccessStoriesContainerController.class);
	
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final SuccessStoriesContainerModel component)
	{
		LOG.debug("---------SuccessStoriesContainerController---------------");
		
		List<SimpleCMSComponentModel> simpleCMSComponents = new ArrayList<>();
        simpleCMSComponents.addAll(component.getSimpleCMSComponents());
		model.addAttribute("components", simpleCMSComponents);
		model.addAttribute("title", component.getTitle());
		model.addAttribute("brandsLogos", component.getBrandsLogos());
	}
}
