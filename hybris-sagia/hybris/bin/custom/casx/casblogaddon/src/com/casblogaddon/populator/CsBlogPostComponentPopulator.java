package com.casblogaddon.populator;

import com.casblogaddon.data.BlogPostComponentData;
import com.casblogaddon.model.BlogPostComponentModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CsBlogPostComponentPopulator<SOURCE extends BlogPostComponentModel, TARGET extends BlogPostComponentData> implements Populator<SOURCE, TARGET> {

    public CsBlogPostComponentPopulator() {}

    public void populate(SOURCE source, TARGET target) throws ConversionException {

        target.setBlogType(source.getBlogType());
        target.setTitle(source.getTitle());
        target.setPostUrl(source.getPostUrl());
        target.setTag(source.getTag());
        target.setPromoteToIndexPage(source.isPromoteToIndexPage());
        target.setShortContent(source.getShortContent());
        target.setExtractedContent(source.getExtractedContent());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setUniqueCode(source.getUniqueCode());

    }
}
