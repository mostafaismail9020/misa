package com.casblogaddon.facade.impl;

import com.casblogaddon.facade.NewsLetterFacade;
import com.casblogaddon.model.BlogPostComponentModel;
import com.casblogaddon.model.NewsLetterModel;
import com.casblogaddon.service.BlogPostService;
import com.sap.ibso.eservices.facade.customerticket.DefaultSagiaBlogTicketingFacade;
import com.sap.ibso.eservices.facades.data.BlogTicketData;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerticketingfacades.data.TicketCategory;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultNewsLetterFacade implements NewsLetterFacade {

	private static final Logger LOG = Logger.getLogger(DefaultNewsLetterFacade.class);

	@Resource(name = "modelService")
	ModelService modelService;

	@Resource(name = "eventService")
	EventService eventService;

	@Resource(name = "baseSiteService")
	BaseSiteService baseSiteService;

	@Resource(name = "userService")
	UserService userService;

	@Resource(name = "blogPostService")
	BlogPostService blogPostService;

	@Resource(name = "defaultBlogTicketFacade")
	private DefaultSagiaBlogTicketingFacade ticketFacade;

	@Override
	public void saveTicket(final String subject, final String comment, String blogPostId) {

		BlogTicketData blogTicketData = new BlogTicketData();
		blogTicketData.setSubject(subject);
		blogTicketData.setBlogComment(comment);
		blogTicketData.setAssociatedTo(blogPostId);
		blogTicketData.setCustomerId(userService.getCurrentUser().getUid());
		blogTicketData.setTicketCategory(TicketCategory.OPPORTUNITYSUBMISSION); //TODO needs confirm with customer for ticket catergory
		blogTicketData.setBlogPostComponent(getBlogPostComponent(blogPostId));

		ticketFacade.createTicket(blogTicketData);
	}

	private BlogPostComponentModel getBlogPostComponent(String blogPostId) {
		return blogPostService.getBlogPostComponentByEventPage(blogPostId);
	}

	@Override
	public void saveNewsLetter(final String fname, final String lname, final String email) {
		final NewsLetterModel newsLetterModel = modelService.create(NewsLetterModel.class);
		newsLetterModel.setFirstName(fname);
		newsLetterModel.setLastName(lname);
		newsLetterModel.setEmail(email);
		modelService.save(newsLetterModel);

		BaseSiteModel currentBaseSite = baseSiteService.getCurrentBaseSite();
		// eventService.publishEvent(new
		// HeritageSubscribeNewsLetterEvent(newsLetterModel, currentBaseSite));
	}

	@Override
	public void saveSubscription(String eventDetail) {
		UserModel userModel = userService.getCurrentUser();
		final BlogPostComponentModel blogPostComponentModel = blogPostService.getBlogPostComponentByCode(eventDetail);
		final List<BlogPostComponentModel> blogPostComponentModelList = new ArrayList<BlogPostComponentModel>();

		if(CollectionUtils.isNotEmpty(userModel.getSubscribedBlogEvents()))
		{
			blogPostComponentModelList.addAll(userModel.getSubscribedBlogEvents());
		}

		blogPostComponentModelList.add(blogPostComponentModel);
		userModel.setSubscribedBlogEvents(blogPostComponentModelList);
		modelService.save(userModel);
	}

	@Override
	public void unSubscribeNewsletter(String eventDetail) {
		UserModel userModel = userService.getCurrentUser();

		if(CollectionUtils.isNotEmpty(userModel.getSubscribedBlogEvents())) {

			List<BlogPostComponentModel> blogPostComponentModelList = new ArrayList<BlogPostComponentModel>(userModel.getSubscribedBlogEvents());

			Iterator<BlogPostComponentModel> iterator = blogPostComponentModelList.iterator();
			while (iterator.hasNext()) {
				BlogPostComponentModel blogPostModel = iterator.next();
				if (blogPostModel.getUniqueCode().equalsIgnoreCase(eventDetail)) {
					iterator.remove();
				}
			}
			userModel.setSubscribedBlogEvents(blogPostComponentModelList);
			modelService.save(userModel);
		}
	}
}