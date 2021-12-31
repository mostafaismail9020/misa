package com.casblogaddon.controllers.pages;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casblogaddon.facade.NewsLetterFacade;

@Scope("tenant")
@RequestMapping(value = "/newsletter")
public class BlogNewsLetterController {
	private static final Logger LOG = Logger.getLogger(BlogNewsLetterController.class);

	@Resource(name = "newsLetterFacade")
	private NewsLetterFacade newsLetterFacade;

	@RequestMapping(value = "/saveTicket", method = RequestMethod.POST)
	@ResponseBody
	public String saveDetails(final Model model, @RequestParam(value = "subject", required = true) final String subject,
							  @RequestParam(value = "comments", required = true) final String comments,
							  @RequestParam(value = "blogPostId", required = true) final String blogPostId) {
		try {
			newsLetterFacade.saveTicket(subject, comments, blogPostId);
			return "success";
		} catch (final Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	@ResponseBody
	public String subscribeToNewsLetter(final Model model,
										@RequestParam(value = "eventDetail", required = true) final String eventDetail) {
		try {
			newsLetterFacade.saveSubscription(eventDetail);
			return "success";
		} catch (final Exception e) {
			LOG.error("Error while saving subscription", e);
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
	@ResponseBody
	public String unSubscribeToNewsLetter(final Model model,
										  @RequestParam(value = "eventDetail", required = true) final String eventDetail) {
		try {
			newsLetterFacade.unSubscribeNewsletter(eventDetail);
			return "success";
		} catch (final Exception e) {
			LOG.error("Error while unsubscribing", e);
			e.printStackTrace();
			return "error";
		}
	}

}
