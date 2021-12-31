package com.casblogaddon.controllers.cms;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.casblogaddon.controllers.CasblogaddonControllerConstants;
import com.casblogaddon.model.components.BlogPostContainerComponentModel;
import com.casblogaddon.service.BlogPostService;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;


/**
 * The Class BlogPostContainerComponentController.
 *
 * @author Ankitkumar.Patel
 */

@Controller("BlogPostContainerComponentController")
@Scope("tenant")
@RequestMapping(value = CasblogaddonControllerConstants.Actions.Cms.BlogPostContainerComponent)
public class BlogPostContainerComponentController extends AbstractCMSAddOnComponentController<BlogPostContainerComponentModel>
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
	protected void fillModel(final HttpServletRequest request, final Model model, final BlogPostContainerComponentModel component)
	{
		int pageNumber = 0;
		final String page = request.getParameter("page");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		boolean multipleMonth = StringUtils.isNotEmpty(request.getParameter("multipleMonth"))
				&& request.getParameter("multipleMonth").equals("true");
		
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
		model.addAttribute("blogData", blogPostService.getBlogPosts(component.getBlogType(), pageSize, pageNumber, startDate, endDate));

		long startDt = 0;
		long endDt = 0 ;
		int range = 0;
		if(startDate ==null) {
			startDt = new Date().getTime();
		}else {
			startDt = Long.parseLong(startDate);
			endDt = Long.parseLong(endDate);
			range = (int) ((endDt - startDt) / (1000 * 60 * 60 * 24)) +1;
		}
		
		model.addAttribute("startDate", startDt);
		model.addAttribute("endDate", endDt);
		model.addAttribute("range", range);

		if (multipleMonth) {
			model.addAttribute("multipleMonth", true);
		}

	}

}
