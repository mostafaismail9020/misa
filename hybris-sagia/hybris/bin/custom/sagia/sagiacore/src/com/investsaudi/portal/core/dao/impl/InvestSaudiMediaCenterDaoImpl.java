package com.investsaudi.portal.core.dao.impl;

import com.investsaudi.portal.core.dao.InvestSaudiMediaCenterDao;
import com.investsaudi.portal.core.model.InvestSaudiEventsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiNewsComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiResourceComponentModel;
import com.investsaudi.portal.core.model.InvestSaudiWebinarVideoComponentModel;
import com.sap.ibso.eservices.core.enums.InvestSaudiVideoType;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchParameter;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvestSaudiMediaCenterDaoImpl extends DefaultGenericDao<InvestSaudiNewsComponentModel> implements InvestSaudiMediaCenterDao {

    private static final Logger LOG = Logger.getLogger(InvestSaudiMediaCenterDaoImpl.class);

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    private PaginatedFlexibleSearchService paginatedFlexibleSearchService;

    @Resource
    private CatalogVersionService catalogVersionService;

    private static final String CATALOG_ID = "sagiaContentCatalog";
    private static final String ONLINE = "Online";

    private static final String QUERY_ALL_NEWS_COMPONENT = "SELECT {p:pk} FROM {InvestSaudiNewsComponent AS p}";
    private static final String QUERY_FIRST_NEWS_COMPONENT = "SELECT {p:pk} FROM {InvestSaudiNewsComponent AS p} ORDER BY {p.newsDate} DESC";
    private static final String QUERY_NEWS_PAGE_COMPONENT = "SELECT {p:pk} FROM {InvestSaudiNewsComponent AS p} "
    		+ "WHERE {p.uid}=?uid AND {p.catalogVersion} = ?catalogVersion";

    private static final String QUERY_ALL_RESOURCE_COMPONENT = "SELECT {r:pk} FROM {InvestSaudiResourceComponent! AS r}";
    private static final String QUERY_RESOURCE_DETAILS_COMPONENT = "SELECT {r:pk} FROM {InvestSaudiResourceComponent AS r} "
    		+ "WHERE {r.uid}=?uid AND {r.catalogVersion} = ?catalogVersion" ;

    private static final String QUERY_ALL_EVENTS_COMPONENT = "SELECT {e:pk} FROM {InvestSaudiEventsComponent AS e}";
    private static final String QUERY_EVENT_DETAILS_COMPONENT = "SELECT {e:pk} FROM {InvestSaudiEventsComponent AS e} "
    		+ "WHERE {e.uid}=?uid AND {e.catalogVersion} = ?catalogVersion";

    private static final String QUERY_ALL_VIDEO_COMPONENT = "SELECT {v:pk} FROM {InvestSaudiWebinarVideoComponent AS v}";


    private static final String QUERY_RECENT_NEWS_COMPONENT = "SELECT {p:pk} FROM {InvestSaudiNewsComponent AS p} "
    		+ "WHERE {p.newsDate} < ?newsDate AND {p.catalogVersion} = ?catalogVersion ORDER BY {p.newsDate} DESC LIMIT 2";

    private static final String QUERY_RECENT_RESOURCES_COMPONENT = "SELECT {r:pk} FROM {InvestSaudiResourceComponent! AS r} "
    		+ "WHERE {r.resourceDate} < ?resourceDate AND {r.catalogVersion} = ?catalogVersion ORDER BY {r.resourceDate} DESC LIMIT 2";

    private static final String QUERY_RECENT_EVENTS_COMPONENT = "SELECT {e:pk} FROM {InvestSaudiEventsComponent AS e} "
    		+ "WHERE {e.eventStartDate} < ?eventStartDate AND {e.catalogVersion} = ?catalogVersion ORDER BY {e.eventStartDate} DESC LIMIT 2";

    private static final String QUERY_REGION_NEWS_COMPONENT = "SELECT {p.PK} FROM {InvestSaudiNewsComponent AS p JOIN ProvinceNewsRel as rel ON {p:PK} = {rel:target} JOIN ProvinceComponent AS c ON {rel:source} = {c:PK} } WHERE {c.uid} =?uid AND {p.catalogVersion} = ?catalogVersion AND {p:regionSpecific} =?regionSpecific ORDER BY {p.newsDate} DESC";

    private static final String QUERY_REGION_RESOURCES_COMPONENT = "SELECT {p.PK} FROM {InvestSaudiResourceComponent! AS p JOIN ProvinceResourcesRel as rel ON {p:PK} = {rel:target} JOIN ProvinceComponent AS c ON {rel:source} = {c:PK} } WHERE {c.uid} =?uid AND {p.catalogVersion} = ?catalogVersion AND {p:regionSpecific} =?regionSpecific ORDER BY {p.resourceDate} DESC";

	private static final String QUERY_REGION_EVENTS_COMPONENT = "SELECT {p.PK} FROM {InvestSaudiEventsComponent AS p JOIN ProvinceEventsRel as rel ON {p:PK} = {rel:target} JOIN ProvinceComponent AS c ON {rel:source} = {c:PK} } WHERE {c.uid} =?uid AND {p.catalogVersion} = ?catalogVersion AND {p:regionSpecific} =?regionSpecific ORDER BY {p.eventStartDate} DESC";

	private static final String QUERY_REGION_OPPORTUNITIES = "SELECT {p.PK} FROM {OpportunityProduct AS p JOIN ProvinceProductsRel as rel ON {p:PK} = {rel:target} JOIN ProvinceComponent AS c ON {rel:source} = {c:PK} } WHERE {c.uid} =?uid AND {p.catalogVersion} = ?catalogVersion AND {p:regionSpecific} =?regionSpecific ORDER BY {p.creationtime} DESC";

    public InvestSaudiMediaCenterDaoImpl(String typecode) {
        super(typecode);
    }

    @Override
    public SearchPageData<InvestSaudiNewsComponentModel> getAllNews(PaginationData paginationData) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_ALL_NEWS_COMPONENT);

        SearchPageData<InvestSaudiNewsComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    @Override
    public Collection<InvestSaudiNewsComponentModel> getNews(int number) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_FIRST_NEWS_COMPONENT);
        searchQuery.setCount(number);
        final SearchResult<InvestSaudiNewsComponentModel> resultList = flexibleSearchService.search(searchQuery);

        return null != resultList ? resultList.getResult() : new ArrayList<>();
    }

    @Override
    public SearchPageData<InvestSaudiResourceComponentModel> getAllResource(PaginationData paginationData, Boolean isCurrentYear) {

    	StringBuilder query = new StringBuilder(QUERY_ALL_RESOURCE_COMPONENT);

        SearchPageData<InvestSaudiResourceComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        if(isCurrentYear != null) {
	        if(isCurrentYear)
	        {
	            query.append(" WHERE {r.resourceDate} >= ?resourceDate");

	        }else {
	            query.append(" WHERE {r.resourceDate} < ?resourceDate");
	        }
	        query.append(" AND {r.catalogVersion} = ?catalogVersion  ORDER BY {r.resourceDate} DESC");
	        //final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        }
        else
        {
        	query.append(" WHERE {r.catalogVersion} = ?catalogVersion  ORDER BY {r.resourceDate} DESC");
        }

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        if(isCurrentYear != null) {
	        Date resourceDate = getDate();
	        searchQuery.addQueryParameter("resourceDate", resourceDate);
        }

        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

        LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE)+" query: "+searchQuery.getQuery());
        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    @Override
    public InvestSaudiResourceComponentModel getResourceDetails(String resourceCode) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", resourceCode);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_RESOURCE_DETAILS_COMPONENT, params);
        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

        final SearchResult<InvestSaudiResourceComponentModel> resultList = flexibleSearchService.search(searchQuery);

        return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null;
    }

    @Override
    public SearchPageData<InvestSaudiEventsComponentModel> getAllEvents(PaginationData paginationData, Boolean isCurrentYear) {

        StringBuilder query = new StringBuilder(QUERY_ALL_EVENTS_COMPONENT);
        SearchPageData<InvestSaudiEventsComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        if(isCurrentYear != null)
        {
	        if(isCurrentYear)
	        {
	            query.append(" WHERE {e.eventStartDate} >= ?eventStartDate");

	        }else {
	            query.append(" WHERE {e.eventStartDate} < ?eventStartDate");

	        }
	        query.append(" AND {e.catalogVersion} = ?catalogVersion  ORDER BY {e.eventStartDate} DESC");
	        //final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        }
        else {
        	query.append("WHERE {e.catalogVersion} = ?catalogVersion  ORDER BY {e.eventStartDate} DESC");
        }

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        Date eventStartDate = getUpcomingEventDate();
        searchQuery.addQueryParameter("eventStartDate", eventStartDate);
        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    @Override
    public InvestSaudiEventsComponentModel getEventDetails(String eventCode) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", eventCode);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_EVENT_DETAILS_COMPONENT, params);
        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

        final SearchResult<InvestSaudiEventsComponentModel> resultList = flexibleSearchService.search(searchQuery);

        return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null;
    }

    @Override
    public SearchPageData<InvestSaudiWebinarVideoComponentModel> getAllVideos(PaginationData paginationData, String videoType) {

        StringBuilder query = new StringBuilder(QUERY_ALL_VIDEO_COMPONENT);
        SearchPageData<InvestSaudiWebinarVideoComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        query.append(" WHERE {v.videoType} = ?investSaudiVideoType AND {v.catalogVersion} = ?catalogVersion  ORDER BY {v.modifiedtime} DESC");

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
        LOG.info("Before entered into VideoType=" + getVideoTypeEnum(videoType));
        searchQuery.addQueryParameter("investSaudiVideoType", getVideoTypeEnum(videoType));
        PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    private InvestSaudiVideoType getVideoTypeEnum(String videoType)
    {
    	LOG.info("videoType=" +videoType);
    	if(InvestSaudiVideoType.VIDEO_STORY.getCode().equalsIgnoreCase(videoType))
    	{
    		return InvestSaudiVideoType.VIDEO_STORY;

    	} else	{
    		return InvestSaudiVideoType.SUCCESS_STORY;
    	}
    }

    @Override
    public SearchPageData<InvestSaudiNewsComponentModel> getAllNewsPage(PaginationData paginationData, Boolean isCurrentYear) {

    	StringBuilder query = new StringBuilder(QUERY_ALL_NEWS_COMPONENT);
        SearchPageData<InvestSaudiNewsComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        if(isCurrentYear != null) {
	        if(isCurrentYear)
	        {
	            query.append(" WHERE {p.newsDate} >= ?newsDate");
	        } else {
	            query.append(" WHERE {p.newsDate} < ?newsDate");
	        }

	        query.append(" AND {p.catalogVersion} = ?catalogVersion  ORDER BY {p.newsDate} DESC");
	        //final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        }
        else {
        	 query.append(" WHERE {p.catalogVersion} = ?catalogVersion  ORDER BY {p.newsDate} DESC");
        }

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        Date currentYearFirstDate = getDate();
        searchQuery.addQueryParameter("newsDate", currentYearFirstDate);
     	searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

     	LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
     	PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

    public InvestSaudiNewsComponentModel getNewsDetailsPage(String newsCode)
    {
	   final Map<String, Object> params = new HashMap<String, Object>();
	   params.put("uid", newsCode);
       final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_NEWS_PAGE_COMPONENT, params);

       searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

       final SearchResult<InvestSaudiNewsComponentModel> resultList = flexibleSearchService.search(searchQuery);
       return (null != resultList && resultList.getResult().size() > 0) ? resultList.getResult().get(0) : null;
    }


   public List<InvestSaudiNewsComponentModel> getRecentNews(Date newsDate)
   {
	   final Map<String, Object> params = new HashMap<String, Object>();
	   params.put("newsDate", newsDate);
       final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_RECENT_NEWS_COMPONENT, params);

       searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
       LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
       final SearchResult<InvestSaudiNewsComponentModel> resultList = flexibleSearchService.search(searchQuery);

       return resultList.getResult();
   }

   public List<InvestSaudiResourceComponentModel> getrecentResourceDetails(Date resourceDate)
   {
	   final Map<String, Object> params = new HashMap<String, Object>();
	   params.put("resourceDate", resourceDate);
       final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_RECENT_RESOURCES_COMPONENT, params);

       searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
       LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
       final SearchResult<InvestSaudiResourceComponentModel> resultList = flexibleSearchService.search(searchQuery);

       return resultList.getResult();
   }

   public List<InvestSaudiEventsComponentModel> getrecentEventDetails(Date eventStartDate)
   {
	   final Map<String, Object> params = new HashMap<String, Object>();
	   params.put("eventStartDate", eventStartDate);
       final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(QUERY_RECENT_EVENTS_COMPONENT, params);

       searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
       LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
       final SearchResult<InvestSaudiEventsComponentModel> resultList = flexibleSearchService.search(searchQuery);

       return resultList.getResult();
   }

   private Date getDate()
   {
	   Date date = new Date();
	   Calendar calendar = new GregorianCalendar();
	   calendar.setTime(date);
	   int year = calendar.get(Calendar.YEAR);

    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.DAY_OF_YEAR, 1);

    	return  cal.getTime();
   }

	 private Date getUpcomingEventDate()
   {
	   Date date = new Date();
    	return  date;
   }

	@Override
    public SearchPageData<InvestSaudiNewsComponentModel> getRegionNews(PaginationData paginationData, String provinceId){

		StringBuilder query = new StringBuilder(QUERY_REGION_NEWS_COMPONENT);
		SearchPageData<InvestSaudiNewsComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameter("uid", provinceId);
        searchQuery.addQueryParameter("regionSpecific", Boolean.TRUE);
     	searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

     	LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
     	PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

	@Override
    public SearchPageData<InvestSaudiResourceComponentModel> getRegionResources(PaginationData paginationData, String provinceId){

		StringBuilder query = new StringBuilder(QUERY_REGION_RESOURCES_COMPONENT);
		SearchPageData<InvestSaudiResourceComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameter("uid", provinceId);
        searchQuery.addQueryParameter("regionSpecific", Boolean.TRUE);
     	searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

     	LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
     	PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }

	@Override
    public SearchPageData<InvestSaudiEventsComponentModel> getRegionEvents(PaginationData paginationData, String provinceId){

		StringBuilder query = new StringBuilder(QUERY_REGION_EVENTS_COMPONENT);
		SearchPageData<InvestSaudiEventsComponentModel> searchPageData = new SearchPageData<>();
        searchPageData.setPagination(paginationData);

        final FlexibleSearchQuery  searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameter("uid", provinceId);
        searchQuery.addQueryParameter("regionSpecific", Boolean.TRUE);
     	searchQuery.addQueryParameter("catalogVersion", catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));

     	LOG.info("catalogVersion :"+catalogVersionService.getCatalogVersion(CATALOG_ID, ONLINE));
     	PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
        parameter.setFlexibleSearchQuery(searchQuery);
        parameter.setSearchPageData(searchPageData);

        return paginatedFlexibleSearchService.search(parameter);
    }
}
