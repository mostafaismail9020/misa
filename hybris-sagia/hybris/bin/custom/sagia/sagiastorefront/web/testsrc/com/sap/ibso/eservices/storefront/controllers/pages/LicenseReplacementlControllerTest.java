package com.sap.ibso.eservices.storefront.controllers.pages;


import com.google.common.collect.Iterables;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseReplacementFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.services.attachments.ContentDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementFormData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for LicenseReplacementlController
 */
public class LicenseReplacementlControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private LicenseReplacementlController licenseReplacementlController;

    @Mock
    private SagiaLicenseReplacementFacade sagiaLicenseReplacementFacade;

    @Mock
    private Collection<LicenseReplaceMentData> licensereplacements;

    @Mock
    private LicenseReplaceMentData licensereplacement;

    @Mock
    private Collection<CustomizingGetData> licensereplacementinfos;

    @Mock
    private ContentDetailsService contentDetailsService;

    @Mock
    private InputStream inputStream;

    @Mock
    private BindingResult result;

    @Mock
    private LicenseReplacementFormData licenseReplacementFormData;

    @Mock
    private CustomizationListService customizationListService;

    @Mock
    private Collection<CustomizingGetData> supportedAttachments;

    /**
     * Default constructor, initialize spied mocks.
     */
    public LicenseReplacementlControllerTest() {
        licenseReplacementlController = new LicenseReplacementlController();
        licensereplacementinfos = new ArrayList<>();
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
    public void getLicenseReplacementsTest(){
        CustomizingGetData data = new CustomizingGetData();

        given(sagiaLicenseReplacementFacade.getLicenseReplacementDataList()).willReturn(licensereplacements);
        given(licensereplacements.isEmpty()).willReturn(true);
        licensereplacementinfos = Arrays.asList(data);
        given(sagiaLicenseReplacementFacade.getLicenseReplacementInfo()).willReturn(licensereplacementinfos);

        try {
            Assert.assertNull(licenseReplacementlController.getLicenseReplacements(model, ""));
            verify(model).addAttribute("licenseReplaceMents", licensereplacements);
            verify(model).addAttribute("licenseReplaceMentinfo", licensereplacementinfos.stream().findFirst().orElse(null));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }


    @Test
    public void newLicenseReplacementTest(){
        try {
            Assert.assertNull(licenseReplacementlController.newLicenseReplacement(model));
            verify(model).addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void resubmitLicenseReplacementTest(){
        try {
            Assert.assertNull(licenseReplacementlController.resubmitLicenseReplacement(model));
            verify(model).addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getLicenseReplacementTest(){
        CustomizingGetData data = new CustomizingGetData();

        given(sagiaLicenseReplacementFacade.getLicenseReplacementDataList()).willReturn(licensereplacements);
        given(licensereplacements.isEmpty()).willReturn(true);
        licensereplacementinfos = Arrays.asList(data);
        given(sagiaLicenseReplacementFacade.getLicenseReplacementInfo()).willReturn(licensereplacementinfos);
        given(sagiaLicenseReplacementFacade.getLicenseReplacementData("id")).willReturn(licensereplacement);

        try {
            Assert.assertNull(licenseReplacementlController.getLicenseReplacement("id",model));
            verify(model).addAttribute("licenseReplaceMents", licensereplacements);
            model.addAttribute("licensereplacementFormData", licensereplacement);
            model.addAttribute("messages", licensereplacement.getLicenseReplaceMentToTextNav());
            model.addAttribute("uploadedAttachments", licensereplacement.getLicenseReplaceMentToContentNav());
            verify(model).addAttribute("licenseReplaceMentinfo", licensereplacementinfos.stream().findFirst().orElse(null));
        } catch (Exception e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void getAttachmentsTest(){
        given(sagiaLicenseReplacementFacade.getContentDetailsService()).willReturn(contentDetailsService);
        given(contentDetailsService.readAttachmentBy("objectId", "documentId")).willReturn(inputStream);
        assertEquals(HttpStatus.OK, licenseReplacementlController.getAttachments("objectId","documentId",request,model).getStatusCode());
        try {
            assertEquals(inputStream, licenseReplacementlController.getAttachments("objectId","documentId",request,model).getBody().getInputStream());
        } catch (IOException e) {
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
        assertEquals(MediaType.parseMediaType("application/pdf"), licenseReplacementlController.getAttachments("objectId","documentId",request,model).getHeaders().getContentType());

    }

    @Test
    public void submitHasErrorsTest(){
        try{
            given(result.hasErrors()).willReturn(true);
            Assert.assertEquals("conflict",licenseReplacementlController.submit(model,licenseReplacementFormData,result));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }

    @Test
    public void submitHasNoErrorsTest(){
        try{
            given(result.hasErrors()).willReturn(false);
            given(sagiaLicenseReplacementFacade.getCustomizationListService()).willReturn(customizationListService);
            given(customizationListService.readCancelLicenseSupportingAttachments()).willReturn(supportedAttachments);
            Assert.assertNull(licenseReplacementlController.submit(model,licenseReplacementFormData,result));
        }catch (Exception e){
            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
        }
    }
}
