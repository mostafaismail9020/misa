package com.investsaudi.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.authentication.AuthenticationCodeGeneratorService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SmsService;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import com.investsaudi.controllers.ControllerConstants;
import com.investsaudi.event.InvestSaudi2FactorAuthEmailEvent;
import com.investsaudi.forms.SagiaAuthenticateCodeForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.hsqldb.lib.StringUtil;


@Controller
@RequestMapping(value = "/login-second-step")
public class CodeAuthenticationController extends AbstractLoginPageController {
    @Resource
    private UserService userService;

    @Resource
    private AutoLoginStrategy autoLoginStrategy;

    @Resource
    private SmsService smsService;

    @Resource
    private ModelService modelService;

    @Resource(name = "sagiaAuthenticateCodeFormValidator")
    private Validator sagiaAuthenticateCodeFormValidator;

    @Resource(name = "defaultAuthenticationCodeGeneratorService")
    private AuthenticationCodeGeneratorService authenticationCodeGeneratorService;
    
    @Autowired
    private EventService eventService;
    
    @Resource(name = "baseStoreService")
    private BaseStoreService baseStoreService;
    
    @Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;
    
    @Resource(name = "commonI18NService")
    private CommonI18NService commonI18NService;

    private static final Logger LOG = Logger.getLogger(CodeAuthenticationController.class);

    private static final String SAGIA_CODE_AUTH_CMS_PAGE = "login-second-step";
    private static final String SAGIA_AUTHENTICATE_CODE_FORM = "sagiaAuthenticateCodeForm";
    private static final String FORM_GLOBAL_ERROR = "form.global.error";
    private static final String CODE_AUTH_ERROR = "form.code.authentication.error";
    private static final String SMS_SERVICE_ERROR = "form.sms.service.error";
    private static final String HYBRIS_USERNAME = "hybrisusername";
    private static final String HYBRIS_PASS = "hybrispassword";
    private static final String AUTH_CODE_FORM_ERROR = "sagiaAuthenticateCodeFormError";
    private static final String AUTH_CODE = "AUTH_CODE";
    private static final String AUTH_CODE_GENERATED_DATE = "AUTH_CODE_GENERATED_DATE";
    private static final String SECOND_PAGE_AUTHETICATION_RETRIES = "SECOND_PAGE_AUTHETICATION_RETRIES";

    private HttpSessionRequestCache httpSessionRequestCache;

    @RequestMapping(method = RequestMethod.GET)
    public String getAuthenticationCode(HttpSession session, final Model model) throws CMSItemNotFoundException {
    	CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
    	LOG.info("**** 2factor success :"+customerModel.getUserNameEmail());
        setAuthCodeOnSession(session);
        //final LoginForm loginForm = new LoginForm();
        model.addAttribute(SAGIA_AUTHENTICATE_CODE_FORM, new SagiaAuthenticateCodeForm());
        model.addAttribute("maskedEmailID", getMaskedEmail(customerModel));
    	model.addAttribute("maskedPhoneNumber", getMaskedPhoneNumber(customerModel));
        //model.addAttribute(loginForm);
        sendSMS(session, model);
        String sessionAuthenticationCode = (String) session.getAttribute(AUTH_CODE);
        eventService.publishEvent(initializeEvent(new InvestSaudi2FactorAuthEmailEvent(sessionAuthenticationCode), customerModel));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));

        return getView();
    }
    
    public String getMaskedEmail(CustomerModel customerModel) {
		String email = customerModel.getUserNameEmail();
		String userName   = email.substring(0, email.lastIndexOf("@"));
		String domain = email.substring(email.lastIndexOf("@") +1);
		if(StringUtil.isEmpty(email))
		{
			return StringUtils.EMPTY;
		}
		else
		{
			return userName.substring(0, 2)+"******"+"@"+domain;
		}
	}
	
	public String getMaskedPhoneNumber(CustomerModel customerModel) {
		String contactNumber=customerModel.getMobileNumber();
		if(StringUtil.isEmpty(contactNumber))
		{
			return StringUtils.EMPTY;
		}
		else
		{
			return "*****"+contactNumber.substring(contactNumber.length() - 4);
		}
	}
    
	protected AbstractCommerceUserEvent initializeEvent(final AbstractCommerceUserEvent event, final CustomerModel customerModel)
	{
		event.setBaseStore(baseStoreService.getCurrentBaseStore());
		event.setSite(baseSiteService.getCurrentBaseSite());
		event.setCustomer(customerModel);
		event.setLanguage(commonI18NService.getCurrentLanguage());
		event.setCurrency(commonI18NService.getCurrentCurrency());
		return event;
	}

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(final Model model, final HttpSession session, final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm, final BindingResult bindingResult) throws CMSItemNotFoundException {
        getSagiaAuthenticateCodeFormValidator().validate(sagiaAuthenticateCodeForm, bindingResult);
        LOG.info("**** validation success");
        if (bindingResult.hasErrors()) {
        	LOG.info("**** has binding error");
            model.addAttribute(AUTH_CODE_FORM_ERROR, FORM_GLOBAL_ERROR);
            model.addAttribute(sagiaAuthenticateCodeForm);
            storeCmsPageInModel(model, getCmsPage());
            setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
            return getView();
        }

        Long sessionAuthenticationCodeGeneratedDate = (Long) session.getAttribute(AUTH_CODE_GENERATED_DATE);
        String sessionAuthenticationCode = (String) session.getAttribute(AUTH_CODE);
        Integer retries = null;
        try {
            retries = (Integer)session.getAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
            LOG.info("**** retries :"+retries);
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
        if(retries == null) {
            retries = 0;
            session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, retries);
        }

        //token expires after 5 minutes
        if ((sessionAuthenticationCodeGeneratedDate + 300000 < System.currentTimeMillis()) ||
                !sessionAuthenticationCode.equals(sagiaAuthenticateCodeForm.getCode().trim())) {
        	LOG.info("**** auth fail session code "+sessionAuthenticationCode+" form code "+sagiaAuthenticateCodeForm.getCode());
            model.addAttribute("sagiaAuthenticateCodeIncorrectError", CODE_AUTH_ERROR);
            model.addAttribute(sagiaAuthenticateCodeForm);
            storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
            setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
            if(retries >= 5) {
                session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
                try {
                	 LOG.info("**** login disabled :");
                    UserModel user = userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
                    user.setLoginDisabled(true);
                    modelService.save(user);
                    return "redirect:/";
                } catch(Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            } else {
            	LOG.info("**** login disabled :"+retries);
                session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, retries + 1);
            }
            CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
            model.addAttribute("maskedEmailID", getMaskedEmail(customerModel));
        	model.addAttribute("maskedPhoneNumber", getMaskedPhoneNumber(customerModel));
            return getView();
        }
        
        LOG.info("**** login success :");
        autoLoginStrategy.login((String) session.getAttribute(HYBRIS_USERNAME),
                (String) session.getAttribute(HYBRIS_PASS), request, response);
        session.removeAttribute(HYBRIS_USERNAME);
        session.removeAttribute(HYBRIS_PASS);
        session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
        return "redirect:/";
    }

    @RequestMapping(value = "/resend", method = RequestMethod.GET)
    public String resendAuthenticationCode(HttpSession session, final Model model, @ModelAttribute final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm) throws CMSItemNotFoundException {
    	CustomerModel customerModel = (CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
    	LOG.info("**** resend success : "+customerModel.getUserNameEmail());
    	session.setAttribute(AUTH_CODE, authenticationCodeGeneratorService.getGeneratedAuthenticationCode());
        session.setAttribute(AUTH_CODE_GENERATED_DATE, System.currentTimeMillis());
        sendSMS(session, model);
        String sessionAuthenticationCode = (String) session.getAttribute(AUTH_CODE);
        eventService.publishEvent(initializeEvent(new InvestSaudi2FactorAuthEmailEvent(sessionAuthenticationCode), customerModel));
        model.addAttribute(sagiaAuthenticateCodeForm);
        model.addAttribute("maskedEmailID", getMaskedEmail(customerModel));
    	model.addAttribute("maskedPhoneNumber", getMaskedPhoneNumber(customerModel));
        storeCmsPageInModel(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE));
        return getView();
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
            smsService.sendSmsInvestSaudi(mobileCountryCode + customerModel.getMobileNumber(), (String)session.getAttribute(AUTH_CODE));
        } catch(Exception e) {
            LOG.error("stacktrace:", e);
            model.addAttribute(AUTH_CODE_FORM_ERROR, SMS_SERVICE_ERROR);
        }

        LOG.warn("Generated code: "+ session.getAttribute(AUTH_CODE));
    }

    public Validator getSagiaAuthenticateCodeFormValidator() {
        return sagiaAuthenticateCodeFormValidator;
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
        return getContentPageForLabelOrId(SAGIA_CODE_AUTH_CMS_PAGE);
    }

    @Override
    protected String getView() {
        return ControllerConstants.Views.Pages.Account.AccountLoginSecondStepVerification;
    }
}
