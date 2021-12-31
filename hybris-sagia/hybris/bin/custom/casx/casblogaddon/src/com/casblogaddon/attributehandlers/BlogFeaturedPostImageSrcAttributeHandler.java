/**
 *
 */
package com.casblogaddon.attributehandlers;

import com.casblogaddon.model.BlogPostComponentModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Ankitkumar.Patel
 *
 */
public class BlogFeaturedPostImageSrcAttributeHandler implements DynamicAttributeHandler<String, BlogPostComponentModel>
{
	@Override
	public String get(final BlogPostComponentModel blogPost)
	{
		if (blogPost.getPostImage() != null)
		{
			return blogPost.getPostImage().getURL();

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

