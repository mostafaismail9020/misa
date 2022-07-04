package com.sap.ibso.eservices.storefront.controllers.pages;

import com.google.gson.Gson;
import com.sap.ibso.eservices.facades.data.SagiaServiceData;
import com.sap.ibso.eservices.facades.data.SagiaServiceTabData;
import com.sap.ibso.eservices.facades.sagia.SagiaSearchFacade;
import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/service-search")
public class ServiceSearchController extends SagiaAbstractPageController {
    @Resource(name = "sagiaSearchFacade")
    SagiaSearchFacade sagiaSearchFacade;

    @Resource(name = "userService")
    private UserService userService;

    private static final String SERVICE_SEARCH_CMS_PAGE = "service-search";

    private static final String PATH_VARIABLE_PATTERN = "/{categoryLabel}";

    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    public String getServiceSearchCmsPage(final Model model) throws CMSItemNotFoundException {
        Map<String, List<SagiaServiceData>> serviceResultList = sagiaSearchFacade.getAllServices();
        model.addAttribute("SagiaServices",serviceResultList);
        storeCmsPageInModel(model, getContentPageForLabelOrId(SERVICE_SEARCH_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SERVICE_SEARCH_CMS_PAGE));
        return getViewForPage(model);
    }

    /**
     * Retrieve Services Based on Category Label
     *
     * @param categoryLabel
     * @param model
     * @return
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
    public String getSagiaServiceSearchCmsPage(@PathVariable("categoryLabel") final String categoryLabel, final Model model)
            throws  CMSItemNotFoundException {

        Map<String, List<SagiaServiceData>> serviceResultList = sagiaSearchFacade.getSagiaServicesByCategoryLabel(categoryLabel);
        model.addAttribute("SagiaServices",serviceResultList);
        storeCmsPageInModel(model, getContentPageForLabelOrId(SERVICE_SEARCH_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SERVICE_SEARCH_CMS_PAGE));
        return getViewForPage(model);
    }

    @RequestMapping(value = "/search/{text}", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public String searchText(@PathVariable(value = "text") String searchText) {
        if (StringUtils.isNotBlank(searchText)) {
            return new Gson().toJson(sagiaSearchFacade.searchServices(searchText));
        } else {
            return new Gson().toJson(sagiaSearchFacade.getAllServices());
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @RequireHardLogIn
    @ResponseBody
    public String getAll() {
        return new Gson().toJson(sagiaSearchFacade.getAllServices());
    }

    @RequestMapping(value = "/serviceDetails/{code}", method = RequestMethod.GET)
    @ResponseBody
    @RequireHardLogIn
    public String getServiceDetails(@PathVariable("code") String code) {
        List<SagiaServiceTabData> tabsList = sagiaSearchFacade.getServiceDetails(code);
        String tabsListJson = "";
        if (CollectionUtils.isNotEmpty(tabsList)) {
            tabsListJson = new Gson().toJson(tabsList);
        }
        return tabsListJson;
    }
}
