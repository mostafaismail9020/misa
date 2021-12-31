package com.casblogaddon.job;

import java.util.List;

import javax.annotation.Resource;

import com.casblogaddon.service.SendEventsJobService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import com.casblogaddon.model.BlogPostComponentModel;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.mail.MailUtils;

public class SendUpComingEventsJob extends AbstractJobPerformable<CronJobModel>
{

	@Resource
	private SendEventsJobService sendEventsJobService;
	
	private static final Logger LOG = Logger.getLogger(SendUpComingEventsJob.class);

	@Override
	public PerformResult perform(final CronJobModel cronJobModel)
	{
		LOG.debug("**********************************");
		LOG.debug(" SendEventsJobPerformable!!! ");
		LOG.debug("**********************************");
		List<BlogPostComponentModel> eventList = sendEventsJobService.getUpcomingEvents();
		
		final StringBuilder mailContentBuilder = new StringBuilder(2000);
		int index = 1;
		mailContentBuilder.append("Upcoming Events List:\n\n");
		for (final BlogPostComponentModel event : eventList)
		{
			LOG.debug(index +" -- "+ event.getName());
			mailContentBuilder.append(index++).append(". ").append(event.getName()).append("\n");
			mailContentBuilder.append(" Start Date: ").append(event.getStartDate());
			mailContentBuilder.append(" End Date: ").append(event.getEndDate());
			mailContentBuilder.append("\n\n");
		}try{
			LOG.debug("Sending mails..............");
			sendEmail(mailContentBuilder.toString());
			LOG.debug("Mails sent..............");
		}catch (final EmailException e){
			LOG.error("Problem sending new email. Note that org.apache.commons.mail.send() can block if behind a firewall/proxy.");
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	private void sendEmail(final String message) throws EmailException
	{
		final String subject = "Upcoming Events";
		final Email email = MailUtils.getPreConfiguredEmail();
		LOG.debug(subject);
		LOG.debug(message);
		email.addTo(Config.getString("news_summary_mailing_address", null));
		email.setSubject(subject);
		email.setMsg(message);
		email.setTLS(true);
		email.send();
		
	}
	
	public void setSendEventsJobService(SendEventsJobService sendEventsJobService) {
		this.sendEventsJobService = sendEventsJobService;
	}
	
}
