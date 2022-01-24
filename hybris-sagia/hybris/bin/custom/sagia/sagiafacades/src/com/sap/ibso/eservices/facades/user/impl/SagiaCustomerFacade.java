package com.sap.ibso.eservices.facades.user.impl;

import com.investsaudi.portal.core.model.ContactTicketModel;
import com.sap.ibso.eservices.core.enums.LicenseStatus;
import com.sap.ibso.eservices.core.enums.TermsAndConditionsAcceptanceEventEnum;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.core.sagia.enums.ValidationError;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.core.sagia.services.SagiaCountryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaSectorService;
import com.sap.ibso.eservices.core.sagia.services.SagiaUserService;
import com.sap.ibso.eservices.facades.data.odata.ServiceRequestCreation;
import com.sap.ibso.eservices.facades.sagia.SagiaTermsAndConditionsFacade;
import com.sap.ibso.eservices.facades.user.data.SagiaRegisterData;
import com.sap.ibso.eservices.facades.user.exception.DuplicateEmailException;
import com.sap.ibso.eservices.facades.user.exception.DuplicatePhoneNumberException;
import com.sap.ibso.eservices.facades.user.exception.RegistrationValidationFailException;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * SagiaCustomerFacade
 * @package com.sap.ibso.eservices.facades.user.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaCustomerFacade extends DefaultCustomerFacade {
    private static final Logger LOG = Logger.getLogger(SagiaCustomerFacade.class);
    private static final String UPDATE_PASS_INVALID = "updatePwd.pwd.invalid";

    private SagiaCountryService sagiaCountryService;
    private SagiaSectorService sagiaSectorService;
    private MediaService mediaService;
    private SagiaUserService sagiaUserService;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Autowired
    private UserService userService;

    @Resource
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Resource
    private SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade;

    @Resource
	private LicenseApplyService licenseApplyService;
    
    /**
     * creates new customer from register data and persists it in  DB
     *
     * @param registerData registerData
     * @throws DuplicateUidException exception
     */
    @Override
    public void register(final RegisterData registerData) throws DuplicateUidException, DuplicatePhoneNumberException, DuplicateEmailException {
        SagiaRegisterData sagiaRegisterData = (SagiaRegisterData) registerData;

        validateParameterNotNullStandardMessage("registerData", sagiaRegisterData);
        Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
        Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
        Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

        final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
        newCustomer.setName(getCustomerNameStrategy().getName(sagiaRegisterData.getFirstName(), sagiaRegisterData.getLastName()));
        newCustomer.setCompany(sagiaRegisterData.getCompany());
        newCustomer.setMobileCountryCode(sagiaRegisterData.getMobileCountryCode());
        newCustomer.setMobileNumber(sagiaRegisterData.getMobileNumber());
        newCustomer.setUserNameEmail(sagiaRegisterData.getEmail());
        newCustomer.setCampaign(sagiaRegisterData.getCampaign());
        if(StringUtils.isNotEmpty(sagiaRegisterData.getEntityId())){
            newCustomer.setEntityID(sagiaRegisterData.getEntityId());
            newCustomer.setInternetUserID(sagiaRegisterData.getEntityId());
            newCustomer.setPasswordMigration(true);
        }
        final SagiaSectorModel sector = sagiaSectorService.getSectorForCode(((SagiaRegisterData) registerData).getSectorCode());
        newCustomer.setSector(sector);

        if (StringUtils.isNotBlank(sagiaRegisterData.getFirstName()) && StringUtils.isNotBlank(sagiaRegisterData.getLastName())) {
            newCustomer.setName(getCustomerNameStrategy().getName(sagiaRegisterData.getFirstName(), sagiaRegisterData.getLastName()));
        }
        final TitleModel title = getUserService().getTitleForCode(sagiaRegisterData.getTitleCode());
        newCustomer.setTitle(title);

        final SagiaCountryModel country = sagiaCountryService.getCountryForCode(((SagiaRegisterData) registerData).getCountryCode());
        newCustomer.setCountry(country);

        setUidForRegister(sagiaRegisterData, newCustomer);

        //Validation before saving
        final List<ValidationError> validationErrors = sagiaUserService.validateUniqueUserAttributes(newCustomer.getUid(), sagiaRegisterData.getMobileNumber(), sagiaRegisterData.getMobileCountryCode(), sagiaRegisterData.getEmail());
        if (!CollectionUtils.isEmpty(validationErrors)) {
            throw new RegistrationValidationFailException("Registration validation fails", validationErrors);
        }

        newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
        newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
//        newCustomer.setEntityID(sagiaRegisterData.ge);
        try {
            getCustomerAccountService().register(newCustomer, sagiaRegisterData.getPassword());
            getSagiaTermsAndConditionsFacade().acceptTermsAndConditions(newCustomer, TermsAndConditionsAcceptanceEventEnum.REGISTRATION);
        } catch (ModelSavingException e) {
            String exceptionMessage = e.getLocalizedMessage().toLowerCase();
            if (e.getCause() instanceof JaloGenericCreationException) {
                // We assume: JaloGenericCreationException caused by ConsistencyCheckException
                // Email address (as email) already exists
                throw new DuplicateUidException(e);
            } else if ((e.getCause() instanceof JaloSystemException) && exceptionMessage.contains("mobilenumber")) {
                // We assume:  JaloSystemException caused by HJMPException caused by DuplicateKeyException
                //It's wrong, because ModelSavingException also trigger this because of Email!
                // It must to be informative!
                // Country code and mobile number combination already exists
                throw new DuplicatePhoneNumberException(e);
            }
            else if (exceptionMessage.contains("usernameemail")) {
                throw new DuplicateEmailException(e);
            }

            // Unknown cause for ModelSavingException
            throw e;
        }

        /*
         * Note: if email address (as email) and country code/mobile number combination already exists then a DuplicateUidException is
         * already thrown by DefaultCustomerAccountService based on ModelSavingException caused by InterceptorException of
         * the UniqueAttributesInterceptor.
         */
    }

    public SagiaLicenseModel setApplicationServiceRequestID(ServiceRequestCreation serviceRequestCreation){
        final CustomerModel customerModel = getCurrentSessionCustomer();
        customerModel.setApplicationServiceRequestID(serviceRequestCreation.getObjectid());
        getModelService().save(customerModel);
        
        SagiaLicenseModel draftLicense = licenseApplyService.getDraftLicense();
        draftLicense.setStatus(LicenseStatus.APPLIED);
        draftLicense.setApplicantReferenceID(customerModel.getApplicantReferenceID());
        draftLicense.setApplicationServiceRequestID(customerModel.getApplicationServiceRequestID());
        draftLicense.setGuid(serviceRequestCreation.getGuid());
        getModelService().save(draftLicense);
		return draftLicense;
    }

    /**
     * Validates the new password
     * @param newPassword newPassword
     * @param errors errors
     */
    public void validate(String newPassword, final Errors errors) {
        if (StringUtils.isEmpty(newPassword)) {
            errors.rejectValue("pwd", UPDATE_PASS_INVALID);
        } else {
            String passwordRegex = getSagiaConfigurationFacade().getPasswordRegex();
            if (passwordRegex != null && !passwordRegex.trim().isEmpty()) {
                Pattern pattern = Pattern.compile(passwordRegex);
                Matcher matcher = pattern.matcher(newPassword);
                if (!matcher.matches()) {
                    errors.rejectValue("pwd", UPDATE_PASS_INVALID);
                }
            }
        }
    }

    /**
     * validates the CurrentPassword
     * @param password password
     * @return boolean
     */
    public boolean validateCurrentPassword(String password) {
        try {
            return getPasswordEncoderService().isValid(getUserService().getCurrentUser(), password);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * updates the Password
     * @param oldPassword oldPassword
     * @param newPassword newPassword
     * @param errors errors
     */
    public void updatePassword(String oldPassword, String newPassword, final Errors errors) {
        UserModel userModel = getUserService().getCurrentUser();
        if (getPasswordEncoderService().isValid(userModel, oldPassword)) {
            userModel.setPassword(newPassword);
            getModelService().save(userModel);
        } else {
            errors.rejectValue("pwd", UPDATE_PASS_INVALID);
        }
    }

    /**
     * Gets Opportunities Created by the customer
     * @param contactEmail contactEmail
     */
    public List<ContactTicketModel> getUserRaisedOpportunities(String contactEmail) {
        return sagiaUserService.getUserRaisedOpportunities(contactEmail);
    }

    /**
     * Get customer details based on mobileNumber and mobileCountryCode
     * @param mobileNumber
     * @param mobileCountryCode
     * @return
     */
   public CustomerData getCustomerByMobileNumber(final String mobileNumber, final String mobileCountryCode)
    {
        Assert.hasText(mobileNumber, "The field [mobileNumber] cannot be empty");
        CustomerModel customer = sagiaUserService.getCustomerByMobileNumber(mobileNumber,mobileCountryCode);
        validateParameterNotNull(customer, "No customer available in system with this phone number" );
        if(customer != null) {
            return getCustomerConverter().convert(customer);
        }
        throw new UnknownIdentifierException("Cannot find user with propertyValue '" + mobileNumber + "'");
    }

        public SagiaConfigurationFacade getSagiaConfigurationFacade() {
        return sagiaConfigurationFacade;
    }

    public void setSagiaConfigurationFacade(SagiaConfigurationFacade sagiaConfigurationFacade) {
        this.sagiaConfigurationFacade = sagiaConfigurationFacade;
    }

    public SagiaCountryService getSagiaCountryService() {
        return sagiaCountryService;
    }

    public void setSagiaCountryService(SagiaCountryService sagiaCountryService) {
        this.sagiaCountryService = sagiaCountryService;
    }

    public SagiaSectorService getSagiaSectorService() {
        return sagiaSectorService;
    }

    public void setSagiaSectorService(SagiaSectorService sagiaSectorService) {
        this.sagiaSectorService = sagiaSectorService;
    }

    public MediaService getMediaService() {
        return mediaService;
    }

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public void updateProfile(CustomerData customerData) throws DuplicateUidException {
        validateDataBeforeUpdate(customerData);

        final String name = getCustomerNameStrategy().getName(customerData.getFirstName(), customerData.getLastName());
        final CustomerModel customer = getCurrentSessionCustomer();

        customer.setCompany(customerData.getCompany());
        customer.setSector(sagiaSectorService.getSectorForCode(customerData.getSector().getCode()));
        customer.setCountry(sagiaCountryService.getCountryForCode(customerData.getCountry().getCode()));
        customer.setMobileCountryCode(customerData.getMobileCountryCode());
        customer.setMobileNumber(customerData.getMobileNumber());
        customer.setUserNameEmail(customerData.getEmail());
        customer.setCompanyWebsite(customerData.getCompanyWebsite());

        try {
            getCustomerAccountService().updateProfile(customer, customerData.getTitleCode(), name, customerData.getUid());
        } catch (ModelSavingException e) {
            String exceptionMessage = e.getLocalizedMessage();

            if (exceptionMessage.contains("DuplicateKeyException") && exceptionMessage.contains("MOBILENUMBERINDEX")) {
                // Note: mobileNumberIndex is defined in sagiacore-items.xml

                // Country code and mobile number combination already exists
                throw new DuplicatePhoneNumberException(e);
            } else if (exceptionMessage.contains("DuplicateKeyException") && exceptionMessage.contains("USERNAMEEMAILINDEX")) {

                throw new DuplicateEmailException(e);
            }

            // Unknown cause for ModelSavingException
            throw e;
        }
    }

    /**
     * updates email
     * @param customerData customerData
     * @throws DuplicateEmailException exception
     */
    public void updateEmail(CustomerData customerData) throws DuplicateEmailException {
        validateDataBeforeUpdate(customerData);
        final CustomerModel customer = getCurrentSessionCustomer();
        if (StringUtils.isNotBlank(customerData.getEmail())) {
            customer.setUserNameEmail(customerData.getEmail());
        }
    }

    @Override
    protected void validateDataBeforeUpdate(CustomerData customerData) {
        super.validateDataBeforeUpdate(customerData);
        Assert.hasText(customerData.getMobileNumber(), "Mobile number can't be null");
        Assert.hasText(customerData.getSector().getCode(), "Sector can't be null");
        Assert.hasText(customerData.getCountry().getCode(), "Country can't be null");
        Assert.hasText(customerData.getEmail(), "Email can't be null");
    }

    /**
     * update the media item that represents profile picture attached to the current user.
     * userId represents the media code and will be replaced with new image each time user updates it.
     *
     * @param file file
     * @throws IOException exception
     */

    public void updateProfilePicture(MultipartFile file) throws IOException {
        UserModel userModel = getUserService().getCurrentUser();
        // create media
        String mediaCode = userModel.getUid();
        MediaModel mediaModel = getMediaModel(mediaCode);
        getMediaService().setDataForMedia(mediaModel, file.getBytes());
        //add it to user
        userModel.setProfilePicture(mediaModel);
        getModelService().save(userModel);
    }
    
    /**
     * update the media item that represents company logo picture attached to the current user.
     * userId represents the media code and will be replaced with new image each time user updates it.
     *
     * @param file file
     * @throws IOException exception
     */

    public void updateCompanyLogo(MultipartFile file) throws IOException {
        UserModel userModel = getUserService().getCurrentUser();
        // create media
        if(userModel instanceof CustomerModel) {
        	CustomerModel customer = (CustomerModel)userModel;
        	String mediaCode = userModel.getUid();
            MediaModel mediaModel = getMediaModel(mediaCode);
            getMediaService().setDataForMedia(mediaModel, file.getBytes());
            //add it to user
            customer.setCompanyLogo(mediaModel);
            getModelService().save(customer);
        	
        }
    }

    /**
     * update the media item that represents dashboard picture attached to the current user.
     * userId-dashboard represents the media code and will be replaced with new image each time user updates it.
     *
     * @param file file
     * @throws IOException exception
     */
    public void updateDashboardPicture(MultipartFile file) {
        try {
            UserModel userModel = getUserService().getCurrentUser();
            // create media
            String mediaCode = userModel.getUid() + "dashboard";
            MediaModel mediaModel = getMediaModel(mediaCode);
            getMediaService().setDataForMedia(mediaModel, file.getBytes());
            //add it to user
            final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();
            customerModel.setDashboardMedia(mediaModel);
            customerModel.setShouldDisplaySetCompanyPhotoOption(Boolean.FALSE);
            getModelService().save(customerModel);
        } catch(IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private MediaModel getMediaModel(String mediaCode) {
        MediaModel mediaModel;
        try {
            mediaModel = getMediaService().getMedia(mediaCode);
        } catch (UnknownIdentifierException e) {
            mediaModel = getModelService().create(MediaModel.class);
            mediaModel.setCode(mediaCode);
            getModelService().save(mediaModel);
        }
        return mediaModel;
    }

    /**
     * should DisplayOptionToSetNowCompanyPhoto
     * @param setNowCompanyPhoto setNowCompanyPhoto
     */
    public void shouldDisplayOptionToSetNowCompanyPhoto(boolean setNowCompanyPhoto) {
        final CustomerModel customerModel = getCurrentSessionCustomer();
        customerModel.setShouldDisplaySetCompanyPhotoOption(setNowCompanyPhoto);
        getModelService().save(customerModel);
    }

    /**
     * displays DashboardTutorial
     * @return boolean
     */
    public Boolean displayDashboardTutorial(){
        final CustomerModel customerModel = getCurrentSessionCustomer();
        return customerModel.getDashboardTutorialDismissed() != null ? !customerModel.getDashboardTutorialDismissed() : Boolean.FALSE;
    }

    /**
     * dismisses DashboardTutorial
     */
    public void dismissDashboardTutorial(){
        final CustomerModel customerModel = getCurrentSessionCustomer();
        customerModel.setDashboardTutorialDismissed(true);
        getModelService().save(customerModel);
    }

    /**
     * displays DashboardNoLicenseTutorial
     *
     * @return boolean
     */
    public Boolean displayDashboardNoLicenseTutorial(){
        final CustomerModel customerModel = getCurrentSessionCustomer();
        return !customerModel.getDashboardNoLicenseTutorialDismissed();
    }

    /**
     * dismisses DashboardNoLicenseTutorial
     */
    public void dismissDashboardNoLicenseTutorial(){
        final CustomerModel customerModel = getCurrentSessionCustomer();
        customerModel.setDashboardNoLicenseTutorialDismissed(true);
        getModelService().save(customerModel);
    }

    @Override
    public void forgottenPassword(final String email) {
        Assert.hasText(email, "The field [email] cannot be empty");
        try {
            final CustomerModel customerModel = sagiaUserService.getCustomerByEmail(email);
            getCustomerAccountService().forgottenPassword(customerModel);
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

	public CustomerData getSagiaCustomer(final String uid)
	{
		Assert.hasText(uid, "The field [uid] cannot be empty");
		return getCustomerConverter().convert(getUserService().getUserForUID(uid.toLowerCase(), CustomerModel.class));

	}
    
    @Override
    public PasswordEncoderService getPasswordEncoderService() {
        return passwordEncoderService;
    }

    @Override
    public void setPasswordEncoderService(PasswordEncoderService passwordEncoderService) {
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SagiaTermsAndConditionsFacade getSagiaTermsAndConditionsFacade() {
        return sagiaTermsAndConditionsFacade;
    }

    public void setSagiaTermsAndConditionsFacade(SagiaTermsAndConditionsFacade sagiaTermsAndConditionsFacade) {
        this.sagiaTermsAndConditionsFacade = sagiaTermsAndConditionsFacade;
    }

    public void setSagiaUserService(final SagiaUserService sagiaUserService)
    {
        this.sagiaUserService = sagiaUserService;
    }
}
