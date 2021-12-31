package com.sap.ibso.eservices.sagiaservices.investor.impl;

import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.investor.exception.MissingApplicantReferenceIdentifierException;
import com.sap.ibso.eservices.sagiaservices.investor.exception.MissingEntityIdentifierException;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Implements access to identifiers for investor related data mapping (in SAP backend system).
 */
public class DefaultInvestorMappingService implements InvestorMappingService
{
    private ModelService modelService;
    private ZQeemahService qeemahService;
    private UserService userService;

    /**
     * Creates the default investor mapping service.
     *
     * @param modelService  the Hybris model service
     * @param qeemahService the Qeemah service (to retrieve application status information)
     * @param userService   the Hybris user service
     */
    public DefaultInvestorMappingService(ModelService modelService, ZQeemahService qeemahService, UserService userService)
    {
        this.modelService = modelService;
        this.qeemahService = qeemahService;
        this.userService = userService;
    }

    @Override
    public String getApplicantReferenceId(CustomerModel customer)
    {
       
        if(customer == null ) {
        	customer = (CustomerModel) userService.getCurrentUser();
        }
        String applicantReferenceID = customer.getApplicantReferenceID();

        if (StringUtils.isEmpty(applicantReferenceID))
        {
            throw new MissingApplicantReferenceIdentifierException("Applicant reference identifier must not be null or empty.");
        }

        return applicantReferenceID;
    }

    @Override
    public String getEntityId()
    {
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        String entityId = customer.getEntityID();

        if (StringUtils.isEmpty(entityId))
        {
            /* There is no entity identifier associated with the current user, therefore check (license) application status
             * information to possibly retrieve the entity identifier. Retrieving application status information requires
             * an applicant reference identifier to be associated with the current user.
             */

            if (StringUtils.isEmpty(customer.getApplicantReferenceID()))
            {
                /* There is no applicant reference identifier associated with the current user, therefore application
                 * status information won't be retrievable. Very likely the user has not yet applied for a license.
                 */
                throw new MissingEntityIdentifierException("Entity identifier must not be null or empty.");
            }

            // Remark: for requesting application status information the internet user id is not required (and might be null)
            Optional<ApplicationStatusData> applicationStatusData = qeemahService.getApplicationStatus();
            entityId = applicationStatusData.isPresent() ? applicationStatusData.get().getEntityId() : null;

            if (StringUtils.isEmpty(entityId))
            {
                // Entity identifier could not be retrieved by application status information
                throw new MissingEntityIdentifierException("Entity identifier must not be null or empty.");
            }

            entityId = ConversionUtil.removeLeadingZeros(entityId);

            /* Entity identifier was successful retrieved by application status information. Now we associate it
             * with the current user.
             */
            customer.setEntityID(entityId);

            /* In the scenario where the entity identifier was successful retrieved by application status information,
             * we set the same value as internet user identifier as well.
             * For Qeemah applications via Hybris it is ensured in SAP CRM that entity identifier and internet user
             * identifier are the same.
             * Note: this might be different for migrated user scenarios, but there the entity identifier is also not
             * retrieved by application status information, rather by impex import.
             */
            customer.setInternetUserID(entityId);
            modelService.save(customer);
        }

        return entityId;
    }
}
