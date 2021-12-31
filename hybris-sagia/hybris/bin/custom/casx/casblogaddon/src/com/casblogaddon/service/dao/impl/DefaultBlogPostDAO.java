package com.casblogaddon.service.dao.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.casblogaddon.enums.BlogType;
import com.casblogaddon.model.BlogPostComponentModel;
import com.casblogaddon.model.components.FeaturedPostComponentModel;
import com.casblogaddon.service.dao.BlogPostDAO;

import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.paginated.PaginatedFlexibleSearchParameter;
import de.hybris.platform.servicelayer.search.paginated.dao.impl.DefaultPaginatedGenericDao;
import org.apache.commons.collections.CollectionUtils;


/**
 * The Class DefaultBlogPostDAO.
 *
 * @author Sumit Gupta
 */
public class DefaultBlogPostDAO extends DefaultPaginatedGenericDao<BlogPostComponentModel>
		implements BlogPostDAO<BlogPostComponentModel>
{
	/**
	 * Instantiates a new default blog post DAO.
	 *
	 * @param typeCode the type code
	 */
	public DefaultBlogPostDAO(final String typeCode)
	{
		super(typeCode);
	}
	
	private FlexibleSearchService flexibleSearchService;

	/**
	 * Find Upcoming Events List used on bulk email
	 *
	 * @return list of events
	 */

	@Override
	public List<BlogPostComponentModel> getUpcomingEvents() {

		final String query = "SELECT {" + BlogPostComponentModel.PK + "} FROM {" +
				BlogPostComponentModel._TYPECODE + "} WHERE {" + BlogPostComponentModel.STARTDATE + "} > DATE_ADD(CURDATE(), INTERVAL 5 DAY)";
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);

		SearchResult<BlogPostComponentModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
		List<BlogPostComponentModel> eventList = searchResult.getResult();
		return eventList;
	}


	/**
	 * Find paged blog post.
	 *
	 * @param blogType the blog type
	 * @param paginationData the pagination data
	 * @return the search page data
	 */
	@Override
	public SearchPageData<BlogPostComponentModel> findPagedBlogPost(BlogType blogType, PaginationData paginationData, String startDateStr, String endDateStr)
	{
		
		String start = null;
		String end = null;
		try {
			if(startDateStr!=null && startDateStr.length()>0 && endDateStr!=null && endDateStr.length()>0) {
				start = getConvertedDate(startDateStr);
				//if(endDateStr!=null && endDateStr.length()>0) {
				end = getConvertedDate(endDateStr);
				//}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {").append(BlogPostComponentModel.PK).append("} ");
		queryString.append("FROM {").append(BlogPostComponentModel._TYPECODE).append("} WHERE ");

		String connectCode = BlogType.CONNECT.getCode();
		String blogTypeQuery = "({{select {pk} from {BlogType} where {code} = ";
		if (blogType == null || connectCode.equalsIgnoreCase(blogType.getCode()))
		{
			queryString.append("({").append(BlogPostComponentModel.BLOGTYPE).append("} ").append("IS NULL");
			queryString.append(" OR ");
			queryString.append("{").append(BlogPostComponentModel.BLOGTYPE).append("} = ").append(blogTypeQuery).append("'").append(connectCode).append("'}})) ");
		}
		else
		{
			queryString.append("{").append(BlogPostComponentModel.BLOGTYPE).append("} = ").append(blogTypeQuery).append("'").append(blogType.getCode()).append("'}}) ");
		}
		queryString.append("AND {").append(BlogPostComponentModel.VISIBLE).append("} = '1' ");
		queryString.append("AND {").append(BlogPostComponentModel.PK).append("} NOT IN ");
		queryString.append("({{ SELECT {").append(FeaturedPostComponentModel.FEATUREDPOST).append("} ");
		queryString.append("FROM {").append(FeaturedPostComponentModel._TYPECODE).append("} }}) ");

		if(startDateStr==null) {
			queryString.append("AND {").append(BlogPostComponentModel.ENDDATE).append("} >= CURDATE()");
		}else {
			queryString.append("AND {").append(BlogPostComponentModel.STARTDATE).append("} <= '").append(end).append("' ");
			queryString.append("AND {").append(BlogPostComponentModel.ENDDATE).append("} >= '").append(start).append("' ");
		}
		
		queryString.append("ORDER BY {").append(BlogPostComponentModel.PROMOTETOINDEXPAGE).append("} DESC");
		queryString.append(", {").append(BlogPostComponentModel.STARTDATE).append("}");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryString.toString());
		
		SearchPageData<BlogPostComponentModel> searchPageDataInput = new SearchPageData<BlogPostComponentModel>();
		searchPageDataInput.setPagination(paginationData);

		PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
		parameter.setFlexibleSearchQuery(searchQuery);
		parameter.setSearchPageData(searchPageDataInput);
		return getPaginatedFlexibleSearchService().search(parameter);
	}

	@Override
	public SearchPageData<BlogPostComponentModel> findPagedBlogPostByText(BlogType blogType, PaginationData paginationData, String text) {
		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {").append(BlogPostComponentModel.PK).append("} ");
		queryString.append("FROM {").append(BlogPostComponentModel._TYPECODE).append("} WHERE ");

		String connectCode = BlogType.CONNECT.getCode();
		String blogTypeQuery = "({{select {pk} from {BlogType} where {code} = ";
		if (blogType == null || connectCode.equalsIgnoreCase(blogType.getCode()))
		{
			queryString.append("({").append(BlogPostComponentModel.BLOGTYPE).append("} ").append("IS NULL");
			queryString.append(" OR ");
			queryString.append("{").append(BlogPostComponentModel.BLOGTYPE).append("} = ").append(blogTypeQuery).append("'").append(connectCode).append("'}})) ");
		}
		else
		{
			queryString.append("{").append(BlogPostComponentModel.BLOGTYPE).append("} = ").append(blogTypeQuery).append("'").append(blogType.getCode()).append("'}}) ");
		}
		queryString.append("AND {").append(BlogPostComponentModel.VISIBLE).append("} = '1' ");
		queryString.append("AND (LOWER({").append(BlogPostComponentModel.TITLE).append("}) LIKE '%").append(text.toLowerCase())
				.append("%' OR LOWER({").append(BlogPostComponentModel.CONTENT).append("}) LIKE '%").append(text.toLowerCase()).append("%') ");
		queryString.append("AND {").append(BlogPostComponentModel.PK).append("} NOT IN ");
		queryString.append("({{ SELECT {").append(FeaturedPostComponentModel.FEATUREDPOST).append("} ");
		queryString.append("FROM {").append(FeaturedPostComponentModel._TYPECODE).append("} }}) ");
		queryString.append("ORDER BY {").append(BlogPostComponentModel.PROMOTETOINDEXPAGE).append("} DESC");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryString.toString());

		SearchPageData<BlogPostComponentModel> searchPageDataInput = new SearchPageData<BlogPostComponentModel>();
		searchPageDataInput.setPagination(paginationData);

		PaginatedFlexibleSearchParameter parameter = new PaginatedFlexibleSearchParameter();
		parameter.setFlexibleSearchQuery(searchQuery);
		parameter.setSearchPageData(searchPageDataInput);
		return getPaginatedFlexibleSearchService().search(parameter);
	}
	
	/**
	 * To convert Milliseconds to Date Format for DB
	 * @param date in milliseconds(String)
	 * @return String value of formatted date
	 * */
	public String getConvertedDate(String dateStr){
		SimpleDateFormat destFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.S");
		//TimeZone tzInAmerica = TimeZone.getTimeZone("America/Paramaribo");
		//destFormat.setTimeZone(tzInAmerica);
		long longDate = Long.parseLong(dateStr);
		Date date = new Date(longDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 20);
		return destFormat.format(cal.getTime());
	}

	public BlogPostComponentModel findBlogPostComponentByCode(String code) {

		final Map parameters = new HashMap();
		parameters.put(BlogPostComponentModel.UID, code);
		FlexibleSearchQuery searchQuery = createFlexibleSearchQuery(parameters);

		SearchResult<BlogPostComponentModel> searchResult = flexibleSearchService.search(searchQuery);
		return searchResult.getResult().get(0);
	}

	public FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
