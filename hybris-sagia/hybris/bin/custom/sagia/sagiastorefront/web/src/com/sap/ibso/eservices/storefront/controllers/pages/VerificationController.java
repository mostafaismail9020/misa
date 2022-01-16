package com.sap.ibso.eservices.storefront.controllers.pages;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMException;

import com.sap.ibso.eservices.core.enums.MobileVerificationStatus;
import com.sap.ibso.eservices.core.enums.QeemahEmailVerificationStatus;
import com.sap.ibso.eservices.core.enums.RegEmailVerificationStatus;
import com.sap.ibso.eservices.sagiaservices.data.zverifyModuleSrvOData.EmailVerificationData;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.sagiaservices.services.authentication.AuthenticationCodeGeneratorService;
import com.sap.ibso.eservices.sagiaservices.services.impl.EmailVerificationSetService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SmsService;
import com.sap.ibso.eservices.storefront.forms.SagiaAuthenticateCodeForm;
import com.sap.ibso.eservices.storefront.forms.SagiaVerificationForm;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.facades.sagia.impl.SagiaVerificationFacade;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commerceservices.customer.TokenInvalidatedException;
import de.hybris.platform.commerceservices.event.ForgottenPwdEvent;
import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.commerceservices.security.SecureTokenService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import com.sap.ibso.eservices.core.event.EmailVerificationEvent;
import com.sap.ibso.eservices.facades.user.SagiaUserFacade;
import com.sap.ibso.eservices.sagiaservices.data.zverifyModuleSrvOData.EmailSubscriptionFeeData;

@Controller
@RequestMapping(value = "/verification")
public class VerificationController extends AbstractLoginPageController {

	@Resource
	private UserService userService;

	@Resource
	private AutoLoginStrategy autoLoginStrategy;

	@Resource
	private SmsService smsService;

	@Resource
	private ModelService modelService;

	@Resource
	private SecureTokenService secureTokenService;

	@Resource(name = "sagiaAuthenticateCodeFormValidator")
	private Validator sagiaAuthenticateCodeFormValidator;

	@Resource(name = "defaultAuthenticationCodeGeneratorService")
	private AuthenticationCodeGeneratorService authenticationCodeGeneratorService;

	@Resource(name = "sagiaConfigurationFacade")
	private SagiaConfigurationFacade sagiaConfigurationFacade;

	@Resource(name = "emailVerificationSetService")
	private EmailVerificationSetService emailVerificationSetService;

	@Resource
	private SagiaVerificationFacade sagiaVerificationFacade;

	@Resource(name = "sagiaUserFacade")
	private SagiaUserFacade sagiaUserFacade;

	private static final Logger LOG = Logger.getLogger(VerificationController.class);

	private static final String SAGIA_CODE_AUTH_CMS_PAGE = "login-second-step";
	private static final String SAGIA_VERIFICATION_CMS_PAGE = "verification";
	private static final String SAGIA_CHECK_SUBSCRIPTION_FEE_CMS_PAGE = "checkSubscriptionFee";
	private static final String IS_VERIFICATION_PAGE = "isVerificationPage";
	private static final String SAGIA_VERIFICATION_FORM = "sagiaVerificationForm";
	private static final String SAGIA_AUTHENTICATE_CODE_FORM = "sagiaAuthenticateCodeForm";
	private static final String MOBILE_VERIFICATION_ENABLED = "isMobileVerificationEnabled";
	private static final String TWO_FACTOR_AUTHENTICATION_ENABLED = "isEnableTwoFactorAuthService";
	private static final String EMAIL_VERIFICATION_ENABLED = "isEmailVerificationEnabled";
	private static final String MOBILE_VERIFICATION_STATUS = "mobileStatus";
	private static final String REG_EMAIL_VERIFICATION_STATUS = "regEmailStatus";
	private static final String QEEMAH_EMAIL_VERIFICATION_STATUS = "qeemahEmailStatus";
	private static final String QEEMAH_EMAIL = "qeemahEmail";
	private static final String EMAIL_TYPE_REG = "reg";
	private static final String EMAIL_TYPE_QEEMAH = "qeemah";
	private static final String VERIFIED = "VERIFIED";
	private static final String FORM_GLOBAL_ERROR = "form.global.error";
	private static final String CODE_AUTH_ERROR = "form.code.authentication.error";
	private static final String SMS_SERVICE_ERROR = "form.sms.service.error";
	private static final String HYBRIS_USERNAME = "hybrisusername";
	private static final String HYBRIS_PASS = "hybrispassword";
	private static final String AUTH_CODE_FORM_ERROR = "sagiaAuthenticateCodeFormError";
	private static final String AUTH_CODE = "AUTH_CODE";
	private static final String AUTH_CODE_GENERATED_DATE = "AUTH_CODE_GENERATED_DATE";
	private static final String SECOND_PAGE_AUTHETICATION_RETRIES = "SECOND_PAGE_AUTHETICATION_RETRIES";
	private static final String REDIRECT_HOME = "redirect:/";
	public static final Pattern MOBILE_NUMBER_REGEX = Pattern.compile("\\d+");

	//private long tokenValiditySeconds = sagiaConfigurationFacade.getEmailValidityInSeconds();

	private HttpSessionRequestCache httpSessionRequestCache;

	@RequestMapping(method = RequestMethod.GET)
	public String getVerification(HttpSession session, final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
		CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
		if(sagiaConfigurationFacade.isMobileVerificationEnabled() && sagiaConfigurationFacade.isEmailVerificationEnabled()) {
			if((customerModel.getMobileStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getMobileStatus().getCode()))
					&& (customerModel.getRegEmailStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getRegEmailStatus().getCode()))
					&& (StringUtils.isEmpty(customerModel.getEntityID()) ||(StringUtils.isNotEmpty(customerModel.getEntityID()) && customerModel.getQeemahEmailStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getQeemahEmailStatus().getCode())))) {
				if(StringUtils.isNotEmpty(customerModel.getEntityID()) && customerModel.getQeemahEmailStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getQeemahEmailStatus().getCode())) {
					return toCheckSubscriptionFee(session, model, request, response, customerModel);
				}

				return toDashBoard(session, request, response);
			}
		}else if(!sagiaConfigurationFacade.isMobileVerificationEnabled() && sagiaConfigurationFacade.isEmailVerificationEnabled()) {
			if((customerModel.getRegEmailStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getRegEmailStatus().getCode()))
					&& (StringUtils.isEmpty(customerModel.getEntityID()) ||(StringUtils.isNotEmpty(customerModel.getEntityID()) && customerModel.getQeemahEmailStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getQeemahEmailStatus().getCode())))) {
				if(StringUtils.isNotEmpty(customerModel.getEntityID()) && customerModel.getQeemahEmailStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getQeemahEmailStatus().getCode())) {
					return toCheckSubscriptionFee(session, model, request, response, customerModel);
				}

				return toDashBoard(session, request, response);
			}
		}else if(sagiaConfigurationFacade.isMobileVerificationEnabled() && !sagiaConfigurationFacade.isEmailVerificationEnabled()) {
			if(customerModel.getMobileStatus() != null && VERIFIED.equalsIgnoreCase(customerModel.getMobileStatus().getCode())) {

				return toDashBoard(session, request, response);
			}
		}

		String mobileCountryCode = customerModel.getMobileCountryCode().startsWith("+") ? customerModel.getMobileCountryCode() : ("+" + customerModel.getMobileCountryCode());
		String customerMobile = mobileCountryCode + customerModel.getMobileNumber();
		SagiaVerificationForm verificationForm = new SagiaVerificationForm();
		verificationForm.setMobile(customerModel.getMobileNumber());
		verificationForm.setCountryCode(customerModel.getMobileCountryCode());
		verificationForm.setRegEmail(customerModel.getUserNameEmail());
		//verificationForm.setQeemahEmail(customerModel.getQeemahEmail());
		//setAuthCodeOnSession(session);
		//model.addAttribute(SAGIA_AUTHENTICATE_CODE_FORM, new SagiaAuthenticateCodeForm());
		//sendSMS(session, model);
		if(StringUtils.isNotEmpty(customerModel.getEntityID())) {
			if(StringUtils.isNotEmpty(customerModel.getQeemahEmail()) && customerModel.getQeemahEmailStatus() != null && "PENDING".equalsIgnoreCase(customerModel.getQeemahEmailStatus().getCode())) {
				//if(customerModel.getQeemahEmailStatus() != null && "PENDING".equalsIgnoreCase(customerModel.getQeemahEmailStatus().getCode())) {
				verificationForm.setQeemahEmail(customerModel.getQeemahEmail());
				//}
			}else {
				//EmailVerificationData emailVerificationData = emailVerificationSetService.get(customerModel.getEntityID());
				EmailVerificationData emailVerificationData = sagiaVerificationFacade.getEmailVerification(customerModel.getEntityID().trim());
				if(emailVerificationData != null){
					LOG.info("########## emailVerificationData "+emailVerificationData.getEmailId()+" **** verified : "+emailVerificationData.getVerified()+" #### Isverificationrequired "+emailVerificationData.getIsverificationrequired()+"#### IsSubscriptionFeeCheckRequired "+emailVerificationData.getIsSubscriptionFeeCheckRequired()+" #### IsFinancialStatementCheckRequired "+emailVerificationData.getIsFinancialStatementCheckRequired());
				}
				if(emailVerificationData != null && StringUtils.isNotEmpty(emailVerificationData.getIsverificationrequired()) && emailVerificationData.getIsverificationrequired().equalsIgnoreCase("N") ) {
					if(StringUtils.isNotEmpty(emailVerificationData.getIsSubscriptionFeeCheckRequired())) {
						if(emailVerificationData.getIsSubscriptionFeeCheckRequired().equalsIgnoreCase("Y")) {
							customerModel.setIsOutstandingFee(true);
							modelService.save(customerModel);
							storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CHECK_SUBSCRIPTION_FEE_CMS_PAGE));
							setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CHECK_SUBSCRIPTION_FEE_CMS_PAGE));
							return ControllerConstants.Views.Pages.Account.SubscriptionFeeCheckPage;
						}else {
							customerModel.setIsOutstandingFee(false);
							modelService.save(customerModel);
						}
					}
					if(StringUtils.isNotEmpty(emailVerificationData.getIsFinancialStatementCheckRequired())) {
						if(emailVerificationData.getIsFinancialStatementCheckRequired().equalsIgnoreCase("Y")) {
							customerModel.setIsPendingFinancialStatement(true);
							modelService.save(customerModel);
						}else {
							customerModel.setIsPendingFinancialStatement(false);
							modelService.save(customerModel);
						}
					}

					return toDashBoard(session, request, response);

				}
				if(StringUtils.isNotEmpty(emailVerificationData.getEmailId())) {
					customerModel.setQeemahEmail(emailVerificationData.getEmailId().toLowerCase());
					if(StringUtils.isNotEmpty(emailVerificationData.getVerified()) && !emailVerificationData.getVerified().equalsIgnoreCase("y") ) {
						customerModel.setQeemahEmailStatus(QeemahEmailVerificationStatus.NOT_VERIFIED);
					}else {
						customerModel.setQeemahEmailStatus(QeemahEmailVerificationStatus.VERIFIED);
					}
					modelService.save(customerModel);
				}

			}
		}
		verificationForm.setQeemahEmail(customerModel.getQeemahEmail());

		model.addAttribute(QEEMAH_EMAIL_VERIFICATION_STATUS, customerModel.getQeemahEmailStatus());
		model.addAttribute(MOBILE_VERIFICATION_ENABLED, sagiaConfigurationFacade.isMobileVerificationEnabled());
		model.addAttribute(TWO_FACTOR_AUTHENTICATION_ENABLED, sagiaConfigurationFacade.isEnableTwoFactorAuthService());
		model.addAttribute(EMAIL_VERIFICATION_ENABLED, sagiaConfigurationFacade.isEmailVerificationEnabled());
		model.addAttribute(SAGIA_VERIFICATION_FORM, verificationForm);
		model.addAttribute(MOBILE_VERIFICATION_STATUS, customerModel.getMobileStatus());
		model.addAttribute(REG_EMAIL_VERIFICATION_STATUS, customerModel.getRegEmailStatus());
		model.addAttribute(QEEMAH_EMAIL, customerModel.getQeemahEmail());
		model.addAttribute("customerMobile", customerMobile);
		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());

		return getView();
	}

	public String toCheckSubscriptionFee(HttpSession session, final Model model, final HttpServletRequest request, final HttpServletResponse response, CustomerModel customerModel) throws CMSItemNotFoundException {
		//model.addAttribute(IS_VERIFICATION_PAGE, true);
		if(customerModel != null && StringUtils.isNotEmpty(customerModel.getEntityID())){
			EmailVerificationData emailVerificationData = sagiaVerificationFacade.getEmailVerification(customerModel.getEntityID().trim());
			LOG.info("#### IsSubscriptionFeeCheckRequired "+emailVerificationData.getIsSubscriptionFeeCheckRequired());
			if(emailVerificationData != null && StringUtils.isNotEmpty(emailVerificationData.getIsSubscriptionFeeCheckRequired())) {
				//return toDashBoard(session, request, response);
				if(emailVerificationData.getIsSubscriptionFeeCheckRequired().equalsIgnoreCase("Y")) {
					customerModel.setIsOutstandingFee(true);
					modelService.save(customerModel);
					model.addAttribute("SubscriptionFeeCheckMessage", emailVerificationData.getSubscriptionFeeCheckMessage());
					storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CHECK_SUBSCRIPTION_FEE_CMS_PAGE));
					setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CHECK_SUBSCRIPTION_FEE_CMS_PAGE));
					return ControllerConstants.Views.Pages.Account.SubscriptionFeeCheckPage;
				}else {
					customerModel.setIsOutstandingFee(false);
					modelService.save(customerModel);
				}
			}
			LOG.info("#### IsFinancialStatementCheckRequired "+emailVerificationData.getIsFinancialStatementCheckRequired());
			if(emailVerificationData != null && StringUtils.isNotEmpty(emailVerificationData.getIsFinancialStatementCheckRequired())) {
				if(emailVerificationData.getIsFinancialStatementCheckRequired().equalsIgnoreCase("Y")) {
					customerModel.setIsPendingFinancialStatement(true);
					modelService.save(customerModel);

				}else {
					customerModel.setIsPendingFinancialStatement(false);
					modelService.save(customerModel);
				}
			}
		}
		return toDashBoard(session, request, response);
	}

	@RequestMapping(value = "/later", method = RequestMethod.GET)
	public String toVerifyLater(final HttpSession session, final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
		autoLoginStrategy.login((String) session.getAttribute(HYBRIS_USERNAME),
				(String) session.getAttribute(HYBRIS_PASS), request, response);
		setLastSuccessLoginTime((CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME)));
		session.removeAttribute(HYBRIS_USERNAME);
		session.removeAttribute(HYBRIS_PASS);
		session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
		return REDIRECT_HOME;
	}

	@RequestMapping(value = "/emailsubscriptionfee", method = RequestMethod.GET)
	public String emailSubscription(final HttpSession session, final Model model, final HttpServletRequest request, final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
		EmailSubscriptionFeeData emailSubscriptionFeeData = sagiaVerificationFacade.getEmailSubscriptionFee(customerModel.getEntityID().trim());
		if(emailSubscriptionFeeData != null) {
			LOG.info("******* entered emailsubscriptionfee "+emailSubscriptionFeeData.getMessage());
		}
		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
				emailSubscriptionFeeData.getMessage());

		return "redirect:/verification";
	}


	@RequestMapping(value = "/billPayment", method = RequestMethod.GET)
	public String billPayment(final HttpSession session, final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
		toDashBoard(session, request, response);
		return REDIRECT_HOME;
	}

	@RequestMapping(value = "/backtologin", method = RequestMethod.GET)
	public String backToLogin(final HttpSession session, final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
		session.removeAttribute(HYBRIS_USERNAME);
		session.removeAttribute(HYBRIS_PASS);
		session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
		return REDIRECT_HOME;
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verifyVerification(HttpSession session, final Model model, @ModelAttribute final SagiaVerificationForm sagiaVerificationForm, final BindingResult bindingResult) throws CMSItemNotFoundException {
		LOG.info("########## enter verify "+sagiaVerificationForm.getMobile());
		setAuthCodeOnSession(session);
		model.addAttribute(SAGIA_AUTHENTICATE_CODE_FORM, new SagiaAuthenticateCodeForm());
		model.addAttribute(SAGIA_VERIFICATION_FORM, sagiaVerificationForm);
		model.addAttribute(IS_VERIFICATION_PAGE, true);
		sendSMS(session, model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));

		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	@RequestMapping(params="smsverify", method = RequestMethod.POST)
	public String mobileVerify(HttpSession session, final Model model, @ModelAttribute final SagiaVerificationForm sagiaVerificationForm, final BindingResult bindingResult, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		final Matcher matcher = MOBILE_NUMBER_REGEX.matcher(sagiaVerificationForm.getMobile());
		if (!matcher.matches()) {
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,"register.mobileNumber.digitsOnly");
			return "redirect:/verification";
		}
		LOG.info("########## enter smsverify "+sagiaVerificationForm.getMobile());
		model.addAttribute(SAGIA_AUTHENTICATE_CODE_FORM, new SagiaAuthenticateCodeForm());
		model.addAttribute(SAGIA_VERIFICATION_FORM, sagiaVerificationForm);
		model.addAttribute(IS_VERIFICATION_PAGE, true);
		if(sagiaVerificationForm.getMobile() != null) {
			CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
			if(!customerModel.getMobileNumber().equalsIgnoreCase(sagiaVerificationForm.getMobile())) {
				boolean validationResult = sagiaUserFacade.validateUniqueValue("", "", sagiaVerificationForm.getMobile(), sagiaVerificationForm.getCountryCode());
				if (!validationResult) {
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
							"register.mobileNumber.duplicate");
					return "redirect:/verification";
				}
			}

			customerModel.setMobileNumber(sagiaVerificationForm.getMobile());
			customerModel.setMobileCountryCode(sagiaVerificationForm.getCountryCode());
			modelService.save(customerModel);
		}
		setAuthCodeOnSession(session);
		sendSMSOnVerificationPage(session, model, XSSFilterUtil.filter(sagiaVerificationForm.getMobile()));
		storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));

		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	private void sendSMSOnVerificationPage(HttpSession session, Model model, String mobile) {
		CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
		String mobileCountryCode = customerModel.getMobileCountryCode().startsWith("+") ? customerModel.getMobileCountryCode() : ("+" + customerModel.getMobileCountryCode());

		try {
			if(mobile.toString().equalsIgnoreCase(customerModel.getMobileNumber().toString()))
			{
				smsService.send(mobileCountryCode + customerModel.getMobileNumber(), (String)session.getAttribute(AUTH_CODE));
			}

		} catch(Exception e) {
			LOG.error("stacktrace:", e);
			model.addAttribute(AUTH_CODE_FORM_ERROR, SMS_SERVICE_ERROR);
		}

		LOG.debug("Generated code: "+ session.getAttribute(AUTH_CODE));
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(final Model model, final HttpSession session, final HttpServletRequest request, @ModelAttribute final SagiaVerificationForm sagiaVerificationForm, final HttpServletResponse response, @ModelAttribute final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm, final BindingResult bindingResult) throws CMSItemNotFoundException {
		getSagiaAuthenticateCodeFormValidator().validate(sagiaAuthenticateCodeForm, bindingResult);
		LOG.info("########## Mobile :authenticate "+sagiaVerificationForm.getMobile());
		model.addAttribute(IS_VERIFICATION_PAGE, true);
		if (bindingResult.hasErrors()) {
			LOG.info("########## Error :authenticate ");
			model.addAttribute(AUTH_CODE_FORM_ERROR, FORM_GLOBAL_ERROR);
			model.addAttribute(sagiaAuthenticateCodeForm);
			model.addAttribute(sagiaVerificationForm);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
			setUpMetaDataForContentPage(model, (ContentPageModel) getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
			return ControllerConstants.Views.Pages.Account.AccountLoginPage;
		}

		Long sessionAuthenticationCodeGeneratedDate = (Long) session.getAttribute(AUTH_CODE_GENERATED_DATE);
		String sessionAuthenticationCode = (String) session.getAttribute(AUTH_CODE);
		Integer retries = null;
		try {
			retries = (Integer)session.getAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
		}
		if(retries == null) {
			retries = 0;
			session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, retries);
		}

		//token expires after 5 minutes
		if ((sessionAuthenticationCodeGeneratedDate + 420000 < System.currentTimeMillis()) ||
				!sessionAuthenticationCode.equals(sagiaAuthenticateCodeForm.getCode().trim())) {
			LOG.info("########## time :authenticate "+(sessionAuthenticationCodeGeneratedDate + 420000 < System.currentTimeMillis())+" ****** "+sessionAuthenticationCode.equals(sagiaAuthenticateCodeForm.getCode().trim()));
			model.addAttribute("sagiaAuthenticateCodeIncorrectError", CODE_AUTH_ERROR);
			model.addAttribute(sagiaAuthenticateCodeForm);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
			setUpMetaDataForContentPage(model, (ContentPageModel) getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
			if(retries >= 5) {
				session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
				try {
					UserModel user = userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
					user.setLoginDisabled(true);
					modelService.save(user);
					return REDIRECT_HOME;
				} catch(Exception e) {
					LOG.error(e.getMessage(), e);
				}
			} else {
				session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, retries + 1);
			}
			return ControllerConstants.Views.Pages.Account.AccountLoginPage;
		}
		System.out.println("########## Retries :authenticate "+retries);
		CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
		customerModel.setMobileStatus(MobileVerificationStatus.VERIFIED);
		modelService.save(customerModel);
		model.addAttribute(MOBILE_VERIFICATION_STATUS, customerModel.getMobileStatus());
//	        autoLoginStrategy.login((String) session.getAttribute(HYBRIS_USERNAME), (String) session.getAttribute(HYBRIS_PASS), request, response);
//	        System.out.println("########## enter9 :authenticate ");
//	        session.removeAttribute(HYBRIS_USERNAME);
//	        session.removeAttribute(HYBRIS_PASS);
		session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
		return "redirect:/verification";
	}

	@RequestMapping(params="regemailverify", method = RequestMethod.POST)
	public String regEmailVerify(HttpSession session, final Model model, @ModelAttribute final SagiaVerificationForm sagiaVerificationForm, final BindingResult bindingResult, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		LOG.info("########## enter regemailverify "+sagiaVerificationForm.getRegEmail());
		model.addAttribute(SAGIA_VERIFICATION_FORM, sagiaVerificationForm);
		model.addAttribute(IS_VERIFICATION_PAGE, true);
		if(sagiaVerificationForm.getRegEmail() != null) {
			CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));

			if(!customerModel.getUserNameEmail().equalsIgnoreCase(sagiaVerificationForm.getRegEmail())) {
				boolean validationResult = sagiaUserFacade.validateUniqueValue("", sagiaVerificationForm.getRegEmail(), "", "");
				if (!validationResult) {
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
							"update.email.duplicate");
					return "redirect:/verification";
				}
			}

			customerModel.setUserNameEmail(sagiaVerificationForm.getRegEmail());

			final long timeStamp = sagiaConfigurationFacade.getEmailValidityInSeconds() > 0L ? new Date().getTime() : 0L;
			final SecureToken data = new SecureToken(customerModel.getUid(), timeStamp);
			final String token = secureTokenService.encryptData(data);
			customerModel.setToken(token);
			customerModel.setRegEmailStatus(RegEmailVerificationStatus.PENDING);
			LOG.info("########## token "+token);
			modelService.save(customerModel);
			//eventService.publishEvent(initializeEvent(new EmailVerificationEvent(token, EMAIL_TYPE_REG), customerModel));
			sagiaVerificationFacade.sendVerification(token, EMAIL_TYPE_REG, customerModel);
		}
		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
				"account.confirmation.verification.email.link.sent");

		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());

		return "redirect:/verification";
	}

	@RequestMapping(params="qeemahemailverify", method = RequestMethod.POST)
	public String qeemahEmailVerify(HttpSession session, final Model model, @ModelAttribute final SagiaVerificationForm sagiaVerificationForm, final BindingResult bindingResult, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		LOG.info("########## enter qeemahemailverify "+sagiaVerificationForm.getQeemahEmail());
		model.addAttribute(SAGIA_VERIFICATION_FORM, sagiaVerificationForm);
		model.addAttribute(IS_VERIFICATION_PAGE, true);
		if(sagiaVerificationForm.getQeemahEmail() != null) {
			CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));

			if(!customerModel.getQeemahEmail().equalsIgnoreCase(sagiaVerificationForm.getQeemahEmail())) {
				boolean validationResult = sagiaUserFacade.validateUniqueValue("", sagiaVerificationForm.getQeemahEmail(), "", "");
				if (!validationResult) {
					GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
							"update.email.duplicate");
					return "redirect:/verification";
				}
			}

			customerModel.setQeemahEmail(sagiaVerificationForm.getQeemahEmail());

			final long timeStamp = sagiaConfigurationFacade.getEmailValidityInSeconds() > 0L ? new Date().getTime() : 0L;
			final SecureToken data = new SecureToken(customerModel.getUid(), timeStamp);
			final String qeemahToken = secureTokenService.encryptData(data);
			customerModel.setQeemahToken(qeemahToken);
			customerModel.setQeemahEmailStatus(QeemahEmailVerificationStatus.PENDING);
			LOG.info("########## qeemahtoken "+qeemahToken);
			modelService.save(customerModel);
			//eventService.publishEvent(initializeEvent(new EmailVerificationEvent(qeemahToken, EMAIL_TYPE_QEEMAH), customerModel));
			sagiaVerificationFacade.sendVerification(qeemahToken, EMAIL_TYPE_QEEMAH, customerModel);
		}
		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
				"account.confirmation.verification.email.link.sent");

		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());

		return "redirect:/verification";
	}


	@RequestMapping(value = "/emailverify", method = RequestMethod.GET)
	public String emailVerify(@RequestParam("token") final String token, @RequestParam("type") final String type, final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		LOG.info("########## enter emailverify "+token+"  ***** "+type);

		try {
			validateToken(token, type, model, redirectModel);
		}
		catch (final TokenInvalidatedException e)
		{
			LOG.error(e.getMessage(),e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
					"text.verification.notification.regemail.notverify");
		}
		catch (final RuntimeException e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(e);
			}
			LOG.error(e.getMessage(),e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
					"text.verification.notification.regemail.notverify");
		}

		//storeCmsPageInModel(model, getContentPageForLabelOrId(null));
		//setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
		//storeCmsPageInModel(model, getCmsPage());
		//setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());

		return "redirect:/login";
	}


	@RequestMapping(value = "/resend", method = RequestMethod.GET)
	public String resendAuthenticationCode(HttpSession session, final Model model, @ModelAttribute final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm) throws CMSItemNotFoundException {
		session.setAttribute(AUTH_CODE, authenticationCodeGeneratorService.getGeneratedAuthenticationCode());
		session.setAttribute(AUTH_CODE_GENERATED_DATE, System.currentTimeMillis());
		sendSMS(session, model);
		model.addAttribute(sagiaAuthenticateCodeForm);
		model.addAttribute(IS_VERIFICATION_PAGE, true);
		storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	private void validateToken(final String token, final String type, final Model model, final RedirectAttributes redirectModel) throws TokenInvalidatedException
	{
		Assert.hasText(token, "The field [token] cannot be empty");

		final SecureToken data = secureTokenService.decryptData(token);
		if (sagiaConfigurationFacade.getEmailValidityInSeconds() > 0L)
		{
			final long delta = new Date().getTime() - data.getTimeStamp();
			LOG.info("******* Delta seconds "+delta);
			if (delta / 1000 > sagiaConfigurationFacade.getEmailValidityInSeconds())
			{
				throw new TokenInvalidatedException("Token expired");
			}
		}

		final CustomerModel customer = userService.getUserForUID(data.getData(), CustomerModel.class);
		if (customer == null)
		{
			throw new TokenInvalidatedException("Token not found for the customer");
		}
		if(EMAIL_TYPE_REG.equalsIgnoreCase(type)) {
			if (!token.equals(customer.getToken()))
			{
				LOG.info("***** token expired");
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
						"text.verification.notification.regemail.notverify");
				throw new TokenInvalidatedException("Token expired");
			}
			LOG.info("****** token sucess");
			customer.setToken(null);
			customer.setLoginDisabled(false);
			customer.setRegEmailStatus(RegEmailVerificationStatus.VERIFIED);
			modelService.save(customer);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"text.verification.notification.regemail.verify");
		}else if(EMAIL_TYPE_QEEMAH.equalsIgnoreCase(type)) {
			if (!token.equals(customer.getQeemahToken()))
			{
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
						"text.verification.notification.qeemahemail.notverify");
				throw new TokenInvalidatedException("Token expired");
			}
			customer.setQeemahToken(null);
			customer.setLoginDisabled(false);
			customer.setQeemahEmailStatus(QeemahEmailVerificationStatus.VERIFIED);
			modelService.save(customer);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"text.verification.notification.qeemahemail.verify");

			EmailVerificationData emailVerificationData = new EmailVerificationData();
			if(StringUtils.isNotEmpty(customer.getEntityID())) {
				emailVerificationData.setEntity(customer.getEntityID());
				if(StringUtils.isNotEmpty(customer.getQeemahEmail())) {
					emailVerificationData.setEmailId(customer.getQeemahEmail());
				}
				if(customer.getQeemahEmailStatus() != null && "VERIFIED".equalsIgnoreCase(customer.getQeemahEmailStatus().getCode())) {
					emailVerificationData.setVerified("Y");
				}
				try {
					//emailVerificationSetService.create(emailVerificationData);
					sagiaVerificationFacade.createEmailVerification(emailVerificationData);
				}catch (SagiaCRMException ex){
					LOG.error(ex.getMessage(),ex);
				}

			}
		}

	}

	private void setAuthCodeOnSession(HttpSession session) {
		//String generatedCode = (String) session.getAttribute(AUTH_CODE);
		//if(generatedCode == null) {
		String generatedCode = authenticationCodeGeneratorService.getGeneratedAuthenticationCode();
		session.setAttribute(AUTH_CODE, generatedCode);
		session.setAttribute(AUTH_CODE_GENERATED_DATE, System.currentTimeMillis());
		//}
	}

	private void sendSMS(HttpSession session, Model model) {
		CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
		String mobileCountryCode = customerModel.getMobileCountryCode().startsWith("+") ? customerModel.getMobileCountryCode() : ("+" + customerModel.getMobileCountryCode());

		try {
			smsService.send(mobileCountryCode + customerModel.getMobileNumber(), (String)session.getAttribute(AUTH_CODE));
		} catch(Exception e) {
			LOG.error("stacktrace:", e);
			model.addAttribute(AUTH_CODE_FORM_ERROR, SMS_SERVICE_ERROR);
		}

		LOG.debug("Generated code: "+ session.getAttribute(AUTH_CODE));
	}

	public Validator getSagiaAuthenticateCodeFormValidator() {
		return sagiaAuthenticateCodeFormValidator;
	}

	public String toDashBoard(final HttpSession session, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException {
		if (sagiaConfigurationFacade.isEnableTwoFactorAuthService() && !request.getRequestURL().toString().contains("/billPayment")) {
			return "redirect:/verification/twoFactorAuth";
		} else {
			autoLoginStrategy.login((String) session.getAttribute(HYBRIS_USERNAME),
					(String) session.getAttribute(HYBRIS_PASS), request, response);
			setLastSuccessLoginTime((CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME)));
			session.removeAttribute(HYBRIS_USERNAME);
			session.removeAttribute(HYBRIS_PASS);
			session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
			return REDIRECT_HOME;
		}
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response) {
		if (httpSessionRequestCache.getRequest(request, response) != null) {
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/";
	}

	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache) {
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException {
		return getContentPageForLabelOrId(SAGIA_VERIFICATION_CMS_PAGE);
	}

	@Override
	protected String getView() {
		return ControllerConstants.Views.Pages.Account.VerificationPage;
	}

	@RequestMapping(value = "/twoFactorAuth", method = RequestMethod.GET)
	public String twoFactorAuth(HttpSession session, final Model model, @ModelAttribute final SagiaVerificationForm sagiaVerificationForm, final BindingResult bindingResult, final RedirectAttributes redirectModel) throws CMSItemNotFoundException {
		model.addAttribute(SAGIA_AUTHENTICATE_CODE_FORM, new SagiaAuthenticateCodeForm());
		model.addAttribute(SAGIA_VERIFICATION_FORM, sagiaVerificationForm);
		model.addAttribute(TWO_FACTOR_AUTHENTICATION_ENABLED, sagiaConfigurationFacade.isEnableTwoFactorAuthService());
		CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
		model.addAttribute("maskedEmailID", sagiaVerificationFacade.getMaskedEmail(customerModel));
		model.addAttribute("maskedPhoneNumber", sagiaVerificationFacade.getMaskedPhoneNumber(customerModel));
		setAuthCodeOnSession(session);
		try
		{
			sendSMS(session, model);
			sagiaVerificationFacade.sendTwoFactorAuthenticationMail(session.getAttribute(AUTH_CODE).toString(), EMAIL_TYPE_REG, customerModel);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured while sending SMS or Email to the Customer : "+customerModel.getUserNameEmail(), e);
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	private void setLastSuccessLoginTime(CustomerModel customerModel) {
		try {
			sagiaVerificationFacade.setCustomerLastLoginTime(customerModel);
		}
		catch(Exception e)
		{
			LOG.error("Error while saving the last success login time for the customer "+e);
		}
	}

}
