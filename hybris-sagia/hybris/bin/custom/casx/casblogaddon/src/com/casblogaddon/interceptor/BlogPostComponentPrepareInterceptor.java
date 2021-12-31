package com.casblogaddon.interceptor;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;

import com.casblogaddon.model.BlogPostComponentModel;


/**
 * @author Ankitkumar.Patel
 *
 *         To save extractedContent from the post content
 */
public class BlogPostComponentPrepareInterceptor implements PrepareInterceptor
{

	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof BlogPostComponentModel)
		{
			final BlogPostComponentModel post = (BlogPostComponentModel) model;
			if (ctx.isNew(post) || isModified(ctx, model, BlogPostComponentModel.CONTENT)
					|| isModified(ctx, model, BlogPostComponentModel.SHORTCONTENT))
			{
				updateExtractedContent(ctx, post);
			}
		}
	}


	private boolean isModified(final InterceptorContext ctx, final Object model, final String key)
	{
		final Map<String, Set<Locale>> dirtyAttributes = ctx.getDirtyAttributes(model);
		return MapUtils.isNotEmpty(dirtyAttributes) && dirtyAttributes.get(key) != null;
	}


	protected void updateExtractedContent(final InterceptorContext ctx, final BlogPostComponentModel post)
	{
		final Set<Locale> localeSet = getDirtyAttribLocal(ctx, post);
		for (final Locale locale : localeSet)
		{
			String shortContent = post.getShortContent(locale);
			if (StringUtils.isBlank(shortContent))
			{
				//Short Content is empty so grab from post
				shortContent = grabShortContentFromPostContent(post.getContent(locale));
			}
			post.setExtractedContent(shortContent, locale);
		}
	}

	private Set<Locale> getDirtyAttribLocal(final InterceptorContext ctx, final BlogPostComponentModel post)
	{
		final Set<Locale> localeSet = new HashSet<Locale>();
		final Map<String, Set<Locale>> dirtyAttributes = ctx.getDirtyAttributes(post);
		final Set<Locale> contentLocalSet = dirtyAttributes.get(BlogPostComponentModel.CONTENT);
		final Set<Locale> shortContentLocalSet = dirtyAttributes.get(BlogPostComponentModel.SHORTCONTENT);
		addAllIfNotNull(localeSet, contentLocalSet);
		addAllIfNotNull(localeSet, shortContentLocalSet);
		return localeSet;
	}

	public static <E> void addAllIfNotNull(final Set<E> localeSet, final Set<E> c)
	{
		if (c != null)
		{
			localeSet.addAll(c);
		}
	}

	private String grabShortContentFromPostContent(final String content)
	{
		String text = "";
		if (StringUtils.isNotBlank(content))
		{
			text = Jsoup.parse(content).text();
			text.replace("\"", "");
			final int toIndex = text.length() > 200 ? 200 : text.length();
			text = text.substring(0, toIndex);
		}
		return text;
	}
}
