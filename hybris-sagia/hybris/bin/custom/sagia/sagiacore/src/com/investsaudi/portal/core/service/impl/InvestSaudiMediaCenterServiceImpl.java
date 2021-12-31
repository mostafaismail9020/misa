package com.investsaudi.portal.core.service.impl;

import com.investsaudi.portal.core.dao.InvestSaudiMediaCenterDao;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiEventsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiWebinarVideoComponentModel;
import com.investsaudi.portal.core.service.InvestSaudiMediaCenterService;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import javax.annotation.Resource;
import java.util.Collection;
import com.sap.ibso.eservices.core.enums.InvestSaudiVideoType;
import java.util.Date;
import java.util.List;

public class InvestSaudiMediaCenterServiceImpl implements InvestSaudiMediaCenterService {

    @Resource
    private InvestSaudiMediaCenterDao investSaudiMediaCenterDao;


    @Override
    public SearchPageData<InvestSaudiNewsComponentModel> getAllNews(PaginationData paginationData) {
        return investSaudiMediaCenterDao.getAllNews(paginationData);
    }

    @Override
    public Collection<InvestSaudiNewsComponentModel> getNews(int number) {
        return investSaudiMediaCenterDao.getNews(number);
    }
    
    
    @Override
    public SearchPageData<InvestSaudiResourceComponentModel> getAllResource(PaginationData paginationData, Boolean isCurrentYear) {
        return investSaudiMediaCenterDao.getAllResource(paginationData, isCurrentYear);
    }
    
    @Override
    public InvestSaudiResourceComponentModel getResourceDetails(String resourceCode) {
        return investSaudiMediaCenterDao.getResourceDetails(resourceCode);
    }
    
    @Override
    public SearchPageData<InvestSaudiEventsComponentModel> getAllEvents(PaginationData paginationData, Boolean isCurrentYear) {
        return investSaudiMediaCenterDao.getAllEvents(paginationData, isCurrentYear);
    }
    
    @Override
    public InvestSaudiEventsComponentModel getEventDetails(String eventCode) {
    	return investSaudiMediaCenterDao.getEventDetails(eventCode);
    }
    
    @Override
    public SearchPageData<InvestSaudiWebinarVideoComponentModel> getAllVideos(PaginationData paginationData, String videoType) {
    	return investSaudiMediaCenterDao.getAllVideos(paginationData, videoType);
    }
    
    @Override
    public SearchPageData<InvestSaudiNewsComponentModel> getAllNewsPage(PaginationData paginationData, Boolean isCurrentYear) {
        return investSaudiMediaCenterDao.getAllNewsPage(paginationData, isCurrentYear);
    }

    @Override
   public InvestSaudiNewsComponentModel getNewsDetailsPage(String newsTitle)
   {
    	return investSaudiMediaCenterDao.getNewsDetailsPage(newsTitle);
   }
    
   public List<InvestSaudiNewsComponentModel> getRecentNews(Date newsDate)
   {
	   return investSaudiMediaCenterDao.getRecentNews(newsDate);
   }
   
   public List<InvestSaudiResourceComponentModel> getrecentResourceDetails(Date resourceDate)
   {
	   return investSaudiMediaCenterDao.getrecentResourceDetails(resourceDate);
   }
   
   public List<InvestSaudiEventsComponentModel> getrecentEventDetails(Date eventStartDate)
   {
	   return investSaudiMediaCenterDao.getrecentEventDetails(eventStartDate);
   }
}
