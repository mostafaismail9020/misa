package com.sap.ibso.eservices.bol.feedback;

import com.sap.ibso.eservices.bol.feedback.data.FeedbackBackendData;

/**
 * Submits feedback synchronously to SAP backend system about user experience related to e-services.
 */
public interface FeedbackBackendService
{
    /**
     * Submits a backend rating value and a comment as user experience feedback for an e-service.
     *
     * @param serviceId the unique e-service identifier
     * @param value the rating value
     * @param comment the comment
     * @return the feedback backend data
     */
    FeedbackBackendData submitUserExperienceFeedback(String serviceId, BackendRatingValue value, String comment, String email);
}
