package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPRegionSetData;
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
public class NipRegionServiceTest extends ServicelayerTransactionalTest {

    private static NipRegionService nipRegionService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipRegionService = appCtx.getBean("nipRegionService", NipRegionService.class);
        nipRegionService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateNIPRegionSetData() {
        Collection<NIPRegionSetData> nipRegionList = nipRegionService.getCollection("", "E");
        Assert.assertTrue(nipRegionList.size() > 0);
        NIPRegionSetData data = nipRegionList.iterator().next();
        String payload = nipRegionService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("Region"));
    }

    @Test
    public void shouldCreateNIPRegionSetData() {
        NIPRegionSetData nipRegionSetData = new NIPRegionSetData();
        nipRegionSetData.setREGION("Yemen");
        nipRegionService.create(nipRegionSetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
