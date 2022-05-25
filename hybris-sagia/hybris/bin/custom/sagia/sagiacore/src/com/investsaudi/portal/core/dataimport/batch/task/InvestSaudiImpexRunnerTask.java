package com.investsaudi.portal.core.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.AbstractImpexRunnerTask;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InvestSaudiImpexRunnerTask extends AbstractImpexRunnerTask {
    private static final Logger LOG = Logger.getLogger(AbstractImpexRunnerTask.class);

    private static final String EMAIL_SUBJECT = "InvestSaudi HotFolder Import Error for file: ";
    private static final String HI_THERE_WAS_AN_ERROR = "Hi, There were some errors while importing a file ";
    private static final String YOU_CAN_FIND_THE_ATTACHED = ". Below are some lines from the error. You can find the attached file.";

    private SessionService sessionService;
    private ImportService importService;
    private EmailService emailService;

    @Override
    public BatchHeader execute(final BatchHeader header) throws FileNotFoundException
    {
        Assert.notNull(header);
        Assert.notNull(header.getEncoding());
        if (CollectionUtils.isNotEmpty(header.getTransformedFiles()))
        {
            final Session localSession = getSessionService().createNewSession();
            try
            {
                for (final File file : header.getTransformedFiles())
                {
                    processFile(file, header.getEncoding());
                }
            }
            finally
            {
                getSessionService().closeSession(localSession);
            }
        }
        return header;
    }

    /**
     * Process an impex file using the given encoding
     *
     * @param file
     * @param encoding
     * @throws FileNotFoundException
     */
    protected void processFile(final File file, final String encoding) throws FileNotFoundException
    {
        try (final FileInputStream fis = new FileInputStream(file))
        {
            final ImportConfig config = getImportConfig();
            if (config == null)
            {
                LOG.error(String.format("Import config not found. The file %s won't be imported.",
                        file == null ? null : file.getName()));
                return;
            }
            final ImpExResource resource = new StreamBasedImpExResource(fis, encoding);
            ;
            config.setScript(resource);
            final ImportResult importResult = getImportService().importData(config);
            try
            {
                if (importResult.isError() && importResult.hasUnresolvedLines())
                {

                    stepFailedEmail(importResult.getUnresolvedLines().getPreview(),file);
                    LOG.error(importResult.getUnresolvedLines().getPreview());
                }
            }
            catch (final Exception e)
            {
                LOG.error("Error occured while sending email file: " + file, e);
            }
        }
        catch (final IOException | IllegalStateException e)
        {
            LOG.error("Error occured while process file: " + file, e);
        }
    }

    public SessionService getSessionService()
    {
        return sessionService;
    }

    @Required
    public void setSessionService(final SessionService sessionService)
    {
        this.sessionService = sessionService;
    }

    public ImportService getImportService()
    {
        return importService;
    }

    @Required
    public void setImportService(final ImportService importService)
    {
        this.importService = importService;
    }

    /**
     * Lookup method to return the import config
     *
     * @return import config
     */
    @Override
    public ImportConfig getImportConfig(){
        return null;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    protected void stepFailedEmail(final String message, final File file) throws FileNotFoundException {
        List<EmailAddressModel> toEmailAddress = new ArrayList<>();
        EmailAddressModel toEmailAddressModel = emailService.getOrCreateEmailAddressForEmail(Config.getParameter("investsaudi.import.error.to.email"), "Administrator");
        toEmailAddress.add(toEmailAddressModel);
        EmailAddressModel fromEmailAddressModel = emailService.getOrCreateEmailAddressForEmail(Config.getParameter("investsaudi.import.error.from.email"), "Administrator");
        List<EmailAttachmentModel> attachments = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        EmailAttachmentModel attachmentModel = emailService.createEmailAttachment(dataInputStream, file.getName(), ".csv");
        attachments.add(attachmentModel);
        final EmailMessageModel emailMessage = emailService.createEmailMessage(toEmailAddress,
                null,
                null,
                fromEmailAddressModel,
                null,
                EMAIL_SUBJECT + file.getName(),
                HI_THERE_WAS_AN_ERROR + file.getName() + YOU_CAN_FIND_THE_ATTACHED + System.lineSeparator() + message,
                attachments);

        emailService.send(emailMessage);
    }
}
