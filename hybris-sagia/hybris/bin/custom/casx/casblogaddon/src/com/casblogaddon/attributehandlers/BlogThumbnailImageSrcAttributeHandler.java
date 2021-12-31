package com.casblogaddon.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.casblogaddon.model.BlogPostComponentModel;


/**
 * @author Ankitkumar.Patel
 */
public class BlogThumbnailImageSrcAttributeHandler implements DynamicAttributeHandler<String, BlogPostComponentModel>
{

	@Override
	public String get(final BlogPostComponentModel blogPost)
	{
		if (blogPost.getThumbnailImage() != null)
		{
			return blogPost.getThumbnailImage().getURL();

		}
		else if (blogPost.getContent() != null)
		{
			final Pattern p = Pattern.compile("<img[^>]*src[ ]?=[ ]?[\\\"']([^\\\"^']*)");
			final Matcher m = p.matcher(blogPost.getContent());
			while (m.find())
			{
				return m.group(1);
			}
		}
		return null;
	}

	@Override
	public void set(final BlogPostComponentModel blogPost, final String source)
	{
		throw new UnsupportedOperationException();

	}
}