package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SpCheckHistory;
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
public class SPCheckHistoryServiceTest extends ServicelayerTransactionalTest {

    private static SPCheckHistoryService spCheckHistoryService;

    @BeforeClass
    public static void beforeClass() {
        ApplicationContext appCtx = TestUtil.buildContext();
        spCheckHistoryService = appCtx.getBean("spCheckHistoryService", SPCheckHistoryService.class);
        spCheckHistoryService.getoDataService().setRootUrl("https://sagia.local:9002/sagiaservices");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetSpCheckHistorysForServiceDiscriminator() {
        Collection<SpCheckHistory> spCheckHistoryList = spCheckHistoryService.getCollection("");
        Assert.assertEquals(37, spCheckHistoryList.stream()
                .filter(data -> data.getCAT_CODE1().equals("ZSPLJAWA")).count());
    }

    @Test
    public void shouldGetSpCheckHistory() {
        SpCheckHistory data = spCheckHistoryService.get(1);
        Assert.assertEquals("9000056", data.getOBJECT_ID());
        Assert.assertEquals("E0002", data.getSTATUS());
    }
}