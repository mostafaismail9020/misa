package com.sap.ibso.eservices.sagiaservices.services.license.info.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import com.sap.ibso.eservices.sagiaservices.services.license.info.LicenseInformationService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Implements access to license information.
 */
public class DefaultLicenseInformationService implements LicenseInformationService {
    private static final String ISSUED_STATUS = "Issued";
    private static final String AWAIT = "await";
    private static final String PAY = "pay";

    private UserService userService;
    private ZQeemahService qeemahService;

    /**
     * Creates the default license information service.
     *
     * @param userService   the user service to retrieve the currently logged in user
     * @param qeemahService the Qeemah service to retrieve license application status information
     */
    public DefaultLicenseInformationService(UserService userService, ZQeemahService qeemahService) {
        this.userService = userService;
        this.qeemahService = qeemahService;
    }

    @Override
    public boolean hasLicense() {
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();

        String applicantReferenceId = customer.getApplicantReferenceID();
        String entityId = customer.getEntityID();

        if (StringUtils.isEmpty(applicantReferenceId) && StringUtils.hasLength(entityId)) {
            /* There is no applicant reference identifier, but an entity identifier. This indicates that user is on board without
             * Hybris license application process, e.g. via back office administration or impex import.
             * However, in such a case we expect that the company of the user has an issued license.
             */
            return true;
        }

        if (StringUtils.isEmpty(applicantReferenceId) && StringUtils.isEmpty(entityId)) {
            /* There is no applicant reference identifier and no entity identifier.
             * In such a case we conclude that the company of the user has not yet applied for a license.
             */
            return false;
        }

        // Applicant reference identifier is not empty, therefore check Qeemah license application status
        Optional<ApplicationStatusData> applicationStatusData = qeemahService.getApplicationStatus();

        return applicationStatusData.isPresent() && applicationStatusData.get().getStatusDesc().startsWith(ISSUED_STATUS);
    }

    @Override
    public void evictApplicationStatus()
    {
    	qeemahService.evictApplicationStatus();
    }
    
    @Override
    public boolean hasAwaitingPayment() {
        Optional<ApplicationStatusData> applicationStatusData = qeemahService.getApplicationStatus();

        if(applicationStatusData.isPresent()){
            String applicationStatusDescription = applicationStatusData.get().getStatusDesc().toLowerCase();
            return  applicationStatusDescription.contains(AWAIT) && applicationStatusDescription.contains(PAY);
        }
        return false;
    }
}
