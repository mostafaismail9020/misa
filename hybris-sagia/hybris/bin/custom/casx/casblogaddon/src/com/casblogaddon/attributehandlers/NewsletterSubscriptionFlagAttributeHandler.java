package com.casblogaddon.attributehandlers;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.casblogaddon.model.BlogPostComponentModel;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.user.UserService;

public class NewsletterSubscriptionFlagAttributeHandler implements DynamicAttributeHandler<Boolean, BlogPostComponentModel> {
	
	@Resource(name = "userService")
	UserService userService;
	
	@Override
	public Boolean get(final BlogPostComponentModel blogPost)
	{
		UserModel currentUser = userService.getCurrentUser();
		boolean subscriptionFlag = false;
		if(CollectionUtils.isNotEmpty(blogPost.getSubscribedUsers()) && blogPost.getSubscribedUsers().contains(currentUser))
			{
				subscriptionFlag = true;
			}
		return subscriptionFlag;
	}

	@Override
	public void set(final BlogPostComponentModel blogPost, final Boolean source)
	{
		throw new UnsupportedOperationException();

	}

}
