package com.sap.ibso.eservices.storefront.controllers.cms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;

/*
*   This controller is used to display the icons on the home page
*
*/

@Controller("InvestSaudiHomePageIconsController")
@RequestMapping("/view/InvestSaudiHomePageIconsController")
public class InvestSaudiHomePageIconsController extends AbstractAcceleratorCMSComponentController<AbstractCMSComponentModel> {

    @Override
    protected void fillModel(HttpServletRequest request, Model model, AbstractCMSComponentModel component) {
        /*int numberOfNews = component.getNumberOfNews();
        Collection<InvestSaudiNewsComponentModel> lastNews = investSaudiMediaCenterService.getNews(numberOfNews);
        model.addAttribute("lastNews", lastNews);*/
    }
}
