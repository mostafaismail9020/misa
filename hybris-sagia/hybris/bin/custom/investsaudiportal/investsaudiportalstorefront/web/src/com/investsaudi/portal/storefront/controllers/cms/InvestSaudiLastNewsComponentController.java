package com.investsaudi.portal.storefront.controllers.cms;


import com.investsaudi.portal.core.model.InvestSaudiLastNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiNewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/*
*   This controller is used to display the last n news present in the system
*
*/

@Controller("InvestSaudiLastNewsComponentController")
@RequestMapping("/view/InvestSaudiLastNewsComponentController")
public class InvestSaudiLastNewsComponentController extends AbstractAcceleratorCMSComponentController<InvestSaudiLastNewsComponentModel> {

    @Resource
    private InvestSaudiNewsService investSaudiNewsService;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, InvestSaudiLastNewsComponentModel component) {
        int numberOfNews = component.getNumberOfNews();
        Collection<InvestSaudiNewsComponentModel> lastNews = investSaudiNewsService.getNews(numberOfNews);
        model.addAttribute("lastNews", lastNews);
    }
}
