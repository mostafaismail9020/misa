package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.site.BaseSiteService;
import org.apache.commons.configuration.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

import java.util.Locale;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for LoginPageController
 */
public class SagiaLoginPageControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;



    @Spy
    @InjectMocks
    private SagiaLoginPageController loginPageController;

    @Mock
    private HttpSession session;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Mock
    private I18NService i18nService;

    @Mock
    private Configuration configuration;

    @Mock
    private BaseSiteService baseSiteService;

    @Mock
    private BaseSiteModel baseSiteModel;

    @Mock
    private MessageSource messageSource;

    @Mock
    private HttpSessionRequestCache httpSessionRequestCache;
    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaLoginPageControllerTest() {
        loginPageController = new SagiaLoginPageController();
        redirectAttributes = new RedirectAttributesModelMap();
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
    public void getDefaultLoginPageTest(){
        try {
            String username = "TEST";
            given(sagiaConfigurationFacade.getPasswordRegex()).willReturn("\\pass");
            given(session.getAttribute("SPRING_SECURITY_LAST_USERNAME")).willReturn(username);
            given(configurationService.getConfiguration()).willReturn(configuration);
            given(baseSiteService.getCurrentBaseSite()).willReturn(baseSiteModel);
            given(i18nService.getCurrentLocale()).willReturn(Locale.ENGLISH);
            Assert.assertEquals("pages/account/accountLoginPage",loginPageController.doLogin("referer",true,model,request,response,session));
            verify(model).addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.INDEX_NOFOLLOW);
            verify(model).addAttribute("loginError", Boolean.valueOf(true));
            verify(model).addAttribute("loginFormError", "login.error.account.not.found.title");
            Assert.assertTrue(model.containsAttribute("breadcrumbs"));
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void storeRefererTest(){

            given(request.getServerName()).willReturn("referer");
        try {
            String username = "TEST";
            given(sagiaConfigurationFacade.getPasswordRegex()).willReturn("\\pass");
            given(session.getAttribute("SPRING_SECURITY_LAST_USERNAME")).willReturn(username);
            given(configurationService.getConfiguration()).willReturn(configuration);
            given(baseSiteService.getCurrentBaseSite()).willReturn(baseSiteModel);
            given(i18nService.getCurrentLocale()).willReturn(Locale.ENGLISH);
            Assert.assertEquals("pages/account/accountLoginPage",loginPageController.doLogin("referer",false,model,request,response,session));
            verify(model).addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.INDEX_NOFOLLOW);
            Assert.assertTrue(model.containsAttribute("breadcrumbs"));
        } catch (Exception e) {
            fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }



    }

}
