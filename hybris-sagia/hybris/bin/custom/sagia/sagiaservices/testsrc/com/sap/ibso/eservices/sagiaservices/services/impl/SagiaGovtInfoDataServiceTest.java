package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfoDataCRM;
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
public class SagiaGovtInfoDataServiceTest extends ServicelayerTransactionalTest {

    private static SagiaGovtInfoDataService sagiaGovtInfoDataService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        sagiaGovtInfoDataService = appCtx.getBean("sagiaGovtInfoDataService", SagiaGovtInfoDataService.class);
        sagiaGovtInfoDataService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAmendProductsWithId() {
        SagiaGovtServiceInfoDataCRM sagiaGovtServiceInfoDataCRM = sagiaGovtInfoDataService.getInfoData();
        Assert.assertTrue(sagiaGovtServiceInfoDataCRM != null);
    }
}
