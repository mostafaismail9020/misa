package com.casblogaddon.attributehandlers;

import com.casblogaddon.model.BlogPostComponentModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class BlogUniqueCodeAttributeHandler implements DynamicAttributeHandler<String, BlogPostComponentModel> {

    @Override
    public String get(final BlogPostComponentModel blogPost) {

        if (!blogPost.getUid().isEmpty()) {
            return blogPost.getUid();
        }
        return null;
    }

    @Override
    public void set(final BlogPostComponentModel blogPost, final String source) {
        throw new UnsupportedOperationException();
    }

}
