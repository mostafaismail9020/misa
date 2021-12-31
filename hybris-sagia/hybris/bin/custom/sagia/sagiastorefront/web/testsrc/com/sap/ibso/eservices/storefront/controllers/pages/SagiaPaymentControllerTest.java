package com.sap.ibso.eservices.storefront.controllers.pages;

import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.sagia.PaymentFacade;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit Test Class for SagiaPaymentController
 */
@UnitTest
public class SagiaPaymentControllerTest  extends AbstractCmsContentPageSetupUnitTestHelper{
    private static final String UNEXPECTED_EXCEPTION = "Unexpected exception: ";


    @Spy
    @InjectMocks
    private SagiaPaymentController sagiaPaymentController;

    @Mock
    private PaymentFacade sagiaPaymentFacade;

    @Mock
    private PaymentData paymentData;

    /**
     * Default constructor, initialize spied mocks.
     */
    public SagiaPaymentControllerTest() {

        sagiaPaymentController = new SagiaPaymentController();
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
    public void getPaymentDetailsTest(){
        try {
            given(sagiaPaymentFacade.getPayment("id")).willReturn(paymentData);
            sagiaPaymentController.getPaymentDetails("id", model);
            verify(model).addAttribute("paymentData", paymentData);
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

    @Test
    public void getPaymentDetailsRuntimeExceptionTest(){
        try {
            given(sagiaPaymentFacade.getPayment("id")).willThrow(new RuntimeException("Testing runtime exception.."));
        }catch (Exception ex){
            Assert.fail(UNEXPECTED_EXCEPTION + ex.getMessage());
        }
    }

}
