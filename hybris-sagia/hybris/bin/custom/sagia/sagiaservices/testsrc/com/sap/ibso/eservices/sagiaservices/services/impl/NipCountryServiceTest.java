package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPCountrySetData;
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
public class NipCountryServiceTest extends ServicelayerTransactionalTest {

    private static NipCountryService nipCountryService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipCountryService = appCtx.getBean("nipCountryService", NipCountryService.class);
        nipCountryService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateNIPCountrySetData() {
        Collection<NIPCountrySetData> nipCountrySetDataList = nipCountryService.getCollection("E");
        Assert.assertTrue(nipCountrySetDataList.size() > 0);
        NIPCountrySetData data = nipCountrySetDataList.iterator().next();
        String payload = nipCountryService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("Country"));
    }

    @Test
    public void shouldPopulateNIPCountrySetDataByNationality() {
        Collection<NIPCountrySetData> nipCountrySetDataList = nipCountryService.getCollection("EN");
        Assert.assertTrue(nipCountrySetDataList.size() > 0);
    }

    @Test
    public void shouldCreateNIPCountrySetData() {
        NIPCountrySetData nipCountrySetData = new NIPCountrySetData();
        nipCountrySetData.setCOUNTRY("AF");
        nipCountryService.create(nipCountrySetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
