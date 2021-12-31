package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.authentication.AuthenticationCodeGeneratorService;
import com.sap.ibso.eservices.sagiaservices.services.impl.SmsService;
import com.sap.ibso.eservices.storefront.forms.SagiaAuthenticateCodeForm;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorstorefrontcommons.security.AutoLoginStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;

/**
 * Unit Test Class for CodeAuthenticationController
 */
@UnitTest
public class CodeAuthenticationControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Mock
    private UserService userService;

    @Mock
    private AutoLoginStrategy autoLoginStrategy;

    @Mock
    private SmsService smsService;

    @Mock
    private Validator sagiaAuthenticateCodeFormValidator;

    @Mock
    private AuthenticationCodeGeneratorService authenticationCodeGeneratorService;

    @Mock
    private HttpSession httpSession;

    @Mock
    private CustomerModel customerModel;

    @Mock
    private SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm;

    @Mock
    private BindingResult bindingResult;


    @Spy
    @InjectMocks
    private CodeAuthenticationController codeAuthenticationController;


    /**
     * Default constructor, initialize spied mocks.
     */
    public CodeAuthenticationControllerTest() {
        codeAuthenticationController = new CodeAuthenticationController();
    }


    /**
     * Initialize mocks
     */
    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
        setupCmsContentPage();
    }

    @Test
    public void getAuthenticationCodeTest(){
        given(httpSession.getAttribute("AUTHENTICATION_CODE")).willReturn(null);
        given(httpSession.getAttribute("hybrisusername")).willReturn("userUid");
        given(userService.getUserForUID("userUid")).willReturn(customerModel);
        try {
            doNothing().when(smsService).send(anyString(),anyString());
            assertEquals("pages/account/accountLoginPage",codeAuthenticationController.getAuthenticationCode(httpSession, model));
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }

    }

    @Test
    public void authenticateTest() {

        doNothing().when(sagiaAuthenticateCodeFormValidator).validate(sagiaAuthenticateCodeForm, bindingResult);
        given(httpSession.getAttribute("AUTHENTICATION_CODE")).willReturn("code");
        given(sagiaAuthenticateCodeForm.getCode()).willReturn("code");
        given(httpSession.getAttribute("hybrisusername")).willReturn("hybrisusername");
        given(httpSession.getAttribute("hybrispassword")).willReturn("hybrispassword");

        try {
           assertEquals("redirect:/", codeAuthenticationController. authenticate(model,httpSession, request, response, sagiaAuthenticateCodeForm, bindingResult));
        } catch (CMSItemNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void authenticateWithBindingErrorsTest() {

        doNothing().when(sagiaAuthenticateCodeFormValidator).validate(sagiaAuthenticateCodeForm, bindingResult);
        given(bindingResult.hasErrors()).willReturn(true);
        try {
            assertEquals("pages/account/accountLoginPage", codeAuthenticationController. authenticate(model,httpSession, request, response, sagiaAuthenticateCodeForm, bindingResult));
        } catch (CMSItemNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void authenticateIncorrectCodeTest() {

        doNothing().when(sagiaAuthenticateCodeFormValidator).validate(sagiaAuthenticateCodeForm, bindingResult);
        given(httpSession.getAttribute("AUTHENTICATION_CODE")).willReturn("code1");
        given(sagiaAuthenticateCodeForm.getCode()).willReturn("code");

        try {
            assertEquals("pages/account/accountLoginPage", codeAuthenticationController. authenticate(model,httpSession, request, response, sagiaAuthenticateCodeForm, bindingResult));
        } catch (CMSItemNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resendAuthenticationCodeTest(){
        given(httpSession.getAttribute("hybrisusername")).willReturn("hybrisusername");
        given(userService.getUserForUID("userUid")).willReturn(customerModel);

        try {
            doNothing().when(smsService).send(anyString(),anyString());
            assertEquals("pages/account/accountLoginPage",codeAuthenticationController.resendAuthenticationCode(httpSession, model, sagiaAuthenticateCodeForm));
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        } catch (CMSItemNotFoundException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }


    }

}
