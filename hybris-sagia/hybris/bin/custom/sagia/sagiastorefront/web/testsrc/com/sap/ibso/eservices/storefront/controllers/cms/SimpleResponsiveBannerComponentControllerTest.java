/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorcms.model.components.SimpleResponsiveBannerComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SimpleSuggestionComponentModel;
import de.hybris.platform.acceleratorfacades.device.ResponsiveMediaFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSComponentService;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.core.model.media.MediaContainerModel;
import junit.framework.Assert;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link SimpleResponsiveBannerComponentController}
 */
@UnitTest
public class SimpleResponsiveBannerComponentControllerTest {
    private static final String COMPONENT_UID = "componentUid";
    private static final String TEST_COMPONENT_UID = "componentUID";
    private static final String TEST_TYPE_CODE = SimpleSuggestionComponentModel._TYPECODE;
    private static final String TEST_TYPE_VIEW = ControllerConstants.Views.Cms.ComponentPrefix
            + StringUtils.lowerCase(TEST_TYPE_CODE);
    private static final String URL_LINK_VALUE = "urlLinkValue";
    private static final String URL_LINK = "urlLink";
    private static final String MEDIAS = "medias";
    private static final String COMPONENT = "component";

    @Mock
    private SimpleResponsiveBannerComponentModel simpleResponsiveBannerComponentModel;
    @Mock
    private Model model;
    @Mock
    private DefaultCMSComponentService cmsComponentService;
    @Mock
    private CommerceCommonI18NService commerceCommonI18NService;
    @Mock
    private ResponsiveMediaFacade responsiveMediaFacade;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ImageData imageData;
    @Mock
    private MediaContainerModel mediaContainerModel;

    @InjectMocks
    private SimpleResponsiveBannerComponentController simpleResponsiveBannerComponentController;

    private final List<ImageData> imageDataList = Collections.singletonList(imageData);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRenderComponent() {
        given(simpleResponsiveBannerComponentModel.getUrlLink()).willReturn(URL_LINK_VALUE);
        given(commerceCommonI18NService.getCurrentLocale()).willReturn(
                Locale.ENGLISH);
        given(simpleResponsiveBannerComponentModel.getMedia(Mockito.any(Locale.class))).willReturn(
                mediaContainerModel);
        given(simpleResponsiveBannerComponentModel.getItemtype()).willReturn(TEST_TYPE_CODE);
        given(
                responsiveMediaFacade.getImagesFromMediaContainer(mediaContainerModel)).willReturn(imageDataList);
        given(request.getAttribute(COMPONENT)).willReturn(simpleResponsiveBannerComponentModel);

        final String viewName = simpleResponsiveBannerComponentController.handleGet(request, response, model);
        verify(model, Mockito.times(1)).addAttribute(COMPONENT, simpleResponsiveBannerComponentModel);
        verify(model, Mockito.times(1)).addAttribute(MEDIAS, imageDataList);
        verify(model, Mockito.times(1)).addAttribute(URL_LINK, URL_LINK_VALUE);
        Assert.assertEquals(TEST_TYPE_VIEW, viewName);
    }

    @Test
    public void testRenderComponentUid() throws Exception {
        given(request.getAttribute(COMPONENT_UID)).willReturn(TEST_COMPONENT_UID);
        given(cmsComponentService.getAbstractCMSComponent(TEST_COMPONENT_UID)).willReturn(simpleResponsiveBannerComponentModel);

        given(simpleResponsiveBannerComponentModel.getUrlLink()).willReturn(URL_LINK_VALUE);
        given(commerceCommonI18NService.getCurrentLocale()).willReturn(
                Locale.ENGLISH);
        given(simpleResponsiveBannerComponentModel.getMedia(Mockito.any(Locale.class))).willReturn(
                mediaContainerModel);
        given(simpleResponsiveBannerComponentModel.getItemtype()).willReturn(TEST_TYPE_CODE);
        given(
                responsiveMediaFacade.getImagesFromMediaContainer(mediaContainerModel)).willReturn(imageDataList);
        given(request.getAttribute(COMPONENT)).willReturn(simpleResponsiveBannerComponentModel);

        final String viewName = simpleResponsiveBannerComponentController.handleGet(request, response, model);
        verify(model, Mockito.times(1)).addAttribute(COMPONENT, simpleResponsiveBannerComponentModel);
        verify(model, Mockito.times(1)).addAttribute(MEDIAS, imageDataList);
        verify(model, Mockito.times(1)).addAttribute(URL_LINK, URL_LINK_VALUE);
        Assert.assertEquals(TEST_TYPE_VIEW, viewName);
    }

    @Test(expected = AbstractPageController.HttpNotFoundException.class)
    public void testRenderComponentNotFound() {
        given(request.getAttribute(COMPONENT_UID)).willReturn(null);
        given(request.getParameter(COMPONENT_UID)).willReturn(null);
        simpleResponsiveBannerComponentController.handleGet(request, response, model);
    }

    @Test(expected = AbstractPageController.HttpNotFoundException.class)
    public void testRenderComponentNotFound2() throws Exception {
        given(request.getAttribute(COMPONENT_UID)).willReturn(null);
        given(request.getParameter(COMPONENT_UID)).willReturn(TEST_COMPONENT_UID);
        given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willReturn(null);
        simpleResponsiveBannerComponentController.handleGet(request, response, model);
    }

    @Test(expected = AbstractPageController.HttpNotFoundException.class)
    public void testRenderComponentNotFound3() throws Exception {
        given(request.getAttribute(COMPONENT_UID)).willReturn(TEST_COMPONENT_UID);
        given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willReturn(null);
        given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willThrow(new CMSItemNotFoundException(""));
        simpleResponsiveBannerComponentController.handleGet(request, response, model);
    }
}
