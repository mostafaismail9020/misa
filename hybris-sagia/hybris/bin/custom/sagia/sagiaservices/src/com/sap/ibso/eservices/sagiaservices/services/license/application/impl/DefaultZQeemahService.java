package com.sap.ibso.eservices.sagiaservices.services.license.application.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.ApplicationStatusService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.UserRegisterService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;

/**
 * Implements access to ZQEEMAH service data.
 */
public class DefaultZQeemahService implements ZQeemahService
{
    private static final Logger LOGGER = LogManager.getLogger(DefaultZQeemahService.class);

    private ApplicationStatusService applicationStatusService;
    private UserRegisterService userRegisterService;
    private UserService userService;

    @Override
    public Optional<ApplicationStatusData> getApplicationStatus()
    {
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        String applicantReferenceId = customer.getApplicantReferenceID();

        if (applicantReferenceId == null || applicantReferenceId.isEmpty())
        {
            // The current user is not yet registered in the SAP backend system
            return Optional.empty();
        }

        Collection<ApplicationStatusData> result = applicationStatusService.getApplicationStatus(applicantReferenceId);

        // If not empty then the result shall have only one entry
        Assert.isTrue(result.size() <= 1, "The application status data collection is expected to have at most one entry");

        if (CollectionUtils.isEmpty(result))
        {
            LOGGER.warn("No application status information found for applicant reference identifier " + applicantReferenceId);
            return Optional.empty();
        }

        return Optional.of(result.iterator().next());
    }

    @Override
    public void evictApplicationStatus()
    {
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        String applicantReferenceId = customer.getApplicantReferenceID();

        if (applicantReferenceId != null && !applicantReferenceId.isEmpty())
        {
        	applicationStatusService.evictApplicationStatus(applicantReferenceId);
        }
    }
    
    @Override
    public boolean createApplicantReferenceId()
    {
        return userRegisterService.createApplicantReferenceId();
    }

    @Required
    public void setApplicationStatusService(ApplicationStatusService applicationStatusService)
    {
        this.applicationStatusService = applicationStatusService;
    }

    @Required
    public void setUserRegisterService(UserRegisterService userRegisterService)
    {
        this.userRegisterService = userRegisterService;
    }

    @Required
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }
}
