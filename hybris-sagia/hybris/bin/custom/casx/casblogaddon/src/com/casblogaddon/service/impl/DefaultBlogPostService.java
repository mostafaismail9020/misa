/**
 *
 */
package com.casblogaddon.service.impl;

import de.hybris.platform.core.servicelayer.data.PaginationData;
import de.hybris.platform.core.servicelayer.data.SearchPageData;

import javax.annotation.Resource;

import com.casblogaddon.enums.BlogType;
import com.casblogaddon.model.BlogPostComponentModel;
import com.casblogaddon.service.BlogPostService;
import com.casblogaddon.service.dao.BlogPostDAO;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;


/**
 * The Class DefaultBlogPostService.
 *
 * @author Ankitkumar.Patel
 */
public class DefaultBlogPostService implements BlogPostService
{

	/** The blog post DAO. */
	@Resource(name = "blogPostDAO")
	private BlogPostDAO<BlogPostComponentModel> blogPostDAO;

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Resource(name = "modelService")
	private ModelService modelService;

	/**
	 * Gets the blog posts.
	 *
	 * @param blogType the blog type
	 * @param pageSize the page size
	 * @param pageNumber the page number
	 * @return the blog posts
	 */
	@Override
	public SearchPageData<BlogPostComponentModel> getBlogPosts(BlogType blogType, int pageSize, int pageNumber, String startDate, String endDate)
	{
		final PaginationData pageData = createPageableData(pageSize, pageNumber);
		final SearchPageData<BlogPostComponentModel> searchPageData = blogPostDAO.findPagedBlogPost(blogType, pageData, startDate, endDate);
		return searchPageData;
	}

	/**
	 * Creates the pageable data.
	 *
	 * @param pageSize the page size
	 * @param currentPage the current page
	 * @return the pagination data
	 */
	protected PaginationData createPageableData(final int pageSize, final int currentPage)
	{
		final PaginationData paginationData = new PaginationData();
		paginationData.setPageSize(pageSize);
		paginationData.setCurrentPage(currentPage);
		paginationData.setNeedsTotal(true);
		return paginationData;
	}

	@Override
	public SearchPageData<BlogPostComponentModel> getBlogPostsByText(BlogType blogType, int pageSize, int pageNumber, String text) {
		final PaginationData pageData = createPageableData(pageSize, pageNumber);
		final SearchPageData<BlogPostComponentModel> searchPageData = blogPostDAO.findPagedBlogPostByText(blogType, pageData, text);
		return searchPageData;
	}

	@Override
	public BlogPostComponentModel getBlogPostComponentByCode(String Code){
		return blogPostDAO.findBlogPostComponentByCode(Code);
	}

	@Override
	public BlogPostComponentModel getBlogPostComponentByEventPage(String blogPostId) {

		BlogPostComponentModel blogPostComponentModel = new BlogPostComponentModel();
		blogPostComponentModel.setUid(blogPostId);

		BlogPostComponentModel blogPostComponentModelFound = flexibleSearchService.getModelByExample(blogPostComponentModel);

		return blogPostComponentModelFound;
	}
}
