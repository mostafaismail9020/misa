//package com.sap.ibso.eservices.storefront.controllers.pages;
//
//import de.hybris.platform.acceleratorfacades.cartfileupload.SavedCartFileUploadFacade;
//import de.hybris.platform.acceleratorservices.config.SiteConfigService;
//import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
//import de.hybris.platform.acceleratorstorefrontcommons.forms.ImportCSVSavedCartForm;
//import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ImportCSVSavedCartFormValidator;
//import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
//import de.hybris.platform.servicelayer.i18n.I18NService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
//
//import java.io.IOError;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//
///**
// * Unit Test Class for ImportCSVPageController
// */
//public class ImportCSVPageControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
//    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";
//
//    @Spy
//    @InjectMocks
//    private ImportCSVPageController importCSVPageController;
//
//    @Mock
//    private ResourceBreadcrumbBuilder resourceBreadcrumbBuilder;
//
//    @Mock
//    private ImportCSVSavedCartFormValidator importCSVSavedCartFormValidator;
//
//    @Mock
//    private SavedCartFileUploadFacade savedCartFileUploadFacade;
//
//    @Mock
//    private SiteConfigService siteConfigService;
//
//    @Mock
//    private ImportCSVSavedCartForm importCSVSavedCartForm;
//
//    @Mock
//    private BindingResult bindingResult;
//
//    @Mock
//    private I18NService i18nService;
//
//    @Mock
//    private MessageSource messageSource;
//
//    @Mock
//    private MultipartFile multipartFile;
//
//    @Mock
//    private InputStream inputStream;
//
//
//    @Mock
//    private ObjectError error;
//
//    /**
//     * Default constructor, initialize spied mocks.
//     */
//    public ImportCSVPageControllerTest() {
//        importCSVPageController = new ImportCSVPageController();
//
//    }
//
//
//    /**
//     * Initialize mocks
//     */
//    @Before
//    public void before() {
//
//        MockitoAnnotations.initMocks(this);
//        setupCmsContentPage();
//    }
//
//    @Test
//    public void savedCartImportTest(){
//        try {
//            Assert.assertEquals("pages/csv/importCSVSavedCartPage", importCSVPageController.savedCartImport(model));
//        } catch (CMSItemNotFoundException e) {
//            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
//        }
//    }
//
//    @Test
//    public void handleSavedCartImportHasErrorsTest(){
//        doNothing().when(importCSVSavedCartFormValidator).validate(importCSVSavedCartForm, bindingResult);
//        given(bindingResult.hasErrors()).willReturn(true);
//        given(i18nService.getCurrentLocale()).willReturn(Locale.ENGLISH);
//
//        given(error.getCode()).willReturn("ERROR");
//        List<ObjectError> errors = new ArrayList<>();
//        errors.add(error);
//        given(bindingResult.getAllErrors()).willReturn(errors);
//
//        try {
//            Assert.assertEquals(HttpStatus.BAD_REQUEST,importCSVPageController.handleSavedCartImport(importCSVSavedCartForm,bindingResult).getStatusCode());
//            Assert.assertEquals(null,importCSVPageController.handleSavedCartImport(importCSVSavedCartForm,bindingResult).getBody());
//        } catch (IOException e) {
//            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
//        }
//    }
//
//
//    @Test
//    public void handleSavedCartImportHasNoErrorsTest(){
//        try {
//            doNothing().when(importCSVSavedCartFormValidator).validate(importCSVSavedCartForm, bindingResult);
//            given(bindingResult.hasErrors()).willReturn(false);
//            given(importCSVSavedCartForm.getCsvFile()).willReturn(multipartFile);
//            given(multipartFile.getInputStream()).willReturn(inputStream);
//
//            Assert.assertEquals(HttpStatus.OK,importCSVPageController.handleSavedCartImport(importCSVSavedCartForm,bindingResult).getStatusCode());
//        } catch (Exception e) {
//            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
//        }
//    }
//
//    @Test
//    public void handleSavedCartImportInternalServerErrorTest(){
//        try {
//            doNothing().when(importCSVSavedCartFormValidator).validate(importCSVSavedCartForm, bindingResult);
//            given(bindingResult.hasErrors()).willReturn(false);
//            given(importCSVSavedCartForm.getCsvFile()).willReturn(multipartFile);
//            given(multipartFile.getInputStream()).willThrow(new IOException("Testing Internal Server Error"));
//
//            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,importCSVPageController.handleSavedCartImport(importCSVSavedCartForm,bindingResult).getStatusCode());
//        } catch (Exception e) {
//            Assert.fail(UNEXPECTED_EXCEPTION + e.getMessage());
//        }
//    }
//}
