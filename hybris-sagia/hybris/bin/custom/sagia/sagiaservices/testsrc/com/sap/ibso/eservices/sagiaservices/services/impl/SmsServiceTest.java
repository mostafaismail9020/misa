package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

//@IntegrationTest
public class SmsServiceTest {

    private static SmsService smsService;

    //@BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        smsService = appCtx.getBean("smsService", SmsService.class);

    }

    //@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }




}
