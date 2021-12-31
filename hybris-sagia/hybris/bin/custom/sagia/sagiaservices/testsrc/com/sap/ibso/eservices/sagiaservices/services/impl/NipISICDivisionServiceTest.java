package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.nip.NIPISICDivisionSetData;
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
public class NipISICDivisionServiceTest extends ServicelayerTransactionalTest {

    private static NipISICDivisionService nipISICDivisionService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        nipISICDivisionService = appCtx.getBean("nipISISCDivisionService", NipISICDivisionService.class);
        nipISICDivisionService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateNIPISICDivisionSetData() {
        Collection<NIPISICDivisionSetData> nipSICSectionSetList = nipISICDivisionService.getCollection("E");
        Assert.assertTrue(nipSICSectionSetList.size() > 0);
        NIPISICDivisionSetData data = nipSICSectionSetList.iterator().next();
        String payload = nipISICDivisionService.save(data);
        Assert.assertTrue(StringUtils.isNotEmpty(payload));
        Assert.assertTrue(payload.contains("Isic_DIVISION"));
    }

    @Test
    public void shouldPopulateNIPISICDivisionSetDataBysectionCode() {
        Collection<NIPISICDivisionSetData> nipSICSectionSetList = nipISICDivisionService.getCollection("1", "E");
        Assert.assertTrue(nipSICSectionSetList.size() > 0);
    }

    @Test
    public void shouldCreateNIPISICDivisionSetData() {
        NIPISICDivisionSetData nipisicDivisionSetData = new NIPISICDivisionSetData();
        nipisicDivisionSetData.setISIC_DIVISION("Yemen");
        nipISICDivisionService.create(nipisicDivisionSetData);
        assertThat("Exception was not thrown").isNotNull();
    }
}
