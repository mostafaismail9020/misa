package com.casblogaddon.controllers.cms;

import com.casblogaddon.controllers.CasblogaddonControllerConstants;
import com.casblogaddon.model.components.BlogPostSearchContainerComponentModel;
import com.casblogaddon.service.BlogPostService;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * The Class BlogPostSearchContainerComponentController.
 *
 * @author Thiago.Santos
 */

@Controller("BlogPostSearchContainerComponentController")
@Scope("tenant")
@RequestMapping(value = CasblogaddonControllerConstants.Actions.Cms.BlogPostSearchContainerComponent)
public class BlogPostSearchContainerComponentController extends AbstractCMSAddOnComponentController<BlogPostSearchContainerComponentModel>
{

	/** The blog post service. */
	@Resource(name = "blogPostService")
	BlogPostService blogPostService;

	/**
	 * Fill model.
	 *
	 * @param request the request
	 * @param model the model
	 * @param component the component
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final BlogPostSearchContainerComponentModel component)
	{
		int pageNumber = 0;
		final String page = request.getParameter("page");
		final String text = request.getParameter("searchText");
		if (StringUtils.isNotEmpty(page))
		{
			try
			{
				pageNumber = Integer.parseInt(page);
			}
			catch (final Exception e)
			{
				// YTODO: handle exception
			}
		}
		final int pageSize = component.getNumberOfPostPerPage() != null && component.getNumberOfPostPerPage().intValue() > 0
				? component.getNumberOfPostPerPage().intValue() : 10;
		model.addAttribute("blogSearchData", blogPostService.getBlogPostsByText(component.getBlogType(), pageSize, pageNumber, text));
		model.addAttribute("blogSearchIndexUrl", "/events/searchResults?searchText=" + text);
	}

}
