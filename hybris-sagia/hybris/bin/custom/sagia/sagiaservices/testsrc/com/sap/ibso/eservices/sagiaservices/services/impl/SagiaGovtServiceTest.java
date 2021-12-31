package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtService;
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
public class SagiaGovtServiceTest extends ServicelayerTransactionalTest {
    private static SagiaGovtService sagiaGovtService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        sagiaGovtService = appCtx.getBean("sagiaGovtService", SagiaGovtService.class);
        sagiaGovtService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetCRMServiceById() {
        SagiaCRMGovtService sagiaCRMGovtService = sagiaGovtService.getCRMServiceById("1");
        Assert.assertNotNull(sagiaCRMGovtService);
    }

    @Test
    public void shouldGetCRMServicesByCategory() {
        Collection<SagiaCRMGovtService> sagiaCRMGovtServiceList = sagiaGovtService.getCRMServicesByCategory("1");
        Assert.assertTrue(sagiaCRMGovtServiceList.size() > 0);
    }
}
