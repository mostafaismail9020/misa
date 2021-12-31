package com.sap.ibso.eservices.sagiaservices.services.license.application.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.TelecodeData;
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
public class TelecodeServiceTest extends ServicelayerTransactionalTest {

    private static TelecodeService telecodeService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        telecodeService = appCtx.getBean("telecodeService", TelecodeService.class);
        telecodeService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldGetTelecodeData() {
        TelecodeData telecodeData = telecodeService.get("RO");
        Assert.assertNotNull(telecodeData);
    }


}
