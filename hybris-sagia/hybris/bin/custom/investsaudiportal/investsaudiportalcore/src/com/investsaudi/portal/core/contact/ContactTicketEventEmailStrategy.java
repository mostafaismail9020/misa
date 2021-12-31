/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudi.portal.core.contact;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import de.hybris.platform.comments.model.CommentAttachmentModel;
import de.hybris.platform.commons.renderer.RendererService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.ticket.email.context.AbstractTicketContext;
import de.hybris.platform.ticket.events.model.CsTicketEmailModel;
import de.hybris.platform.ticket.model.CsTicketEventEmailConfigurationModel;
import de.hybris.platform.ticket.strategies.impl.DefaultTicketEventEmailStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ContactTicketEventEmailStrategy extends DefaultTicketEventEmailStrategy {
    private final static Logger log = LoggerFactory.getLogger(ContactTicketEventEmailStrategy.class);

    private MediaService mediaService;

    private RendererService rendererService;

    protected CsTicketEmailModel constructAndSendEmail(final AbstractTicketContext ticketContext,
                                                       final CsTicketEventEmailConfigurationModel config) {
        try {
            if (ticketContext.getTo() == null) {
                log.warn("Could not send email for event [" + ticketContext.getEvent() + "]. With config [" + config
                    + "] No recipient could be found.");
                return null;
            }

            //Create mail instance using commons-mail and the hybris MailUtility class.
            final HtmlEmail email =
                (HtmlEmail) getPreConfiguredEmail(); //creates a mail instance with set mail properties read from project.properties
            setMailEncoding(email, "UTF-8");

            final VelocityContext ctx = new VelocityContext();
            ctx.put("ctx", ticketContext);
            final StringWriter subj = new StringWriter();
            Velocity.evaluate(ctx, subj, "logtag", new StringReader(config.getSubject()));
            email.setSubject(subj.toString());
            if (StringUtils.contains(ticketContext.getTo(), ",")) {
                email.addTo(StringUtils.split(ticketContext.getTo(), ","));
            } else {
                email.addTo(ticketContext.getTo());
            }

            final StringWriter htmlVersion = new StringWriter();
            // Create the HTML version of the email from the template
            rendererService.render(config.getHtmlTemplate(), ticketContext, htmlVersion);
            email.setHtmlMsg(htmlVersion.toString());

            final StringWriter textVersion = new StringWriter();
            if (config.getPlainTextTemplate() != null) {
                // Create the plain text version of the email from the template
                rendererService.render(config.getPlainTextTemplate(), ticketContext, textVersion);
                email.setTextMsg(textVersion.toString());
            }

            final Collection<CommentAttachmentModel> attachments = ticketContext.getAttachments();
            attachMediaToMail(email, attachments);

            // Send the email and capture the message ID
            final CsTicketEmailModel storedEmail = getModelService().create(CsTicketEmailModel.class);
            storedEmail.setTo(ticketContext.getTo());
            storedEmail.setFrom(email.getFromAddress().toString());
            storedEmail.setSubject(email.getSubject());
            storedEmail.setBody(textVersion.toString() + System.getProperty("line.separator") + htmlVersion.toString());
            final String messageID = email.send();
            storedEmail.setMessageId(messageID);
            return storedEmail;
        } catch (final EmailException e) {
            log.error("Error sending email to [" + config.getRecipientType() + "]. Context was [" + ticketContext + "]",
                e);
        }

        return null;
    }

    private void setMailEncoding(final HtmlEmail email, final String encoding) {
        try {
            email.setCharset(encoding);
        } catch (final IllegalArgumentException iae) {
            log.error("Setting charset to '{}' failed.", encoding, iae);
        }
    }

    private void attachMediaToMail(final HtmlEmail email, final Collection<CommentAttachmentModel> attachments)
        throws EmailException {
        if (attachments == null || attachments.isEmpty()) {
            return;
        }

        for (final CommentAttachmentModel attachment : attachments) {
            if (!(attachment.getItem() instanceof MediaModel)) {
                continue;
            }
            try {
                final MediaModel mediaAttachment = (MediaModel) attachment.getItem();
                final DataSource dataSource = new ByteArrayDataSource(mediaService.getStreamFromMedia(mediaAttachment),
                    mediaAttachment.getMime());
                email.attach(dataSource, mediaAttachment.getRealFileName(), mediaAttachment.getDescription());
            } catch (final IOException ex) {
                log.error("Failed to load attachment data into data source [{}}]", attachment, ex);
            }
        }
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    @Override
    public void setMediaService(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public RendererService getRendererService() {
        return rendererService;
    }

    @Override
    public void setRendererService(final RendererService rendererService) {
        this.rendererService = rendererService;
    }
}
