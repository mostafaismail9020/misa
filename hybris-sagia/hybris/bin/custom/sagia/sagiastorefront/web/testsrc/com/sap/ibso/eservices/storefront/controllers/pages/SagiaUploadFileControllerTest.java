package com.sap.ibso.eservices.storefront.controllers.pages;


import com.sap.ibso.eservices.core.model.SagiaUploadFilesDataModel;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import com.sap.ibso.eservices.storefront.request.UploadFilesRemoveRequest;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Unit Test Class for SagiaUploadFileController
 */
@UnitTest
public class SagiaUploadFileControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {

    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Mock
    private MediaService mediaService;

    @Mock
    private ModelService modelService;

    @Mock
    private SagiaCustomerFacade sagiaCustomerFacade;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private MultipartHttpServletRequest multipartHttpServletRequest;

    @Mock
    private UploadFilesRemoveRequest requestData;

    @Mock
    private CatalogUnawareMediaModel catalogUnawareMediaModel;

    @Mock
    private SagiaUploadFilesDataModel sagiaUploadFilesDataModel;

    @Mock
    private CustomerData customerData;




    @Spy
    @InjectMocks
    private RedirectAttributes redirectAttributes;


    @Spy
    @InjectMocks
    private SagiaUploadFileController sagiaUploadFileController;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaUploadFileControllerTest() {
        sagiaUploadFileController = new SagiaUploadFileController();
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
    public void sendDataTest(){
        given(multipartFile.isEmpty()).willReturn(false);
        given(modelService.create(CatalogUnawareMediaModel.class)).willReturn(catalogUnawareMediaModel);
        given(modelService.create(SagiaUploadFilesDataModel.class)).willReturn(sagiaUploadFilesDataModel);
        given(sagiaCustomerFacade.getCurrentCustomer()).willReturn(customerData);
        assertEquals(Integer.valueOf(0),   sagiaUploadFileController.sendData(multipartFile, "code", multipartHttpServletRequest).getStatus());


    }

    @Test
    public void sendDataEmptyFileTest() {
     given(multipartFile.isEmpty()).willReturn(true);
     assertEquals(Integer.valueOf(1),   sagiaUploadFileController.sendData(multipartFile, "code", multipartHttpServletRequest).getStatus());
    }

    @Test
    public void removeDataTest(){
        List<String> list =new ArrayList<>();
        list.add("code");

        given(requestData.getCode()).willReturn("myCode");
        given(requestData.getCodes()).willReturn(list);

        sagiaUploadFileController.removeData(requestData);

    }

}
