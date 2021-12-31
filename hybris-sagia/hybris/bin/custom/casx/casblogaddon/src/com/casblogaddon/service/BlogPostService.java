/**
 *
 */
package com.casblogaddon.service;

import de.hybris.platform.core.servicelayer.data.SearchPageData;

import com.casblogaddon.enums.BlogType;
import com.casblogaddon.model.BlogPostComponentModel;


/**
 * The Interface BlogPostService.
 *
 * @author Ankitkumar.patel
 */
public interface BlogPostService
{

	/**
	 * Gets the blog posts.
	 *
	 * @param blogType the blog type
	 * @param pageSize the page size
	 * @param pageNumber the page number
	 * @return the blog posts
	 */
	SearchPageData<BlogPostComponentModel> getBlogPosts(BlogType blogType, int pageSize, int pageNumber, String startDate, String endDate);

	SearchPageData<BlogPostComponentModel> getBlogPostsByText(BlogType blogType, int pageSize, int pageNumber, String text);

	BlogPostComponentModel getBlogPostComponentByCode(String code);

	BlogPostComponentModel getBlogPostComponentByEventPage(String eventPage);
}
