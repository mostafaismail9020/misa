package com.investsaudi.portal.core.dao;

import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiEventsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiWebinarVideoComponentModel;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import java.util.Collection;
import com.sap.ibso.eservices.core.enums.InvestSaudiVideoType;
import java.util.Date;
import java.util.List;

public interface InvestSaudiMediaCenterDao {
    /**
     * returns all the InvestSaudiNewsComponents present in the System
     *
     *
     * @return a collection of all InvestSaudiNewsComponentsModel
     */
    SearchPageData<InvestSaudiNewsComponentModel> getAllNews(PaginationData paginationData);

    /**
     * returns  the first number InvestSaudiNewsComponents ordered by newsDate
     *
     * @param number the number of InvestSaudiNewsComponentsModel to retrieve
     * @return a collection ot the first number InvestSaudiNewsComponentsModel
     */
    Collection<InvestSaudiNewsComponentModel> getNews(int number);

    
    SearchPageData<InvestSaudiResourceComponentModel> getAllResource(PaginationData paginationData, Boolean isCurrentYear);
    
    InvestSaudiResourceComponentModel getResourceDetails(String resourceCode);
    
    SearchPageData<InvestSaudiEventsComponentModel> getAllEvents(PaginationData paginationData, Boolean isCurrentYear);
    
    InvestSaudiEventsComponentModel getEventDetails(String eventCode);
    
    SearchPageData<InvestSaudiWebinarVideoComponentModel> getAllVideos(PaginationData paginationData ,String videoType);
    
    SearchPageData<InvestSaudiNewsComponentModel> getAllNewsPage(PaginationData paginationData, Boolean isCurrentYear);
    
    InvestSaudiNewsComponentModel getNewsDetailsPage(String newsTitle);
    
    List<InvestSaudiNewsComponentModel> getRecentNews(Date newsDate);
    
    List<InvestSaudiResourceComponentModel> getrecentResourceDetails(Date resourceDate);
    
    List<InvestSaudiEventsComponentModel> getrecentEventDetails(Date eventStartDate);
    
    SearchPageData<InvestSaudiNewsComponentModel> getRegionNews(PaginationData paginationData, String provinceId);
    
    SearchPageData<InvestSaudiResourceComponentModel> getRegionResources(PaginationData paginationData, String provinceId);
    
    SearchPageData<InvestSaudiEventsComponentModel> getRegionEvents(PaginationData paginationData, String provinceId);

}
