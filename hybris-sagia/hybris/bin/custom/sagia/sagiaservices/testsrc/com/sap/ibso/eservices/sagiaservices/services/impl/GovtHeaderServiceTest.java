package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
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
public class GovtHeaderServiceTest extends ServicelayerTransactionalTest {

    private static GovtHeaderService govtHeaderService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        govtHeaderService = appCtx.getBean("govtHeaderService", GovtHeaderService.class);
        govtHeaderService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetGovtHeader() throws Exception {
        GovtHeaderData govtHeaderData = govtHeaderService.getGovtHeader("1");
        Assert.assertEquals("Not Verified", govtHeaderData.getStatus());
        Assert.assertEquals("613392", govtHeaderData.getEntity());
    }

    @Test
    public void shouldGetGovtHeaderWithBranchSet() throws Exception {
        GovtHeaderData govtHeaderData = govtHeaderService.getGovtHeaderWithBranchSet("1");
        Assert.assertEquals("Not Verified", govtHeaderData.getStatus());
        Assert.assertEquals("601992", govtHeaderData.getEntity());
    }
}