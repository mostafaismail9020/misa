package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICSectionSetData;
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
public class NipISICSectionServiceTest extends ServicelayerTransactionalTest {

    private static NipISICSectionService nipISICSectionService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipISICSectionService = appCtx.getBean("nipISISCSectionService", NipISICSectionService.class);
        nipISICSectionService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateISICSectionSetData() {
        Collection<NIPISICSectionSetData> nipSICSectionSetList = nipISICSectionService.getCollection("E");
        Assert.assertTrue(nipSICSectionSetList.size() > 0);
        NIPISICSectionSetData data = nipSICSectionSetList.iterator().next();
        String payload = nipISICSectionService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("Isic_SECTION"));
    }

    @Test
    public void shouldCreateISICSectionSetData() {
        NIPISICSectionSetData nipisicSectionSetData = new NIPISICSectionSetData();
        nipisicSectionSetData.setISIC_SECTION("Yemen");
        nipISICSectionService.create(nipisicSectionSetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
