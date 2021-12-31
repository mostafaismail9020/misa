package com.casblogaddon.service.dao;

import com.casblogaddon.enums.BlogType;
import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import java.util.List;


/**
 * The Interface BlogPostDAO.
 *
 * @author Ankitkumar.Patel
 * @param <BlogPostComponentModel> the generic type
 */
public interface BlogPostDAO<BlogPostComponentModel>
{

	/**
	 * Find paged blog post.
	 *
	 * @param blogType the blog type
	 * @param paginationData the pagination data
	 * @return the search page data
	 */
	SearchPageData<BlogPostComponentModel> findPagedBlogPost(BlogType blogType, PaginationData paginationData, String startDate, String endDate);

	SearchPageData<BlogPostComponentModel> findPagedBlogPostByText(BlogType blogType, PaginationData paginationData, String text);

	BlogPostComponentModel findBlogPostComponentByCode(String code);

	List<BlogPostComponentModel> getUpcomingEvents();
}
