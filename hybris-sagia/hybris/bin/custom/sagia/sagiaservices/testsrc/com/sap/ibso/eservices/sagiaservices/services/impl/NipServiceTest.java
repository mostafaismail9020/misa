package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPHeaderSetData;
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
public class NipServiceTest extends ServicelayerTransactionalTest {

    private static NipService nipService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipService = appCtx.getBean("nipService", NipService.class);
        nipService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateNIPHeaderSetData() {
        Collection<NIPHeaderSetData> nipHeaderList = nipService.get("1");
        Assert.assertEquals(1, nipHeaderList.stream()
                .filter(data -> data.getCAPITAL().equals("25000")).count());
        NIPHeaderSetData data = nipHeaderList.iterator().next();
        String payload = nipService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("25000"));
    }

    @Test
    public void shouldCreateNIPHeaderSetData() {
        NIPHeaderSetData nipHeaderSetData = new NIPHeaderSetData();
        nipHeaderSetData.setREGION("Yemen");
        nipService.create(nipHeaderSetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}