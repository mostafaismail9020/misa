package com.sap.ibso.eservices.storefront.controllers.pages;

import de.hybris.platform.acceleratorservices.storefront.util.PageTitleResolver;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;
import de.hybris.platform.commercefacades.user.UserFacade;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

/**
 * Setup mocks for CMS content page.
 */
public class AbstractCmsContentPageSetupUnitTestHelper {
    @Mock
    protected PageTitleResolver pageTitleResolver;

    @Mock
    protected HttpServletRequest request;

    @Mock
    protected CMSPageService cmsPageService;

    @Mock
    protected UserFacade userFacade;

    @Spy
    protected ContentPageModel contentPageModel = new ContentPageModel();


    @Spy
    @InjectMocks
    protected Model model = new BindingAwareModelMap();

    protected HttpServletResponse response = new MockHttpServletResponse();

    protected void setupCmsContentPage() {
        given(request.getContextPath()).willReturn("/test");
        given(request.getRequestURI()).willReturn("/test");
        given(request.getServletPath()).willReturn("/test");

        given(pageTitleResolver.resolveContentPageTitle(anyString())).willReturn("page_title");
        willReturn("cms_page_title").given(contentPageModel).getTitle();
        willReturn("keyword1, keyword2").given(contentPageModel).getKeywords();
        willReturn("keyword1, keyword2").given(contentPageModel).getKeywords(anyObject());
        willReturn("description").given(contentPageModel).getDescription();
        willReturn("description").given(contentPageModel).getDescription(anyObject());
        try {
            given(cmsPageService.getPageForLabel(anyString())).willReturn(contentPageModel);
            given(cmsPageService.getPageForLabelOrId(anyString())).willReturn(contentPageModel);
        } catch (CMSItemNotFoundException e) {
            Assert.fail();
        }
    }

}
