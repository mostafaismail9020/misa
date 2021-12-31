package com.casblogaddon.service.impl;

import com.casblogaddon.model.BlogPostComponentModel;
import com.casblogaddon.service.SendEventsJobService;
import com.casblogaddon.service.dao.BlogPostDAO;

import javax.annotation.Resource;
import java.util.List;

public class DefaultSendEventsJobService implements SendEventsJobService {

    @Resource
    private BlogPostDAO blogPostDAO;

    @Override
    public List<BlogPostComponentModel> getUpcomingEvents() {
        return blogPostDAO.getUpcomingEvents();
    }

    public void setSendEventsJobDAO(BlogPostDAO blogPostDAO) {
        this.blogPostDAO = blogPostDAO;
    }
}
