package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.RegionCityData;
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
public class SagiaRegionCityServiceTest extends ServicelayerTransactionalTest {

    private static SagiaRegionCityService sagiaRegionCityService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        sagiaRegionCityService = appCtx.getBean("sagiaRegionCityService", SagiaRegionCityService.class);
        sagiaRegionCityService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateRegionCityDataByRegion() {
        Collection<RegionCityData> regions = sagiaRegionCityService.getCities("1");
        Assert.assertEquals(1, regions.stream()
                .filter(data -> data.getRegionDesc().equals("Riyadh")).count());
    }

    @Test
    public void shouldPopulateRegionCityData() {
        Collection<RegionCityData> regions = sagiaRegionCityService.getCollection();
        Assert.assertEquals(1, regions.stream()
                .filter(data -> data.getRegionDesc().equals("Riyadh")).count());
    }
}