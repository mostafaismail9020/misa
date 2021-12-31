package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

//@IntegrationTest
public class WarningLettersInfosServiceTest {

    private static WarningLettersInfosService warningLettersInfosService;

    //@BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        warningLettersInfosService = appCtx.getBean("warningLettersInfosService", WarningLettersInfosService.class);
        warningLettersInfosService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    //@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // TODO missing controller mapping and json
}
