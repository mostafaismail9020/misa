package com.sap.ibso.eservices.sagiaservices.feedback.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.feedback.BackendRatingValue;
import com.sap.ibso.eservices.bol.feedback.FeedbackBackendService;
import com.sap.ibso.eservices.bol.feedback.data.FeedbackBackendData;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.sagiaservices.feedback.FeedbackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Implements feedback submission about user experience related to e-services.
 */
public class DefaultFeedbackService extends BackendAwareService implements FeedbackService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultFeedbackService.class);

    /**
     * Creates the default feedback service.
     *
     * @param feedbackBackendServiceBeanName the bean name (or alias) of the feedback backend service
     */
    public DefaultFeedbackService(String feedbackBackendServiceBeanName)
    {
        super(feedbackBackendServiceBeanName);
    }

    @Override
    public void submitUserExperienceFeedback(String serviceId, Rating value, String comment, String investorEmail)
    {
        // Basic parameter checks
        Assert.notNull(serviceId, "Service identifier must not be null.");
        Assert.notNull(value, "Rating value must not be null.");

        // Backend service access
        FeedbackBackendService backendService = getBackendService();

        // Submit feedback to the corresponding backend system
        FeedbackBackendData feedbackBackendData = backendService.submitUserExperienceFeedback(serviceId, BackendRatingValue.valueOf(value.name()), comment, investorEmail);

        // Log potential backend messages
        MessageUtil.logBackendMessages(feedbackBackendData.getMessages(), LOGGER);
    }
}
