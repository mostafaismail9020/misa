package com.investsaudi.portal.storefront.controllers.pages;

import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiNewsService;
import com.investsaudi.portal.core.service.utils.PaginationUtils;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.util.Config;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/news")
public class NewsPageController extends DefaultPageController {
    protected static final String NEWS_LABEL = "/news";
    private static final Logger LOG = Logger.getLogger(NewsPageController.class);

    @Resource
    private InvestSaudiNewsService investSaudiNewsService;

    @RequestMapping( method = {RequestMethod.GET})
    public String newsSearch(final Model model,
                                      final HttpServletRequest request, final HttpServletResponse response,
                                      @RequestParam(required = false, defaultValue = "0") Integer page)
            throws CMSItemNotFoundException {

        final int newsResultSize = Config.getInt("news.result.size", 9);
        SearchPageData<InvestSaudiNewsComponentModel> searchPageData = investSaudiNewsService.getAllNews(PaginationUtils.createPaginationData(page, newsResultSize));
        model.addAttribute("searchPageData", searchPageData);
        storeCmsPageInModel(model, getContentPageForRequest(request));
        return super.get(model,request,response);
    }

}
