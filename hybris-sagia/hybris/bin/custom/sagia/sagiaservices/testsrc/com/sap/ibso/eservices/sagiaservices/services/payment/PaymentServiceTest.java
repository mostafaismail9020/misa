package com.sap.ibso.eservices.sagiaservices.services.payment;

import com.sap.ibso.eservices.sagiaservices.data.payment.SalesOrderPayment;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@IntegrationTest
public class PaymentServiceTest extends ServicelayerTransactionalTest {
    private static PaymentService paymentService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        paymentService = appCtx.getBean("sagiaPaymentService", PaymentService.class);
        paymentService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetPayments() {
        Collection<SalesOrderPayment> salesOrderPayments = paymentService.getPayments();
        Assert.assertTrue(salesOrderPayments.size() > 0);
    }
}