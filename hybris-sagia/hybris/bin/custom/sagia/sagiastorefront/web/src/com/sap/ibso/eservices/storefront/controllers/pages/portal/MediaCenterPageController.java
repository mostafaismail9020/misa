package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiEventsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiWebinarVideoComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiMediaCenterService;
import com.investsaudi.portal.core.service.utils.PaginationUtils;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.util.Config;
//import com.investsaudi.portal.core.service.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sap.ibso.eservices.core.enums.InvestSaudiVideoType;
import com.sap.ibso.eservices.storefront.controllers.pages.portal.DefaultPageController;

import java.util.Collection;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

import de.hybris.platform.servicelayer.media.MediaService;
import java.io.InputStream;
import java.io.IOException;
import de.hybris.platform.core.model.media.MediaContainerModel;

import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

@Controller
@RequestMapping(value = "/mediaCenter")
public class MediaCenterPageController extends AbstractPageController {
   
    private static final Logger LOG = Logger.getLogger(MediaCenterPageController.class);
    
    protected static final String HOMEPAGE_LABEL = "/mediaCenter";
    protected static final String NEWS_LABEL = "/mediaCenter/news";
    protected static final String RESOURCES_LABEL = "/mediaCenter/resources";
    protected static final String EVENTS_LABEL = "/mediaCenter/events";
    protected static final String VIDEOS_LABEL = "/mediaCenter/videos";
    
    protected static final String RESOURCES_CODE_PATH_VARIABLE_PATTERN = "/resources/{resourceCode}";
    protected static final String EVENTS_CODE_PATH_VARIABLE_PATTERN = "/events/{eventCode}";
    protected static final String NEWS_CODE_PATH_VARIABLE_PATTERN = "/news/{newsCode}";
    
    private static final String RESOURCES_DETAILS_PAGE = "/resources/resource-details";
    private static final String EVENTS_DETAILS_PAGE = "/events/event-details";			
    private static final String NEWS_DETAILS_PAGE = "/news/news-details";
	    
    public static final String VIDEO_WEBINAR = "VIDEO_STORY";
    public static final String SUCCESS_STORY = "SUCCESS_STORY";
    public static final String BREADCRUMBS_KEY = "breadcrumbs";   
    
    @Resource
    private InvestSaudiMediaCenterService investSaudiMediaCenterService;
    
    @Resource(name = "contentPageBreadcrumbBuilder")
    private ContentPageBreadcrumbBuilder contentPageBreadcrumbBuilder;  
    
    @Resource
	private MediaService mediaService;
    
    @Resource
    private CMSSiteService cmsSiteService;
    
    @Resource
    private CommonI18NService commonI18NService;
    
    
    @RequestMapping(method = {RequestMethod.GET})
    public String homePage(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException
    {
    	SearchPageData<InvestSaudiNewsComponentModel> newsPageData = investSaudiMediaCenterService
         		.getAllNewsPage(PaginationUtils.createPaginationData(0, 3), null);
    	 
    	SearchPageData<InvestSaudiEventsComponentModel> eventsPageData = investSaudiMediaCenterService.
         		getAllEvents(PaginationUtils.createPaginationData(0, 3), null);
    	 
        SearchPageData<InvestSaudiResourceComponentModel> resourcePageData = investSaudiMediaCenterService.
        		getAllResource(PaginationUtils.createPaginationData(0, 3), null);
        
        SearchPageData<InvestSaudiWebinarVideoComponentModel> webinarPageData = investSaudiMediaCenterService.
        		getAllVideos(PaginationUtils.createPaginationData(0, 3), VIDEO_WEBINAR);
        
        SearchPageData<InvestSaudiWebinarVideoComponentModel> successStoryPageData = investSaudiMediaCenterService.
        		getAllVideos(PaginationUtils.createPaginationData(0, 3), SUCCESS_STORY);
        
        model.addAttribute("resourcePageData", resourcePageData);
        model.addAttribute("eventsPageData", eventsPageData);
        model.addAttribute("newsPageData", newsPageData);
        model.addAttribute("webinarPageData", webinarPageData);
        model.addAttribute("successStoryPageData", successStoryPageData);
               
        final ContentPageModel contentPage = getContentPageForLabelOrId(HOMEPAGE_LABEL);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);		
		storeContentPageTitleInModel(model, contentPage.getTitle());
		
		return getViewForPage(model);
    }    
    
    @RequestMapping(value = "/resources", method = {RequestMethod.GET})
    public String resourcesSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response,
                  @RequestParam(required = false, defaultValue = "0") Integer page,
                  @RequestParam(required = false, defaultValue ="true") boolean isCurrentYear)
            throws CMSItemNotFoundException 
    {
        final int newsResultSize = Config.getInt("news.result.size", 9);
        SearchPageData<InvestSaudiResourceComponentModel> searchPageData = investSaudiMediaCenterService.
        		getAllResource(PaginationUtils.createPaginationData(page, newsResultSize), isCurrentYear);
        model.addAttribute("searchPageData", searchPageData);
       
        final ContentPageModel contentPage = getContentPageForLabelOrId(RESOURCES_LABEL);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPage.getTitle());
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPage));
		
		return getViewForPage(model);
    }
    
    @RequestMapping(value = RESOURCES_CODE_PATH_VARIABLE_PATTERN, method = {RequestMethod.GET})
    public String resourceDetails(@PathVariable("resourceCode") final String resourceCode, final Model model,
    			final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException 
    {
    	LOG.info("resourceCode=" + resourceCode);
    	
    	InvestSaudiResourceComponentModel resourceDetails = null;
    	List<InvestSaudiResourceComponentModel> recentResourceDetails = null;
    	
        if (null != resourceCode) {
	        resourceDetails = investSaudiMediaCenterService.getResourceDetails(XSSFilterUtil.filter(resourceCode));
	        if (null != resourceDetails) {
	        	recentResourceDetails= investSaudiMediaCenterService.getrecentResourceDetails(resourceDetails.getResourceDate());
	        }
	    }
                    
        model.addAttribute("resourceDetails", resourceDetails);
        model.addAttribute("recentResourceDetails", recentResourceDetails);
        
        ContentPageModel contentPageModel = getContentPageForLabelOrId(RESOURCES_DETAILS_PAGE);     
    	model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
		storeCmsPageInModel(model, contentPageModel);
		setUpMetaDataForContentPage(model, contentPageModel);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPageModel.getTitle());
			
		return getViewForPage(model);        
    }

    @RequestMapping(value = "/events", method = {RequestMethod.GET})
    public String eventsSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response,
                  @RequestParam(required = false, defaultValue = "0") Integer page,
                  @RequestParam(required = false, defaultValue ="true") boolean isCurrentYear)
            throws CMSItemNotFoundException 
    {
        final int newsResultSize = Config.getInt("news.result.size", 9);
        SearchPageData<InvestSaudiEventsComponentModel> searchPageData = investSaudiMediaCenterService.
        		getAllEvents(PaginationUtils.createPaginationData(page, newsResultSize), isCurrentYear);
        
        model.addAttribute("searchPageData", searchPageData);
        final ContentPageModel contentPage = getContentPageForLabelOrId(EVENTS_LABEL);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPage));
		storeContentPageTitleInModel(model, contentPage.getTitle());
		
		return getViewForPage(model);
    }
    
    @RequestMapping(value = EVENTS_CODE_PATH_VARIABLE_PATTERN, method = {RequestMethod.GET})
    public String eventDetails(@PathVariable("eventCode") final String eventCode, final Model model,
    			final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException 
    {	
    	LOG.info("eventCode=" + eventCode);

    	InvestSaudiEventsComponentModel eventDetails = null;
    	List<InvestSaudiEventsComponentModel> recentEventDetails = null;
        if (null != eventCode) {
        	
        	eventDetails= investSaudiMediaCenterService.getEventDetails(XSSFilterUtil.filter((eventCode)));
        	if (null != eventDetails) {
        		recentEventDetails = investSaudiMediaCenterService.getrecentEventDetails(eventDetails.getEventStartDate());
        	}
        }                      
        model.addAttribute("eventDetails", eventDetails);
        model.addAttribute("recentEventDetails", recentEventDetails);
        
        ContentPageModel contentPageModel = getContentPageForLabelOrId(EVENTS_DETAILS_PAGE);
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
		storeCmsPageInModel(model, contentPageModel);
		setUpMetaDataForContentPage(model, contentPageModel);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPageModel.getTitle());
		
		return getViewForPage(model);
    }
    
    @RequestMapping(value = "/videos", method = {RequestMethod.GET})
    public String videosPage(final Model model, final HttpServletRequest request, final HttpServletResponse response,
                  @RequestParam(required = false, defaultValue = "0") Integer page,
                  @RequestParam(required = false, defaultValue = "VIDEO_STORY") String videoType)
            throws CMSItemNotFoundException 
    {
        final int newsResultSize = Config.getInt("news.result.size", 4);
        SearchPageData<InvestSaudiWebinarVideoComponentModel> searchPageData = investSaudiMediaCenterService.
        		getAllVideos(PaginationUtils.createPaginationData(page, newsResultSize), videoType);
        model.addAttribute("searchPageData", searchPageData);
		
        final ContentPageModel contentPage = getContentPageForLabelOrId(VIDEOS_LABEL);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPage.getTitle());
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPage));
		
		return getViewForPage(model);
    }
    	
    @RequestMapping(value = "/news", method = {RequestMethod.GET})
    public String newsSearch(final Model model, final HttpServletRequest request, final HttpServletResponse response,
                  @RequestParam(required = false, defaultValue = "0") Integer page, 
                  @RequestParam(required = false, defaultValue ="true") boolean isCurrentYear)
            throws CMSItemNotFoundException
    {
        final int newsResultSize = Config.getInt("news.result.size", 9);
        SearchPageData<InvestSaudiNewsComponentModel> searchPageData = 
        		investSaudiMediaCenterService.getAllNewsPage(PaginationUtils.createPaginationData(page, newsResultSize), isCurrentYear);
        model.addAttribute("searchPageData", searchPageData);
        
        final ContentPageModel contentPage = getContentPageForLabelOrId(NEWS_LABEL);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPage.getTitle());
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPage));
		
		return getViewForPage(model);
    }
    
    @RequestMapping(value = NEWS_CODE_PATH_VARIABLE_PATTERN, method = {RequestMethod.GET})
    public String newsPage(@PathVariable("newsCode") final String newsCode, final Model model,
                                      final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException
    {        
    	LOG.info("newsCode=" + newsCode);
    	
    	InvestSaudiNewsComponentModel newsDetails = null;
    	List<InvestSaudiNewsComponentModel> recentNews = null;
        if (null != newsCode) {
        	newsDetails = investSaudiMediaCenterService.getNewsDetailsPage(XSSFilterUtil.filter(newsCode));
        	if (null != newsDetails) {
        		recentNews = investSaudiMediaCenterService.getRecentNews(newsDetails.getNewsDate());
        	}
        }
    	model.addAttribute("newsDetails", newsDetails);
    	model.addAttribute("recentNews", recentNews);        
       		
        ContentPageModel contentPageModel = getContentPageForLabelOrId(NEWS_DETAILS_PAGE);
        model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(contentPageModel));
		storeCmsPageInModel(model, contentPageModel);
		setUpMetaDataForContentPage(model, contentPageModel);
		//updatePageTitle(model, contentPage);
		storeContentPageTitleInModel(model, contentPageModel.getTitle());
		
		return getViewForPage(model);
    }
    
    @RequestMapping(value = "/downloadResoruce/{resourceCode}", method = RequestMethod.GET)
    public void getResourceFile(@PathVariable("resourceCode") final String resourceCode, final Model model,
            final HttpServletRequest request, final HttpServletResponse response)
    {
    	LOG.info("Entered into resource file service controller");
    	
    	InputStream is = null;
    	String fileName = null;
    	 
    	try {
    		InvestSaudiResourceComponentModel resourceModel = null;    		
    		resourceModel  = investSaudiMediaCenterService.getResourceDetails(resourceCode);
    		MediaContainerModel containerModel = resourceModel.getResourceFullReport(); 	
    		
    		final Collection<MediaModel> mediaModels = containerModel.getMedias();           
    		if (CollectionUtils.isNotEmpty(mediaModels))
    		{
    			for (final MediaModel media : mediaModels)
    			{
    				LOG.info("media code: " +media.getCode());
    				LOG.info("current Language: " +commonI18NService.getCurrentLanguage().getName());    				
    				if (media != null && media.getCode().toLowerCase().contains(commonI18NService.getCurrentLanguage().getName().toLowerCase()))
    				{
    					LOG.info("entered into media code and Language");	
    					is = mediaService.getStreamFromMedia(media);
    					fileName = media.getRealFileName();
    				}
    			}	
    		}

    		// copy it to response's OutputStream
    		if(is != null)	
    		{    		
    			response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename="+fileName);

    			LOG.info("before downloading the file");	
    			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
    			response.getOutputStream().flush();
    		}
    		LOG.info("Exit from resource file service controller");

    	} catch (IOException ex) {
    		LOG.info("Error writing file to output stream. Filename was '{}'"+ ex);
    		throw new RuntimeException("IOError writing file to output stream");
    	}
    }       
}
