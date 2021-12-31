package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPLegalStatusSetData;
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
public class NipLegalStatusServiceTest extends ServicelayerTransactionalTest {

    private static NipLegalStatusService nipLegalStatusService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipLegalStatusService = appCtx.getBean("nipLegalStatusService", NipLegalStatusService.class);
        nipLegalStatusService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateNIPLegalStatusSetData() {
        Collection<NIPLegalStatusSetData> nipLegalStatusList = nipLegalStatusService.getCollection("E");
        Assert.assertTrue(nipLegalStatusList.size() > 0);
        NIPLegalStatusSetData data = nipLegalStatusList.iterator().next();
        String payload = nipLegalStatusService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("Legal_STATUS"));
    }

    @Test
    public void shouldCreateNIPLegalStatusSetData() {
        NIPLegalStatusSetData nipLegalStatusSetData = new NIPLegalStatusSetData();
        nipLegalStatusSetData.setLEGAL_STATUS("Yemen");
        nipLegalStatusService.create(nipLegalStatusSetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
