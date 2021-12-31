package com.investsaudi.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import de.hybris.platform.catalog.CatalogVersionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("tenant")
@RequestMapping("/events")
public class EventsPageController extends AbstractPageController {

    private static final String EVENTS_INDEX_CMS_PAGE = "blogIndex";

    public static final String EVENT_INDEX_PAGE_NAME = "Event Index Page";
    public static final String EVENTS_URL = "/events";
    public static final String EVENTS = "Events";
    public static final String SELECT_POST_PAGE = "SELECT {pk} FROM {ContentPage} WHERE {label} = ?postUid AND {catalogVersion}=?catalogVersionUid";

    private final static String CATALOG_ID = "investsaudiContentCatalog";
    private final static String VERSION_ONLINE = "Online";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private CatalogVersionService catalogVersionService;

    @Resource(name = "contentPageBreadcrumbBuilder")
    private ContentPageBreadcrumbBuilder contentPageBreadcrumbBuilder;

    @RequestMapping(method = RequestMethod.GET)
    public String getEvents(final Model model) throws CMSItemNotFoundException
    {
        final ContentPageModel pageForRequest = getContentPageForLabelOrId(EVENTS_INDEX_CMS_PAGE);
        storeCmsPageInModel(model, pageForRequest);
        setUpMetaDataForContentPage(model, pageForRequest);

        List<Breadcrumb> breadcrumbs = contentPageBreadcrumbBuilder.getBreadcrumbs(pageForRequest);
        List<Breadcrumb> updatedBreadCrumList = generateEventBreadCrumb(breadcrumbs);

        model.addAttribute(WebConstants.BREADCRUMBS_KEY, updatedBreadCrumList);

        return getViewForPage(model);
    }


    @RequestMapping(value = "/{postUid}", method = RequestMethod.GET)
    public String getPost(@PathVariable final String postUid, final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {

        final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(CATALOG_ID, VERSION_ONLINE);


        Map<String, String> paramsToQuery = new HashMap<>();
        paramsToQuery.put("postUid", "/events/" + postUid);
        paramsToQuery.put("catalogVersionUid", catalogVersion.getPk().toString());

        ContentPageModel pageFound = flexibleSearchService.<ContentPageModel>search(SELECT_POST_PAGE, paramsToQuery).getResult().iterator().next();

        if(pageFound != null ){
            storeCmsPageInModel(model, pageFound);
            setUpMetaDataForContentPage(model, pageFound);

            List<Breadcrumb> breadcrumbs = contentPageBreadcrumbBuilder.getBreadcrumbs(pageFound);
            List<Breadcrumb> updatedBreadCrumList = generateEventBreadCrumb(breadcrumbs);

            model.addAttribute(WebConstants.BREADCRUMBS_KEY, updatedBreadCrumList);

        }

        return getViewForPage(model);
    }

    private List<Breadcrumb> generateEventBreadCrumb(List<Breadcrumb> breadcrumbs) {

        List<Breadcrumb> updatedBreadCrumList = new ArrayList<>();
        for (Breadcrumb currentPageBreadCrumb: breadcrumbs) {

            if(currentPageBreadCrumb.getName().equals(EVENT_INDEX_PAGE_NAME)){
                currentPageBreadCrumb.setName(EVENTS);
                updatedBreadCrumList.add(0, currentPageBreadCrumb);
                break;
            }else{
                updatedBreadCrumList.add(0, new Breadcrumb(EVENTS_URL, EVENTS,"active"));
                updatedBreadCrumList.add(1, currentPageBreadCrumb);
            }
        }
        return updatedBreadCrumList;
    }

}
