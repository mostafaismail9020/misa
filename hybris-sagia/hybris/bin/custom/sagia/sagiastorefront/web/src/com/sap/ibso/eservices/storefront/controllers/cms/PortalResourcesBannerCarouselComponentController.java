package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.EconomicAndInvestmentMonitorModel;
import com.investsaudi.portal.core.model.EconomicAndInvestmentReportsAndStudiesModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.MonthlyBulletinReportModel;
import com.investsaudi.portal.core.model.PortalResourceCarouselComponentModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller("PortalResourcesBannerCarouselComponentController")
@RequestMapping("/view/PortalResourcesBannerCarouselComponentController")
public class PortalResourcesBannerCarouselComponentController extends AbstractAcceleratorCMSComponentController<PortalResourceCarouselComponentModel>

{
	private static final Logger LOGGER = Logger.getLogger(PortalResourcesBannerCarouselComponentController.class);
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PortalResourceCarouselComponentModel component)
	{
		LOGGER.info(" Entered into PortalResourcesBannerCarouselComponentController ");
		List<InvestSaudiResourceComponentModel> listAllResourcesToBeDisplayed = new ArrayList<>();
		List<InvestSaudiResourceComponentModel> listAllResources = component.getResources();
		for (InvestSaudiResourceComponentModel  investSaudiResourceComponentModel : listAllResources ) {
			if(investSaudiResourceComponentModel instanceof EconomicAndInvestmentMonitorModel ||
					investSaudiResourceComponentModel instanceof InvestSaudiResourceComponentModel ||
					investSaudiResourceComponentModel instanceof EconomicAndInvestmentReportsAndStudiesModel ||
					investSaudiResourceComponentModel instanceof MonthlyBulletinReportModel
			) {

				continue;
			} else {
				listAllResourcesToBeDisplayed.add(investSaudiResourceComponentModel);
			}

		}

		model.addAttribute("resources", listAllResourcesToBeDisplayed);
		LOGGER.info(" Exit into PortalResourcesBannerCarouselComponentController ");
	}

}
