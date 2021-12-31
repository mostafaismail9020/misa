package com.casblogaddon.service;

import java.util.List;

import com.casblogaddon.model.BlogPostComponentModel;

public interface SendEventsJobService {

	List<BlogPostComponentModel> getUpcomingEvents();

}
