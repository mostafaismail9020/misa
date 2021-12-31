package com.sap.ibso.eservices.storefront.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiEventsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiWebinarVideoComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiMediaCenterComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.core.model.media.MediaModel;

import java.util.List;

import javax.annotation.Resource;

import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.commons.collections.CollectionUtils;
import com.investsaudi.portal.core.service.InvestSaudiMediaCenterService;
import com.investsaudi.portal.core.service.utils.PaginationUtils;
import com.investsaudi.portal.core.model.PortalMediaCenterCarouselComponentModel;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sap.ibso.eservices.storefront.controllers.cms.AbstractAcceleratorCMSComponentController;


/**
 * 
 * @author BSV3KOR
 *
 */
@Controller("PortalMediaCenterCarouselComponentController")
@RequestMapping("/view/PortalMediaCenterCarouselComponentController")
public class PortalMediaCenterCarouselComponentController 
		extends AbstractAcceleratorCMSComponentController<PortalMediaCenterCarouselComponentModel>
{
	private static final Logger LOGGER = Logger.getLogger(PortalMediaCenterCarouselComponentController.class);
	
	@Resource
	private InvestSaudiMediaCenterService investSaudiMediaCenterService;
	 
	@Resource
	private ModelService modelService;
	    
	public static final String WEBINAR = "VIDEO_STORY";
	public static final String SUCCESS_STORY = "SUCCESS_STORY";    
	
	
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PortalMediaCenterCarouselComponentModel component)
	{
		LOGGER.debug("Entered into PortalMediaCenterCarouselComponentController");
		
		SearchPageData<InvestSaudiNewsComponentModel> newsPageData = investSaudiMediaCenterService.
				getAllNewsPage(PaginationUtils.createPaginationData(0, 1), null);
    	 
		SearchPageData<InvestSaudiEventsComponentModel> eventsPageData = investSaudiMediaCenterService.
	        	getAllEvents(PaginationUtils.createPaginationData(0, 1), null);
		 
		SearchPageData<InvestSaudiResourceComponentModel> resourcePageData = investSaudiMediaCenterService.
	        	getAllResource(PaginationUtils.createPaginationData(0, 1), null);
		 
        SearchPageData<InvestSaudiWebinarVideoComponentModel> webinarPageData = investSaudiMediaCenterService.
        		getAllVideos(PaginationUtils.createPaginationData(0, 1), WEBINAR);
        
        SearchPageData<InvestSaudiWebinarVideoComponentModel> successStoryPageData = investSaudiMediaCenterService.
        		getAllVideos(PaginationUtils.createPaginationData(0, 1), SUCCESS_STORY);
        
		
        InvestSaudiNewsComponentModel latestNews = CollectionUtils.isNotEmpty(newsPageData.getResults())? 
        		newsPageData.getResults().get(0):null;
        		
        InvestSaudiEventsComponentModel latestEvent = CollectionUtils.isNotEmpty(eventsPageData.getResults())? 
        		eventsPageData.getResults().get(0):null;
        		
        InvestSaudiResourceComponentModel latestResource = CollectionUtils.isNotEmpty(resourcePageData.getResults())?
        		resourcePageData.getResults().get(0):null;
        		
        InvestSaudiWebinarVideoComponentModel latestWebinar = CollectionUtils.isNotEmpty(webinarPageData.getResults())?
        		webinarPageData.getResults().get(0):null;
        		
        InvestSaudiWebinarVideoComponentModel latestSuccessStory = CollectionUtils.isNotEmpty(successStoryPageData.getResults())?
        		successStoryPageData.getResults().get(0):null;
	  	  
				          		
        List<InvestSaudiMediaCenterComponentModel> mediaCenterComponents = component.getMcComponents();
			if(null!= mediaCenterComponents && !mediaCenterComponents.isEmpty()) {
				for(InvestSaudiMediaCenterComponentModel mediaCenter : mediaCenterComponents)
				{
				  switch (mediaCenter.getUid()){
		            case "latestnews" :
		            	if(null != latestNews) {
		            		if(null != latestNews.getUid()) {
		            			mediaCenter.setMediaCenterCode(latestNews.getUid());
		            		}
		            		if(null != latestNews.getNewsTitle()) {
		            			mediaCenter.setMediaCenterTitle(latestNews.getNewsTitle());
		            		}
		            		if(null != latestNews.getNewsShortInformation()) {
		            			mediaCenter.setMediaCenterShortInformation(latestNews.getNewsShortInformation());
		            		}
		            		if(null != latestNews.getNewsDate()) {
		            			mediaCenter.setMediaCenterStartDate(latestNews.getNewsDate());
		            		}
		            		if(null != latestNews.getNewsDetailsImage()) {
		            			final MediaModel newsDetailImage = latestNews.getNewsDetailsImage();
				            	mediaCenter.setMediaCenterDetailsImage(newsDetailImage);
		            		}
		            		modelService.save(mediaCenter);
		            	}		            	
		            	break;
		            	
		            case "latestevents" :
		            	if(null != latestEvent) {
		            		if(null != latestEvent.getUid()) {
		            			mediaCenter.setMediaCenterCode(latestEvent.getUid());
		            		}
		            		if(null != latestEvent.getEventName()) {
		            			mediaCenter.setMediaCenterTitle(latestEvent.getEventName());
		            		}
		            		if(null != latestEvent.getEventShortInformation()) {
		            			mediaCenter.setMediaCenterShortInformation(latestEvent.getEventShortInformation());
		            		}
		            		if(null != latestEvent.getEventStartDate()) {
		            			mediaCenter.setMediaCenterStartDate(latestEvent.getEventStartDate());
		            		}
		            		if(null != latestEvent.getEventBannerImage()) {
		            			final MediaModel eventDetailImage = latestEvent.getEventBannerImage();
				            	mediaCenter.setMediaCenterDetailsImage(eventDetailImage);
		            		}
		            		modelService.save(mediaCenter);
		            	}
		            	break;
		            	
		            case "latestresources" :
		            	if(null != latestResource) {
		            		if(null != latestResource.getUid()) {
		            			mediaCenter.setMediaCenterCode(latestResource.getUid());
		            		}
		            		if(null != latestResource.getResourceTitle()) {
		            			mediaCenter.setMediaCenterTitle(latestResource.getResourceTitle());
		            		}
		            		if(null != latestResource.getResourceShortInformation()) {
		            			mediaCenter.setMediaCenterShortInformation(latestResource.getResourceShortInformation());
		            		}
		            	    if(null != latestResource.getResourceDate()) {
		            			mediaCenter.setMediaCenterStartDate(latestResource.getResourceDate());
		            		}
		            	    if(null != latestResource.getResourceDetailsImage()) {
		            	    	final MediaModel resourceDetailImage = latestResource.getResourceDetailsImage();
			            	    mediaCenter.setMediaCenterDetailsImage(resourceDetailImage);
		            		}
		            	    modelService.save(mediaCenter);
		            	}
		            	break;
		            	
		            case "latestwebinar" : 
		            	if(null!= latestWebinar && null != latestWebinar.getEmbedURL()) {		            		
		            		if(null != latestWebinar.getTitle()) {
		            			mediaCenter.setMediaCenterTitle(latestWebinar.getTitle());
		            		}
		            		if(null != latestWebinar.getShortDescription()) {
		            			mediaCenter.setMediaCenterShortInformation(latestWebinar.getShortDescription());
		            		}
		            		final CMSLinkComponentModel webinarLink = latestWebinar.getEmbedURL();
		            		if(null != webinarLink.getUrl()) {
		            			mediaCenter.setEmbedURL(webinarLink.getUrl());
		            		}
		            		if(null != latestWebinar.getVideosDetailsImage()) {
		            			final MediaModel webinarVideoDetailImage = latestWebinar.getVideosDetailsImage();
			            	    mediaCenter.setMediaCenterDetailsImage(webinarVideoDetailImage);
		            		}
		            		modelService.save(mediaCenter);
		            	} 
		            	break;
		            	
		            case "latestsuccessstory" : 
		            	if(null!= latestSuccessStory && null != latestSuccessStory.getEmbedURL()) {		            		
		            		if(null != latestSuccessStory.getTitle()) {
		            			mediaCenter.setMediaCenterTitle(latestSuccessStory.getTitle());
		            		}
		            		if(null != latestSuccessStory.getShortDescription()) {
		            			mediaCenter.setMediaCenterShortInformation(latestSuccessStory.getShortDescription());
		            		}
		            		final CMSLinkComponentModel successStoryLink = latestSuccessStory.getEmbedURL();
		            		if(null != successStoryLink.getUrl()) {
		            			mediaCenter.setEmbedURL(successStoryLink.getUrl());
		            		}
		            		if(null != latestSuccessStory.getVideosDetailsImage()) {
		            			final MediaModel webinarSuccessStoryDetailImage = latestSuccessStory.getVideosDetailsImage();
			            	    mediaCenter.setMediaCenterDetailsImage(webinarSuccessStoryDetailImage);
		            		}
		            		modelService.save(mediaCenter);
		            	} 
		            	break;		             
				  	}
				}
		  }
		  model.addAttribute("mediaCenterComponents", mediaCenterComponents);
		  LOGGER.debug("Exit into PortalMediaCenterCarouselComponentController");
	} 
}	 
