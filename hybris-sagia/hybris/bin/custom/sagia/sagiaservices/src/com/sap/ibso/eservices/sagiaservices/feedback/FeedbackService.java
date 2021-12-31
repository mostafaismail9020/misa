package com.sap.ibso.eservices.sagiaservices.feedback;

import org.springframework.util.Assert;

/**
 * Submits feedback about user experience related to e-services.
 */
public interface FeedbackService
{
    /**
     * Provides the rating values of satisfaction with the user experience of an e-service.
     */
    enum Rating
    {
        /**
         * Indicates very poor user experience with an e-service.
         */
        ONE_STAR,
        /**
         * Indicates poor user experience with an e-service.
         */
        TWO_STARS,
        /**
         * Indicates average user experience with an e-service.
         */
        THREE_STARS,
        /**
         * Indicates good user experience with an e-service.
         */
        FOUR_STARS,
        /**
         * Indicates very good user experience with an e-service.
         */
        FIVE_STARS;

        /**
         * Gets a corresponding rating value for an integer value.
         * Supported integer values are 1 for {@link Rating#ONE_STAR}, 2 for {@link Rating#TWO_STARS} , 3 for {@link Rating#THREE_STARS},
         * 4 for {@link Rating#FOUR_STARS} and 5 for {@link Rating#FIVE_STARS}.
         *
         * @param i the integer value
         * @return the rating value
         * @throws IllegalArgumentException if an unsupported integer value is provided
         */
        public static Rating getValue(int i)
        {
            Assert.isTrue(i > 0 && i < 6, "Integer for rating value must be 1, 2, 3, 4, or 5, but provided was " + i);
            return Rating.values()[i-1];
        }
    }

    /**
     * Submits a rating value and a comment as user experience feedback for an e-service.
     * <p>
     * The following basic parameter checks are performed: the e-service identifier and the rating value must not be null.
     * </p>
     *
     * @param serviceId the e-service identifier
     * @param value the rating value
     * @param comment the comment
     * @throws IllegalArgumentException if a basic parameter check fails
     */
    void submitUserExperienceFeedback(String serviceId, Rating value, String comment, String investorEmail);

    /**
     * Submits a rating value as user experience feedback.
     * <p>
     * The following basic parameter checks are performed: the e-service identifier and the rating value must not be null.
     * </p>
     *
     * @param serviceId the e-service identifier
     * @param value the rating value
     * @throws IllegalArgumentException if a basic parameter check fails
     */
    default void submitUserExperienceFeedback(String serviceId, Rating value)
    {
        submitUserExperienceFeedback(serviceId, value, null, null);
    }
}
