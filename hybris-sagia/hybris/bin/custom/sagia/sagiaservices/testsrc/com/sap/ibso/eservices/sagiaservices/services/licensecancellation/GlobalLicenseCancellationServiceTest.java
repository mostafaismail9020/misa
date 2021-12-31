package com.sap.ibso.eservices.sagiaservices.services.licensecancellation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GlobalLicenseCancellation;
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
public class GlobalLicenseCancellationServiceTest extends ServicelayerTransactionalTest {
    private static GlobalLicenseCancellationService globalLicenseCancellationService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        globalLicenseCancellationService = appCtx.getBean("globalLicenseCancellationService", GlobalLicenseCancellationService.class);
        globalLicenseCancellationService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetGlobalLicenseCancellation() {
        GlobalLicenseCancellation globalLicenseCancellation = globalLicenseCancellationService.getGlobalLicenseCancellation("1" , "1");
        Assert.assertTrue(globalLicenseCancellation != null);
    }
}

