package com.sap.ibso.eservices.sagiaservices.services.license.odata;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ApplicationStatusData;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.ApplicationStatusService;
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
public class ApplicationStatusServiceTest extends ServicelayerTransactionalTest {

    private static ApplicationStatusService applicationStatusService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        applicationStatusService = appCtx.getBean("applicationStatusService", ApplicationStatusService.class);
        applicationStatusService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldPopulateApplicationStatusData() {
        Collection<ApplicationStatusData> applicationStatusDataList = applicationStatusService.getApplicationStatus("1");
        Assert.assertTrue(applicationStatusDataList.size() > 0);
    }


}
