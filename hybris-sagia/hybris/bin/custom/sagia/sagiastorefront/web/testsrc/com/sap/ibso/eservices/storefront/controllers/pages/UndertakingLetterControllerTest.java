package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.SagiaMediaData;
import com.sap.ibso.eservices.facades.sagia.SagiaMediaFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cmsfacades.data.MediaData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

/**
 * Unit Test Class for UndertakingLetterController
 */
@UnitTest
public class UndertakingLetterControllerTest extends AbstractCmsContentPageSetupUnitTestHelper {
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";

    @Spy
    @InjectMocks
    private UndertakingLetterController undertakingLetterController;

    @Mock
    private SagiaMediaFacade sagiaMediaFacade;

    @Mock
    private SagiaMediaData pageMedia;

    /**
     * Default constructor, initialize spied mocks.
     */
    public UndertakingLetterControllerTest() {
        undertakingLetterController = new UndertakingLetterController();
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
    public void getUndertakingLetterTest(){
        List<MediaData> attachments = new ArrayList<>();
        MediaData mediaData = new MediaData();
        mediaData.setCode("undertakingLetterSample");
        attachments.add(mediaData);
        given(sagiaMediaFacade.getSagiaMediaForPageName("undertakingLetter")).willReturn(pageMedia);
        given(pageMedia.getAttachments()).willReturn(attachments);
        Assert.assertEquals(HttpStatus.OK, undertakingLetterController.getUndertakingLetter(request,model).getStatusCode());
        Assert.assertEquals("undertakingLetterUrl", undertakingLetterController.getUndertakingLetter(request,model).getBody().entrySet().iterator().next().getKey());
    }

    @Test
    public void getUndertakingLetterRutimeExceptionTest(){
        try {
            given(sagiaMediaFacade.getSagiaMediaForPageName("undertakingLetter")).willThrow(new RuntimeException("Testing runtime exception.."));
            undertakingLetterController.getUndertakingLetter(request, model);
        }catch (Exception ex)
        {
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

}
