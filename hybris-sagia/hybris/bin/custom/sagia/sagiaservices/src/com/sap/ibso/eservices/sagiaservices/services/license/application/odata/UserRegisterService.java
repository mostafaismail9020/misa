package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.UserRegisterData;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionRequest;
import com.sap.ibso.eservices.sagiaservices.services.http.HttpURLConnectionResponse;
import com.sap.ibso.eservices.sagiaservices.services.license.application.InvestorRegistrationException;
import com.sap.ibso.eservices.sagiaservices.services.license.application.ZQeemahService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.*;

/**
 * Implements access to REGISTER_USER_ENT entities of the ZQEEMAH service.
 */
public class UserRegisterService extends AbstractSagiaService<UserRegisterData>
{
    private UserService userService;
    private ModelService modelService;
    private KeyGenerator applicantCodeGenerator;
    private ZQeemahService qeemahService;

    /**
     * Creates an user register service instance.
     *
     * @param userService            the Hybris user service
     * @param modelService           the Hybris model service
     * @param applicantCodeGenerator the key generator
     */
    public UserRegisterService(UserService userService, ModelService modelService, KeyGenerator applicantCodeGenerator, ZQeemahService qeemahService)
    {
        this.userService = userService;
        this.modelService = modelService;
        this.applicantCodeGenerator = applicantCodeGenerator;
        this.qeemahService = qeemahService;
    }

    /**
     * Creates an applicant reference identifier for the currently logged in user (customer) and stores it.
     * A successfully created and stored applicant reference identifier promotes the user as license applicant.
     *
     * @return {@code true} if a new applicant reference identifier was successfully created and stored, {@code false}
     * if the current user has already an applicant reference identifier assigned in which case there is no need to create a new one
     * @throws InvestorRegistrationException if the applicant reference identifier creation failed
     */
    public boolean createApplicantReferenceId()
    {
        // TODO Synchronize multiple requests of the same user
        // TODO Check: HTTP Request Sequencing https://help.hybris.com/6.6.0/hcd/8c4b8114866910149e7ed7565ae310db.html

        // Get the logged in user (who shall be a customer)
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();

        // First, check whether the applicant reference ID of the current user is already known
        // A rejected license application requires a new applicant reference ID
        String applicantReferenceID = customer.getApplicantReferenceID();
        if (applicantReferenceID != null && !applicantReferenceID.isEmpty() && !isLicenseRejected())
        {
            // Applicant reference ID is already known, no need to create one
            return false;
        }

        // Applicant reference ID is not yet known
        String applicantCode = (String) applicantCodeGenerator.generate();
        Assert.isTrue(applicantCode != null && applicantCode.length() <= 10, "The generated applicant code is null or has more than 10 characters");

        UserRegisterData userRegisterData = new UserRegisterData();

        userRegisterData.setCcodeMob(customer.getMobileCountryCode());
        userRegisterData.setCompany(customer.getCompany());
        userRegisterData.setContNumber(customer.getMobileNumber());
        userRegisterData.setContPersname(customer.getName());
        userRegisterData.setCountry(customer.getCountry().getCode());
        userRegisterData.setEmail(customer.getContactEmail());

        userRegisterData.setPassword(applicantCode); // Example: HY00000000
        userRegisterData.setRef_id("");
        userRegisterData.setReturnProperty("");
        userRegisterData.setUserid(applicantCode); // Example: HY00000000

        userRegisterData = create(userRegisterData);

        applicantReferenceID = userRegisterData.getRef_id();
        Assert.hasLength(applicantReferenceID, "The applicant reference ID must not be null or empty");

        if ("0000000000".equals(applicantReferenceID)) // An initial applicant reference ID indicates that the backend registration was not successful
        {
            /* Possible "returnProperty" values (list may not be complete):
             * - "Mobile No is already exist"
             * - "Email id is already exist"
             * - "Mobile No & Email id is already exist"
             * - "User Id is already exist"
             */
            String returnProperty = userRegisterData.getReturnProperty().toLowerCase();
            if(returnProperty.contains("email") && returnProperty.contains("already exist")){
                throw new InvestorRegistrationException("This email has been already involve in the 'Apply for new license' process.");
            }

            throw new InvestorRegistrationException(userRegisterData.getReturnProperty());
        }

        // Investor user registering as applicant in backend was successful, store the applicant reference ID as customer attribute
        customer.setApplicantReferenceID(applicantReferenceID);
        customer.setEntityID("");
        customer.setInternetUserID("");
        customer.setApplicationServiceRequestID("");
        modelService.save(customer);
        qeemahService.evictApplicationStatus();
        // Applicant reference ID created and stored
        return true;
    }

    private boolean isLicenseRejected() {
        Optional<ApplicationStatusData> applicationStatus = qeemahService.getApplicationStatus();
        if (applicationStatus.isPresent()) {
            String applicationStatusDescription = applicationStatus.get().getStatusDesc();
            if (!applicationStatusDescription.isEmpty() && applicationStatusDescription.toLowerCase().contains("rejected")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Executes an OData service call to post user register data.
     *
     * @param data the (posted) user register data
     * @return the user register data (returned by the OData service call
     */
    private UserRegisterData create(UserRegisterData data)
    {
        try
        {
            final HttpURLConnectionRequest request = new HttpURLConnectionRequest(REQUEST_TYPE_POST, getoDataService().createURL(getEntitySetName()));
            request.getRequestProperties().put(ACCEPT, APPLICATION_JSON);
            request.getRequestProperties().put(CONTENT_TYPE, APPLICATION_JSON);
            request.getRequestProperties().put(X_REQUESTED_WITH, X_REQUESTED_WITH_VALUE_X);

            ODataModel model = new ODataModel();
            getoDataReversePopulator().populate(data, model);
            request.setPayload(model.getAsPayload());

            final HttpURLConnectionResponse response = getoDataService().executeWithRetry(request);

            final EdmEntitySet entitySet = getoDataService().getEdm().getDefaultEntityContainer().getEntitySet(getEntitySetName());
            ODataModel resultModel = new ODataModel(EntityProvider.readEntry(APPLICATION_JSON, entitySet, new ByteArrayInputStream(response.getPayload()), NO_READ_PROPERTIES));

            UserRegisterData resultData = new UserRegisterData();
            getoDataPopulator().populate(resultModel, resultData);

            return resultData;
        } catch (IOException | EdmException | EntityProviderException e)
        {
            throw new SagiaODataException("Could not create data in backend system", e);
        }
    }

    /**
     * retrieves the User
     *
     * @param userRegisterData userRegisterData
     * @return Collection of UserRegisterData
     */
    public Collection<UserRegisterData> getUser(UserRegisterData userRegisterData) {
        return super.getCollection(
                UserRegisterData.class,
                "Userid", userRegisterData.getUserid(),
                "Password", userRegisterData.getPassword(),
                "Email", userRegisterData.getEmail(),
                "ContNumber", userRegisterData.getContNumber(),
                "ContPersname", userRegisterData.getContPersname(),
                "Company", userRegisterData.getCompany(),
                "Ref_id", userRegisterData.getRef_id(),
                "Country", userRegisterData.getCountry(),
                "CcodeMob", userRegisterData.getCcodeMob()
        );
    }


}
