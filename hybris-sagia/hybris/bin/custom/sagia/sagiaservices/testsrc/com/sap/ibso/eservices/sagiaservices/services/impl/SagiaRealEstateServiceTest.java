package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.*;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@IntegrationTest
public class SagiaRealEstateServiceTest extends ServicelayerTransactionalTest {

    private static SagiaRealEstateService sagiaRealEstateService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        sagiaRealEstateService = appCtx.getBean("sagiaRealEstateService", SagiaRealEstateService.class);
        sagiaRealEstateService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //java.net.HttpRetryException: cannot retry due to redirection, in streaming mode
    @Ignore
    @Test
    public void shouldGetRealEstateHistory() {
        Collection<RealEstateData> historyList = sagiaRealEstateService.getRealEstateHistory();
        Assert.assertEquals(1, historyList.stream()
                .filter(data -> data.getREType().equals("Buy")).count());
    }

    @Test
    public void shouldPopulateRegionCityData() {
        RealEstateData data = sagiaRealEstateService.getRealEstateById("6000291");
        Assert.assertEquals("6000291", data.getObjectId());
    }
}