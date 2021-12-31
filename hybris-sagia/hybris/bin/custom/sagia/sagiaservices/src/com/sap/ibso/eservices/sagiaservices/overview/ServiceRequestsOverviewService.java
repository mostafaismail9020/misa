package com.sap.ibso.eservices.sagiaservices.overview;

import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestData;
import com.sap.ibso.eservices.sagiaservices.overview.data.ServiceRequestsOverviewData;

/**
 * Provides access to an overview of e-service requests.
 */
public interface ServiceRequestsOverviewService
{
    /**
     * Retrieves overview data of (submitted) service requests (including status information) of an investor company
     * which is associated to the current user.
     * Language dependent names and descriptions of service requests are returned in the current Hybris session language.
     * The returned {@link ServiceRequestsOverviewData} instance contains the language ISO code of the language of
     * these names and descriptions.
     *
     * @return the overview data of service requests
     */
    ServiceRequestsOverviewData getServiceRequests();

    /**
     * Replaces language dependent names and descriptions of overview data of (submitted) service requests in a target language.
     * As prerequisite the names and description must have been maintained in Hybris in the target language.
     * The given {@link ServiceRequestsOverviewData} instance including its containing {@link ServiceRequestData} instances
     * are copied and language dependent names and descriptions are replaced in target language.
     * Names and descriptions that are not available in the target language remain unchanged.
     *
     * @param overviewData the overview data of (submitted) service requests
     * @param language the language ISO code of the target language (e.g. "en" or "ar").
     * @return the overview data of (submitted) service requests with names and description in target language
     */
    ServiceRequestsOverviewData translate(ServiceRequestsOverviewData overviewData, String language);
}
