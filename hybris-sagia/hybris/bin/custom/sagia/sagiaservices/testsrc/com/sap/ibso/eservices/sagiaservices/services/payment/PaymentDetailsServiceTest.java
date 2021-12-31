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

@IntegrationTest
public class PaymentDetailsServiceTest extends ServicelayerTransactionalTest {
    private static PaymentDetailsService paymentDetailsService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        paymentDetailsService = appCtx.getBean("sagiaPaymentDetailsService", PaymentDetailsService.class);
        paymentDetailsService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetPayment() {
        SalesOrderPayment salesOrderPayment = paymentDetailsService.getPayment("18000731");
        Assert.assertEquals("18000731", salesOrderPayment.getServiceId());
    }
}