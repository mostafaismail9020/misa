package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCitySetData;
import com.sap.ibso.eservices.sagiaservices.services.util.TestUtil;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class NipCityServiceTest extends ServicelayerTransactionalTest {

    private static NipCityService nipCityService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipCityService = appCtx.getBean("nipCityService", NipCityService.class);
        nipCityService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateNIPCitySetData() {
        Collection<NIPCitySetData> nipCityDataList = nipCityService.getCollection("E");
        Assert.assertTrue(nipCityDataList.size() > 0);
        NIPCitySetData data = nipCityDataList.iterator().next();
        String payload = nipCityService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("Country"));
    }

    @Test
    public void shouldPopulateNIPCitySetDataByRegion(){
        Collection<NIPCitySetData> nipCityDataList = nipCityService.getCollection("EA");
        Assert.assertTrue(nipCityDataList.size() > 0);
    }

    @Test
    public void shouldCreateNIPCitySetData() {
        NIPCitySetData nipCityDataList = new NIPCitySetData();
        nipCityDataList.setCOUNTRY("AF");
        nipCityService.create(nipCityDataList);
        assertThat("Exception was not thrown").isNotNull();
    }
}
