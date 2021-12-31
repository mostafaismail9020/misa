package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.authentication.AuthenticationCodeGeneratorService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SmsService;
import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import com.sap.ibso.eservices.storefront.forms.SagiaAuthenticateCodeForm;
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
import com.sap.ibso.eservices.facades.sagia.impl.SagiaVerificationFacade;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @Resource
    private SagiaVerificationFacade sagiaVerificationFacade;

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
        setAuthCodeOnSession(session);
        model.addAttribute(SAGIA_AUTHENTICATE_CODE_FORM, new SagiaAuthenticateCodeForm());
        sendSMS(session, model);
        storeCmsPageInModel(model, getCmsPage());
        setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());

        return getView();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(final Model model, final HttpSession session, final HttpServletRequest request, final HttpServletResponse response, @ModelAttribute final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm, final BindingResult bindingResult) throws CMSItemNotFoundException {
        getSagiaAuthenticateCodeFormValidator().validate(sagiaAuthenticateCodeForm, bindingResult);

        if (bindingResult.hasErrors()) {
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
            model.addAttribute("sagiaAuthenticateCodeIncorrectError", CODE_AUTH_ERROR);
            model.addAttribute(sagiaAuthenticateCodeForm);
            storeCmsPageInModel(model, getCmsPage());
            setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
            if(retries >= 5) {
                session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
                try {
                    UserModel user = userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME));
                    user.setLoginDisabled(true);
                    modelService.save(user);
                    return "redirect:/";
                } catch(Exception e) {
                    LOG.error(e.getMessage(), e);
                }
            } else {
                session.setAttribute(SECOND_PAGE_AUTHETICATION_RETRIES, retries + 1);
            }
            return getView();
        }

        autoLoginStrategy.login((String) session.getAttribute(HYBRIS_USERNAME),
                (String) session.getAttribute(HYBRIS_PASS), request, response);
        setLastSuccessLoginTime((CustomerModel)userService.getUserForUID((String)session.getAttribute(HYBRIS_USERNAME)));
        session.removeAttribute(HYBRIS_USERNAME);
        session.removeAttribute(HYBRIS_PASS);
        session.removeAttribute(SECOND_PAGE_AUTHETICATION_RETRIES);
        return "redirect:/";
    }

    @RequestMapping(value = "/resend", method = RequestMethod.GET)
    public String resendAuthenticationCode(HttpSession session, final Model model, @ModelAttribute final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm) throws CMSItemNotFoundException {
        session.setAttribute(AUTH_CODE, authenticationCodeGeneratorService.getGeneratedAuthenticationCode());
        sendSMS(session, model);
        model.addAttribute(sagiaAuthenticateCodeForm);
        storeCmsPageInModel(model, getCmsPage());
        setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
        return getView();
    }

    private void setAuthCodeOnSession(HttpSession session) {
        String generatedCode = (String) session.getAttribute(AUTH_CODE);
        if(generatedCode == null) {
            generatedCode = authenticationCodeGeneratorService.getGeneratedAuthenticationCode();
            session.setAttribute(AUTH_CODE, generatedCode);
            session.setAttribute(AUTH_CODE_GENERATED_DATE, System.currentTimeMillis());
        }
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
